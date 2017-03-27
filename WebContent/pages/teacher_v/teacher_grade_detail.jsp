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

<title>환영합니다 ${sessionScope.in_id} 강사님</title>

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
.list {
	max-height: 430px;
	overflow: auto;
	margin-bottom: 20px;
}
/* 1. table마다 검색 결과에 따른 학생 수 카운트 영역(div로 처리함) */
.countSt {
	text-align: right;
	padding: 10px;
}

.goback {
	text-align: right;
	padding: 10px;
}

/* 2. 성적 입력 시 사용자의 원활한 입력을 돕기 위한 설명이 포함된 영역 */
.informarea {
	padding: 10px;
	border: 1px whitesmoke solid;
}

/* 3. 성적 입력에 관련된 form 요소들이 포함된 영역 */
.inputarea {
	margin-top: 10px;
	padding: 10px;
	border: 1px whitesmoke solid;
}

/* 4. input element의 줄 간격 조정 */
.inputInfo {
	margin-bottom: 10px;
}

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
	right: 0;
	padding-top: 8px;
}
.seachBn_jho{
	margin-right: 100px;
}

</style>
<script>
	$(document).ready(function() {
		
		//검색 진행후 검색 키워드가 화면에 그대로 남아있도록 액션 추가
		//->검색 폼 부분에 기존 검색 키워드를 재설정
		$("input#svalue").val("${svalue}");
		$("select#skey option[value='${skey}']").attr("selected","selected");
		
		// select-value버튼 ( 입력, 수정, 삭제 ) 클릭 시 모달 창 title 값을 동적 지정
		$("button.select-value").on("click", function() {
			var title_modal = $(".panel-heading").text();
			$("p.select-title").text(title_modal);
		});
		
		/* 성적입력 모달 */
		$("button.insert").on("click", function() {
			
			var attend = $(this).parents("tr").children().eq(4).text();
			
			$("#teacher_grade_insert").modal();
			$("#num1").val($(this).val());
			$("#name1").val($(this).parents("tr").children().eq(1).text());
			$("#phone1").val($(this).parents("tr").children().eq(2).text());
			// 출결 점수가 0이면, 값을 미리 입력하지 않는다.
			if( attend != '0') {
				$("#attend1").val($(this).parents("tr").children().eq(4).text());
				$("#writing1").val($(this).parents("tr").children().eq(5).text());
				$("#practice1").val($(this).parents("tr").children().eq(6).text());
			}
			
			// 과정id와 과목id, skey, svalue를 모달의 hidden폼에 입력
			$("input.modalCourse").val($("input.subCourse").val());
			$("input.modalSubject").val($("input.subSubject").val());
			$("input.modalSkey").val($("#skey").val());
			$("input.modalSvalue").val($("#svalue").val());
			
			
			// 입력의 최소, 최대값을 과목 정보의 배점 값으로 설정
			// placeholder 동적 변경 --> 0점 ~ 배점
			$("#attend1").attr({max : $("#ch1").text(),	placeholder : "0점 이상 " + $("#ch1").text() + "점 이하"});
			$("#writing1").attr({max : $("#ch2").text(), placeholder : "0점 이상 " + $("#ch2").text() + "점 이하"});
			$("#practice1").attr({max : $("#ch3").text(), placeholder : "0점 이상 " + $("#ch3").text() + "점 이하"});
			$("#attend2").attr({max : $("#ch1").text(),	placeholder : "0점 이상 " + $("#ch1").text() + "점 이하"});
			$("#writing2").attr({max : $("#ch2").text(), placeholder : "0점 이상 " + $("#ch2").text() + "점 이하"});
			$("#practice2").attr({max : $("#ch3").text(), placeholder : "0점 이상 " + $("#ch3").text() + "점 이하"});
			
			
		
		});
		
		/* 성적수정 모달 */
		$("button.modify").on("click", function() {
			
			var attend = $(this).parents("tr").children().eq(4).text();
			
			$("#teacher_grade_modify").modal();
			$("#num2").val($(this).val());
			$("#name2").val($(this).parents("tr").children().eq(1).text());
			$("#phone2").val($(this).parents("tr").children().eq(2).text());
			if( attend != '0') {
				$("#attend2").val($(this).parents("tr").children().eq(4).text());
				$("#writing2").val($(this).parents("tr").children().eq(5).text());
				$("#practice2").val($(this).parents("tr").children().eq(6).text());
			}
			
			// 과정id와 과목id, skey, svalue를 모달의 hidden폼에 입력
			$("input.modalCourse").val($("input.subCourse").val());
			$("input.modalSubject").val($("input.subSubject").val());
			$("input.modalSkey").val($("#skey").val());
			$("input.modalSvalue").val($("#svalue").val());
			
			// 입력의 최소, 최대값을 과목 정보의 배점 값으로 설정
			// placeholder 동적 변경 --> 0점 ~ 배점
			$("#attend1").attr({max : $("#ch1").text(),	placeholder : "0점 이상 " + $("#ch1").text() + "점 이하"});
			$("#writing1").attr({max : $("#ch2").text(), placeholder : "0점 이상 " + $("#ch2").text() + "점 이하"});
			$("#practice1").attr({max : $("#ch3").text(), placeholder : "0점 이상 " + $("#ch3").text() + "점 이하"});
			$("#attend2").attr({max : $("#ch1").text(),	placeholder : "0점 이상 " + $("#ch1").text() + "점 이하"});
			$("#writing2").attr({max : $("#ch2").text(), placeholder : "0점 이상 " + $("#ch2").text() + "점 이하"});
			$("#practice2").attr({max : $("#ch3").text(), placeholder : "0점 이상 " + $("#ch3").text() + "점 이하"});
		});

		/* 성적삭제 모달 */
		$("button.delete").on("click", function() {
			$("#teacher_grade_delete").modal();
			$("#num3").val($(this).val());
			$("#name3").val($(this).parents("tr").children().eq(1).text());
			$("#phone3").val($(this).parents("tr").children().eq(2).text());
			$("#attend3").val($(this).parents("tr").children().eq(4).text());
			$("#writing3").val($(this).parents("tr").children().eq(5).text());
			$("#practice3").val($(this).parents("tr").children().eq(6).text());
			
			// 과정id와 과목id, skey, svalue를 모달의 hidden폼에 입력
			$("input.modalCourse").val($("input.subCourse").val());
			$("input.modalSubject").val($("input.subSubject").val());
			$("input.modalSkey").val($("#skey").val());
			$("input.modalSvalue").val($("#svalue").val());
		});
		
		/* 성적입력제한 */
		/* 클릭 할 경우 안내문구 창 띄우기 */
		
		
		
		// 교재명을 클릭 시 이미지 확인 modal창 동적 생성
		$("a.bookImage").on("click", function() {
			
			var book_img = $(this).next().attr("value");
			console.log(book_img);
			var book_imgPath = "/grade_Management_System_0120/picture/" + book_img;
			console.log(book_imgPath);
			// 모달 창 텍스트 출력 부분
			// modal-title 출력
			$("#bookImageModal h4.modal-title").text($(this).text());
			// img의 title값 동적 조정
			$("#bookImageModal img#book_img").attr("title", book_img);
			// img의 src값 동적 조정
			$("#bookImageModal img#book_img").attr('src', book_imgPath);

			$("#bookImageModal").modal();
			
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
				<a class="navbar-brand" href="ins_scheduleList.it"><img
					src="/grade_Management_System_0120/img/sist_logo.png"></a> <span
					class="navbar-brand">성적처리프로그램 v2.0 <small>강사용</small></span>
				<!-- <span class="navbar-brand"><img title="계정별 아이콘"></span> -->

			</div>
			<!-- Navibar header 영역 종료 -->

			<!-- 사용자 계정 관련 Dropdown 메뉴 아이콘 -->
			<ul class="nav navbar-top-links navbar-right">
				<!-- 사용자 이름 보이기 -->
				<li class="navbar-brand"><small> ${sessionScope.in_id} 강사님 환영합니다.</small></li>
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
						<li><a href="logOut.it?key=in"><i class="fa fa-sign-out fa-fw"></i>
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
						<!-- 강의 스케줄 조회 -->
						<li><a href="teacher_scheduleList.jsp">
						<i class="glyphicon glyphicon-calendar"></i> 강의스케쥴 조회<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">

								<!-- 강의예정, 강의중, 강의 종료 드롭다운 메뉴 -->
								<li>
									<!-- 경로명 수정!!! --> <a href="ins_scheduleList.it?lid=sub1"><i
										class="glyphicon glyphicon-step-forward"></i> 강의 예정</a> <!-- /.nav-second-level -->
								</li>
								<li><a href="ins_scheduleList.it?lid=sub2"><i
										class="glyphicon glyphicon-play"></i> 강의 중</a> <!-- /.nav-second-level -->
								</li>
								<li><a href="ins_scheduleList.it?lid=sub3"><i
										class="glyphicon glyphicon-stop"></i> 강의 종료</a> <!-- /.nav-second-level -->
							</ul>
								</li>
						<li><a href="ins_subScoreList.it"><i
								class="fa fa-pencil fa-fw"></i>배점 관리</a></li>
						<li><a href="ins_subGradeList.it" class="active"><i
								class="fa fa-edit fa-fw"></i>성적 관리</a></li>
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
					<h4 style="padding-top: 10px">성적 관리</h4>
					<hr>
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>과목 번호</th>
									<th>과정명</th>
									<th>과정 기간</th>
									<th>강의실</th>
									<th>과목명</th>
									<th>과목 기간</th>
									<th>교재명</th>
									<th>시험문제</th>
									<th>출결</th>
									<th>필기</th>
									<th>실기</th>
									<th>성적등록여부</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="m" items="${subjectList}">
									<tr>
										<td>${m.subject_id}</td>
										<td>${m.course_name}</td>
										<td>${m.cr_start_date}~ ${m.cr_end_date}</td>
										<td>${m.classroom_name}</td>
										<td>${m.subject_name}</td>
										<td>${m.sj_start_date}~ ${m.sj_end_date}</td>
										<td><a href="#" class="bookImage">${m.book_name}</a>
											<input type="hidden" value="${m.book_img}">
										</td>
										<td><a href="doDownload.it?exam=${test_list[0].test_paper}">${test_list[0].test_paper}</a></td>
										<td id="ch1">${m.sc_attend}</td>
										<td id="ch2">${m.sc_writing}</td>
										<td id="ch3">${m.sc_practice}</td>
										<!-- 성적입력여부를 O, X로 변환이 필요 -->
										
										<td>
										<c:out value="${gradeCheckMessage}" />
										</td>
									</tr>
								</c:forEach>
							</tbody>

						</table>

						<!-- stylesheet 적용->검색 폼 센터 정렬 -->

						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->

					<!-- content 포함 영역 START!! -->
					<!-- /.panel -->
					<!-- 과목 상세정보 구간 시작! -->
					<div class="panel panel-default">
						<div class="panel-heading"><c:out value="${term_subject}"></c:out></div>
						<div class="panel-body list">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>번 호</th>
										<th>이 름</th>
										<th>전화번호</th>
										<th>수료여부</th>
										<th>출 결</th>
										<th>필 기</th>
										<th>실 기</th>
										<%-- 버튼이 위치할 영역 --%>
										<th style="width: 135px; text-align: center;">입력 / 수정 /
											삭제</th>
									</tr>
								</thead>
								<tbody>
									<!-- 
									<tr>
										
										<td>1</td>
										<td>홍길동</td>
										<td>010-1234-1234</td>
										<td>수료</td>
										<td>20</td>
										<td>25</td>
										<td>30</td>
										<td>
											<button class="btn btn-default btn-xs insert">입력</button>
											<button class="btn btn-default btn-xs modify">수정</button>
											<button class="btn btn-default btn-xs delete">삭제</button>
										</td>
										
									</tr>
									 -->
									<!--  
									request.setAttribute("stList", stList) 로 객체를 전송했음에도 불구하고
									읽어오지를 못한다.
									 -->
									  
									<c:forEach var="s" items="${stList}">
									<tr>
										<td><span class="subStudent_id">${s.student_id}</span>
											<input type="hidden" class="subCourse" value="${s.course_id}" />
											<input type="hidden" class="subSubject" value="${s.subject_id}" />
										</td>
										<td>${s.st_name}</td>
										<td>${s.st_phone}</td>
										<!-- 
							            case 1:
							               s = "수료";
							               break;
							            case 0:
							               s = "수료중";
							               break;
							            case -1:
							               s = "중도탈락";
							               break;
							            default:
							               s = "확인불가";
										 -->
										<td>
											<c:set var="failck" value="${s.failcheck}"/>
											<c:choose>
												<c:when test="${failck == 1}">
													수료
												</c:when>
												<c:when test="${failck == 0}">
													수료중
												</c:when>
												<c:when test="${failck == -1}">
													중도탈락
												</c:when>
												<c:otherwise>
													확인불가
												</c:otherwise>
											</c:choose>
										</td>
										<td>${s.gr_attend}</td>
										<td>${s.gr_writing}</td>
										<td>${s.gr_practice}</td>
										<td>
										<%-- <button class="btn btn-default btn-xs insert 
											${((empty s.gr_attend)?'':'disabled'}" 
											${((empty s.gr_attend)&(empty s.gr_writing)&(empty s.gr_pracitce)?"":"disabled"}>입력</button> --%>
											<button class="btn btn-default btn-xs insert select-value" ${((s.gr_attend + s.gr_attend + s.gr_attend) == 0)?"":"disabled"} value="${s.student_id}">입력</button>
											<button class="btn btn-default btn-xs modify select-value" value="${s.student_id}">수정</button>
											<button class="btn btn-default btn-xs delete select-value" ${((s.gr_attend + s.gr_attend + s.gr_attend) == 0)?"disabled":""} value="${s.student_id}">삭제</button>
										</td>
									</tr>
									</c:forEach>

								</tbody>
							</table>

							

							</div>
						</div>
						<!-- 검색 폼 -->
							<div style="text-align: center;">
								<form class="form-inline" action="ins_gra_subjectList.it" method="post">
									<div class="form-group">
										<%-- 해당 과목ID hidden form에 담아 보내기 --%>
										<input type="hidden" id="subjectId" name="subjectId" value="${subjectId}">
										<select class="form-control" id="skey" name="skey">
											<option value="all">전체</option>
											<option value="name">이름</option>
											<!-- 검색 기준 추가 -->
										</select> <input type="text" class="form-control" id="svalue" name="svalue">
										<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
									</div>
									<button type="submit" class="btn btn-default xs">
										<i class="fa fa-search"></i> Search
									</button>
									<span class="seach_info">총 <c:out value="${countSt}"></c:out>명의 수강생 검색</span>
								</form>
							</div>
							<div class="goback">
								<form action="ins_subGradeList.it">
									<button class="btn btn-default btn-md" data-toggle="modal"
										data-target="#goBack">뒤로가기</button>
								</form>
					</div>
					<!-- 과목 상세 정보에 대한 내용 끝 -->
				</div>
			</div>
		</div>
		<!-- /.panel -->
	</div>
	<!-- /#page-wrapper -->

	<%-- 성적 입력 Modal --%>
	<div id="teacher_grade_insert" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">성적 입력</h4>
					<p class="select-title"></p>
				</div>
				<div class="modal-body">
					<%-- action="" 속성의 주소 변경 --%>

					<form action="ins_addGrade.it" method="post" class="form-horizontal">

						<!-- 과정id, 과목id 동적 생성 -->
						<div class="form-group">
							<input type="hidden" class="modalCourse" name="subCourse_id" value="" />
							<input type="hidden" class="modalSubject" name="subSubject_id" value="" />
							<input type="hidden" class="modalSkey" name="subSkey" value="" />
							<input type="hidden" class="modalSvalue" name="subSvalue" value="" />
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-2" for="num1">번호:</label>
							<div class="col-sm-10">
								<input class="form-control" id="num1" name="num" readonly>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="name1">이름:</label>
							<div class="col-sm-10">
								<input class="form-control" id="name1" name="name" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="phone1">연락처:</label>
							<div class="col-sm-10">
								<input class="form-control" id="phone1" name="phone" readonly>
							</div>
						</div>
						<!-- 출결, 필기, 실기 시험 입력 점수에 대한 제약이 필요함. -->
						<!-- 클릭 시 배점 정보에 따라 제약조건 설정할 예정 -->
						<div class="form-group">
							<label class="control-label col-sm-2" for="attend1">출결:</label>
							<div class="col-sm-10">
								<input class="form-control" id="attend1" name="attend"
									type=number min=0 placeholder="20~출결배점 점수" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="writing1">필기:</label>
							<div class="col-sm-10">
								<input class="form-control" id="writing1" name="writing"
									type=number min=0 placeholder="0~필기배점 점수" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="practice1">실기:</label>
							<div class="col-sm-10">
								<input class="form-control" id="practice1" name="practice"
									type=number min=0 placeholder="0~실기배점 점수" required>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 입력한 성적을 등록할까요?</div>
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

	<%-- 성적 수정 Modal --%>
	<div id="teacher_grade_modify" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">성적 수정</h4>
					<p class="select-title"></p>
				</div>
				<div class="modal-body">
					<%-- action="" 속성의 주소 변경 --%>


					<form action="ins_modifyGrade.it" method="post" class="form-horizontal">
					
					
						<!-- 과정id, 과목id 동적 생성 -->
						<div class="form-group">
							<input type="hidden" class="modalCourse" name="subCourse_id" value="" />
							<input type="hidden" class="modalSubject" name="subSubject_id" value="" />
							<input type="hidden" class="modalSkey" name="subSkey" value="" />
							<input type="hidden" class="modalSvalue" name="subSvalue" value="" />
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="num2">번호:</label>
							<div class="col-sm-10">
								<input class="form-control" id="num2" name="num" readonly>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="name2">이름:</label>
							<div class="col-sm-10">
								<input class="form-control" id="name2" name="name" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="phone2">연락처:</label>
							<div class="col-sm-10">
								<input class="form-control" id="phone2" name="phone" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="attend2">출결:</label>
							<div class="col-sm-10">
								<input class="form-control" id="attend2" name="attend"
									type=number min=0 placeholder="0~출결배점 점수" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="writing2">필기:</label>
							<div class="col-sm-10">
								<input class="form-control" id="writing2" name="writing"
									type=number min=0 placeholder="0~필기배점 점수" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="practice2">실기:</label>
							<div class="col-sm-10">
								<input class="form-control" id="practice2" name="practice"
									type=number min=0 placeholder="0~실기배점 점수" required>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 수정한 성적으로 등록할까요?</div>
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

	<%-- 성적 삭제 Modal --%>
	<div id="teacher_grade_delete" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">성적 삭제</h4>
					<p class="select-title"></p>
				</div>
				<div class="modal-body">
					<%-- action="" 속성의 주소 변경 --%>

					<form action="ins_deleteGrade.it" method="post" class="form-horizontal">
					
						<!-- 과정id, 과목id 동적 생성 -->
						<div class="form-group">
							<input type="hidden" class="modalCourse" name="subCourse_id" value="" />
							<input type="hidden" class="modalSubject" name="subSubject_id" value="" />
							<input type="hidden" class="modalSkey" name="subSkey" value="" />
							<input type="hidden" class="modalSvalue" name="subSvalue" value="" />
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-2" for="student_id">번호:</label>
							<div class="col-sm-10">
								<input class="form-control" id="num3" name="num" readonly>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="student_id">이름:</label>
							<div class="col-sm-10">
								<input class="form-control" id="name3" name="name" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pw">연락처:</label>
							<div class="col-sm-10">
								<input class="form-control" id="phone3" name="phone" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="phone">출결:</label>
							<div class="col-sm-10">
								<input class="form-control" id="attend3" name="attend"
									type=number readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="phone">필기:</label>
							<div class="col-sm-10">
								<input class="form-control" id="writing3" name="writing"
									type=number readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="phone">실기:</label>
							<div class="col-sm-10">
								<input class="form-control" id="practice3" name="practice"
									type=number readonly>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 성적을 삭제할까요?</div>
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
	
		<%-- 교재 이미지 Modal--%>
	<div id="bookImageModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<%-- EL 표현으로 파일명을 얻어와서 참조주소 동적 생성 --%>
					<img src="" id="book_img" title="" style="width:100%;"/>
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

