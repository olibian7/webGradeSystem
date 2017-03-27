package com.academy.ssit.admin;

import java.util.*;

import com.academy.ssit.DatabaseConnection;

import java.sql.*;

public class Ad_subClassDAO3 {

	// --과정명ID + 과정명 출력
	public List<Admin> ad_cr_courseNameList(String value) {
		List<Admin> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT course_name_id, course_name";
		sql += " FROM course_name";
		if (!value.equals("")) {
			sql += " WHERE course_name_id = ?";
		}
		sql += " ORDER BY course_name_id";

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			if (!value.equals("")) {
				pstmt.setString(1, value);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String course_name_id = rs.getString("course_name_id");
				String course_name = rs.getString("course_name");

				Admin ad = new Admin();
				// setter 사용하여 입력
				ad.setCourse_name_id(course_name_id);
				ad.setCourse_name(course_name);

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



	// --과정ID시퀀스 출력(뒤에서 강의실 정보까지 받은 후에 과정 생성)
	public String ad_cr_addCourseId() {
		String result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT course_id FROM courseIDSeqView";

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("course_id");
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

	// --과정 정보 입력(과정ID에 시퀀스 값 입력.)
	public int ad_cr_addCourse(Admin ad) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO course(course_id, start_date, end_date, classroom_id, course_name_id)";
		sql += " VALUES (?,?,?,?,?)";
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ad.getCourse_id());
			pstmt.setString(2, ad.getCr_start_date());
			pstmt.setString(3, ad.getCr_end_date());
			pstmt.setString(4, ad.getClassroom_id());
			pstmt.setString(5, ad.getCourse_name_id());
			result = pstmt.executeUpdate();
			++result;

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
	
	// --과정 정보 수정(과정ID에 시퀀스 값 입력.)
	public int ad_cr_modifyCourse(Admin ad) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE course SET start_date=?, end_date=?, classroom_id=? WHERE course_id=?";
		
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ad.getCr_start_date());
			pstmt.setString(2, ad.getCr_end_date());
			pstmt.setString(3, ad.getClassroom_id());
			pstmt.setString(4, ad.getCourse_id());
			
			result = pstmt.executeUpdate();

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


	// --과정 정보 수정(과정ID에 시퀀스 값 입력.)
	public int ad_cr_deleteCourse(String course_id) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM course WHERE course_id=?";
		
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course_id);
			
