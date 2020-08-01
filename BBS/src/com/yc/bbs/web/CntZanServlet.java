package com.yc.bbs.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

@WebServlet("/CntZanServlet")
public class CntZanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//获取帖子的点赞数量
		Jedis jd=new Jedis();
		String topicid=request.getParameter("topicid");
		long cnt=jd.scard("topic-zan:"+topicid);
		jd.close();
		response.getWriter().append("{\"cnt\":"+cnt+"}");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
