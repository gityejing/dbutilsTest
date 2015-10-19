package com.dbutilsTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class DBUtilTest {

	public static void main(String[] args) throws SQLException {
//		insert_test();
//		insert_test2();
		select_test();
//		update_test();
//		delete_test();
	}

	static void insert_test() throws SQLException {
		Connection conn = ConnectDB.Connect();
		// 创建SQL执行工具
		QueryRunner qRunner = new QueryRunner();
		// userId,userName,passWord,userType,qxzId,lasttime,ctime
		// 执行SQL插入
		int n = qRunner.update(conn,
				" insert into tblUser(userId,userName,passWord,userType,qxzId,lasttime,ctime) " +
				" values('000000000005','hello','9b4b5ac88c4aacc0df2dae07ebb0794e','0','000000000001','2013-10-17 08:10:34.233','1900-01-01 12:00:00.000')");
		System.out.println("成功插入" + n + "条数据！");
		// 关闭数据库连接
		DbUtils.closeQuietly(conn);
	}
	
	static void insert_test2() throws SQLException {
		Connection conn = ConnectDB.Connect();
		// 创建SQL执行工具
		QueryRunner qRunner = new QueryRunner();
		// userId,userName,passWord,userType,qxzId,lasttime,ctime
		String insert = " insert into tblUser(userId,userName,passWord,userType,qxzId,lasttime,ctime) " +
				" values('000000000010','hello','9b4b5ac88c4aacc0df2dae07ebb0794e','0','000000000001',"
				+ " '2013-10-17 08:10:34.233','1900-01-01 12:00:00.000')";
		TUser user = qRunner.insert(conn,insert, new ResultSetHandler<TUser>() {
			@Override
			public TUser handle(ResultSet rs) throws SQLException {
				// 如果是insert语句，这里是没有结果的
				System.out.println(rs.getRow());
				TUser user = new TUser();
				return user;
			}
		});
		System.out.println(user.getUserId());
//		// 执行SQL插入
//		int n = qRunner.update(conn,
//				" insert into tblUser(userId,userName,passWord,userType,qxzId,lasttime,ctime) " +
//				" values('000000000005','hello','9b4b5ac88c4aacc0df2dae07ebb0794e','0','000000000001','2013-10-17 08:10:34.233','1900-01-01 12:00:00.000')");
//		System.out.println("成功插入" + n + "条数据！");
		// 关闭数据库连接
		DbUtils.closeQuietly(conn);
	}
	

	static void select_test() throws SQLException {
		Connection conn = ConnectDB.Connect();

		// 创建SQL执行工具
		QueryRunner qRunner = new QueryRunner();

		List<TUser> list = (List<TUser>) qRunner.query(conn,//
			" select userId,userName,passWord,userType,qxzId,lasttime,ctime from tblUser ",//
			new BeanListHandler<TUser>(TUser.class));//

		// 输出查询结果
		for (TUser user : list) {
			System.out.println(user.getUserName());
		}

		// 关闭数据库连接
		DbUtils.closeQuietly(conn);
	}
	
	static void update_test() throws SQLException {
		Connection conn = ConnectDB.Connect();

		// 创建SQL执行工具
		QueryRunner qRunner = new QueryRunner();
		// 执行SQL插入
		int n = qRunner.update(conn, "update tblUser set userName = 'yejing'");
		System.out.println("成功更新" + n + "条数据！");

		// 关闭数据库连接
		DbUtils.closeQuietly(conn);
	}

	static void delete_test() throws SQLException {
		Connection conn = ConnectDB.Connect();

		// 创建SQL执行工具
		QueryRunner qRunner = new QueryRunner();

		// 执行SQL插入
		int n = qRunner.update(conn, "DELETE from tblUser WHERE userId='000000000005';");
		System.out.println("删除成功" + n + "条数据！");

		// 关闭数据库连接
		DbUtils.closeQuietly(conn);
	}

}
