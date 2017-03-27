package com.academy.ssit.Instructor;

//강사 정보
public class Instructor {

	//과목 테이블(instructor)의 자료 입력, 출력에 관련된 모든 항목
	
	private String instructor_id//강사ID
	,in_social_num//강사주민번호 뒷자리
	,in_name//강사이름
	,subject_id//과목번호
	,subject_name//과목명
	,sj_start_date//과목시작날짜
	,sj_end_date//과목끝날짜
	,course_id//과정명id
	,course_name//과정명
	,cr_start_date//과정시작날짜
	,cr_end_date//과정끝날짜
	,classroom_name//강의실명
	,book_name//교재명
	,test_paper_name
	,book_img//교재이미지명
	,student_id//수강생ID
	,st_name//수강생이름
	,st_phone//수강생 핸드폰번호
	,test_paper//시험문제
	,test_date;//시험날짜

	private int st_count//수강생등록인원
	,failcheck//중도탈락여부(1:수료, 0: 미정, -1: 중도탈락)
	,gr_attend//(성적)출결
	,gr_writing//(성적)필기
	,gr_practice//(성적)실기
	,sc_attend//(배점)출결
	,sc_writing//(배점)필기
	,sc_practice//(배점)실기
	,gradecheck;//성적등록여부(1: 등록, 0:미등록)
	
	public String getTest_paper_name() {
		return test_paper_name;
	}


	public void setTest_paper_name(String test_paper_name) {
		this.test_paper_name = test_paper_name;
	}
	
	public String getTest_paper() {
		return test_paper;
	}


	public void setTest_paper(String test_paper) {
		this.test_paper = test_paper;
	}


	public String getTest_date() {
		return test_date;
	}


	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}
	
	public String getCourse_id() {
		return course_id;
	}


	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}


	public String getInstructor_id() {
		return instructor_id;
	}


	public void setInstructor_id(String instructor_id) {
		this.instructor_id = instructor_id;
	}


	public String getIn_social_num() {
		return in_social_num;
	}


	public void setIn_social_num(String in_social_num) {
		this.in_social_num = in_social_num;
	}


	public String getIn_name() {
		return in_name;
	}


	public void setIn_name(String in_name) {
		this.in_name = in_name;
	}


	public String getSubject_id() {
		return subject_id;
	}


	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
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


	public String getClassroom_name() {
		return classroom_name;
	}


	public void setClassroom_name(String classroom_name) {
		this.classroom_name = classroom_name;
	}


	public String getBook_name() {
		return book_name;
	}


	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}


	public String getStudent_id() {
		return student_id;
	}


	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}


	public String getSt_name() {
		return st_name;
	}


	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}


	public String getSt_phone() {
		return st_phone;
	}


	public void setSt_phone(String st_phone) {
		this.st_phone = st_phone;
	}


	public int getSt_count() {
		return st_count;
	}


	public void setSt_count(int st_count) {
		this.st_count = st_count;
	}


	public int getFailcheck() {
		return failcheck;
	}


	public void setFailcheck(int failcheck) {
		this.failcheck = failcheck;
	}


	public int getGr_attend() {
		return gr_attend;
	}


	public void setGr_attend(int gr_attend) {
		this.gr_attend = gr_attend;
	}


	public int getGr_writing() {
		return gr_writing;
	}


	public void setGr_writing(int gr_writing) {
		this.gr_writing = gr_writing;
	}


	public int getGr_practice() {
		return gr_practice;
	}


	public void setGr_practice(int gr_practice) {
		this.gr_practice = gr_practice;
	}


	public int getSc_attend() {
		return sc_attend;
	}


	public void setSc_attend(int sc_attend) {
		this.sc_attend = sc_attend;
	}


	public int getSc_writing() {
		return sc_writing;
	}


	public void setSc_writing(int sc_writing) {
		this.sc_writing = sc_writing;
	}


	public int getSc_practice() {
		return sc_practice;
	}


	public void setSc_practice(int sc_practice) {
		this.sc_practice = sc_practice;
	}


	public int getGradecheck() {
		return gradecheck;
	}


	public void setGradecheck(int gradecheck) {
		this.gradecheck = gradecheck;
	}


	public String getBook_img() {
		return book_img;
	}


	public void setBook_img(String book_img) {
		this.book_img = book_img;
	}

}
