package com.academy.ssit.Instructor;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;
import org.json.simple.*;

import com.academy.ssit.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

public class InstructorAction {

	private InstructorDAO dao = new InstructorDAO();
	// 강사ID는 session 객체에서 받아와야 하나, 임의로 부여하여 테스트 후에 완료할 예정
	// 전역변수로 세션을 넘길려 했으나 웹 특성으로 인해 자동 삭제됨
	/* private String id = "in001"; */

	// 2-1. 강사계정 - 강의 스케쥴(강의종료, 강의예정, 강의중)
	// 강의 예정(과목ID, 과목이름, 과목시작 날짜, 과목 끝날짜, 과정이름, 과정시작날짜, 과정끝날짜, 강의실이름, 과목이름, 학생
	// 수)
	public String ins_scheduleList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("강의스케쥴 조회");

		// 검색 요청 데이터 수신 처리
		/* String skey = request.getParameter("skey"); */
		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("in_id");
		// 리스트 출력을 위해 필요한 값들 -> lid, 강사id, subKey, subValue

		// 스케쥴 구분(lid)의 초기값은 lid="sub1"(강의예정)
		String lid = request.getParameter("lid");
		if (lid == null) {
			lid = "sub1";
		}
		System.out.println(lid);

		// 강사ID은 추후 session에서 받아오는 것으로 하되,
		// 테스트를 위해서 id="in001" 초기값 지정(private)

		// 검색기준키(전체, 과목ID, 과목명)
		String subKey = request.getParameter("subKey");
		if (subKey == null) {
			subKey = "1";
		}
		System.out.println("subKey : " + subKey);
		// 검색 기준키에 따른 값
		String subValue = request.getParameter("subValue");
		System.out.println("subValue : " + subValue);

		// 데이터베이스 액션 처리 과정
		List<Instructor> list = null;
		String count = "0";
		String title = null;
		switch (lid) {
		case "sub1":
			title = "강의 예정";
			break;
		case "sub2":
			title = "강의 중";
			break;
		case "sub3":
			title = "강의 종료";
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

		// JSP페이지에 넘길 데이터 준비
		request.setAttribute("lid", lid);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("title", title);
		request.setAttribute("subKey", subKey);
		request.setAttribute("subValue", subValue);
		request.setAttribute("id", id);

		// Modal액션에 연계할 데이터 넘겨주기
		ins_studentList(request, response);

		return "teacher_v/teacher_scheduleList";
	}

	// 2-2. 강사계정 - 과정에 대한 수강생 리스트
	// 과목(강의 예정, 중, 종료)에 대한 수강생정보 출력(student_id, name, phone, 중도탈락여부, gr_attend,
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

	// 2-2(추가). 강사계정 - 과목 선택에 대한 수강생 명단 보기
	@SuppressWarnings("unchecked")
	public String teacher_scheduleList_ajaxReceive(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		// session으로 얻어올 ID 임시 부여
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("in_id");

		String keyword = request.getParameter("keyword");
		System.out.println("키워드" + keyword);
		String searchkey = request.getParameter("searchkey");
		if (searchkey == null) {
			searchkey = "";
		}
		System.out.println("키" + searchkey);
		String searchvalue = request.getParameter("searchvalue");
		if (searchvalue == null) {
			searchvalue = "";
		}
		System.out.println("값" + searchvalue);
		InstructorDAO dao = new InstructorDAO();
		List<Instructor> list = dao.ins_studentList(searchkey, searchvalue, keyword, id);
		System.out.println("사이즈 :" + list.size());

		// JSON 시작
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

	// 2-3. 강사계정 - 과목별 배점출력 + 성적등록여부 확인
	// 배점출력(과목ID, 과목명,과목시작날짜, 과목 종료날짜, 과정명, 과정시작날짜, 과정끝날짜, 강의실명, 과목명, sc_attend,
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
		
		// 검색액션 준비
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

	// 2-4. 강사계정 - 배점입력
	// 배점입력(과목ID, 출결, 필기, 실기)score(subject_id, attend_score, writing_score,
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

		// DB에 배점 입력 액션
		InstructorDAO dao = new InstructorDAO();
		dao.ins_addScore(in);

		return "redirect:ins_subScoreList";
	}

