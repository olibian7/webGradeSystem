package com.academy.ssit.admin;

//관리자 정보
public class Admin {
	private String course_name_id//과정명ID
	,course_id//과정ID
	,course_name//과정명
	,cr_start_date//과정시작날짜
	,cr_end_date//과정끝날짜

	,subject_name_id//과목명ID
	,subject_id//과목ID
	,subject_name//과목명
	,sj_start_date//과목시작날짜
	,sj_end_date//과목끝날짜

	,classroom_id//강의실ID
	,classroom_name//강의실 이름

	,book_id//교재ID
	,book_name//교재명
	,publisher//출판사

	,test_paper//시험문제 파일
	,test_paper_name//시험문제 원본 파일 이름

	,instructor_id//강사ID
	,in_name//강사 이름
	,in_social_num //강사 주민번호뒷자리
	,in_phone//강사 전화번호
	,sj_available//강사의 강의가능 과목명

	,student_id//수강생 ID
	,st_name//수강생 이름
	,st_social_num//수강생 주민번호 뒷자리 
	,st_phone//수강생 전화번호
	,st_registration_date//수강생 등록날짜
	,fail_date//중도탈락날짜

	,gradecheck //성적등록여부(1: 등록, 0:미등록)
	,testcheck
	,book_img;
	
	public String getTest_paper_name() {
		return test_paper_name;
	}


	public void setTest_paper_name(String test_paper_name) {
		this.test_paper_name = test_paper_name;
	}


	private int max_num//강의실 정원
	,countStu//과정을 수강중인 학생 수
	,countSub//과정의 과목 수
	,countRequest//학생 과정 수강신청 횟수
	,dateCheck//강의진행여부(-1 : 강의 예정, 0 : 강의 중, 1 : 강의 끝)
	,gr_attend//(성적)출결
	,gr_writing//(성적)필기
	,gr_practice//(성적)실기
	,sc_attend//(배점)출결
	,sc_writing//(배점)필기
	,sc_practice;//(배점)실기


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
