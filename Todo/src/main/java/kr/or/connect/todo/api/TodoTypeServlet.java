package kr.or.connect.todo.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 		main.jsp에서 오른쪽 버튼이 클릭된 todo 원소의 타입을 변경하는 서블릿이다. 
 * 		
 * 		POST /todo/{todoId}
 */
@WebServlet("/todo/*")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
