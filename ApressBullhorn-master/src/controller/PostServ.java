//PostServ.java
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import model.Bhpost;
import model.Bhuser;
import service.DbPost;


@WebServlet("/PostServ")
public class PostServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostServ() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, 
					HttpServletResponse response) 
							throws ServletException, IOException {
		
		String posttext = request.getParameter("posttext");
		String nextURL = "/error.jsp";
		
		//Get user out of session. If they don't exist then 
		//end the session and send them back to the login page.
		HttpSession session = request.getSession();
		if (session.getAttribute("user")==null){
			nextURL = "/login.jsp";
			session.invalidate();
		} else {

		//Get the user out of the session
		Bhuser bhuser = (Bhuser)session.getAttribute("user");
	
		//insert the post
		Bhpost bhPost = new Bhpost();
		bhPost.setBhuser(bhuser);
		Date postdate = Calendar.getInstance().getTime();//today's date
		bhPost.setPostdate(postdate);
		bhPost.setPosttext(posttext);	
		DbPost.insert(bhPost);
		nextURL = "/Newsfeed";//go to newsfeed servlet to show all posts
		}
		
		//the value of nextURL will be newsfeed, login or error
		getServletContext().getRequestDispatcher(nextURL)
		    .forward(request, response);
	}
}
//end of PostServ.java