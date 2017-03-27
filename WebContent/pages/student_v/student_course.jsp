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

<title>환영합니다 ${user_id}학생</title>

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

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
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
<script type="text/javascript">
<!-- <a href="st_courseList.it"> -->
	/* <input type="hidden" id = "c_id" value="${m.course_id}">	 */
	function courseBn() {

	}
</script>
</head>

<body>

	<div id="wrapper">

		<!-- Navigation 시작 -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<!-- Navigation header 영역 -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="st_studentInfo.it"><img
					src="/grade_Management_System_0120/img/sist_logo.png"></a> <span
					class="navbar-brand">성적처리프로그램 v2.0 <small>학생용</small></span>
				<!-- <span class="navbar-brand"><img title="계정별 아이콘"></span> -->

			</div>
			<!-- Navibar header 영역 종료 -->

			<!-- 사용자 계정 관련 Dropdown 메뉴 아이콘 -->
			<ul class="nav navbar-top-links navbar-right">
				<!-- 사용자 이름 보이기 -->
				<li class="navbar-brand"><small> ${user_id}님 환영합니다.</small></li>
				<!-- 해당 아이콘 클릭 시 dropdown 이벤트 발생 -->
				<li class="dropdown">
					<!-- 아이콘 image파일 포함 영역 --> <a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a> <!-- dropdown시 포함된 컨텐츠 -->
					<ul class="dropdown-menu dropdown-user">
						<li><a href="st_studentInfo.it"><i class="fa fa-user fa-fw"></i> User
								Profile</a></li>
						<li class="divider"></li>
						<li><a href="logOut.it?key=st"><i class="fa fa-sign-out fa-fw"></i>
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
						<!-- 학생 - 1. 사용자 정보 조회 -->
						<li class="active"><a href="st_studentInfo.it"><i
								class="fa fa-users fa-fw"></i> 사용자 정보 조회</a></li>
						<!-- 학생 - 2. 성적 및 과정 조회 -->
						<li><a href="#"><i class="fa fa-pencil-square fa-fw"></i>
								나의 성적 조회</a> <!-- /.nav-second-level --></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<!-- content 포함 영역 START!! -->
		<div id="page-wrapper">
			<!-- content의 내용을 입력하시면 됩니다. table이라던지 기타 등등 집어넣으세요! -->
			<div class="row">
				<div class="col-lg-12">
					<h4 style="padding-top: 10px">나의 성적 조회 > 과정조회</h4>
					<hr>
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>과정 ID</th>
									<th>과정명</th>
									<th>과정기간</th>
									<th style="width: 40px">과목조회</th>
								</tr>
							</thead>
							<tbody>
								<!-- <tr>
									<td>sc001</td>
									<td>[100% 국비지원] 오라클 전문가 과정</td>
									<td>2016-11-01 ~ 2017-03-31</td>
									<td><a href="student_course_detail.jsp"><button
												class="btn btn-default btn-xs">과목조회</button></a></td>
								</tr> -->
								<c:forEach var="m" items="${list}">
									<tr>
										<td>${m.course_id}</td>
										<td>${m.course_name}</td>
										<td>${m.cr_start_date}~${m.cr_end_date}</td>
										<td><form action="st_courseList.it" method="post">
												<input type="hidden" name="course_id" value="${m.course_id}">
												<input type="hidden" name="course_name"
													value="${m.course_name}"> <input type="hidden"
													name="cr_start_date" value="${m.cr_start_date}"> <input
													type="hidden" name="cr_end_date" value="${m.cr_end_date}">
												<button class="btn btn-default btn-xs" type="submit">과목조회</button>
											</form></td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->

				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

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

