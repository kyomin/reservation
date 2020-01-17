package kr.or.connect.todo.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;

/** 
 * 		main.jsp 화면에 보여질 데이터를 컨트롤하는 서블릿 클래스이다.
 * 		
 * 		모든 todo정보 읽어오기
 * 		GET 	/todo
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 12345678L;
	
	/** 
	 *	type 별로 분류하여 각각을 위한 리스트에 담아주는 메소드이다.
	 *
	 *	@param		totalList
	 *				분류 대상인 전체 todo 리스트
	 *	@param		type
	 *				뽑아내고자 하는 대상 타입
	 *	@param		start 
	 *				시작 인덱스
	 *	@param		end
	 *				끝 인덱스(totalList의 길이)
	 *
	 *	@return		해당 타입으로 분류하여 담긴 리스트
	 */
	private List<TodoDto> makeTypeList(List<TodoDto> totalList, String type, int start, int end) {
		
		List<TodoDto> typeList = new ArrayList<TodoDto>();
		
		for(int idx=start; idx<end; idx++) {
			if(totalList.get(idx).getType().equals(type)) {
				typeList.add(totalList.get(idx));
			} else {
				break;
			}
		}
		
		return typeList;
	}
	
	
	/** 
	 * 	GET REQUEST !!!
	 *	/todo의 URI를 타고 오면 처리되는 곳이다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 
		 *	DB의 todo 테이블의 데이터를 모두 totalList로 불러오기
		 */
		TodoDao todoDao = new TodoDao();
		List<TodoDto> totalList = todoDao.getTodos();
		
		
		/** 
		 *	type별 분류 작업 시작!
		 */
		if(totalList.size() != 0) {
			String[] types = {"doing", "done", "todo"};
			
			int start = 0;
			int end = totalList.size();
			
			for(String type : types) {
				List<TodoDto> typeList = makeTypeList(totalList, type.toUpperCase(), start, end);
				start += typeList.size();
				request.setAttribute(type+"List", typeList);
			}
		}
		
		/** 
		 *	main.jsp 페이지로 DB로부터 불러온 리스트를 전달한다.
		 */
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/main.jsp");
		requestDispatcher.forward(request, response);
	}

}
