<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script>
	$(document).ready(
			function() {

				//문제) 삭제 버튼에 click 이벤트 등록 및 실행
				//버튼이 있는 라인의 번호, 이름 정보를 얻어서 화면에 출력 + hidden form 지정.
				$("button.choose").on("click", function() {
					$("#sid").text($(this).parents("tr").children().eq(0).text());
					$("#subject_name").text($(this).parents("tr").children().eq(1).text());
					$("#coursedate").text($(this).parents("tr").children().eq(2).text());
					
					// 학생 아이디
					var st_id = $("#stId").text();
					
					// 코스아이디
					var cr_id = $(this).parents("#cr_list").find("td").eq(0).text();
							
					console.log(st_id + cr_id);
							
					$.post("ad_gr_studentGrade_ajax.it", {st_id:st_id, cr_id:cr_id}, function(data){
						
						console.log(data);

						var myObj = JSON.parse(data);

						var obj_length = myObj.subject.length;
							
						console.log(obj_length);
								
						var result = "";

						for (var i = 0; i < obj_length; i++) {
											
							var s =	myObj.subject[i];
											
							result += "<tr>"
							result += "<td>"+s.sj_id+"</td>";
							result += "<td>"+s.sj_name+"</td>";
							result += "<td>"+s.sj_start_data+"~"+s.sj_end_date+"</td>";
							result += "<td>"+s.in_name+"</td>";
							
							console.log(s.test_paper);
							console.log(s.test_paper_name);
							
							if(s.test_paper_name==null){
								result += "<td>-</td>";
							}else{
								result += "<td>"+"<a href=\"doDownload.it?exam=" + s.test_paper + "\">" +s.test_paper_name + "</a>" + "</td>";
							}
									
							result += "<td>"+s.gr_attend+"/"+s.sc_attend+"</td>";
							result += "<td>"+s.gr_writing+"/"+s.sc_writing+"</td>";
							result += "<td>"+s.gr_practice+"/"+s.sc_practice+"</td>";
							result += "</tr>"
									
						}
						$("#st_list > tbody").html(result); 
						$("#admin_add_course_modal .seach_info").html("총 "+obj_length+"개의 과목정보 검색"); 
								
					});
							
					$("#admin_add_course_modal").modal();
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

/* 모달창 후터부분 줄없애기 */
.modal-footer {
    border-top: 0px;
}

/* 모달창 스크롤 생성 */
.list {
	max-height: 430px;
	overflow: auto;
	margin-bottom: 20px;
}
/* 백그라운드 크기 조절(배경색을 위한 조정)_정호 */
html, body, #wrapper{
		height: 100%;
}
#page-wrapper{
		min-height: 92%;
}
/* 네비게이션 이미지와 타이틀 여백 조절_정호 */
span.navbar-brand{
	padding:18px 0px 0px 10px;
} 
a.navbar-brand{
	padding: 6px 0px 0px 15px;
} 
/* 정보 검색 위치 조절_정호 */
.form-inline{
	position: relative;
} 
.seach_info{
	position: absolute;
	right: 5px;
	padding-top: 8px;
}
.seachBn_jho{
	margin-right: 100px;
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

						<!-- 관리자 - 2. 강사 관리 -->
						<li>
							<!-- class="원하는 그림이 클래스화 되어 있습니다." 문의해주세요~ --> <a
							href="ad_ins_instructorList.it"><i class="fa fa-male fa-fw"></i>
								강사 계정 관리</a>
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

			<!-- content의 내용을 입력하시면 됩니다. table이라던지 기타 등등 집어넣으세요! -->
			<div class="row">
				<div class="col-lg-12">
					<h4 style="padding-top: 10px">성적 조회 > 학생기준조회</h4>
					<hr>
					<!-- /.panel-heading -->
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>수강생ID</th>
									<th>이름</th>
									<th>주민번호</th>
									<th>번화번호</th>
									<th>가입날짜</th>
								</tr>
							</thead>
							<tbody>
								<!-- <tr>
									<td>st001</td>
									<td>홍길동</td>
									<td>1111111</td>
									<td>010-1234-1234</td>
									<td>2017-01-01</td>
								</tr> -->
								<c:forEach var = "s" items="${stList}">
								<tr>
									<td id="stId">${s.student_id}</td>
									<td>${s.st_name}</td>
									<td>${s.st_social_num}</td>
									<td>${s.st_phone}</td>
									<td>${s.st_registration_date}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading"><h4 style="font-weight:bold;">과목조회</h4></div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive list">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>과정ID</th>
											<th>과정명</th>
											<th>과정기간</th>
											<th>과목 성적조회</th>
										</tr>
									</thead>
									<tbody>
										<!-- <tr>
											<td>cs001</td>
											<td>[100%국비지원]오라클 전문가 과정</td>
											<td>2016-11-01 ~ 2017-03-31</td>
											<td><button class="btn btn-default btn-xs choose">선택</button></td>
										</tr> -->
										<c:forEach var = "cr" items="${crList}">
										<tr id="cr_list">
											<td>${cr.course_id}</td>
											<td>${cr.course_name}</td>
											<td>${cr.cr_start_date} ~ ${cr.cr_end_date}</td>
											<td><button class="btn btn-default btn-xs choose">조회</button></td>
										</tr>
										</c:forEach>
									</tbody>
								</table>


								<!-- 검색 폼 -->
								<div style="text-align: center;">
									<form  method="post" class="form-inline">
										<div class="form-group">
											<select class="form-control" id="skey" name="skey">
												<option value="all">전체</option>
												<option value="course_name">과정명</option>
												<!-- 검색 기준 추가 -->
											</select> <input type="text" class="form-control" id="svalue"
												name="svalue">
											<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
										</div>
										<button type="submit" class="btn btn-default xs seachBn_jho">
											<i class="fa fa-search"></i> Search
										</button>
										<span class="seach_info">총 <span>${totalcount}</span>개의 과정정보 검색</span>
									</form>
								</div>


							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->


	<!-- 상세정보 모달 -->
	<div id="admin_add_course_modal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="title">${st_id}님의 과정/과목 조회</h4>
				</div>
				<div class="modal-body">

					<table class="table table-striped">
						<tr>
							<th>과정ID</th>
							<th>과정명</th>
							<th>과목기간</th>
						</tr>
						<tr>
							<td id="sid"></td>
							<td id="subject_name"></td>
							<td id="coursedate"></td>
						</tr>
					</table>
					<hr>
					<p>
						<b>과목조회</b>
					</p>
					<div class="list">
						<table class="table table-striped" id="st_list">
						<thead>
							<tr>
								<th>과목ID</th>
								<th>과목명</th>
								<th>과목기간</th>
								<th>강사명</th>
								<th>시험문제</th>
								<th>출결</th>
								<th>필기</th>
								<th>실기</th>
							</tr>
						</thead>
						<tbody>
						</tbody>	
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">확인</button>
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

