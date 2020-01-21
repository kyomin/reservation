package kr.or.connect.todo.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;
import kr.or.connect.todo.dto.TodoDtoBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

/** 
 * 		main.jsp에서 오른쪽 버튼이 클릭된 todo 원소의 타입을 변경하는 서블릿이다. 
 * 		
 * 		PUT 	/type
 */
@WebServlet("/type")
public class TodoTypeServlet extends HttpServlet {
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text");
		request.setCharacterEncoding("utf-8");

		//	id : params[0], type : params[1]
		String[] params = getBody(request).split(",");
		
		long id = Long.parseLong(params[0]);
		String type = params[1];
		
		//	TodoDto를 위한 빌더 객체 생성
		TodoDtoBuilder todoDtoBuilder = new TodoDtoBuilder();
		
		//	빌더 객체를 이용한 데이터 전달 객체 DTO 생성
		TodoDto todoDto = todoDtoBuilder
				.setId(id)
				.setType(type)
				.build();
				
		//	Update 작업을 처리하기 위한 DAO 생성
		TodoDao todoDao = new TodoDao();
		boolean isUpdateComplete = todoDao.updateTodo(todoDto) != 0;
		
		//	클라이언트로 응답할 스트림 생성
		PrintWriter out = response.getWriter();
		
		if(isUpdateComplete) {
			out.write("success");
		} else {
			out.write("fail");
		}
			
		out.close();
	}

	//	request body를 문자열로 추출하는 함수
	private String getBody(HttpServletRequest request) throws IOException {
		String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        
        try (InputStream inputStream = request.getInputStream();
        		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
            
            if (inputStream != null) {
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        }
        
        body = stringBuilder.toString();
        return body;
	}
}
