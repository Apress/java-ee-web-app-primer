//NewsFeed.java
package controller;


import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bhpost;
import service.DbPost;


@WebServlet("/Newsfeed")
public class Newsfeed extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Newsfeed() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//users can get to this servlet through a get request so handle it here
		//With a get request the parameters are part of the url. 
		//We already handle everything in doPost so just call that.
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long filterByUserID = 0; 
		String searchtext = "";
		
		//set the value of the next page. It should change in the code below.
		String nextURL = "/error.jsp";
		
		//get user out of session. 
		//If they don't exist then send them back to the login page.
		//kill the session while you're at it.
		HttpSession session = request.getSession();
		if (session.getAttribute("user")==null){
			nextURL = "/login.jsp";
			session.invalidate();
			response.sendRedirect(request.getContextPath() + nextURL);
		    return;//return prevents an error
		}
		
		//get posts based on parameters; if no parameters then get all posts
		List<Bhpost> posts = null;
		if (request.getParameter("userid")!=null 
				&& !request.getParameter("userid").isEmpty()){
			filterByUserID = Integer.parseInt(request.getParameter("userid"));
			posts = DbPost.postsofUser(filterByUserID);
			
		}else if (request.getParameter("searchtext")!=null 
				&& !request.getParameter("searchtext").isEmpty()){
			searchtext = request.getParameter("searchtext").toString();
			posts = DbPost.searchPosts(searchtext);
			
		}else{
			posts = DbPost.bhPost();
		}
		
		//add posts to request 
		request.setAttribute("posts", posts);
		//display posts in newsfeed.jsp
		nextURL = "/newsfeed.jsp";
		//redirect to next page as indicated by the value of the nextURL variable
		getServletContext().getRequestDispatcher(nextURL).forward(request,response);
	}

}
//end of NewsFeed.java