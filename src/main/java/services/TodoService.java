package services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TodoDao;
import dto.TodoUser;
import dto.Todotask;
import net.bytebuddy.build.Plugin.Engine.Listener.WithTransformationsOnly;

public class TodoService {

	TodoUser user = new TodoUser();
	TodoDao dao = new TodoDao();
	Todotask task = new Todotask();
	HttpSession session;
	private List<Todotask> findbytaskid;

	public void signup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		long number = Long.parseLong(request.getParameter("number"));
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");

		user.setName(name);
		user.setEmail(email);
		user.setNumber(number);
		user.setGender(gender);
		user.setPassword(password);

		List<TodoUser> list = dao.findByEmail(user.getEmail());

		if (list.isEmpty()) {
			dao.saveData(user);
			response.getWriter().print("<h1 style='color:green'> account created succesfull</h1>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		List<TodoUser> list = dao.findByEmail(email);

		if (list.isEmpty()) {
			resp.getWriter().print("<h1 style='color:red'>Email is invalid</h1>");
		} else {
			TodoUser user = list.get(0);
			if (user.getPassword().equals(password)) {
				session = req.getSession();
				session.setAttribute("user", user);
				session.setAttribute("msg", "login succesfull");

				resp.sendRedirect("home.jsp");
			} else {
				resp.getWriter().print("<h1 style='color:red'>invalid password");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
		}

	}

	public void addtask(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		task.setTaskName(request.getParameter("taskname"));
		task.setDescirption(request.getParameter("description"));
		task.setCreatedTime(LocalDateTime.now());
		task.setUser(user = (TodoUser) request.getSession().getAttribute("user"));
		task.setStatus(false);

		if (task != null) {
			dao.saveTask(task);
			response.getWriter().print("<h1 style='color:green'> Tast Added  succesfull</h1>");
			List<Todotask> tasklist = dao.findbytaskid(user.getId());
			request.setAttribute("tasks", tasklist);
			request.getRequestDispatcher("home.jsp").include(request, response);

		}

	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().removeAttribute("user");
		resp.getWriter().print("<h1 align='center' style='color:green'>logout <h1>");
		resp.sendRedirect("login.html");
	}

	public void changesatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		dao.getById(Integer.parseInt(request.getParameter("id")));

		List<Todotask> tasklist = dao.findbytaskid(user.getId());
		request.setAttribute("tasks", tasklist);
		request.getRequestDispatcher("home.jsp").include(request, response);

	}

	public void delteteTast(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (dao.deletById(Integer.parseInt(req.getParameter("id")))) {
			List<Todotask> tasklist = dao.findbytaskid(user.getId());
			req.setAttribute("tasks", tasklist);
			req.getRequestDispatcher("home.jsp").include(req, resp);
			resp.getWriter().print("<h1 style='color:green'> task deleted <h1>");
		} else {
			List<Todotask> tasklist = dao.findbytaskid(user.getId());
			req.setAttribute("tasks", tasklist);
			req.getRequestDispatcher("home.jsp").include(req, resp);
			resp.getWriter().print("<h1 style='color:green'> task is already deleted <h1>");
		}

	}

	public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskname=req.getParameter("taskname");
		String discription=req.getParameter("description");
		int id=Integer.parseInt(req.getParameter("id"));
		
		if(dao.editDb(taskname,discription,id)) {
			List<Todotask> tasklist = dao.findbytaskid(user.getId());
			req.setAttribute("tasks", tasklist);
			req.getRequestDispatcher("home.jsp").include(req, resp);
			resp.getWriter().print("<h1 style='color:green'> task updated <h1>");
		}
	}

}
