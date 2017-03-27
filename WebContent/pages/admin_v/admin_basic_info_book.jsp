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
/* 교재등록 버튼 */
.addStudent {
	float: left;
}
</style>
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

						//수정 버튼 클릭시 해당 교재ID, 교재명, 출판사, 교재이미지 불러오기
						$("button.modify").on(
								"click",
								function() {
									$("#admin_basicInfo_book_modify").modal();
									$("#id2").val(
											$(this).parents("tr").children()
													.eq(0).text());
									$("#book2").val(
											$(this).parents("tr").children()
													.eq(1).text());
									$("#publisher2").val(
											$(this).parents("tr").children()
													.eq(2).text());
									$("#image_detail2").val(
											$(this).parents("tr").children()
													.eq(2).text());
								});

						//삭제 버튼 클릭시 해당 교재ID, 교재명, 출판사, 교재이미지 불러오기
						$("button.delete").on(
								"click",
								function() {
									$("#admin_basicInfo_book_delete").modal();
									$("#id3").val(
											$(this).parents("tr").children()
													.eq(0).text());
									$("#book3").val(
											$(this).parents("tr").children()
													.eq(1).text());
									$("#publisher3").val(
											$(this).parents("tr").children()
													.eq(2).text());
									$("#image_detail3").val(
											$(this).parents("tr").children()
													.eq(2).text());
								});

						$(".book_img").on("click", function() {
							//서버에 사진 정보를 확인하기 위한 직원번호를 전송한다.
							console.log($("#pictureModal").find("img").attr("src"));
							var book_id = $(this).parents("tr").children().eq(0).text();
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

			<!-- content의 내용을 입력하시면 됩니다. table이라던지 기타 등등 집어넣으세요! -->
			<div class="row">
				<div class="col-lg-12">
					<h4 style="padding-top: 10px">기초 정보 관리 > 교재명(출판사)</h4>
					<hr>
					<!-- /.panel-heading -->

					<div class="table-responsive list">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>교재명ID</th>
									<th>교재명</th>
									<th>출판사</th>
									<th style="width: 20px">수정</th>
									<th style="width: 20px">삭제</th>
								</tr>
							</thead>
							<tbody>
								<%-- 
								<tr>
									<td>bn001</td>
									<td>오라클 ABCD</td>
									<td>한빛미디어</td>
									<td><a href="#"><button
												class="btn btn-default btn-xs modify">수정</button></a></td>
									<td><a href="#"><button
												class="btn btn-default btn-xs delete">삭제</button></a></td>
								</tr>
								<tr>
									<td>bn002</td>
									<td>자바 ABCD</td>
									<td>도우출판</td>
									<td><a href="#"><button
												class="btn btn-default btn-xs modify">수정</button></a></td>
									<td><a href="#"><button
												class="btn btn-default btn-xs delete">삭제</button></a></td>
								</tr>
								--%>
								<c:forEach var="a" items="${list}">
									<tr>
										<td>${a.book_id}</td>
										<td><a href="#" class="book_img">${a.book_name}</a></td>
										<td>${a.publisher}</td>
										<td><a href="#"><button
													class="btn btn-default btn-xs modify">수정</button></a></td>
													
													<td><a href="#"><button type="button"
			class="btn btn-default btn-xs delete"
			value="${a.book_id}"
			<c:forEach var="b" items="${checklist}"> ${b.book_id==a.book_id?"disabled=\"disabled\"":""}</c:forEach>>삭제</button></a></td>
								
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>


					<!-- 검색 폼 -->
					<div style="text-align: center;">
						<!-- <div style="float: left;"> -->
						<button type="button" class="btn btn-default addStudent"
							data-toggle="modal" data-target="#admin_basicInfo_book">교재등록</button>
						<!-- </div> -->
						<form class="form-inline" method="post">
							<div class="form-group">
								<select class="form-control" id="skey" name="skey">
									<option value="all">전체</option>
									<option value="book_id">교재ID</option>
									<option value="book_name">교재명</option>
									<option value="publisher">출판사</option>
									<!-- 검색 기준 추가 -->
								</select> <input type="text" class="form-control" id="svalue"
									name="svalue">
								<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
							</div>
							<button type="submit" class="btn btn-default xs seachBn_jho">
								<i class="fa fa-search"></i> Search
							</button>
							<span class="seach_info">총 <span>${length}</span>개의 교재 검색
							</span>
						</form>
					</div>
					<!-- /.table-responsive -->

				</div>
				<!-- /.row -->
			</div>
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<%-- 교재 추가 Modal --%>
	<div id="admin_basicInfo_book" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">교재 등록</h4>
				</div>
				<div class="modal-body">
					<%-- action="" 속성의 주소 변경 --%>
					<form action="ad_info_addBook.it" method="post" role="form" enctype="multipart/form-data">

						<%-- 번호 전송은 hidden form 추가 --%>
						<input type="hidden" id="" name="" value="">

						<%-- id="" 속성의 식별자 유일한 이름을 가지도록 수정 --%>
						<div class="form-group">
							<label for="book">교재명:</label> <input
								class="form-control form-inline" id="book1" name="book"
								maxlength="40" required></input>
						</div>
						<div class="form-group">
							<label for="publisher">출판사:</label> <input
								class="form-control form-inline" id="publisher1"
								name="publisher" maxlength="20" required></input>
						</div>
						<div class="form-group">
							<label for="image_detail">상세이미지:</label> <input
								class="form-control form-inline" type="file" id="image_detail"
								name="image_detail" required></input>
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

	<%-- 교재 수정 Modal --%>
	<div id="admin_basicInfo_book_modify" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">교재 수정</h4>
				</div>
				<div class="modal-body">
					<%-- action="" 속성의 주소 변경 --%>
					<form action="ad_info_modifyBookName.it" method="post" role="form" enctype="multipart/form-data">

						<%-- 번호 전송은 hidden form 추가 --%>
						<input type="hidden" id="" name="" value="">

						<%-- id="" 속성의 식별자 유일한 이름을 가지도록 수정 --%>
						<div class="form-group">
							<label for="id">교재명ID:</label> <input
								class="form-control form-inline" id="id2" name="id" readonly></input>
						</div>

						<div class="form-group">
							<label for="book">교재명:</label> <input
								class="form-control form-inline" id="book2" name="book"
								maxlength="40" required></input>
						</div>
						<div class="form-group">
							<label for="publisher">출판사:</label> <input
								class="form-control form-inline" id="publisher2"
								name="publisher" maxlength="20" required></input>
						</div>
						<div class="form-group">
							<label for="image_detail">상세이미지:</label> <input
								class="form-control form-inline" type="file" id="image_detail2"
								name="image_detail" required></input>
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

	<%-- 교재 삭제 Modal --%>
	<div id="admin_basicInfo_book_delete" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">교재 삭제</h4>
				</div>
				<div class="modal-body">
					<%-- action="" 속성의 주소 변경 --%>
					<form action="ad_info_deleteBookName.it" method="post" role="form" enctype="multipart/form-data">

						<%-- 번호 전송은 hidden form 추가 --%>
						<input type="hidden" id="" name="" value="">

						<%-- id="" 속성의 식별자 유일한 이름을 가지도록 수정 --%>
						<div class="form-group">
							<label for="id">교재명ID:</label> <input
								class="form-control form-inline" id="id3" name="id" readonly></input>
						</div>

						<div class="form-group">
							<label for="book">교재명:</label> <input
								class="form-control form-inline" id="book3" name="book"
								maxlength="40" readonly></input>
						</div>
						<div class="form-group">
							<label for="publisher">출판사:</label> <input
								class="form-control form-inline" id="publisher3"
								name="publisher" maxlength="20" readonly></input>
						</div>
						<div class="form-group">
							<label for="image_detail">상세이미지:</label> <input
								class="form-control form-inline" type="file" id="image_detail3"
								name="image_detail" disabled readonly></input>
						</div>

						<div class="form-group">현재 교재 정보를 삭제할까요?</div>

						<!-- 서브밋 버튼 클릭시 hidden form의 데이터(번호)가 서버로 전송된다. -->
						<button type="submit" class="btn btn-default">확인</button>

						<!-- 취소 버튼 클릭시 현재 모달창을 닫는다. -->
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>

					</form>

				</div>
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

