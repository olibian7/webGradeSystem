package com.academy.ssit.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.academy.ssit.Instructor.Instructor;
import com.academy.ssit.student.Student;

public class AdminAction6 {
	// 3-6-32. 관리자계정 - 성적조회>과목기준조회>과정 목록 출력(과정ID, 과정명, 과정기간)
	public String ad_gr_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // 전체 인원수
		String count = "0"; // 검색 결과 인원수

		Ad_subClassDAO6 dao = new Ad_subClassDAO6();
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

		list = dao.ad_gr_courseList(skey, svalue);
		totalCount = String.valueOf(list.size());

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);

		return "/pages/admin_v/admin_grade_course";
	}

	// 3-6-33. 관리자계정 - 성적조회>과목기준조회>특정 과정 정보 출력(과목ID, 과목명, 과목기간, 강사명, 성적입력여부,
	// 시험지등록여부, 강의실, 교재명, 과정명, 과정기간)
	public String ad_gr_courseSubjectList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // 전체 인원수

		// courseid 가져오기
		String course_id = request.getParameter("course_id");

		// 검색 요청 데이터 수신
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		System.out.printf("%s,%s, %s%n", course_id, skey, svalue);

		if (skey == null) {
			skey = "all";
			svalue = "";
		}
		
		Ad_subClassDAO6 dao = new Ad_subClassDAO6();
		List<Admin> course_list = dao.ad_gr_courseList(course_id);

		List<Admin> subject_list = dao.ad_gr_courseSubjectList(course_id, skey, svalue);

		totalCount = String.valueOf(subject_list.size());
		System.out.println(totalCount);
		
		
		// --> 성적 입력 여부 확인 시작
		
		List<String> gradeCheck = new ArrayList<>();

		for (int i = 0; i < subject_list.size(); ++i) {

			// 선택된 과목에 대한 수강생 목록 출력(list2)
			List<Admin> list2 = dao.ad_gr_subStudentlist(subject_list.get(i).getSubject_id(), skey, svalue);
			System.out.println("선택 과목에 대한 수강생 리스트 사이즈:" + list2.size());
			String countSt = String.valueOf(list2.size());

			// 2. list의 사이즈
			int subSumList = list2.size();

			// 1. 학생의 성적의 총합이 0인 학생의 수
			int checkPoint = 0;

			// 성적 등록 여부 문자열
			String gradeCheckMessage = null;

			for (int a = 0; a < subSumList; ++a) {

				// 한 학생의 성적의 총합
				int sumList = list2.get(a).getGr_attend() + list2.get(a).getGr_writing()
						+ list2.get(a).getGr_practice();

				if (sumList == 0) {
					++checkPoint;
				}
			}
			if (checkPoint == 0) {
				gradeCheckMessage = "등록완료";
			} else if (checkPoint > 0 && checkPoint < subSumList) {
				gradeCheckMessage = "등록 중";
			} else {
				gradeCheckMessage = "미등록";
			}			
			System.out.println("확인: " + gradeCheckMessage);

			// 성적 입력 여부 확인 메세지를 해당 리스트에 입력
			gradeCheck.add(gradeCheckMessage);
		}
		
		request.setAttribute("gradeCheck", gradeCheck);
		
		// 성적 입력 여부 확인 종료 --> 
		
		

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("course_id", course_id);
		request.setAttribute("cs_list", course_list);
		request.setAttribute("sj_list", subject_list);
		request.setAttribute("sj_list_size", subject_list.size());
		request.setAttribute("totalCount", totalCount);

		return "/pages/admin_v/admin_grade_course_detail";
	}

	// 3-6-34. 관리자계정 - 성적조회>과목기준조회>특정 과목을 듣는 수강생 정보 출력(학생ID, 이름, 전화번호, 출결, 필기,
	// 실기)
	public String ad_gr_subStudentlist(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return "redirect:";
	}

	// 3-35. 관리자계정 - 성적조회>학생기준조회>전체 학생 리스트 출력(학생ID, 이름, 주민번호, 전화번호, 등록일자)
	public String ad_gr_allStudentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // 전체 인원수
		// 검색 요청 데이터 수신
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		// String course_id = request.getParameter("course_id");

		if (skey == null) {
			skey = "all";
			svalue = "";
		}

		Ad_subClassDAO6 dao = new Ad_subClassDAO6();
		List<Admin> list = dao.ad_gr_allStudentList(skey, svalue);
		totalCount = String.valueOf(list.size());

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);

		return "/pages/admin_v/admin_grade_student";
	}

	// 3-36. 관리자계정 - 성적조회>학생기준조회>현재날짜 기준 성적줘야하는 학생의 과정 리스트 출력(과정ID, 과정명, 기간)
	// 위에 겹침
	public String ad_gr_studentCourseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // 전체 인원수
		String count = "0"; // 검색 결과 인원수
		// 검색 요청 데이터 수신
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		String st_id = request.getParameter("sid");

		System.out.println("stid?" + st_id);

		Ad_subClassDAO6 dao = new Ad_subClassDAO6();
		List<Admin> stList = dao.ad_gr_allStudentList("student_id", st_id);
		List<Student> crList = dao.ad_gr_studentCourseList("", st_id);
		int totalcount = crList.size();
		
		request.setAttribute("stList", stList);
		request.setAttribute("crList", crList);
		request.setAttribute("st_id", st_id);
		request.setAttribute("totalcount", totalcount);
		
		return "/pages/admin_v/admin_grade_student_detail";
	}

	// 3-6-37. 관리자계정 - 성적조회>학생기준조회>특정 수강생이 수강한 과정 안에 있는 과목 정보 + 성적 출력(학생ID, 이름,
	// 전화번호, 과목ID, 과목명, 과목기간, 출결, 필기, 실기)
	public String ad_gr_studentGrade_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // 전체 인원수
		String count = "0"; // 검색 결과 인원수
		// 검색 요청 데이터 수신
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		
		String st_id = request.getParameter("st_id");
		String cr_id = request.getParameter("cr_id");
		System.out.println("st_id" + st_id);
		System.out.println("cr_id" + cr_id);
		Ad_subClassDAO6 dao = new Ad_subClassDAO6();
		List<Admin> gList = dao.ad_gr_studentGrade(st_id, cr_id);

		JSONObject subjects = new JSONObject();
		JSONArray array = new JSONArray();

		for (Admin g : gList) {
			JSONObject subject = new JSONObject();

			subject.put("sj_id", g.getSubject_id());
			subject.put("sj_name", g.getSubject_name());
			subject.put("sj_start_data", g.getSj_start_date());
			subject.put("sj_end_date", g.getSj_end_date());
			subject.put("in_name", g.getIn_name());
			subject.put("test_paper", g.getTest_paper());
			subject.put("test_paper_name", g.getTest_paper_name());
			subject.put("gr_attend", g.getGr_attend());
			subject.put("sc_attend", g.getSc_attend());
			subject.put("gr_writing", g.getGr_writing());
			subject.put("sc_writing", g.getSc_writing());
			subject.put("gr_practice", g.getGr_practice());
			subject.put("sc_practice", g.getSc_practice());

			array.add(subject);
		}

		subjects.put("subject", array);

		request.setAttribute("result", subjects.toJSONString());

		return "/pages/admin_v/admin_grade_student_detail_ajax";
	}

	public String admin_grade_course_detail_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // 전체 인원수
		String count = "0"; // 검색 결과 인원수
		// 검색 요청 데이터 수신
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		if (skey == null) {
			skey = "all";
			svalue = "";
		}
		;

		String subject_id = request.getParameter("subject_id");
		System.out.println("subject_id?" + subject_id);

		Ad_subClassDAO6 dao = new Ad_subClassDAO6();
		List<Admin> list = dao.ad_gr_subStudentlist(subject_id,skey,svalue);

		totalCount = String.valueOf(list.size());
		
		JSONObject students = new JSONObject();
		// 수강생 정보 저장용 배열
		JSONArray array1 = new JSONArray();
		// 검색 키, 밸류 저장용 배열
		JSONArray array2 = new JSONArray();

		for (Admin s : list) {

			JSONObject student = new JSONObject();

			student.put("st_id", s.getStudent_id());
			student.put("name", s.getSt_name());
			student.put("phone", s.getSt_phone());
			student.put("gr_attend", s.getGr_attend());
			student.put("gr_writing", s.getGr_writing());
			student.put("gr_practice", s.getGr_practice());
			student.put("totalCount",totalCount);

			array1.add(student);
		}

		// 검색 키, 밸류 저장 객체
		JSONObject keyvalues = new JSONObject();
		keyvalues.put("skey", skey);
		keyvalues.put("svalue", svalue);
		array2.add(keyvalues);

		students.put("student", array1);
		students.put("keyvalue", array2);

		request.setAttribute("result", students.toJSONString());

		return "/pages/admin_v/admin_grade_course_detail_ajax";
	}

}
