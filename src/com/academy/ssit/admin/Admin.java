package com.academy.ssit.admin;

//������ ����
public class Admin {
	private String course_name_id//������ID
	,course_id//����ID
	,course_name//������
	,cr_start_date//�������۳�¥
	,cr_end_date//��������¥

	,subject_name_id//�����ID
	,subject_id//����ID
	,subject_name//�����
	,sj_start_date//������۳�¥
	,sj_end_date//���񳡳�¥

	,classroom_id//���ǽ�ID
	,classroom_name//���ǽ� �̸�

	,book_id//����ID
	,book_name//�����
	,publisher//���ǻ�

	,test_paper//���蹮�� ����
	,test_paper_name//���蹮�� ���� ���� �̸�

	,instructor_id//����ID
	,in_name//���� �̸�
	,in_social_num //���� �ֹι�ȣ���ڸ�
	,in_phone//���� ��ȭ��ȣ
	,sj_available//������ ���ǰ��� �����

	,student_id//������ ID
	,st_name//������ �̸�
	,st_social_num//������ �ֹι�ȣ ���ڸ� 
	,st_phone//������ ��ȭ��ȣ
	,st_registration_date//������ ��ϳ�¥
	,fail_date//�ߵ�Ż����¥

	,gradecheck //������Ͽ���(1: ���, 0:�̵��)
	,testcheck
	,book_img;
	
	public String getTest_paper_name() {
		return test_paper_name;
	}


	public void setTest_paper_name(String test_paper_name) {
		this.test_paper_name = test_paper_name;
	}


	private int max_num//���ǽ� ����
	,countStu//������ �������� �л� ��
	,countSub//������ ���� ��
	,countRequest//�л� ���� ������û Ƚ��
	,dateCheck//�������࿩��(-1 : ���� ����, 0 : ���� ��, 1 : ���� ��)
	,gr_attend//(����)���
	,gr_writing//(����)�ʱ�
	,gr_practice//(����)�Ǳ�
	,sc_attend//(����)���
	,sc_writing//(����)�ʱ�
	,sc_practice;//(����)�Ǳ�


	public String getGradecheck() {
		return gradecheck;
	}


	public String getTestcheck() {
		return testcheck;
	}


	public void setGradecheck(String gradecheck) {
		this.gradecheck = gradecheck;
	}


	public void setTestcheck(String testcheck) {
		this.testcheck = testcheck;
	}


	public String getCourse_name_id() {
		return course_name_id;
	}


	public String getCourse_id() {
		return course_id;
	}


	public String getCourse_name() {
		return course_name;
	}


	public String getCr_start_date() {
		return cr_start_date;
	}


	public String getCr_end_date() {
		return cr_end_date;
	}


	public String getSubject_name_id() {
		return subject_name_id;
	}


	public String getSubject_id() {
		return subject_id;
	}


	public String getSubject_name() {
		return subject_name;
	}


	public String getSj_start_date() {
		return sj_start_date;
	}


	public String getSj_end_date() {
		return sj_end_date;
	}


	public String getClassroom_id() {
		return classroom_id;
	}


	public String getClassroom_name() {
		return classroom_name;
	}


	public String getBook_id() {
		return book_id;
	}


	public String getBook_name() {
		return book_name;
	}


	public String getPublisher() {
		return publisher;
	}


	public String getTest_paper() {
		return test_paper;
	}


	public String getInstructor_id() {
		return instructor_id;
	}


	public String getIn_name() {
		return in_name;
	}


	public String getIn_social_num() {
		return in_social_num;
	}


	public String getIn_phone() {
		return in_phone;
	}


	public String getSj_available() {
		return sj_available;
	}


	public String getStudent_id() {
		return student_id;
	}


	public String getSt_name() {
		return st_name;
	}


	public String getSt_social_num() {
		return st_social_num;
	}


	public String getSt_phone() {
		return st_phone;
	}


	public String getSt_registration_date() {
		return st_registration_date;
	}


	public String getFail_date() {
		return fail_date;
	}


	public int getMax_num() {
		return max_num;
	}


	public int getCountStu() {
		return countStu;
	}


	public int getCountSub() {
		return countSub;
	}


	public int getCountRequest() {
		return countRequest;
	}


	public int getDateCheck() {
		return dateCheck;
	}
	
	public int getGr_attend() {
		return gr_attend;
	}


	public int getGr_writing() {
		return gr_writing;
	}


	public int getGr_practice() {
		return gr_practice;
	}


	public int getSc_attend() {
		return sc_attend;
	}


	public int getSc_writing() {
		return sc_writing;
	}


	public int getSc_practice() {
		return sc_practice;
	}


	public void setCourse_name_id(String course_name_id) {
		this.course_name_id = course_name_id;
	}


	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}


	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}


	public void setCr_start_date(String cr_start_date) {
		this.cr_start_date = cr_start_date;
	}


	public void setCr_end_date(String cr_end_date) {
		this.cr_end_date = cr_end_date;
	}


	public void setSubject_name_id(String subject_name_id) {
		this.subject_name_id = subject_name_id;
	}


	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}


	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}


	public void setSj_start_date(String sj_start_date) {
		this.sj_start_date = sj_start_date;
	}


	public void setSj_end_date(String sj_end_date) {
		this.sj_end_date = sj_end_date;
	}


	public void setClassroom_id(String classroom_id) {
		this.classroom_id = classroom_id;
	}


	public void setClassroom_name(String classroom_name) {
		this.classroom_name = classroom_name;
	}


	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}


	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public void setTest_paper(String test_paper) {
		this.test_paper = test_paper;
	}


	public void setInstructor_id(String instructor_id) {
		this.instructor_id = instructor_id;
	}


	public void setIn_name(String in_name) {
		this.in_name = in_name;
	}


	public void setIn_social_num(String in_social_num) {
		this.in_social_num = in_social_num;
	}


	public void setIn_phone(String in_phone) {
		this.in_phone = in_phone;
	}


	public void setSj_available(String sj_available) {
		this.sj_available = sj_available;
	}


	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}


	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}


	public void setSt_social_num(String st_social_num) {
		this.st_social_num = st_social_num;
	}


	public void setSt_phone(String st_phone) {
		this.st_phone = st_phone;
	}


	public void setSt_registration_date(String st_registration_date) {
		this.st_registration_date = st_registration_date;
	}


	public void setFail_date(String fail_date) {
		this.fail_date = fail_date;
	}


	public void setMax_num(int max_num) {
		this.max_num = max_num;
	}


	public void setCountStu(int countStu) {
		this.countStu = countStu;
	}


	public void setCountSub(int countSub) {
		this.countSub = countSub;
	}


	public void setCountRequest(int countRequest) {
		this.countRequest = countRequest;
	}


	public void setDateCheck(int dateCheck) {
		this.dateCheck = dateCheck;
	}

	public void setGr_attend(int gr_attend) {
		this.gr_attend = gr_attend;
	}


	public void setGr_writing(int gr_writing) {
		this.gr_writing = gr_writing;
	}


	public void setGr_practice(int gr_practice) {
		this.gr_practice = gr_practice;
	}


	public void setSc_attend(int sc_attend) {
		this.sc_attend = sc_attend;
	}


	public void setSc_writing(int sc_writing) {
		this.sc_writing = sc_writing;
	}


	public void setSc_practice(int sc_practice) {
		this.sc_practice = sc_practice;
	}


	public String getBook_img() {
		return book_img;
	}


	public void setBook_img(String book_img) {
		this.book_img = book_img;
	}
	
	
	
}
