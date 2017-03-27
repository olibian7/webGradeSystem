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

.table {
	margin-bottom: 10px;
}

.col-sm-4 {
	padding-left: 0px;
	padding-right: 0px;
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
<script>
	$(document).ready(function() {
		
		//검색 진행후 검색 키워드가 화면에 그대로 남아있도록 액션 추가
		//-> 검색 폼 부분에 기존 검색 키워드를 재설정
		$("#skey").on("change",function(){
			$("#svalue").val("");
		});
		
		$("#skey").val("${skey}").attr("selected", "selected");
		$("#svalue").val("${svalue}");
	
		
		
		$("button.showSubjectGrade").on("click", function() {

			$("input.subject_id").val($(this).parents("tr").children().eq(0).text());
			
		});
		
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
				<!-- 수정  -->
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
						<li><a href="#"> <i
								class="glyphicon glyphicon-calendar"></i> 강의스케쥴 조회<span
								class="fa arrow"></span></a>
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
							</ul></li>
						<li><a href="ins_subScoreList.it"><i
								class="fa fa-pencil fa-fw"></i>배점 관리</a></li>
						<li><a href="ins_subGradeList.it"><i
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

			<div class="row">
				<div class="col-lg-12">
					<!-- content의 내용을 입력하시면 됩니다. table이라던지 기타 등등 집어넣으세요! -->
					<h4 style="padding-top: 10px">성적 관리</h4>
					<hr>
					<div class="table-responsive list">
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
									<th style="width: 20px;">보기</th>
								</tr>
							</thead>
							<tbody>
								<!-- 
								<tr>
									<td>sj010</td>
									<td>cn006</td>
									<td>2017-04-01 ~ 2017-11-30</td>
									<td>cl003</td>
									<td>오라클</td>
									<td>2017-04-01 ~ 2017-07-01</td>
									<td><a href="">자바</a></td>
									<td><a href="">test.zip</a></td>
									<td>25</td>
									<td>40</td>
									<td>35</td>
									<td>O</td>
									<td><a href="teacher_grade_detail.jsp">
											<button class="btn btn-default btn-xs" title="선택">선택</button>
									</a></td>
								</tr>
								 -->
								 <c:forEach var="i" begin="1" end="${listSize}" varStatus="status">
								 <c:set var = "m" value ="${list}"/>
								 <c:set var ="g" value="${gradeCheck}" />
								 	<tr>
										<td>${m[i-1].subject_id}</td>
										<td>${m[i-1].course_name}</td>
										<td>${m[i-1].cr_start_date} ~ ${m[i-1].cr_end_date}</td>
										<td>${m[i-1].classroom_name}</td>
										<td>${m[i-1].subject_name}</td>
										<td>${m[i-1].sj_start_date} ~ ${m[i-1].sj_end_date}</td>
										<td><a href="#" class="bookImage">${m[i-1].book_name}</a>
											<input type="hidden" value="${m[i-1].book_img}">
										</td>
										<td><a href="doDownload.it?exam=${test_list[i-1].test_paper}">${(empty test_list[i-1].test_paper)?'-':test_list[i-1].test_paper_name}</a></td>
										<td>${m[i-1].sc_attend}</td>
										<td>${m[i-1].sc_writing}</td>
										<td>${m[i-1].sc_practice}</td>
										<!-- 성적입력여부를 O, X로 변환이 필요 -->
										<td>${g[i-1]}</td>
										<td>
											<form action="ins_gra_subjectList.it" method="post">
												<input type="hidden" class="subject_id" name="subjectId" value="">
												<button type="submit" class="btn btn-default btn-xs showSubjectGrade" title="선택">선택</button>
											</form>
										</td>
									</tr>
								 </c:forEach>

							</tbody>
						</table>



					</div>
						<!-- 검색 폼 -->
						<div style="text-align: center;">
							<form method="post" class="form-inline">
								<div class="form-group">
									<select class="form-control" id="skey" name="skey">	
									<option value="all">전체</option>
										<option value="subject_id">과목 번호</option>
										<option value="subject_name">과목명</option>
										<option value="course_name">과정명</option>
										
										<!-- 검색 기준 추가 -->
									</select> <input type="text" class="form-control" id="svalue"
										name="svalue">
									<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
								</div>
								<button type="submit" class="btn btn-default xs">
									<i class="fa fa-search"></i> Search
								</button>
								<span class="seach_info">총 <c:out value="${count}"></c:out>개의 과목정보 검색</span>
							</form>

						</div>

				</div>
			</div>
		</div>

		<!-- /.row -->
	</div>
	<!-- /#page-wrapper -->
	
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

