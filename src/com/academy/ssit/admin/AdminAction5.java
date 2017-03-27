package com.academy.ssit.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.*;

public class AdminAction5 {
	// 3-5-25. 관리자계정 - 학생관리>학생정보등록(학생ID, 이름, 주민번호, 핸드폰)
	public String ad_st_addStudent(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// ID 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";

		}

		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_st_addStudent 메소드 호출");

		String name = request.getParameter("name");
		String ssn = request.getParameter("ssn");
		String phone = request.getParameter("phone");

		System.out.println(name + ssn + phone);

		Admin ad = new Admin();
		ad.setSt_name(name);
		ad.setSt_social_num(ssn);
		ad.setSt_phone(phone);

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();

		dao.ad_st_addStudent(ad);

		return "redirect:ad_st_allStudentList";
	}

	// 3-5-26. 관리자계정 - 학생관리>전체검색>수강생 전체리스트 출력(학생ID, 이름, 주민번호, 핸드폰, 등록일, 수강신청횟수)
	public String ad_st_allStudentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // 전체 인원수
		String count = "0"; // 검색 결과 인원수

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();
		List<Admin> list = null;

		// 검색 요청 데이터 수신
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		// 최초 요청시에는 검색 요청 데이터가 없는 상태이므로
		// 기본값을 채워야 한다.
		if (skey == null) {
			skey = "all";
		}

		System.out.println(skey);
		System.out.println(svalue);

		list = dao.ad_st_allStudentList(skey, svalue); // 검색 및 전체 출력

		System.out.println("여기");

		totalCount = String.valueOf(dao.ad_ins_totalCount()); // 전체 인원수
		count = String.valueOf(list.size()); // 검색 결과 인원수

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("allStudentList", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);

		// 결과 반환->페이지 이동 정보->JSP 페이지
		return "/pages/admin_v/admin_student"; // memberlist.jsp

	}

	// 3-5-27. 관리자계정 - 학생관리>학생 과정 등록 시 과정 목록 출력(과정Id, 과정명, 과정기간, 강의실, 수용인원,
	// 등록과목수)
	public String ad_st_studentCourseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_st_studentCourseList메소드 실행");

		String student_id = request.getParameter("student_id");

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();

		// // 난난
		// int result = dao.ad_st_registerCourse(course_id, student_id);

		List<Admin> list = dao.ad_st_allStudentList("student_id", student_id);
		List<Admin> total_list = dao.ad_st_studentCourseList();
		List<Admin> check_list = dao.ad_st_studentInfo("student_id", student_id);
		int check_len = check_list.size();
		int total_len = total_list.size();

		String totalcount = String.valueOf(total_list.size());

		// 검색 요청 데이터 수신
		// list = dao.ad_st_courseStudentList("student_id", student_id); // 검색 및
		// 전체 출력

		request.setAttribute("list", list);
		request.setAttribute("total_list", total_list);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("check_list", check_list);

		request.setAttribute("check_len", check_len);
		request.setAttribute("total_len", total_len);

		// request.setAttribute("result", result);

		// 결과 반환->페이지 이동 정보->JSP 페이지
		return "/pages/admin_v/admin_student_addcourse"; // memberlist.jsp
	}

	// 3-5-33. 관리자계정 - 학생관리>학생 과정 등록
	public String ad_st_add_studentCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_st_add_studentCourse메소드 실행");

		String student_id = request.getParameter("student_id");
		String course_id = request.getParameter("course_id");

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();

		// 등록 결과
		int result = dao.ad_st_add_studentCourse(course_id, student_id);

		if (result == 0) {
			System.out.println("학생 과정등록 실패");
		} else {
			System.out.println("학생 과정등록 성공");
		}

		// 결과 반환->페이지 이동 정보->JSP 페이지
		return String.format("get:ad_st_studentCourseList.it?student_id=%s",student_id);
	}

	// 3-5-34. 관리자계정 - 학생관리>학생 과정 등록 취소
	public String ad_st_delete_studentCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_st_delete_studentCourse메소드 실행");

		String student_id = request.getParameter("student_id");
		String course_id = request.getParameter("course_id");

		System.out.printf("%s,%s%n",student_id,course_id);
		
		Ad_subClassDAO5 dao = new Ad_subClassDAO5();

		// 삭제 결과
		int result = dao.ad_st_delete_studentCourse(course_id, student_id);

		if (result == 0) {
			System.out.println("학생 과정등록 취소 실패");
		} else {
			System.out.println("학생 과정등록 취소 성공");
		}

		// 결과 반환->페이지 이동 정보->JSP 페이지
		return String.format("get:ad_st_studentCourseList.it?student_id=%s", student_id);
	}

	// 3-5-29. 관리자계정 - 학생관리>수강생 개인정보 출력(과정명, 과정기간, 강의실명, 중도탈락일)
	public String ad_st_studentInfo(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		return "redirect:";
	}

	// 3-5-30. 관리자계정 - 학생관리>과정검색>관리자 과정 전체 출력(과정ID, 과정명, 과정기간, 강의실)
	public String ad_st_allCourseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // 전체 인원수
		String count = "0"; // 검색 결과 인원수

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();
		List<Admin> list = null;

		// 검색 요청 데이터 수신
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		// 최초 요청시에는 검색 요청 데이터가 없는 상태이므로
		// 기본값을 채워야 한다.
		if (skey == null) {
			skey = "all";
			svalue = "";
		}

		list = dao.ad_st_allCourseList(skey, svalue); // 검색 및 전체 출력

		totalCount = String.valueOf(dao.ad_ins_totalCount()); // 전체 인원수
		count = String.valueOf(list.size()); // 검색 결과 인원수

		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);
		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		// ad_st_courseStudentList(request, response);

		// ad_st_allStudentList(request,response);
		// 결과 반환->페이지 이동 정보->JSP 페이지
		return "/pages/admin_v/admin_student_coursesearch"; // memberlist.jsp
	}

	// 3-5-31. (모달)관리자계정 - 학생관리>과정검색>수강생 이름 검색(학생ID, 이름, 주민등록번호, 전화번호, 등록일자,
	// 중도탈락날짜)
	public String ad_st_courseStudentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // 전체 인원수
		String count = "0"; // 검색 결과 인원수

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();
		List<Admin> list = null;

		// 검색 요청 데이터 수신
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		String course_id = request.getParameter("course_id");

		// 최초 요청시에는 검색 요청 데이터가 없는 상태이므로
		// 기본값을 채워야 한다.
		if (skey == null) {
			skey = "all";
			svalue = "";
		}

		list = dao.ad_st_courseStudentList("course_id", course_id); // 검색 및 전체
																	// 출력

		totalCount = String.valueOf(dao.ad_ins_totalCount()); // 전체 인원수
		count = String.valueOf(list.size()); // 검색 결과 인원수
		System.out.println(count);
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);

		// 결과 반환->페이지 이동 정보->JSP 페이지
		return "/pages/admin_v/admin_student_coursesearch"; // memberlist.jsp

	}

	// 3-5-32. AJAX(모달)관리자계정 - 학생관리>과정검색>수강생 이름 검색(학생ID, 이름, 주민등록번호, 전화번호, 등록일자,
	// 중도탈락날짜)