			result = pstmt.executeUpdate();

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

	
	// --과정 정보 출력
	// --과정ID,과정명,과정기간,강의실명,수강생인원/정원,개설과목수
	public List<Admin> ad_cr_courseList(String key, String value) {
		List<Admin> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT course_id, course_name, cr_start_date, cr_end_date";
		sql += ", classroom_name, countStu, max_num, countSub";
		sql += " FROM courseClassroomCountView";
		
		switch (key) {
		case "all":
			break;
		case "course_id":
			sql += " WHERE INSTR(UPPER(course_id),UPPER(?)) > 0";
			break;
		case "course_name":
			sql += " WHERE INSTR(UPPER(course_name),UPPER(?)) > 0";
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
				pstmt.setString(1, value);
				break;
			case "course_name":
				pstmt.setString(1, value);
				break;
			}
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String course_id = rs.getString("course_id");
				String course_name = rs.getString("course_name");
				String cr_start_date = rs.getString("cr_start_date");
				String cr_end_date = rs.getString("cr_end_date");
				String classroom_name = rs.getString("classroom_name");
				int countStu = rs.getInt("countStu");
				int max_num = rs.getInt("max_num");
				int countSub = rs.getInt("countSub");

				Admin ad = new Admin();
				ad.setCourse_id(course_id);
				ad.setCourse_name(course_name);
				ad.setCr_start_date(cr_start_date);
				ad.setCr_end_date(cr_end_date);
				ad.setClassroom_name(classroom_name);
				ad.setCountStu(countStu);
				ad.setMax_num(max_num);
				ad.setCountSub(countSub);

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
				if (conn != null)
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	//강의실명 출력
	public List<Admin> ad_cr_ClassroomList() {
		List<Admin> result = new ArrayList<Admin>();
		String sql = "SELECT classroom_id, classroom_name, max_num  FROM classroom ORDER BY classroom_id";

		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Admin ad = new Admin();
				ad.setClassroom_id(rs.getString("classroom_id"));
				ad.setClassroom_name(rs.getString("classroom_name"));
				ad.setMax_num(rs.getInt("max_num"));
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
	
	
	
	// --5) 특정 과정에 속한 과목정보 출력 뷰 생성
	// --과목ID 과목명 과목기간 교재명 강사명
	public List<Admin> ad_cr_subjectList(String course_id) {
		List<Admin> result = new ArrayList<>();
		/*
		 * SELECT subject_id, subject_name, sj_start_date, sj_end_date,
		 * book_name, in_name
		 * 
		 * FROM courseSubView WHERE course_id = ?;
		 */

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT subject_id, subject_name, sj_start_date, sj_end_date, book_name, in_name";
		sql += " FROM courseSubView";
		sql += " WHERE course_id = ?";
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String subject_id = rs.getString("subject_id");
				String subject_name = rs.getString("subject_name");
				String sj_start_date = rs.getString("sj_start_date");
				String sj_end_date = rs.getString("sj_end_date");
				String book_name = rs.getString("book_name");
				String in_name = rs.getString("in_name");

				Admin ad = new Admin();
				ad.setSubject_id(subject_id);
				ad.setSubject_name(subject_name);
				ad.setSj_start_date(sj_start_date);
				ad.setSj_end_date(sj_end_date);
				ad.setBook_name(book_name);
				ad.setIn_name(in_name);

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

	// --6)특정 과정에 속한 과목을 듣는 수강생들의 정보 + 중도탈락날짜 출력 뷰 생성
	// --수강생ID,수강생 이름,주민번호뒷자리,전화번호,등록일,중도탈락날짜
	public List<Admin> ad_cr_studentList(String course_id) {
		List<Admin> result = new ArrayList<>();
		/*
		 * SELECT student_id, st_name, st_social_num , st_phone,
		 * registration_date, fail_date FROM st_faildateView WHERE course_id =
		 * ?;
		 */

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT student_id, st_name, st_social_num,st_phone, registration_date, fail_date";
		sql += " FROM st_faildateView";
		sql += " WHERE course_id = ?";
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String student_id = rs.getString("student_id");
				String st_name = rs.getString("st_name");
				String st_social_num = rs.getString("st_social_num");
				String st_phone = rs.getString("st_phone");
				String registration_date = rs.getString("registration_date");
				String fail_date = rs.getString("fail_date");

				Admin ad = new Admin();
				ad.setStudent_id(student_id);
				ad.setSt_name(st_name);
				ad.setSt_social_num(st_social_num);
				ad.setSt_phone(st_phone);

				// 기존 Admin의 field에서는 registration_date가 st_registration_date로
				// 표기되어있어 에러발생
				ad.setSt_registration_date(registration_date);
				ad.setFail_date(fail_date);

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
	   // 오늘 날짜 가져오는 쿼리 추가
	   public String ad_cr_dateCheck() {

	      String today = null;

	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD') from dual";

	      try {
	         conn = DatabaseConnection.connect();
	         pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery();
	         while (rs.next()) {
	            today = rs.getString("TO_CHAR(SYSDATE,'YYYY-MM-DD')");
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

	      return today;
	   }

	   
	   
	// --타이틀(과정명, 과정기간)
		public List<Admin> ad_courseNavi(String selec) {
			List<Admin> result = new ArrayList<>();
			/*
			 * SELECT course_name, cr_start_date, cr_end_date FROM
			 * courseClassroomCountView WHERE course_id = ?;
			 */
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "SELECT course_name, cr_start_date, cr_end_date";
			sql += " FROM courseClassroomCountView";
			sql += " WHERE course_id = ?";
			try {
				conn = DatabaseConnection.connect();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, selec);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					String course_name = rs.getString("course_name");
					String cr_start_date = rs.getString("cr_start_date");
					String cr_end_date = rs.getString("cr_end_date");

					Admin ad = new Admin();
					ad.setCourse_name(course_name);
					ad.setCr_start_date(cr_start_date);
					ad.setCr_end_date(cr_end_date);

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
		

		// 전체 과정수 출력
		public int totalCount() {
			int result = 0;

			/*
			 SELECT COUNT(*) AS "count" FROM studentView;
			 */

			String sql = "SELECT COUNT(*) AS \"count\" FROM courseClassroomCountView";

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
		
		/////////////////////안씀
// --2-1. 강의실ID + 강의실명 출력(선택한 날짜에 사용가능한 강의실만 출력)
public List<Admin> ad_cr_emptyClassroomList(String co_start_date, String co_end_date) {
	List<Admin> result = new ArrayList<>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	/*SELECT classroom_id,classroom_name, max_num
	  FROM classroom
	  WHERE classroom_id NOT IN(SELECT DISTINCT classroom_id
	                      FROM classroomdateView
	                        WHERE TO_CHAR(TO_DATE('시작날짜')) BETWEEN cr_start_date AND cr_end_date
	                        OR TO_CHAR(TO_DATE('끝날짜')) BETWEEN cr_start_date AND cr_end_date)*/
	String sql = "SELECT DISTINCT classroom_id, classroom_name, max_num";
	sql += " FROM classroomdateView";
	sql += " WHERE classroom_id NOT IN(SELECT DISTINCT classroom_id";
	sql += " FROM classroomdateView";
	sql += " WHERE TO_CHAR(TO_DATE(?)) BETWEEN cr_start_date AND cr_end_date";
	sql += " OR TO_CHAR(TO_DATE(?)) BETWEEN cr_start_date AND cr_end_date)";
	sql += " AND ? <= ?";
	sql += " ORDER BY classroom_name";

//			if (!class_id.equals("")) {
//				sql += " AND classroom_id = ?";
//			}

	try {
		conn = DatabaseConnection.connect();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, co_start_date);
		pstmt.setString(2, co_end_date);
		pstmt.setString(3, co_start_date);
		pstmt.setString(4, co_end_date);
//				if (!class_id.equals("")) {
//					pstmt.setString(3, class_id);
//				}
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String classroom_id = rs.getString("classroom_id");
			String classroom_name = rs.getString("classroom_name");
			int max_num = rs.getInt("max_num");

			Admin ad = new Admin();
			// setter 사용하여 입력
			ad.setClassroom_id(classroom_id);
			ad.setClassroom_name(classroom_name);
			ad.setMax_num(max_num);

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
		

	   
}
