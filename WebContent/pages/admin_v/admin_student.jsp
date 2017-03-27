<%-- 
2017-01-18 pm. 3:34 김현수
관리자 계정. 학생관리 > 전체검색jsp
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
	$(document)
			.ready(
					function() {

						//검색 진행후 검색 키워드가 화면에 그대로 남아있도록 액션 추가
						//-> 검색 폼 부분에 기존 검색 키워드를 재설정
						$("#skey").val("${skey}").attr("selected", "selected");
						$("#svalue").val("${svalue}");

						$("#skey").on("change", function() {
							$("#svalue").val("");
						});

						//문제) 삭제 버튼에 click 이벤트 등록 및 실행
						//버튼이 있는 라인의 번호, 이름 정보를 얻어서 화면에 출력 + hidden form 지정.
						$("button.info").on("click", function() {
							$("#title").text($(this).parents("tr").children().eq(1).text() + "(" + $(this).parents("tr").children().eq(0).text() + ")");

							var keyword = $(this).val();

							$.post("admin_student_json.it",	{keyword : keyword}, function(data) {

								console.log(data);

								var myObj = JSON.parse(data);
								var obj_length = myObj.myinfo.length;
								var result = "";

								if (!($.trim(data) == "")) {
									
									for (var i = 0; i < obj_length; i++) {
										result += "<tr>";
										result += "<td>" + myObj.myinfo[i].course_id + "</td>";
										result += "<td>" + myObj.myinfo[i].course_name + "</td>";
										result += "<td>" + myObj.myinfo[i].cr_start_date + "~" + myObj.myinfo[i].cr_end_date + "</td>";
										result += "<td>" + myObj.myinfo[i].classroom_name + "</td>";
										result += "<td>" + myObj.myinfo[i].fail_date + "</td>";
										result += "</tr>";
									}

									$("table.student_info_data > tbody").html(result);

								}

							});

							$("#admin_student_info_modal").modal();
						});
	
	
							//학생 등록
							
							

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

/* 모달 창 스크롤 생성 */
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
.form-inline {
	position: relative;
}

.seach_info {
	position: absolute;
	right: 5px;
	padding-top: 8px;
}

.seachBn_jho {
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
						<li><a href=logOut.it?key=admin><i class="fa fa-sign-out fa-fw"></i>
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
					<h4 style="padding-top: 10px">학생 관리 > 전체검색</h4>
					<hr>
					
					<div class="table-responsive list" style="padding-top: 10px">
						<form>
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>수강생ID</th>
										<th>이름</th>
										<th>전화번호</th>
										<th>등록일</th>
										<th>수강(신청)횟수</th>
										<th style="width: 40px">상세정보</th>
										<th style="width: 40px">과정등록</th>
									</tr>
								</thead>
								<tbody>

									<%-- JSTL, EL을 이용한 동적 데이터 출력 --%>
									<c:forEach var="ad" items="${allStudentList}">
										<tr>
											<td>${ad.student_id}</td>
											<td>${ad.st_name}</td>
											<td>${ad.st_phone}</td>
											<td>${ad.st_registration_date}</td>
											<td>${ad.countRequest}</td>
											<td><button type="button"
													class="btn btn-default btn-xs info" value="${ad.student_id}">상세정보</button></td>
											<td><a
												href="ad_st_studentCourseList.it?student_id=${ad.student_id}"
												role="button" class="btn btn-default btn-xs add_student">과정등록</a></td>
										</tr>
									</c:forEach>

									<!-- <tr>
									<td>st001</td>
									<td>홍길동</td>
									<td>1234567</td>
									<td>010-1234-1234</td>
									<td>2017-01-01</td>
									<td>-</td>
									<td><button type="button"
											class="btn btn-default btn-xs info">상세정보</button></td>
									<td><a href="admin_student_addcourse.jsp"><button
												type="button" class="btn btn-default btn-xs add_student">과정등록</button></a></td>
								</tr>
								<tr>
									<td>st002</td>
									<td>김길동</td>
									<td>1542322</td>
									<td>010-1234-1234</td>
									<td>2017-01-01</td>
									<td>-</td>
									<td><button type="button"
											class="btn btn-default btn-xs info">상세정보</button></td>
									<td><a href="admin_student_addcourse.jsp"><button type="button"
												class="btn btn-default btn-xs add_student">과정등록</button></a></td>
								</tr>  -->
								</tbody>
							</table>
						</form>
					</div>
						<!-- 검색 폼 -->
					<div style="text-align: center;">
						<form class="form-inline">
							<button type="button" class="btn btn-default addStudent"
								data-toggle="modal" data-target="#admin_addstudent"
								style="float: left;">학생등록</button>
							<div class="form-group">
								<select class="form-control" id="skey" name="skey">
									<option value="all">전체</option>
									<option value="student_id">수강생ID</option>
									<option value="name">이름</option>
									<option value="phone">전화번호</option>
									<!-- 검색 기준 추가 -->
								</select> <input type="text" class="form-control" id="svalue"
									name="svalue">
								<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
							</div>
							<button type="submit" class="btn btn-default xs seachBn_jho">
								<i class="fa fa-search"></i> Search
							</button>
							<span class="seach_info">총 <span id="tocount">${totalCount}</span>명의
								학생정보 검색
							</span>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal 영역 시작!!!!!! -->

	<%-- 학생등록 Modal --%>
	<div id="admin_addstudent" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn-xs close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">학생 등록</h4>
				</div>

				<div class="modal-body">


					<form action="ad_st_addStudent.it" method="post" class="form-horizontal">

						<div class="form-group">
							<label class="control-label col-sm-2" for="name">이름:</label>
							<div class="col-sm-10">
								<input class="form-control" id="name1" name="name" value=""
									maxlength="20" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="ssn">주민번호뒷자리:</label>
							<div class="col-sm-10">
								<input class="form-control" id="ssn" name="ssn" value=""
									maxlength="7" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="phone1">연락처:</label>
							<div class="col-sm-10">
								<input class="form-control" id="phone1" name="phone" value=""
									maxlength="20" required>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 입력한 학생정보를 등록할까요?</div>
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

	<!-- 상세정보 -->
	<div id="admin_student_info_modal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="title">홍길동(st001)</h4>
				</div>
				<div class="modal-body">
					<div class="list">
						<table class="table table-striped student_info_data">
							<thead>
								<tr>
									<th>과정ID</th>
									<th>과정명</th>
									<th>과정기간</th>
									<th>강의실</th>
									<th>중도탈락날짜</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
							<%-- 
									JSTL, EL을 이용한 동적 데이터 출력
									<c:forEach var="ad" items="${list}">
										<tr>
											<td>${ad.course_id}</td>
											<td>${ad.course_name}</td>
											<td>${ad.cr_start_date}${ad.cr_end_date}</td>
											<td>${ad.classroom_name}</td>
											<td>${ad.fail_date}</td>
											</tr>
									</c:forEach> --%>

							<!--
							<tr>
								<td id="cid">cs002</td>
								<td id="course_name">[100%국비지원]오라클 전문가 과정</td>
								<td id="course_date">2015-12-01 ~ 2016-03-30</td>
								<td id="class_room">101호</td>
								<td id="course_failer">2015-12-31</td>
							</tr> -->



						</table>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">확인</button>
					</div>
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