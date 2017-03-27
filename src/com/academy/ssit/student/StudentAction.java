package com.academy.ssit.student;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.academy.ssit.MyFileRenamePolicy;
import com.academy.ssit.admin.Ad_subClassDAO1;
import com.academy.ssit.admin.Admin;
import com.oreilly.servlet.MultipartRequest;

public class StudentAction {

	// 1-1. 수강생계정 - 수강생정보출력(아이디, 이름, 민번, 전번, 등록일)
	public String st_studentInfo(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");
		/* String id = (String) request.getAttribute("id"); */
		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("user_id");

		if (id == null) {
			return "/pages/error";
		}

		StudentDAO dao = new StudentDAO();
		List<Student> list = dao.st_studentInfo("", id);

		request.setAttribute("list", list);
		return "student_v/student_info";
	}

	// 1-2. 수강생계정 - 수강생 정보 수정(이름, 민번, 전번 수정가능)
	public String st_modifyStudentInfo(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("user_id");

		String st_id = request.getParameter("st_id");
		String st_name = request.getParameter("st_name");
		String st_phone = request.getParameter("st_phone");

		if (id == null) {
			return "/pages/error";
		}

		Student s = new Student();
		s.setStudent_id(st_id);
		s.setName(st_name);
		s.setPhone(st_phone);

		StudentDAO dao = new StudentDAO();
		dao.st_studentModify(s);

		return "redirect:st_studentInfo";
	}

	// 1-3. 수강생계정 - 수강생이 수강하는 과정조회(과정ID, 과정명, 과정기간)
	public String st_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("user_id");

		if (id == null) {
			return "/pages/error";
		}

		String course_id = (String) request.getParameter("course_id");
		String course_name = (String) request.getParameter("course_name");
		String cr_start_date = (String) request.getParameter("cr_start_date");
		String cr_end_date = (String) request.getParameter("cr_end_date");

		request.setAttribute("course_id", course_id);
		request.setAttribute("course_name", course_name);
		request.setAttribute("cr_start_date", cr_start_date);
		request.setAttribute("cr_end_date", cr_end_date);

		StudentDAO dao = new StudentDAO();
		List<Student> list = dao.st_gradeList(course_id, id);

		request.setAttribute("list", list);

		return "student_v/student_course_detail";
	}

	// 1-4. 수강생계정 - 학생성적 출력(과목ID 과목명 과목기간 교재명 강사명 출결(점수/배점) 필기(점수/배점) 실기(점수/배점)
	// 시험날짜 시험문제)
	public String st_gradeList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");
		/* String id = (String) request.getAttribute("id"); */
		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("user_id");
		if (id == null) {
			return "/pages/error";
		}

		StudentDAO dao = new StudentDAO();
		List<Student> list = dao.st_courseList("", id);

		request.setAttribute("list", list);

		return "student_v/student_course";
	}

	public String student_picture_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("user_id");

		if (id == null) {
			return "/pages/error";
		}
		System.out.println("admin_basic_info_book_picture_ajax 메소드 호출");

		request.setCharacterEncoding("UTF-8");
		String book_id = request.getParameter("book_id");

		System.out.println(book_id);

		StudentDAO dao = new StudentDAO();
		List<Student> list = dao.st_bookList("book_id", book_id);

		String result = "";

		result = list.get(0).getBook_img();
		if (result == null) {
			result = "";
		}
		System.out.printf("파일명:%s%n", result);

		request.setAttribute("result", result);

		return "/pages/admin_v/admin_basic_info_book_picture_ajax";
	}

	public String doDownload(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
	
		String url="hold";

		int BUFFER_SIZE = 4096;

		String exam = request.getParameter("exam");

		// get absolute path of the application
		ServletContext context = request.getServletContext();
		String appPath = context.getRealPath("\\test_files");
		System.out.println("appPath = " + appPath);

		System.out.println(exam);
		
		// construct the complete absolute path of the file
		String fullPath = appPath + "\\" + exam;
		File downloadFile = new File(fullPath);
		
		System.out.println(fullPath);
		
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		System.out.println(downloadFile.getName());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		System.out.println(headerKey);
		System.out.println(headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		System.out.println(url);
		
		inputStream.close();
		outStream.close();

		System.out.println(url);
		
		return url;
		
	}

}
