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

import redis.clients.jedis.Jedis;


@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TopicDao  tDao=new TopicDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//通过topicid帖子id 查询帖子信息和回帖信息
		String topicid=request.getParameter("topicid");
		List<Map<String,Object>> list=tDao.queryByDetail(topicid);
		
		/**
		 * 将访问次数存入数据库 Redis 其实很大工作是设置key 值
		 * topicid----->key topic:4  值(访问次数)1,2,3......
		 *                  topic:5  值数字(字符串)
		 *                  
		 * 访问量排行榜             zset===>key topic-zset
		 *                          value topicid
		 *                          score 浏览量     
		 * */
		 Jedis jd=new Jedis();
		 //incr返回增长的值 原值+1 incr实现自增
		 long cnt=jd.incr("topic:"+topicid);
		 
		 //将topic保存到生成排行榜中
		 jd.zadd("topic-zset", cnt,topicid);
         /**
          * 返回json格式
          * [{原贴},{跟帖},{跟帖},{跟帖}.....]
          * 
          * */
		 //将cnt加入到list中  
		list.get(0).put("cnt",cnt);
		jd.close();
		
		//使用GSon 将数据一json格式返回到页面
		Gson gson=new Gson();
		String json=gson.toJson(list);
	    response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
