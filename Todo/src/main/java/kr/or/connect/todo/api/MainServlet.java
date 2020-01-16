package kr.or.connect.todo.api;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * 		GET /todo
 */
@WebServlet("/todo")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** 
	 *	모두 조회한 결과 리스트를 담는다.
	 */
	private List<TodoDto> totalList;
	
	/** 
	 *	type 별로 분류하여 리스트에 담기 위한 변수이다.
	 */
	private List<TodoDto> todoList;
	private List<TodoDto> doingList;
	private List<TodoDto> doneList;
	
	
	private void parseDate() {
		for(TodoDto todo : totalList) {
			
			/** 
			 *	임시 String 배열이다.
			 *	공백으로 구분하게 되면 0번째 인덱스에 화면에 보여주고자 하는 형식이 담긴다.
			 *	불러온 date 스트링은 yyyy-mm-dd이므로 이를 yyyy.mm.dd로 변환한다.
			 */
			String[] array = todo.getRegdate().split(" ");
			
			String ds1 = array[0];
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
			
			try {
				
				String ds2 = sdf2.format(sdf1.parse(ds1));				
				todo.setRegdate(ds2);
			
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/** 
	 *	type 별로 분류하여 각각을 위한 리스트에 담아주는 메소드이다.
	 */
	private void classifyType() {
		for(TodoDto todo : totalList) {
			String type = todo.getType();
			
			switch(type) {
			case "TODO":
				todoList.add(todo);
				break;
			case "DOING":
				doingList.add(todo);
				break;
			case "DONE":
				doneList.add(todo);
				break;
			}
		}
	}
	
	
	/** 
	 * 	GET REQUEST !!!
	 *	/todo의 URI를 타고 오면 처리되는 곳이다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		todoList = new ArrayList<TodoDto>();
		doingList = new ArrayList<TodoDto>();
		doneList = new ArrayList<TodoDto>();
		
		/** 
		 *	DB의 todo 테이블을 모두 조회하기 위한 객체 생성
		 */
		TodoDao todoDao = new TodoDao();
		totalList = todoDao.getTodos();
		
		
		/** 
		 *	날짜 스트링을 화면에 보여줄 형식에 맞춰 변환한다.
		 */
		parseDate();
		
		
		/** 
		 *	type 별 분류 작업 시작!
		 */
		classifyType();
		
		
		/** 
		 *	결과 데이터 콘솔 테스트!
		 */
		System.out.println("================TODO================");
		for(TodoDto todo : todoList) {
			System.out.println(todo);
		}
		
		System.out.println("================DOING================");
		for(TodoDto todo : doingList) {
			System.out.println(todo);
		}
		
		System.out.println("================DONE================");
		for(TodoDto todo : doneList) {
			System.out.println(todo);
		}
		
		/** 
		 *	main.jsp 페이지로 보낼 데이터 셋팅
		 */
		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);
		
		
		/** 
		 *	main.jsp 페이지로 DB로부터 불러온 리스트를 전달한다.
		 */
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/main.jsp");
		requestDispatcher.forward(request, response);
	}

}
