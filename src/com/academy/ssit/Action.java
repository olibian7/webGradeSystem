package com.academy.ssit;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.academy.ssit.Instructor.*;
import com.academy.ssit.admin.*;
import com.academy.ssit.student.*;

public class Action {
	// public String student_info(HttpServletRequest request,
	// HttpServletResponse response)throws SecurityException, IOException{
	//
	// String id = request.getParameter("id");
	//
	// StudentDAO dao = new StudentDAO();
	// List<Student> list = dao.studentInfoList("", "st001");
	//
	// request.setAttribute("list", list);
	//
	// return "pages/student_v/student_info";
	// }

	private StudentAction stAction = new StudentAction();
	private InstructorAction inAction = new InstructorAction();
	private AdminAction1 adAction1 = new AdminAction1();
	private AdminAction2 adAction2 = new AdminAction2();
	private AdminAction3 adAction3 = new AdminAction3();
	private AdminAction4 adAction4 = new AdminAction4();
	private AdminAction5 adAction5 = new AdminAction5();
	private AdminAction6 adAction6 = new AdminAction6();
	private Login login = new Login();

	// =======================================1.
	// 수강생=====================================

	// 1-1. 수강생계정 - 수강생정보출력(아이디, 이름, 민번, 전번, 등록일)
	public String st_studentInfo(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.st_studentInfo(request, response);
	}

	// 1-2. 수강생계정 - 수강생 정보 수정(이름, 민번, 전번 수정가능)
	public String st_modifyStudentInfo(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.st_modifyStudentInfo(request, response);
	}

