package dao;

import model.User;
import java.sql.*;
public class UserDao {
    public User login(Connection con,User user)throws Exception
    {
        User result=null;
        String sql="select * from user where username=? and password = ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2, user.getPassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next())
        {
            result=new User();
            result.setId(rs.getInt("id"));
            result.setUsername(rs.getString("username"));
            result.setPassword(rs.getString("password"));
        }
        pstmt.close();
        con.close();
        return result;
    }
    public User loginA(Connection con,User user)throws Exception
    {
        User result=null;
        String sql="select * from auser where username=? and password=?";

        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getPassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next())
        {
            result=new User();
            result.setId(rs.getInt("id"));
            result.setUsername(rs.getString("username"));
            result.setPassword(rs.getString("password"));
        }
        pstmt.close();
        con.close();
        return result;
    }
	public int register(Connection con, User user)throws Exception 
	{
		String sql="select * from user where username=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next())
        {
        	return 0;//用户名已存在
        }
        
		sql="insert into user(username,password)value(?,?)";
		pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getPassword());
        pstmt.execute();
        return 1;
	}
	
	public int add(Connection con,User user)throws Exception
	{
		String sql="select * from auser where username=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next())
        {
        	return 0;//用户名已存在
        }
        
		sql="insert into auser(username,password)value(?,?)";
		pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getPassword());
        pstmt.execute();
        return 1;
	}
	
	public int delete(Connection con, User user)throws Exception
	{
		String sql="select * from auser where username=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        ResultSet rs = pstmt.executeQuery();
        if(!rs.next())
        {
        	return 0;//用户名不存在
        }
        else
        {
        	sql="delete from auser where username=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,user.getUsername());
            pstmt.execute();
        	return 1;
        }
	}
}
