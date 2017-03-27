package com.academy.ssit.Instructor;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;
import org.json.simple.*;

import com.academy.ssit.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

public class InstructorAction {

	private InstructorDAO dao = new InstructorDAO();
	// ����ID�� session ��ü���� �޾ƿ;� �ϳ�, ���Ƿ� �ο��Ͽ� �׽�Ʈ �Ŀ� �Ϸ��� ����
	// ���������� ������ �ѱ�� ������ �� Ư������ ���� �ڵ� ������
	/* private String id = "in001"; */

	// 2-1. ������� - ���� ������(��������, ���ǿ���, ������)
	// ���� ����(����ID, �����̸�, ������� ��¥, ���� ����¥, �����̸�, �������۳�¥, ��������¥, ���ǽ��̸�, �����̸�, �л�
	// ��)
	public String ins_scheduleList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("���ǽ����� ��ȸ");

		// �˻� ��û ������ ���� ó��
		/* String skey = request.getParameter("skey"); */
		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("in_id");
		// ����Ʈ ����� ���� �ʿ��� ���� -> lid, ����id, subKey, subValue

		// ������ ����(lid)�� �ʱⰪ�� lid="sub1"(���ǿ���)
		String lid = request.getParameter("lid");
		if (lid == null) {
			lid = "sub1";
		}
		System.out.println(lid);

		// ����ID�� ���� session���� �޾ƿ��� ������ �ϵ�,
		// �׽�Ʈ�� ���ؼ� id="in001" �ʱⰪ ����(private)

		// �˻�����Ű(��ü, ����ID, �����)
		String subKey = request.getParameter("subKey");
		if (subKey == null) {
			subKey = "1";
		}
		System.out.println("subKey : " + subKey);
		// �˻� ����Ű�� ���� ��
		String subValue = request.getParameter("subValue");
		System.out.println("subValue : " + subValue);

		// �����ͺ��̽� �׼� ó�� ����
		List<Instructor> list = null;
		String count = "0";
		String title = null;
		switch (lid) {
		case "sub1":
			title = "���� ����";
			break;
		case "sub2":
			title = "���� ��";
			break;
		case "sub3":
			title = "���� ����";
			break;
		default:
			title = "error";
		}

		if (lid != null && (lid.equals("sub1") || lid.equals("sub2") || lid.equals("sub3"))) {
			list = dao.ins_scheduleList(lid, id, subKey, subValue);
		} else {
			lid = "sub1";
			list = dao.ins_scheduleList(lid, id, subKey, subValue);
		}
		count = String.valueOf(list.size());

		// JSP�������� �ѱ� ������ �غ�
		request.setAttribute("lid", lid);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("title", title);
		request.setAttribute("subKey", subKey);
		request.setAttribute("subValue", subValue);
		request.setAttribute("id", id);

		// Modal�׼ǿ� ������ ������ �Ѱ��ֱ�
		ins_studentList(request, response);

