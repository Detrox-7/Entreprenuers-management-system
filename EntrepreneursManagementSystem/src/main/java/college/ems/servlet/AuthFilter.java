package college.ems.servlet;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebFilter;import jakarta.servlet.http.*;import java.io.IOException;
@WebFilter(urlPatterns={"/dashboard","/entrepreneurs/*","/businesses/*","/employees/*","/investments/*","/reports","/settings"})
public class AuthFilter implements Filter{@Override public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{HttpServletRequest req=(HttpServletRequest)request;HttpServletResponse resp=(HttpServletResponse)response;HttpSession session=req.getSession(false);if(session==null||session.getAttribute("userId")==null){resp.sendRedirect(req.getContextPath()+"/login");return;}chain.doFilter(request,response);}}
