package com.academy.ssit.admin;

import java.sql.*;
import java.util.*;

import com.academy.ssit.DatabaseConnection;

public class Ad_subClassDAO2 {

	Connection conn = null;

	/***************************
	 * 2. 강사 계정 관리
	 ************************************/

	// 강사계정관리>강사정보등록
	public int ad_ins_addInstructor(Admin ad, String[] list_m) {

		int result = 0;

		String sql1 = "SELECT instructor_id FROM instructorSeqView";
		String sql2 = "INSERT INTO instructor(instructor_id, name, social_num,phone) VALUES (?, ?, ?, ?)";
		String sql3 = "INSERT INTO subjects_available(instructor_id, subject_name_id) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랜잭션 처리
			conn.setAutoCommit(false);

			// 강사 신규번호 얻는 과정
			String instructor_id = "in011";
			pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();
			while (rs.next()) {
				instructor_id = rs.getString("instructor_id");
			}

			rs.close();
			
			System.out.println("강사등록 정보");
			System.out.println(ad.getIn_name());
			System.out.println(ad.getIn_social_num());
			System.out.println(ad.getIn_phone());
			for(int i=0; i<list_m.length; i++){
				System.out.println(list_m[i]);
			}
			
			// 강사 개인 정보 입력 과정
			pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setString(1, instructor_id);
			pstmt2.setString(2, ad.getIn_name());
			pstmt2.setString(3, ad.getIn_social_num());
			pstmt2.setString(4, ad.getIn_phone());
			
			pstmt2.executeUpdate();

			// 강의 가능 과목 입력 과정
			pstmt3 = conn.prepareStatement(sql3);
			for (String subject_name_id : list_m) {
				pstmt3.setString(1, instructor_id);
				pstmt3.setString(2, subject_name_id);
				result += pstmt3.executeUpdate();
			}

			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// 트랜잭션 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {

				if (pstmt3 != null)
					pstmt3.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (pstmt1 != null)
					pstmt1.close();

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
	// 강사계정관리>강사정보수정
		public int ad_ins_modifyInstructor(Admin ad, String[] list_m) {

			int result = 0;

			/*String sql1 = "SELECT instructor_id FROM instructorSeqView";*/
			String sql1 = "UPDATE instructor SET name = ?,phone = ? WHERE instructor_id = ?";
			String sql2 = "DELETE FROM subjects_available WHERE instructor_id = ?";
			String sql3 = "INSERT INTO subjects_available(instructor_id, subject_name_id) VALUES (?, ?)";
			Connection conn = null;
			/*PreparedStatement pstmt1 = null;*/
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;

			try {
				conn = DatabaseConnection.connect();

				// 트랜잭션 처리
				conn.setAutoCommit(false);
				
				for(int i=0; i<list_m.length; i++){
					System.out.println(list_m[i]);
				}
				
				// 강사 개인 정보 입력 과정
				pstmt1 = conn.prepareStatement(sql1);
				
				pstmt1.setString(1, ad.getIn_name());
				pstmt1.setString(2, ad.getIn_phone());
				pstmt1.setString(3, ad.getInstructor_id());				
				
				pstmt1.executeUpdate();

				// 강의 가능 과목 입력 과정
				pstmt2 = conn.prepareStatement(sql2);
				
				pstmt2.setString(1, ad.getInstructor_id());
				pstmt2.executeUpdate();
				
				pstmt3 = conn.prepareStatement(sql3);
				
				for (String subject_name_id : list_m) {
					pstmt3.setString(1, ad.getInstructor_id());
					pstmt3.setString(2, subject_name_id);
					result += pstmt3.executeUpdate();
				}

				conn.commit();
			} catch (ClassNotFoundException | SQLException e) {
				// 트랜잭션 처리
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {

					if (pstmt3 != null)
						pstmt3.close();
					if (pstmt2 != null)
						pstmt2.close();
					if (pstmt1 != null)
						pstmt1.close();

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
	// 강사계정관리>강사정보 검색 & 출력
	public List<Admin> ad_ins_instructorList(String key, String value) {
		List<Admin> result = new ArrayList<Admin>();
		PreparedStatement pstmt = null;
		String sql = "SELECT instructor_id, name, social_num, phone, sj_available FROM PAlistaggView";

		switch (key) {
		case "all":
			break;
		case "instructor_id":
			sql += " WHERE INSTR(UPPER(instructor_id),UPPER(?)) > 0";
			break;
		case "instructor_name":
			sql += " WHERE INSTR(UPPER(name),UPPER(?)) > 0";
			break;
		}

		sql += " ORDER BY instructor_id";

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "all":
				break;
			case "instructor_id":
			case "instructor_name":
				pstmt.setString(1, value);
				break;
			}
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Admin ad = new Admin();
				ad.setInstructor_id(rs.getString("instructor_id"));
				ad.setIn_name(rs.getString("name"));
				ad.setIn_social_num(rs.getString("social_num"));
				ad.setIn_phone(rs.getString("phone"));
				ad.setSj_available(rs.getString("sj_available"));

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
	// 강사계정관리>강사정보(과목을 강의하는 강사들 출력)
	public List<Admin> ad_ins_teachSubjectList() {
		List<Admin> result = new ArrayList<Admin>();
		PreparedStatement pstmt = null;
		String sql = "SELECT DISTINCT instructor_id FROM subject";
		
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Admin ad = new Admin();
				ad.setInstructor_id(rs.getString("instructor_id"));				
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

	public List<Admin> ad_ins_instructorList_detail(String instructor_id) {

		List<Admin> result = new ArrayList<Admin>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT subject_name,sj_start_date,sj_end_date,course_name,cr_start_date,cr_end_date,book_name,classroom_name,instructor_id,in_name,datecheck"
				+ " FROM SCCBdateCheckView WHERE instructor_id = ?";

		// subject_name sj_start_date sj_end_date course_name cr_start_date
		// cr_end_date book_name classroom_name instructor_id in_name datecheck

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, instructor_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				Admin ad = new Admin();

				ad.setIn_name(rs.getString("in_name"));
				ad.setSubject_name(rs.getString("subject_name"));
				ad.setSj_start_date(rs.getString("sj_start_date"));
				ad.setSj_end_date(rs.getString("sj_end_date"));
				ad.setCourse_name(rs.getString("course_name"));
				ad.setCr_start_date(rs.getString("cr_start_date"));
				ad.setCr_end_date(rs.getString("cr_end_date"));
				ad.setBook_name(rs.getString("book_name"));
				ad.setClassroom_name(rs.getString("classroom_name"));
				ad.setDateCheck(rs.getInt("dateCheck"));

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

	// 강사정보 삭제
	public int ad_ins_deleteInstructor(String instructor_id) {

		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt1 = null; // 쿼리문 실행용
		PreparedStatement pstmt2 = null;
		String sql1 = "DELETE FROM subjects_available WHERE instructor_id = ?"; // pstmt에서
		// 사용
		String sql2 = "DELETE FROM instructor WHERE instructor_id = ?"; // pstmt에서
		// 사용

		try {

			conn = DatabaseConnection.connect();
			conn.setAutoCommit(false);
			pstmt1 = conn.prepareStatement(sql1);

			pstmt1.setString(1, instructor_id);

			result = pstmt1.executeUpdate();
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, instructor_id);
			result = pstmt2.executeUpdate();
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// e.printStackTrace();
			System.out.printf("%n**강사님의 과목이 이미 수업에 배정되어있습니다. 다시 확인해주세요.%n%n");
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
				if (conn != null) {
					DatabaseConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int ad_ins_totalCount() {

		int result = 0;

		String sql = "SELECT COUNT(*) AS \"count\" FROM instructor";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("count");
			}
			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// finally 구문 내부에서 PreparedStatement, Connection 객체에 대한 마무리
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
	// 강사계정관리>강사등록>강의가능과목
		public List<Admin> ad_ins_subjectList() {
			List<Admin> result = new ArrayList<Admin>();
			PreparedStatement pstmt = null;
			String sql = "SELECT subject_name_id, subject_name FROM subject_name ORDER BY subject_name_id";

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