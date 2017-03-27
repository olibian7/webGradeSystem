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

<script>
	$(document).ready(function(){
		
		$(".book_img").on("click", function() {
			//서버에 사진 정보를 확인하기 위한 직원번호를 전송한다.
			console.log($("#pictureModal").find("img").attr("src"));
			var book_id = $(this).parents("tr").children().eq(0).find("input").val();
			//책제목 동적 출력 과정 추가
			$("#pictureModal").find("h4").text($(this).text());
			$.post("student_picture_ajax.it", {book_id : book_id},function(data) {
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
				<!-- 해당 아이콘 클릭 시 dropdown 이벤트 발생 -->
				<!-- 사용자 이름 보이기 -->
				<li class="navbar-brand"><small> ${user_id}님 환영합니다.</small></li>
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
						<li><a href="st_studentInfo.it"><i
								class="fa fa-users fa-fw"></i> 사용자 정보 조회</a></li>
						<!-- 학생 - 2. 성적 및 과정 조회 -->
						<li class="active"><a href="st_gradeList.it"><i
								class="fa fa-pencil-square fa-fw"></i> 나의 성적 조회</a> <!-- /.nav-second-level -->
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

			<!-- content의 내용을 입력하시면 됩니다. table이라던지 기타 등등 집어넣으세요! -->
			<div class="row">
				<div class="col-lg-12">
					<h4 style="padding-top: 10px">나의 성적 조회 > 과정조회 > 과목조회</h4>
					<hr>
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>과정ID</th>
									<th>과정명</th>
									<th>과정기간</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${course_id}</td>
									<td>${course_name}</td>
									<td>${cr_start_date} ~ ${cr_end_date}</td>
								</tr>
							</tbody>
						</table>
					</div>

					<!-- /.panel -->
					<div class="panel panel-default">
						<div class="panel-heading">${course_name} ${cr_start_date} ~ ${cr_end_date}<!-- (2016-11-01
							~ 2017-03-31) --></div>
						<div class="panel-body">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>과목ID</th>
										<th>과목명</th>
										<th>과목기간</th>
										<th>교재명</th>
										<th>강사명</th>
										<th>출결/배점</th>
										<th>필기/배점</th>
										<th>실기/배점</th>
										<th>시험날짜</th>
										<th>시험문제</th>
									</tr>
								</thead>
								<tbody>
									<!-- <tr>
										<td>sj003</td>
										<td>오라클</td>
										<td>2016-11-01 ~ 2017-01-30</td>
										<td><a href="#">JAVA</a></td>
										<td>최길동</td>
										<td>10/20</td>
										<td>20/30</td>
										<td>30/50</td>
										<td>2017-01-10</td>
										<td><a href="#">java.zip</a></td>
									</tr> -->
									<c:forEach var="m" items="${list}">
										<tr>
											<td hidden="hidden"><input type="hidden" name="book_id" value="${m.book_id}"></td>
											<td>${m.scsv_subject_id}</td>
											<td>${m.subject_name}</td>
											<td>${m.sj_start_date} ~ ${m.sj_end_date}</td>
											<td><a href="#" class="book_img">${m.book_name}</a></td>
											<td>${m.in_name}</td>
											<td>${m.gr_attend}/${m.sc_attend}</td>
											<td>${m.gr_writing}/${m.sc_writing}</td>
											<td>${m.gr_practice}/${m.sc_practice}</td>
											<td>${m.test_date}</td>
											<td><a href="doDownload.it?exam=${m.test_paper}">${m.test_paper_name}</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!-- /.table-responsive -->

				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

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