		return "teacher_v/teacher_scheduleList";
	}

	// 2-2. ������� - ������ ���� ������ ����Ʈ
	// ����(���� ����, ��, ����)�� ���� ���������� ���(student_id, name, phone, �ߵ�Ż������, gr_attend,
	// gr_writing, gr_practice)
	public String ins_studentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		String subject_id = "";
		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("in_id");
		String searchkey = "notYet";
		String searchvalue = "notYet";
		List<Instructor> studentList = dao.ins_studentList(searchkey, searchvalue, subject_id, id);
		request.setAttribute("studentList", studentList);

		return "redirect:teacher_scheduleList";
	}

	// 2-2(�߰�). ������� - ���� ���ÿ� ���� ������ ��� ����
	@SuppressWarnings("unchecked")
	public String teacher_scheduleList_ajaxReceive(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		// session���� ���� ID �ӽ� �ο�
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("in_id");

		String keyword = request.getParameter("keyword");
		System.out.println("Ű����" + keyword);
		String searchkey = request.getParameter("searchkey");
		if (searchkey == null) {
			searchkey = "";
		}
		System.out.println("Ű" + searchkey);
		String searchvalue = request.getParameter("searchvalue");
		if (searchvalue == null) {
			searchvalue = "";
		}
		System.out.println("��" + searchvalue);
		InstructorDAO dao = new InstructorDAO();
		List<Instructor> list = dao.ins_studentList(searchkey, searchvalue, keyword, id);
		System.out.println("������ :" + list.size());

		// JSON ����
		JSONObject students = new JSONObject();

		JSONArray array = new JSONArray();

		for (int i = 0; i < list.size(); ++i) {
			JSONObject temp = new JSONObject();

			temp.put("student_id", list.get(i).getStudent_id());
			temp.put("name", list.get(i).getSt_name());
			temp.put("phone", list.get(i).getSt_phone());
			String failcheck = (list.get(i).getFailcheck() == 0 ? "O" : "X");
			temp.put("failcheck", failcheck);
			temp.put("gr_attend", list.get(i).getGr_attend());
			temp.put("gr_writing", list.get(i).getGr_writing());
			temp.put("gr_practice", list.get(i).getGr_practice());
			temp.put("sc_attend", list.get(i).getSc_attend());
			temp.put("sc_writing", list.get(i).getSc_writing());
			temp.put("sc_practice", list.get(i).getSc_practice());

			array.add(temp);
		}

		students.put("student", array);
		request.setAttribute("result", students.toJSONString());

		return "/pages/teacher_v/teacher_scheduleList_ajaxreceive";
	}

	// 2-3. ������� - ���� ������� + ������Ͽ��� Ȯ��
	// �������(����ID, �����,������۳�¥, ���� ���ᳯ¥, ������, �������۳�¥, ��������¥, ���ǽǸ�, �����, sc_attend,
	// sc_writing, sc_practice, gradecheck)
	public String ins_subScoreList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("in_id");
		String url = "teacher_v/teacher_score";
		
		if(id==null){
			url = "/error";
		}
		
		// �˻��׼� �غ�
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		
		if (skey == null) {
			skey = "all";
			svalue = "";
		}
		
		List<Instructor> list = dao.ins_subScoreList("key", id, skey,svalue);

		List<String> temp = new ArrayList<String>();
		
		for(int i=0; i<list.size(); i++){
			String temp_subject = list.get(i).getSubject_id();
			temp.add(temp_subject);
		}
		
		List<Instructor> test_list = dao.ins_testList(temp);

		String count = String.valueOf(list.size());

		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("list", list);
		request.setAttribute("test_list", test_list);
		request.setAttribute("count", count);
		
		return "teacher_v/teacher_score";
	}

	// 2-4. ������� - �����Է�
	// �����Է�(����ID, ���, �ʱ�, �Ǳ�)score(subject_id, attend_score, writing_score,
	// practice_score)
	public String ins_addScore(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String subject_id = request.getParameter("subject_id1");
		String sc_attend = request.getParameter("attendance1");
		String sc_writing = request.getParameter("exam1");
		String sc_practice = request.getParameter("prac_exam1");

		Instructor in = new Instructor();
		in.setSubject_id(subject_id);
		in.setSc_attend(Integer.parseInt(sc_attend));
		in.setSc_writing(Integer.parseInt(sc_writing));
		in.setSc_practice(Integer.parseInt(sc_practice));

		// DB�� ���� �Է� �׼�
		InstructorDAO dao = new InstructorDAO();
		dao.ins_addScore(in);

		return "redirect:ins_subScoreList";
	}

	// 2-5. ������� - ��������
	public String ins_modifyScore(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String subject_id = request.getParameter("subject_id2");
		String sc_attend = request.getParameter("attendance2");
		String sc_writing = request.getParameter("exam2");
		String sc_practice = request.getParameter("prac_exam2");

		Instructor in = new Instructor();
		in.setSubject_id(subject_id);
		in.setSc_attend(Integer.parseInt(sc_attend));
		in.setSc_writing(Integer.parseInt(sc_writing));
		in.setSc_practice(Integer.parseInt(sc_practice));

		// DB�� ���� �Է� �׼�
		InstructorDAO dao = new InstructorDAO();
		dao.ins_modifyScore(in);

		return "redirect:ins_subScoreList";
	}

	// 2-6. ������� - ��������
	public String ins_deleteScore(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String subject_id = request.getParameter("subject_id3");

		// DB�� ���� �Է� �׼�
		InstructorDAO dao = new InstructorDAO();
		dao.ins_deleteScore(subject_id);

		return "redirect:ins_subScoreList";
	}

	// 2-7. ������� - ������
	// ������(����ID, ���賯¥, ���蹮��)test(subject_id, test_date, test_paper)
	public String ins_addTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		// ���� ���ε� �׼�
		String url = "redirect:ins_subScoreList";
		try {
			// ���� ������ -> ������ ������ ����, ���� ����(�̸�, ������, ����)

			// picture ������ ���� ������ ���� ��� Ȯ��
			// ->����. ��Ŭ������ ������Ʈ ������ ���ؼ� ���� ��ΰ� �ƴ�. ��Ĺ ������ ��ϴ� ���� ������.
			String savePath = request.getServletContext().getRealPath("test_files");
			System.out.println(savePath);

			// picture ������ �������� �ʴ� ��� ���� ����
			java.io.File f = new java.io.File(savePath);
			if (!f.exists()) {
				f.mkdir();
			}

			// ���ε� ������ ũ�� ����
			int sizeLimit = 1024 * 500; // 1024byte -> 1K byte -> 1024K byte
										// ->
										// 1M byte

			// ���ε� ���Ͽ� ���� ������ ó�� �� ���� Ȯ���� ���� ��ü ����
			// ���ε� ���Ͽ� ���� ������ ����
			// ���� ���� �̸��� ���� ���� ���ε忡 ����ؼ� ���� �̸� ���� ����
			MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
					new MyFileRenamePolicy());

			// ���ε� ���Ͽ� ���� ���� Ȯ��
			String filesystemName = multi.getFilesystemName("fileName");
			String contentType = multi.getContentType("fileName");
			String originalFileName = multi.getOriginalFileName("fileName");

			// employeeid ������ ��� ������ request.getParameter()�� �ƴϰ�,
			// multi.getParameter() �̴�.
			String subject_id = multi.getParameter("subject_id4");
			String test_date = multi.getParameter("exam_date1");
			String instructor_id = multi.getParameter("instructor_id");

			// �űԵ�Ͽ��� Ȯ��Ű
			String picturekey = "0";
			/*
			 * System.out.printf("getContentType: %s %n", filesystemName);
			 * System.out.printf("getFilesystemName: %s %n", contentType);
			 * System.out.printf("getOriginalFileName: %s %n",
			 * originalFileName);
			 */

			System.out.println(contentType);
			
			// ���� -> image/jpeg, image/png
			if (!(contentType.equals("application/x-zip-compressed"))) {

				// ���������� ���ε�� ���Ͽ� ���� ���� �׼� �߰�
				String deleteFilename = savePath + "\\" + filesystemName;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();
				// System.out.println("deleteFilename : " + deleteFilename);

				// ���� �߻��� -> ���ε�� ���Ͽ� ���� ó�� ����
				throw new Exception("zip ������ �ƴϰų� �߸��� ���� �Դϴ�.");
			}

			// �����ͺ��̽��� ���� ����
			// ->�����̸�(�ű� ���ϸ�, filesystemName), ���� ����(comment)
			// ->���� ó�� �߰�
			Instructor in = new Instructor();
			in.setSubject_id(subject_id);
			in.setTest_date(test_date);
			in.setTest_paper(filesystemName);
			in.setTest_paper_name(originalFileName);
			//in.setTest_paper_name(originalFileName);
			
			
			InstructorDAO dao = new InstructorDAO();
			int result = 0;
			if (picturekey.equals("0")) {
				// �������� �Է� �׼�
				result = dao.ins_addTest(in);
				System.out.println("ins_addTest �޼ҵ� ȣ��");
			} else {
				// ������ �ԷµǾ� �ִ� ���� ���ϸ� �ҷ�����
				String test_paper = dao.ins_subScoreList("", instructor_id, "subject_id", subject_id).get(0).getTest_paper();

				// ���������� ���ε�� ���Ͽ� ���� ���� �׼� �߰�
				String deleteFilename = savePath + "\\" + test_paper;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();

				// ���� ���� ����
				// result = dao.pictureModify(emp);
				// System.out.println("pictureModify �޼ҵ� ȣ��");
			}

			if (result == 0) {
				String deleteFilename = savePath + "\\" + filesystemName;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();
				throw new Exception("���ε�� ������ ������ �����ͺ��̽��� ������� ���߽��ϴ�.");
			}
		} catch (Exception e) {
			url = "/error";
			e.printStackTrace();
		}
		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // ins_subScoreList.jsp
	}

	// 2-8. ������� - �������
	public String ins_modifyTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		System.out.println("ins_modifyTest �������� �̵�");
		request.setCharacterEncoding("UTF-8");

		// ���� ���ε� �׼�
		String url = "redirect:ins_subScoreList";
		try {
			// ���� ������ -> ������ ������ ����, ���� ����(�̸�, ������, ����)

			// picture ������ ���� ������ ���� ��� Ȯ��
			// ->����. ��Ŭ������ ������Ʈ ������ ���ؼ� ���� ��ΰ� �ƴ�. ��Ĺ ������ ��ϴ� ���� ������.
			String savePath = request.getServletContext().getRealPath("test_files");
			System.out.println(savePath);

			// picture ������ �������� �ʴ� ��� ���� ����
			java.io.File f = new java.io.File(savePath);
			if (!f.exists()) {
				f.mkdir();
			}

			// ���ε� ������ ũ�� ����
			int sizeLimit = 1024 * 500; // 1024byte -> 1K byte -> 1024K byte
										// ->
										// 1M byte

			// ���ε� ���Ͽ� ���� ������ ó�� �� ���� Ȯ���� ���� ��ü ����
			// ���ε� ���Ͽ� ���� ������ ����
			// ���� ���� �̸��� ���� ���� ���ε忡 ����ؼ� ���� �̸� ���� ����
			MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
					new MyFileRenamePolicy());

			// ���ε� ���Ͽ� ���� ���� Ȯ��
			String filesystemName = multi.getFilesystemName("exam_file2");
			String contentType = multi.getContentType("exam_file2");
			String originalFileName = multi.getOriginalFileName("exam_file2");

			// employeeid ������ ��� ������ request.getParameter()�� �ƴϰ�,
			// multi.getParameter() �̴�.
			String subject_id = multi.getParameter("subject_id5");
			String test_date = multi.getParameter("exam_date2");
			String instructor_id = multi.getParameter("instructor_id");

			// �űԵ�Ͽ��� Ȯ��Ű
			String picturekey = "0";
			/*
			 * System.out.printf("getContentType: %s %n", filesystemName);
			 * System.out.printf("getFilesystemName: %s %n", contentType);
			 * System.out.printf("getOriginalFileName: %s %n",
			 * originalFileName);
			 */

			System.out.println(contentType);
			
			// ���� -> image/jpeg, image/png
			if (!(contentType.equals("application/x-zip-compressed"))) {

				// ���������� ���ε�� ���Ͽ� ���� ���� �׼� �߰�
				String deleteFilename = savePath + "\\" + filesystemName;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();
				// System.out.println("deleteFilename : " + deleteFilename);

				// ���� �߻��� -> ���ε�� ���Ͽ� ���� ó�� ����
				throw new Exception("zip ������ �ƴϰų� �߸��� ���� �Դϴ�.");
			}

			// �����ͺ��̽��� ���� ����
			// ->�����̸�(�ű� ���ϸ�, filesystemName), ���� ����(comment)
			// ->���� ó�� �߰�
			Instructor in = new Instructor();
			in.setSubject_id(subject_id);
			in.setTest_date(test_date);
			in.setTest_paper(filesystemName);
			in.setTest_paper_name(originalFileName);
			//in.setTest_paper_name(originalFileName);
			
			
			InstructorDAO dao = new InstructorDAO();
			int result = 0;
			if (picturekey.equals("0")) {
				// �������� �Է� �׼�
				result = dao.ins_modifyTest(in);
			} else {
				// ������ �ԷµǾ� �ִ� ���� ���ϸ� �ҷ�����
				String test_paper = dao.ins_subScoreList("", instructor_id, "subject_id", subject_id).get(0).getTest_paper();

				// ���������� ���ε�� ���Ͽ� ���� ���� �׼� �߰�
				String deleteFilename = savePath + "\\" + test_paper;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();

				// ���� ���� ����
				// result = dao.pictureModify(emp);
				// System.out.println("pictureModify �޼ҵ� ȣ��");
			}

			if (result == 0) {
				String deleteFilename = savePath + "\\" + filesystemName;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();
				throw new Exception("���ε�� ������ ������ �����ͺ��̽��� ������� ���߽��ϴ�.");
			}
		} catch (Exception e) {
			url = "/error";
			e.printStackTrace();
		}
	
		return "redirect:ins_subScoreList";
	}

	// 2-9. ������� - �������
	public String ins_deleteTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		System.out.println("ins_deleteTest �������� �̵�");
		request.setCharacterEncoding("UTF-8");
			
		String subject_id = request.getParameter("subject_id6");
		String exam_date = request.getParameter("exam_date3");
		String examfile = request.getParameter("exam_file3");
		
		Instructor ins = new Instructor();
		ins.setSubject_id(subject_id);
		ins.setTest_date(exam_date);
		ins.setTest_paper(examfile);

		//DB ���� �׼�
		int result = dao.ins_deleteTest(ins);
		if(result==0){
			System.out.println("DB���� ���� ���� ");
		}else{
			System.out.println("DB���� ���� ����! ");
		}
		
		
