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
		
		//	데이터 전달 객체 DTO 생성
		TodoDto todoDto = new TodoDto(Long.parseLong(params[0]), params[1]);
				
		//	Update 작업을 처리하기 위한 DAO 생성
		TodoDao todoDao = new TodoDao();
		int updateCount = todoDao.updateTodo(todoDto);
		
		//	클라이언트로 응답할 스트림 생성
		PrintWriter out = response.getWriter();
		
		if(updateCount == 1) {
			out.write("success");
		} else {
			out.write("fail");
		}
			
		out.close();
		
		System.out.println("업데이트된 대상 개수 : " + updateCount);
	}

	//	request body를 문자열로 추출하는 함수
	private String getBody(HttpServletRequest request) throws IOException {
		String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
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
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        
        body = stringBuilder.toString();
        return body;
	}
}
