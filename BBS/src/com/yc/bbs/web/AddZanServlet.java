package com.yc.bbs.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

@WebServlet("/AddZanServlet")
public class AddZanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/**
	 * 点赞功能：用户(登录之后)在浏览器浏览帖子之后 对帖子点赞 用户可以选择取消点赞 
	 * 点赞之后不能再次的点赞
	 * 设计Redis的key值：
	 *       key: topic-zan:帖子id
	 *       value:set集合(不重复k???) 记录每个点赞的用户的id
	 *           
	 * 用户id直接从会话中获取：loginedUser
	 * 帖子id：从请求中获取
	 * */
		@SuppressWarnings("unchecked")
		Map<String,Object> user=
				(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		Object uid=user.get("uid");
		Jedis jd=new Jedis();
		String topicid=request.getParameter("topicid");
		//sadd返回0 失败 1成功
		jd.sadd("topic-zan:"+topicid,uid.toString());
		long cnt=jd.scard("topic-zan:"+topicid);
		jd.close();
		response.getWriter().append("{\"cnt\":"+cnt+"}");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
