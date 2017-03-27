package com.academy.ssit.admin;

import java.util.*;

import com.academy.ssit.DatabaseConnection;


import java.sql.*;
public class AdminDAO {
	Connection conn = null;
	

	
	//기초정보관리>과정명
	
	//1. 입력
	public int addCourse(Admin ad){
		int result = 0;
		String sql1 = "SELECT course_name_id FROM courseSeqView";

		String sql2 ="INSERT INTO course_name(course_name_id, course_name) VALUES (?, ?)";
		  PreparedStatement pstmt1 = null;
	      PreparedStatement pstmt2 = null;
		
		try{
			conn = DatabaseConnection.connect();
			
		 
	         //트랜잭션 처리
	         conn.setAutoCommit(false);

	         pstmt1 = conn.prepareStatement(sql1);
	         ResultSet rs = pstmt1.executeQuery();

	         String course_name_id = "cn000";
	         while (rs.next()) {
	        	 course_name_id = rs.getString("course_name_id");
	         }
	         rs.close();

	         // 2. 과정명 입력
	         pstmt2 = conn.prepareStatement(sql2);

	         pstmt2.setString(1, course_name_id);
	         pstmt2.setString(2, ad.getCourse_name());
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
	            if (pstmt1 != null) {
	               pstmt1.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }

	         try {
	            if (pstmt2 != null) {
	               pstmt2.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }

	      }
		
		return result;
		
		
	}
	
	//2.출력
	public List<Admin> courseInfoList(){
		List<Admin> result = new ArrayList<Admin>();
		//쿼리
		String sql = "SELECT course_name_id, course_name FROM course_name";

			PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Admin  ad = new Admin();
				ad.setCourse_id(rs.getString("course_name_id"));
				ad.setCourse_name(rs.getString("course_name"));
					result.add(ad);
				
			}rs.close();

		} catch ( ClassNotFoundException|SQLException e1) {
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
	
	
	
	//기초정보관리>과목명

	//1. 입력
	public int addSub(Admin ad){
		int result = 0;
		String sql1 = "SELECT subject_name_id FROM subjectSeqView ";

		String sql2 ="INSERT INTO subject_name(subject_name_id, subject_name) VALUES (?, ?) ";
		  PreparedStatement pstmt1 = null;
	      PreparedStatement pstmt2 = null;
	  
		try{
			conn = DatabaseConnection.connect();
			
		 
	         //트랜잭션 처리
	         conn.setAutoCommit(false);

	         pstmt1 = conn.prepareStatement(sql1);
	         ResultSet rs = pstmt1.executeQuery();

	         String subject_name_id = "";
	         while (rs.next()) {
	        	 subject_name_id = rs.getString("subject_name_id");
	         }
	         rs.close();

	      
	         pstmt2 = conn.prepareStatement(sql2);

	         pstmt2.setString(1, subject_name_id);
	         pstmt2.setString(2, ad.getSubject_name());
	        
	       
	         result= pstmt2.executeUpdate();
	       
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
	            if (pstmt1 != null) {
	               pstmt1.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }

	         try {
	            if (pstmt2 != null) {
	               pstmt2.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	        
	      }
		
		return result;
		
		
	}
	
	
	//2.출력
	public List<Admin> subjectInfoList(){
List<Admin> result = new ArrayList<Admin>();
		String sql = "SELECT subject_name_id, subject_name  FROM subject_name ORDER BY subject_name_id" ;


		PreparedStatement pstmt = null;
	try {
		conn = DatabaseConnection.connect();
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Admin  ad = new Admin();
			ad.setSubject_name_id(rs.getString("subject_name_id"));
			ad.setSubject_name(rs.getString("subject_name"));
				result.add(ad);
			
		}rs.close();

	} catch ( ClassNotFoundException|SQLException e1) {
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

	
	
	//기초정보관리>강의실명(정원)

     //1. 입력
		
/*	SELECT classroom_id FROM classroomSeqView;

	INSERT INTO classroom(classroom_id, classroom_name, max_num)
	  VALUES (?, ?, ?);*/
	
	public int addClassroom(Admin ad){
		int result = 0;
		String sql1 = "SELECT classroom_id FROM classroomSeqView";

		String sql2 ="INSERT INTO classroom(classroom_id, classroom_name, max_num) VALUES (?, ?, ?)";
		  PreparedStatement pstmt1 = null;
	      PreparedStatement pstmt2 = null;
	     
		
		try{
			conn = DatabaseConnection.connect();
			
		 
	         //트랜잭션 처리
	         conn.setAutoCommit(false);

	         pstmt1 = conn.prepareStatement(sql1);
	         ResultSet rs = pstmt1.executeQuery();

	         String classroom_id = "";
	         while (rs.next()) {
	        	 classroom_id = rs.getString("classroom_id");
	         }
	         rs.close();

	      
	         pstmt2 = conn.prepareStatement(sql2);

	         pstmt2.setString(1, classroom_id);
	         pstmt2.setString(2, ad.getClassroom_name());
	         pstmt2.setInt(3, ad.getMax_num());
	       
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
	            if (pstmt1 != null) {
	               pstmt1.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }

	         try {
	            if (pstmt2 != null) {
	               pstmt2.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	        
	      }
		
		return result;
		
		
	}
	
	//2.출력
	public List<Admin> clroomInfoList(){
		List<Admin> result = new ArrayList<Admin>();
		String sql = "SELECT classroom_id, classroom_name, max_num  FROM classroom";


		PreparedStatement pstmt = null;
	try {
		conn = DatabaseConnection.connect();
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Admin  ad = new Admin();
			ad.setClassroom_id(rs.getString("classroom_id"));
			ad.setClassroom_name(rs.getString("classroom_name"));
			ad.setMax_num(rs.getInt("max_num"));
				result.add(ad);
			
		}rs.close();

	} catch ( ClassNotFoundException|SQLException e1) {
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
	
	
	//기초정보관리>교재명(출판사)


	//1. 입력
	public int addBook(Admin ad){
		int result = 0;
		String sql1 = "SELECT book_id FROM bookSeqView";

		String sql2 ="INSERT INTO book(book_id, book_name, publisher)  VALUES (?, ?, ?)";
		  PreparedStatement pstmt1 = null;
	      PreparedStatement pstmt2 = null;
	     
		
		try{
			conn = DatabaseConnection.connect();
			
		 
	         //트랜잭션 처리
	         conn.setAutoCommit(false);

	         pstmt1 = conn.prepareStatement(sql1);
	         ResultSet rs = pstmt1.executeQuery();

	         String book_id = "";
	         while (rs.next()) {
	        	 book_id = rs.getString("book_id");
	         }
	         rs.close();

	      
	         pstmt2 = conn.prepareStatement(sql2);

	         pstmt2.setString(1, book_id);
	         pstmt2.setString(2, ad.getBook_name());
	         pstmt2.setString(3, ad.getPublisher());
	       
	         result= pstmt2.executeUpdate();
	       
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
	            if (pstmt1 != null) {
	               pstmt1.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }

	         try {
	            if (pstmt2 != null) {
	               pstmt2.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	        
	      }
		
		return result;
		
		
	}
			
	//2.출력
	public List<Admin> bookInfoList(){
		List<Admin> result = new ArrayList<Admin>();
		String sql = "SELECT book_id, book_name, publisher  FROM book";

		PreparedStatement pstmt = null;
	try {
		conn = DatabaseConnection.connect();
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Admin  ad = new Admin();
			ad.setBook_id(rs.getString("book_id"));
			ad.setBook_name(rs.getString("book_name"));
			ad.setPublisher(rs.getString("publisher"));
				result.add(ad);
			
		}rs.close();

	} catch ( ClassNotFoundException|SQLException e1) {
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
