package com.academy.ssit.admin;

import java.sql.*;
import java.util.*;

import com.academy.ssit.*;

public class Ad_subClassDAO5 {

	/*
	 * ==================================================== -5.학생관리
	 * ==================================================== 관리자화면> 5.학생관리> 1.수강생
	 * 정보 등록 --------------------------------------------------
	 * -------------------------------------------------------------------------
	 * ------- -- 1) 수강생 정보 등록 - 트렌젝션 처리 -- 2) 과정 등록
	 * -------------------------------------------------------------------------
	 * -------
	 */
	public String ad_st_addStudent(Admin studentInfo) {
		String result = null;

		String sql1 = "SELECT student_id FROM studentSeqView";
		String sql2 = "INSERT INTO student(student_id, name, social_num, phone)  VALUES(?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			conn = DatabaseConnection.connect();

			// 트랙잭션 처리
			conn.setAutoCommit(false);

			// 강사 신규 번호 얻는 과정
			String student_id = "st000";
			pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();

			while (rs.next()) {
				student_id = rs.getString("student_id");
			}
			rs.close();

			// 학생 개인 정보 입력 과정
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, student_id);
			pstmt2.setString(2, studentInfo.getSt_name());
			pstmt2.setString(3, studentInfo.getSt_social_num());
			pstmt2.setString(4, studentInfo.getSt_phone());

			int res = pstmt2.executeUpdate();
			if (res > 0) {
				result = student_id;
			}

