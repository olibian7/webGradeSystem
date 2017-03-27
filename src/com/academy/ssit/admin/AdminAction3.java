package com.academy.ssit.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.*;

public class AdminAction3 {

	// 3-3-17. 관리자계정 - 개설과정관리>과정 정보 출력(과정ID, 과정명, 과정시작&끝날짜, 강의실명, 등록한 수강생수, 정원,
	// 등록된 과목수)
	public String ad_cr_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}
		request.setCharacterEncoding("UTF-8");

		// 검색 요청 처리 과정 추가
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		// 데이터베이스 액션 처리 과정
		Ad_subClassDAO3 dao = new Ad_subClassDAO3();
		List<Admin> list = null;
		String totalCount = "0";
		String count = "0";

		if (skey == null) {
			skey = "all";
			svalue = "";
		}
		list = dao.ad_cr_courseList(skey, svalue);

		totalCount = String.valueOf(dao.totalCount());
		count = String.valueOf(list.size());

		// JSP 페이지에 넘길 데이터 준비
		request.setAttribute("courselist", list);
		request.setAttribute("count", count);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);

		/////////// *과정등록 모달창*////////////////
		// 1.과정명리스트 출력(과정명ID)
		List<Admin> coursenamelist = null;
		coursenamelist = dao.ad_cr_courseNameList("");

		// JSP 페이지에 넘길 데이터 준비
		request.setAttribute("coursenamelist", coursenamelist);

		// 2.강의실 출력(과정명ID)
		// 시작날짜, 끝날짜
		List<Admin> classroomlist = null;

		classroomlist = dao.ad_cr_ClassroomList();
		String cl_count = String.valueOf(classroomlist.size());

		// JSP 페이지에 넘길 데이터 준비
		request.setAttribute("classroomlist", classroomlist);
		request.setAttribute("cl_count", cl_count);

		return "/pages/admin_v/admin_course";
	}

	// 3-3-15. 관리자계정 - 개설과정관리>과정등록>과정ID 부여(강의실 정보 받은 후에 진행)
	public String ad_cr_addCourseId(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		return "redirect:ad_cr_courseList";
	}

	// 3-3-16. 관리자계정 - 개설과정관리>과정등록>신규과정등록
	public String ad_cr_addCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ad_cr_addCourse 메소드 호출");

		request.setCharacterEncoding("UTF-8");

		// 과정명ID, 과정시작날짜, 끝날짜, 강의실 정보 받아오기
		String course_name_id = request.getParameter("course_name_id");
		String cr_start_date = request.getParameter("cr_start_date");
		String cr_end_date = request.getParameter("cr_end_date");
		String classroom_id = request.getParameter("classroom_id");

		// 데이터베이스 액션 처리 과정
		Ad_subClassDAO3 dao = new Ad_subClassDAO3();
		String course_id = dao.ad_cr_addCourseId();

		Admin ad = new Admin();

		ad.setCourse_id(course_id);
		ad.setCourse_name_id(course_name_id);
		ad.setCr_start_date(cr_start_date);
		ad.setCr_end_date(cr_end_date);
		ad.setClassroom_id(classroom_id);

		System.out.printf("%s,%s,%s,%s,%s%n", course_id, course_name_id, cr_start_date, cr_end_date, classroom_id);

		// 과정 등록
		int result = dao.ad_cr_addCourse(ad);
		System.out.printf("과정등록 성공여부:%s%n", result);

		// String url = "";
		//
		// if(result==1){
		// url="redirect:ad_cr_courseList.it";
		// }else{
		// url="/pages/error.jsp";
		// }

		return "redirect:ad_cr_courseList";
	}

	// 3-3-21. 관리자계정 - 개설과정관리>과정수정
	public String ad_cr_modifyCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_cr_modifyCourse 메소드 호출");
		request.setCharacterEncoding("UTF-8");

		// 과정명ID, 과정시작날짜, 끝날짜, 강의실 정보 받아오기
		String course_id = request.getParameter("course_id");
		String course_name = request.getParameter("course_name");
		String cr_start_date = request.getParameter("cr_start_date");
		String cr_end_date = request.getParameter("cr_end_date");
		String classroom_id = request.getParameter("classroom_id");
		
		//DB에서 수정 액션
		Admin ad = new Admin();
		ad.setCourse_id(course_id);
		ad.setCourse_name(course_name);
		ad.setCr_start_date(cr_start_date);
		ad.setCr_end_date(cr_end_date);
		ad.setClassroom_id(classroom_id);
		
		Ad_subClassDAO3 dao = new Ad_subClassDAO3();
		
		int result = dao.ad_cr_modifyCourse(ad);		

		if(result==0){
			//에러페이지

		}else{
			//수정 완료
		}
		
		return "redirect:ad_cr_courseList";
	}

	
	// 3-3-22. 관리자계정 - 개설과정관리>과정삭제
	public String ad_cr_deleteCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_cr_deleteCourse 메소드 호출");
		request.setCharacterEncoding("UTF-8");

		// 과정명ID, 과정시작날짜, 끝날짜, 강의실 정보 받아오기
		String course_id = request.getParameter("course_id");
		
		//DB에서 삭제 액션		
		Ad_subClassDAO3 dao = new Ad_subClassDAO3();
		
		int result = dao.ad_cr_deleteCourse(course_id);

		if(result==0){
			//에러페이지
			//System.out.println("과정 삭제가 되지 않았습니다.");
		}else{
			//System.out.println("과정 삭제완료.");
		}
		
		return "redirect:ad_cr_courseList";
	}
	
	// 3-3-18. 관리자계정 - 모달))개설과정관리>과정 상세>과목 정보 출력(과목ID, 과목명, 과목시작&끝날짜, 교재명, 강사명,
	// 정원,
	// 등록된 과목수)
	public String admin_course_ajaxReceive(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}
		String keyword = request.getParameter("keyword");

		System.out.println(keyword);

		Ad_subClassDAO3 dao = new Ad_subClassDAO3();
		List<Admin> list = dao.ad_cr_subjectList(keyword);

		JSONObject subjects = new JSONObject();

		JSONArray array = new JSONArray();

		for (int i = 0; i < list.size(); i++) {

			JSONObject temp = new JSONObject();

			temp.put("subject_id", list.get(i).getSubject_id());
			temp.put("subject_name", list.get(i).getSubject_name());
			temp.put("sj_start_date", list.get(i).getSj_start_date());
			temp.put("sj_end_date", list.get(i).getSj_end_date());
			temp.put("book_name", list.get(i).getBook_name());
			temp.put("in_name", list.get(i).getIn_name());

			array.add(temp);
		}

		subjects.put("subject", array);
		request.setAttribute("result", subjects.toJSONString());

		return "/pages/admin_v/admin_course_ajaxReceive";
	}

	// 개설과정관리 - 모달> 수강생정보리스트 출력
	// 3-3-19. 관리자계정 - 개설과정관리>과정 상세>수강생 정보 출력(수강생ID, 수강생이름, 전화번호, 등록일자, 중도탈락날짜)
	public String admin_course_ajaxReceive2(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}
		String keyword = request.getParameter("keyword");

		System.out.println(keyword);

		Ad_subClassDAO3 dao = new Ad_subClassDAO3();
		List<Admin> list = dao.ad_cr_studentList(keyword);

		JSONObject students = new JSONObject();

		JSONArray array = new JSONArray();

		for (int i = 0; i < list.size(); i++) {

			JSONObject temp = new JSONObject();

			temp.put("student_id", list.get(i).getStudent_id());
			temp.put("st_name", list.get(i).getSt_name());
			temp.put("st_social_num", list.get(i).getSt_social_num());
			temp.put("st_phone", list.get(i).getSt_phone());
			temp.put("st_registration_date", list.get(i).getSt_registration_date());
			temp.put("fail_date", (list.get(i).getFail_date() == null) ? "-" : list.get(i).getFail_date());

			array.add(temp);
		}

		students.put("student", array);
		request.setAttribute("result", students.toJSONString());

		return "/pages/admin_v/admin_course_ajaxReceive2";
	}

	// 3-3-20. 관리자계정 - 개설과정관리> 오늘날짜 출력하여 과정개설날짜(시작, 끝) 검증
	public String ad_cr_dateCheck(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		return "redirect:";
	}

	///////////////////////////////// 사용안함//////////////////////////////////

	// 3-3-13. 관리자계정 -모달))개설과정관리>과정명리스트 출력(과정명ID, 과정명)
	// public String ad_cr_courseNameList(HttpServletRequest request,
	// HttpServletResponse response)
	// throws SecurityException, IOException {
	// return "/pages/admin_v/admin_course";
	// }

}