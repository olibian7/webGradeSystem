<%-- 
2017-01-18 pm. 5:30 김현수
관리자 계정. 개설 과목 관리jsp
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
	$(document).ready(
			function() {				
				
				//검색 진행후 검색 키워드가 화면에 그대로 남아있도록 액션 추가
				//-> 검색 폼 부분에 기존 검색 키워드를 재설정
				$("#skey").on("change",function(){
					$("#svalue").val("");
				});
				
				$("#skey").val("${skey}").attr("selected", "selected");
				$("#svalue").val("${svalue}");
				
				
				//정보보기 버튼에 click 이벤트 등록 및 실행
				//과목정보 보 기 버튼이 있는 라인의 번호, 이름 정보를 얻어서 화면에 출력 + hidden form 지정.
				$("button.info").on(
						"click",
						function() {

							var keyword = $(this).parents("tr").children()
									.eq(0).text();
							$("#detail_title").html($(this).parents("tr").children().eq(1).text()+" "+$(this).parents("tr").children().eq(2).text());
							console.log(keyword);
										
							/* 과정에 속한 과목정보 불러오기 */
							$.post("admin_course_ajaxReceive.it", {keyword : keyword}, function(data) {

								console.log(data);

								var myObj = JSON.parse(data);

								var obj_length = myObj.subject.length;
								$("#sj_count").html(obj_length);	
								
								var result = "";
		
								for (var i = 0; i < obj_length; i++) {
									result += "<tr>";
									result += "<td>"
											+ myObj.subject[i].subject_id
											+ "</td>";
									result += "<td>"
											+ myObj.subject[i].subject_name
											+ "</td>";
									result += "<td>"
											+ myObj.subject[i].sj_start_date
											+ "~"
											+ myObj.subject[i].sj_end_date
											+ "</td>";
									result += "<td>"
											+ myObj.subject[i].book_name
											+ "</td>";
									result += "<td>" + myObj.subject[i].in_name
											+ "</td>";
									result += "</tr>";
								}
								
								if($.trim(data)!=""){
								$("table.subject > tbody").html(result);
								}

							});
							
				
							
							/* 과정에 속한 학생정보 불러오기 */
							$.post("admin_course_ajaxReceive2.it", {keyword : keyword}, function(data) {

								console.log(data);

								var myObj = JSON.parse(data);

								var obj_length = myObj.student.length;
								$("#st_count").html(obj_length);
								
								var result = "";

								for (var i = 0; i < obj_length; i++) {
									result += "<tr>";
									result += "<td>" + myObj.student[i].student_id
											+ "</td>";
									result += "<td>"
											+ myObj.student[i].st_name
											+ "</td>";
									result += "<td>" + myObj.student[i].st_phone
											+ "</td>";
									result += "<td>" + myObj.student[i].st_registration_date
											+ "</td>";
									result += "<td>" + myObj.student[i].fail_date
											+ "</td>";
									result += "</tr>";
								}
								if($.trim(data)!=""){
								$("table.student > tbody").html(result);
								}
							});
								
							
							$("#infoModal").modal();
							
						
						});

				//과정 삭제 버튼
				$("button.remove").on(
						"click",
						function() {
							$("#delete_course_id").val(
									$(this).parents("tr").children().eq(0)
											.text());
							$("#cid").text(
									$(this).parents("tr").children().eq(0)
											.text());
							$("#course_name").text(
									$(this).parents("tr").children().eq(1)
											.text());
							$("#course_date").text(
									$(this).parents("tr").children().eq(2)
											.text());
							$("#class_room").text(
									$(this).parents("tr").children().eq(3)
											.text());
							$("#count").text(
									$(this).parents("tr").children().eq(4)
											.text());
							$("#course_count").text(
									$(this).parents("tr").children().eq(5)
											.text());
							$("#removeModal").modal();
						});

				//과정 수정 버튼
				$("button.modify").on(
						"click",
						function() {							
							$("#course_id_modify").val($(this).parents("tr").children().eq(0).text());
							$("#course_name_modify").val($(this).parents("tr").children().eq(1).text());
 							$("#cr_start_date_modify").val($(this).parents("tr").children().eq(2).find(".span1").text());
 							$("#cr_end_date_modify").val($(this).parents("tr").children().eq(2).find(".span2").text());
 							
 							var count = ${cl_count};

 							for(var i=0; i<count;++i){
 								if($(this).parents("tr").children().eq(3).text()==$("#classroom_modify > option").eq(i).val()){
 									console.log($("#classroom_modify > option").eq(i).val());
 									$("#classroom_modify > option").eq(i).attr("selected", "selected");
	 							}
							}
 							
 							$("#modifyModal").modal();
 							
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

.list {
	max-height: 190px;
	overflow: auto;
}

.list2 {
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
					<h4 style="padding-top: 10px">개설 과정 관리</h4>
					<hr>
					<div class="table-responsive list2">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>과정명ID</th>
									<th>과정명</th>
									<th>과정기간</th>
									<th>강의실명</th>
									<th>인원/정원</th>
									<th>개설과목수</th>
									<th style="width: 20px">정 보</th>
									<th style="width: 20px">수 정</th>
									<th style="width: 20px">삭 제</th>
								</tr>
							</thead>
							<tbody>

								<%-- JSTL, EL을 이용한 동적 데이터 출력 --%>
								<%--AdminAction3.ad_cr_courseList 메소드--%>
								<c:forEach var="ad" items="${courselist}">
									<tr>
										<td>${ad.course_id}</td>
										<td>${ad.course_name}</td>
										<td><span class="span1">${ad.cr_start_date}</span>~<span
											class="span2">${ad.cr_end_date}</span></td>
										<td>${ad.classroom_name}</td>
										<td>${ad.countStu}/${ad.max_num}</td>
										<td>${ad.countSub}</td>
										<td><button type="button"
												class="btn btn-default btn-xs info">보기</button></td>
										<td><button type="button"
												class="btn btn-default btn-xs modify">수정</button></td>
										<td><button type="button"
                                    class="btn btn-default btn-xs remove ${(ad.countStu>0 || ad.countSub>0)?'disabled':''}" ${(ad.countStu>0|| ad.countSub>0)?"disabled":""}>삭제</button></td>
									</tr>
								</c:forEach>
								<%-- 
								<tr>
									<td>cs001</td>
									<td>[100%국비지원]오라클 전문가 과정</td>
									<td>2016-11-01~2017-03-31</td>
									<td>102호</td>
									<td>13/20</td>
									<td>0</td>
									<td><button type="button"
											class="btn btn-default btn-xs info">보기</button></td>
									<td><button type="button"
											class="btn btn-default btn-xs modify">수정</button></td>
									<td><button type="button"
											class="btn btn-default btn-xs remove">삭제</button></td>
								</tr>
								<tr>
									<td>cs002</td>
									<td>[100%국비지원]자바 전문가 과정</td>
									<td>2016-12-01~2017-04-30</td>
									<td>102호</td>
									<td>13/20</td>
									<td>2</td>
									<td><button type="button"
											class="btn btn-default btn-xs info">보기</button></td>
									<td><button type="button"
											class="btn btn-default btn-xs modify">수정</button></td>
									<td><button type="button"
											class="btn btn-default btn-xs remove">삭제</button></td>
								</tr>
								--%>

							</tbody>
						</table>
					</div>


					<!-- 검색 폼 -->
					<div style="text-align: center;">
						<button type="button" class="btn btn-default addCourse"
							data-toggle="modal" data-target="#admin_basicInfo"
							style="float: left;">과정등록</button>
						<form method="post" class="form-inline">
							<div class="form-group">
								<select class="form-control" id="skey" name="skey">
									<option value="all">전체</option>
									<option value="course_id">과정ID</option>
									<option value="course_name">과정명</option>
									<!-- 검색 기준 추가 -->
								</select> <input type="text" class="form-control" id="svalue"
									name="svalue">
								<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
							</div>
							<button type="submit" class="btn btn-default xs seachBn_jho">
								<i class="fa fa-search"></i> Search
							</button>
							<span class="seach_info">총 <span>${count}</span>개의 과정정보 검색
							</span>
						</form>
					</div>


				</div>
			</div>
		</div>
	</div>

	<!-- modal 영역 시작!!!!!!! -->

	<%-- 과정등록 Modal --%>
	<div id="admin_basicInfo" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn-xs close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">개설 과정 등록</h4>
				</div>
				<div class="modal-body">

					<form action="ad_cr_addCourse.it" method="post" class="form-horizontal">
						<p>과정 정보를 등록하세요.</p>
						<div class="form-group">
							<label class="control-label col-sm-2" for="course_name_id">과정명ID:</label>
							<div class="col-sm-10">
								<select class="form-control" name="course_name_id">
									<!-- 
									<option>cn001 [100%국비지원]오라클 전문가 과정</option>
									<option>cn002 [100%국비지원]자바 전문가 과정</option>
									<option>cn003 [100%국비지원]SQL 전문가 과정</option>
									 -->
									<%--AdminAction3.ad_cr_courseNameList 메소드--%>
									<c:forEach var="cn" items="${coursenamelist}">
										<option value="${cn.course_name_id}">${cn.course_name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="cr_start_date">과정개설날짜:</label>
							<div class="col-sm-10">
								<input type="date" class="form-control cr_start_date" value="" name="cr_start_date">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="cr_end_date">과정종강날짜:</label>
							<div class="col-sm-10">
								<input type="date" class="form-control cr_end_date" value="" name="cr_end_date">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="classroom_name">강의실:</label>
							<div class="col-sm-10">
								<select class="form-control classroom" name="classroom_id">
									<c:forEach var="cl" items="${classroomlist}">
										<option value="${cl.classroom_id}">${cl.classroom_name}</option>
									</c:forEach>
									<!-- 
									<option>cl001 101호</option>
									<option>cl004 104호</option>
									<option>cl005 105호</option>
									 -->
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 선택한 정보를 추가할까요?</div>
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




	<%-- Modal --%>
	<!-- 상세정보 Modal -->
	<div id="infoModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content modal-lg">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="detail_title">[100% 국비지원] 자바 전문가
						과정(2016-12-01 ~ 2017-04-30)</h4>
				</div>
				<div class="modal-body">

					<form class="form-inline">

						<p>
							과정의 과목(<span id="sj_count"></span>건)의 정보입니다.
						</p>
						<div class="list">

							<table class="table table-striped subject">
								<thead>
									<tr>
										<th>과목ID</th>
										<th>과목명</th>
										<th>과목기간</th>
										<th>교재명</th>
										<th>강사명</th>
									</tr>
								</thead>
								<tbody>
									<%-- 
									<tr>
										<td>sj001</td>
										<td>자바</td>
										<td>2016-12-01~2017-02-01</td>
										<td>자바ABCD</td>
										<td>이흔호</td>
									</tr>
									<tr>
										<td>sj002</td>
										<td>오라클</td>
										<td>2017-02-02~2017-04-30</td>
										<td>오라클ABCD</td>
										<td>이진욱</td>
									</tr>
									<tr>
										<td>sj002</td>
										<td>오라클</td>
										<td>2017-02-02~2017-04-30</td>
										<td>오라클ABCD</td>
										<td>이진욱</td>
									</tr>
									<tr>
										<td>sj002</td>
										<td>오라클</td>
										<td>2017-02-02~2017-04-30</td>
										<td>오라클ABCD</td>
										<td>이진욱</td>
									</tr>
									<tr>
										<td>sj002</td>
										<td>오라클</td>
										<td>2017-02-02~2017-04-30</td>
										<td>오라클ABCD</td>
										<td>이진욱</td>
									</tr>
									<tr>
										<td>sj002</td>
										<td>오라클</td>
										<td>2017-02-02~2017-04-30</td>
										<td>오라클ABCD</td>
										<td>이진욱</td>
									</tr>
									<tr>
										<td>sj002</td>
										<td>오라클</td>
										<td>2017-02-02~2017-04-30</td>
										<td>오라클ABCD</td>
										<td>이진욱</td>
									</tr>
									<tr>
										<td>sj002</td>
										<td>오라클</td>
										<td>2017-02-02~2017-04-30</td>
										<td>오라클ABCD</td>
										<td>이진욱</td>
									</tr>
									<tr>
										<td>sj002</td>
										<td>오라클</td>
										<td>2017-02-02~2017-04-30</td>
										<td>오라클ABCD</td>
										<td>이진욱</td>
									</tr>
									<tr>
										<td>sj002</td>
										<td>오라클</td>
										<td>2017-02-02~2017-04-30</td>
										<td>오라클ABCD</td>
										<td>이진욱</td>
									</tr>
									<tr>
										<td>sj002</td>
										<td>오라클</td>
										<td>2017-02-02~2017-04-30</td>
										<td>오라클ABCD</td>
										<td>이진욱</td>
									</tr>
									<tr>
										<td>sj002</td>
										<td>오라클</td>
										<td>2017-02-02~2017-04-30</td>
										<td>오라클ABCD</td>
										<td>이진욱</td>
									</tr>
									--%>
								</tbody>
							</table>
						</div>
						<p style="padding: 1%;">
							과정의 수강생(<span id="st_count"></span>명)의 정보입니다.
						</p>
						<div class="list">
							<table class="table table-striped student">
								<thead>
									<tr>
										<th>수강생ID</th>
										<th>수강생 이름</th>
										<th>전화번호</th>
										<th>등록일</th>
										<th>중도탈락날짜</th>
									</tr>
								</thead>
								<tbody>
									<!-- 
								<tr>
									<td>st001</td>
									<td>홍길동</td>
									<td>1022432</td>
									<td>010-2356-4528</td>
									<td>2016-12-06</td>
									<td>-</td>
								</tr>
								<tr>
									<td>st002</td>
									<td>이순신</td>
									<td>1544236</td>
									<td>010-4758-6532</td>
									<td>2016-12-05</td>
									<td>2017-01-01</td>
								</tr>
								 -->
								</tbody>
							</table>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<div class="row" align="center">
						<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>

		</div>
	</div>

	<%-- 과정 수정 modal --%>
	<div id="modifyModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">개설 과정 수정</h4>
				</div>
				<div class="modal-body">

					<form action="ad_cr_modifyCourse.it" method="post" class="form-horizontal">
						<p>과정 정보를 수정하세요.</p>
						<div class="form-group">
							<label class="control-label col-sm-2" for="course_id_modify">과정ID:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="course_id_modify"
									name="course_id" value="" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="course_name_modify">과정명:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="course_name_modify"
									name="course_name" value="" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="cr_start_date_modify">과정개설날짜:</label>
							<div class="col-sm-10">
								<input type="date" id="cr_start_date_modify" name="cr_start_date"
									class="form-control cr_start_date" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="cr_end_date_modify">과정종강날짜:</label>
							<div class="col-sm-10">
								<input type="date" id="cr_end_date_modify" name="cr_end_date"
									class="form-control cr_end_date" value="">
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-sm-2" for="classroom">강의실:</label>
							<div class="col-sm-10">
								<select id="classroom_modify" class="form-control" name="classroom_id">
									<c:forEach var="cl" items="${classroomlist}">
										<option value="${cl.classroom_id}">${cl.classroom_name}</option>
									</c:forEach>
								</select>
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

	<%-- 과정 삭제 form Modal --%>
	<div id="removeModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content modal-lg">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="title">개설 과정 삭제</h4>
				</div>
				<div class="modal-body">

					<form action="ad_cr_deleteCourse.it" method="post" class="form-inline">

						<div>
							<p>해당 과정을 삭제하시겠습니까?</p>
							<input type="hidden" name="course_id" id="delete_course_id" value="">
							
							<table class="table table-striped">
								<tr>
									<th>과정ID</th>
									<th>과정명</th>
									<th>과정기간</th>
									<th>강의실명</th>
									<th>인원/정원</th>
									<th>개설과목수</th>
								</tr>

								<tr>
									<td id="cid"></td>
									<td id="course_name"></td>
									<td id="course_date"></td>
									<td id="class_room"></td>
									<td id="count"></td>
									<td id="course_count"></td>
								</tr>
							</table>
						</div>
						<div class="row" align="center">
							<button type="submit" class="btn btn-default">삭제</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">취소</button>
						</div>
					</form>
				</div>
				<div class="modal-footer"></div>
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