public String admin_student_coursesearch_ajax(HttpServletRequest request, HttpServletResponse response)
		throws SecurityException, IOException {
	System.out.println("admin_student_coursesearch_ajax 메소드 실행");

	request.setCharacterEncoding("UTF-8");

	String course_id = request.getParameter("course_id");
	String skey = request.getParameter("skey");
	String svalue = request.getParameter("svalue");

	if (skey == null) {
		skey = "all";
		svalue = "";
	}

	Ad_subClassDAO5 dao = new Ad_subClassDAO5();
	List<Admin> list = dao.ad_st_courseStudentList("course", course_id, skey, svalue);

	JSONObject students = new JSONObject();
	
	// 수강생 정보 저장용 배열
	JSONArray array = new JSONArray();


	// 수강생 정보 저장용 배열
	for (int i = 0; i < list.size(); i++) {

		JSONObject student = new JSONObject();

		student.put("student_id", list.get(i).getStudent_id());
		student.put("st_name", list.get(i).getSt_name());
		student.put("st_social_num", list.get(i).getSt_social_num());
		student.put("st_phone", list.get(i).getSt_phone());
		student.put("st_registration_date", list.get(i).getSt_registration_date());
		student.put("fail_date", (list.get(i).getFail_date() == null) ? "-" : list.get(i).getFail_date());
		
		// 배열에 저장
		array.add(student);
	}

	students.put("student", array);

	request.setAttribute("result", students.toJSONString());

	return "/pages/admin_v/admin_student_coursesearch_ajax";
}

	// 3-5-33.
	public String admin_student_json(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		String student_id = request.getParameter("keyword");
		// String course = request.getParameter("course");

		// String student_id_sub = student_id.substring(4,9);

		System.out.println("json.it에서 실행한 결과");
		System.out.println(student_id);

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();
		List<Admin> list = dao.ad_st_studentInfo("student_id", student_id);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getCourse_id());
		}

		JSONObject students = new JSONObject();
		JSONArray array = new JSONArray();

		for (int i = 0; i < list.size(); i++) {

			JSONObject student = new JSONObject();

			student.put("course_id", list.get(i).getCourse_id());
			student.put("course_name", list.get(i).getCourse_name());
			student.put("cr_start_date", list.get(i).getCr_start_date());
			student.put("cr_end_date", list.get(i).getCr_end_date());
			student.put("classroom_name", list.get(i).getClassroom_name());
			student.put("fail_date", (list.get(i).getFail_date() == null) ? "-" : list.get(i).getFail_date());

			array.add(student);
		}

		students.put("myinfo", array);

		request.setAttribute("result", students.toJSONString());

		return "/pages/admin_v/admin_student_json";
	}

}
