<%-- 
2017-01-18 pm. 3:20 김현수
관리자 계정. 개설 과목 관리 > [100%국비지원]오라클 전문가 과정(2016-11-01~2017-03-31)jsp
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>환영합니다 관리자님</title>
<!-- Bootstrap Core CSS -->
<link
	href="/grade_Management_System_0120/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="/grade_Management_System_0120/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/grade_Management_System_0120/dist/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="/grade_Management_System_0120/vendor/morrisjs/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="/grade_Management_System_0120/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- jQueryUI 환경 설정 추가 -->
<!-- <link rel="stylesheet" -->
<!-- 	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> -->
<!-- <script -->
<!-- 	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<!-- <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->

<script>
	$(document).ready(function() {

						// 				//과목 시작, 끝날짜 달력 
						// 				$("input.date").datepicker({
						// 					changeMonth : true,//월 변경 가능
						// 					changeYear : true,//년도 변경 가능
						// 					dateFormat : "yy-mm-dd"//날짜 서식 지정
						// 				});

						//문제) 삭제 버튼에 click 이벤트 등록 및 실행
						//버튼이 있는 라인의 번호, 이름 정보를 얻어서 화면에 출력 + hidden form 지정.
						$("button.info").on("click", function() {
							$("#title").text($(this).parents("tr").children().eq(1).text());
							$("#infoModal").modal();
						});

	
						//신규 과목 등록 버튼
						$("button.addSubject").on("click", function() {

							$("#admin_basicInfo").modal();
						
						});
						
						
						//과목 삭제 버튼
						$("button.remove").on("click", function() {
							$("#delete_subject_id").val($(this).parents("tr").children().eq(0).text());
							$("#sid").text($(this).parents("tr").children().eq(0).text());
							$("#subject_name").text($(this).parents("tr").children().eq(1).text());
							$("#subject_date").text($(this).parents("tr").children().eq(2).text());
							$("#class_room").text($(this).parents("tr").children().eq(3).text());
							$("#count").text($(this).parents("tr").children().eq(4).text());
							$("#removeModal").modal();
						});

						//수정 버튼 클릭에 대한 모달 창 액션
						$("button.modify").on("click", function() {
// 							$("#course_modify_modal #modify_title").find("span").text($("#title").find("span").eq(0).text()	+ $("#title").find("span").eq(1).text());
// 							$("#course_modify_modal #modify_title").find("small").text($("#title").find("span").eq(2).text() + $("#title").find("span").eq(3).text());
							
							$("#course_modify_modal #modify_subject_id2").val($(this).parents("tr").children().eq(0).text());
							
							$("#course_modify_modal #modify_subject_id").val($(this).parents("tr").children().eq(0).text() + " - " + $(this).parents("tr").children().eq(1).text());
							$("#course_modify_modal #modify_sj_start_date").val($(this).parents("tr").children().eq(2).children().eq(0).text());
							$("#course_modify_modal #modify_sj_end_date").val($(this).parents("tr").children().eq(2).children().eq(1).text());
							$("#course_modify_modal #modify_book").val($(this).parents("tr").next().val()).attr("selected", "selected");
							$("#course_modify_modal #modify_instructor").val($(this).parents("tr").nextAll("#instructor_id").val()).attr("selected","selected");
							$("#course_modify_modal").modal();
						});
						
						//책 이미지 뜨기
						$(".book_img").on("click", function() {
							//서버에 사진 정보를 확인하기 위한 직원번호를 전송한다.
							console.log($("#pictureModal").find("img").attr("src"));
							var book_id = $(this).parents("tr").next().val();
							//책제목 동적 출력 과정 추가
							$("#pictureModal").find("h4").text($(this).text());
							$.post("admin_basic_info_book_picture_ajax.it",	{book_id : book_id},function(data) {
								//Ajax 응답으로 받은 사진 파일명을 가지고 이미지 출력
								console.log(data);
								//이미지 동적 출력 과정 추가
								if ($.trim(data) != "") {
									$("#pictureModal").find("img").attr("src", "${pageContext.request.contextPath}/picture/" + data);
								} else {
									$("#pictureModal").find("img").attr("src", "${pageContext.request.contextPath}/picture/book.jpg");
								}

								console.log($("#pictureModal").find("img").attr("src"));

							});
							//모달 창 호출 액션
							$("#pictureModal").modal();

						});

					});
