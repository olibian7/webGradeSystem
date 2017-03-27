<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	request.setCharacterEncoding("UTF-8");

	String radio = request.getParameter("reg_gender");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	if(radio == null){	
		response.sendRedirect("loginform.it");
	}else if(radio.equals("0")){	// 학생
		/* response.sendRedirect("/grade_Management_System_0120/pages/student_v/student_info.jsp"); */
	} else if(radio.equals("1")){	// 강사
		/* response.sendRedirect("/grade_Management_System_0120/pages/teacher_v/teacher_scheduleList.jsp"); */
	} else if(radio.equals("2")) {	// 관리자
		/* response.sendRedirect("/grade_Management_System_0120/pages/admin_v/admin_basic_info.jsp"); */
	}
	
%>
