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

<script>
	$(document).ready(function() {

		//문제) 삭제 버튼에 click 이벤트 등록 및 실행
		//버튼이 있는 라인의 번호, 이름 정보를 얻어서 화면에 출력 + hidden form 지정.
		$("button.choose").on("click", function() {
			$("#st_id").val($(this).parents("tr").children().eq(0).text());
			$("#st_name").val($(this).parents("tr").children().eq(1).text());
			/* $("#st_pw").val($(this).parents("tr").children().eq(2).text()); */
			$("#st_phone").val($(this).parents("tr").children().eq(2).text());
			$("#st_reg_date").val($(this).parents("tr").children().eq(3).text());
			$("#student_info").modal();
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
						<li><a href="#"><i class="fa fa-user fa-fw"></i> User
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
						<li class="active">
							<!-- class="원하는 그림이 클래스화 되어 있습니다." 문의해주세요~ --> <a
							href="student_info.jsp"><i class="fa fa-users fa-fw"></i> 사용자
								정보 조회</a>
						</li>
						<!-- 학생 - 2. 성적 및 과정 조회 -->
						<li>
							<!-- class="원하는 그림이 클래스화 되어 있습니다." 문의해주세요~ --> <a
							href="student_course.jsp"><i
								class="fa fa-pencil-square fa-fw"></i> 나의 성적 조회</a> <!-- /.nav-second-level -->
						</li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<!-- 사이드바 메뉴 컨텐츠가 포함되는 영역 START -->
					<ul class="nav" id="side-menu">
						<!-- 학생 - 1. 사용자 정보 조회 -->
						<li class="active">
							<!-- class="원하는 그림이 클래스화 되어 있습니다." 문의해주세요~ --> <a href="#"><i
								class="fa fa-users fa-fw"></i> 사용자 정보 조회</a>
						</li>
						<!-- 학생 - 2. 성적 및 과정 조회 -->
						<li>
							<!-- class="원하는 그림이 클래스화 되어 있습니다." 문의해주세요~ --> <a
							href="st_gradeList.it"><i class="fa fa-pencil-square fa-fw"></i>
								나의 성적 조회</a> <!-- /.nav-second-level -->
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
			<!-- <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">사용자 정보</h1>
                </div>
                /.col-lg-12
            </div> -->
			<!-- /.row -->

			<!-- content의 내용을 입력하시면 됩니다. table이라던지 기타 등등 집어넣으세요! -->
			<div class="row">
				<div class="col-lg-12">
					<!--      <div class="panel panel-default">
                        <div class="panel-heading"> -->
					<h4 style="padding-top: 10px">사용자 정보 조회</h4>
					<hr>
					<!-- </div> -->
					<!-- /.panel-heading -->
					<!-- <div class="panel-body"> -->
					<div class="table-responsive">
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>학생 ID</th>
									<th>이 름</th>
									<!-- <th>주민등록번호 뒤 7자리</th> -->
									<th>전화번호</th>
									<th>과정 등록일</th>
									<th>수 정</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<c:forEach var="m" items="${list}">
										<tr>
											<td>${m.student_id}</td>
											<td>${m.name}</td>
											<%-- <td>${m.social_num}</td> --%>
											<td>${m.phone}</td>
											<td>${m.registration_date}</td>
											<td style="width: 20px"><button
													class="btn btn-default btn-xs choose">수정</button></td>
										</tr>

									</c:forEach>

								</tr>
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->
					<!--      </div>
                        /.panel-body
                    </div>
                    /.panel -->
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

	<%-- 학생수정 Modal --%>
	<div id="student_info" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn-xs close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">사용자 정보 수정</h4>
				</div>
				<div class="modal-body">

					<form action="st_modifyStudentInfo.it" method="post" class="form-horizontal">
						<ul>
							<li>사용자 정보를 등록하세요.</li>
						</ul>
							<div class="form-group">
								<label class="control-label col-sm-2" for="student_id">수강생ID:</label>
								<div class="col-sm-10">
									<input type="text" id="st_id" name = "st_id" class="form-control" readonly>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-2" for="student_id">이름:</label>
								<div class="col-sm-10">
									<input type="text" id="st_name" name = "st_name"class="form-control">
								</div>
							</div>
							<!-- <div class="form-group">
								<label class="control-label col-sm-2" for="pw">Password:</label>
								<div class="col-sm-10">
									<input type="password" id="st_pw" class="form-control">
								</div>
							</div> -->
							<div class="form-group">
								<label class="control-label col-sm-2" for="phone">연락처:</label>
								<div class="col-sm-10">
									<input type="text" id="st_phone" name ="st_phone" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="reg_date">등록날짜:</label>
								<div class="col-sm-10">
									<input type="date" id="st_reg_date" class="form-control" readonly>
								</div>
							</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 수정한 정보를 저장할까요?</div>
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

