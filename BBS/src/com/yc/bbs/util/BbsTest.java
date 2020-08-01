package com.yc.bbs.util;

import java.util.List;
import java.util.Map;

public class BbsTest {
public static void main(String[] args) {
	DBHelper dbh=new DBHelper();
	String sql="select * from tbl_board";
	List<Map<String,Object>> list=dbh.query(sql);
	for(Map<String,Object> row:list) {
		System.out.println(row);
	}
}
}
