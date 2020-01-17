package kr.or.connect.todo.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;


/** 
 * 		브라우저(todoForm.jsp)로부터 입력받은 데이터를 DB의 todo 테이블에 삽입하는 것을 처리하는 서블릿이다.
 * 		
 * 		POST 	/todo/add
 */
@WebServlet("/add")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식에서의 한글 인코딩 처리!
		request.setCharacterEncoding("UTF-8");  
		
		// 브라우저로부터 입력받은 데이터를 전달하기 위한 DTO 생성
		TodoDto todoDto = new TodoDto(request.getParameter("name"), Integer.parseInt(request.getParameter("sequence")), request.getParameter("title"));
		
		// Insert 작업을 위한 DAO 생성
		TodoDao todoDao = new TodoDao();
		int insertCount = todoDao.addTodo(todoDto);
		
		System.out.println(insertCount);
		
		response.sendRedirect("/Todo/main");
	}

}
