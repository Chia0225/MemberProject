package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.MemberDao;
import model.Member;
import util.DbConnection;

public class MemberDaoImpl implements MemberDao{

	public static void main(String[] args) {
		System.out.println(new MemberDaoImpl().findByUsername("tungchia"));

	}

	private static Connection conn=DbConnection.getDb();
	
	@Override
	public void addMember(Member m) {
		/*
		 * 1.SQL語法
		 * 2.交給PreparedStatement
		 * 3.executeUpdate
		 */
		
		String SQL="insert into member(name,username,password,address,phone,mobile) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1, m.getName());
			ps.setString(2, m.getUsername());
			ps.setString(3, m.getPassword());
			ps.setString(4, m.getAddress());
			ps.setString(5, m.getPhone());
			ps.setString(6, m.getMobile());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Member findByUsernameAndPassword(String username, String password) {
		/*
		 * 1.SQL語法
		 * 2.Preparedstatement-->executeQuery()
		 * 3.ResultSet-->Member
		 * 
		 */
		String SQL="select * from member where username=? and password=?";
		Member m=null;
		try {
			PreparedStatement preparedStatemnt=conn.prepareStatement(SQL);
			preparedStatemnt.setString(1, username);
			preparedStatemnt.setString(2, password);
			ResultSet resultSet=preparedStatemnt.executeQuery();
			
			if(resultSet.next())
			{
				m=new Member();
				m.setId(resultSet.getInt("id"));
				m.setName(resultSet.getString("name"));
				m.setUsername(resultSet.getString("username"));
				m.setPassword(resultSet.getString("password"));
				m.setAddress(resultSet.getString("address"));
				m.setPhone(resultSet.getString("phone"));
				m.setMobile(resultSet.getString("mobile"));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public boolean findByUsername(String username) {
		/*
		 * 1.SQL語法,boolean變數
		 * 2.PreparedStatement-->executeQuery
		 * 3.ResultSet-->true/false
		 */
		
		String SQL="select * from member where username=?";
		boolean isUsernameBeenUse=false;
		
		try {
			PreparedStatement preparedStatemnt=conn.prepareStatement(SQL);
			preparedStatemnt.setString(1, username);
			ResultSet resultSet=preparedStatemnt.executeQuery();
			if(resultSet.next()) isUsernameBeenUse=true;		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isUsernameBeenUse;
	}

}