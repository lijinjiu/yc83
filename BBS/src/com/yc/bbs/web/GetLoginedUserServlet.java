package com.yc.bbs.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/GetLoginedUserServlet")
public class GetLoginedUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从会话中获取登录的用户名
		
		@SuppressWarnings("unchecked")
		Map<String,Object> user=
				(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		Gson gson=new Gson();
		String json=gson.toJson(user);
		response.getWriter().print(json);
   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
