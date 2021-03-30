package com.techouts;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String phone=req.getParameter("unum");
		String pass=req.getParameter("upass");

		String url = "jdbc:mysql://localhost:3306";
		String user = "root";
		String password = "techouts";

		ServletContext context=getServletContext();
		context.setAttribute("name", phone);
		
		String query="select userphonenumber from ravindradb.regester where userphonenumber=? and userpassword=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		
		preparedStatement.setString(1, phone);
		preparedStatement.setString(2, pass);
		
		ResultSet resultSet=preparedStatement.executeQuery();
		
		if(resultSet.next())
		{
			RequestDispatcher dispatcher=req.getRequestDispatcher("BookHome.jsp");
			dispatcher.include(req, resp);
		}
		else
		{
			RequestDispatcher  dispatcher=req.getRequestDispatcher("Login.html");
			dispatcher.include(req, resp);
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
