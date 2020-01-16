package kr.or.connect.todo.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;

@WebServlet("/todo/add")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식에서의 한글 인코딩 처리!
		request.setCharacterEncoding("EUC-KR");  
		
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence"));
		
		// 받은 데이터 콘솔 확인!
		System.out.println("title : " + title);
		System.out.println("name : " + name);
		System.out.println("sequence : " + sequence);
		
		TodoDto todoDto = new TodoDto(name, sequence, title);
		
		TodoDao todoDao = new TodoDao();
		int insertCount = todoDao.addTodo(todoDto);
		
		System.out.println(insertCount);
		
		response.sendRedirect("/Todo/todo");
	}

}
