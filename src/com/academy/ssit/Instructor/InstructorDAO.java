package com.academy.ssit.Instructor;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.academy.ssit.DatabaseConnection;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class InstructorDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;

	// > 강사화면> 강의스케쥴 조회> 강의예정
	public List<Instructor> ins_scheduleList(String lid, String id, String subKey, String subValue) {
		List<Instructor> result = new ArrayList<Instructor>();
		String sql = "SELECT subject_id, subject_name, sj_start_date, sj_end_date, course_name, cr_start_date, cr_end_date, classroom_name, book_name, book_img, st_count FROM SCCBCourse_recordView";

		// 강의 상태에 따른 1차 분류
		// 강의예정
		if (lid.equals("sub1")) {
			sql += " WHERE TO_CHAR(TO_DATE(sj_start_date),'YYYY-MM-DD') > TO_CHAR(SYSDATE, 'YYYY-MM-DD')";
			sql += " AND instructor_id = ?";
			// 강의종료
		} else if (lid.equals("sub3")) {
			sql += " WHERE TO_CHAR(TO_DATE(sj_end_date),'YYYY-MM-DD') <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')";
			sql += " AND instructor_id = ?";
			// 강의중
		} else if (lid.equals("sub2")) {
			sql += " WHERE TO_CHAR(TO_DATE(sj_end_date),'YYYY-MM-DD') > TO_CHAR(SYSDATE, 'YYYY-MM-DD')";
			sql += " AND TO_CHAR(TO_DATE(sj_start_date),'YYYY-MM-DD') <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')";
			sql += " AND instructor_id = ?";
		}

		// 검색기준값에 의한 2차 분류
		switch (subKey) {
		// 과목ID 기준
		case "2":
			sql += " AND INSTR(subject_id, ?) > 0";
			break;
		// 과목명 기준
		case "3":
			sql += " AND INSTR(UPPER(subject_name), UPPER(?)) > 0";
		}

		sql += " ORDER BY sj_start_date DESC";
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			// 데이터 바인딩 구간
			switch (subKey) {
			case "1":
				pstmt.setString(1, id);
				break;
			case "2":
			case "3":
				pstmt.setString(1, id);
				pstmt.setString(2, subValue);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Instructor ins = new Instructor();

				ins.setSubject_id(rs.getString("subject_id"));
				ins.setSubject_name(rs.getString("subject_name"));
				ins.setSj_start_date(rs.getString("sj_start_date"));
				ins.setSj_end_date(rs.getString("sj_end_date"));
				ins.setCourse_name(rs.getString("course_name"));
				ins.setCr_start_date(rs.getString("cr_start_date"));
				ins.setCr_end_date(rs.getString("cr_end_date"));
				ins.setClassroom_name(rs.getString("classroom_name"));
				ins.setBook_name(rs.getString("book_name"));
				// book_img가 null인 경우 기본값으로 book.jpg라는 이름으로 입력
				String book_img = rs.getString("book_img");
				if (book_img == null) {
					book_img = "book.jpg";
				}
				ins.setBook_img(book_img);
				ins.setSt_count(rs.getInt("st_count"));

				result.add(ins);

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

	// 과목(강의 예정, 중, 종료)에 대한 수강생정보 출력
	public List<Instructor> ins_studentList(String searchkey, String searchvalue, String key, String id) {
		List<Instructor> result = new ArrayList<Instructor>();
		String sql = "SELECT scsv_student_id AS student_id, name, phone, failcheck, gr_attend, gr_writing, gr_practice, sc_attend, sc_writing, sc_practice";
		sql += " FROM st_dateCheckView";
		sql += " WHERE scsv_subject_id = ?";
		sql += " AND instructor_id = ?";

		switch (searchkey) {
		case "all":
			sql += "";
			break;
		case "stid":
			sql += " AND INSTR(scsv_student_id, ?) > 0";
			break;
		case "stname":
			sql += " AND INSTR(name, ?) > 0";
			System.out.println("여기!");
			break;
		}

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, key);
			pstmt.setString(2, id);
			switch (searchkey) {
			case "all":
				break;
			case "stid":
			case "stname":
				pstmt.setString(3, searchvalue);
				break;
			}
			System.out.println(searchkey);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Instructor ins = new Instructor();

				ins.setStudent_id(rs.getString("student_id"));
				ins.setSt_name(rs.getString("name"));
				ins.setSt_phone(rs.getString("phone"));
				ins.setFailcheck(rs.getInt("failcheck"));
				ins.setGr_attend(rs.getInt("gr_attend"));
				ins.setGr_writing(rs.getInt("gr_writing"));
				ins.setGr_practice(rs.getInt("gr_practice"));
				ins.setSc_attend(rs.getInt("sc_attend"));
				ins.setSc_writing(rs.getInt("sc_writing"));
				ins.setSc_practice(rs.getInt("sc_practice"));

				result.add(ins);

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

	// 6-1. 배점 출력
	public List<Instructor> ins_subScoreList(String key, String instructor_id, String skey, String svalue) {
		List<Instructor> result = new ArrayList<Instructor>();
		String sql = "SELECT subject_id, subject_name, sj_start_date,sj_end_date, course_name, cr_start_date,cr_end_date,"
				+ " classroom_name, book_name, book_img, sc_attend, sc_writing, sc_practice, gradecheck, test_date, test_paper"
				+ " FROM INST_GradeCheckView3_test" + " WHERE instructor_id = ?";

		switch (skey) {
		case "all":
			break;
		case "subject_id":
			sql += " AND INSTR(subject_id,?) > 0";
			break;
		case "subject_name":
			sql += " AND INSTR(UPPER(subject_name),UPPER(?)) > 0";
			break;
		case "course_name":
			sql += " AND INSTR(UPPER(course_name),UPPER(?)) > 0";
			break;
		}

		sql += " ORDER BY subject_id";

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, instructor_id);

			switch (skey) {
			case "all":
				break;
			case "subject_id":
			case "subject_name":
			case "course_name":
				pstmt.setString(2, svalue);
				break;

			}

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Instructor ins = new Instructor();

				ins.setSubject_id(rs.getString("subject_id"));
				ins.setSubject_name(rs.getString("subject_name"));
				ins.setSj_start_date(rs.getString("sj_start_date"));
				ins.setSj_end_date(rs.getString("sj_end_date"));
				ins.setCourse_name(rs.getString("course_name"));
				ins.setCr_start_date(rs.getString("cr_start_date"));
				ins.setCr_end_date(rs.getString("cr_end_date"));
				ins.setClassroom_name(rs.getString("classroom_name"));
				ins.setBook_name(rs.getString("book_name"));
				ins.setTest_paper(rs.getString("test_paper"));
				ins.setTest_date(rs.getString("test_date"));

				// book_img가 null인 경우 기본값으로 book.jpg라는 이름으로 입력
				String book_img = rs.getString("book_img");
				if (book_img == null) {
					book_img = "book.jpg";
				}
				ins.setBook_img(book_img);
				ins.setSc_attend(rs.getInt("sc_attend"));
				ins.setSc_writing(rs.getInt("sc_writing"));
				ins.setSc_practice(rs.getInt("sc_practice"));
				ins.setGradecheck(rs.getInt("gradecheck"));
				System.out.println("성적입력 여부(1:O, 0:X : "+rs.getInt("gradecheck"));

				result.add(ins);

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

	// 6-1. 시험 출력
	public List<Instructor> ins_testList(List<String> subject_id) {
		List<Instructor> result = new ArrayList<Instructor>();
		String sql = "SELECT test_date, test_paper, test_paper_name FROM test WHERE subject_id = ?";
		sql += " ORDER BY subject_id";

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			for(int i=0; i<subject_id.size(); i++){
				
			pstmt.setString(1, subject_id.get(i));

			ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					Instructor ins = new Instructor();

					String test_date = rs.getString("test_date");
					test_date = test_date.split(" ")[0]; 
					ins.setTest_date(test_date);
					System.out.println(test_date);
					ins.setTest_paper(rs.getString("test_paper"));
					ins.setTest_paper_name(rs.getString("test_paper_name"));
					
					result.add(ins);

				} else {
					Instructor ins = new Instructor();
					
					result.add(ins);
				}
				rs.close();
			}

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

	// 6-2. 배점 입력
	public int ins_addScore(Instructor in) {
		int result = 0;
		String sql1 = "INSERT INTO score(subject_id, attend_score, writing_score, practice_score) VALUES(?,?,?,?)";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql1);

			pstmt.setString(1, in.getSubject_id());// 과목번호
			pstmt.setInt(2, in.getSc_attend());// 출결
			pstmt.setInt(3, in.getSc_writing());// 필기
			pstmt.setInt(4, in.getSc_practice());// 실기

			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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

	// 6-3. 시험문제, 날짜 입력
	public int ins_addTest(Instructor in) {
		int result = 0;
		String sql = "INSERT INTO test(subject_id, test_date, test_paper, test_paper_name) VALUES(?, ?, ?, ?)";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, in.getSubject_id());// 과목번호
			pstmt.setString(2, in.getTest_date());// 시험날짜
			pstmt.setString(3, in.getTest_paper());// 시험날짜
			pstmt.setString(4, in.getTest_paper_name());
			
			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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
	// 6-4. 배점 수정
		public int ins_modifyScore(Instructor in) {
			int result = 0;
			String sql1 = "UPDATE score SET attend_score = ?, writing_score = ?, practice_score = ? WHERE subject_id = ?";

			try {
				conn = DatabaseConnection.connect();

				pstmt = conn.prepareStatement(sql1);

				pstmt.setInt(1, in.getSc_attend());// 출결
				pstmt.setInt(2, in.getSc_writing());// 필기
				pstmt.setInt(3, in.getSc_practice());// 실기
				pstmt.setString(4, in.getSubject_id());// 과목번호

				result = pstmt.executeUpdate();

			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
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
		// 6-5. 배점 삭제
		public int ins_deleteScore(String in_id) {
			int result = 0;
			String sql1 = "DELETE FROM score WHERE subject_id = ?";
			
			try {
				conn = DatabaseConnection.connect();
				
				pstmt = conn.prepareStatement(sql1);
				
				pstmt.setString(1, in_id);// 출결
				
				result = pstmt.executeUpdate();
				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
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
	
	//시험문제 수정
	public int ins_modifyTest(Instructor in) {
		int result = 0;
		String sql = "UPDATE TEST SET TEST_DATE =? , test_paper = ?, test_paper_name = ? WHERE subject_id = ?";
		

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, in.getTest_date());
			pstmt.setString(2, in.getTest_paper());
			pstmt.setString(3, in.getTest_paper_name());
			pstmt.setString(4, in.getSubject_id());
			
			result = pstmt.executeUpdate();
	

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			// System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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

	//시험삭제
	
	public int ins_deleteTest(Instructor in) {
		int result = 0;
		String sql = "DELETE TEST WHERE subject_id = ? ";
		

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, in.getSubject_id());
		
	
			result = pstmt.executeUpdate();
	

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			// System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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

	

	public List<Instructor> ins_gra_subjectList(String subject_id, String instructor_id) {
		List<Instructor> result = new ArrayList<Instructor>();
		String sql = "SELECT subject_id, subject_name, sj_start_date,sj_end_date, course_name, cr_start_date,cr_end_date, classroom_name, book_name, book_img, sc_attend, sc_writing, sc_practice, gradecheck"
				+ " FROM INST_GradeCheckView3_test" + " WHERE instructor_id = ? AND subject_id = ? ORDER BY subject_id";

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, instructor_id);
			pstmt.setString(2, subject_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Instructor ins = new Instructor();

				ins.setSubject_id(rs.getString("subject_id"));
				ins.setSubject_name(rs.getString("subject_name"));
				ins.setSj_start_date(rs.getString("sj_start_date"));
				ins.setSj_end_date(rs.getString("sj_end_date"));
				ins.setCourse_name(rs.getString("course_name"));
				ins.setCr_start_date(rs.getString("cr_start_date"));
				ins.setCr_end_date(rs.getString("cr_end_date"));
				ins.setClassroom_name(rs.getString("classroom_name"));
				ins.setBook_name(rs.getString("book_name"));
				// book_img가 null인 경우 기본값으로 book.jpg라는 이름으로 입력
				String book_img = rs.getString("book_img");
				if (book_img == null) {
					book_img = "book.jpg";
				}
				System.out.println("book_img:" + book_img);
				ins.setBook_img(book_img);
				ins.setSc_attend(rs.getInt("sc_attend"));
				ins.setSc_writing(rs.getInt("sc_writing"));
				ins.setSc_practice(rs.getInt("sc_practice"));
				ins.setGradecheck(rs.getInt("gradecheck"));

				result.add(ins);

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

	// 7-3.특정과목 수강중인 수강생 정보출력(성적, 수료여부)
	public List<Instructor> ins_gra_studentList(String key, String value, String subject_id) {
		List<Instructor> result = new ArrayList<Instructor>();
		String sql = "SELECT scsv_course_id AS course_id, scsv_subject_id AS subject_id, scsv_student_id AS student_id, name, phone, failcheck, gr_attend, gr_writing, gr_practice"
				+ " FROM st_dateCheckView WHERE scsv_subject_id = ?";
		if (key.equals("name")) {
			sql += " AND name = ?";
		}
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, subject_id);
			if (key.equals("name")) {
				pstmt.setString(2, value);
			}

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Instructor ins = new Instructor();

				ins.setCourse_id(rs.getString("course_id"));
				ins.setSubject_id(rs.getString("subject_id"));
				ins.setStudent_id(rs.getString("student_id"));
				ins.setSt_name(rs.getString("name"));
				ins.setSt_phone(rs.getString("phone"));
				ins.setFailcheck(rs.getInt("failcheck"));
				ins.setGr_attend(rs.getInt("gr_attend"));
				ins.setGr_writing(rs.getInt("gr_writing"));
				ins.setGr_practice(rs.getInt("gr_practice"));

				result.add(ins);

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

	public int ins_addGrade(Instructor ins) {
		int result = 0;
		String sql1 = "INSERT INTO GRADE(subject_id, course_id, student_id, attend_score, writing_score, practice_score) VALUES(?, ?, ?, ?, ?, ?)";
		System.out.println("test2");
		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql1);

			pstmt.setString(1, ins.getSubject_id());// 과목ID
			pstmt.setString(2, ins.getCourse_id());// 과정ID
			pstmt.setString(3, ins.getStudent_id());// 학생ID
			pstmt.setInt(4, ins.getGr_attend());// 출결점수
			pstmt.setInt(5, ins.getGr_writing());// 필기점수
			pstmt.setInt(6, ins.getGr_practice());// 실기점수

			result = pstmt.executeUpdate();
			System.out.println("왜 안되?");

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			// System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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

	public int ins_modifyGrade(Instructor ins) {
		int result = 0;
		/*
		 * UPDATE GRADE SET ATTEND_SCORE = 30, WRITING_SCORE = 30,
		 * PRACTICE_SCORE = 30 WHERE STUDENT_ID = 'st001' AND SUBJECT_ID =
		 * 'sj037' AND COURSE_ID = 'cs001';
		 */
		String sql = "UPDATE grade SET attend_score = ?, writing_score = ?, practice_score = ?";
		sql += "WHERE course_id = ?";
		sql += "AND subject_id = ?";
		sql += "AND student_id = ?";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ins.getGr_attend());// 출결점수
			pstmt.setInt(2, ins.getGr_writing());// 필기점수
			pstmt.setInt(3, ins.getGr_practice());// 실기점수
			pstmt.setString(4, ins.getCourse_id());// 과정ID
			pstmt.setString(5, ins.getSubject_id());// 과목ID
			pstmt.setString(6, ins.getStudent_id());// 학생ID

			result = pstmt.executeUpdate();
			System.out.printf("%d개의 값 수정완료", result);

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			// System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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

	public int ins_deleteGrade(Instructor ins) {
		int result = 0;
		/*
		 * 
		 * DELETE FROM grade WHERE COURSE_ID = 'cs001' AND SUBJECT_ID = 'sj037'
		 * AND student_id = 'st001';
		 * 
		 */
		String sql = "DELETE FROM grade WHERE course_id = ? AND subject_id = ? AND student_id = ?";

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ins.getCourse_id());// 과정ID
			pstmt.setString(2, ins.getSubject_id());// 과목ID
			pstmt.setString(3, ins.getStudent_id());// 학생ID
			System.out.println("1:" + ins.getCourse_id());
			System.out.println("2:" + ins.getSubject_id());
			System.out.println("3:" + ins.getStudent_id());

			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			// System.out.println("오류! 다시 시도하세요.");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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

	// 강사 메뉴경로
	// ㄹㄷㄴㅁㄻㄴㅇㄹ ㅁㄴㄷㄻㄷㄴㅁㄹㅊㄴㄻㄴㄻㄴㄹㅇㅁㄴㄻㄷㄴ
	public Instructor ins_naviTitle(String key, String value) {
		Instructor result = new Instructor();
		String sql = "SELECT subject_name, sj_start_date ,sj_end_date FROM SCCBCourse_recordView"
				+ " WHERE subject_id = ?";
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, value);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result.setSubject_name(rs.getString("subject_name"));
				result.setSj_start_date(rs.getString("sj_start_date"));
				result.setSj_end_date(rs.getString("sj_end_date"));
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

	// 7-2. 타이틀) 선택한 특정 과목명 + 기간 + 배점
	public Instructor ins_gra_naviTitle(String key, String value) {
		Instructor result = new Instructor();
		String sql = "SELECT subject_name,sj_start_date, sj_end_date,course_id, sc_attend, sc_writing, sc_practice FROM INST_GradeCheckView3 WHERE subject_id = ?";
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, value);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result.setSubject_name(rs.getString("subject_name"));
				result.setSj_start_date(rs.getString("sj_start_date"));
				result.setSj_end_date(rs.getString("sj_end_date"));
				result.setCourse_id(rs.getString("course_id"));
				result.setSc_attend(rs.getInt("sc_attend"));
				result.setSc_writing(rs.getInt("sc_writing"));
				result.setSc_practice(rs.getInt("sc_practice"));
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

	// 7-4. 타이틀) 선택한 특정 과목명 + 기간 + 수강생 이름
	public Instructor ins_gra_naviTitle2(String sj, String st) {
		Instructor result = new Instructor();
		String sql = "SELECT scsv_student_id AS student_id, name"
				+ " FROM st_dateCheckView WHERE scsv_subject_id = ? AND scsv_student_id = ?";
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, sj);
			pstmt.setString(2, st);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result.setStudent_id(rs.getString("student_id"));
				result.setSt_name(rs.getString("name"));
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
