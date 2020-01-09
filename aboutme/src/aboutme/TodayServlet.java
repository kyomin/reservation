package aboutme;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public TodayServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		
		out.println("<html>");
		out.println("<head><title>today</title></head>");
		out.println("<body>");
		
		out.println("<a href='http://localhost:8080/aboutme/index.html'>메인화면</a>");
		
		// 5번 개행!
		for(int i=0; i<15; i++)
		{
			out.println("<br/>");
		}
		
		out.println("<h1 style='text-align:center;font-size:50pt'>" +
				"현재시간 : " + currentDateTime + "</h1>");
		
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
}