	// 2-5. 강사계정 - 배점수정
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

		// DB에 배점 입력 액션
		InstructorDAO dao = new InstructorDAO();
		dao.ins_modifyScore(in);

		return "redirect:ins_subScoreList";
	}

	// 2-6. 강사계정 - 배점삭제
	public String ins_deleteScore(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		String subject_id = request.getParameter("subject_id3");

		// DB에 배점 입력 액션
		InstructorDAO dao = new InstructorDAO();
		dao.ins_deleteScore(subject_id);

		return "redirect:ins_subScoreList";
	}

	// 2-7. 강사계정 - 시험등록
	// 시험등록(과목ID, 시험날짜, 시험문제)test(subject_id, test_date, test_paper)
	public String ins_addTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 파일 업로드 액션
		String url = "redirect:ins_subScoreList";
		try {
			// 수신 데이터 -> 물리적 데이터 파일, 파일 정보(이름, 사이즈, 종류)

			// picture 폴더에 대한 물리적 절대 경로 확인
			// ->주의. 이클립스가 프로젝트 관리를 위해서 만든 경로가 아님. 톰캣 서버가 운영하는 전용 폴더임.
			String savePath = request.getServletContext().getRealPath("test_files");
			System.out.println(savePath);

			// picture 폴더가 존재하지 않는 경우 동적 생성
			java.io.File f = new java.io.File(savePath);
			if (!f.exists()) {
				f.mkdir();
			}

			// 업로드 파일의 크기 제한
			int sizeLimit = 1024 * 500; // 1024byte -> 1K byte -> 1024K byte
										// ->
										// 1M byte

			// 업로드 파일에 대한 물리적 처리 및 정보 확인을 위한 객체 생성
			// 업로드 파일에 대한 물리적 저장
			// 동일 파일 이름을 가진 파일 업로드에 대비해서 파일 이름 동적 생성
			MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
					new MyFileRenamePolicy());

			// 업로드 파일에 대한 정보 확인
			String filesystemName = multi.getFilesystemName("fileName");
			String contentType = multi.getContentType("fileName");
			String originalFileName = multi.getOriginalFileName("fileName");

			// employeeid 정보를 얻는 과정은 request.getParameter()이 아니고,
			// multi.getParameter() 이다.
			String subject_id = multi.getParameter("subject_id4");
			String test_date = multi.getParameter("exam_date1");
			String instructor_id = multi.getParameter("instructor_id");

			// 신규등록여부 확인키
			String picturekey = "0";
			/*
			 * System.out.printf("getContentType: %s %n", filesystemName);
			 * System.out.printf("getFilesystemName: %s %n", contentType);
			 * System.out.printf("getOriginalFileName: %s %n",
			 * originalFileName);
			 */

			System.out.println(contentType);
			
			// 사진 -> image/jpeg, image/png
			if (!(contentType.equals("application/x-zip-compressed"))) {

				// 물리적으로 업로드된 파일에 대한 삭제 액션 추가
				String deleteFilename = savePath + "\\" + filesystemName;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();
				// System.out.println("deleteFilename : " + deleteFilename);

				// 예외 발생시 -> 업로드된 파일에 대한 처리 부족
				throw new Exception("zip 파일이 아니거나 잘못된 파일 입니다.");
			}

			// 데이터베이스에 정보 저장
			// ->파일이름(신규 파일명, filesystemName), 사진 설명(comment)
			// ->예외 처리 추가
			Instructor in = new Instructor();
			in.setSubject_id(subject_id);
			in.setTest_date(test_date);
			in.setTest_paper(filesystemName);
			in.setTest_paper_name(originalFileName);
			//in.setTest_paper_name(originalFileName);
			
			
			InstructorDAO dao = new InstructorDAO();
			int result = 0;
			if (picturekey.equals("0")) {
				// 시험정보 입력 액션
				result = dao.ins_addTest(in);
				System.out.println("ins_addTest 메소드 호출");
			} else {
				// 기존에 입력되어 있던 사진 파일명 불러오기
				String test_paper = dao.ins_subScoreList("", instructor_id, "subject_id", subject_id).get(0).getTest_paper();

				// 물리적으로 업로드된 파일에 대한 삭제 액션 추가
				String deleteFilename = savePath + "\\" + test_paper;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();

				// 시험 파일 수정
				// result = dao.pictureModify(emp);
				// System.out.println("pictureModify 메소드 호출");
			}

			if (result == 0) {
				String deleteFilename = savePath + "\\" + filesystemName;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();
				throw new Exception("업로드된 파일의 정보를 데이터베이스에 등록하지 못했습니다.");
			}
		} catch (Exception e) {
			url = "/error";
			e.printStackTrace();
		}
		// 결과 반환->페이지 이동 정보->JSP 페이지
		return url; // ins_subScoreList.jsp
	}

	// 2-8. 강사계정 - 시험수정
	public String ins_modifyTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		System.out.println("ins_modifyTest 페이지로 이동");
		request.setCharacterEncoding("UTF-8");

		// 파일 업로드 액션
		String url = "redirect:ins_subScoreList";
		try {
			// 수신 데이터 -> 물리적 데이터 파일, 파일 정보(이름, 사이즈, 종류)

			// picture 폴더에 대한 물리적 절대 경로 확인
			// ->주의. 이클립스가 프로젝트 관리를 위해서 만든 경로가 아님. 톰캣 서버가 운영하는 전용 폴더임.
			String savePath = request.getServletContext().getRealPath("test_files");
			System.out.println(savePath);

			// picture 폴더가 존재하지 않는 경우 동적 생성
			java.io.File f = new java.io.File(savePath);
			if (!f.exists()) {
				f.mkdir();
			}

			// 업로드 파일의 크기 제한
			int sizeLimit = 1024 * 500; // 1024byte -> 1K byte -> 1024K byte
										// ->
										// 1M byte

			// 업로드 파일에 대한 물리적 처리 및 정보 확인을 위한 객체 생성
			// 업로드 파일에 대한 물리적 저장
			// 동일 파일 이름을 가진 파일 업로드에 대비해서 파일 이름 동적 생성
			MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
					new MyFileRenamePolicy());

			// 업로드 파일에 대한 정보 확인
			String filesystemName = multi.getFilesystemName("exam_file2");
			String contentType = multi.getContentType("exam_file2");
			String originalFileName = multi.getOriginalFileName("exam_file2");

			// employeeid 정보를 얻는 과정은 request.getParameter()이 아니고,
			// multi.getParameter() 이다.
			String subject_id = multi.getParameter("subject_id5");
			String test_date = multi.getParameter("exam_date2");
			String instructor_id = multi.getParameter("instructor_id");

			// 신규등록여부 확인키
			String picturekey = "0";
			/*
			 * System.out.printf("getContentType: %s %n", filesystemName);
			 * System.out.printf("getFilesystemName: %s %n", contentType);
			 * System.out.printf("getOriginalFileName: %s %n",
			 * originalFileName);
			 */

			System.out.println(contentType);
			
			// 사진 -> image/jpeg, image/png
			if (!(contentType.equals("application/x-zip-compressed"))) {

				// 물리적으로 업로드된 파일에 대한 삭제 액션 추가
				String deleteFilename = savePath + "\\" + filesystemName;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();
				// System.out.println("deleteFilename : " + deleteFilename);

				// 예외 발생시 -> 업로드된 파일에 대한 처리 부족
				throw new Exception("zip 파일이 아니거나 잘못된 파일 입니다.");
			}

			// 데이터베이스에 정보 저장
			// ->파일이름(신규 파일명, filesystemName), 사진 설명(comment)
			// ->예외 처리 추가
			Instructor in = new Instructor();
			in.setSubject_id(subject_id);
			in.setTest_date(test_date);
			in.setTest_paper(filesystemName);
			in.setTest_paper_name(originalFileName);
			//in.setTest_paper_name(originalFileName);
			
			
			InstructorDAO dao = new InstructorDAO();
			int result = 0;
			if (picturekey.equals("0")) {
				// 시험정보 입력 액션
				result = dao.ins_modifyTest(in);
			} else {
				// 기존에 입력되어 있던 사진 파일명 불러오기
				String test_paper = dao.ins_subScoreList("", instructor_id, "subject_id", subject_id).get(0).getTest_paper();

				// 물리적으로 업로드된 파일에 대한 삭제 액션 추가
				String deleteFilename = savePath + "\\" + test_paper;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();

				// 시험 파일 수정
				// result = dao.pictureModify(emp);
				// System.out.println("pictureModify 메소드 호출");
			}

			if (result == 0) {
				String deleteFilename = savePath + "\\" + filesystemName;
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();
				throw new Exception("업로드된 파일의 정보를 데이터베이스에 등록하지 못했습니다.");
			}
		} catch (Exception e) {
			url = "/error";
			e.printStackTrace();
		}
	
		return "redirect:ins_subScoreList";
	}

	// 2-9. 강사계정 - 시험삭제
	public String ins_deleteTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		System.out.println("ins_deleteTest 페이지로 이동");
		request.setCharacterEncoding("UTF-8");
			
		String subject_id = request.getParameter("subject_id6");
		String exam_date = request.getParameter("exam_date3");
		String examfile = request.getParameter("exam_file3");
		
		Instructor ins = new Instructor();
		ins.setSubject_id(subject_id);
		ins.setTest_date(exam_date);
		ins.setTest_paper(examfile);

		//DB 삭제 액션
		int result = dao.ins_deleteTest(ins);
		if(result==0){
			System.out.println("DB에서 삭제 실패 ");
		}else{
			System.out.println("DB에서 삭제 성공! ");
		}
		
		
