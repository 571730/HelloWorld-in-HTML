package no.hvl.dat152.obl4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat152.obl4.database.AppUser;
import no.hvl.dat152.obl4.database.SearchItem;
import no.hvl.dat152.obl4.database.SearchItemDAO;


@WebServlet("/mydetails")
public class MyDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (RequestHelper.isLoggedIn(request)) {
			
			AppUser user = (AppUser) request.getSession().getAttribute("user");

			SearchItemDAO searchItemDAO = new SearchItemDAO();
			List<SearchItem> myhistory = searchItemDAO
					.getSearchHistoryForUser(user.getUsername());

			request.setAttribute("myhistory", myhistory);

			request.getRequestDispatcher("mydetails.jsp").forward(request,
					response);
		} else {
			request.getSession().invalidate();
			request.getRequestDispatcher("index.html").forward(request,
					response);
		}
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
