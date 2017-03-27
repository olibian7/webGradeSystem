package com.academy.ssit.admin;

import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.academy.ssit.Instructor.Instructor;
import com.academy.ssit.admin.*;

public class AdminAction2 {
	// 3-2-9. 관리자계정 - 강사계정관리>강사정보등록(강사ID, 이름, 주민등록뒷번호, 전화번호), 강의가능과목 등록(강사ID,
	// 과목명ID)
	public String ad_ins_addInstructor(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		System.out.println("ad_ins_addInstructor메소드 실행");
		
		request.setCharacterEncoding("UTF-8");
		
//		String check = request.getParameter("check");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String ssn = request.getParameter("ssn");
		String[] check = request.getParameterValues("subjectCheck");
		
		System.out.println("temp배열값");
		for(int i=0; i<check.length; i++){
			System.out.println(check[i]);
		}
		
		Ad_subClassDAO2 dao = new Ad_subClassDAO2();
		
		Admin ad = new Admin();
		
		ad.setIn_name(name);
		ad.setIn_phone(phone);
		ad.setIn_social_num(ssn);
		
		dao.ad_ins_addInstructor(ad, check);
		
//		System.out.println("check걍 값");
//		System.out.println(check);
		
		return "redirect:ad_ins_instructorList"; // memberlist.it
	}

	// 3-2-10. 관리자계정 - 강사계정관리>강사정보출력(강사ID, 이름, 주민등록뒷번호, 전화번호, 강의가능과목)
	public String ad_ins_instructorList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}
		System.out.println("ad_ins_instructorList 메소드 실행");
		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // 전체 인원수
		String count = "0"; // 검색 결과 인원수

		// 과목명 리스트 불러오기
		/* Ad_subClassDAO1 dao1 = new Ad_subClassDAO1(); */

		// 강사정보 불러오기
		Ad_subClassDAO2 dao = new Ad_subClassDAO2();
		List<Admin> list = null;
		List<Admin> teach_list = null;

		// 검색 요청 데이터 수신
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		// 최초 요청시에는 검색 요청 데이터가 없는 상태이므로
		// 기본값을 채워야 한다.
		if (skey == null) {
			skey = "all";
			svalue = "";
		}

		list = dao.ad_ins_instructorList(skey, svalue); // 검색 및 전체 출력
		teach_list = dao.ad_ins_teachSubjectList(); // 강의중인 강사

		totalCount = String.valueOf(dao.ad_ins_totalCount()); // 전체 인원수
		System.out.println(totalCount);

		count = String.valueOf(list.size()); // 검색 결과 인원수
		
		/*for(Admin in: list){
			for(Admin t_in : teach_list){
				if(in.getInstructor_id() == t_in.getInstructor_id()){
					
				}
			}
		}*/

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("list", list);
		request.setAttribute("teach_list", teach_list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);

		// 결과 반환->페이지 이동 정보->JSP 페이지
		return "/pages/admin_v/admin_teacher_info"; // memberlist.jsp

	}

	// 3-2-11. 관리자계정 - 강사계정관리> 강사정보출력검색(강사이름, 과목명, 과목시작날짜&끝날짜, 과정명, 과정시작날짜&끝날짜,
	// 교재명, 강의실명, 강의진행여부 )
	public String ad_ins_instructorList_detail(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		// 결과 반환->페이지 이동 정보->서블릿 주소
		return "redirect:studentlist.it"; // memberlist.it
	}

	// 3-2-12. 관리자계정 - 강사계정관리> 강사계정관리> 강사정보삭제(강의가능과목에서도 삭제)
	public String ad_ins_deleteInstructor(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// 아이디 체크
				HttpSession session = request.getSession();
				String id = (String) session.getAttribute("admin_id");

				if (id == null) {
					return "/pages/error";
				}
				System.out.println("ad_ins_deleteInstructor 메소드 실행");
				request.setCharacterEncoding("UTF-8");
				
				String in_id = request.getParameter("in_id");
				Ad_subClassDAO2 dao = new Ad_subClassDAO2();
				String delete_ok = String.valueOf(dao.ad_ins_deleteInstructor(in_id));
				
				request.setAttribute("delete_key", delete_ok);

		return "redirect:ad_ins_instructorList";
	}

	// 3-2-12. 관리자계정 - 강사계정관리> 강사등록
	public String ad_ins_subjectAjax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ad_ins_subjectAjax 메소드 실행");

		// 아이디 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}

		request.setCharacterEncoding("UTF-8");

		/* String in_id = request.getParameter("in_id"); */

		Ad_subClassDAO2 dao = new Ad_subClassDAO2();
		List<Admin> list = dao.ad_ins_subjectList();
		/*
		 * List<Admin> sj_list = dao.ad_ins_instructorList("instructor_id",
		 * in_id);
		 */

		JSONObject subjects = new JSONObject();
		JSONArray array = new JSONArray();

		for (Admin s : list) {
			JSONObject subject = new JSONObject();
			subject.put("subject_id", s.getSubject_name_id());
			subject.put("subject_name", s.getSubject_name());
			array.add(subject);
		}
		/*
		 * JSONObject subject2 = new JSONObject(); subject2.put("sj_available",
		 * sj_list.get(0).getSj_available()); array.add(subject2);
		 */

		/* ad.setSj_available(rs.getString("sj_available")); */

		/*
		 * for(Admin s : sj_list){ JSONObject subject = new JSONObject();
		 * subject.put("sj_available", s.getSj_available()); array.add(subject);
		 * }
		 */

		subjects.put("subject", array);

		request.setAttribute("result", subjects.toJSONString());

		return "/pages/admin_v/admin_teacher_info_subject_ajax";
	}
	// 3-2-13. 관리자계정 - 강사계정관리>강사정보수정(강사ID, 이름, 주민등록뒷번호, 전화번호)
	// 과목명ID)
	public String ad_ins_modifyInstructor(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		System.out.println("ad_ins_modifyInstructor메소드 실행");
		
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String[] check = request.getParameterValues("subjectCheck");
		
		System.out.println("temp배열값");
		for(int i=0; i<check.length; i++){
			System.out.println(check[i]);
		}
		
		Ad_subClassDAO2 dao = new Ad_subClassDAO2();
		
		Admin ad = new Admin();
		
		ad.setInstructor_id(id);
		ad.setIn_name(name);
		ad.setIn_phone(phone);
		
		dao.ad_ins_modifyInstructor(ad, check);
		
		return "redirect:ad_ins_instructorList"; // memberlist.it
	}
}