//		// ������ �ԷµǾ� �ִ� ���� ���ϸ� �ҷ�����
//		String test_paper = dao.ins_subScoreList("", instructor_id, "subject_id", subject_id).get(0).getTest_paper();
//
//		// ���������� ���ε�� ���Ͽ� ���� ���� �׼� �߰�
//		String deleteFilename = savePath + "\\" + test_paper;
//		java.io.File temp = new java.io.File(deleteFilename);
//		temp.delete();
//		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
//				new MyFileRenamePolicy());
//
//	
//		// ���ε� ���Ͽ� ���� ���� Ȯ��
//		// ������ �ԷµǾ� �ִ� ���� ���ϸ� �ҷ�����
//		String filesystemName = multi.getFilesystemName("fileName");
//		String originalFileName = multi.getOriginalFileName("fileName");
//		//������ ���� ����
//		String deleteFilename = savePath + "\\" + filesystemName;
//		java.io.File temp = new java.io.File(deleteFilename);
//		temp.delete();
		
		// ���������� ���ε�� ���Ͽ� ���� ���� �׼� �߰�
		String savePath = request.getServletContext().getRealPath("test_files");
		
		String deleteFilename = savePath + "\\" + examfile;
		java.io.File temp = new java.io.File(deleteFilename);
		temp.delete();
		
		
		request.setAttribute("subject_id5", subject_id);
		request.setAttribute("exam_date2", exam_date);
		request.setAttribute("exam_file2", examfile);
		
		return "redirect:ins_subScoreList";
	}

	// 2-10(�߰�). ������� - Ư�� ���� ���� ��, ���õ� ������ ���� ǥ��
		// �������� ��ȯ�Ǹ鼭 ������ �׼��� �߰���.
		public String ins_gra_subjectList(HttpServletRequest request, HttpServletResponse response)
				throws SecurityException, IOException {
			System.out.println("ins_gra_subjectList �������� �̵�");
			request.setCharacterEncoding("UTF-8");

			HttpSession sesion = request.getSession();
			String id = (String) sesion.getAttribute("in_id");
			if (id == null) {
				// id�� ������ ����
				return "/pages/error.jsp";
			}

			// ���õ� ���� ���� ���(list1)
			// �޾ƿ��� �� = subjectId, skey, svalue, id
			String subjectId = request.getParameter("subjectId");
			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");
			if (skey == null) {
				skey = "";
			}
			
			List<String> temp = new ArrayList<>();
			temp.add(subjectId);
			List<Instructor> test_list = dao.ins_testList(temp);
			
			List<Instructor> list1 = dao.ins_gra_subjectList(subjectId, id);
			String term = String.format("%s(%s~%s)", list1.get(0).getCourse_name(), list1.get(0).getCr_start_date(),
					list1.get(0).getCr_end_date());
			System.out.println(term);

			// ���õ� ���� ���� ������ ��� ���(list2)
			List<Instructor> list2 = dao.ins_gra_studentList(skey, svalue, subjectId);
			System.out.println("���� ���� ���� ������ ����Ʈ ������:" + list2.size());
			String countSt = String.valueOf(list2.size());
			
			
			// 1. �л��� ������ ������ 0�� �л��� ��
			// 2. list�� ������
			// 1�� 2�� ���Ͽ�
			// case1. 1�� �׸��� 0�� ��� :  ��� �Ϸ�
			// case1. 1�� �׸��� 0 �̻� (2�� �׸�) �̸��� ��� :  ��� ��
			// case1. 1�� �׸� = 2�� �׸� :  �̵��
			
			// 2. list�� ������
			int subSumList = list2.size();

			// 1. �л��� ������ ������ 0�� �л��� ��
			int checkPoint = 0;
			
			// ���� ��� ���� ���ڿ�
			String gradeCheckMessage = null;
			
			for(int a = 0; a < subSumList; ++a) {
				
				// �� �л��� ������ ����
				int sumList = list2.get(a).getGr_attend() + list2.get(a).getGr_writing() + list2.get(a).getGr_practice();
				
				if(sumList == 0) {
					++checkPoint;
				}
			}
			System.out.println("Ȯ��: "+checkPoint);
			if(checkPoint == 0) {
				gradeCheckMessage = "��ϿϷ�";
			} else if(checkPoint > 0 && checkPoint < subSumList) {
				gradeCheckMessage = "��� ��";
			} else {
				gradeCheckMessage = "�̵��";
			}
			request.setAttribute("gradeCheckMessage", gradeCheckMessage);
			

			request.setAttribute("subjectId", subjectId);
			request.setAttribute("subjectList", list1);
			request.setAttribute("term_subject", term);
			request.setAttribute("stList", list2);
			request.setAttribute("countSt", countSt);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			request.setAttribute("test_list", test_list);
			
			return "teacher_v/teacher_grade_detail";
		}

	// 2-10. ������� - Ư�� ���� �������� ������ ���� ���(����, ���Ῡ��)student_id, name, phone,
	// failcheck, gr_attend, gr_writing, gr_practice
	public String ins_gra_studentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return "redirect:";
	}

	// 2-11. ���� �Է�(subject_id, course_id, student_id, attend_score,
	// writing_score, practice_score)
	public String ins_addGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ins_addGrade �������� �̵�");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("in_id");
		if(id == null) {
			// id�� ������ ����
			return "/pages/error.jsp";
		}
		
		// �Է� ���� ���� �޾ƿ���
		String student_id = request.getParameter("num");
		String course_id = request.getParameter("subCourse_id");
		System.out.println(course_id);
		String subject_id = request.getParameter("subSubject_id");
		System.out.println(subject_id);
		String gr_attend = request.getParameter("attend");
		System.out.println(gr_attend);
		String gr_writing = request.getParameter("writing");
		System.out.println(gr_writing);
		String gr_practice = request.getParameter("practice");
		System.out.println(gr_practice);
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		
		// �޾ƿ� ���� Instructor ��ü�� �Է�
		Instructor ins = new Instructor();
		ins.setStudent_id(student_id);
		ins.setCourse_id(course_id);
		ins.setSubject_id(subject_id);
		ins.setGr_attend(Integer.parseInt(gr_attend));
		ins.setGr_writing(Integer.parseInt(gr_writing));
		ins.setGr_practice(Integer.parseInt(gr_practice));
		System.out.println("test1");
		
		
		// ��ü�� ���� DAO �׼� ���� -->
		int result = dao.ins_addGrade(ins);
		System.out.printf("%d ���� ������ �Է� ����!", result);
		
		// ��� ������ �� �Ѱ��ֱ�
		if (skey == null) {
			skey = "";
		}
		List<Instructor> list1 = dao.ins_gra_subjectList(subject_id, id);
		String term = String.format("%s(%s~%s)", list1.get(0).getCourse_name(), list1.get(0).getCr_start_date(),
				list1.get(0).getCr_end_date());
		System.out.println(term);

		List<String> temp = new ArrayList<>();
		temp.add(subject_id);
		
		List<Instructor> test_list = dao.ins_testList(temp);
		
		// ���õ� ���� ���� ������ ��� ���(list2)
		List<Instructor> list2 = dao.ins_gra_studentList(skey, svalue, subject_id);
		System.out.println("���� ���� ���� ������ ����Ʈ ������:" + list2.size());
		String countSt = String.valueOf(list2.size());
		
		// 2. list�� ������
		int subSumList = list2.size();

		// 1. �л��� ������ ������ 0�� �л��� ��
		int checkPoint = 0;
		
		// ���� ��� ���� ���ڿ�
		String gradeCheckMessage = null;
		
		for(int a = 0; a < subSumList; ++a) {
			
			// �� �л��� ������ ����
			int sumList = list2.get(a).getGr_attend() + list2.get(a).getGr_writing() + list2.get(a).getGr_practice();
			
			if(sumList == 0) {
				++checkPoint;
			}
		}
		System.out.println("Ȯ��: "+checkPoint);
		if(checkPoint == 0) {
			gradeCheckMessage = "��ϿϷ�";
		} else if(checkPoint > 0 && checkPoint < subSumList) {
			gradeCheckMessage = "��� ��";
		} else {
			gradeCheckMessage = "�̵��";
		}
		request.setAttribute("gradeCheckMessage", gradeCheckMessage);

		request.setAttribute("subjectId", subject_id);
		request.setAttribute("subjectList", list1);
		request.setAttribute("term_subject", term);
		request.setAttribute("stList", list2);
		request.setAttribute("countSt", countSt);
		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("test_list", test_list);
		
		return "teacher_v/teacher_grade_detail";
	}

	// 2-12. ���� ����
	public String ins_modifyGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ins_modifyGrade �������� �̵�");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("in_id");
		if(id == null) {
			// id�� ������ ����
			return "/pages/error.jsp";
		}
		
		// �Է� ���� ���� �޾ƿ���
		String student_id = request.getParameter("num");
		String course_id = request.getParameter("subCourse_id");
		System.out.println(course_id);
		String subject_id = request.getParameter("subSubject_id");
		System.out.println(subject_id);
		String gr_attend = request.getParameter("attend");
		System.out.println(gr_attend);
		String gr_writing = request.getParameter("writing");
		System.out.println(gr_writing);
		String gr_practice = request.getParameter("practice");
		System.out.println(gr_practice);
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		
		// �޾ƿ� ���� Instructor ��ü�� �Է�
		Instructor ins = new Instructor();
		ins.setStudent_id(student_id);
		ins.setCourse_id(course_id);
		ins.setSubject_id(subject_id);
		ins.setGr_attend(Integer.parseInt(gr_attend));
		ins.setGr_writing(Integer.parseInt(gr_writing));
		ins.setGr_practice(Integer.parseInt(gr_practice));
		System.out.println("test1");
		
		
		// ��ü�� ���� DAO �׼� ���� -->
		int result = dao.ins_modifyGrade(ins);
		System.out.printf("%d ���� ������ ���� ����!", result);
		
		// ��� ������ �� �Ѱ��ֱ�
		if (skey == null) {
			skey = "";
		}
		List<Instructor> list1 = dao.ins_gra_subjectList(subject_id, id);
		String term = String.format("%s(%s~%s)", list1.get(0).getCourse_name(), list1.get(0).getCr_start_date(),
				list1.get(0).getCr_end_date());
		System.out.println(term);

		List<String> temp = new ArrayList<>();
		temp.add(subject_id);
		
		List<Instructor> test_list = dao.ins_testList(temp);
		
		// ���õ� ���� ���� ������ ��� ���(list2)
		List<Instructor> list2 = dao.ins_gra_studentList(skey, svalue, subject_id);
		System.out.println("���� ���� ���� ������ ����Ʈ ������:" + list2.size());
		String countSt = String.valueOf(list2.size());
		
		// 2. list�� ������
		int subSumList = list2.size();

		// 1. �л��� ������ ������ 0�� �л��� ��
		int checkPoint = 0;
		
		// ���� ��� ���� ���ڿ�
		String gradeCheckMessage = null;
		
		for(int a = 0; a < subSumList; ++a) {
			
			// �� �л��� ������ ����
			int sumList = list2.get(a).getGr_attend() + list2.get(a).getGr_writing() + list2.get(a).getGr_practice();
			
			if(sumList == 0) {
				++checkPoint;
			}
		}
		System.out.println("Ȯ��: "+checkPoint);
		if(checkPoint == 0) {
			gradeCheckMessage = "��ϿϷ�";
		} else if(checkPoint > 0 && checkPoint < subSumList) {
			gradeCheckMessage = "��� ��";
		} else {
			gradeCheckMessage = "�̵��";
		}
		request.setAttribute("gradeCheckMessage", gradeCheckMessage);

		request.setAttribute("subjectId", subject_id);
		request.setAttribute("subjectList", list1);
		request.setAttribute("term_subject", term);
		request.setAttribute("stList", list2);
		request.setAttribute("countSt", countSt);
		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("test_list", test_list);
		
		return "teacher_v/teacher_grade_detail";
	}

	// 2-13. ���� ����
	public String ins_deleteGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		System.out.println("ins_deleteGrade �������� �̵�");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("in_id");
		if(id == null) {
			// id�� ������ ����
			return "/pages/error.jsp";
		}
		
		// �Է� ���� ���� �޾ƿ���
		String student_id = request.getParameter("num");
		String course_id = request.getParameter("subCourse_id");
		String subject_id = request.getParameter("subSubject_id");
		System.out.println("1" + course_id);
		System.out.println("2" + subject_id);
		System.out.println("3" + student_id);
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		
		// �޾ƿ� ���� Instructor ��ü�� �Է�
		Instructor ins = new Instructor();
		ins.setCourse_id(course_id);
		ins.setSubject_id(subject_id);
		ins.setStudent_id(student_id);
		
		
		// ��ü�� ���� DAO �׼� ���� -->
		int result = dao.ins_deleteGrade(ins);
		System.out.printf("%d���� ������ ���� �Ϸ�", result);
		
		// ��� ������ �� �Ѱ��ֱ�
		if (skey == null) {
			skey = "";
		}
		List<Instructor> list1 = dao.ins_gra_subjectList(subject_id, id);
		String term = String.format("%s(%s~%s)", list1.get(0).getCourse_name(), list1.get(0).getCr_start_date(),
				list1.get(0).getCr_end_date());
		System.out.println(term);

		List<String> temp = new ArrayList<>();
		temp.add(subject_id);
		
		List<Instructor> test_list = dao.ins_testList(temp);
		
		// ���õ� ���� ���� ������ ��� ���(list2)
		List<Instructor> list2 = dao.ins_gra_studentList(skey, svalue, subject_id);
		System.out.println("���� ���� ���� ������ ����Ʈ ������:" + list2.size());
		String countSt = String.valueOf(list2.size());
		
		// 2. list�� ������
		int subSumList = list2.size();

		// 1. �л��� ������ ������ 0�� �л��� ��
		int checkPoint = 0;
		
		// ���� ��� ���� ���ڿ�
		String gradeCheckMessage = null;
		
		for(int a = 0; a < subSumList; ++a) {
			
			// �� �л��� ������ ����
			int sumList = list2.get(a).getGr_attend() + list2.get(a).getGr_writing() + list2.get(a).getGr_practice();
			
			if(sumList == 0) {
				++checkPoint;
			}
		}
		System.out.println("Ȯ��: "+checkPoint);
		if(checkPoint == 0) {
			gradeCheckMessage = "��ϿϷ�";
		} else if(checkPoint > 0 && checkPoint < subSumList) {
			gradeCheckMessage = "��� ��";
		} else {
			gradeCheckMessage = "�̵��";
		}
		request.setAttribute("gradeCheckMessage", gradeCheckMessage);

		request.setAttribute("subjectId", subject_id);
		request.setAttribute("subjectList", list1);
		request.setAttribute("term_subject", term);
		request.setAttribute("stList", list2);
		request.setAttribute("countSt", countSt);
		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);
		request.setAttribute("test_list", test_list);
		
		return "teacher_v/teacher_grade_detail";
	}

	// 2-14. �������� ȭ�� / ���� ������� + ������Ͽ��� Ȯ��
	public String ins_subGradeList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession sesion = request.getSession();

		String id = (String) sesion.getAttribute("in_id");
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");

		if (skey == null) {
			skey = "all";
			svalue = "";
		}
		// TEST
		System.out.printf("%s, %s, %s%n", id, skey, svalue);

		List<Instructor> list = dao.ins_subScoreList("key", id, skey, svalue);

		String count = String.valueOf(list.size());

		List<String> temp = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			temp.add(list.get(i).getSubject_id());
		}
		
		List<Instructor> test_list = dao.ins_testList(temp);
		
		
		// --> ���� �Է� ���� Ȯ�� ����
		List<String> gradeCheck = new ArrayList<>();
		
		for (int i = 0; i < list.size(); ++i) {

			// ���õ� ���� ���� ������ ��� ���(list2)
			List<Instructor> list2 = dao.ins_gra_studentList(skey, svalue, list.get(i).getSubject_id());
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
		
		request.setAttribute("svalue", svalue);
		request.setAttribute("skey", skey);
		request.setAttribute("list", list);
		request.setAttribute("listSize", list.size());		
		request.setAttribute("count", count);
		request.setAttribute("test_list", test_list);
		
		return "teacher_v/teacher_grade";
	}
}
