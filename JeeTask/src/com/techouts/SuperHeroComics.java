package com.techouts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuperHeroComics extends HttpServlet
{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
	
		String url = "jdbc:mysql://localhost:3306";
		String user = "root";
		String password = "techouts";
		
		String query="select * from ravindradb.superherocomics";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, user, password);
			
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			PrintWriter pw=resp.getWriter();
			
			pw.println("<html><body>");  
			pw.println("<table><th>Bookname</th><th>BookAuthor</th><th>BookId</th><th>BookPrice</th>");  
			pw.println("</body></html>");  
			while(resultSet.next())
			{
				String bname=resultSet.getString(1);
				String bauthor=resultSet.getString(2);
				String bookid=resultSet.getString(3);
				String bookprice=resultSet.getString(4);
				pw.println("<html><body>"); 
				pw.println("<tr><td>"+bname+"</td><td>"+bauthor+"</td><td>"+bookid+"</td><td>"+bookid+"</td></tr>");
				pw.println("</body></html>");  
				//System.out.println(resultSet.getString(1));
			}
			//pw.println("<html><body>");
			pw.print("<form action='BookIts'method='post'>");
			pw.print("Please Enter BookId :<input type='text' name='id'><br>");
			pw.print("<input type='submit' value='get'>");
			//pw.println("</body></html>");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	
}
}
