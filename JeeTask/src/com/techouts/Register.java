package com.techouts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String number = request.getParameter("unumber");
		String mail = request.getParameter("umail");
		// String upass = request.getParameter("upass");
		String repass = request.getParameter("repass");
		String gender = request.getParameter("gender");
		System.out.println(gender);
		//String dob = request.getParameter("udate");
		String loc=request.getParameter("uloc");

		String username = fname + " " + lname;

		String url = "jdbc:mysql://localhost:3306";
		String user = "root";
		String password = "techouts";

		String query = "insert into ravindradb.regester values (?,?,?,?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, number);
			preparedStatement.setString(3, mail);
			preparedStatement.setString(4, repass);
			preparedStatement.setString(5, gender);
			//Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dob);
			preparedStatement.setString(6, loc);
			preparedStatement.executeUpdate();

			PrintWriter printWriter = response.getWriter();
			printWriter.print("Successfully Registered");
			RequestDispatcher dispatcher=request.getRequestDispatcher("Login.html");
			dispatcher.include(request, response);

			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
