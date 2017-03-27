package com.academy.ssit.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.*;

public class AdminAction3 {

	// 3-3-17. �����ڰ��� - ������������>���� ���� ���(����ID, ������, ��������&����¥, ���ǽǸ�, ����� ��������, ����,
	// ��ϵ� �����)
	public String ad_cr_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// ���̵� üũ
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}
		request.setCharacterEncoding("UTF-8");

		// �˻� ��û ó�� ���� �߰�
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		// �����ͺ��̽� �׼� ó�� ����
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

		// JSP �������� �ѱ� ������ �غ�
		request.setAttribute("courselist", list);
		request.setAttribute("count", count);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);

		/////////// *������� ���â*////////////////
		// 1.��������Ʈ ���(������ID)
		List<Admin> coursenamelist = null;
		coursenamelist = dao.ad_cr_courseNameList("");

		// JSP �������� �ѱ� ������ �غ�
		request.setAttribute("coursenamelist", coursenamelist);

		// 2.���ǽ� ���(������ID)
		// ���۳�¥, ����¥
		List<Admin> classroomlist = null;

		classroomlist = dao.ad_cr_ClassroomList();
		String cl_count = String.valueOf(classroomlist.size());

		// JSP �������� �ѱ� ������ �غ�
		request.setAttribute("classroomlist", classroomlist);
		request.setAttribute("cl_count", cl_count);

		return "/pages/admin_v/admin_course";
	}

	// 3-3-15. �����ڰ��� - ������������>�������>����ID �ο�(���ǽ� ���� ���� �Ŀ� ����)
	public String ad_cr_addCourseId(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		return "redirect:ad_cr_courseList";
	}

	// 3-3-16. �����ڰ��� - ������������>�������>�ű԰������
	public String ad_cr_addCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ad_cr_addCourse �޼ҵ� ȣ��");

		request.setCharacterEncoding("UTF-8");

		// ������ID, �������۳�¥, ����¥, ���ǽ� ���� �޾ƿ���
		String course_name_id = request.getParameter("course_name_id");
		String cr_start_date = request.getParameter("cr_start_date");
		String cr_end_date = request.getParameter("cr_end_date");
		String classroom_id = request.getParameter("classroom_id");

		// �����ͺ��̽� �׼� ó�� ����
		Ad_subClassDAO3 dao = new Ad_subClassDAO3();
		String course_id = dao.ad_cr_addCourseId();

		Admin ad = new Admin();

		ad.setCourse_id(course_id);
		ad.setCourse_name_id(course_name_id);
		ad.setCr_start_date(cr_start_date);
		ad.setCr_end_date(cr_end_date);
		ad.setClassroom_id(classroom_id);

		System.out.printf("%s,%s,%s,%s,%s%n", course_id, course_name_id, cr_start_date, cr_end_date, classroom_id);

		// ���� ���
		int result = dao.ad_cr_addCourse(ad);
		System.out.printf("������� ��������:%s%n", result);

		// String url = "";
		//
		// if(result==1){
		// url="redirect:ad_cr_courseList.it";
		// }else{
		// url="/pages/error.jsp";
		// }

		return "redirect:ad_cr_courseList";
	}

	// 3-3-21. �����ڰ��� - ������������>��������
	public String ad_cr_modifyCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_cr_modifyCourse �޼ҵ� ȣ��");
		request.setCharacterEncoding("UTF-8");

		// ������ID, �������۳�¥, ����¥, ���ǽ� ���� �޾ƿ���
		String course_id = request.getParameter("course_id");
		String course_name = request.getParameter("course_name");
		String cr_start_date = request.getParameter("cr_start_date");
		String cr_end_date = request.getParameter("cr_end_date");
		String classroom_id = request.getParameter("classroom_id");
		
		//DB���� ���� �׼�
		Admin ad = new Admin();
		ad.setCourse_id(course_id);
		ad.setCourse_name(course_name);
		ad.setCr_start_date(cr_start_date);
		ad.setCr_end_date(cr_end_date);
		ad.setClassroom_id(classroom_id);
		
		Ad_subClassDAO3 dao = new Ad_subClassDAO3();
		
		int result = dao.ad_cr_modifyCourse(ad);		

		if(result==0){
			//����������

		}else{
			//���� �Ϸ�
		}
		
		return "redirect:ad_cr_courseList";
	}

	
	// 3-3-22. �����ڰ��� - ������������>��������
	public String ad_cr_deleteCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_cr_deleteCourse �޼ҵ� ȣ��");
		request.setCharacterEncoding("UTF-8");

		// ������ID, �������۳�¥, ����¥, ���ǽ� ���� �޾ƿ���
		String course_id = request.getParameter("course_id");
		
		//DB���� ���� �׼�		
		Ad_subClassDAO3 dao = new Ad_subClassDAO3();
		
		int result = dao.ad_cr_deleteCourse(course_id);

		if(result==0){
			//����������
			//System.out.println("���� ������ ���� �ʾҽ��ϴ�.");
		}else{
			//System.out.println("���� �����Ϸ�.");
		}
		
		return "redirect:ad_cr_courseList";
	}
	
	// 3-3-18. �����ڰ��� - ���))������������>���� ��>���� ���� ���(����ID, �����, �������&����¥, �����, �����,
	// ����,
	// ��ϵ� �����)
	public String admin_course_ajaxReceive(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ���̵� üũ
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

	// ������������ - ���> ��������������Ʈ ���
	// 3-3-19. �����ڰ��� - ������������>���� ��>������ ���� ���(������ID, �������̸�, ��ȭ��ȣ, �������, �ߵ�Ż����¥)
	public String admin_course_ajaxReceive2(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ���̵� üũ
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

	// 3-3-20. �����ڰ��� - ������������> ���ó�¥ ����Ͽ� ����������¥(����, ��) ����
	public String ad_cr_dateCheck(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		return "redirect:";
	}

	///////////////////////////////// ������//////////////////////////////////

	// 3-3-13. �����ڰ��� -���))������������>��������Ʈ ���(������ID, ������)
	// public String ad_cr_courseNameList(HttpServletRequest request,
	// HttpServletResponse response)
	// throws SecurityException, IOException {
	// return "/pages/admin_v/admin_course";
	// }

}