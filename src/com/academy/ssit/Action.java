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
	// ������=====================================

	// 1-1. ���������� - �������������(���̵�, �̸�, �ι�, ����, �����)
	public String st_studentInfo(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.st_studentInfo(request, response);
	}

	// 1-2. ���������� - ������ ���� ����(�̸�, �ι�, ���� ��������)
	public String st_modifyStudentInfo(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.st_modifyStudentInfo(request, response);
	}

	// 1-3. ���������� - �������� �����ϴ� ������ȸ(����ID, ������, �����Ⱓ)
	public String st_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.st_courseList(request, response);
	}

	// 1-4. ���������� - �л����� ���(����ID ����� ����Ⱓ ����� ����� ���(����/����) �ʱ�(����/����) �Ǳ�(����/����)
	// ���賯¥ ���蹮��)
	public String st_gradeList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.st_gradeList(request, response);
	}

	// =======================================2.
	// ����=====================================

	// 2-1. ������� - ���� ������(��������, ���ǿ���, ������)
	// ���� ����(����ID, �����̸�, ������� ��¥, ���� ����¥, �����̸�, �������۳�¥, ��������¥, ���ǽ��̸�, �����̸�, �л�
	// ��)
	public String ins_scheduleList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_scheduleList(request, response);
	}

	// 2-2. ������� - ���� ���� ������ ����Ʈ
	// ����(���� ����, ��, ����)�� ���� ���������� ���(student_id, name, phone, �ߵ�Ż������, gr_attend,
	// gr_writing, gr_practice)
	public String ins_studentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_studentList(request, response);
	}

	// 2-2(�߰�). ������� - ���� ���� �� ������ ��� ����
	public String teacher_scheduleList_ajaxReceive(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.teacher_scheduleList_ajaxReceive(request, response);
	}

	// 2-3. ������� - ���� ������� + ������Ͽ��� Ȯ��
	// �������(����ID, �����,������۳�¥, ���� ���ᳯ¥, ������, �������۳�¥, ��������¥, ���ǽǸ�, �����, sc_attend,
	// sc_writing, sc_practice, gradecheck)
	public String ins_subScoreList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_subScoreList(request, response);
	}

	// 2-4. ������� - �����Է�
	// �����Է�(����ID, ���, �ʱ�, �Ǳ�)score(subject_id, attend_score, writing_score,
	// practice_score)
	public String ins_addScore(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_addScore(request, response);
	}

	// 2-5. ������� - ��������
	public String ins_modifyScore(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_modifyScore(request, response);
	}

	// 2-6. ������� - ��������
	public String ins_deleteScore(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_deleteScore(request, response);
	}

	// 2-7. ������� - ������
	// ������(����ID, ���賯¥, ���蹮��)test(subject_id, test_date, test_paper)
	public String ins_addTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_addTest(request, response);
	}

	// 2-8. ������� - �������
	public String ins_modifyTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_modifyTest(request, response);
	}

	// 2-9. ������� - �������
	public String ins_deleteTest(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_deleteTest(request, response);
	}

	public String ins_gra_subjectList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_gra_subjectList(request, response);
	}

	// 2-10. ������� - Ư�� ���� �������� ������ ���� ���(����, ���Ῡ��)student_id, name, phone,
	// failcheck, gr_attend, gr_writing, gr_practice
	public String ins_gra_studentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_gra_studentList(request, response);
	}

	// 2-11. ���� �Է�(subject_id, course_id, student_id, attend_score,
	// writing_score, practice_score)
	public String ins_addGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_addGrade(request, response);
	}

	// 2-12. ���� ����
	public String ins_modifyGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_modifyGrade(request, response);
	}

	// 2-13. ���� ����
	public String ins_deleteGrade(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_deleteGrade(request, response);
	}

	// 2-14. ������� -��������- ���� ������� + ������Ͽ��� Ȯ��
	// �������(����ID, �����,������۳�¥, ���� ���ᳯ¥, ������, �������۳�¥, ��������¥, ���ǽǸ�, �����, sc_attend,
	// sc_writing, sc_practice, gradecheck)
	public String ins_subGradeList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return inAction.ins_subGradeList(request, response);
	}

	// =======================================3.
	// ������=====================================

	// 3-1-1. �����ڰ��� - ������������>�������Է�(������ID, ������)
	public String ad_info_addCourseName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_addCourseName(request, response);
	}

	// 3-1-1. �����ڰ��� - ������������>��������(������ID, ������)
	public String ad_info_modifyCourseName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_modifyCourseName(request, response);
	}

	// 3-1-1. �����ڰ��� - ������������>��������(������ID, ������)
	public String ad_info_deleteCourseName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_deleteCourseName(request, response);
	}

	// 3-1-2. �����ڰ��� - ������������>��������Ʈ ���(������ID, ������)
	public String ad_info_courseNameList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_courseNameList(request, response);
	}

	// 3-1-3. �����ڰ��� - ������������>������Է�(�����ID, �����)
	public String ad_info_addSubName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_addSubName(request, response);
	}

	// 3-1-3. �����ڰ��� - ������������>��������(�����ID, �����)
	public String ad_info_modifySubName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_modifySubName(request, response);
	}

	// 3-1-3. �����ڰ��� - ������������>��������(�����ID, �����)
	public String ad_info_deleteSubName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_deleteSubName(request, response);
	}

	// 3-1-4. �����ڰ��� - ������������>�������Ʈ ���(�����ID, �����)
	public String ad_info_subjectNameInfoList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_subjectNameInfoList(request, response);
	}

	// 3-1-5. �����ڰ��� - ������������>���ǽǸ��Է�(���ǽǸ�ID, ���ǽǸ�, ����)
	public String ad_info_addClassroom(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_addClassroom(request, response);
	}

	// 3-1-6. �����ڰ��� - ������������>���ǽǸ���Ʈ ���(���ǽǸ�ID, ���ǽǸ�, ����)
	public String ad_info_classroomList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_classroomList(request, response);
	}

	// 3-1-6. �����ڰ��� - ������������>���ǽǸ���Ʈ ����(���ǽǸ�ID, ���ǽǸ�, ����)
	public String ad_info_deleteClassroom(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_deleteClassroom(request, response);
	}

	// 3-1-6. �����ڰ��� - ������������>���ǽǸ���Ʈ ����(���ǽǸ�ID, ���ǽǸ�, ����)
	public String ad_info_modiftyClassroom(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_modiftyClassroom(request, response);
	}

	// 3-1-7. �����ڰ��� - ������������>������Է�(�����ID, �����, ���ǻ�)
	public String ad_info_addBook(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_addBook(request, response);
	}

	// 3-1-8. �����ڰ��� - ������������>�������Ʈ ���(�����ID, �����, ���ǻ�)
	public String ad_info_bookList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_bookList(request, response);
	}

	// 3-1-9. �����ڰ��� - ������������>������� ���(�������)
	public String admin_basic_info_book_picture_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.admin_basic_info_book_picture_ajax(request, response);
	}

	// �����ڰ��� - ������������>��������(�����ID, �����, ���ǻ�)
	public String ad_info_deleteBookName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_deleteBookName(request, response);
	}

	// =======================================================================================

	// 3-2-9. �����ڰ��� - �����������>�����������(����ID, �̸�, �ֹε�ϵ޹�ȣ, ��ȭ��ȣ), ���ǰ��ɰ��� ���(����ID,
	// �����ID)
	public String ad_ins_addInstructor(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_addInstructor(request, response);
	}

	public String admin_teacher_subjectAjax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_subjectAjax(request, response);
	}

	// 3-2-10. �����ڰ��� - �����������>�����������(����ID, �̸�, �ֹε�ϵ޹�ȣ, ��ȭ��ȣ, ���ǰ��ɰ���)
	public String ad_ins_instructorList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_instructorList(request, response);
	}

	// 3-2-11. �����ڰ��� - �����������> ����������°˻�(�����̸�, �����, ������۳�¥&����¥, ������, �������۳�¥&����¥,
	// �����, ���ǽǸ�, �������࿩�� )
	public String ad_ins_instructorList_detail(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_instructorList_detail(request, response);
	}

	// 3-2-12. �����ڰ��� - �����������> �����������> ������������(���ǰ��ɰ��񿡼��� ����)
	public String ad_ins_deleteInstructor(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_deleteInstructor(request, response);
	}

	// 3-2-13. �����ڰ��� - �����������>�������������̸�, �ֹε�ϵ޹�ȣ, ��ȭ��ȣ, ���ǰ��ɰ���)
	public String ad_ins_modify(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction2.ad_ins_modifyInstructor(request, response);
	}

	// =======================================================================================

	// 3-3-13. �����ڰ��� - ������������>��������Ʈ ���(������ID, ������)
	// public String ad_cr_courseNameList(HttpServletRequest request,
	// HttpServletResponse response)
	// throws SecurityException, IOException {
	//
	// return adAction3.ad_cr_courseNameList(request, response);
	// }

	// 3-3-15. �����ڰ��� - ������������>�������>����ID �ο�(���ǽ� ���� ���� �Ŀ� ����)
	public String ad_cr_addCourseId(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_addCourseId(request, response);
	}

	// 3-3-16. �����ڰ��� - ������������>�������>�ű԰������
	public String ad_cr_addCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_addCourse(request, response);
	}

	// 3-3-21. �����ڰ��� - ������������>��������
	public String ad_cr_modifyCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_modifyCourse(request, response);
	}

	// 3-3-22. �����ڰ��� - ������������>��������
	public String ad_cr_deleteCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_deleteCourse(request, response);
	}

	// 3-3-17. �����ڰ��� - ������������>���� ���� ���(����ID, ������, ��������&����¥, ���ǽǸ�, ����� ��������, ����,
	// ��ϵ� �����)
	public String ad_cr_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_courseList(request, response);
	}

	// 3-3-18. �����ڰ��� - ���)������������>���� ��>���� ���� ���(����ID, �����, �������&����¥, �����, �����,
	// ����,
	// ��ϵ� �����)
	public String admin_course_ajaxReceive(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.admin_course_ajaxReceive(request, response);
	}

	// 3-3-19. �����ڰ��� - ���)������������>���� ��>������ ���� ���(������ID, �������̸�, ��ȭ��ȣ, �������,
	// �ߵ�Ż����¥)
	public String admin_course_ajaxReceive2(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.admin_course_ajaxReceive2(request, response);
	}

	// 3-3-20. �����ڰ��� - ������������> ���ó�¥ ����Ͽ� ����������¥(����, ��) ����
	public String ad_cr_dateCheck(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction3.ad_cr_dateCheck(request, response);
	}

	// =======================================================================================

	// 3-4-22. �����ڰ��� - �����������> ��������Ʈ ���(����ID, ������, �����Ⱓ, ��ϰ����)
	public String ad_sj_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction4.ad_sj_courseList(request, response);
	}

	// 3-4-23. �����ڰ��� - �����������>���񸮽�Ʈ ���(����ID, �����, ����Ⱓ, ����ID, ������, �����Ⱓ, �����,
	// �����
	// )
	public String ad_sj_subjectList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction4.ad_sj_subjectList(request, response);
	}

	// 3-4-24. �����ڰ��� - �����������>���� �ű� ����
	public String ad_sj_addSubject(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction4.ad_sj_addSubject(request, response);
	}

	// 3-4-24. �����ڰ��� - �����������>�������
	public String ad_sj_deleteSubject(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction4.ad_sj_deleteSubject(request, response);
	}

	// 3-4-25. �����ڰ��� - �����������>�������
	public String ad_sj_modifySubject(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction4.ad_sj_modifySubject(request, response);
	}

	// =======================================================================================

	// 3-5-25. �����ڰ��� - �л�����>�л��������(�л�ID, �̸�, �ֹι�ȣ, �ڵ���)
	public String ad_st_addStudent(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.ad_st_addStudent(request, response);
	}

	// 3-5-26. �����ڰ��� - �л�����>��ü�˻�>������ ��ü����Ʈ ���(�л�ID, �̸�, �ֹι�ȣ, �ڵ���, �����, ������ûȽ��)
	public String ad_st_allStudentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.ad_st_allStudentList(request, response);
	}

	// 3-5-27. �����ڰ��� - �л�����>�л� ���� ��� �� ���� ��� ���(����Id, ������, �����Ⱓ, ���ǽ�, �����ο�,
	// ��ϰ����)
	public String ad_st_studentCourseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.ad_st_studentCourseList(request, response);
	}

	// 3-5-33. �����ڰ��� - �л�����>�л� ���� ���
	public String ad_st_add_studentCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		return adAction5.ad_st_add_studentCourse(request, response);
	}
	
	// 3-5-34. �����ڰ��� - �л�����>�л� ���� ��� ���
	public String ad_st_delete_studentCourse(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		return adAction5.ad_st_delete_studentCourse(request, response);
	}
	
	// 3-5-28. �����ڰ��� - �л�����>�����Է�(����ID, �л�ID)
	// public String ad_st_registerCourse(HttpServletRequest request,
	// HttpServletResponse response)
	// throws SecurityException, IOException {
	//
	// return adAction5.ad_st_registerCourse(request, response);
	// }

	// 3-5-29. �����ڰ��� - �л�����>������ �������� ���(������, �����Ⱓ, ���ǽǸ�, �ߵ�Ż����)
	public String ad_st_studentInfo(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.ad_st_studentInfo(request, response);
	}

	// 3-5-30. �����ڰ��� - �л�����>�����˻�>������ ���� ��ü ���(����ID, ������, �����Ⱓ, ���ǽ�)
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
	 * // 3-5-31. �����ڰ��� - �л�����>�����˻�>������ �̸� �˻�(�л�ID, �̸�, �ֹε�Ϲ�ȣ, ��ȭ��ȣ, �������,
	 * �ߵ�Ż����¥) public String ad_st_courseStudentList(HttpServletRequest request,
	 * HttpServletResponse response) throws SecurityException, IOException {
	 * 
	 * return adAction5.ad_st_courseStudentList(request, response); }
	 */
	// =======================================================================================

	// 3-6-32. �����ڰ��� - ������ȸ>���������ȸ>���� ��� ���(����ID, ������, �����Ⱓ)
	public String ad_gr_courseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction6.ad_gr_courseList(request, response);
	}

	// 3-6-33. �����ڰ��� - ������ȸ>���������ȸ>Ư�� ���� ���� ���(����ID, �����, ����Ⱓ, �����, �����Է¿���,
	// ��������Ͽ���, ���ǽ�, �����, ������, �����Ⱓ)
	public String ad_gr_courseSubjectList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction6.ad_gr_courseSubjectList(request, response);
	}

	// 3-6-34. �����ڰ��� - ������ȸ>���������ȸ>Ư�� ������ ��� ������ ���� ���(�л�ID, �̸�, ��ȭ��ȣ, ���, �ʱ�,
	// �Ǳ�)
	public String ad_gr_subStudentlist(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction6.ad_gr_subStudentlist(request, response);
	}

	// 3-35. �����ڰ��� - ������ȸ>�л�������ȸ>��ü �л� ����Ʈ ���(�л�ID, �̸�, �ֹι�ȣ, ��ȭ��ȣ, �������)
	public String ad_gr_allStudentList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction6.ad_gr_allStudentList(request, response);
	}

	// 3-36. �����ڰ��� - ������ȸ>�л�������ȸ>�л��� ���� ����Ʈ ���(����ID, ������, �Ⱓ)
	public String ad_gr_studentCourseList(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {
		return adAction6.ad_gr_studentCourseList(request, response);
	}

	// 3-6-37. �����ڰ��� - ������ȸ>�л�������ȸ>Ư�� �������� ������ ���� �ȿ� �ִ� ���� ���� + ���� ���(�л�ID, �̸�,
	// ��ȭ��ȣ, ����ID, �����, ����Ⱓ, ���, �ʱ�, �Ǳ�)
	// public String ad_gr_studentGrade(HttpServletRequest request,
	// HttpServletResponse response)
	// throws SecurityException, IOException {
	//
	// return adAction6.ad_gr_studentGrade(request, response);
	// }

	// �����ڰ��� - ������ȸ>���������ȸ>Ư�������� ��� �л��� ajaxó��
	public String admin_grade_course_detail_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction6.admin_grade_course_detail_ajax(request, response);
	}

	// �����ڰ��� - ������ȸ>���������ȸ>Ư�������� ��� �л��� ajaxó��
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

	// admin_Action4 - JSON������ ���� ����
	// public String admin_subject_json(HttpServletRequest request,
	// HttpServletResponse response)
	// throws SecurityException, IOException {
	//
	// return adAction4.admin_subject_json(request, response);
	// }

	// admin_Action4 - JSON������ ���� ����
	public String admin_student_json(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction5.admin_student_json(request, response);
	}

	// admin_Action4 - JSON������ ���� ����
	public String ad_info_modifyBookName(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_modifyBookName(request, response);
	}

	// admin_Action4 - JSON������ ���� ����
	public String student_book_picture_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return adAction1.ad_info_modifyBookName(request, response);
	}

	// admin_Action4 - JSON������ ���� ����
	public String student_picture_ajax(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.student_picture_ajax(request, response);
	}

	// admin_Action4 - JSON������ ���� ����
	public String doDownload(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException {

		return stAction.doDownload(request, response);
	}
}