package com.academy.ssit.admin;

import java.util.*;
import java.sql.*;

import com.academy.ssit.DatabaseConnection;

public class Ad_subClassDAO4 {

	// 과정 리스트 출력
	public List<Admin> ad_sj_courseList(String key, String value) {
		List<Admin> result = new ArrayList<>();

		String sql = null;
		sql = "SELECT course_id, course_name, cr_start_date, cr_end_date, countSub FROM CCVCountView";

		switch (key) {
		case "all":
			break;
		case "course_id":
			sql += " WHERE INSTR(course_id, ?)>0";
			break;
		case "course_name":
			sql += " WHERE INSTR(course_name, ?)>0";
			break;
		}

		sql += " ORDER BY course_id";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "all":
				break;
			case "course_id":
			case "course_name":
				pstmt.setString(1, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Admin ad = new Admin();

				ad.setCourse_id(rs.getString("course_id"));
				ad.setCourse_name(rs.getString("course_name"));
				ad.setCr_start_date(rs.getString("cr_start_date"));
				ad.setCr_end_date(rs.getString("cr_end_date"));
				ad.setCountSub(rs.getInt("countSub"));

				result.add(ad);
			}

			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	// 과목명 리스트 출력
	public List<Admin> ad_sj_totalSubject() {
		List<Admin> result = new ArrayList<>();

		String sql = null;
		sql = "SELECT subject_name_id, subject_name FROM subject_name";

		sql += " ORDER BY subject_name_id";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Admin ad = new Admin();

				ad.setSubject_name_id(rs.getString("subject_name_id"));
				ad.setSubject_name(rs.getString("subject_name"));

				result.add(ad);
			}

			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	// 교재명 리스트 출력
	public List<Admin> ad_sj_totalbook() {
		List<Admin> result = new ArrayList<>();

		String sql = null;
		sql = "SELECT book_id, book_name, publisher, book_img FROM book";

		sql += " ORDER BY book_id";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Admin ad = new Admin();

				ad.setBook_id(rs.getString("book_id"));
				ad.setBook_name(rs.getString("book_name"));
				ad.setPublisher(rs.getString("publisher"));
				ad.setBook_img(rs.getString("book_img"));

				result.add(ad);
			}

			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	// 강사명 리스트 출력
	public List<Admin> ad_sj_totalInstructor() {
		List<Admin> result = new ArrayList<>();

		String sql = null;
		sql = "SELECT instructor_id, name, social_num, phone FROM instructor";

		sql += " ORDER BY instructor_id";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Admin ad = new Admin();

				ad.setInstructor_id(rs.getString("instructor_id"));
				ad.setIn_name(rs.getString("name"));
				ad.setIn_social_num(rs.getString("social_num"));
				ad.setIn_phone(rs.getString("phone"));

				result.add(ad);
			}

			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	// 과목 리스트 출력
	public List<Admin> ad_sj_subjectList(String key, String value) {
		List<Admin> result = new ArrayList<>();

		String sql = null;

		sql = "SELECT subject_id, subject_name, subject_name_id, sj_start_date, sj_end_date, course_id, course_name, cr_start_date, cr_end_date, book_id, book_name, name, instructor_id FROM CCVSubBoInsView";

		switch (key) {
		case "all":
			break;
		case "course_id":
			sql += " WHERE course_id = ?";
			break;
		case "subject_id":
			sql += " WHERE subject_id = ?";
			break;
		}

		sql += " ORDER BY subject_id";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "all":
				break;
			case "course_id":
				pstmt.setString(1, value);
				break;
			case "subject_id":
				pstmt.setString(1, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Admin ad = new Admin();

				ad.setSubject_id(rs.getString("subject_id"));
				ad.setSubject_name(rs.getString("subject_name"));
				ad.setSubject_name_id(rs.getString("subject_name_id"));
				ad.setSj_start_date(rs.getString("sj_start_date"));
				ad.setSj_end_date(rs.getString("sj_end_date"));
				ad.setCourse_id(rs.getString("course_id"));
				ad.setCourse_name(rs.getString("course_name"));
				ad.setCr_start_date(rs.getString("cr_start_date"));
				ad.setCr_end_date(rs.getString("cr_end_date"));
				ad.setBook_id(rs.getString("book_id"));
				ad.setBook_name(rs.getString("book_name"));
				ad.setIn_name(rs.getString("name"));
				ad.setInstructor_id(rs.getString("instructor_id"));
				result.add(ad);
			}

			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	// 과목 신규 생성
	public int ad_sj_addSubject(Admin admin) {
		int result = 0;

		String sql1 = "SELECT subject_id FROM subjectIDSeqView";
		String sql2 = "INSERT INTO subject(subject_id, start_date, end_date, book_id, subject_name_id, course_id, instructor_id) VALUES(?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랜잭션 처리
			conn.setAutoCommit(false);

			// 신규 과목ID 생성
			String subject_id = "sj000";

			pstmt1 = conn.prepareStatement(sql1);

			ResultSet rs = pstmt1.executeQuery();
			while (rs.next()) {
				subject_id = rs.getString("subject_id");
			}
			rs.close();

			// 과목 정보 입력
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, subject_id);
			pstmt2.setString(2, admin.getSj_start_date());
			pstmt2.setString(3, admin.getSj_end_date());
			pstmt2.setString(4, admin.getBook_id());
			pstmt2.setString(5, admin.getSubject_name_id());
			pstmt2.setString(6, admin.getCourse_id());
			pstmt2.setString(7, admin.getInstructor_id());

			result = pstmt2.executeUpdate();

			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		} finally {
			try {
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	// 과목 삭제
	public int ad_sj_deleteSubject(String subject_id) {
		int result = 0;

		String sql = "DELETE FROM subject WHERE subject_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, subject_id);

			result = pstmt.executeUpdate();

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

	// 과목 수정
	public int ad_sj_modifySubject(Admin ad) {
		int result = 0;
		
		String sql = "UPDATE subject SET start_date=?, end_date=?, book_id=?, instructor_id=? WHERE subject_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ad.getSj_start_date());
			pstmt.setString(2, ad.getSj_end_date());
			pstmt.setString(3, ad.getBook_id());
			pstmt.setString(4, ad.getInstructor_id());
			pstmt.setString(5, ad.getSubject_id());

			result = pstmt.executeUpdate();

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
	
	///////////////////////////////////////// 여기서 부터 사용
	///////////////////////////////////////// 안함////////////////////////////////
	// 타이틀바 (과정)
	public String title_course(String course_id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String result = "";

		String sql = "SELECT course_name, cr_start_date, cr_end_date FROM courseClassroomView WHERE course_id = ?";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, course_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				rs.getString("course_name");
				rs.getString("cr_start_date");
				rs.getString("cr_end_date");

				result = String.format("%s(%s~%s)", rs.getString("course_name"), rs.getString("cr_start_date"),
						rs.getString("cr_end_date"));

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
