package com.academy.ssit.admin;

import java.sql.*;
import java.util.*;

import com.academy.ssit.DatabaseConnection;

public class Ad_subClassDAO1 {

	Connection conn = null;

	/************************** 1.기초 정보 관리 *********************************/

	// 기초정보관리>과정명

	// 1. 입력
	public int ad_info_addCourseName(Admin ad) {
		int result = 0;

		String sql1 = "SELECT course_name_id FROM courseSeqView";

		String sql2 = "INSERT INTO course_name(course_name_id, course_name) VALUES (?, ?)";
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랜잭션 처리
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

	// 2.출력
	public List<Admin> ad_info_courseNameList(String key, String value) {
		List<Admin> result = new ArrayList<Admin>();
		// 쿼리

		String sql = "SELECT course_name_id, course_name FROM course_name";

		switch (key) {
		case "all":
			break;
		case "course_name_id":
			sql += " WHERE INSTR(UPPER(course_name_id),UPPER(?)) > 0";
			break;
		case "course_name":
			sql += " WHERE INSTR(UPPER(course_name),UPPER(?)) > 0";
			break;
		}

		sql += " ORDER BY course_name_id";

		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "all":
				break;
			case "course_name_id":
				pstmt.setString(1, value);
				break;
			case "course_name":
				pstmt.setString(1, value);
				break;
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

			System.out.println(result.size());

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

	// 3. 삭제
	public int ad_info_deleteCourseName(Admin ad) {
		int result = 0;
		String sql = "DELETE FROM course_name  WHERE  course_name_id = ?";

		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ad.getCourse_name_id());

			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
			}
		}