//		// 기존에 입력되어 있던 사진 파일명 불러오기
//		String test_paper = dao.ins_subScoreList("", instructor_id, "subject_id", subject_id).get(0).getTest_paper();
//
//		// 물리적으로 업로드된 파일에 대한 삭제 액션 추가
//		String deleteFilename = savePath + "\\" + test_paper;
//		java.io.File temp = new java.io.File(deleteFilename);
//		temp.delete();
//		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8",
//				new MyFileRenamePolicy());
//
//	
//		// 업로드 파일에 대한 정보 확인
//		// 기존에 입력되어 있던 사진 파일명 불러오기
//		String filesystemName = multi.getFilesystemName("fileName");
//		String originalFileName = multi.getOriginalFileName("fileName");
//		//물리적 파일 삭제
//		String deleteFilename = savePath + "\\" + filesystemName;
//		java.io.File temp = new java.io.File(deleteFilename);
//		temp.delete();
		
		// 물리적으로 업로드된 파일에 대한 삭제 액션 추가
		String savePath = request.getServletContext().getRealPath("test_files");
		
		String deleteFilename = savePath + "\\" + examfile;
		java.io.File temp = new java.io.File(deleteFilename);
		temp.delete();
		
		
		request.setAttribute("subject_id5", subject_id);
		request.setAttribute("exam_date2", exam_date);
		request.setAttribute("exam_file2", examfile);
		
		return "redirect:ins_subScoreList";
	}

	// 2-10(추가). 강사계정 - 특정 과목 선택 시, 선택된 과목의 정보 표기
		// 페이지가 전환되면서 동일한 액션이 추가됨.
		public String ins_gra_subjectList(HttpServletRequest request, HttpServletResponse response)
				throws SecurityException, IOException {
			System.out.println("ins_gra_subjectList 페이지로 이동");
			request.setCharacterEncoding("UTF-8");

			HttpSession sesion = request.getSession();
			String id = (String) sesion.getAttribute("in_id");
			if (id == null) {
				// id가 없으면 에러
				return "/pages/error.jsp";
			}

			// 선택된 과목 정보 출력(list1)
			// 받아오는 값 = subjectId, skey, svalue, id
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

			// 선택된 과목에 대한 수강생 목록 출력(list2)
			List<Instructor> list2 = dao.ins_gra_studentList(skey, svalue, subjectId);
			System.out.println("선택 과목에 대한 수강생 리스트 사이즈:" + list2.size());
			String countSt = String.valueOf(list2.size());
			
			
			// 1. 학생의 성적의 총합이 0인 학생의 수
			// 2. list의 사이즈
			// 1과 2를 비교하여
			// case1. 1의 항목이 0인 경우 :  등록 완료
			// case1. 1의 항목이 0 이상 (2의 항목) 미만인 경우 :  등록 중
			// case1. 1의 항목 = 2의 항목 :  미등록
			
			// 2. list의 사이즈
			int subSumList = list2.size();

			// 1. 학생의 성적의 총합이 0인 학생의 수
			int checkPoint = 0;
			
			// 성적 등록 여부 문자열
			String gradeCheckMessage = null;
			
			for(int a = 0; a < subSumList; ++a) {
				
				// 한 학생의 성적의 총합
				int sumList = list2.get(a).getGr_attend() + list2.get(a).getGr_writing() + list2.get(a).getGr_practice();
				
				if(sumList == 0) {
					++checkPoint;
				}
			}
			System.out.println("확인: "+checkPoint);
			if(checkPoint == 0) {
				gradeCheckMessage = "등록완료";
			} else if(checkPoint > 0 && checkPoint < subSumList) {
				gradeCheckMessage = "등록 중";
			} else {
				gradeCheckMessage = "미등록";
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

	// 2-10. 강사계정 - 특정 과목 수강중인 수강생 정보 출력(성적, 수료여부)student_id, name, phone,
	// failcheck, gr_attend, gr_writing, gr_practice
	public String ins_gra_studentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return "redirect:";
	}

	// 2-11. 성적 입력(subject_id, course_id, student_id, attend_score,
	// writing_score, practice_score)
	public String ins_addGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ins_addGrade 페이지로 이동");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("in_id");
		if(id == null) {
			// id가 없으면 에러
			return "/pages/error.jsp";
		}
		
		// 입력 받은 값을 받아오기
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
		
		// 받아온 값을 Instructor 객체에 입력
		Instructor ins = new Instructor();
		ins.setStudent_id(student_id);
		ins.setCourse_id(course_id);
		ins.setSubject_id(subject_id);
		ins.setGr_attend(Integer.parseInt(gr_attend));
		ins.setGr_writing(Integer.parseInt(gr_writing));
		ins.setGr_practice(Integer.parseInt(gr_practice));
		System.out.println("test1");
		
		
		// 객체를 통해 DAO 액션 진행 -->
		int result = dao.ins_addGrade(ins);
		System.out.printf("%d 개의 데이터 입력 성공!", result);
		
		// 출력 데이터 값 넘겨주기
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
		
		// 선택된 과목에 대한 수강생 목록 출력(list2)
		List<Instructor> list2 = dao.ins_gra_studentList(skey, svalue, subject_id);
		System.out.println("선택 과목에 대한 수강생 리스트 사이즈:" + list2.size());
		String countSt = String.valueOf(list2.size());
		
		// 2. list의 사이즈
		int subSumList = list2.size();

		// 1. 학생의 성적의 총합이 0인 학생의 수
		int checkPoint = 0;
		
		// 성적 등록 여부 문자열
		String gradeCheckMessage = null;
		
		for(int a = 0; a < subSumList; ++a) {
			
			// 한 학생의 성적의 총합
			int sumList = list2.get(a).getGr_attend() + list2.get(a).getGr_writing() + list2.get(a).getGr_practice();
			
			if(sumList == 0) {
				++checkPoint;
			}
		}
		System.out.println("확인: "+checkPoint);
		if(checkPoint == 0) {
			gradeCheckMessage = "등록완료";
		} else if(checkPoint > 0 && checkPoint < subSumList) {
			gradeCheckMessage = "등록 중";
		} else {
			gradeCheckMessage = "미등록";
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

	// 2-12. 성적 수정
	public String ins_modifyGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		System.out.println("ins_modifyGrade 페이지로 이동");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("in_id");
		if(id == null) {
			// id가 없으면 에러
			return "/pages/error.jsp";
		}
		
		// 입력 받은 값을 받아오기
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
		
		// 받아온 값을 Instructor 객체에 입력
		Instructor ins = new Instructor();
		ins.setStudent_id(student_id);
		ins.setCourse_id(course_id);
		ins.setSubject_id(subject_id);
		ins.setGr_attend(Integer.parseInt(gr_attend));
		ins.setGr_writing(Integer.parseInt(gr_writing));
		ins.setGr_practice(Integer.parseInt(gr_practice));
		System.out.println("test1");
		
		
		// 객체를 통해 DAO 액션 진행 -->
		int result = dao.ins_modifyGrade(ins);
		System.out.printf("%d 개의 데이터 수정 성공!", result);
		
		// 출력 데이터 값 넘겨주기
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
		
		// 선택된 과목에 대한 수강생 목록 출력(list2)
		List<Instructor> list2 = dao.ins_gra_studentList(skey, svalue, subject_id);
		System.out.println("선택 과목에 대한 수강생 리스트 사이즈:" + list2.size());
		String countSt = String.valueOf(list2.size());
		
		// 2. list의 사이즈
		int subSumList = list2.size();

		// 1. 학생의 성적의 총합이 0인 학생의 수
		int checkPoint = 0;
		
		// 성적 등록 여부 문자열
		String gradeCheckMessage = null;
		
		for(int a = 0; a < subSumList; ++a) {
			
			// 한 학생의 성적의 총합
			int sumList = list2.get(a).getGr_attend() + list2.get(a).getGr_writing() + list2.get(a).getGr_practice();
			
			if(sumList == 0) {
				++checkPoint;
			}
		}
		System.out.println("확인: "+checkPoint);
		if(checkPoint == 0) {
			gradeCheckMessage = "등록완료";
		} else if(checkPoint > 0 && checkPoint < subSumList) {
			gradeCheckMessage = "등록 중";
		} else {
			gradeCheckMessage = "미등록";
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

	// 2-13. 성적 삭제
	public String ins_deleteGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		System.out.println("ins_deleteGrade 페이지로 이동");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession sesion = request.getSession();
		String id = (String) sesion.getAttribute("in_id");
		if(id == null) {
			// id가 없으면 에러
			return "/pages/error.jsp";
		}
		
		// 입력 받은 값을 받아오기
		String student_id = request.getParameter("num");
		String course_id = request.getParameter("subCourse_id");
		String subject_id = request.getParameter("subSubject_id");
		System.out.println("1" + course_id);
		System.out.println("2" + subject_id);
		System.out.println("3" + student_id);
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		
		// 받아온 값을 Instructor 객체에 입력
		Instructor ins = new Instructor();
		ins.setCourse_id(course_id);
		ins.setSubject_id(subject_id);
		ins.setStudent_id(student_id);
		
		
		// 객체를 통해 DAO 액션 진행 -->
		int result = dao.ins_deleteGrade(ins);
		System.out.printf("%d개의 데이터 삭제 완료", result);
		
		// 출력 데이터 값 넘겨주기
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
		
		// 선택된 과목에 대한 수강생 목록 출력(list2)
		List<Instructor> list2 = dao.ins_gra_studentList(skey, svalue, subject_id);
		System.out.println("선택 과목에 대한 수강생 리스트 사이즈:" + list2.size());
		String countSt = String.valueOf(list2.size());
		
		// 2. list의 사이즈
		int subSumList = list2.size();

		// 1. 학생의 성적의 총합이 0인 학생의 수
		int checkPoint = 0;
		
		// 성적 등록 여부 문자열
		String gradeCheckMessage = null;
		
		for(int a = 0; a < subSumList; ++a) {
			
			// 한 학생의 성적의 총합
			int sumList = list2.get(a).getGr_attend() + list2.get(a).getGr_writing() + list2.get(a).getGr_practice();
			
			if(sumList == 0) {
				++checkPoint;
			}
		}
		System.out.println("확인: "+checkPoint);
		if(checkPoint == 0) {
			gradeCheckMessage = "등록완료";
		} else if(checkPoint > 0 && checkPoint < subSumList) {
			gradeCheckMessage = "등록 중";
		} else {
			gradeCheckMessage = "미등록";
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

	// 2-14. 성적관리 화면 / 과목별 배점출력 + 성적등록여부 확인
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
		
		
		// --> 성적 입력 여부 확인 시작
		List<String> gradeCheck = new ArrayList<>();
		
		for (int i = 0; i < list.size(); ++i) {

			// 선택된 과목에 대한 수강생 목록 출력(list2)
			List<Instructor> list2 = dao.ins_gra_studentList(skey, svalue, list.get(i).getSubject_id());
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
		
		request.setAttribute("svalue", svalue);
		request.setAttribute("skey", skey);
		request.setAttribute("list", list);
		request.setAttribute("listSize", list.size());		
		request.setAttribute("count", count);
		request.setAttribute("test_list", test_list);
		
		return "teacher_v/teacher_grade";
	}
}
