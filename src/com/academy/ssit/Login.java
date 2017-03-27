package com.academy.ssit;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.academy.ssit.Instructor.InstructorAction;
import com.academy.ssit.admin.AdminAction1;
import com.academy.ssit.student.StudentAction;

public class Login {

	public Login() {
		System.out.println("여기는 login");
	}
	public String loginCheck(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		/* request.setCharacterEncoding("UTF-8"); */
		
		LoginDAO dao = new LoginDAO();

		String result = "WEB-INF/source/error";

		String radio = request.getParameter("reg_gender");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		if (radio == null) {
			/*
			 * response.sendRedirect(
			 * "/grade_Management_System_0120/pages/login-form.jsp");
			 */
			result = "login-form";
		} else if (radio.equals("0")) { // 학생
			
			if (dao.loginStudent(id, pw) != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user_id", id);

				StudentAction sta = new StudentAction();
				result = sta.st_studentInfo(request, response);

			} else {
				System.out.println("학생 로그인 실패");
				result = "loginfail";
			}

		} else if (radio.equals("1")) { // 강사
			if (dao.loginInstructor(id, pw) != null) {
				HttpSession session = request.getSession();
				session.setAttribute("in_id", id);

				InstructorAction isa = new InstructorAction();
				result = isa.ins_scheduleList(request, response);

			} else {
				System.out.println("강사 로그인 실패");
				result = "loginfail";
			}
		} else if (radio.equals("2")) { // 관리자
			if (dao.loginAdmin(id, pw) != null) {
				HttpSession session = request.getSession();
				session.setAttribute("admin_id", id);

				AdminAction1 aa = new AdminAction1();
				result = aa.ad_info_courseNameList(request, response);

			} else {
				System.out.println("관리자 로그인 실패");
				result = "loginfail";
			}
		}
		return result;
	}
}
