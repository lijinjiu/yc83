package com.yc.bbs.dao;

import java.util.List;
import java.util.Map;

import com.yc.bbs.util.DBHelper;

//DAO---Data Access Object 数据访问类 
public class TopicDao {
  public List<Map<String,Object>> queryByboard(String boardid){
	  String sql="select * from tbl_topic where boardid=?";
	  return new DBHelper().query(sql, boardid);
  }
  public List<Map<String,Object>> queryByDetail(String topicid){
	  //预编译SQL语句  关联查询+联合查询
	  String sql="SELECT\n" +
			  "	*\n" +
			  "FROM\n" +
			  "	tbl_topic a\n" +
			  "left JOIN tbl_user b ON a.uid = b.uid\n" +
			  "WHERE\n" +
			  "	topicid = ?\n" +
			  "UNION ALL\n" +
			  "	SELECT\n" +
			  "		*\n" +
			  "	FROM\n" +
			  "		tbl_reply a\n" +
			  "left JOIN tbl_user b ON a.uid = b.uid\n" +
			  "	WHERE\n" +
			  "		topicid = ?";
	  return new DBHelper().query(sql,topicid,topicid);
  }
  
  //发表帖子
  public void insert(String title,String content,String uid,String boardid) {
	  String sql="insert into tbl_topic values(null,?,?,now(),null,?,?)";
	  DBHelper dbh=new DBHelper();
	  dbh.update(sql, title,content,uid,boardid);
  }
}
