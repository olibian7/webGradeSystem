package com.academy.ssit.admin;

import java.sql.*;
import java.util.*;

import com.academy.ssit.DatabaseConnection;
import com.academy.ssit.student.Student;

public class Ad_subClassDAO6 {

	// 전체 출력
	public List<Admin> ad_gr_courseList(String key, String value) {
		List<Admin> result = new ArrayList<Admin>();
		// 쿼리
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT DISTINCT course_id, course_name, cr_start_date, cr_end_date FROM sungjukView ";

		switch (key) {
		case "all":
			break;
		case "course_id":
			sql += " WHERE INSTR(UPPER(course_id),UPPER(?))>0";
			break;
		case "course_name":
			sql += " WHERE INSTR(UPPER(course_name),UPPER(?))>0";
			break;
		}

		sql += " ORDER BY course_id";

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
				result.add(ad);
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

	public List<Admin> ad_gr_courseList(String course_id) {
		List<Admin> result = new ArrayList<Admin>();
		// 쿼리
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT DISTINCT course_id, course_name, cr_start_date, cr_end_date FROM sungjukView WHERE course_id = ?";

		sql += " ORDER BY course_id";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, course_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Admin ad = new Admin();
				ad.setCourse_id(rs.getString("course_id"));
				ad.setCourse_name(rs.getString("course_name"));
				ad.setCr_start_date(rs.getString("cr_start_date"));
				ad.setCr_end_date(rs.getString("cr_end_date"));
				result.add(ad);
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

	// 특정 과정 정보 출력
	// gradecheck, testcheck = 1 : 입력 된것, 0이 입력 안된것.
	public List<Admin> ad_gr_courseSubjectList(String course_id, String key, String value) {
		List<Admin> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT subject_id, subject_name, sj_start_date, sj_end_date, in_name,"
				+ " gradecheck, testcheck, test_paper, test_paper_name,classroom_name, book_name,book_img, course_name,"
				+ " cr_start_date, cr_end_date FROM sungjukView_test WHERE course_id = ?";

		switch (key) {
		case "all":
			break;
		case "subject_id":
			sql += " AND INSTR(UPPER(subject_id),UPPER(?))>0";
			break;
		case "subject_name":
			sql += " AND INSTR(UPPER(subject_name),UPPER(?))>0";
			break;
		}

		sql += " ORDER BY subject_id";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, course_id);

			switch (key) {
			case "all":
				break;
			case "subject_id":
			case "subject_name":
				pstmt.setString(2, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Admin ad = new Admin();
				ad.setSubject_id(rs.getString("subject_id"));
				ad.setSubject_name(rs.getString("subject_name"));
				ad.setSj_start_date(rs.getString("sj_start_date"));
				ad.setSj_end_date(rs.getString("sj_end_date"));
				ad.setIn_name(rs.getString("in_name"));
				ad.setTest_paper(rs.getString("test_paper"));
				ad.setTest_paper_name(rs.getString("test_paper_name"));

				if (rs.getString("gradecheck").equals("1")) {
					ad.setGradecheck("등록 중");
				} else if(rs.getString("gradecheck").equals("0")){
					ad.setGradecheck("미등록");
				}
				
				ad.setClassroom_name(rs.getString("classroom_name"));
				ad.setBook_name(rs.getString("book_name"));
				ad.setBook_img(rs.getString("book_img"));
				ad.setCourse_name(rs.getString("course_name"));
				ad.setCr_start_date(rs.getString("cr_start_date"));
				ad.setCr_end_date(rs.getString("cr_end_date"));
				list.add(ad);
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

		return list;
	}

	// 특정 과목을 듣는 수강생 정보 출력
	public List<Admin> ad_gr_subStudentlist(String subject_id, String key, String value) {
		List<Admin> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT scsv_student_id, name, phone, gr_attend, gr_writing,"
				+ " gr_practice FROM st_dateCheckView WHERE scsv_subject_id = ? ";

		switch (key) {
		case "all":
			break;
		case "student_id":
			sql += " AND INSTR(UPPER(scsv_student_id),UPPER(?))>0";
			break;
		case "name":
			sql += " AND INSTR(UPPER(name),UPPER(?))>0";
			break;
		case "phone":
			sql += " AND INSTR(UPPER(phone),UPPER(?))>0";
			break;
		}

		sql += " ORDER BY scsv_student_id";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, subject_id);

			switch (key) {
			case "all":
				break;
			case "student_id":
			case "name":
			case "phone":
				pstmt.setString(2, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Admin ad = new Admin();
				ad.setStudent_id(rs.getString("scsv_student_id"));
				ad.setSt_name(rs.getString("name"));
				ad.setSt_phone(rs.getString("phone"));
				ad.setGr_attend(rs.getInt("gr_attend"));
				ad.setGr_writing(rs.getInt("gr_writing"));
				ad.setGr_practice(rs.getInt("gr_practice"));
				list.add(ad);
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

		return list;
	}

	// 특정 수강생이 수강한 과정안에 있는 과목 정보 출력 + 성적 출력
	public List<Admin> ad_gr_studentGrade(String student_id, String course_id) {
		List<Admin> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT scsv_subject_id, subject_name, sj_start_date, sj_end_date, gr_attend, gr_writing, gr_practice, in_name, "
				+ "sc_attend, sc_writing, sc_practice, test_paper, test_paper_name"
				+ " FROM st_dateCheckView WHERE scsv_student_id = ? AND scsv_course_id = ? ORDER BY scsv_student_id";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, student_id);
			pstmt.setString(2, course_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Admin ad = new Admin();

				ad.setSubject_id(rs.getString("scsv_subject_id"));
				ad.setSubject_name(rs.getString("subject_name"));
				ad.setSj_start_date(rs.getString("sj_start_date"));
				ad.setSj_end_date(rs.getString("sj_end_date"));
				ad.setIn_name(rs.getString("in_name"));
				ad.setTest_paper(rs.getString("test_paper"));
				ad.setTest_paper_name(rs.getString("test_paper_name"));
				ad.setGr_attend(rs.getInt("gr_attend"));
				ad.setSc_attend(rs.getInt("sc_attend"));
				ad.setGr_writing(rs.getInt("gr_writing"));
				ad.setSc_writing(rs.getInt("sc_writing"));
				ad.setGr_practice(rs.getInt("gr_practice"));
				ad.setSc_practice(rs.getInt("sc_practice"));

				list.add(ad);
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

		return list;
	}

	// 학생기준 조회 - 전체 학생 출력
	public List<Admin> ad_gr_allStudentList(String key, String value) {
		List<Admin> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = " SELECT student_id, name, social_num, phone, registration_date FROM student";

		switch (key) {
		case "all":
			break;
		case "name":
			sql += " WHERE INSTR(name, ?) > 0";
			break;
		case "student_id":
			sql += " WHERE INSTR(student_id, ?)>0";
			break;
		case "phone":
			sql += " WHERE INSTR(phone, ?)>0";
			break;
		}

		sql += " ORDER BY student_id";

		try

		{
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "all":
				break;
			case "name":
			case "student_id":
			case "phone":
				pstmt.setString(1, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Admin ad = new Admin();
				ad.setStudent_id(rs.getString("student_id"));
				ad.setSt_name(rs.getString("name"));
				ad.setSt_phone(rs.getString("phone"));
				ad.setSt_social_num(rs.getString("social_num"));
				String a = rs.getString("registration_date");
				String[] arrS = a.split(" ");
				ad.setSt_registration_date(arrS[0]);
				list.add(ad);
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

		return list;
	}

	// 학생기준 조회 - 학생이 수강중인 과정 정보 출력
	public List<Student> ad_gr_studentCourseList(String key, String value) {
		List<Student> result = new ArrayList<Student>();
		String sql = "SELECT course_id, course_name, cr_start_date, cr_end_date FROM studentCourseView WHERE student_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Student s = new Student();
				s.setCourse_id(rs.getString("course_id"));
				s.setCourse_name(rs.getString("course_name"));
				s.setCr_start_date(rs.getString("cr_start_date"));
				s.setCr_end_date(rs.getString("cr_end_date"));

				result.add(s);

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

	///////////////////////////////////////////// 여기서부터
	///////////////////////////////////////////// 사용안함////////////////////////////////////////
	// 특정 수강생이 수강한 과정안에 있는 과목 정보 출력 + 성적 출력
	public List<Admin> searchStudentName_Co(String student_name, String course_id) {
		List<Admin> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT DISTINCT scsv_student_id, name, phone"
				+ " FROM st_dateCheckView WHERE INSTR(name,?)>0 AND scsv_course_id = ? ORDER BY scsv_student_id";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, student_name);
			pstmt.setString(2, course_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Admin ad = new Admin();
				ad.setStudent_id(rs.getString("scsv_student_id"));
				ad.setSt_name(rs.getString("name"));
				ad.setSt_phone(rs.getString("phone"));
				// ad.setSubject_id(rs.getString("scsv_subject_id"));
				// ad.setSubject_name(rs.getString("subject_name"));
				// ad.setSj_start_date(rs.getString("sj_start_date"));
				// ad.setSj_end_date(rs.getString("sj_end_date"));
				// ad.setGr_attend(rs.getInt("gr_attend"));
				// ad.setGr_writing(rs.getInt("gr_writing"));
				// ad.setGr_practice(rs.getInt("gr_practice"));
				list.add(ad);
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

		return list;
	}

	// 타이틀 - 과정명+기간
	public String title_course(String course_id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String result = "";

		String sql = "SELECT DISTINCT course_name, cr_start_date, cr_end_date FROM sungjuk_title_term WHERE course_id = ?";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, course_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

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

	// 타이틀 - 과목명+기간
	public String title_subject(String subject_id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String result = "";

		String sql = "SELECT subject_name, sj_start_date, sj_end_date FROM sungjuk_title_term WHERE subject_id = ?";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, subject_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				result = String.format("%s(%s~%s)", rs.getString("subject_name"), rs.getString("sj_start_date"),
						rs.getString("sj_end_date"));

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

	// 타이틀 - 학생 이름 아이디
	public String student_title(String student_id) {
		String result = "";

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT name, student_id FROM student WHERE name = ?";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, student_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = String.format("%s(%s)", rs.getString("name"), rs.getString("student_id"));
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