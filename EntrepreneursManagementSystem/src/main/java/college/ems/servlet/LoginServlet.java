package college.ems.servlet;

import college.ems.dao.UserDAO;
import college.ems.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDAO users=new UserDAO();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);}
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");String username=req.getParameter("username"),password=req.getParameter("password");
        try{User user=(username==null||password==null)?null:users.findByUsername(username.trim());if(user!=null&&BCrypt.checkpw(password,user.getPasswordHash())){HttpSession session=req.getSession(true);session.setAttribute("userId",user.getUserId());session.setAttribute("username",user.getUsername());session.setAttribute("role",user.getRole());session.setMaxInactiveInterval(30*60);resp.sendRedirect(req.getContextPath()+"/dashboard");return;}req.setAttribute("error","Username or password is incorrect.");}
        catch(Exception ex){getServletContext().log("Login failed",ex);req.setAttribute("error","Sign-in is temporarily unavailable. Check database configuration.");}
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }
}
