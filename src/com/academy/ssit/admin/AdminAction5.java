package com.academy.ssit.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.*;

public class AdminAction5 {
	// 3-5-25. �����ڰ��� - �л�����>�л��������(�л�ID, �̸�, �ֹι�ȣ, �ڵ���)
	public String ad_st_addStudent(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// ID üũ
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";

		}

		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_st_addStudent �޼ҵ� ȣ��");

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

	// 3-5-26. �����ڰ��� - �л�����>��ü�˻�>������ ��ü����Ʈ ���(�л�ID, �̸�, �ֹι�ȣ, �ڵ���, �����, ������ûȽ��)
	public String ad_st_allStudentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // ��ü �ο���
		String count = "0"; // �˻� ��� �ο���

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();
		List<Admin> list = null;

		// �˻� ��û ������ ����
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		// ���� ��û�ÿ��� �˻� ��û �����Ͱ� ���� �����̹Ƿ�
		// �⺻���� ä���� �Ѵ�.
		if (skey == null) {
			skey = "all";
		}

		System.out.println(skey);
		System.out.println(svalue);

		list = dao.ad_st_allStudentList(skey, svalue); // �˻� �� ��ü ���

		System.out.println("����");

		totalCount = String.valueOf(dao.ad_ins_totalCount()); // ��ü �ο���
		count = String.valueOf(list.size()); // �˻� ��� �ο���

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("allStudentList", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return "/pages/admin_v/admin_student"; // memberlist.jsp

	}

	// 3-5-27. �����ڰ��� - �л�����>�л� ���� ��� �� ���� ��� ���(����Id, ������, �����Ⱓ, ���ǽ�, �����ο�,
	// ��ϰ����)
	public String ad_st_studentCourseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_st_studentCourseList�޼ҵ� ����");

		String student_id = request.getParameter("student_id");

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();

		// // ����
		// int result = dao.ad_st_registerCourse(course_id, student_id);

		List<Admin> list = dao.ad_st_allStudentList("student_id", student_id);
		List<Admin> total_list = dao.ad_st_studentCourseList();
		List<Admin> check_list = dao.ad_st_studentInfo("student_id", student_id);
		int check_len = check_list.size();
		int total_len = total_list.size();

		String totalcount = String.valueOf(total_list.size());

		// �˻� ��û ������ ����
		// list = dao.ad_st_courseStudentList("student_id", student_id); // �˻� ��
		// ��ü ���

		request.setAttribute("list", list);
		request.setAttribute("total_list", total_list);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("check_list", check_list);

		request.setAttribute("check_len", check_len);
		request.setAttribute("total_len", total_len);

