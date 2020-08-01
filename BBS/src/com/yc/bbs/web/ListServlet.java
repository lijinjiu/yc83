package com.yc.bbs.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yc.bbs.dao.TopicDao;
/**servlet作用
 * 1.响应浏览器请求
 * 2.调用业务对象的放大(dao,biz类的方法)，获取数据或执行修改
 * request----请求对象
 * response----响应对象
 * */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TopicDao  tDao=new TopicDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取请求参数 从浏览器发送过来的
		String boardid=request.getParameter("boardid");
		List<Map<String,Object>> list=tDao.queryByboard(boardid);
		//使用GSon 将数据一json格式返回到页面
		Gson gson=new Gson();
		String json=gson.toJson(list);
		response.getWriter().print(json);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
