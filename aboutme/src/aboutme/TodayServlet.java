package aboutme;

import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.util.StringUtils;

/*
  	http://localhost:8080/aboutme/today의 경로를 타고 들어오는 서비스를 처리하는 자바 서블릿 클래스이다.
*/
@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public String stringRepeat(String str, int n) {
    	String result = "";
    	
    	for(int i=0; i<n; i++) {
    		result += str;
    	}
    	
    	return result;
    }

    /*
  		http://localhost:8080/aboutme/today의 경로에 대해 GET Request를 처리하는 메소드이다.
  		클라이언트에게 GET 요청을 한 시점의 시각을 제공한다.
    */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		
		out.println("<html>");
		out.println("<head><title>현재 시간</title></head>");
		out.println("<body>");
		out.println("<a href='/aboutme/index.html'>메인화면</a>");
		
		// 15번 개행!
		out.println(stringRepeat("<br/>", 15));
		
		out.println("<h1 style='text-align:center;font-size:50pt'>" +
				"현재시간 : " + currentDateTime + "</h1>");
		out.println("</body>");
		out.println("</html>");
		
		
		// 스트림 닫기
		out.close();
	}
}