		// request.setAttribute("result", result);

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return "/pages/admin_v/admin_student_addcourse"; // memberlist.jsp
	}

	// 3-5-33. �����ڰ��� - �л�����>�л� ���� ���
	public String ad_st_add_studentCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_st_add_studentCourse�޼ҵ� ����");

		String student_id = request.getParameter("student_id");
		String course_id = request.getParameter("course_id");

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();

		// ��� ���
		int result = dao.ad_st_add_studentCourse(course_id, student_id);

		if (result == 0) {
			System.out.println("�л� ������� ����");
		} else {
			System.out.println("�л� ������� ����");
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return String.format("get:ad_st_studentCourseList.it?student_id=%s",student_id);
	}

	// 3-5-34. �����ڰ��� - �л�����>�л� ���� ��� ���
	public String ad_st_delete_studentCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ad_st_delete_studentCourse�޼ҵ� ����");

		String student_id = request.getParameter("student_id");
		String course_id = request.getParameter("course_id");

		System.out.printf("%s,%s%n",student_id,course_id);
		
		Ad_subClassDAO5 dao = new Ad_subClassDAO5();

		// ���� ���
		int result = dao.ad_st_delete_studentCourse(course_id, student_id);

		if (result == 0) {
			System.out.println("�л� ������� ��� ����");
		} else {
			System.out.println("�л� ������� ��� ����");
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return String.format("get:ad_st_studentCourseList.it?student_id=%s", student_id);
	}

	// 3-5-29. �����ڰ��� - �л�����>������ �������� ���(������, �����Ⱓ, ���ǽǸ�, �ߵ�Ż����)
	public String ad_st_studentInfo(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		return "redirect:";
	}

	// 3-5-30. �����ڰ��� - �л�����>�����˻�>������ ���� ��ü ���(����ID, ������, �����Ⱓ, ���ǽ�)
	public String ad_st_allCourseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // ��ü �ο���
		String count = "0"; // �˻� ��� �ο���

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();
		List<Admin> list = null;

		// �˻� ��û ������ ����
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		// ���� ��û�ÿ��� �˻� ��û �����Ͱ� ���� �����̹Ƿ�
		// �⺻���� ä���� �Ѵ�.
		if (skey == null) {
			skey = "all";
			svalue = "";
		}

		list = dao.ad_st_allCourseList(skey, svalue); // �˻� �� ��ü ���

		totalCount = String.valueOf(dao.ad_ins_totalCount()); // ��ü �ο���
		count = String.valueOf(list.size()); // �˻� ��� �ο���

		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);
		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		// ad_st_courseStudentList(request, response);

		// ad_st_allStudentList(request,response);
		// ��� ��ȯ->������ �̵� ����->JSP ������
		return "/pages/admin_v/admin_student_coursesearch"; // memberlist.jsp
	}

	// 3-5-31. (���)�����ڰ��� - �л�����>�����˻�>������ �̸� �˻�(�л�ID, �̸�, �ֹε�Ϲ�ȣ, ��ȭ��ȣ, �������,
	// �ߵ�Ż����¥)
	public String ad_st_courseStudentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // ��ü �ο���
		String count = "0"; // �˻� ��� �ο���

		Ad_subClassDAO5 dao = new Ad_subClassDAO5();
		List<Admin> list = null;

		// �˻� ��û ������ ����
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		String course_id = request.getParameter("course_id");

		// ���� ��û�ÿ��� �˻� ��û �����Ͱ� ���� �����̹Ƿ�
		// �⺻���� ä���� �Ѵ�.
		if (skey == null) {
			skey = "all";
			svalue = "";
		}

		list = dao.ad_st_courseStudentList("course_id", course_id); // �˻� �� ��ü
																	// ���

		totalCount = String.valueOf(dao.ad_ins_totalCount()); // ��ü �ο���
		count = String.valueOf(list.size()); // �˻� ��� �ο���
		System.out.println(count);
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return "/pages/admin_v/admin_student_coursesearch"; // memberlist.jsp

	}

	// 3-5-32. AJAX(���)�����ڰ��� - �л�����>�����˻�>������ �̸� �˻�(�л�ID, �̸�, �ֹε�Ϲ�ȣ, ��ȭ��ȣ, �������,
	// �ߵ�Ż����¥)
public String admin_student_coursesearch_ajax(HttpServletRequest request, HttpServletResponse response)
		throws SecurityException, IOException {
	System.out.println("admin_student_coursesearch_ajax �޼ҵ� ����");

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
	
	// ������ ���� ����� �迭
	JSONArray array = new JSONArray();


	// ������ ���� ����� �迭
	for (int i = 0; i < list.size(); i++) {

		JSONObject student = new JSONObject();

		student.put("student_id", list.get(i).getStudent_id());
		student.put("st_name", list.get(i).getSt_name());
		student.put("st_social_num", list.get(i).getSt_social_num());
		student.put("st_phone", list.get(i).getSt_phone());
		student.put("st_registration_date", list.get(i).getSt_registration_date());
		student.put("fail_date", (list.get(i).getFail_date() == null) ? "-" : list.get(i).getFail_date());
		
		// �迭�� ����
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

		System.out.println("json.it���� ������ ���");
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