			// 트랙잭션 처리
			conn.commit();

		} catch (ClassNotFoundException | SQLException e) {
			// 트랙잭션 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// e.printStackTrace();
		} finally {
			try {
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

	// 학생 과정 신청List
	// >관리자화면 > 학생관리> 수강생 정보 등록> 과정 신청
	public List<Admin> ad_st_studentCourseList() {
		List<Admin> result = new ArrayList<>();

		String sql3 = "SELECT course_id, course_name, cr_start_date, cr_end_date, classroom_name, countStudent, max_num, countsub FROM CCVCCCourse_recordView";

		Connection conn = null;
		PreparedStatement pstmt3 = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt3 = conn.prepareStatement(sql3);
			ResultSet rs3 = pstmt3.executeQuery();
			while (rs3.next()) {
				Admin ad = new Admin();
				ad.setCourse_id(rs3.getString("course_id"));
				ad.setCourse_name(rs3.getString("course_name"));
				ad.setCr_start_date(rs3.getString("cr_start_date"));
				ad.setCr_end_date(rs3.getString("cr_end_date"));
				ad.setClassroom_name(rs3.getString("classroom_name"));
				ad.setCountStu(rs3.getInt("countStudent"));
				ad.setMax_num(rs3.getInt("max_num"));
				ad.setCountSub(rs3.getInt("countsub"));
				result.add(ad);
			}
			rs3.close();
		} catch (ClassNotFoundException | SQLException e) {
			// 트랙잭션 처리
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

	public List<Admin> ad_st_studentCourseList(String stu_id) {
		List<Admin> result = new ArrayList<>();

		String sql3 = "SELECT course_id, course_name, cr_start_date, cr_end_date, classroom_name, countstudent, max_num, countsub, fail_date FROM CCVCCCourse_recordView WHERE student_id = ?";

		Connection conn = null;
		PreparedStatement pstmt3 = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt3 = conn.prepareStatement(sql3);

			pstmt3.setString(1, stu_id);

			ResultSet rs3 = pstmt3.executeQuery();
			while (rs3.next()) {
				Admin ad = new Admin();
				ad.setCourse_id(rs3.getString("course_id"));
				ad.setCourse_name(rs3.getString("course_name"));
				ad.setCr_start_date(rs3.getString("cr_start_date"));
				ad.setCr_end_date(rs3.getString("cr_end_date"));
				ad.setClassroom_name(rs3.getString("classroom_name"));
				ad.setCountStu(rs3.getInt("countstudent"));
				ad.setMax_num(rs3.getInt("max_num"));
				ad.setCountSub(rs3.getInt("countsub"));
				ad.setFail_date(rs3.getString("fail_date"));
				result.add(ad);
			}
			rs3.close();
		} catch (ClassNotFoundException | SQLException e) {
			// 트랙잭션 처리
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

	// 과정 입력
	public int ad_st_add_studentCourse(String course_id, String student_id) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		String sql1 = "INSERT INTO course_record(course_id, student_id) VALUES (?, ?)";
		try {
			conn = DatabaseConnection.connect();

			pstmt1 = conn.prepareStatement(sql1);

			pstmt1.setString(1, course_id);
			pstmt1.setString(2, student_id);

			result = pstmt1.executeUpdate();

		} catch (ClassNotFoundException | SQLException e1) {
			// e1.printStackTrace();
			System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt1 != null) {
					pstmt1.close();
				}
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

	// 과정 취소 
	public int ad_st_delete_studentCourse(String course_id, String student_id) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		String sql1 = "DELETE FROM course_record WHERE course_id=? AND student_id=?";
		try {
			conn = DatabaseConnection.connect();

			pstmt1 = conn.prepareStatement(sql1);

			pstmt1.setString(1, course_id);
			pstmt1.setString(2, student_id);

			result = pstmt1.executeUpdate();

		} catch (ClassNotFoundException | SQLException e1) {
			// e1.printStackTrace();
			System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt1 != null) {
					pstmt1.close();
				}
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
	
	// 수강생 전체 리스트
	public List<Admin> ad_st_allStudentList(String key, String value) {
		List<Admin> result = new ArrayList<Admin>();
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		String sql1 = "SELECT student_id, name, social_num, phone, registration_date, countRequest FROM StudentCountView";

		System.out.println(key);
		System.out.println(value);
		
		switch (key) {
		case "all":
			break;
		case "student_id":
			sql1 += " WHERE INSTR(UPPER(student_id),UPPER(?))>0";
			break;
		case "name":
			sql1 += " WHERE INSTR(UPPER(name),UPPER(?))>0";
			break;
		case "phone":
			sql1 += " WHERE INSTR(phone,?)>0";
			break;
		}

		sql1 += " ORDER BY student_id";

		try {
			conn = DatabaseConnection.connect();

			pstmt1 = conn.prepareStatement(sql1);

			switch (key) {
			case "all":
				break;
			case "student_id":
			case "name":
			case "phone":
				pstmt1.setString(1, value);
				break;
			}

			ResultSet rs = pstmt1.executeQuery();
			while (rs.next()) {

				String student_id = rs.getString("student_id");
				String name = rs.getString("name");
				String social_num = rs.getString("social_num");
				String phone = rs.getString("phone");
				String registration_date = rs.getString("registration_date");
				int countRequest = rs.getInt("countRequest");

				Admin ad = new Admin();
				ad.setStudent_id(student_id);
				ad.setSt_name(name);
				ad.setSt_social_num(social_num);
				ad.setSt_phone(phone);
				ad.setSt_registration_date(registration_date);
				ad.setCountRequest(countRequest);

				result.add(ad);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// e1.printStackTrace();
			System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt1 != null) {
					pstmt1.close();
				}
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

	// 수강생 개인정보 출력
	public List<Admin> ad_st_studentInfo(String key, String value) {
		List<Admin> result = new ArrayList<Admin>();
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		String sql1 = "SELECT course_id, course_name, cr_start_date, cr_end_date, classroom_name, fail_date, student_id FROM PSFail_dateView WHERE student_id = ?";
		
		try {
			conn = DatabaseConnection.connect();

			pstmt1 = conn.prepareStatement(sql1);

			pstmt1.setString(1, value);

			ResultSet rs = pstmt1.executeQuery();
			while (rs.next()) {
				String course_id = rs.getString("course_id");
				String course_name = rs.getString("course_name");
				String cr_start_date = rs.getString("cr_start_date");
				String cr_end_date = rs.getString("cr_end_date");
				String classroom_name = rs.getString("classroom_name");
				String fail_date = rs.getString("fail_date");

				Admin ad = new Admin();
				ad.setCourse_id(course_id);
				ad.setCourse_name(course_name);
				ad.setCr_start_date(cr_start_date);
				ad.setCr_end_date(cr_end_date);
				ad.setClassroom_name(classroom_name);
				ad.setFail_date(fail_date);

				result.add(ad);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// e1.printStackTrace();
			System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt1 != null) {
					pstmt1.close();
				}
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

	public List<Admin> ad_st_courseStudentList(String key, String value) {
		List<Admin> result = new ArrayList<Admin>();
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		String sql = "";
		switch (key) {
		case "name":
			sql += "SELECT student_id, name, phone FROM student WHERE INSTR(name, ?) > 0";
			break;
		case "course":
			sql += "SELECT student_id, name, social_num, phone, registration_date, fail_date"
					+ " FROM psfail_dateview WHERE course_id = ?";
			break;
		case "student_id":
			sql += "SELECT student_id, name, social_num, phone, registration_date, fail_date"
					+ " FROM psfail_dateview WHERE student_id = ?";
		}
		try {
			conn = DatabaseConnection.connect();

			pstmt1 = conn.prepareStatement(sql);

			pstmt1.setString(1, value);

			ResultSet rs = pstmt1.executeQuery();
			while (rs.next()) {
				String student_id = "없음";
				String student_name = "없음";
				String social_num = "없음";
				String phone = "없음";
				String registration_date = "없음";
				String fail_date = "없음";
				switch (key) {
				case "name":
					student_id = rs.getString("student_id");
					student_name = rs.getString("name");
					phone = rs.getString("phone");
					break;
				case "course":
					student_id = rs.getString("student_id");
					student_name = rs.getString("name");
					social_num = rs.getString("social_num");
					phone = rs.getString("phone");
					registration_date = rs.getString("registration_date");
					fail_date = rs.getString("fail_date");
					break;
				case "student_id":
					student_id = rs.getString("student_id");
					student_name = rs.getString("name");
					social_num = rs.getString("social_num");
					phone = rs.getString("phone");
					registration_date = rs.getString("registration_date");
					fail_date = rs.getString("fail_date");
					
					System.out.println("여기부터");
					System.out.println(student_id);
					System.out.println(student_name);
					System.out.println(social_num);
					System.out.println(phone);
					System.out.println(registration_date);
					System.out.println(fail_date);
					break;
				}
				Admin ad = new Admin();
				ad.setFail_date(fail_date);
				ad.setStudent_id(student_id);
				ad.setSt_name(student_name);
				ad.setSt_social_num(social_num);
				ad.setSt_phone(phone);
				ad.setSt_registration_date(registration_date);

				result.add(ad);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt1 != null) {
					pstmt1.close();
				}
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

	
	// 관리자 과정 전체 검색
	  public List<Admin> ad_st_courseStudentList(String key, String value, String skey, String svalue) {
	      List<Admin> result = new ArrayList<Admin>();
	      Connection conn = null;
	      PreparedStatement pstmt1 = null;
	      String sql = "";
	      switch(key){
	      case "name":
	         sql += "SELECT student_id, name, phone FROM student WHERE INSTR(name, ?) > 0";
	         break;
	      case "course":
	         sql += "SELECT student_id, name, social_num, phone, registration_date, fail_date FROM psfail_dateview WHERE course_id = ?";
	         break;
	     	      }   
	      
	      switch(skey){
	      case "all":
	    	  break;
	      case "student_id":
	         sql += " AND INSTR(UPPER(student_id), UPPER(?)) > 0";
	         break;
	      case "name":
	    	  sql+= " AND INSTR(UPPER(name), UPPER(?)) > 0";
	         break;
	      }
	      
	      sql += " ORDER BY student_id";
	      
	      try {
	         conn = DatabaseConnection.connect();
	         
	         pstmt1 = conn.prepareStatement(sql);
	         
	         pstmt1.setString(1, value);
	         
	         switch(skey){
	         case "all":
	       	  break;
	         case "student_id":
	         case "name":
	        	pstmt1.setString(2, svalue);
	            break;
	         }
	         
	         ResultSet rs = pstmt1.executeQuery();        
	         
	         while(rs.next()){
	            String student_id = "없음";
	            String student_name = "없음";
	            String social_num = "없음";
	            String phone = "없음";
	            String registration_date = "없음";
	            String fail_date = "없음";
	            
	            switch(key){            
	            case "name":
	               student_id = rs.getString("student_id");
	               student_name = rs.getString("name");
	               phone = rs.getString("phone");
	               break;
	            case "course":
	               student_id = rs.getString("student_id");
	               student_name = rs.getString("name");
	               social_num = rs.getString("social_num");
	               phone = rs.getString("phone");
	               registration_date = rs.getString("registration_date");
	               fail_date = rs.getString("fail_date");
	               break;
	            }
	            
	            
	               Admin ad = new Admin();
	               ad.setFail_date(fail_date);
	               ad.setStudent_id(student_id);
	               ad.setSt_name(student_name);
	               ad.setSt_social_num(social_num);
	               ad.setSt_phone(phone);
	               ad.setSt_registration_date(registration_date);
	               
	               result.add(ad);            
	         }
	         
	         rs.close();
	         
	      } catch (ClassNotFoundException | SQLException e1) {
	         e1.printStackTrace();
	         System.out.println("오류! 다시 시도하세요.");
	      } finally {
	         try {
	            if (pstmt1 != null){
	               pstmt1.close();
	            }               
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

	
	  
	// 관리자 과정 전체 검색
		public List<Admin> ad_st_allCourseList(String key, String value) {
			List<Admin> result = new ArrayList<Admin>();
			Connection conn = null;
			PreparedStatement pstmt1 = null;
			String sql = "SELECT course_id, course_name, cr_start_date, cr_end_date, classroom_name FROM admin_Print_CourseView";

			switch (key) {
			case "all":
				break;

			case "course_name":
				sql += " WHERE INSTR(UPPER(course_name) ,UPPER(?)) > 0";
				break;
			case "course_id":
				sql += " WHERE INSTR(UPPER(course_id) ,UPPER(?)) > 0";
			break;
			}

			sql += " ORDER BY course_id";

			try {
				conn = DatabaseConnection.connect();

				pstmt1 = conn.prepareStatement(sql);

				switch (key) {
				case "all":
					break;
				case "course_name":
				case "course_id":
					pstmt1.setString(1, value);
					break;
				}

				ResultSet rs = pstmt1.executeQuery();
				while (rs.next()) {

					String course_id = rs.getString("course_id");
					String course_name = rs.getString("course_name");
					String cr_start_date = rs.getString("cr_start_date");
					String cr_end_date = rs.getString("cr_end_date");
					String classroom_name = rs.getString("classroom_name");

					Admin ad = new Admin();
					ad.setCourse_id(course_id);
					ad.setCourse_name(course_name);
					ad.setCr_start_date(cr_start_date);
					ad.setCr_end_date(cr_end_date);
					ad.setClassroom_name(classroom_name);

					result.add(ad);
				}
				rs.close();
			} catch (ClassNotFoundException | SQLException e1) {
				// e1.printStackTrace();
				System.out.println("오류! 다시 시도하세요.");
			} finally {
				try {
					if (pstmt1 != null) {
						pstmt1.close();
					}
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
	  
	/////////////////////////////////////////////////// 여기서 부터
	/////////////////////////////////////////////////// 사용안함///////////////////////////////////////
	// 삭제할 학생 이름 검색
	public List<Admin> studentDeleteList(String string, String student_name) {
		List<Admin> result = new ArrayList<Admin>();
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		String sql1 = "SELECT student_id, name, phone, registration_date, countRequest FROM StudentCountView  WHERE INSTR(name, ?) > 0";

		try {
			conn = DatabaseConnection.connect();

			pstmt1 = conn.prepareStatement(sql1);

			pstmt1.setString(1, student_name);

			ResultSet rs = pstmt1.executeQuery();
			while (rs.next()) {

				String student_id = rs.getString("student_id");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String registration_date = rs.getString("registration_date");
				int countRequest = rs.getInt("countRequest");

				Admin ad = new Admin();
				ad.setStudent_id(student_id);
				ad.setSt_name(name);
				ad.setSt_phone(phone);
				ad.setSt_registration_date(registration_date);
				ad.setCountRequest(countRequest);

				result.add(ad);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// e1.printStackTrace();
			System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt1 != null) {
					pstmt1.close();
				}
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

	// 삭제할 학생 상세 검색
	public List<Admin> studentDeleteList2(String string, String id) {
		List<Admin> result = new ArrayList<Admin>();
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		String sql1 = "SELECT course_name, cr_start_date, cr_end_date, classroom_name, fail_date FROM scv_psfvView WHERE student_id = ?";

		try {
			conn = DatabaseConnection.connect();

			pstmt1 = conn.prepareStatement(sql1);

			pstmt1.setString(1, id);

			ResultSet rs = pstmt1.executeQuery();
			while (rs.next()) {

				String course_name = rs.getString("course_name");
				String cr_start_date = rs.getString("cr_start_date");
				String cr_end_date = rs.getString("cr_end_date");
				String classroom_name = rs.getString("classroom_name");
				String fail_date = rs.getString("fail_date");

				Admin ad = new Admin();
				ad.setCourse_name(course_name);
				ad.setCr_start_date(cr_start_date);
				ad.setCr_end_date(cr_end_date);
				ad.setClassroom_name(classroom_name);
				ad.setFail_date(fail_date);

				result.add(ad);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// e1.printStackTrace();
			System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt1 != null) {
					pstmt1.close();
				}
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

	public int studentDelete(String student_id) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		String sql1 = "DELETE FROM student WHERE student_id = ?";

		try {
			conn = DatabaseConnection.connect();

			pstmt1 = conn.prepareStatement(sql1);

			pstmt1.setString(1, student_id);

			result = pstmt1.executeUpdate();

		} catch (ClassNotFoundException | SQLException e1) {
			// e1.printStackTrace();
			// System.out.println("현재 과정이 있으므로 삭제할수 없습니다.");
		} finally {
			try {
				if (pstmt1 != null) {
					pstmt1.close();
				}
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

	// ----------------------------------------------------------------------------------------------------
	public int ad_ins_totalCount() {

		int result = 0;

		String sql = "SELECT COUNT(*) AS \"count\" FROM student";

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

}
	
	
	
