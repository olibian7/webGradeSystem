package com.academy.ssit.student;

import java.sql.*;
import java.util.*;

import com.academy.ssit.DatabaseConnection;
import com.academy.ssit.admin.Admin;

//데이터베이스 액션 클래스
public class StudentDAO {

   Connection conn = null;
   PreparedStatement pstmt = null;

   // 3. 사용자 정보 조회 - 타)ID에 매칭되는 이름 가져오기
   // - ID에 매칭되는 데이터 가져오기
   /*
    * 
    * /* - ID에 매칭되는 이름 가져오기 SELECT name FROM student WHERE student_id =
    * 'st001'; 1.입력한 ID에 해당하는 ID와 주민번호 뒷자리 불러오기 SELECT student_id, social_num
    * FROM student WHERE student_id = ? AND social_num = ? 3. 수강생 정보 조회 SELECT
    * student_id, name, social_num, phone,
    * TO_CHAR(registration_date,'YYYY-MM-DD') AS registration_date FROM student
    * WHERE student_id = ?
    * 
    * 
    */

   public List<Student> st_studentInfo(String key, String value) {
      /*
       * ----------------------- >학생 화면>홍길동님의 정보 ----------------------- 수강생
       * 아이디 : st001 수강생 이름 : 홍길동 주민번호 뒷자리 : 1111111 전화번호 : 010-1234-1234 등록일
       * : 20150822
       * 
       * press any key to continue....
       * 
       */
      List<Student> result = new ArrayList<Student>();
      String sql = "SELECT student_id, name, social_num, phone, TO_CHAR(registration_date,'YYYY-MM-DD') AS registration_date FROM student WHERE student_id = ?";
      try {
         conn = DatabaseConnection.connect();
         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, value);

         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            Student s = new Student();
            s.setStudent_id(rs.getString("student_id"));
            s.setName(rs.getString("name"));
            s.setSocial_num(rs.getString("social_num"));
            s.setPhone(rs.getString("phone"));
            s.setRegistration_date(rs.getString("registration_date"));
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

   /*
    * 4. 과정조회 - 과정 정보 출력 - 타) 과정명 + 과정기간
    */

   /*-- 타)과정+기간
   SELECT cn.course_name, cs.start_date, cs.end_date
     FROM course cs, course_name cn
     WHERE cs.course_name_id = cn.course_name_id
       AND cs.course_id = 'cs001';
       
       3. 과정정보 출력(과정ID, 과정명, 과정기간)
   SELECT course_id, course_name
      , CONCAT(CONCAT(cr_start_date, '~'),cr_end_date) AS coursedate
   FROM studentCourseView
      WHERE student_id = ?
      
      
      --------------------------
   >학생 화면>과정 조회
   ----------------------------------------------------------------------------------------
   과정ID      과정명                    과정기간
   ----------------------------------------------------------------------------------------
   cn001   [100%국비지원]오라클 전문가 과정         2016-11-01~2017-03-31
   cn002   [100%국비지원]자바 전문가 과정           2016-12-01~2017-04-30
   cn003   [100%국비지원]자바스크립트 전문가 과정   2017-01-01~2017-05-31
   ----------------------------------------------------------------------------------------
   */
   public List<Student> st_courseList(String key, String value) {
      List<Student> result = new ArrayList<Student>();
      String sql = "SELECT course_id, course_name, cr_start_date, cr_end_date FROM studentCourseView WHERE student_id = ?";

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

   // - 과정이 갖고있는 과목 학생(본인)성적 교재명 출력
   public List<Student> st_gradeList(String course_id, String student_id) {

      /* 과정ID 선택(0 quit)?cn001 */
      /*
       * >학생 화면>과정 조회(성적)>[100%국비지원]오라클 전문가 과정(2016-11-01~2017-03-31)
       * ---------------------------------------------------------------------
       * ---------------------------------------------------------------------
       * --------------- 과목ID 과목명 과목기간 교재명 강사명 출결(점수/배점) 필기(점수/배점) 실기(점수/배점)
       * 시험날짜 시험문제
       * ---------------------------------------------------------------------
       * ---------------------------------------------------------------------
       * --------------- sj003 오라클 2016-11-01 ~ 2016-12-01 자바ABCD 이진욱 ?/20
       * ?/30 ?/50 2016-12-08 오라클중간시험.zip sj004 자바 2016-12-01 ~ 2017-01-31
       * 안드로이드ABCD 이진욱 ?/20 ?/40 ?/40 2017-02-07 안드로이드중간시험.zip
       */
      List<Student> result = new ArrayList<Student>();
      String sql = "SELECT subject_id, course_name, subject_name, sj_start_date, sj_end_date, book_name, book_img,book_id, in_name, gr_attend, sc_attend, gr_writing, sc_writing, gr_practice, sc_practice, test_date, test_paper, test_paper_name ";
      sql += " FROM studentCourseSubjectGradeView";
      sql += " WHERE student_id = ? AND course_id = ?";
      sql += " ORDER BY subject_id";
      try {
         conn = DatabaseConnection.connect();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, student_id);
         pstmt.setString(2, course_id);

         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            Student s = new Student();
            // scsv_subject_id, subject_name, sj_start_date, sj_end_date,
            // book_name
            // , in_name, gr_attend, sc_attend, gr_writing, sc_writing,
            // gr_practice
            // , sc_practice, test_date, test_paper
            s.setCourse_name(rs.getString("course_name"));
            s.setScsv_subject_id(rs.getString("subject_id"));
            s.setSubject_name(rs.getString("subject_name"));
            s.setSj_start_date(rs.getString("sj_start_date"));
            s.setSj_end_date(rs.getString("sj_end_date"));
            s.setBook_name(rs.getString("book_name"));
            s.setTest_paper_name(rs.getString("test_paper_name"));
            String bookimg = rs.getString("book_img");            
            if(bookimg != null){
            	s.setBook_img(bookimg);	
            }else{
            	s.setBook_img("book.jpg");
            }
            s.setBook_id(rs.getString("book_id"));
            s.setIn_name(rs.getString("in_name"));
            s.setGr_attend(rs.getInt("gr_attend"));
            s.setSc_attend(rs.getInt("sc_attend"));
            s.setGr_writing(rs.getInt("gr_writing"));
            s.setSc_writing(rs.getInt("sc_writing"));
            s.setGr_practice(rs.getInt("gr_practice"));
            s.setSc_practice(rs.getInt("sc_practice"));
            s.setTest_date(rs.getString("test_date"));
            s.setTest_paper(rs.getString("test_paper"));

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

   // navi 화면
   // >학생 화면>과정 조회(성적)>[100%국비지원]오라클 전문가 과정(2016-11-01~2017-03-31)
   // ㄹ디ㅏㄹ머리ㅑㅁㄴ덜망러믿냐르민ㅇ라ㅓ디랴맏르킽얄
   public Student studentNaviList(String key, String value) {
      Student result = new Student();
      String sql = "SELECT course_id, course_name, cr_start_date, cr_end_date FROM student_title_term WHERE course_id = ?";

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
            result = s;
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
   
   public int st_studentModify(Student s) {
	      
	      String sql = "UPDATE student SET name = ?, phone = ? WHERE STUDENT_ID = ?";
	      int result = 0;
	      try {
	         conn = DatabaseConnection.connect();
	         pstmt = conn.prepareStatement(sql);

	         pstmt.setString(1, s.getName());
	         pstmt.setString(2, s.getPhone());
	         pstmt.setString(3, s.getStudent_id());

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

	// 2.출력
	public List<Student> st_bookList(String key, String value) {
		List<Student> result = new ArrayList<Student>();
		String sql = "SELECT book_id, book_name, publisher, book_img FROM book";

		switch (key) {
		case "all":
			break;
		case "book_id":
			sql += " WHERE INSTR(UPPER(book_id),UPPER(?))>0";
			break;
		case "book_name":
			sql += " WHERE INSTR(UPPER(book_name),UPPER(?))>0";
			break;
		case "publisher":
			sql += " WHERE INSTR(UPPER(publisher),UPPER(?))>0";
			break;
		}

		sql += " ORDER BY book_id";

		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "all":
				break;
			case "book_id":
				pstmt.setString(1, value);
				break;
			case "book_name":
				pstmt.setString(1, value);
				break;
			case "publisher":
				pstmt.setString(1, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Student ad = new Student();
				ad.setBook_id(rs.getString("book_id"));
				ad.setBook_name(rs.getString("book_name"));
				ad.setPublisher(rs.getString("publisher"));
				ad.setBook_img(rs.getString("book_img"));
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