package com.priya.controller;

import java.io.IOException;
import java.util.List;

import com.priya.dao.UserDAO;
import com.priya.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserDAO userDao;
	
	public void init() {
		userDao = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if (action.equals("insert")) {
			insertUser(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");
	    
	    if (action == null || action.equals("list")) { 
	        viewUsers(request, response);
	    } 
	}

	private void viewUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> userList = userDao.getAllUsers();
	    System.out.println("Users fetched from DB: " + userList);
		request.setAttribute("userList", userList);
		System.out.println("User list size: " + userList.size());
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String name = request.getParameter("userName");
		String email = request.getParameter("email");
		
		User user = new User(name, email);
		try {
			userDao.addUser(user);
			response.sendRedirect("UserController?action=list");
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Failed to add user.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}