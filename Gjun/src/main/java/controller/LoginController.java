package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Member;
import dao.impl.MemberDaoImpl;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.接收-->request-->username,password
		 * 2.findByUsernameAndPassword判斷-->!null->true/false
		 * 3.true-->LoginSuccess.jsp
		 * 4.false-->LoginError.jsp
		 */
		
		String Username=request.getParameter("username");
		String Password=request.getParameter("password");
		
		Member member=new MemberDaoImpl().findByUsernameAndPassword(Username, Password);
		HttpSession session=request.getSession();
		if(member!=null)
		{
			//System.out.println(member.getName());
			session.setAttribute("Member", member);
			response.sendRedirect("member/loginSuccess.jsp");
		}
		else
		{
			response.sendRedirect("loginError.jsp");
		}
		
	}

}