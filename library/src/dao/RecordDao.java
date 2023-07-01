package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import model.Card;

public class RecordDao {
	public int add(Connection con,int cardNo,String no)throws Exception
	{
		String sql="insert into record (cardNo,no)value(?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, cardNo);
		pstmt.setString(2, no);
		pstmt.executeUpdate();
		
		sql="update card set amount = amount + 1 where cardNo = ?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, cardNo);
		pstmt.execute();
		
		sql="update book set storage = storage - 1 where no = '"+no+"'";
		pstmt=con.prepareStatement(sql);
		pstmt.execute();
		return 0;
	}
	
	public int remove(Connection con,int cardNo,String no)throws Exception
	{
		String sql="delete from record where cardNo = ? and no = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, cardNo);
		pstmt.setString(2, no);
		int num=pstmt.executeUpdate();
		
		sql="update card set amount = amount - ? where cardNo = ?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.setInt(2, cardNo);
		pstmt.execute();
		
		sql="update book set storage = storage + ? where no = '"+no+"'";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.execute();
		return num;
	}
	
	public int returnAll(Connection con,int cardNo)throws Exception
	{
		String sql="select * from card where cardNo = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, cardNo);
		ResultSet rs=pstmt.executeQuery();
		if(!rs.next())
		{
			return -1;//没有该借书证
		}
		
		sql="select * from record where cardNo = ?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, cardNo);
		rs=pstmt.executeQuery();
		
		int flag=0;
		while(rs.next())
		{
			String no=rs.getString("no");
			sql="update book set storage = storage + 1 where no = '"+no+"'";
			pstmt=con.prepareStatement(sql);
			pstmt.execute();
			flag=1;
		}
		
		if(flag==1)
		{
			sql="delete from record where cardNo = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cardNo);
			int num=pstmt.executeUpdate();
			
			sql="update card set amount = amount - ? where cardNo = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, cardNo);
			pstmt.execute();
		}
		return flag;
	}
	
	public int deleteCard(Connection con, int cardNo)throws Exception
	{
		String sql="select * from card where cardNo = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, cardNo);
		ResultSet rs=pstmt.executeQuery();
		if(!rs.next())
		{
			return -1;//没有该借书证
		}
		else
		{
			if(rs.getInt("amount")!=0)
			{
				return 1;//有书未还
			}
			else
			{
				sql="delete from card where cardNo = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, cardNo);
				pstmt.execute();
				return 0;//成功注销
			}
		}
	}
	
	public int deleteUser(Connection con,String username)throws Exception
	{
		String sql="select * from user where username = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs=pstmt.executeQuery();
		if(!rs.next())
		{
			return -1;//没有该用户
		}
		else
		{
			sql="select * from card where username = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			
			while(rs.next())//处理借书证
			{
				returnAll(con,rs.getInt("cardNo"));
				deleteCard(con,rs.getInt("cardNo"));
			}
			
			sql="delete from user where username = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.execute();
			return 0;//删除成功
		}
	}
	
	public boolean checkUserCard(Connection con,String username)throws Exception
	{
		String sql="select * from card where username = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int checkCard(Connection con,Card card)throws Exception
	{
		String sql="select * from card where cardNo = ? and username = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, card.getCardNo());
		pstmt.setString(2, card.getUsername());
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
		{
			if(rs.getInt("amount")>=10)
			{
				return 2;//借书量满
			}
			else if(rs.getInt("amount")==0)
			{
				return 3;//没有借书
			}
			return 1;//有证且借书量未满
		}
		return 0;//无此证
	}
	public boolean checkPassword(Connection con,String username,String password)throws Exception
	{
		String sql="select * from user where username = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,username);
		ResultSet rs=pstmt.executeQuery();
		
		String correctPassword="";
		while(rs.next())
		{
			correctPassword=rs.getString("password");
		}
		if(correctPassword.equals(password))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int checkBlankBorrow(int cardNo, String password)
	{
		if(cardNo==0)
		{
			return 0;//卡号为空
		}
		else if(password.isEmpty())
		{
			return 1;// 密码为空
		}
		else
		{
			return 2;//正确
		}
	}
	public int checkBlankReturn(int cardNo, String no)
	{
		if(cardNo==0)
		{
			return 0;//卡号为空
		}
		else if(no.isEmpty())
		{
			return 1;// 书号为空
		}
		else
		{
			return 2;//正确
		}
	}
	
	public ResultSet getRecords(Connection con,int cardNo)throws Exception
	{
		String sql="select * from record where cardNo = "+cardNo;
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		return rs;
	}
	
	public DefaultTableModel getBooks(Connection con,ResultSet rsRecord,DefaultTableModel dtm) throws Exception
	{
			while(rsRecord.next())
			{
				String no=rsRecord.getString("no");
				String sql="select * from book where no = '"+no+"'";
				PreparedStatement pstmt=con.prepareStatement(sql);
				ResultSet rs=pstmt.executeQuery();
				rs.next();
				Vector v=new Vector();
				v.add(rs.getString("no"));
				v.add(rs.getString("name"));
				v.add(rs.getString("author"));
				v.add(rs.getString("type"));
				v.add(rs.getString("publisher"));
				dtm.addRow(v);
			}
		return dtm;
	}
}
