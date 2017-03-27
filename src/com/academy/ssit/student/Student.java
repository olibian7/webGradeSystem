package com.academy.ssit.student;

//학생 정보
public class Student {

			//학생이름,   학생ID,    주민뒷번호  전화번호  등록일
	private String name, student_id, social_num, phone, registration_date	
	,course_id //과정ID
	,course_name //과정명
	,cr_start_date //과정시작
	,cr_end_date // 과정 끝
	,scsv_subject_id//과목ID
	,subject_name//과목명
	,sj_start_date//과목기간시작
	,sj_end_date//과목기간종료
	,book_name//교재명
	,book_img//교재이미지
	,book_id
	,publisher
	,test_paper_name
	,in_name//강사명	
	,test_date//시험날짜
	,test_paper;//시험문제
	
	public String getTest_paper_name() {
		return test_paper_name;
	}

	public void setTest_paper_name(String test_paper_name) {
		this.test_paper_name = test_paper_name;
	}

	private int gr_attend //출결점수
	,sc_attend //출결배점
	,gr_writing //필기점수
	,sc_writing //필기배점
	,gr_practice//실기점수
	,sc_practice;//실기배점

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getBook_img() {
		return book_img;
	}

	public void setBook_img(String book_img) {
		this.book_img = book_img;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getSocial_num() {
		return social_num;
	}

	public void setSocial_num(String social_num) {
		this.social_num = social_num;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}


	public String getCr_start_date() {
		return cr_start_date;
	}

	public void setCr_start_date(String cr_start_date) {
		this.cr_start_date = cr_start_date;
	}

	public String getCr_end_date() {
		return cr_end_date;
	}

	public void setCr_end_date(String cr_end_date) {
		this.cr_end_date = cr_end_date;
	}

	public String getScsv_subject_id() {
		return scsv_subject_id;
	}

	public void setScsv_subject_id(String scsv_subject_id) {
		this.scsv_subject_id = scsv_subject_id;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public String getSj_start_date() {
		return sj_start_date;
	}

	public void setSj_start_date(String sj_start_date) {
		this.sj_start_date = sj_start_date;
	}

	public String getSj_end_date() {
		return sj_end_date;
	}

	public void setSj_end_date(String sj_end_date) {
		this.sj_end_date = sj_end_date;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getIn_name() {
		return in_name;
	}

	public void setIn_name(String in_name) {
		this.in_name = in_name;
	}

	public String getTest_date() {
		return test_date;
	}

	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}

	public String getTest_paper() {
		return test_paper;
	}

	public void setTest_paper(String test_paper) {
		this.test_paper = test_paper;
	}

	public int getGr_attend() {
		return gr_attend;
	}

	public void setGr_attend(int gr_attend) {
		this.gr_attend = gr_attend;
	}

	public int getSc_attend() {
		return sc_attend;
	}

	public void setSc_attend(int sc_attend) {
		this.sc_attend = sc_attend;
	}

	public int getGr_writing() {
		return gr_writing;
	}

	public void setGr_writing(int gr_writing) {
		this.gr_writing = gr_writing;
	}

	public int getSc_writing() {
		return sc_writing;
	}

	public void setSc_writing(int sc_writing) {
		this.sc_writing = sc_writing;
	}

	public int getGr_practice() {
		return gr_practice;
	}

	public void setGr_practice(int gr_practice) {
		this.gr_practice = gr_practice;
	}

	public int getSc_practice() {
		return sc_practice;
	}

	public void setSc_practice(int sc_practice) {
		this.sc_practice = sc_practice;
	}

	
}


