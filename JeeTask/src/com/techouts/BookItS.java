package com.techouts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookItS extends HttpServlet
{
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException 
{
	String id = req.getParameter("id");
	String url = "jdbc:mysql://localhost:3306";
	String user = "root";
	String password = "techouts";

	ServletContext context = getServletContext();
	context.setAttribute("ids", id);

	String query = "select * from ravindradb.superherocomics where bookid=?";

	Connection connection;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<h2>BookDeatails Are</h2>");
		if (resultSet.next()) {
			/*printWriter.println("<tr><td>" + resultSet.getString(1) +" "+ "</td><td>" + resultSet.getString(2)
					+ "</td><td>" + resultSet.getString(3) + "</td></tr>");
*/
			context.setAttribute("name", resultSet.getString(1));
			context.setAttribute("author", resultSet.getString(2));
			context.setAttribute("price", resultSet.getString(4));
			RequestDispatcher dispatcher = req.getRequestDispatcher("/DeleteS");
			dispatcher.include(req, resp);
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/SuperHeroComics");
			dispatcher.include(req, resp);
		}
		//System.out.println("thanks for buying");
		//connection.prepareStatement("delete from ravindradb.thriller where bookid='id'").executeUpdate();
		
	} catch (Exception e) {

		e.printStackTrace();
	}

	
}
}
