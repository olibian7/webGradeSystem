<%-- 
2017-01-18 pm. 14:47 김현수
관리자 계정. 강사 계정 관리jsp
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

.list2 {
	max-height: 200px;
	overflow: auto;
}

div.list_subject {
	max-height: 200px;
	overflow: auto;
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
/* 검색버튼 오른쪽 마진 */
.seachBn_jho {
	margin-right: 100px;
}
</style>

<script>
	$(document).ready(
			function() {
				
				//검색 진행후 검색 키워드가 화면에 그대로 남아있도록 액션 추가
				//-> 검색 폼 부분에 기존 검색 키워드를 재설정
				$("#skey").val("${skey}").attr("selected", "selected");
				$("#svalue").val("${svalue}");
				
				$("#skey").on("change",function(){
					$("#svalue").val("");
				});
				
				var check_len = 0;
				
				function subjectsAjax(path, in_subject){
					$.post("admin_teacher_subjectAjax.it", function(data){
						console.log(data);
						console.log(path);
						result = "";						
						var myObj = JSON.parse(data);
						var obj_length = myObj.subject.length;
						check_len = myObj.subject.length;
						
						
						var con = "";

						for(var i = 0; i < obj_length; i++){
							var s = myObj.subject[i];
							result += "<div class=\"checkbox\">"; 
							result += "<label>";							
							result += "<input type=\"checkbox\" value=\"" + s.subject_id + "\" class=\"" + s.subject_id + "\" name=\"subjectCheck\">"+s.subject_id+" "+s.subject_name+"</label>";
							result += "</div>";					
							
						}	
						
						$(path).html(result);	//출력
						
						//체크확인
					 	if(!in_subject){
							console.log("비어있음");												
						}else{
							console.log(in_subject);
							var in_subjectArr = in_subject.split(",");							
							for(var i = 0; i < obj_length; i++){
								console.log("돌고있나?1");
								var s = myObj.subject[i];
								for(var j = 0; j < in_subjectArr.length; ++j){
									console.log("돌고있나?2");
									if(in_subjectArr[j] == s.subject_name){
										var a = "."+s.subject_id;									 
										console.log("돌고있나?3"+a);
										$(a).attr("checked", "checked");
									}
								}
								
							}
						} 
					});
					
				}				
				
				
				$("button.ok_button").on("click", function(){
					
					var arrayParam = new Array();
					
					$("input:checkbox[name='subjectCheck']:checked").each(function(){
						arrayParam.push($(this).val());
					});
					
					$("input#hid").val(arrayParam);
					
				});
				
				$("button.addTeacher").on("click", function() {
					subjectsAjax("#teacher_Info_Add_Modal .list2", "");
					$("#teacher_Info_Add_Modal").modal();

				});

				//수정 버튼 클릭시 해당 강사ID, 강사명, 주민번호 뒷자리, 연락처, 강의가능과목 불러오기
				$("button.modifyTeacher").on(
						"click",
						function() {
							$("#id2").val(
									$(this).parents("tr").children().eq(0)
											.text());
							$("#name2").val(
									$(this).parents("tr").children().eq(1)
											.text());
						/* 	$("#pw2").val(
									$(this).parents("tr").children().eq(2)
											.text()); */
							$("#phone2").val(
									$(this).parents("tr").children().eq(2)
											.text());
							/* $("div.list_subject").find("input") */
							var mysubject = $(this).parents("tr").children().eq(3).text();
							
							
							subjectsAjax("#teacher_Info_Modify_Modal .list_subject", mysubject);
							
							$("#teacher_Info_Modify_Modal").modal();
						}); 
				
				//삭제 버튼 클릭시 해당 강사ID, 강사명, 주민번호 뒷자리, 연락처, 강의가능과목 불러오기
				$("button.deleteTeacher").on(
						"click",
						function() {
							$(".teacher_id").text(
									$(this).parents("tr").children().eq(0)
											.text());
							$("#delete_id").val(
									$(this).parents("tr").children().eq(0)
											.text());
							$("#delete_name").text(
									$(this).parents("tr").children().eq(1)
											.text());
							/* $("#delete_add").text(
									$(this).parents("tr").children().eq(2)
											.text()); */
							$("#delete_phone").text(
									$(this).parents("tr").children().eq(2)
											.text());
							$("#delete_avil").text(
									$(this).parents("tr").children().eq(3)
											.text());
							$("#teacher_Info_Delete_Modal").modal();
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

		<div id="page-wrapper">
			<!-- content의 가장 큰 제목을 입력하시면 됩니다. -->

			<div class="row">
				<div class="col-lg-12">
					<h4 style="padding-top: 10px">강사 계정 관리</h4>
					<hr>
					<div class="table-responsive list">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>강사 ID</th>
									<th>이름</th>
									<!-- <th>주민번호 뒷자리</th> -->
									<th>전화번호</th>
									<th>강의 가능 과목</th>
									<th style="width: 20px">수정</th>
									<th style="width: 20px">삭제</th>
								</tr>
							</thead>
							<tbody>

								<%-- JSTL, EL을 이용한 동적 데이터 출력 --%>
								<c:forEach var="ad" items="${list}">
									<tr>
										<td>${ad.instructor_id}</td>
										 <td>${ad.in_name}</td>
										<%-- <td>${ad.in_social_num}</td> --%>
										<td>${ad.in_phone}</td>
										<td>${ad.sj_available}</td>
										<td>										
											<button type="button" class="modifyTeacher btn btn-default btn-xs" >수정</button>
										</td>  
												<!-- 	data-toggle="modal"
												data-target="#teacher_Info_Modify_Modal" -->
										<td>										
										<%-- <c:forEach var="b" items="${check_list}"> ${b.course_name_id==a.course_name_id?"disabled=\"disabled\"":""}</c:forEach>>삭제</button></a></td>
																		</tr>
										</c:forEach> --%>
										<button type="button" class="deleteTeacher btn btn-default btn-xs" 
									 <c:forEach var="tm" items="${teach_list}">
										<c:if test="${ad.instructor_id == tm.instructor_id}">																			 
											disabled="disabled"
										</c:if> 
									</c:forEach>
									>삭제</button>
										</td>
									</tr>
								</c:forEach>

								<%-- <tr>
									<td>in009</td>
									<td>이민종</td>
									<td>1456999</td>
									<td>010-7599-8654</td>
									<td>오라클, 자바</td>
									<td><a href="#"><button type="button"
												class="modifyTeacher btn btn-default btn-xs">수정</button></a></td>
									<td><a href="#"><button type="button"
												class="deleteTeacher btn btn-default btn-xs">삭제</button></a></td>
								</tr>  --%>
							</tbody>
						</table>
					</div>
					<!-- 검색 폼 -->
					<div style="text-align: center;">
						<form class="form-inline" method="post">
							<button type="button" class="btn btn-default addTeacher"
								style="float: left;">강사등록</button>
							<div class="form-group">
								<select class="form-control" id="skey" name="skey">
									<option value="all">전체</option>
									<option value="instructor_id">강사ID</option>
									<option value="instructor_name">강사명</option>
									<!-- 검색 기준 추가 -->
								</select> <input type="text" class="form-control" id="svalue"
									name="svalue">
								<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
							</div>
							<button type="submit" class="btn btn-default xs seachBn_jho">
								<i class="fa fa-search"></i> Search
							</button>
							<span class="seach_info">총 ${totalCount}개의 강사정보 검색</span>
						</form>
					</div>

				</div>

			</div>
		</div>
	</div>

	<%-- 강사계정등록 Modal --%>
	<div id="teacher_Info_Add_Modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">강사 계정 등록</h4>
				</div>

				<div class="modal-body">
					<%-- 강사 계정 등록 제목 부분 --%>
					<div class="informarea">
						<ul>
							<li>강사의 정보를 등록하세요.</li>
						</ul>
					</div>

					<%-- 강사 정보 등록에 관련된 form 요소들이 포함된 영역 --%>
					<div class="inputarea">

						<form action="ad_ins_addInstructor.it" method="post" class="form-horizontal">
							<input type="hidden" id="hid" name="check">
							<div class="form-group">
								<label class="control-label col-sm-2" for="name">강사이름:</label>
								<div class="col-sm-10 inputInfo">
									<input type="text" class="form-control" id="name1" name="name"
										placeholder="이름을 입력해주세요.(최대 20글자)" required>
								</div>
							</div>
							<!-- <div class="form-group">
								<label class="control-label col-sm-2" for="pw">주민번호:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="pw1" name="pw"
										placeholder="주민등록번호를 입력해주세요.(000000-0000000)" required>
								</div>
							</div> -->
							<div class="form-group">
								<label class="control-label col-sm-2" for="phone">전화번호:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="phone1"
										name="phone" placeholder="전화번호를 입력해주세요.(010-0000-0000)"
										required>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="ssn">주민등록번호:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="ssn1"
										name="ssn" placeholder="주민등록번호 뒷자리를 입력해주세요(0000000)"
										required>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="course_available">강의가능과목:</label>
								<div class="col-sm-10 list2">
								
								<!-- 
									<div class="checkbox">
										<label><input type="checkbox" value="" id="sn007_1"
											name="sn007_1">sn007 jQeury</label>
									</div>
									-->

								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">현재 선택한 정보를 추가할까요?</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<!-- 서브밋 버튼 클릭시 hidden form의 데이터(번호)가 서버로 전송된다. -->
									<button type="submit" class="btn btn-default ok_button">확인</button>
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
	</div>




	<%-- 강사계정수정 Modal --%>
	<div id="teacher_Info_Modify_Modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">강사 계정 수정</h4>
				</div>

				<div class="modal-body">
					<%-- 강사 계정 수정 제목 부분 --%>

					<div class="informarea">
						<ul>
							<li>강사의 정보를 수정하세요.</li>
						</ul>
					</div>
					<%-- 강사 정보 수정에 관련된 form 요소들이 포함된 영역 --%>
					<div class="inputarea">
						<form class="form-horizontal" action="ad_ins_modify.it" method="post">

							<div class="form-group">
								<label class="control-label col-sm-2" for="id2">강사ID:</label>
								<div class="col-sm-10 inputInfo">
									<input type="text" class="form-control" id="id2" name="id"
										required readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="name">강사이름:</label>
								<div class="col-sm-10 inputInfo">
									<input type="text" class="form-control" id="name2" name="name"
										required>
								</div>
							</div>
							<!-- <div class="form-group">
								<label class="control-label col-sm-2" for="pw">주민번호:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="pw2" name="pw"
										required>
								</div>
							</div> -->
							<div class="form-group">
								<label class="control-label col-sm-2" for="phone">전화번호:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="phone2"
										name="phone" required>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-2" for="course_available">강의가능과목:</label>
								<div class="col-sm-10 list_subject">
									<!--
									<div class="checkbox">
										<label><input type="checkbox" value="" id="sn001_1"
											name="sn001_1">sn001 오라클</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" value="" id="sn002_1"
											name="sn002_1">sn002 자바</label>
									</div>
									 -->
<%-- 									 <c:forEach var="sj" items="${ }"> --%>
									 
<%-- 									 </c:forEach> --%>
									
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">현재 수정한 정보로 입력할까요?</div>
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
	</div>

	<%-- 강사계정삭제 Modal --%>
	<div id="teacher_Info_Delete_Modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						강사 계정 삭제(<span class="teacher_id"></span>)
					</h4>
				</div>

				<div class="modal-body">
					<%-- 강사 계정 삭제 제목 부분 --%>

					<div class="informarea">
						<ul>
							<li>강사의 정보를 확인하세요.</li>
						</ul>
					</div>
					<%-- 강사 정보 삭제에 관련된 form 요소들이 포함된 영역 --%>
					<div class="inputarea">
						<form class="form-horizontal" action="ad_ins_deleteInstructor.it" method="post">
							<div class="form-group"
								style="margin-left: 5px; margin-right: 5px;">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>강사_ID</th>
											<th>이름</th>
											<!-- <th>주민번호 뒷자리</th> -->
											<th>전화번호</th>
											<th>강의 가능 과목</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="teacher_id"></td>
											<td id="delete_name"></td>
											<!-- <td id="delete_add"></td> -->
											<td id="delete_phone"></td>
											<td id="delete_avil"></td>
											
										</tr>
									</tbody>
								</table>
								<input type="hidden" id="delete_id" name="in_id">
							</div>
							<div class="form-group">
								<div class="col-sm-offset-7 col-sm-10">현재 강사 정보를 삭제할까요?</div>
							</div>
							<div class="modal-footer" style="border-top: 0px;">
								<button type="submit" class="btn btn-default">확인</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">취소</button>
							</div>
						</form>

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