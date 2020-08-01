package com.yc.bbs.dao;

import java.util.List;
import java.util.Map;

import com.yc.bbs.util.DBHelper;

public class BoardDao {
  public List<Map<String,Object>> queryForIndex(){
	  String sql="select * from(SELECT\n" +
			  "	b.boardname pname,\n" +
			  "	a.boardname bname,\n" +
			  "	a.boardid,\n" +
			  "	c.cnt,\n" +
			  "	d.title,\n" +
			  "	d.uid,\n" +
			  "	d.publishtime\n" +
			  "FROM\n" +
			  "	tbl_board a\n" +
			  "JOIN tbl_board b ON a.parentid = b.boardid\n" +
			  "LEFT JOIN (\n" +
			  "	SELECT\n" +
			  "		boardid,\n" +
			  "		COUNT(*) cnt\n" +
			  "	FROM\n" +
			  "		tbl_topic\n" +
			  "	GROUP BY\n" +
			  "		boardid\n" +
			  ") c ON a.boardid = c.boardid\n" +
			  "LEFT JOIN (\n" +
			  "	SELECT\n" +
			  "		*\n" +
			  "	FROM\n" +
			  "		tbl_topic a\n" +
			  "	WHERE\n" +
			  "		EXISTS (\n" +
			  "			SELECT\n" +
			  "				*\n" +
			  "			FROM\n" +
			  "				(\n" +
			  "					SELECT\n" +
			  "						boardid,\n" +
			  "						MAX(topicid) topicid\n" +
			  "					FROM\n" +
			  "						tbl_topic b\n" +
			  "					GROUP BY\n" +
			  "						boardid\n" +
			  "				) b\n" +
			  "			WHERE\n" +
			  "				a.topicid = b.topicid\n" +
			  "		)\n" +
			  ") d ON a.boardid = d.boardid\n" +
			  "WHERE\n" +
			  "	a.parentid != 0) a";
	  return new DBHelper().query(sql);
  }
}
