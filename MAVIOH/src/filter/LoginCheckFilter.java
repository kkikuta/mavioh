package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginLogic;


/**
 * ログインしているか確認するフィルター
 * @author kkiku
 */
@WebFilter("/*")
public class LoginCheckFilter implements Filter {
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String servletPath = ((HttpServletRequest) request).getServletPath();

		// WebFilterで設定しても効かないため代わりにここで設定
		// chain.doFilter関数は、elseブロック内に書かないと上手く動かない
		if (servletPath.equals("/ForumServlet") || servletPath.equals("/ScheduleServlet") ||
				servletPath.equals("/StudentServlet") || servletPath.equals("/TestViewServlet")) {
			if (LoginLogic.isLogin((HttpServletRequest) request) == false) {
				((HttpServletResponse) response).sendRedirect("ErrorServlet");
			}
			else {
				chain.doFilter(request, response);
			}
		}
		else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException { }

}
