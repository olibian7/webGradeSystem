package com.academy.ssit;

import java.sql.*;

//�����ͺ��̽� Ŀ�ؼ� ��ü ���� Ŭ����
public class DatabaseConnection {

	private static Connection conn = null;

	public static Connection connect() throws ClassNotFoundException, SQLException {
		if (conn == null) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// �����ͺ��̽� ���ῡ �ʿ��� ���� ���� ���
			conn = DriverManager.getConnection("jdbc:oracle:thin:SUNGJUK_HO/1234@211.63.89.92:1521:xe");
		}
		return conn;
	}

	public static void close() throws SQLException {
		if (conn != null)
			conn.close();
		conn = null;
	}

}
