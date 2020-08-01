package com.yc.bbs.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bbs.dao.UserDao;


@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao udao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String uname=request.getParameter("uname");
		String upass=request.getParameter("upass");
		String head=request.getParameter("head");
		String gender=request.getParameter("gender");
		String reupass=request.getParameter("reupass");
		if(uname==null||uname.trim().isEmpty()) {
			response.getWriter().append("请填写用户名！");
		}else if(upass==null||upass.trim().isEmpty()) {
			response.getWriter().append("请填写密码！");
		}else if(upass.equals(reupass)==false) {
			response.getWriter().append("两次输入密码不一致！");
		}else{
			udao.insert(uname, upass, head, gender);
			response.getWriter().append("用户注册成功！");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
