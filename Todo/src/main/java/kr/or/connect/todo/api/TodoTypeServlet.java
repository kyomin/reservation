package kr.or.connect.todo.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;

import com.fasterxml.jackson.databind.ObjectMapper;

/** 
 * 		main.jsp에서 오른쪽 버튼이 클릭된 todo 원소의 타입을 변경하는 서블릿이다. 
 * 		
 * 		PUT 	/type
 */
@WebServlet("/type")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		request.setCharacterEncoding("utf-8");

		
		System.out.println(request.toString());
		System.out.println("id : " + request.getParameter("id"));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		request.setCharacterEncoding("utf-8");
		
		// 데이터 전달 객체 DTO 생성
		TodoDto todoDto = new TodoDto(Long.parseLong(request.getParameter("id")), request.getParameter("type"));
		
		// Update 작업을 처리하기 위한 DAO 생성
		TodoDao todoDao = new TodoDao();
		int updateCount = todoDao.updateTodo(todoDto);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		// 클라이언트로 응답할 스트림 생성
		PrintWriter out = response.getWriter();
		out.println(objectMapper.writeValueAsString(updateCount));
		out.close();
		
		System.out.println("업데이트된 대상 개수 : " + updateCount);
	}
       
    
	

}
