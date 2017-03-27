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
	// 3-6-32. �����ڰ��� - ������ȸ>���������ȸ>���� ��� ���(����ID, ������, �����Ⱓ)
	public String ad_gr_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // ��ü �ο���
		String count = "0"; // �˻� ��� �ο���

		Ad_subClassDAO6 dao = new Ad_subClassDAO6();
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

		list = dao.ad_gr_courseList(skey, svalue);
		totalCount = String.valueOf(list.size());

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);

		return "/pages/admin_v/admin_grade_course";
	}

	// 3-6-33. �����ڰ��� - ������ȸ>���������ȸ>Ư�� ���� ���� ���(����ID, �����, ����Ⱓ, �����, �����Է¿���,
	// ��������Ͽ���, ���ǽ�, �����, ������, �����Ⱓ)
	public String ad_gr_courseSubjectList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // ��ü �ο���

		// courseid ��������
		String course_id = request.getParameter("course_id");

		// �˻� ��û ������ ����
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
		
		
		// --> ���� �Է� ���� Ȯ�� ����
		
		List<String> gradeCheck = new ArrayList<>();

		for (int i = 0; i < subject_list.size(); ++i) {

			// ���õ� ���� ���� ������ ��� ���(list2)
			List<Admin> list2 = dao.ad_gr_subStudentlist(subject_list.get(i).getSubject_id(), skey, svalue);
			System.out.println("���� ���� ���� ������ ����Ʈ ������:" + list2.size());
			String countSt = String.valueOf(list2.size());

			// 2. list�� ������
			int subSumList = list2.size();

			// 1. �л��� ������ ������ 0�� �л��� ��
			int checkPoint = 0;

			// ���� ��� ���� ���ڿ�
			String gradeCheckMessage = null;

			for (int a = 0; a < subSumList; ++a) {

				// �� �л��� ������ ����
				int sumList = list2.get(a).getGr_attend() + list2.get(a).getGr_writing()
						+ list2.get(a).getGr_practice();

				if (sumList == 0) {
					++checkPoint;
				}
			}
			if (checkPoint == 0) {
				gradeCheckMessage = "��ϿϷ�";
			} else if (checkPoint > 0 && checkPoint < subSumList) {
				gradeCheckMessage = "��� ��";
			} else {
				gradeCheckMessage = "�̵��";
			}			
			System.out.println("Ȯ��: " + gradeCheckMessage);

			// ���� �Է� ���� Ȯ�� �޼����� �ش� ����Ʈ�� �Է�
			gradeCheck.add(gradeCheckMessage);
		}
		
		request.setAttribute("gradeCheck", gradeCheck);
		
		// ���� �Է� ���� Ȯ�� ���� --> 
		
		

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("course_id", course_id);
		request.setAttribute("cs_list", course_list);
		request.setAttribute("sj_list", subject_list);
		request.setAttribute("sj_list_size", subject_list.size());
		request.setAttribute("totalCount", totalCount);

		return "/pages/admin_v/admin_grade_course_detail";
	}

	// 3-6-34. �����ڰ��� - ������ȸ>���������ȸ>Ư�� ������ ��� ������ ���� ���(�л�ID, �̸�, ��ȭ��ȣ, ���, �ʱ�,
	// �Ǳ�)
	public String ad_gr_subStudentlist(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return "redirect:";
	}

	// 3-35. �����ڰ��� - ������ȸ>�л�������ȸ>��ü �л� ����Ʈ ���(�л�ID, �̸�, �ֹι�ȣ, ��ȭ��ȣ, �������)
	public String ad_gr_allStudentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // ��ü �ο���
		// �˻� ��û ������ ����
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

	// 3-36. �����ڰ��� - ������ȸ>�л�������ȸ>���糯¥ ���� ��������ϴ� �л��� ���� ����Ʈ ���(����ID, ������, �Ⱓ)
	// ���� ��ħ
	public String ad_gr_studentCourseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // ��ü �ο���
		String count = "0"; // �˻� ��� �ο���
		// �˻� ��û ������ ����
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

	// 3-6-37. �����ڰ��� - ������ȸ>�л�������ȸ>Ư�� �������� ������ ���� �ȿ� �ִ� ���� ���� + ���� ���(�л�ID, �̸�,
	// ��ȭ��ȣ, ����ID, �����, ����Ⱓ, ���, �ʱ�, �Ǳ�)
	public String ad_gr_studentGrade_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // ��ü �ο���
		String count = "0"; // �˻� ��� �ο���
		// �˻� ��û ������ ����
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

		String totalCount = "0"; // ��ü �ο���
		String count = "0"; // �˻� ��� �ο���
		// �˻� ��û ������ ����
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
		// ������ ���� ����� �迭
		JSONArray array1 = new JSONArray();
		// �˻� Ű, ��� ����� �迭
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

		// �˻� Ű, ��� ���� ��ü
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
