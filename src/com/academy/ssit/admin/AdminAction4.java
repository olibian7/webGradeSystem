package com.academy.ssit.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;

import java.util.*;

public class AdminAction4 {
	// 3-4-22. �����ڰ��� - �����������> ��������Ʈ ���(����ID, ������, �����Ⱓ, ��ϰ����)
	// ����������� ����
	private Ad_subClassDAO4 dao = new Ad_subClassDAO4();

	public String ad_sj_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ad_sj_courseList �޼ҵ� ȣ��");

		request.setCharacterEncoding("UTF-8");

		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		if (skey == null) {
			skey = "all";
			svalue = "";
		}

		List<Admin> list = dao.ad_sj_courseList(skey, svalue);

		int length = list.size();

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("list", list);
		request.setAttribute("length", length);

		return "/pages/admin_v/admin_subject";
	}

	// 3-4-23. �����ڰ��� - �����������>���񸮽�Ʈ ���(����ID, �����, ����Ⱓ, ����ID, ������, �����Ⱓ, �����,
	// �����
	// )
	public String ad_sj_subjectList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ad_sj_subjectList�޼ҵ� ȣ��");

		String course_id = request.getParameter("course_id");

		Ad_subClassDAO4 dao = new Ad_subClassDAO4();

		// ��¿� ����Ʈ - �ش� ���� ����, �ش� ������ ���Ե� ���� ����
		List<Admin> list = dao.ad_sj_courseList("course_id", course_id);
		List<Admin> sjlist = dao.ad_sj_subjectList("course_id", course_id);

		// ó���� ����Ʈ - (�����߰�)��ü ��������
		List<Admin> totalSjlist = dao.ad_sj_totalSubject();
		List<Admin> totalBooklist = dao.ad_sj_totalbook();
		List<Admin> totalInstructorlist = dao.ad_sj_totalInstructor();
		int totalcount = sjlist.size();

		request.setAttribute("list", list);
		request.setAttribute("sjlist", sjlist);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("totalSjlist", totalSjlist);
		request.setAttribute("totalBooklist", totalBooklist);
		request.setAttribute("totalInstructorlist", totalInstructorlist);

		return "/pages/admin_v/admin_subject_add";
	}

	// 3-4-24. �����ڰ��� - �����������>���� �ű� ����
	public String ad_sj_addSubject(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		response.setCharacterEncoding("UTF-8");

		System.out.println("ad_sj_addSubject�޼ҵ� ȣ��");

		String subject_name_id = request.getParameter("subject_name_id");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String book_id = request.getParameter("book_id");
		String ins_id = request.getParameter("ins_id");
		String course_id = request.getParameter("course_id");

		System.out.printf("%s,%s,%s,%s,%s,%s%n", subject_name_id, start_date, end_date, book_id, ins_id, course_id);

		Admin ad = new Admin();
		ad.setSubject_name_id(subject_name_id);
		ad.setSj_start_date(start_date);
		ad.setSj_end_date(end_date);
		ad.setBook_id(book_id);
		ad.setInstructor_id(ins_id);
		ad.setCourse_id(course_id);

		Ad_subClassDAO4 dao = new Ad_subClassDAO4();

		dao.ad_sj_addSubject(ad);

		return String.format("get:ad_sj_subjectList.it?course_id=%s", course_id);
	}

	// �������
	public String ad_sj_deleteSubject(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ad_sj_deleteSubject �޼ҵ� ȣ��");

		response.setCharacterEncoding("UTF-8");

		String subject_id = request.getParameter("subject_id");
		String course_id = request.getParameter("course_id");

		Ad_subClassDAO4 dao = new Ad_subClassDAO4();
		int result = dao.ad_sj_deleteSubject(subject_id);

		if (result == 0) {
			System.out.println("���� ���� ����");
		} else {
			System.out.println("���� ���� ����");
		}

		return String.format("get:ad_sj_subjectList.it?course_id=%s", course_id);
	}

	// �������
	public String ad_sj_modifySubject(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ad_sj_modifySubject �޼ҵ� ȣ��");

		response.setCharacterEncoding("UTF-8");

		String subject_id = request.getParameter("subject_id");
		String course_id = request.getParameter("course_id");
		String sj_start_date = request.getParameter("start_date");
		String sj_end_date = request.getParameter("end_date");
		String book_id = request.getParameter("book_id");
		String instructor_id = request.getParameter("instructor_id");

		// DB�� ���� �׼�
		Admin ad = new Admin();
		ad.setSubject_id(subject_id);
		ad.setCourse_id(course_id);
		ad.setSj_start_date(sj_start_date);
		ad.setSj_end_date(sj_end_date);
		ad.setBook_id(book_id);
		ad.setInstructor_id(instructor_id);

		Ad_subClassDAO4 dao = new Ad_subClassDAO4();
		int result = dao.ad_sj_modifySubject(ad);

		if (result == 0) {
			System.out.println("���� ���� ����");
		} else {
			System.out.println("���� ���� ����");
		}

		return String.format("get:ad_sj_subjectList.it?course_id=%s", course_id);
	}

	// �������
	// JSONó�������� ���� ����
	// public String admin_subject_json(HttpServletRequest request,
	// HttpServletResponse response)
	// throws SecurityException, IOException {
	// response.setCharacterEncoding("UTF-8");
	//
	// System.out.println("admin_subject_json�޼ҵ� ����");
	//
	// String subject_id = request.getParameter("subject_id");
	// String sj_start_date = request.getParameter("sj_start_date");
	// String sj_end_date = request.getParameter("sj_end_date");
	// String book_id = request.getParameter("book_id");
	// String ins_id = request.getParameter("ins_id");
	// String course_id = request.getParameter("course_id");
	//
	// System.out.println("admin_subject_json�޼ҵ� ����");
	//
	// String subject_id_sub = subject_id.substring(0, 5);
	// String subject_book_id_sub = book_id.substring(0, 5);
	// String subject_ins_id_sub = ins_id.substring(0, 5);
	//
	// Admin ad = new Admin();
	// ad.setSubject_name_id(subject_id_sub);
	// ad.setSj_start_date(sj_start_date);
	// ad.setSj_end_date(sj_end_date);
	// ad.setBook_id(subject_book_id_sub);
	// ad.setInstructor_id(subject_ins_id_sub);
	// ad.setCourse_id(course_id);
	//
	// Ad_subClassDAO4 dao = new Ad_subClassDAO4();
	//
	// dao.ad_sj_addSubject(ad);
	//
	// List<Admin> list = dao.ad_sj_courseList("course_id", course_id);
	// List<Admin> sjlist = dao.ad_sj_subjectList("course_id", course_id);
	// // String result = "";
	//
	// System.out.println("admin_subject_json�޼ҵ� ����");
	//
	// /*
	// *
	// * <c:forEach var="a" items="${sjlist}"> <tr> <td>${a.subject_id}</td>
	// * <td>${a.subject_name}</td>
	// * <td>${a.sj_start_date}~${a.sj_end_date}</td>
	// * <td>${list[0].course_id}</td> <td>${list[0].course_name}</td>
	// * <td>${list[0].cr_start_date} ~ ${list[0].cr_end_date}</td>
	// * <td>${a.book_name}</td> <td>${a.in_name}</td> <td><button
	// * type="button" class="btn btn-default btn-xs modify">����</button></td>
	// * <td><button type="button"
	// * class="btn btn-default btn-xs remove">����</button></td> </tr>
	// * </c:forEach>
	// */
	//
	// JSONObject mySubjects = new JSONObject();
	//
	// JSONArray array = new JSONArray();
	//
	// int totalcount = sjlist.size();
	// mySubjects.put("totalcount", totalcount);
	//
	// for (int i = 0; i < sjlist.size(); i++) {
	//
	// // result += String.format("<tr>");
	// // result += String.format("<td>%s</td>",
	// // sjlist.get(i).getSubject_id());
	// // result += String.format("<td>%s</td>",
	// // sjlist.get(i).getSubject_name());
	// // result += String.format("<td>%s ~ %s</td>",
	// // sjlist.get(i).getSj_start_date(),
	// // sjlist.get(i).getSj_end_date());
	// // result += String.format("<td>%s</td>",
	// // list.get(0).getCourse_id());
	// // result += String.format("<td>%s</td>",
	// // list.get(0).getCourse_name());
	// // result += String.format("<td>%s ~ %s</td>",
	// // list.get(0).getCr_start_date(), list.get(0).getCr_end_date());
	// // result += String.format("<td>%s</td>",
	// // sjlist.get(i).getBook_name());
	// // result += String.format("<td>%s</td>",
	// // sjlist.get(i).getIn_name());
	// // result += String.format("<td><button type=\"button\" class=\"btn
	// // btn-default btn-xs modify\">����</button></td>");
	// // result += String.format("<td><button type=\"button\" class=\"btn
	// // btn-default btn-xs remove\">����</button></td>");
	// // result += String.format("</tr>");
	// JSONObject mySubject = new JSONObject();
	//
	// mySubject.put("subject_id", sjlist.get(i).getSubject_id());
	// mySubject.put("subject_name", sjlist.get(i).getSubject_name());
	// mySubject.put("sj_start_date", sjlist.get(i).getSj_start_date());
	// mySubject.put("sj_end_date", sjlist.get(i).getSj_end_date());
	// mySubject.put("course_id", list.get(0).getCourse_id());
	// mySubject.put("course_name", list.get(0).getCourse_name());
	// mySubject.put("cr_start_date", list.get(0).getCr_start_date());
	// mySubject.put("cr_end_date", list.get(0).getCr_end_date());
	// mySubject.put("book_name", sjlist.get(i).getBook_name());
	// mySubject.put("in_name", sjlist.get(i).getIn_name());
	//
	// array.add(mySubject);
	// }
	//
	// mySubjects.put("subject", array);
	//
	// request.setAttribute("result", mySubjects.toJSONString());
	// // request.setAttribute("result", result);
	//
	// return "/pages/admin_v/admin_subject_json";
	// }

}
