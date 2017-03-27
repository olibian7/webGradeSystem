<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%-- 현재 페이지를 단독 실행하지 않습니다. 서블릿 요청 시 해당 페이지가 실행됩니다. --%>
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

/* 총 검색 수 카운트 */
.countSt {
	text-align: right;
	padding: 10px;
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

/* 모달창 후터부분 줄없애기 */
.modal-footer {
	border-top: 0px;
}

.table {
	margin-bottom: 20px;
}

.col-sm-4 {
	padding-left: 0px;
	padding-right: 0px;
}

.list {
	max-height: 300px;
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
	$(document).ready( function() {
		
		
		//검색 진행후 검색 키워드가 화면에 그대로 남아있도록 액션 추가
		//->검색 폼 부분에 기존 검색 키워드를 재설정
		$("input#subValue").val("${subValue}");
		$("select#subKey option[value='${subKey}']").attr("selected","selected");
		
		
		// Ajax Action
		$("button.show_studentlist").on("click", function() {
			var keyword = $(this).parents("tr").children().eq(2).text(); // 과목번호
			console.log(keyword);
			
			// Ajax를 통한 동적 출력 구문
			$.post("teacher_scheduleList_ajaxReceive.it", {keyword : keyword}, function(data) {
				
 				var myObj = JSON.parse(data);
				var obj_length = myObj.student.length;
				console.log(obj_length);
				var result = "";
				
				for(var i = 0; i < obj_length; ++i) {
					result += "<tr>"
						+ "<td>"
						+ myObj.student[i].student_id
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].name
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].phone
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].failcheck
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].gr_attend + "/" + myObj.student[i].sc_attend 
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].gr_writing + "/" + myObj.student[i].sc_writing
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].gr_practice  + "/" + myObj.student[i].sc_practice
						+ "</td>";
					result += "</tr>";
				}
				$("#studentList").modal();
				
				$("table.studentList > tbody").html(result);
				$("#countStudent").text(obj_length);
			});
		});
		
		// studentSearch 버튼 클릭 시 검색 출력
		$("button.studentSearch").on("click", function() {
			var keyword = $("button.show_studentlist").parents("tr").children().eq(2).text(); // 과목번호
			console.log(keyword);
			
			var searchkey = $("select#searchkey").val();
			console.log(searchkey);
			
			var searchvalue = $("input#searchvalue").val();
			console.log(searchvalue);
			
			// Ajax를 통한 동적 출력 구문
			$.post("teacher_scheduleList_ajaxReceive.it", {keyword : keyword, searchkey : searchkey, searchvalue : searchvalue}, function(data) {
				
 				var myObj = JSON.parse(data);
				var obj_length = myObj.student.length;
				console.log(obj_length);
				var result = "";
				
				for(var i = 0; i < obj_length; ++i) {
					result += "<tr>"
						+ "<td>"
						+ myObj.student[i].student_id
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].name
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].phone
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].failcheck
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].gr_attend + "/" + myObj.student[i].sc_attend 
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].gr_writing + "/" + myObj.student[i].sc_writing
						+ "</td>"
						+ "<td>"
						+ myObj.student[i].gr_practice  + "/" + myObj.student[i].sc_practice
						+ "</td>";
					result += "</tr>";
				}
				
				$("table.studentList > tbody").html(result);
				$("#countStudent").text(obj_length);
				/* 
				//검색 진행후 검색 키워드가 화면에 그대로 남아있도록 액션 추가
				//-> 검색 폼 부분에 기존 검색 키워드를 재설정
				$("#searchkey").on("change", function() {
					$("#searchvalue").val("");
				});

				//JSON으로 가져온 skey, svalue
				var skey1 = myObj.keyvalue[0].skey;
				var svalue1 = myObj.keyvalue[0].svalue;

				$("#searchkey").val(skey1).attr("selected", "selected");
				$("#searchvalue").val(svalue1);
				 */
			});
		});
		
		
		// (수강생) 명단보기 버튼 클릭 시 제목 동적 생성
		$("button.show_studentlist").on("click", function() {
			
			var subject_name = $(this).parents("tr").children().eq(3).text();
			var subject_period = $(this).parents("tr").children().eq(4).text();
			var studentListModalTitle =  subject_name + "(" + subject_period + ")" + "의 수강생명단";
			$("#studentListModal h4.modal-title").text(studentListModalTitle);
			$("#studentListModal").modal();
			
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
						<li><a href="#"> <i class="glyphicon glyphicon-calendar"></i>
								강의스케쥴 조회<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">

								<!-- 강의예정, 강의중, 강의 종료 드롭다운 메뉴 -->
								<li>
									<!-- 경로명 수정!!! --> <a
									href="ins_scheduleList.it?lid=sub1"><i
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
					<h1></h1>
					<!--  <h1 class="page-header">강의스케줄 조회 > 강의 예정</h1>  -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<!-- content의 내용을 입력하시면 됩니다. table이라던지 기타 등등 집어넣으세요! -->
			<div class="row">
				<div class="col-lg-12">

					<h4>강의스케줄 조회 > <c:out value="${title}"></c:out></h4>
					<hr>
					<div class="table-responsive list2">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>과정명</th>
								<th>과정기간</th>
								<th>과목번호</th>
								<th>과목명</th>
								<th>과목기간</th>
								<th>강의실</th>
								<th>교재명</th>
								<th>수강생 등록 인원</th>
								<th>수강생 명단</th>
							</tr>
						</thead>
						<tbody>
							<%-- 
							<tr>
								<td>sj010</td>
								<td>[100% 국비지원] 오라클 전문가 과정</td>
								<td>2017-04-01 ~ 2017-11-30</td>
								<td>cl003</td>
								<td>오라클</td>
								<td>2017-04-01 ~ 2017-07-01</td>
								<td><a href="">자바</a></td>
								<td>4</td>
								<td><button class="btn btn-default btn-xs"
										data-toggle="modal" data-target="#studentList">명단 보기</button></td>
							</tr>
						--%>
							<c:forEach var="m" items="${list}">
								<tr>
									<td>${m.course_name}</td>
									<td>${m.cr_start_date}~${m.cr_end_date}</td>
									<td>${m.subject_id}</td>
									<td>${m.subject_name}</td>
									<td>${m.sj_start_date}~${m.sj_end_date}</td>
									<td>${m.classroom_name}</td>
									<td><a href="#" class="bookImage" >${m.book_name}</a>
										<input type="hidden" value="${m.book_img}">
									</td>
									<td>${m.st_count}</td>
									<td><button	class="btn btn-default btn-xs show_studentlist">명단 보기</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
					<!-- 검색 폼 -->
					<div style="text-align: center;">
						<form class="form-inline" method="post">
							<div class="form-group">
								<select class="form-control" id="subKey" name="subKey">
									<option value="1">전체</option>
									<option value="2">과목번호</option>
									<option value="3">과목명</option>
									<!-- 검색 기준 추가 -->
								</select> <input type="text" class="form-control" id="subValue"
									name="subValue">
								<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
							</div>
							<button type="submit" class="btn btn-default xs seachBn_jho">
								<i class="fa fa-search"></i> Search
							</button>
							<span class="seach_info">총 <c:out value="${count}"></c:out>개의 강의정보 검색</span>
						</form>
					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /#page-wrapper -->
		</div>
	</div>
	<!-- /#wrapper -->


	<%-- 수강생 명단 Modal --%>
	<div id="studentListModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">

					<%-- action="studentdelete.jsp" 속성의 주소 변경 --%>
					<form action="" method="post">

						<%-- 번호 전송은 hidden form 추가 --%>
						<input type="hidden" id="" name="" value="">


						<div class="table-responsive list">
							<table class="table table-striped table-bordered table-hover studentList">
								<thead>
									<tr>
										<th>번호</th>
										<th>이름</th>
										<th>전화번호</th>
										<th>수료 여부</th>
										<th>출결</th>
										<th>필기</th>
										<th>실기</th>
									</tr>
								</thead>
								<tbody>
									<!-- 
									<tr>
										<td>1</td>
										<td>홍길동</td>
										<td>010-1234-1234</td>
										<td>-</td>
										<td>-</td>
										<td>-</td>
										<td>-</td>
									</tr>
									 -->
									<%-- Ajax표현식이 위치할 예정 --%>
								</tbody>
							</table>
						</div>
					</form>
					<!-- 검색 폼 -->
					<div style="text-align: center; padding-top: inherit;">
						<form class="form-inline" >
							<div class="form-group">
								<select class="form-control" id="searchkey" name="searchkey">
									<option value ="all">전체</option>
									<option value ="stid">학생ID</option>
									<option value ="stname">학생이름</option>
									<!-- 검색 기준 추가 -->
								</select> <input type="text" class="form-control" id="searchvalue"
									name="searchvalue" size="18">
								<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
							</div>
							<button type="button" class="btn btn-default xs studentSearch"
								style="margin-right: 160px">
								Search <i class="fa fa-search"></i>
							</button>
							<span class="seach_info">총 <span id="countStudent"></span>명의 학생정보 검색</span>
						</form>
					</div>
					<!-- 확인 버튼 클릭시 현재 모달창을 닫는다. -->
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">확인</button>
					</div>

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

