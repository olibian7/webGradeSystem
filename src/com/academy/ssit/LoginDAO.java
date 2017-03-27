package com.academy.ssit;

import java.sql.*;

public class LoginDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;

	// 학생 로그인
	public String loginStudent(String id, String pw) {
		String result = null;

		String sql = "SELECT student_id, social_num, name FROM student WHERE student_id = ? AND social_num = ?";
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("name");
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (conn != null)
					DatabaseConnection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}

	//강사 로그인
	public String loginInstructor(String instructor_id, String instructor_pw) {
		String result = null;

		String sql = "SELECT instructor_id, social_num, name FROM instructor WHERE instructor_id = ? AND social_num = ?";
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, instructor_id);
			pstmt.setInt(2, Integer.parseInt(instructor_pw));

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("name");
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (conn != null)
					DatabaseConnection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	   //관리자 로그인
	   public String loginAdmin(String id, String pw) {
	      String result = null;

	      String sql = "SELECT admin_id FROM admin WHERE admin_id = ? AND password = ?";
	      try {
	         conn = DatabaseConnection.connect();
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, id);
	         pstmt.setString(2, pw);

	         ResultSet rs = pstmt.executeQuery();
	         while (rs.next()) {
	            result = rs.getString("admin_id");
	         }
	         rs.close();
	      } catch (ClassNotFoundException | SQLException e1) {
	         e1.printStackTrace();
	      } finally {
	         try {
	            if (pstmt != null)
	               pstmt.close();
	         } catch (SQLException e1) {
	            e1.printStackTrace();
	         }
	         try {
	            if (conn != null)
	               DatabaseConnection.close();
	         } catch (SQLException e1) {
	            e1.printStackTrace();
	         }
	      }
	      return result;
	   }
	   
}
