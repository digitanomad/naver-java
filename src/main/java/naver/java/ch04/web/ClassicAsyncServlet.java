package naver.java.ch04.web;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AsyncDispatchServlet", urlPatterns = { "/asyncDispatch" }, asyncSupported = true)
public class ClassicAsyncServlet extends HttpServlet {
	private static final long serialVersionUID = -4088034141543380214L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final AsyncContext asyncContext = request.startAsync();
		asyncContext.start(new Runnable() {
			@Override
			public void run() {
				// 오래 걸리는 작업 실행
				asyncContext.dispatch("/threadNames.jsp");
			}
		});
	}

}
