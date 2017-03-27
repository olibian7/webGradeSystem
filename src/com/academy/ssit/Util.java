package com.academy.ssit;

import java.util.List;
import java.util.regex.Pattern;

import com.academy.ssit.admin.Admin;

public class Util {

	// ��¥ üũ
	public static void dateCheck(String date) throws java.text.ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		sdf.parse(date); // ���� �߻�
	}

	// �ð� üũ
	public static void hourCheck(String date) throws java.text.ParseException {
		// 00 ~ 23
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");
		sdf.setLenient(false);
		sdf.parse(date); // ���� �߻�
	}

	// ���ڿ� ���� üũ
	public static void lengthCheck(String content, int size) throws Exception {
		if (content.length() > size) {
			throw new Exception();
		}
	}

	public static void pressAnyKey(java.util.Scanner sc) {
		System.out.println();
		System.out.print("press any key to continue....");
		sc.nextLine(); // EnterŰ ����
	}

	/*
	 * 00�� ���۳�¥, ����¥ �Է� �� �ùٸ��� ���� ��¥(���۳�¥�� ����¥�� �ռ��� ���)�� �����ϴ� �޼ҵ带 UtilŬ������
	 * �־����. �����ϼ���~
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

	// �����ID üũ
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

	// ����ID üũ
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

	// ����ID üũ
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

	// �ֹι�ȣ ���� üũ(���ڸ�)
	public static void NumberFormatCheck(String value) throws Exception {
		boolean result = Pattern.matches("\\d{7}", value);
		if (result == false) {
			throw new Exception();
		}
	}

	// ����ID üũ
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
		// ���ϴ� �׸��� ������ ����.
		// 1. '����ϴ� ��� ����'�� '���ڿ�'�� �ܺηκ��� �Ű������� �޾�
		// 2. '���ڿ�'�� byte ���̿� '����ϴ� ��� ����'�� ���Ͽ�
		// 3. ���� ���̸�ŭ ������ �����Ͽ�
		// 4. ���ڿ� ��, Ȥ�� �ڿ� �ٿ��� ��ȯ�Ѵ�.

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

	// subject_name_id�ߺ��Ұ�
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

	// ������ ID üũ
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

	// ������ �̸� üũ
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

	// ����ID üũ
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
	 * �ֹε�Ϲ�ȣ, �޴��� ��ȣ �Է¿� ���� ���Խ� �߰� Util�� �߰��ؼ� ����Ͻø� �˴ϴ�. ���ԽĿ� ���� ������ ���� �߻�
	 */

	// �ֹε�Ϲ�ȣ ���Խ�
	public static void ssnFormatter(String ssn) throws Exception {
		boolean result = false;

		result = Pattern.matches("(1|2)\\d{6}", ssn);
		if (result == false) {
			throw new Exception();
		}

	}

	// �ڵ��� ��ȣ ���Խ�
	public static void phoneFormatter(String phone) throws Exception {
		boolean result = false;

		result = Pattern.matches("(011|010)-\\d{3,4}-\\d{4}", phone);
		if (result == false) {
			throw new Exception();
		}

	}

}
