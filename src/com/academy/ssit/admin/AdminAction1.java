package com.academy.ssit.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.academy.ssit.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;


public class AdminAction1 {

	private Ad_subClassDAO1 dao = new Ad_subClassDAO1();

	// 3-1. 관리자계정 - 기초정보관리>과정명입력(과정명ID, 과정명)
	public String ad_info_addCourseName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}

		request.setCharacterEncoding("UTF-8");

		String course = request.getParameter("course");

		Admin ad = new Admin();

		ad.setCourse_name(course);

		int result = dao.ad_info_addCourseName(ad);

		if (result == 0) {
			return "/error";
		}

		return "redirect:ad_info_courseNameList";
	}

	// 3-1-1. 관리자계정 - 기초정보관리>과정수정(과정명ID, 과정명)
	public String ad_info_modifyCourseName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}

		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_info_modifyCourseName 메소드 호출");

		String course_name_id = request.getParameter("id");
		String course_name = request.getParameter("course");

		Admin ad = new Admin();
		ad.setCourse_name(course_name);
		ad.setCourse_name_id(course_name_id);

		System.out.println(course_name_id + course_name);
		dao.ad_info_modifyCourseName(ad);

		return "redirect:ad_info_courseNameList";
	}

	// 3-1-1. 관리자계정 - 기초정보관리>과정삭제(과정명ID, 과정명)
	public String ad_info_deleteCourseName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}

		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_info_deleteCourseName 메소드 호출");

		String course_name_id = request.getParameter("id");

		Admin ad = new Admin();
		ad.setCourse_name_id(course_name_id);

		System.out.println("과정명ID:" + course_name_id);
		System.out.println();
		dao.ad_info_deleteCourseName(ad);

		return "redirect:ad_info_courseNameList";
	}

	// 3-2. 관리자계정 - 기초정보관리>과정명리스트 출력(과정명ID, 과정명)
	// 과정명 메인
	public String ad_info_courseNameList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}
		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_info_courseNameList 메소드 호출");

		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		System.out.println(skey);
		System.out.println(svalue);
		if (skey == null) {
			skey = "all";
			svalue = "";
		}

		List<Admin> list = dao.ad_info_courseNameList(skey, svalue);
		List<Admin> check_list = dao.ad_info_subjectNameInfoList2();
		String length = String.valueOf(list.size());
		System.out.println(length);

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("list", list);
		request.setAttribute("length", length);
		request.setAttribute("check_list", check_list);
		return "/pages/admin_v/admin_basic_info";
	}

	
		
		
		// 3-3. 관리자계정 - 기초정보관리>과목명입력(과목명ID, 과목명)
		public String ad_info_addSubName(HttpServletRequest request, HttpServletResponse response)
				throws SecurityException, IOException {
			// 아이디 체크
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("admin_id");

			if (id == null) {
				return "/pages/error";
			}
			request.setCharacterEncoding("UTF-8");

			String subject = request.getParameter("subject");

			Admin ad = new Admin();

			ad.setSubject_name(subject);

			int result = dao.ad_info_addSubName(ad);

			if (result == 0) {
				return "/error";
			}

			return "redirect:ad_info_subjectNameInfoList";
		}
		
		
		
		// 3-3. 관리자계정 - 기초정보관리>과목명수정(과목명ID, 과목명)
		public String ad_info_modifySubName(HttpServletRequest request, HttpServletResponse response)
				throws SecurityException, IOException {
			// 아이디 체크
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("admin_id");

			if (id == null) {
				return "/pages/error";
			}
			request.setCharacterEncoding("UTF-8");

			System.out.println("ad_info_modifySubName 메소드 호출");
			
			String subject_name_id = request.getParameter("subject_name_id");
			String subject_name = request.getParameter("subject");
			
			System.out.println();
			System.out.println(subject_name +subject_name_id );
			Admin ad = new Admin();

			ad.setSubject_name(subject_name);
			ad.setSubject_name_id(subject_name_id);

			int result = dao.ad_modify_subjectNameInfo(ad);

			
			if (result == 0) {
				return "/error";
			}

			return "redirect:ad_info_subjectNameInfoList";
		}

		
		// 3-3. 관리자계정 - 기초정보관리>과목명삭제(과목명ID, 과목명)
			public String ad_info_deleteSubName(HttpServletRequest request, HttpServletResponse response)
					throws SecurityException, IOException {
				// 아이디 체크
				HttpSession session = request.getSession();
				String id = (String) session.getAttribute("admin_id");

				if (id == null) {
					return "/pages/error";
				}
				
				
				request.setCharacterEncoding("UTF-8");
				
				
				
				String subject_name_id = request.getParameter("subject_name_id");

				System.out.println(subject_name_id);
				
								
				Admin ad = new Admin();
				ad.setSubject_name_id(subject_name_id);
				int result = dao.ad_delete_subjectNameInfo(ad);
				
				if (result == 0) {
					return "/error";
				}

			
			
				
				
			
				return "redirect:ad_info_subjectNameInfoList";
			}
			
		
	
	// 3-4. 관리자계정 - 기초정보관리>과목명리스트 출력(과목명ID, 과목명)
	// 과목명 메인
	public String ad_info_subjectNameInfoList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ad_info_subjectNameInfoList 메소드 호출");
		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}
		request.setCharacterEncoding("UTF-8");

		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
	

		String subject_name_id = request.getParameter("subject_name_id");
		System.out.println(subject_name_id);
		System.out.println(svalue);

		if (skey == null) {
			skey = "all";
			svalue = "";
		}

		List<Admin> list = dao.ad_info_subjectNameInfoList(skey, svalue);
	
		List<Admin> subject_list = dao.ad_info_subjectNameInfoList2();
	
		int length = list.size();
		
			
		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("list", list);
		request.setAttribute("subject_list", subject_list);
		request.setAttribute("length", length);
		return "/pages/admin_v/admin_basic_info_subject";
	}

	
	
	// 3-5. 관리자계정 - 기초정보관리>강의실명입력(강의실명ID, 강의실명, 정원)
	public String ad_info_addClassroom(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}
		request.setCharacterEncoding("UTF-8");

		String classroom = request.getParameter("classroom");
		String max_num = request.getParameter("max_num");

		Admin ad = new Admin();

		ad.setClassroom_name(classroom);
		ad.setMax_num(Integer.parseInt(max_num));

		int result = dao.ad_info_addClassroom(ad);

		if (result == 0) {
			return "/error";
		}

		return "redirect:ad_info_classroomList";
	}

	// 3-6. 관리자계정 - 기초정보관리>강의실명리스트 출력(강의실명ID, 강의실명, 정원)
	// 강의실 메인
	public String ad_info_classroomList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ad_info_classroomList메소드 호출");
		request.setCharacterEncoding("UTF-8");
		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		if (skey == null) {
			skey = "all";
			svalue = "";
		}

		List<Admin> list = dao.ad_info_classroomList(skey, svalue);
		List<Admin> checklist = dao.ad_info_classroomCheckList();
		int length = list.size();

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("list", list);
		request.setAttribute("length", length);
		request.setAttribute("checklist", checklist);
		
		return "/pages/admin_v/admin_basic_info_room";
	}

	
	//관리자계정 - 기초정보관리>강의실명리스트 삭제(강의실명ID, 강의실명, 정원)
	public String ad_info_deleteClassroom(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}
		
		
		request.setCharacterEncoding("UTF-8");
		System.out.println();
		System.out.println("ad_info_deleteClassroom메소드 호출!");
		
		
		String classroom_id = request.getParameter("classroom_id");

		System.out.println(classroom_id);
		
						
		Admin ad = new Admin();
		ad.setClassroom_id(classroom_id);
		int result = dao.ad_info_deleteClassroom(ad);
		
		
		if (result == 0) {
			return "/error";
		}

		
	
		return "redirect:ad_info_classroomList";
	}
	
	
	//관리자계정 - 기초정보관리>강의실명리스트 수정(강의실명ID, 강의실명, 정원)
		public String ad_info_modiftyClassroom(HttpServletRequest request, HttpServletResponse response)
				throws SecurityException, IOException {
			// 아이디 체크
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("admin_id");

			if (id == null) {
				return "/pages/error";
			}
			
			
			request.setCharacterEncoding("UTF-8");
			System.out.println();
			System.out.println("ad_info_modiftyClassroom메소드 호출!");
			
			String classroom = request.getParameter("classroom");
			String classroom_id = request.getParameter("classroom_id");
			int max_num = Integer.parseInt(request.getParameter("max_num"));

			System.out.println(classroom_id);
			
							
			Admin ad = new Admin();
			ad.setClassroom_id(classroom_id);
			ad.setClassroom_name(classroom);
			ad.setMax_num(max_num);
			int result = dao.ad_info_modifyClassroom(ad);
			
			
			if (result == 0) {
				return "/error";
			}

			
		
			return "redirect:ad_info_classroomList";
		}
		
		
		////////////////////////////////////////////////////
	
	 // 3-7. 관리자계정 - 기초정보관리>교재명입력(교재명ID, 교재명, 출판사)
	   public String ad_info_addBook(HttpServletRequest request, HttpServletResponse response)
	         throws SecurityException, IOException {
	      // 아이디 체크
	      HttpSession session = request.getSession();
	      String id = (String) session.getAttribute("admin_id");

	      if (id == null) {
	         return "/pages/error";
	      }
	      request.setCharacterEncoding("UTF-8");

	      String url = "redirect:ad_info_bookList";
	      int result = 0;
	      
	      try{
	         String savePath = request.getServletContext().getRealPath("picture");
	         
	         java.io.File f = new java.io.File(savePath);
	         if (!f.exists()) {
	            f.mkdir();
	         }
	         
	         int sizeLimit = 1024 * 500;
	         
	         MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
	               new MyFileRenamePolicy());

	         // 업로드 파일에 대한 정보 확인
	         // 동적 생성된 파일이름
	         String book = multi.getParameter("book");
	         String publisher = multi.getParameter("publisher");         
	         String filesystemName = multi.getFilesystemName("image_detail");
	         String contentType = multi.getContentType("image_detail");
	         // 사용자가 요청한 파일이름
	         String originalFileName = multi.getOriginalFileName("image_detail");
	         
	         System.out.println("북 퍼블리셔 출ㄹ격");
	         System.out.println(book);
	         System.out.println(publisher);
	         System.out.println(filesystemName);
	         System.out.println(contentType);
	         System.out.println(originalFileName);
	         
	         System.out.println(savePath);
	         
	         if (!(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {

	            // 물리적으로 업로드된 파일에 대한 삭제 액션 추가
	            String deleteFilename = savePath + "\\" + filesystemName;
	            java.io.File temp = new java.io.File(deleteFilename);
	            temp.delete();
	            // System.out.println("deleteFilename : " + deleteFilename);

	            // 예외 발생시 -> 업로드된 파일에 대한 처리 부족
	            throw new Exception("JPG, PNG 이미지 파일이 아니거나 잘못된 이미지 입니다.");
	         }
	         
	         Admin ad = new Admin();

	         ad.setBook_name(book);
	         ad.setPublisher(publisher);
	         ad.setBook_img(filesystemName);

	         // db에 입력
	         result = dao.ad_info_addBook(ad);
	         
	         if (result == 0) {
	            String deleteFilename = savePath + "\\" + filesystemName;
	            java.io.File temp = new java.io.File(deleteFilename);
	            temp.delete();
	            throw new Exception("업로드된 파일의 정보를 데이터베이스에 등록하지 못했습니다.");
	         }
	         
	      } catch (Exception e) {
	         url = "/pages/error";
	         e.printStackTrace();
	      }      
	      
	      return url;
	   }

	   // 3-8. 관리자계정 - 기초정보관리>교재명리스트 출력(교재명ID, 교재명, 출판사)
	   // 교재명 메인
	   public String ad_info_bookList(HttpServletRequest request, HttpServletResponse response)
	         throws SecurityException, IOException {
	      // 아이디 체크
	      HttpSession session = request.getSession();
	      String id = (String) session.getAttribute("admin_id");

	      if (id == null) {
	         return "/pages/error";
	      }
	      request.setCharacterEncoding("UTF-8");

	      String skey = request.getParameter("skey");
	      String svalue = request.getParameter("svalue");

	      if (skey == null) {
	         skey = "all";
	         svalue = "";
	      }

	      List<Admin> list = dao.ad_info_bookList(skey, svalue);
	      //삭제버튼 비활성화 위해 리스트 비교
		     Ad_subClassDAO4 dao4 = new Ad_subClassDAO4();
		      List<Admin> checklist = dao4.ad_sj_subjectList("", "");
		      
		    
	      int length = list.size();

	      request.setAttribute("skey", skey);
	      request.setAttribute("svalue", svalue);
	      request.setAttribute("list", list);
	      request.setAttribute("length", length);
	      request.setAttribute("checklist", checklist);
	      return "/pages/admin_v/admin_basic_info_book";
	   }

	   //교재명 수정
	   public String ad_info_modifyBookName(HttpServletRequest request, HttpServletResponse response)
	         throws SecurityException, IOException {
	      System.out.println("ad_info_modifyBookName메소드 실행");
	      
	      // 아이디 체크
	      HttpSession session = request.getSession();
	      String id = (String) session.getAttribute("admin_id");

	      if (id == null) {
	         return "/pages/error";
	      }
	      request.setCharacterEncoding("UTF-8");

	      String url = "redirect:ad_info_bookList";
	      int result = 0;
	      
	      try{
	         String savePath = request.getServletContext().getRealPath("picture");
	         
	         java.io.File f = new java.io.File(savePath);
	         if (!f.exists()) {
	            f.mkdir();
	         }
	         
	         int sizeLimit = 1024 * 500;
	         
	         MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
	               new MyFileRenamePolicy());

	         // 업로드 파일에 대한 정보 확인
	         // 동적 생성된 파일이름
	         String book_id = multi.getParameter("id");
	         String book = multi.getParameter("book");
	         String publisher = multi.getParameter("publisher");         
	         String filesystemName = multi.getFilesystemName("image_detail");
	         String contentType = multi.getContentType("image_detail");
	         // 사용자가 요청한 파일이름
	         String originalFileName = multi.getOriginalFileName("image_detail");
	         
	         System.out.println("펄르리셔출발");
	         System.out.println(book_id);
	         System.out.println(book);
	         System.out.println(publisher);
	         System.out.println(filesystemName);
	         System.out.println(contentType);
	         System.out.println(originalFileName);
	         System.out.println(savePath);
	         
	         if (!(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {

	            // 물리적으로 업로드된 파일에 대한 삭제 액션 추가
	            String deleteFilename = savePath + "\\" + filesystemName;
	            java.io.File temp = new java.io.File(deleteFilename);
	            temp.delete();
	            // System.out.println("deleteFilename : " + deleteFilename);

	            // 예외 발생시 -> 업로드된 파일에 대한 처리 부족
	            throw new Exception("JPG, PNG 이미지 파일이 아니거나 잘못된 이미지 입니다.");
	         }
	         
	         Admin ad = new Admin();

	         ad.setBook_id(book_id);
	         ad.setBook_name(book);
	         ad.setPublisher(publisher);
	         ad.setBook_img(filesystemName);

	         // db에 입력
	         result = dao.ad_info_modifyBookName(ad);
	         
	         if (result == 0) {
	            String deleteFilename = savePath + "\\" + filesystemName;
	            java.io.File temp = new java.io.File(deleteFilename);
	            temp.delete();
	            throw new Exception("업로드된 파일의 정보를 데이터베이스에 등록하지 못했습니다.");
	         }
	         
	      } catch (Exception e) {
	         url = "/pages/error";
	         e.printStackTrace();
	      }      
	      
	      return url;
	   }
	   
	   
	   //교재명 삭제
	   
	   public String ad_info_deleteBookName(HttpServletRequest request, HttpServletResponse response)
		         throws SecurityException, IOException {
		      System.out.println("ad_info_deleteBookName메소드 실행");
		    		      
		      // 아이디 체크
		      HttpSession session = request.getSession();
		      String id = (String) session.getAttribute("admin_id");

		      if (id == null) {
		         return "/pages/error";
		      }
		      request.setCharacterEncoding("UTF-8");

		      String url = "redirect:ad_info_bookList";
		      int result = 0;
		      
		      try{
		         String savePath = request.getServletContext().getRealPath("picture");
		         
		         java.io.File f = new java.io.File(savePath);
		         if (!f.exists()) {
		            f.mkdir();
		         }
		         
		         int sizeLimit = 1024 * 500;
		         
		         MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
		               new MyFileRenamePolicy());

		         // 업로드 파일에 대한 정보 확인
		         // 동적 생성된 파일이름
		         String book_id = multi.getParameter("id");
		         String book = multi.getParameter("book");
		         String publisher = multi.getParameter("publisher");         
		         String filesystemName = multi.getFilesystemName("image_detail");
		         String contentType = multi.getContentType("image_detail");
		         // 사용자가 요청한 파일이름
		         String originalFileName = multi.getOriginalFileName("image_detail");
		         
		         System.out.println("펄르리셔출발");
		         System.out.println(book_id);
		         System.out.println(book);
		         System.out.println(publisher);
		         System.out.println(filesystemName);
		         System.out.println(contentType);
		         System.out.println(originalFileName);
		         System.out.println(savePath);
		         
		         Admin ad = new Admin();

		         ad.setBook_id(book_id);
		         ad.setBook_name(book);
		         ad.setPublisher(publisher);
		         ad.setBook_img(filesystemName);

		         // db에 입력
		         result = dao.ad_info_deleteBookName(ad);
		         
		         if (result == 0) {
		            String deleteFilename = savePath + "\\" + filesystemName;
		            java.io.File temp = new java.io.File(deleteFilename);
		            temp.delete();
		            throw new Exception("업로드된 파일의 정보를 데이터베이스에 등록하지 못했습니다.");
		         }
		         
		      } catch (Exception e) {
		         url = "/pages/error";
		         e.printStackTrace();
		      }      
		      
		   
		      
		      return url;
		   }
	   
	   
	   
	   public String admin_basic_info_book_picture_ajax(HttpServletRequest request, HttpServletResponse response)
	         throws SecurityException, IOException {
	      // 아이디 체크
	      HttpSession session = request.getSession();
	      String id = (String) session.getAttribute("admin_id");

	      if (id == null) {
	         return "/pages/error";
	      }
	      System.out.println("admin_basic_info_book_picture_ajax 메소드 호출");

	      request.setCharacterEncoding("UTF-8");
	      String book_id = request.getParameter("book_id");

	      System.out.println(book_id);

	      Ad_subClassDAO1 dao = new Ad_subClassDAO1();
	      List<Admin> list = dao.ad_info_bookList("book_id", book_id);

	      String result = "";

	      result = list.get(0).getBook_img();
	      if (result == null) {
	         result = "";
	      }
	      System.out.printf("파일명:%s%n", result);

	      request.setAttribute("result", result);

	      return "/pages/admin_v/admin_basic_info_book_picture_ajax";
	   }
	}