		return result;
	}

	// 3. 수정
	public int ad_info_modifyCourseName(Admin ad) {
		int result = 0;
		String sql = "UPDATE course_name SET course_name = ? WHERE course_name_id = ?";

		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ad.getCourse_name());
			pstmt.setString(2, ad.getCourse_name_id());

			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
			}
		}

		return result;
	}

	// 기초정보관리>과목명

	// 1. 입력
	public int ad_info_addSubName(Admin ad) {
		int result = 0;

		String sql1 = "SELECT subject_name_id FROM subjectSeqView ";

		String sql2 = "INSERT INTO subject_name(subject_name_id, subject_name) VALUES (?, ?) ";
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랜잭션 처리
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

	// 2.출력
	public List<Admin> ad_info_subjectNameInfoList(String key, String value) {
		List<Admin> result = new ArrayList<Admin>();

		String sql = "SELECT subject_name_id, subject_name  FROM SUBJECT_NAME";

		switch (key) {
		case "all":
			break;
		case "subject_name_id":
			sql += "  WHERE INSTR(UPPER(subject_name_id),UPPER(?)) > 0";
			break;
		case "subject_name":
			sql += "  WHERE INSTR(UPPER(subject_name),UPPER(?)) > 0";
			break;
		}

		sql += " ORDER BY subject_name_id";

		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "all":
				break;
			case "subject_name_id":
				pstmt.setString(1, value);
				break;
			case "subject_name":
				pstmt.setString(1, value);
				break;
			}

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
	
	//삭제버튼 비활성화 위해 list 비교용 메소드
	
	public List<Admin> ad_info_subjectNameInfoList2() {
		List<Admin> result = new ArrayList<Admin>();

		String sql = "SELECT subject_name_id ,course_name_id FROM COURSESUBJECTVIEW";

		

		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Admin ad = new Admin();
				ad.setSubject_name_id(rs.getString("subject_name_id"));
				ad.setCourse_name_id(rs.getString("course_name_id"));
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
	

	//3. 삭제
	public int ad_delete_subjectNameInfo(Admin ad) {
		int result = 0;
		String sql = "DELETE FROM subject_name  WHERE  subject_name_id = ?";
		
		PreparedStatement pstmt = null;
		
		try{
			conn= DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ad.getSubject_name_id());
			
			
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
			}
		}

		return result;
	}

	
	
	
	//3. 수정
		public int ad_modify_subjectNameInfo(Admin ad) {
			int result = 0;
			String sql = "UPDATE subject_name SET subject_name = ? WHERE subject_name_id = ?";
			
			PreparedStatement pstmt = null;
			
			try{
				conn= DatabaseConnection.connect();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, ad.getSubject_name());
				pstmt.setString(2, ad.getSubject_name_id());
				
				result = pstmt.executeUpdate();
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
				}
				try {
					DatabaseConnection.close();
				} catch (SQLException e) {
				}
			}

			return result;
		}

	
	
	

	// 기초정보관리>강의실명(정원)

	// 1. 입력

	/*
	 * SELECT classroom_id FROM classroomSeqView;
	 * 
	 * INSERT INTO classroom(classroom_id, classroom_name, max_num) VALUES (?,
	 * ?, ?);
	 */

	public int ad_info_addClassroom(Admin ad) {
		int result = 0;
		String sql1 = "SELECT classroom_id FROM classroomSeqView";

		String sql2 = "INSERT INTO classroom(classroom_id, classroom_name, max_num) VALUES (?, ?, ?)";
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랜잭션 처리
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
			// e.printStackTrace();

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

	// 2.출력
	public List<Admin> ad_info_classroomList(String key, String value) {
		List<Admin> result = new ArrayList<Admin>();
		String sql = "SELECT classroom_id, classroom_name, max_num  FROM classroom";

		switch (key) {
		case "all":
			break;
		case "classroom_id":
			sql += " WHERE INSTR(UPPER(classroom_id),UPPER(?))>0";
			break;
		case "classroom_name":
			sql += " WHERE INSTR(UPPER(classroom_name),UPPER(?))>0";
			break;
		}

		sql += " ORDER BY classroom_id";

		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "all":
				break;
			case "classroom_id":
				pstmt.setString(1, value);
				break;
			case "classroom_name":
				pstmt.setString(1, value);
				break;
			}

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

	
	
	//삭제버튼 비활성화 위해 리스트 비교용 메소드 (COURSE 테이블에 포함된 classroom_id 비교) 
	
	public List<Admin>ad_info_classroomCheckList(){
		 List<Admin>result = new ArrayList<Admin>();
		 
		 String sql = "SELECT classroom_id from COURSE";
		 

			PreparedStatement pstmt = null;
			try {
				conn = DatabaseConnection.connect();
				pstmt = conn.prepareStatement(sql);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					Admin ad = new Admin();
					ad.setClassroom_id(rs.getString("classroom_id"));
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
	
	
	//강의실명 수정 메소드
	
	public int ad_info_modifyClassroom(Admin ad) {
		int result = 0;
		String sql = "UPDATE CLASSROOM SET classroom_name = ?, max_num = ? WHERE classroom_id = ?";
		
		PreparedStatement pstmt = null;
		
		try{
			conn= DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ad.getClassroom_name());
			pstmt.setInt(2, ad.getMax_num());
			pstmt.setString(3, ad.getClassroom_id());
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
			}
		}

		return result;
	}
	
	
	
	//강의실명 삭제 메소드
			public int  ad_info_deleteClassroom(Admin ad) {
				int result = 0;
				String sql = "DELETE FROM CLASSROOM  WHERE  classroom_id = ?";
				
				PreparedStatement pstmt = null;
				
				try{
					conn= DatabaseConnection.connect();
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, ad.getClassroom_id());
					
					
					result = pstmt.executeUpdate();
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (pstmt != null)
							pstmt.close();
					} catch (SQLException e) {
					}
					try {
						DatabaseConnection.close();
					} catch (SQLException e) {
					}
				}

				return result;
			}


		
			
			
	
	// 기초정보관리>교재명(출판사)

	   // 1. 입력
	   public int ad_info_addBook(Admin ad) {
	      int result = 0;
	      String sql1 = "SELECT book_id FROM bookSeqView";

	      String sql2 = "INSERT INTO book(book_id, book_name, publisher, book_img)  VALUES (?, ?, ?, ?)";
	      PreparedStatement pstmt1 = null;
	      PreparedStatement pstmt2 = null;

	      try {
	         conn = DatabaseConnection.connect();

	         // 트랜잭션 처리
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
	         pstmt2.setString(4, ad.getBook_img());

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

	   // 3. 수정
	   public int ad_info_modifyBookName(Admin ad) {
	      int result = 0;
	      String sql = "UPDATE book SET book_name = ?, publisher = ?, book_img = ? WHERE book_id = ?";

	      PreparedStatement pstmt = null;

	      try {
	         conn = DatabaseConnection.connect();
	         pstmt = conn.prepareStatement(sql);

	         pstmt.setString(1, ad.getBook_name());
	         pstmt.setString(2, ad.getPublisher());
	         pstmt.setString(3, ad.getBook_img());
	         pstmt.setString(4, ad.getBook_id());
	         
	         result = pstmt.executeUpdate();

	      } catch (ClassNotFoundException | SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (pstmt != null)
	               pstmt.close();
	         } catch (SQLException e) {
	         }
	         try {
	            DatabaseConnection.close();
	         } catch (SQLException e) {
	         }
	      }

	      return result;
	   }

	   // 2.출력
	   public List<Admin> ad_info_bookList(String key, String value) {
	      List<Admin> result = new ArrayList<Admin>();
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
	            Admin ad = new Admin();
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
	   
	   //삭제
	   
	public int ad_info_deleteBookName(Admin ad) {
		int result = 0;
		String sql = "DELETE book  WHERE book_id = ?";

		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ad.getBook_id());

			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
			}
		}

		return result;
	}
	
	
	
	/*
	//교재명 삭제 버튼 비활성화 위해 리스트 비교 메소드
	public List<Admin> book_checklist(){
		List<Admin> result = null;
		String sql = "SELECT ";
		
		
		return result;
	}*/
	   
	}