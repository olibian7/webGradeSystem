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
	// 3-2-9. �����ڰ��� - �����������>�����������(����ID, �̸�, �ֹε�ϵ޹�ȣ, ��ȭ��ȣ), ���ǰ��ɰ��� ���(����ID,
	// �����ID)
	public String ad_ins_addInstructor(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		System.out.println("ad_ins_addInstructor�޼ҵ� ����");
		
		request.setCharacterEncoding("UTF-8");
		
//		String check = request.getParameter("check");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String ssn = request.getParameter("ssn");
		String[] check = request.getParameterValues("subjectCheck");
		
		System.out.println("temp�迭��");
		for(int i=0; i<check.length; i++){
			System.out.println(check[i]);
		}
		
		Ad_subClassDAO2 dao = new Ad_subClassDAO2();
		
		Admin ad = new Admin();
		
		ad.setIn_name(name);
		ad.setIn_phone(phone);
		ad.setIn_social_num(ssn);
		
		dao.ad_ins_addInstructor(ad, check);
		
//		System.out.println("check�� ��");
//		System.out.println(check);
		
		return "redirect:ad_ins_instructorList"; // memberlist.it
	}

	// 3-2-10. �����ڰ��� - �����������>�����������(����ID, �̸�, �ֹε�ϵ޹�ȣ, ��ȭ��ȣ, ���ǰ��ɰ���)
	public String ad_ins_instructorList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// ���̵� üũ
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("admin_id");

		if (id == null) {
			return "/pages/error";
		}
		System.out.println("ad_ins_instructorList �޼ҵ� ����");
		request.setCharacterEncoding("UTF-8");

		String totalCount = "0"; // ��ü �ο���
		String count = "0"; // �˻� ��� �ο���

		// ����� ����Ʈ �ҷ�����
		/* Ad_subClassDAO1 dao1 = new Ad_subClassDAO1(); */

		// �������� �ҷ�����
		Ad_subClassDAO2 dao = new Ad_subClassDAO2();
		List<Admin> list = null;
		List<Admin> teach_list = null;

		// �˻� ��û ������ ����
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		// ���� ��û�ÿ��� �˻� ��û �����Ͱ� ���� �����̹Ƿ�
		// �⺻���� ä���� �Ѵ�.
		if (skey == null) {
			skey = "all";
			svalue = "";
		}

		list = dao.ad_ins_instructorList(skey, svalue); // �˻� �� ��ü ���
		teach_list = dao.ad_ins_teachSubjectList(); // �������� ����

		totalCount = String.valueOf(dao.ad_ins_totalCount()); // ��ü �ο���
		System.out.println(totalCount);

		count = String.valueOf(list.size()); // �˻� ��� �ο���
		
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

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return "/pages/admin_v/admin_teacher_info"; // memberlist.jsp

	}

	// 3-2-11. �����ڰ��� - �����������> ����������°˻�(�����̸�, �����, ������۳�¥&����¥, ������, �������۳�¥&����¥,
	// �����, ���ǽǸ�, �������࿩�� )
	public String ad_ins_instructorList_detail(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return "redirect:studentlist.it"; // memberlist.it
	}

	// 3-2-12. �����ڰ��� - �����������> �����������> ������������(���ǰ��ɰ��񿡼��� ����)
	public String ad_ins_deleteInstructor(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		// ���̵� üũ
				HttpSession session = request.getSession();
				String id = (String) session.getAttribute("admin_id");

				if (id == null) {
					return "/pages/error";
				}
				System.out.println("ad_ins_deleteInstructor �޼ҵ� ����");
				request.setCharacterEncoding("UTF-8");
				
				String in_id = request.getParameter("in_id");
				Ad_subClassDAO2 dao = new Ad_subClassDAO2();
				String delete_ok = String.valueOf(dao.ad_ins_deleteInstructor(in_id));
				
				request.setAttribute("delete_key", delete_ok);

		return "redirect:ad_ins_instructorList";
	}

	// 3-2-12. �����ڰ��� - �����������> ������
	public String ad_ins_subjectAjax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ad_ins_subjectAjax �޼ҵ� ����");

		// ���̵� üũ
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
	// 3-2-13. �����ڰ��� - �����������>������������(����ID, �̸�, �ֹε�ϵ޹�ȣ, ��ȭ��ȣ)
	// �����ID)
	public String ad_ins_modifyInstructor(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		System.out.println("ad_ins_modifyInstructor�޼ҵ� ����");
		
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String[] check = request.getParameterValues("subjectCheck");
		
		System.out.println("temp�迭��");
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
