package kr.or.connect.todo.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;
import kr.or.connect.todo.dto.TodoDtoBuilder;
import kr.or.connect.todo.utils.TodoService;

/** 
 * 		브라우저(todoForm.jsp)로부터 입력받은 데이터를 DB의 todo 테이블에 삽입하는 것을 처리하는 서블릿이다.
 * 		
 * 		POST 	/add
 */
@WebServlet("/add")
public class TodoAddServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
		
		// 서블릿에서도 클라이언트의 input 제한 길이의 타당성을 검증한다.
		if(TodoService.isProperTitleLength(request.getParameter("title"))) {
			//	TodoDto를 위한 빌더 객체 생성
			TodoDtoBuilder todoDtoBuilder = new TodoDtoBuilder();
			
			//	빌더 객체를 이용한 데이터 전달 객체 DTO 생성
			TodoDto todoDto = todoDtoBuilder
					.setName(request.getParameter("name"))
					.setSequence(Integer.parseInt(request.getParameter("sequence")))
					.setTitle(request.getParameter("title"))
					.build();
				
			// 	Insert 작업을 위한 DAO 생성
			TodoDao todoDao = new TodoDao();
			boolean isInsertComplete = todoDao.addTodo(todoDto) != 0;
				
			if(isInsertComplete) {
				response.sendRedirect("/main");
			} else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/insertError.jsp");
				requestDispatcher.forward(request, response);
			}
		} else {	//	title에 대한 input 길이 제한이 지켜지지 않았다면
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/inputLengthError.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