</script>

<style>

/* 모달 창 헤더 부분 스타일 */
.modal-header {
	color: #333;
	background-color: #f5f5f5;
	border-color: #ddd;
	padding: 10px 15px;
	border-bottom: 1px solid transparent;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

/* 모달 창 스크롤 */
.list {
	max-height: 430px;
	overflow: auto;
	margin-bottom: 20px;
}

/* 모달창 아래부분 줄 없애기 */
.modal-footer {
	border-top: 0px;
}
/* 백그라운드 크기 조절(배경색을 위한 조정)_정호 */
html, body, #wrapper {
	height: 100%;
}

#page-wrapper {
	min-height: 92%;
}
/* 네비게이션 이미지와 타이틀 여백 조절_정호 */
span.navbar-brand {
	padding: 18px 0px 0px 10px;
}

a.navbar-brand {
	padding: 6px 0px 0px 15px;
}
</style>


</head>
<body>

	<div id="wrapper">
		<!-- Navigation 시작 -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<!-- Navigation header 영역 -->
			<div class="navbar-header">

				<a class="navbar-brand" href="ad_info_courseNameList.it"><img
					src="/grade_Management_System_0120/img/sist_logo.png"></a> <span
					class="navbar-brand">성적처리프로그램 v2.0 <small>관리자용</small></span>
				<!-- <span class="navbar-brand"><img title="계정별 아이콘"></span> -->
			</div>
			<!-- Navibar header 영역 종료 -->

			<!-- 사용자 계정 관련 Dropdown 메뉴 아이콘 -->
			<ul class="nav navbar-top-links navbar-right">
				<!-- 사용자 이름 보이기 -->
				<li class="navbar-brand"><small> ${sessionScope.admin_id} 관리자님 환영합니다.</small></li>
				<!-- 해당 아이콘 클릭 시 dropdown 이벤트 발생 -->
				<li class="dropdown">
					<!-- 아이콘 image파일 포함 영역 --> <a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a> <!-- dropdown시 포함된 컨텐츠 -->
					<ul class="dropdown-menu dropdown-user">
						<li><a href="#"><i class="fa fa-user fa-fw"></i> User
								Profile</a></li>
						<li class="divider"></li>
						<li><a href="logOut.it?key=admin"><i class="fa fa-sign-out fa-fw"></i>
								Logout</a></li>
					</ul> <!-- /.dropdown-user -->
				</li>
				<!-- /.dropdown 종료-->
			</ul>
			<!-- /.navbar-top-links -->


			<!-- 사이드바 메뉴 시작 -->
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<!-- 사이드바 메뉴 컨텐츠가 포함되는 영역 START -->
					<ul class="nav" id="side-menu">
						<!-- 관리자 - 1. 기초 정보 관리 -->
						<li>
							<!-- class="원하는 그림이 클래스화 되어 있습니다." 문의해주세요~ --> <a href="#"> <i
								class="fa fa-info-circle fa-fw"></i> 기초 정보 관리 <span
								class="fa arrow"></span></a> <!-- 기초 정보 관리 드롭다운 메뉴 -->
							<ul class="nav nav-second-level">
								<!-- 과정명, 과목명, 강의실명(정원), 교재명(출판사) -->
								<li><a href="ad_info_courseNameList.it"> <i
										class="fa fa-table fa-fw"></i> 과정명
								</a> <!-- /.nav-second-level --></li>
								<li><a href="ad_info_subjectNameInfoList.it"> <i
										class="fa fa-file-text-o fa-fw"></i> 과목명
								</a> <!-- /.nav-second-level --></li>
								<li><a href="ad_info_classroomList.it"><i
										class="glyphicon glyphicon-blackboard"></i> 강의실명(정원)</a> <!-- /.nav-second-level --></li>
								<li><a href="ad_info_bookList.it"><i
										class="fa fa-book fa-fw"></i> 교재명(출판사)</a> <!-- /.nav-second-level --></li>
							</ul>
						</li>

						<!-- 관리자 - 2. 강사 계정 관리 -->
						<li>
							<!-- class="원하는 그림이 클래스화 되어 있습니다." 문의해주세요~ --> <a
							href="ad_ins_instructorList.it"><i class="fa fa-male fa-fw"></i>
								강사 관리</a>
						</li>
						<!-- 관리자 - 3. 개설 과정 관리 -->
						<li>
							<!-- class="원하는 그림이 클래스화 되어 있습니다." 문의해주세요~ --> <a
							href="ad_cr_courseList.it"><i class="fa fa-table fa-fw"></i>
								개설 과정 관리</a> <!-- /.nav-second-level -->
						</li>
						<!-- 관리자 - 4. 개설 과목 관리 -->
						<li>
							<!-- class="원하는 그림이 클래스화 되어 있습니다." 문의해주세요~ --> <a
							href="ad_sj_courseList.it"><i class="fa fa-file-text-o fa-fw"></i>
								개설 과목 관리</a> <!-- /.nav-second-level -->
						</li>
						<!-- 관리자 - 5. 학생 관리 -->
						<li>
							<!-- class="원하는 그림이 클래스화 되어 있습니다." 문의해주세요~ --> <a href="#"><i
								class="fa fa-graduation-cap fa-fw"></i> 학생 관리<span
								class="fa arrow"></span></a> <!-- 학생 관리 드롭다운 메뉴 -->
							<ul class="nav nav-second-level">
								<!-- 전체 검색, 과정 검색 -->


								<li><a href="ad_st_allStudentList.it"><i
										class="fa fa-bars fa-fw"></i> 전체 검색</a> <!-- /.nav-second-level --></li>
								<li><a href="ad_st_allCourseList.it"><i
										class="fa fa-list-ul fa-fw"></i> 과정 검색</a> <!-- /.nav-second-level --></li>

							</ul>
						</li>
						<!-- 관리자 - 6. 성적 조회 -->
						<li>
							<!-- class="원하는 그림이 클래스화 되어 있습니다." 문의해주세요~ --> <a href="#"><i
								class="fa fa-pencil fa-fw"></i> 성적 조회<span class="fa arrow"></span></a>
							<!-- 성적 조회 드롭다운 메뉴 -->
							<ul class="nav nav-second-level">


								<!-- 과목 기준 조회, 학생 기준 조회 -->
								<%-- !!!!!!!!!경로명 수정 필요(성적조회 servlet연결 후)!!!!!! --%>
								<li><a href="ad_gr_courseList.it"><i
										class="fa fa-file-text-o fa-fw"></i> 과목 기준 조회</a> <!-- /.nav-second-level --></li>
								<li><a href="ad_gr_allStudentList.it"><i
										class="fa fa-child fa-fw"></i> 학생 기준 조회</a> <!-- /.nav-second-level --></li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<!-- content의 가장 큰 제목을 입력하시면 됩니다. -->

			<div class="row">
				<div class="col-lg-12">
					<%-- 
				request.setAttribute("list", list);
				request.setAttribute("sjlist", sjlist);
				request.setAttribute("totalcount", totalcount);
		
				ad.setCourse_id(rs.getString("course_id"));
				ad.setCourse_name(rs.getString("course_name"));
				ad.setCr_start_date(rs.getString("cr_start_date"));
				ad.setCr_end_date(rs.getString("cr_end_date"));
				ad.setCountSub(rs.getInt("countSub"));
		
				--%>
					<!-- <h4 style="padding-top: 10px">cs001 [100%국비지원]오라클 전문가 과정(2016-12-01~2017-03-30)</h4> -->
					<h4 style="padding-top: 10px" id="title">
						<span>${list[0].course_id}</span> <span>${list[0].course_name}</span>
						<span>(${list[0].cr_start_date}~</span> <span>${list[0].cr_end_date})</span>
					</h4>
					<hr>
					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover subject_data">
							<thead>
								<tr>
									<th>과목ID</th>
									<th>개설과목</th>
									<th>과목기간</th>
									<th>교재명</th>
									<th>강사명</th>
									<th style="width: 20px">수 정</th>
									<th style="width: 20px">삭 제</th>
								</tr>
							</thead>
							<tbody>
								<%-- 
								<tr>
									<td>sj002</td>
									<td>오라클</td>
									<td>2016-11-01~2017-02-28</td>
									<td>오라클ABCD</td>
									<td>홍길동</td>
									<td><button type="button"
											class="btn btn-default btn-xs modify">수정</button></td>
									<td><button type="button"
											class="btn btn-default btn-xs remove">삭제</button></td>
								</tr>
								<tr>
									<td>sj003</td>
									<td>자바</td>
									<td>2017-03-01~2017-03-31</td>
									<td>cs003</td>
									<td>[100%국비지원]오라클 전문가</td>
									<td>2016-11-01~2017-03-31</td>
									<td>자바ABCD</td>
									<td>홍길동</td>
									<td><button type="button"
											class="btn btn-default btn-xs modify">수정</button></td>
									<td><button type="button"
											class="btn btn-default btn-xs remove">삭제</button></td>
								</tr>
								--%>

								<%-- 
				request.setAttribute("list", list);
				request.setAttribute("sjlist", sjlist);
				request.setAttribute("totalcount", totalcount);
		
				ad.setCourse_id(rs.getString("course_id"));
				ad.setCourse_name(rs.getString("course_name"));
				ad.setCr_start_date(rs.getString("cr_start_date"));
				ad.setCr_end_date(rs.getString("cr_end_date"));
				ad.setCountSub(rs.getInt("countSub"));
		
				ad.setSubject_id(rs.getString("subject_id"));
				ad.setSubject_name(rs.getString("subject_name"));
				ad.setSj_start_date(rs.getString("sj_start_date"));
				ad.setSj_end_date(rs.getString("sj_end_date"));
				ad.setCourse_id(rs.getString("course_id"));
				ad.setCourse_name(rs.getString("course_name"));
				ad.setCr_start_date(rs.getString("cr_start_date"));
				ad.setCr_end_date(rs.getString("cr_end_date"));
				ad.setBook_name(rs.getString("book_name"));
				ad.setIn_name(rs.getString("name"));
		
				--%>

								<c:forEach var="a" items="${sjlist}">
									<tr>									
										<td>${a.subject_id}</td>
										<td>${a.subject_name}</td>
										<td><span>${a.sj_start_date}</span>~<span>${a.sj_end_date}</span></td>
										<td><a href="#" class="book_img">${a.book_name}</a></td>
										<td>${a.in_name}</td>
										<td><button type="button"
												class="btn btn-default btn-xs modify">수정</button></td>
										<td><button type="button"
												class="btn btn-default btn-xs remove">삭제</button></td>
									</tr>
									<input type="hidden" value="${a.book_id}">
									<input type="hidden" value="${a.instructor_id}" name="ins_id" id="instructor_id">
								</c:forEach>

							</tbody>
						</table>
						<button type="button" class="btn btn-default addSubject"
							style="float: left;">과목등록</button>
						<p align="right">
							<span id="to_count">${totalcount}</span>개의 과목 검색
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%-- 과목추가 Modal --%>
	<div id="admin_basicInfo" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">신규 과목 개설</h4>
					<h4 class="modal-title">
						<span>${list[0].course_id}</span> <span>${list[0].course_name}</span>
						<span><small>(${list[0].cr_start_date}~</small></span> <span><small>${list[0].cr_end_date})</small></span>
					</h4>
				</div>

				<div class="modal-body">

					<form action="ad_sj_addSubject.it" method="post" class="form-horizontal" role="form">
