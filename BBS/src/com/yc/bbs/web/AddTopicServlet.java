package com.yc.bbs.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bbs.dao.TopicDao;

@WebServlet("/AddTopicServlet")
public class AddTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TopicDao tDao=new TopicDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String boardid=request.getParameter("boardid");
		
		//从会话中获取用户map  @SuppressWarnings("unchecked")加上不告警
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		if(user==null) {
			response.getWriter().print("请先登录系统");
		    return;
		}
		
		String uid=""+user.get("uid");   //获取用户ID
		
		if(title==null||title.trim().isEmpty()) {
			response.getWriter().print("请填写帖子标题");
		}else if(content==null||content.trim().isEmpty()) {
			response.getWriter().print("请填写帖子内容");
		}else{
			tDao.insert(title, content, uid, boardid);
			response.getWriter().print("发帖成功");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
