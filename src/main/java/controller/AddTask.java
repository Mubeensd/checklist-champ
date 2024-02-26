package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.TodoService;
@WebServlet("/addtask")
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	TodoService service=new TodoService();
	if(request.getSession().getAttribute("user")!=null) {
	service.addtask(request,response);
	
	
	}
	else {
		response.getWriter().print("<h1 align='center' style='color:red'>login first<h1>");
	}
	}

}
