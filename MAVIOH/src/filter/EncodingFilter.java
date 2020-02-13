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

import setting.Setting;


/**
 * 入力値のエンコーディングを行うフィルター
 * @author kkiku
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {
	/**
	 * 使用しない
	 */
	public void destroy() {	}

	/**
	 * 入力値のエンコードを行う関数
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		((HttpServletRequest)request).setCharacterEncoding(Setting.CHARACTER_CODE);
		chain.doFilter(request, response);
	}

	/**
	 * 使用しない
	 */
	public void init(FilterConfig fConfig) throws ServletException { }

}
