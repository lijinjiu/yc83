package com.yc.bbs.dao;

import java.util.List;
import java.util.Map;

import com.yc.bbs.util.DBHelper;

public class UserDao {
	
 public void insert(String uname,String upass,String head,String gender) {
	 String sql="insert into tbl_user values(null,?,?,?,now(),?)";
	 DBHelper dbh=new DBHelper();  //ctrl+1  错误解决方案提示
	 dbh.update(sql, uname, upass, head, gender);  //update实现增删改查
 }
 
 //代码重构：重新定义 如果真长登陆 返回用户信息(map集合) 否则返回null
 public Map<String,Object> selectByLogin(String uname,String upass) {
	 String sql="select * from tbl_user where uname=? and upass=?";
	 DBHelper dbh=new DBHelper();
	 //return dbh.count(sql, uname,upass)>0;
     List<Map<String,Object>> list=dbh.query(sql, uname,upass);
     if(list.size()==0) {
    	 return null;
     }else {
    	 Map<String,Object> user=list.get(0);
    	 return user;
     }
 }
}
