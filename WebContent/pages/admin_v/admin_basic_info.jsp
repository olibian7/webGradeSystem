<!-- 신규 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

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

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>


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

.list {
	max-height: 430px;
	overflow: auto;
	margin-bottom: 20px;
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
/* 정보 검색 위치 조절_정호 */
.seach_info {
	position: absolute;
	right: 15px;
	padding-top: 8px;
}

.seachBn_jho {
	margin-right: 100px;
}

.addCourse {
	float: left;
}
</style>
<script>
	$(document).ready(function() {

		//검색 진행후 검색 키워드가 화면에 그대로 남아있도록 액션 추가
		//-> 검색 폼 부분에 기존 검색 키워드를 재설정
		$("#skey").on("change", function() {
			$("#svalue").val("");
		});

		$("#skey").val("${skey}").attr("selected", "selected");
		$("#svalue").val("${svalue}");

		//수정 버튼 클릭시 해당 과정명ID, 과정명 불러오기
		$("button.modify").on("click", function() {
			$("#id2").val($(this).parents("tr").children().eq(0).text());
			$("#course2").val($(this).parents("tr").children().eq(1).text());
			$("#admin_basicInfo_course_modify").modal();
		});

		//삭제 버튼 클릭시 해당 과정명ID, 과정명 불러오기
		$("button.delete").on("click", function() {
			$("#id3").val($(this).parents("tr").children().eq(0).text());
			$("#course3").val($(this).parents("tr").children().eq(1).text());
			$("#admin_basicInfo_course_delete").modal();
		});

	});
</script>
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

		<!-- content 포함 영역 START!! -->
		<div id="page-wrapper">
			<!-- content의 가장 큰 제목을 입력하시면 됩니다. -->
			<!-- /.row -->

			<!-- content의 내용을 입력하시면 됩니다. table이라던지 기타 등등 집어넣으세요! -->
			<div class="row">
				<div class="col-lg-12">
					<h4 style="padding-top: 10px">기초 정보 관리 > 과정명</h4>
					<hr>
					<!-- /.panel-heading -->
					<div class="table-responsive list">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>과정명ID</th>
									<th>과정명</th>
									<th style="width: 20px">수정</th>
									<th style="width: 20px">삭제</th>
								</tr>
							</thead>
							<tbody>
								<%--
							<tr>
								<td>cn001</td>
								<td>[100%국비지원]오라클 전문가 과정</td>
								<td><a href="#"><button
											class="btn btn-default btn-xs modify">수정</button></a></td>
								<td><a href="#"><button
											class="btn btn-default btn-xs delete">삭제</button></a></td>
							</tr>
							<tr>
								<td>cn002</td>
								<td>[100%국비지원]자바 전문가 과정</td>
								<td><a href="#"><button
											class="btn btn-default btn-xs modify">수정</button></a></td>
								<td><a href="#"><button
											class="btn btn-default btn-xs delete">삭제</button></a></td>
							</tr>
							<tr>
								<td>cn003</td>
								<td>[100%국비지원]자바스크립트 전문가 과정</td>
								<td><a href="#"><button
											class="btn btn-default btn-xs modify">수정</button></a></td>
								<td><a href="#"><button
											class="btn btn-default btn-xs delete">삭제</button></a></td>
							</tr>
							<tr>
								<td>cn004</td>
								<td>[100%국비지원]HTML5 전문가 과정</td>
								<td><a href="#"><button
											class="btn btn-default btn-xs modify">수정</button></a></td>
								<td><a href="#"><button
											class="btn btn-default btn-xs delete">삭제</button></a></td>
							</tr>
							<tr>
								<td>cn005</td>
								<td>[100%국비지원]안드로이드 전문가 과정</td>
								<td><a href="#"><button
											class="btn btn-default btn-xs modify">수정</button></a></td>
								<td><a href="#"><button
											class="btn btn-default btn-xs delete">삭제</button></a></td>
							</tr>
							<tr>
								<td>cn006</td>
								<td>[100%국비지원]Spring 전문가 과정</td>
								<td><a href="#"><button
											class="btn btn-default btn-xs modify">수정</button></a></td>
								<td><a href="#"><button
											class="btn btn-default btn-xs delete">삭제</button></a></td>
							</tr>
							<tr>
								<td>cn007</td>
								<td>[100%국비지원]My SQL 전문가 과정</td>
								<td><a href="#"><button
											class="btn btn-default btn-xs modify">수정</button></a></td>
								<td><a href="#"><button
											class="btn btn-default btn-xs delete">삭제</button></a></td>
							</tr>
							<tr>
								<td>cn008</td>
								<td>[100%국비지원]Servlet/JSP 전문가 과정</td>
								<td><a href="#"><button
											class="btn btn-default btn-xs modify">수정</button></a></td>
								<td><a href="#"><button
											class="btn btn-default btn-xs delete">삭제</button></a></td>
							</tr>
							<tr>
								<td>cn009</td>
								<td>[100%국비지원]C 언어 전문가 과정</td>
								<td><a href="#"><button
											class="btn btn-default btn-xs modify">수정</button></a></td>
								<td><a href="#"><button
											class="btn btn-default btn-xs delete">삭제</button></a></td>
							</tr>
						--%>
								<c:forEach var="a" items="${list}">
									<tr>
										<td>${a.course_name_id}</td>
										<td>${a.course_name}</td>
										<td><a href="#"><button
													class="btn btn-default btn-xs modify">수정</button></a></td>
										
										<td><a href="#"><button type="button"
			class="btn btn-default btn-xs delete"
			value="${a.course_name_id}"
			<c:forEach var="b" items="${check_list}"> ${b.course_name_id==a.course_name_id?"disabled=\"disabled\"":""}</c:forEach>>삭제</button></a></td>
																		</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<!-- 검색 폼 -->
					<div style="text-align: center;">
						<button type="button" class="btn btn-default addCourse"
							data-toggle="modal" data-target="#admin_basicInfo_course">과정명
							등록</button>
						<form method="post" class="form-inline" action="ad_info_courseNameList.it">
							<div class="form-group">
								<select class="form-control" id="skey" name="skey">
									<option value="all">전체</option>
									<option value="course_name_id">과정명ID</option>
									<option value="course_name">과정명</option>
									<!-- 검색 기준 추가 -->
								</select> <input type="text" class="form-control" id="svalue"
									name="svalue">
								<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
							</div>
							<button type="submit" class="btn btn-default xs seachBn_jho">
								<i class="fa fa-search"></i> Search
							</button>
							<span class="seach_info">총 <span>${length}</span>개의 과정명 검색
							</span>
						</form>
					</div>

				</div>

			</div>
			<!-- /.table-responsive -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<%-- 과정명 추가 Modal --%>
	<div id="admin_basicInfo_course" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">과정명 추가</h4>
				</div>
				<div class="modal-body">

					<%-- action="studentdelete.jsp" 속성의 주소 변경 --%>
					<form action="ad_info_addCourseName.it" method="post">

						<%-- id="" 속성의 식별자 유일한 이름을 가지도록 수정 --%>
						<div class="form-group">
							<label for="course">과정명:</label> <input
								class="form-control form-inline" id="course1" name="course"
								maxlength="40" required></input>
						</div>
						<div class="form-group">현재 입력한 정보를 추가할까요?</div>

						<!-- 서브밋 버튼 클릭시 hidden form의 데이터(번호)가 서버로 전송된다. -->
						<button type="submit" class="btn btn-default">확인</button>

						<!-- 취소 버튼 클릭시 현재 모달창을 닫는다. -->
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>

					</form>

				</div>
			</div>

		</div>
	</div>

	<%-- 과정명 수정 Modal --%>
	<div id="admin_basicInfo_course_modify" class="modal fade"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">과정명 수정</h4>
				</div>
				<div class="modal-body">

					<%-- action="studentdelete.jsp" 속성의 주소 변경 --%>
					<form action="ad_info_modifyCourseName.it" method="post">

						<%-- id="" 속성의 식별자 유일한 이름을 가지도록 수정 --%>
						<div class="form-group">
							<label for="id">과정명ID:</label> <input
								class="form-control form-inline" id="id2" name="id" readonly></input>
						</div>
						<div class="form-group">
							<label for="course">과정명:</label> <input
								class="form-control form-inline" id="course2" name="course"
								maxlength="40" required></input>
						</div>
						<div class="form-group">현재 수정한 정보를 저장할까요?</div>

						<!-- 서브밋 버튼 클릭시 hidden form의 데이터(번호)가 서버로 전송된다. -->
						<button type="submit" class="btn btn-default">확인</button>

						<!-- 취소 버튼 클릭시 현재 모달창을 닫는다. -->
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>

					</form>

				</div>
			</div>

		</div>
	</div>

	<%-- 과정명 삭제 Modal --%>
	<div id="admin_basicInfo_course_delete" class="modal fade"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">과정명 삭제</h4>
				</div>
				<div class="modal-body">

					<%-- action="studentdelete.jsp" 속성의 주소 변경 --%>
					<form action="ad_info_deleteCourseName.it" method="post">

						<%-- id="" 속성의 식별자 유일한 이름을 가지도록 수정 --%>
						<div class="form-group">
							<label for="id">과정명ID:</label> <input
								class="form-control form-inline" id="id3" name="id" readonly></input>
						</div>
						<div class="form-group">
							<label for="course">과정명:</label> <input
								class="form-control form-inline" id="course3" name="course"
								maxlength="40" readonly></input>
						</div>
						<div class="form-group">현재 과정명 정보를 삭제할까요?</div>

						<!-- 서브밋 버튼 클릭시 hidden form의 데이터(번호)가 서버로 전송된다. -->
						<button type="submit" class="btn btn-default">확인</button>

						<!-- 취소 버튼 클릭시 현재 모달창을 닫는다. -->
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>

					</form>

				</div>
			</div>

		</div>
	</div>
	<!-- jQuery -->
	<script src="/grade_Management_System_0120/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="/grade_Management_System_0120/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="/grade_Management_System_0120/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script
		src="/grade_Management_System_0120/vendor/raphael/raphael.min.js"></script>
	<script
		src="/grade_Management_System_0120/vendor/morrisjs/morris.min.js"></script>
	<script src="/grade_Management_System_0120/data/morris-data.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="/grade_Management_System_0120/dist/js/sb-admin-2.js"></script>

</body>

</html>

