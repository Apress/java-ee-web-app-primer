//AddUser.java
package controller;


import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bhuser;
import service.DbUser;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddUser() {
        super();
    }


	protected void doPost(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		//This page does not require user to be logged in
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");
		String userMotto = request.getParameter("userMotto");
		String nextURL = "/error.jsp";
		//check if user exists (by email)
		Bhuser user = DbUser.getUserByEmail(userEmail);
		
		//create user and add them if they don't exit
		if (user == null){
			user = new Bhuser();
			user.setUsername(userName);
			user.setUseremail(userEmail);
			user.setUserpassword(userPassword);
			Date joindate = Calendar.getInstance().getTime();
			user.setJoindate(joindate);
			user.setMotto(userMotto);
			DbUser.insert(user);
			nextURL = "/home.jsp";
		}else{
			String message = "You have an account - ";
			request.setAttribute("message", message);
			nextURL = "/login.jsp";
		}
		
		//add the user to the session
		session.setAttribute("user", user);
		
		//redirect to next page as indicated by the value of the nextURL variable
		getServletContext().getRequestDispatcher(nextURL)
								.forward(request,response);
	}

}
// end of AddUser.java