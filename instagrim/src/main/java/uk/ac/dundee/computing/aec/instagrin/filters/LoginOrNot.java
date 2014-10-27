package uk.ac.dundee.computing.aec.instagrin.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import uk.ac.dundee.computing.aec.instagrim.servlets.Login;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
/**
 * Servlet Filter implementation class LoginOrNot
 */
@WebFilter(filterName = "LoginOrNot", urlPatterns = {"/register.jsp","/login.jsp","/Register","/Login"}, 
dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class LoginOrNot implements Filter {

	 private FilterConfig filterConfig;
    /**
     * Default constructor. 
     */
    public LoginOrNot() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.filterConfig = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		if(session != null){
			/*LoggedIn lg = (LoggedIn) ((HttpServletRequest) request).getSession().getAttribute("LoggedIn");*/
			LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
			if(lg != null && lg.getlogedin()){
				System.out.println("forward to index.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}
		}

		// pass the request along the filter chain
		try {
			chain.doFilter(request, response);
		} catch (Throwable e ) {		
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
	}

}
