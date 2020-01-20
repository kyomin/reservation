package kr.or.connect.todo.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;
import kr.or.connect.todo.utils.TodoService;

/** 
 * 		main.jsp 화면에 보여질 데이터를 컨트롤하는 서블릿 클래스이다.
 * 		
 * 		모든 todo정보 읽어오기
 * 		GET 	/main
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static String[] types = {"TODO", "DOING", "DONE"};
	
	/** 
	 * 		GET REQUEST !!!
	 *		/main의 URI를 타고 오면 처리되는 곳이다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		//	DB의 todo 테이블의 데이터를 모두 totalList로 불러오기 
		TodoDao todoDao = new TodoDao();
		List<TodoDto> totalList = todoDao.getTodos();
		
		//	불러온 결과가 비었을 때의 예외처리
		if(totalList.size() != 0) {
			//	type별 분류 작업 시작!
			HashMap<String, List<TodoDto>> typeMap = TodoService.makeTypeList(totalList);
			for(String type : types) {
				//	request scope를 이용해 데이터 저장
				request.setAttribute(type.toLowerCase()+"List", typeMap.get(type));
			}
		}
		 
		//	main.jsp 페이지로 포워드!
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/main.jsp");
		requestDispatcher.forward(request, response);
	}
}
