package dao;

import java.sql.*;

import model.Book;
import util.Str;

public class BookDao {

	public int add(Connection con,Book book)throws Exception
	{
		String preSql="select * from book where no = ?";
		PreparedStatement prePstmt=con.prepareStatement(preSql);
		prePstmt.setString(1, book.getNo());
		ResultSet rs=prePstmt.executeQuery();
		
		if(!rs.isBeforeFirst())
		{
			String sql="insert into book(no,name,author,type,publisher,storage)value(?,?,?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, book.getNo());
			pstmt.setString(2, book.getName());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getType());
			pstmt.setString(5, book.getPublisher());
			pstmt.setLong(6, book.getStorage());
			return pstmt.executeUpdate();
		}
		else
		{
			String sql="update book set name=?,author=?,type=?,publisher=?,storage=? where no = ?";
			int num=0;
			String name="";
			String author="";
			String type="";
			String publisher="";
			while(rs.next())
			{
				num=rs.getInt("storage");
				author=rs.getString("author");
				type=rs.getString("type");
				publisher=rs.getString("publisher");
				name=rs.getString("name");
			}
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, book.getName().isEmpty()?name:book.getName());
			pstmt.setString(2, book.getAuthor()=="佚名"?author:book.getAuthor());
			pstmt.setString(3, book.getType()=="其他"?type:book.getType());
			pstmt.setString(4, book.getPublisher()=="其他"?publisher:book.getPublisher());
			pstmt.setLong(5, book.getStorage()+num);
			pstmt.setString(6, book.getNo());
			
			return pstmt.executeUpdate();
		}

		
	}
	
	public ResultSet list(Connection con,Book book)throws Exception
	{
		StringBuffer sb=new StringBuffer("select * from book");
		if(!Str.isEmpty(book.getNo()))
		{
			sb.append(" or no like '%"+book.getNo()+"%'");
		}
		if(!Str.isEmpty(book.getName()))
		{
			sb.append(" or name like '%"+book.getName()+"%'");
		}
		if(!Str.isEmpty(book.getAuthor()))
		{
			sb.append(" or author like '%"+book.getAuthor()+"%'");
		}
		if(!Str.isEmpty(book.getType()))
		{
			sb.append(" or type like '%"+book.getType()+"%'");
		}
		if(!Str.isEmpty(book.getPublisher()))
		{
			sb.append(" or publisher like '%"+book.getPublisher()+"%'");
		}
		String sql=sb.toString().replaceFirst("or", "where");
		PreparedStatement pstmt=con.prepareStatement(sql);
		
		return pstmt.executeQuery();
	}
	
	public int delete(Connection con,String no)throws Exception
	{
		String preSql="select * from record where no = ?";
		PreparedStatement prePstmt=con.prepareStatement(preSql);
		prePstmt.setString(1, no);
		ResultSet rs=prePstmt.executeQuery();
		
		if(!rs.next())
		{
			String sql="delete from book where no = '"+no+"'";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.execute();
			return 1;
		}
		return 0;
	}
	
	public int update(Connection con,Book book)throws Exception
	{
		StringBuffer sb=new StringBuffer("update book");
		if(!Str.isEmpty(book.getName()))
		{
			sb.append(" , name = '"+book.getName()+"'");
		}
		if(!Str.isEmpty(book.getAuthor()))
		{
			sb.append(" , author = '"+book.getAuthor()+"'");
		}
		if(!Str.isEmpty(book.getType()))
		{
			sb.append(" , type = '"+book.getType()+"'");
		}
		if(!Str.isEmpty(book.getPublisher()))
		{
			sb.append(" , publisher = '"+book.getPublisher()+"'");
		}
		if(book.getStorage()>= 0)
		{
			sb.append(" , storage = "+book.getStorage());
		}
		sb.append(" where no = '"+book.getNo()+"'");
		String sql=sb.toString().replaceFirst(",", "set");
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
