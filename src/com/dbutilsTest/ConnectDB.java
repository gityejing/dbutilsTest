package com.dbutilsTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

public class ConnectDB {
	
	public static Connection Connect() {
		Connection conn = null;
		try {
			// ��������
			if(DbUtils.loadDriver(ConnectInfo.driveClassName)){
				conn = DriverManager.getConnection(ConnectInfo.url, ConnectInfo.user, ConnectInfo.password);
			}else{
				System.out.println("�������ݿ�����ʧ�ܣ�");
			}
		} catch (SQLException e) {
			System.out.println("�������ݿ�����ʧ�ܣ�");
			e.printStackTrace();
		}
		return conn;
	}
}
