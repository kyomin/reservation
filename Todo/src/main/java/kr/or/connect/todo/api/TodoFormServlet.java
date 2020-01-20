package kr.or.connect.todo.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 		todoForm.jsp로 포워딩하는 서블릿 클래스이다.
 * 		
 * 		GET 	/form
 */
@WebServlet("/form")
public class TodoFormServlet extends HttpServlet {
	/** 
	 * 		GET REQUEST !!!
	 *		/todo/form의 URI를 타고 오면 처리되는 곳이다.
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/todoForm.jsp");
		requestDispatcher.forward(request, response);
	}
}
