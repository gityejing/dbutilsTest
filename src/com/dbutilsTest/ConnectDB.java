package com.dbutilsTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

public class ConnectDB {
	
	public static Connection Connect() {
		Connection conn = null;
		try {
			// 加载驱动
			if(DbUtils.loadDriver(ConnectInfo.driveClassName)){
				conn = DriverManager.getConnection(ConnectInfo.url, ConnectInfo.user, ConnectInfo.password);
			}else{
				System.out.println("加载数据库驱动失败！");
			}
		} catch (SQLException e) {
			System.out.println("创建数据库链接失败！");
			e.printStackTrace();
		}
		return conn;
	}
}