<!-- 						<input type="hidden" id="subject_id" name="subject_id">  -->
<!-- 						<input type="hidden" id="start_date" name="start_date">  -->
<!-- 						<input type="hidden" id="end_date" name="end_date">  -->
<!-- 						<input type="hidden" id="book_id" name="book_id">  -->
<!-- 						<input type="hidden" id="ins_id" name="ins_id">  -->
<%-- 						<input type="hidden" id="course_id" name="course_id" value="${list[0].course_id}"> --%>
						<ul>
							<li>과목 정보를 등록하세요.</li>
						</ul>

						<input type="hidden" value="${list[0].course_id}" name="course_id">
						<div class="form-group">
							<label class="control-label col-sm-2" for="course_id">과목명ID:</label>
							<div class="col-sm-10">
								<select class="form-control" id="mySubject_select" name="subject_name_id">
									<!-- 									<option>sn001 오라클</option> -->
									<!-- 									<option>sn002 자바</option> -->
									<!-- 									<option>sn003 SQL</option> -->
									<option>--과목명을 선택하세요--</option>
									<c:forEach var="sj" items="${totalSjlist}">
										<option value="${sj.subject_name_id}">${sj.subject_name_id}-${sj.subject_name}</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-sm-2" for="sj_start_day">과목개설날짜:</label>
							<div class="col-sm-10">
								<input type="date" id="sj_start_day" class="form-control date" name="start_date">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="sj_end_day">과목종강날짜:</label>
							<div class="col-sm-10">
								<input type="date" id="sj_end_day" class="form-control date" name="end_date">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="book">교재선택:</label>
							<div class="col-sm-10">
								<select class="form-control" id="myBook_select" name="book_id">
									<!-- 									<option>bn001 자바ABCD / 한빛</option> -->
									<!-- 									<option>bn002 오라클ABCD / 한빛</option> -->
									<!-- 									<option>bn003 C언어ABCD / 웅진</option> -->
									<option>--교재를 선택하세요--</option>
									<c:forEach var="book" items="${totalBooklist}">
										<option value="${book.book_id}">${book.book_name}/${book.publisher}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="teacher">강사선택:</label>
							<div class="col-sm-10">
								<select class="form-control" id="myIns_select" name="ins_id">
									<!-- 		request.setAttribute("totalInstructorlist", totalInstructorlist); -->
									<!-- 						ad.setInstructor_id(rs.getString("instructor_id")); -->
									<!-- 				ad.setIn_name(rs.getString("name")); -->
									<!-- 				ad.setIn_social_num(rs.getString("social_num")); -->
									<!-- 				ad.setIn_phone(rs.getString("phone")); -->
									<!-- 									<option>in001 이민호</option> -->
									<!-- 									<option>in002 박세원</option> -->
									<!-- 									<option>in003 이민종</option> -->
									<option>--강사를 선택하세요--</option>
									<c:forEach var="ins" items="${totalInstructorlist}">
										<option value="${ins.instructor_id}">${ins.instructor_id}-
											${ins.in_name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 선택한 정보를 추가할까요?</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<!-- 서브밋 버튼 클릭시 hidden form의 데이터(번호)가 서버로 전송된다. -->
								<button type="submit" class="btn btn-default okButton">확인</button>
								<!-- 취소 버튼 클릭시 현재 모달창을 닫는다. -->
								<button type="button" class="btn btn-default" id="modalclose"
									data-dismiss="modal">취소</button>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>

	<%-- 수정 modal --%>
	<div id="course_modify_modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">과목 수정</h4>
					<h4 class="modal-title" id="modify_title">
						<span>${list[0].course_id}</span> <span>${list[0].course_name}</span>
						<span><small>(${list[0].cr_start_date}~</small></span> <span><small>${list[0].cr_end_date})</small></span>
					</h4>

				</div>
				<div class="modal-body">

					<%-- action="studentdelete.jsp" 속성의 주소 변경 --%>
					<form action="ad_sj_modifySubject.it" method="post" class="form-horizontal">
						<ul>
							<li>과목 정보를 수정하세요.</li>
						</ul>
						<!--  
						<div class="form-group">
							<label class="control-label col-sm-2" for="course_id">과정ID:</label>
							<div class="col-sm-10">
								<input type="text" id="course" class="form-control"
									value="cs001 [100%국비지원]오라클 전문가 과정(2016-12-01~2017-03-30)"
									readonly>
							</div>
						</div>
						-->
						<input type="hidden" name="course_id" id="modify_course_id" value="${list[0].course_id}">
						<input type="hidden" name="subject_id" id="modify_subject_id2" value="">
						
						<div class="form-group">
							<label class="control-label col-sm-2" for="modify_subject_id">과목ID:</label>
							<div class="col-sm-10">
								<input type="text" id="modify_subject_id" value="" name=""
									class="form-control" required readonly="readonly">
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-sm-2" for="modify_sj_start_date">과목개설날짜:</label>
							<div class="col-sm-10">
								<input type="date" id="modify_sj_start_date" name="start_date"
									class="form-control date" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="modify_sj_end_date">과목종강날짜:</label>
							<div class="col-sm-10">
								<input type="date" id="modify_sj_end_date" name="end_date"
									class="form-control date" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="modify_book">교재선택:</label>
							<div class="col-sm-10">
								<select class="form-control" id="modify_book" name="book_id">
									<c:forEach var="book" items="${totalBooklist}">
										<option value="${book.book_id}">${book.book_name}/${book.publisher}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="instructor">강사선택:</label>
							<div class="col-sm-10">
								<select class="form-control" id="modify_instructor" name="instructor_id">
									<c:forEach var="ins" items="${totalInstructorlist}">
										<option value="${ins.instructor_id}">${ins.instructor_id}
											- ${ins.in_name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 수정한 과목정보를 저장할까요?</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<!-- 서브밋 버튼 클릭시 hidden form의 데이터(번호)가 서버로 전송된다. -->
								<button type="submit" class="btn btn-default">확인</button>
								<!-- 취소 버튼 클릭시 현재 모달창을 닫는다. -->
								<button type="button" class="btn btn-default"
									data-dismiss="modal">취소</button>
							</div>
						</div>
					</form>

				</div>
			</div>

		</div>
	</div>

	<%-- 과목삭제 form Modal --%>
	<div id="removeModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content modal-lg">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">과목 삭제</h4>
					<h4 class="modal-title">
						<span>${list[0].course_id}</span> <span>${list[0].course_name}</span>
						<span><small>(${list[0].cr_start_date}~</small></span> <span><small>${list[0].cr_end_date})</small></span>
					</h4>
				</div>
				<div class="modal-body">

					<form action="ad_sj_deleteSubject.it" method="post" class="form-inline">

						<div>
							<p>해당 과목을 삭제하시겠습니까?</p>
							<div class="list">
							<input type="hidden" name="subject_id" id="delete_subject_id" value="">
							<input type="hidden" name="course_id" value="${list[0].course_id}">
							<input type="hidden" name="subject_id" id="delete_subject_id" value="">
							
								<table class="table table-striped">
									<tr>
										<th>과목ID</th>
										<th>과목명</th>
										<th>과목기간</th>
										<th>교재명</th>
										<th>강사명</th>
									</tr>
									<tr>
										<td id="sid"></td>
										<td id="subject_name"></td>
										<td id="subject_date"></td>
										<td id="class_room"></td>
										<td id="count"></td>
									</tr>
								</table>
							</div>
						</div>


						<div class="modal-footer" style="border-top: 0px #FFFFFF;">
							<p>현재 과목 정보를 삭제할까요?</p>
							<button type="submit" class="btn btn-default">확인</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">취소</button>
						</div>
					</form>
				</div>
				<div class="modal-footer"></div>
			</div>

		</div>
	</div>
		<!-- 교재사진보기 -->
	<div id="pictureModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>

					<%-- 사진 보기 선택한 교재명 출력 --%>
					<h4 class="modal-title pictureName"></h4>

				</div>
				<div class="modal-body">
					<div style="text-align: center;">
						<%-- Ajax 요청에 대한 응답 결과를 가지고 이미지 처리 --%>
						<img src="${pageContext.request.contextPath}/picture/book.jpg"
							width="100%">

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- jQuery -->
	<script src="/grade_Management_System_0120/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="/grade_Management_System_0120//vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="/grade_Management_System_0120//vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script
		src="/grade_Management_System_0120//vendor/raphael/raphael.min.js"></script>
	<script
		src="/grade_Management_System_0120//vendor/morrisjs/morris.min.js"></script>
	<script src="/grade_Management_System_0120//data/morris-data.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="/grade_Management_System_0120//dist/js/sb-admin-2.js"></script>

</body>
</html>