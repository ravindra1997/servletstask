package com.techouts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteT extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		String id = (String) context.getAttribute("ids");
		
		String url = "jdbc:mysql://localhost:3306";
		String user = "root";
		String password = "techouts";

		String query = "delete from ravindradb.thriller where bookid=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection(url, user, password);

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, id);

			int i=preparedStatement.executeUpdate();
			

		
		String bname = (String) context.getAttribute("name");
		String author = (String) context.getAttribute("author");
		String price = (String) context.getAttribute("price");

		
		
		PrintWriter out = resp.getWriter();
		out.println("<html><body>");  
		out.println("<table><th>Bookname</th><th>BookAuthor</th><th>BookId</th><th>BookPrice</th>");  
		out.println("</body></html>");  
		out.println("<tr><td>" + id + " " + "</td><td>" + bname + "</td><td>" + author + "</td><td>" + price
				+ "</td></tr>");

		out.print("if you want to continue then <a href='BookHome.jsp'>Continue</a>");

		
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
