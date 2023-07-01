package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Book;
import model.User;
import util.Str;

public class CardDao {
	public int cardNumber(Connection con,String username)throws Exception
	{
		String sql="select * from card where username = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,username);
		ResultSet rs=pstmt.executeQuery();
		int num=0;
		while(rs.next())
		{
			num=rs.getRow();
		}
		return num;
	}
	
	public int add(Connection con,User user)throws Exception
	{
		String sql="select * from user where username = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,user.getUsername());
		ResultSet rs=pstmt.executeQuery();
		
		String password="";
		while(rs.next())
		{
			password=rs.getString("password");
		}
		if(password.equals(user.getPassword()))
		{
			sql="insert into card (username)value(?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.execute();
			
			int cardNo=0;
			sql="select * from card where username = '"+user.getUsername()+"'";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				cardNo=rs.getInt("cardNo");
			}
			return cardNo;
		}
		else
		{
			return 0;
		}
	}
	
	public ResultSet listCard(Connection con,String username)throws Exception
	{
		String sql="select * from card where username = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, username);
		
		return pstmt.executeQuery();
	}
}
