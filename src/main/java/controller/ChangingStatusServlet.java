package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.TodoService;

@WebServlet("/chageStatus")
public class ChangingStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("user")!=null) {
			TodoService service=new TodoService();
			service.changesatus(request, response);
		}
		else {
			response.getWriter().print("<h1 align='center' style='color:red'>login first<h1>");
		}
		}
		
		
		
	}

