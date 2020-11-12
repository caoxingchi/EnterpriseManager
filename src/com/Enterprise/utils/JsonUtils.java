package com.Enterprise.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {
	public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
		//获取列
		ResultSetMetaData md=rs.getMetaData();
		int num=md.getColumnCount();
		JSONArray array=new JSONArray();
		while(rs.next()){
			JSONObject mapOfColValues=new JSONObject();
			for(int i=1;i<=num;i++){
				Object o=rs.getObject(i);//判断这一列是不是data数据类型的
				if(o instanceof Date){
					//获取的是列名
					mapOfColValues.put(md.getColumnName(i), DateUtil.formatDate((Date)o, "yyyy-MM-dd hh:mm:ss"));
				}else{
					//把每个纵向的键值队封装进去
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
				}
				
			}
			array.add(mapOfColValues);
		}
		
		return array;
		
	}
}