	// 1-3. 수강생계정 - 수강생이 수강하는 과정조회(과정ID, 과정명, 과정기간)
	public String st_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.st_courseList(request, response);
	}

	// 1-4. 수강생계정 - 학생성적 출력(과목ID 과목명 과목기간 교재명 강사명 출결(점수/배점) 필기(점수/배점) 실기(점수/배점)
	// 시험날짜 시험문제)
	public String st_gradeList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.st_gradeList(request, response);
	}

	// =======================================2.
	// 강사=====================================

	// 2-1. 강사계정 - 강의 스케쥴(강의종료, 강의예정, 강의중)
	// 강의 예정(과목ID, 과목이름, 과목시작 날짜, 과목 끝날짜, 과정이름, 과정시작날짜, 과정끝날짜, 강의실이름, 과목이름, 학생
	// 수)
	public String ins_scheduleList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_scheduleList(request, response);
	}

	// 2-2. 강사계정 - 과목에 대한 수강생 리스트
	// 과목(강의 예정, 중, 종료)에 대한 수강생정보 출력(student_id, name, phone, 중도탈락여부, gr_attend,
	// gr_writing, gr_practice)
	public String ins_studentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_studentList(request, response);
	}

	// 2-2(추가). 강사계정 - 과목 선택 시 수강생 명단 보기
	public String teacher_scheduleList_ajaxReceive(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.teacher_scheduleList_ajaxReceive(request, response);
	}

	// 2-3. 강사계정 - 과목별 배점출력 + 성적등록여부 확인
	// 배점출력(과목ID, 과목명,과목시작날짜, 과목 종료날짜, 과정명, 과정시작날짜, 과정끝날짜, 강의실명, 과목명, sc_attend,
	// sc_writing, sc_practice, gradecheck)
	public String ins_subScoreList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_subScoreList(request, response);
	}

	// 2-4. 강사계정 - 배점입력
	// 배점입력(과목ID, 출결, 필기, 실기)score(subject_id, attend_score, writing_score,
	// practice_score)
	public String ins_addScore(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_addScore(request, response);
	}

	// 2-5. 강사계정 - 배점수정
	public String ins_modifyScore(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_modifyScore(request, response);
	}

	// 2-6. 강사계정 - 배점삭제
	public String ins_deleteScore(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_deleteScore(request, response);
	}

	// 2-7. 강사계정 - 시험등록
	// 시험등록(과목ID, 시험날짜, 시험문제)test(subject_id, test_date, test_paper)
	public String ins_addTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_addTest(request, response);
	}

	// 2-8. 강사계정 - 시험수정
	public String ins_modifyTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_modifyTest(request, response);
	}

	// 2-9. 강사계정 - 시험삭제
	public String ins_deleteTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_deleteTest(request, response);
	}

	public String ins_gra_subjectList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_gra_subjectList(request, response);
	}

	// 2-10. 강사계정 - 특정 과목 수강중인 수강생 정보 출력(성적, 수료여부)student_id, name, phone,
	// failcheck, gr_attend, gr_writing, gr_practice
	public String ins_gra_studentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_gra_studentList(request, response);
	}

	// 2-11. 성적 입력(subject_id, course_id, student_id, attend_score,
	// writing_score, practice_score)
	public String ins_addGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_addGrade(request, response);
	}

	// 2-12. 성적 수정
	public String ins_modifyGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_modifyGrade(request, response);
	}

	// 2-13. 성적 삭제
	public String ins_deleteGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_deleteGrade(request, response);
	}

	// 2-14. 강사계정 -성적관리- 과목별 배점출력 + 성적등록여부 확인
	// 배점출력(과목ID, 과목명,과목시작날짜, 과목 종료날짜, 과정명, 과정시작날짜, 과정끝날짜, 강의실명, 과목명, sc_attend,
	// sc_writing, sc_practice, gradecheck)
	public String ins_subGradeList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_subGradeList(request, response);
	}

	// =======================================3.
	// 관리자=====================================

	// 3-1-1. 관리자계정 - 기초정보관리>과정명입력(과정명ID, 과정명)
	public String ad_info_addCourseName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_addCourseName(request, response);
	}

	// 3-1-1. 관리자계정 - 기초정보관리>과정수정(과정명ID, 과정명)
	public String ad_info_modifyCourseName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_modifyCourseName(request, response);
	}

	// 3-1-1. 관리자계정 - 기초정보관리>과정삭제(과정명ID, 과정명)
	public String ad_info_deleteCourseName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_deleteCourseName(request, response);
	}

	// 3-1-2. 관리자계정 - 기초정보관리>과정명리스트 출력(과정명ID, 과정명)
	public String ad_info_courseNameList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_courseNameList(request, response);
	}

	// 3-1-3. 관리자계정 - 기초정보관리>과목명입력(과목명ID, 과목명)
	public String ad_info_addSubName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_addSubName(request, response);
	}

	// 3-1-3. 관리자계정 - 기초정보관리>과목명수정(과목명ID, 과목명)
	public String ad_info_modifySubName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_modifySubName(request, response);
	}

	// 3-1-3. 관리자계정 - 기초정보관리>과목명삭제(과목명ID, 과목명)
	public String ad_info_deleteSubName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_deleteSubName(request, response);
	}

	// 3-1-4. 관리자계정 - 기초정보관리>과목명리스트 출력(과목명ID, 과목명)
	public String ad_info_subjectNameInfoList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_subjectNameInfoList(request, response);
	}

	// 3-1-5. 관리자계정 - 기초정보관리>강의실명입력(강의실명ID, 강의실명, 정원)
	public String ad_info_addClassroom(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_addClassroom(request, response);
	}

	// 3-1-6. 관리자계정 - 기초정보관리>강의실명리스트 출력(강의실명ID, 강의실명, 정원)
	public String ad_info_classroomList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_classroomList(request, response);
	}

	// 3-1-6. 관리자계정 - 기초정보관리>강의실명리스트 삭제(강의실명ID, 강의실명, 정원)
	public String ad_info_deleteClassroom(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_deleteClassroom(request, response);
	}

	// 3-1-6. 관리자계정 - 기초정보관리>강의실명리스트 수정(강의실명ID, 강의실명, 정원)
	public String ad_info_modiftyClassroom(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_modiftyClassroom(request, response);
	}

	// 3-1-7. 관리자계정 - 기초정보관리>교재명입력(교재명ID, 교재명, 출판사)
	public String ad_info_addBook(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_addBook(request, response);
	}

	// 3-1-8. 관리자계정 - 기초정보관리>교재명리스트 출력(교재명ID, 교재명, 출판사)
	public String ad_info_bookList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_bookList(request, response);
	}

	// 3-1-9. 관리자계정 - 기초정보관리>교재사진 출력(교재사진)
	public String admin_basic_info_book_picture_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.admin_basic_info_book_picture_ajax(request, response);
	}

	// 관리자계정 - 기초정보관리>교재명삭제(교재명ID, 교재명, 출판사)
	public String ad_info_deleteBookName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_deleteBookName(request, response);
	}

	// =======================================================================================

	// 3-2-9. 관리자계정 - 강사계정관리>강사정보등록(강사ID, 이름, 주민등록뒷번호, 전화번호), 강의가능과목 등록(강사ID,
	// 과목명ID)
	public String ad_ins_addInstructor(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_addInstructor(request, response);
	}

	public String admin_teacher_subjectAjax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_subjectAjax(request, response);
	}

	// 3-2-10. 관리자계정 - 강사계정관리>강사정보출력(강사ID, 이름, 주민등록뒷번호, 전화번호, 강의가능과목)
	public String ad_ins_instructorList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_instructorList(request, response);
	}

	// 3-2-11. 관리자계정 - 강사계정관리> 강사정보출력검색(강사이름, 과목명, 과목시작날짜&끝날짜, 과정명, 과정시작날짜&끝날짜,
	// 교재명, 강의실명, 강의진행여부 )
	public String ad_ins_instructorList_detail(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_instructorList_detail(request, response);
	}

	// 3-2-12. 관리자계정 - 강사계정관리> 강사계정관리> 강사정보삭제(강의가능과목에서도 삭제)
	public String ad_ins_deleteInstructor(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_deleteInstructor(request, response);
	}

	// 3-2-13. 관리자계정 - 강사계정관리>강사정보수정이름, 주민등록뒷번호, 전화번호, 강의가능과목)
	public String ad_ins_modify(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_modifyInstructor(request, response);
	}

	// =======================================================================================

	// 3-3-13. 관리자계정 - 개설과정관리>과정명리스트 출력(과정명ID, 과정명)
	// public String ad_cr_courseNameList(HttpServletRequest request,
	// HttpServletResponse response)
	// throws SecurityException, IOException {
	//
	// return adAction3.ad_cr_courseNameList(request, response);
	// }

	// 3-3-15. 관리자계정 - 개설과정관리>과정등록>과정ID 부여(강의실 정보 받은 후에 진행)
	public String ad_cr_addCourseId(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_addCourseId(request, response);
	}

	// 3-3-16. 관리자계정 - 개설과정관리>과정등록>신규과정등록
	public String ad_cr_addCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_addCourse(request, response);
	}

	// 3-3-21. 관리자계정 - 개설과정관리>과정수정
	public String ad_cr_modifyCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_modifyCourse(request, response);
	}

	// 3-3-22. 관리자계정 - 개설과정관리>과정수정
	public String ad_cr_deleteCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_deleteCourse(request, response);
	}

	// 3-3-17. 관리자계정 - 개설과정관리>과정 정보 출력(과정ID, 과정명, 과정시작&끝날짜, 강의실명, 등록한 수강생수, 정원,
	// 등록된 과목수)
	public String ad_cr_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_courseList(request, response);
	}

	// 3-3-18. 관리자계정 - 모달)개설과정관리>과정 상세>과목 정보 출력(과목ID, 과목명, 과목시작&끝날짜, 교재명, 강사명,
	// 정원,
	// 등록된 과목수)
	public String admin_course_ajaxReceive(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.admin_course_ajaxReceive(request, response);
	}

	// 3-3-19. 관리자계정 - 모달)개설과정관리>과정 상세>수강생 정보 출력(수강생ID, 수강생이름, 전화번호, 등록일자,
	// 중도탈락날짜)
	public String admin_course_ajaxReceive2(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.admin_course_ajaxReceive2(request, response);
	}

	// 3-3-20. 관리자계정 - 개설과정관리> 오늘날짜 출력하여 과정개설날짜(시작, 끝) 검증
	public String ad_cr_dateCheck(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_dateCheck(request, response);
	}

	// =======================================================================================

	// 3-4-22. 관리자계정 - 개설과목관리> 과정리스트 출력(과정ID, 과정명, 과정기간, 등록과목수)
	public String ad_sj_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction4.ad_sj_courseList(request, response);
	}

	// 3-4-23. 관리자계정 - 개설과목관리>과목리스트 출력(과목ID, 과목명, 과목기간, 과정ID, 과정명, 과정기간, 교재명,
	// 강사명
	// )
	public String ad_sj_subjectList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction4.ad_sj_subjectList(request, response);
	}

	// 3-4-24. 관리자계정 - 개설과목관리>과목 신규 생성
	public String ad_sj_addSubject(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction4.ad_sj_addSubject(request, response);
	}

	// 3-4-24. 관리자계정 - 개설과목관리>과목삭제
	public String ad_sj_deleteSubject(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction4.ad_sj_deleteSubject(request, response);
	}

	// 3-4-25. 관리자계정 - 개설과목관리>과목수정
	public String ad_sj_modifySubject(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction4.ad_sj_modifySubject(request, response);
	}

	// =======================================================================================

	// 3-5-25. 관리자계정 - 학생관리>학생정보등록(학생ID, 이름, 주민번호, 핸드폰)
	public String ad_st_addStudent(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.ad_st_addStudent(request, response);
	}

	// 3-5-26. 관리자계정 - 학생관리>전체검색>수강생 전체리스트 출력(학생ID, 이름, 주민번호, 핸드폰, 등록일, 수강신청횟수)
	public String ad_st_allStudentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.ad_st_allStudentList(request, response);
	}

	// 3-5-27. 관리자계정 - 학생관리>학생 과정 등록 시 과정 목록 출력(과정Id, 과정명, 과정기간, 강의실, 수용인원,
	// 등록과목수)
	public String ad_st_studentCourseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.ad_st_studentCourseList(request, response);
	}

	// 3-5-33. 관리자계정 - 학생관리>학생 과정 등록
	public String ad_st_add_studentCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		return adAction5.ad_st_add_studentCourse(request, response);
	}
	
	// 3-5-34. 관리자계정 - 학생관리>학생 과정 등록 취소
	public String ad_st_delete_studentCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		return adAction5.ad_st_delete_studentCourse(request, response);
	}
	
	// 3-5-28. 관리자계정 - 학생관리>과정입력(과정ID, 학생ID)
	// public String ad_st_registerCourse(HttpServletRequest request,
	// HttpServletResponse response)
	// throws SecurityException, IOException {
	//
	// return adAction5.ad_st_registerCourse(request, response);
	// }

	// 3-5-29. 관리자계정 - 학생관리>수강생 개인정보 출력(과정명, 과정기간, 강의실명, 중도탈락일)
	public String ad_st_studentInfo(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.ad_st_studentInfo(request, response);
	}

	// 3-5-30. 관리자계정 - 학생관리>과정검색>관리자 과정 전체 출력(과정ID, 과정명, 과정기간, 강의실)
	public String ad_st_allCourseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.ad_st_allCourseList(request, response);
	}

	// ajax
	public String admin_student_coursesearch_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.admin_student_coursesearch_ajax(request, response);
	}

	/*
	 * // 3-5-31. 관리자계정 - 학생관리>과정검색>수강생 이름 검색(학생ID, 이름, 주민등록번호, 전화번호, 등록일자,
	 * 중도탈락날짜) public String ad_st_courseStudentList(HttpServletRequest request,
	 * HttpServletResponse response) throws SecurityException, IOException {
	 * 
	 * return adAction5.ad_st_courseStudentList(request, response); }
	 */
	// =======================================================================================

	// 3-6-32. 관리자계정 - 성적조회>과목기준조회>과정 목록 출력(과정ID, 과정명, 과정기간)
	public String ad_gr_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction6.ad_gr_courseList(request, response);
	}

	// 3-6-33. 관리자계정 - 성적조회>과목기준조회>특정 과정 정보 출력(과목ID, 과목명, 과목기간, 강사명, 성적입력여부,
	// 시험지등록여부, 강의실, 교재명, 과정명, 과정기간)
	public String ad_gr_courseSubjectList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction6.ad_gr_courseSubjectList(request, response);
	}

	// 3-6-34. 관리자계정 - 성적조회>과목기준조회>특정 과목을 듣는 수강생 정보 출력(학생ID, 이름, 전화번호, 출결, 필기,
	// 실기)
	public String ad_gr_subStudentlist(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction6.ad_gr_subStudentlist(request, response);
	}

	// 3-35. 관리자계정 - 성적조회>학생기준조회>전체 학생 리스트 출력(학생ID, 이름, 주민번호, 전화번호, 등록일자)
	public String ad_gr_allStudentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction6.ad_gr_allStudentList(request, response);
	}

	// 3-36. 관리자계정 - 성적조회>학생기준조회>학생의 과정 리스트 출력(과정ID, 과정명, 기간)
	public String ad_gr_studentCourseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		return adAction6.ad_gr_studentCourseList(request, response);
	}

	// 3-6-37. 관리자계정 - 성적조회>학생기준조회>특정 수강생이 수강한 과정 안에 있는 과목 정보 + 성적 출력(학생ID, 이름,
	// 전화번호, 과목ID, 과목명, 과목기간, 출결, 필기, 실기)
	// public String ad_gr_studentGrade(HttpServletRequest request,
	// HttpServletResponse response)
	// throws SecurityException, IOException {
	//
	// return adAction6.ad_gr_studentGrade(request, response);
	// }

	// 관리자계정 - 성적조회>과목기준조회>특정과목을 듣는 학생들 ajax처리
	public String admin_grade_course_detail_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction6.admin_grade_course_detail_ajax(request, response);
	}

	// 관리자계정 - 성적조회>과목기준조회>특정과목을 듣는 학생들 ajax처리
	public String ad_gr_studentGrade_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		return adAction6.ad_gr_studentGrade_ajax(request, response);
	}

	public String login(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		String result = login.loginCheck(request, response);

		return result;
	}

	public String loginform(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		/*
		 * String id = request.getParameter("id");
		 * request.setAttribute("user_id", id);
		 */

		return "login-form";
	}

	public String logOut(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		HttpSession session = request.getSession();
		/*
		 * String user_id = (String)session.getAttribute("user_id"); String
		 * in_id = (String)session.getAttribute("in_id"); String admin_id =
		 * (String)session.getAttribute("admin_id");
		 */
		String key = request.getParameter("key");
		System.out.println(key);
		if (key.equals("st")) {
			session.removeAttribute("user_id");
		} else if (key.equals("in")) {
			session.removeAttribute("in_id");
		} else if (key.equals("admin")) {
			session.removeAttribute("admin_id");
		}

		return "login-form";
	}

	// admin_Action4 - JSON때문에 새로 만듦
	// public String admin_subject_json(HttpServletRequest request,
	// HttpServletResponse response)
	// throws SecurityException, IOException {
	//
	// return adAction4.admin_subject_json(request, response);
	// }

	// admin_Action4 - JSON때문에 새로 만듦
	public String admin_student_json(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.admin_student_json(request, response);
	}

	// admin_Action4 - JSON때문에 새로 만듦
	public String ad_info_modifyBookName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_modifyBookName(request, response);
	}

	// admin_Action4 - JSON때문에 새로 만듦
	public String student_book_picture_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_modifyBookName(request, response);
	}

	// admin_Action4 - JSON때문에 새로 만듦
	public String student_picture_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.student_picture_ajax(request, response);
	}

	// admin_Action4 - JSON때문에 새로 만듦
	public String doDownload(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.doDownload(request, response);
	}
}