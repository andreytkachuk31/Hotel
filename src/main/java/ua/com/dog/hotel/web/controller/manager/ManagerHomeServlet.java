package ua.com.dog.hotel.web.controller.manager;

import ua.com.dog.hotel.util.Path;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerHomeServlet extends HttpServlet {

	private static final long serialVersionUID = 3936989028674182227L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Path.PAGE_HOME_MANAGER).forward(request, response);
	}
}
