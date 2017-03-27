package com.academy.ssit;

import java.util.List;
import java.util.regex.Pattern;

import com.academy.ssit.admin.Admin;

public class Util {

	// 날짜 체크
	public static void dateCheck(String date) throws java.text.ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		sdf.parse(date); // 예외 발생
	}

	// 시간 체크
	public static void hourCheck(String date) throws java.text.ParseException {
		// 00 ~ 23
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");
		sdf.setLenient(false);
		sdf.parse(date); // 예외 발생
	}

	// 문자열 길이 체크
	public static void lengthCheck(String content, int size) throws Exception {
		if (content.length() > size) {
			throw new Exception();
		}
	}

	public static void pressAnyKey(java.util.Scanner sc) {
		System.out.println();
		System.out.print("press any key to continue....");
		sc.nextLine(); // Enter키 포함
	}

	/*
	 * 00의 시작날짜, 끝날짜 입력 시 올바르지 못한 날짜(시작날짜가 끝날짜를 앞서는 경우)를 방지하는 메소드를 Util클래스에
	 * 넣었어요. 참고하세요~
	 */
	public static void datecheck2(String start_date, String end_date) throws Exception {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = sdf.parse(start_date);
		java.util.Date date2 = sdf.parse(end_date);

		long result1 = date1.getTime();
		long result2 = date2.getTime();

		if (result1 > result2) {
			throw new Exception();
		}
	}

	// 과목명ID 체크
	public static void subjectNameIDcheck(List<Admin> list, String subject_name_id) throws Exception {
		int flag = 0;
		for (Admin ad : list) {
			if (ad.getSubject_name_id().equals(subject_name_id))
				++flag;
		}

		if (flag == 0) {
			throw new Exception();
		}
	}

	// 교재ID 체크
	public static void bookIDcheck(List<Admin> list, String book_id) throws Exception {
		int flag = 0;
		for (Admin ad : list) {
			if (ad.getBook_id().equals(book_id))
				++flag;
		}

		if (flag == 0) {
			throw new Exception();
		}
	}

	// 강사ID 체크
	public static void instructorIDcheck(List<Admin> list, String instructor_id) throws Exception {
		int flag = 0;
		for (Admin ad : list) {
			if (ad.getInstructor_id().equals(instructor_id))
				++flag;
		}

		if (flag == 0) {
			throw new Exception();
		}
	}

	// 주민번호 형식 체크(숫자만)
	public static void NumberFormatCheck(String value) throws Exception {
		boolean result = Pattern.matches("\\d{7}", value);
		if (result == false) {
			throw new Exception();
		}
	}

	// 과정ID 체크
	public static void courseIDcheck(List<Admin> list, String course_id) throws Exception {
		int flag = 0;
		for (Admin ad : list) {
			if (ad.getCourse_id().equals(course_id))
				++flag;
		}

		if (flag == 0) {
			throw new Exception();
		}
	}

	public static String pF(String str, int size) throws Exception {
		// 원하는 그림은 다음과 같다.
		// 1. '희망하는 출력 길이'와 '문자열'을 외부로부터 매개변수로 받아
		// 2. '문자열'의 byte 길이와 '희망하는 출력 길이'를 비교하여
		// 3. 비교한 차이만큼 공백을 생성하여
		// 4. 문자열 앞, 혹은 뒤에 붙여서 반환한다.

		String result = "";
		result = str;

		int byteSize = 0;
		int length = 0;

		if (length < 0) {
			throw new Exception();
		}

		if (str != null) {
			byteSize = result.getBytes().length;
			length = size - byteSize;
		} else {
			result = "-";
			length = size;
		}

		for (int i = 0; i < length; ++i) {
			result += " ";
		}

		return result;
	}

	// subject_name_id중복불가
	public static void UniqueCheck(List<String> list, String value) throws Exception {
		int flag = 0;
		for (String str : list) {
			if (str.equals(value)) {
				++flag;
			}
		}

		if (flag > 0) {
			throw new Exception();
		}
	}

	// 수강생 ID 체크
	public static void stIDcheck(List<Admin> list, String student_id) throws Exception {
		int flag = 0;
		for (Admin ad : list) {
			if (ad.getStudent_id().equals(student_id))
				++flag;
		}
		if (flag == 0) {
			throw new Exception();
		}
	}

	// 수강생 이름 체크
	public static void stNamecheck(List<Admin> list, String student_name) throws Exception {
		int flag = 0;
		for (Admin ad : list) {
			if (ad.getSt_name().contains(student_name))
				++flag;
		}

		if (flag == 0) {
			throw new Exception();
		}
	}

	// 과목ID 체크
	public static void subjectIDcheck(List<Admin> list, String subject_id) throws Exception {
		int flag = 0;
		for (Admin ad : list) {
			if (ad.getSubject_id().equals(subject_id))
				++flag;
		}

		if (flag == 0) {
			throw new Exception();
		}
	}

	/*
	 * 주민등록번호, 휴대폰 번호 입력에 대한 정규식 추가 Util에 추가해서 사용하시면 됩니다. 정규식에 맞지 않으면 예외 발생
	 */

	// 주민등록번호 정규식
	public static void ssnFormatter(String ssn) throws Exception {
		boolean result = false;

		result = Pattern.matches("(1|2)\\d{6}", ssn);
		if (result == false) {
			throw new Exception();
		}

	}

	// 핸드폰 번호 정규식
	public static void phoneFormatter(String phone) throws Exception {
		boolean result = false;

		result = Pattern.matches("(011|010)-\\d{3,4}-\\d{4}", phone);
		if (result == false) {
			throw new Exception();
		}

	}

}
