<%-- 강사계정 배점관리 --%>
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
<!-- <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
/* #scoreInsertdiv{
 display: none;
}  */

/* 모달 창 헤더 부분 스타일 */
.list {
	max-height: 430px;
	overflow: auto;
	margin-bottom: 20px;
}
.modal-header {
	color: #333;
	background-color: #f5f5f5;
	border-color: #ddd;
	padding: 10px 15px;
	border-bottom: 1px solid transparent;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
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
		
			
		// 배점 변경 구간 ------------------------------>	
		
		// 배점 변경 버튼 클릭 시 modal 액션
		$(".scorebn").on("click", function() {
			// 과목명 및 과목기간 변수에 저장 
			var course_title = $(this).parents("tr").children().eq(3).text();
			var subject_period = $(this).parents("tr").children().eq(4).text();
			// 배점 등록 상황에 따라서 입력을 제한(readonly)한다.
			// 1. 배점이 모두 등록된 경우 -> 배점 등록을 제한, 수정-삭제는 활성
			// 2. 배점이 등록되지 않은 경우 -> 배점 등록만 활성화, 수정-삭제는 비활성
			
			// 배점 값들을 미리 변수에 저장하여 재사용
			var attendance = $(this).parents("tr").children().eq(9).text();
			var exam = $(this).parents("tr").children().eq(10).text();
			var prac_exam = $(this).parents("tr").children().eq(11).text();
			
			var sum_score = attendance + exam + prac_exam;
			
			
			$("#scoreInsertdiv").show();
			$("#scoreUpdatediv").hide();
			$("#scoreDeletediv").hide();
			$("#scoreInsert").addClass("btn-primary");
			$("#scoreUpdate").removeClass("btn-primary");
			$("#scoreDelete").removeClass("btn-primary");
			$("#scoreModify h4.modal-title").text(course_title + "(" + subject_period + ")");
			
				$("#attendance3").attr({'readonly':true, 'title':'아직 배점이 입력되지 않았습니다.'});
				$("#exam3").attr({'readonly':true, 'title':'아직 배점이 입력되지 않았습니다.'});
				$("#prac_exam3").attr({'readonly':true, 'title':'아직 배점이 입력되지 않았습니다.'});
			if(sum_score == "000") {
				// 배점이 입력되지 않은 경우 배점 입력 빼고 나머지 비활성(readonly)처리
				$("#attendance2").attr({'readonly':true, 'title':'아직 배점이 입력되지 않았습니다.'});
				$("#exam2").attr({'readonly':true, 'title':'아직 배점이 입력되지 않았습니다.'});
				$("#prac_exam2").attr({'readonly':true, 'title':'아직 배점이 입력되지 않았습니다.'});
				
				
			} else {
				// 배점이 입력된 경우 배점 입력을 readonly로 제한
				$("#attendance1").attr({'readonly':true, 'title':'이미 배점이 입력되었습니다.'});
				$("#exam1").attr({'readonly':true, 'title':'이미 배점이 입력되었습니다.'});
				$("#prac_exam1").attr({'readonly':true, 'title':'이미 배점이 입력되었습니다.'});
				
			}
			
			// 동적으로 속성 제어한 흔적 초기화
			// 클릭을 여러 번하면, readonly 흔적이 남아서 제대로 동작하지 않더라.... 
			$(".close_modal").on("click", function() { 
				for(var i = 1; i < 4; ++i) {
					var temp1 = "#attendance" + i;
					var temp2 = "#exam" + i; 
					var temp3 = "#prac_exam" + i;
					
					$(temp1).removeAttr("readonly", "title");
					$(temp2).removeAttr("readonly", "title");
					$(temp3).removeAttr("readonly", "title");
				}
			});
			

			/* 배점변경 -> 모달(배점등록) */
			// 과목번호 출력
			$("#subject_id1").val($(this).parents("tr").children().eq(2).text());
			$("#attendance1").val(attendance);
			$("#exam1").val(exam);
			$("#prac_exam1").val(prac_exam);
			
			/* 배점변경 -> 모달(배점수정) */
			// 기존의 값이 화면에 출력
			$("#subject_id2").val($(this).parents("tr").children().eq(2).text());
			$("#attendance2").val(attendance);
			$("#exam2").val(exam);
			$("#prac_exam2").val(prac_exam);
			
			/* 배점변경 -> 모달(배점삭제) */
			// 기존의 값이 화면에 출력
			$("#subject_id3").val($(this).parents("tr").children().eq(2).text());
			$("#attendance3").val(attendance);
			$("#exam3").val(exam);
			$("#prac_exam3").val(prac_exam);
			
			// 모달 창 띄우기
			$("#scoreModify").modal();
			
			// 모달 창 닫기
			var n1 = Number($('#attendance1').val());
			var n2 = Number($('#exam1').val());
			var n3 = Number($('#prac_exam1').val());
			var check1 = n1 + n2 + n3;
			
			$('#attendance1').on('change', function() {				
				n1 = Number($('#attendance1').val());
				check1 = n1 + n2 + n3;
				console.log("총점" + check1);
			});
			
			$('#exam1').on('change', function() {				
				n2 = Number($('#exam1').val());
				check1 = n1 + n2 + n3;
				console.log("총점" + check1);
			});
			
			$('#prac_exam1').on('change', function() {				
				n3 = Number($('#prac_exam1').val());
				check1 = n1 + n2 + n3;
				console.log("총점" + check1);
			});
			
			console.log("총점" + check1);
			
			$("button.submit").on('click', function() {
				if(check1 != 100) {
					$('button.submit').attr('type', 'button');
					alert("배점 점수를 확인하시기 바랍니다. 현재 점수 총합 : " + check1);
					
				} else {
					$('button.submit').attr('type', 'submit');
					alert("정상적으로 입력되었습니다.");
				}
			});
			
		});
		
		
		$("#scoreInsert").on("click", function() {
			$("#scoreInsertdiv").show();
			$("#scoreUpdatediv").hide();
			$("#scoreDeletediv").hide();
			$("#scoreInsert").addClass("btn-primary");
			$("#scoreUpdate").removeClass("btn-primary");
			$("#scoreDelete").removeClass("btn-primary");
		});
		
		$("#scoreUpdate").on("click", function() {
			$("#scoreInsertdiv").hide();
			$("#scoreUpdatediv").show();
			$("#scoreDeletediv").hide();
			$("#scoreUpdate").addClass("btn-primary");
			$("#scoreInsert").removeClass("btn-primary");
			$("#scoreDelete").removeClass("btn-primary");
		});
		$("#scoreDelete").on("click", function() {
			$("#scoreInsertdiv").hide();
			$("#scoreUpdatediv").hide();
			$("#scoreDeletediv").show();
			$("#scoreDelete").addClass("btn-primary");
			$("#scoreUpdate").removeClass("btn-primary");
			$("#scoreInsert").removeClass("btn-primary");
		});
		$(".deletebn").on("click", function() {
			var result = confirm("정말 삭제하시겠습니까?");
		});
		
		// -----------------------------------------> 배점 변경 구간 종료
		
		// 시험변경 구간 ----------------------------------------------->
		
		
		
		
		
		//시험변경 버튼 클릭
		$("button.test_change").on("click", function() {
			$("#test_Indiv").show();
			$("#test_Updiv").hide();
			$("#test_Dediv").hide();

			$(".add_test").addClass("btn-primary");
			$(".modify_test").removeClass("btn-primary");
			$(".delete_test").removeClass("btn-primary");
			
			var course_title = $(this).parents("tr").children().eq(0).text();
			var subject_period = $(this).parents("tr").children().eq(1).text();
			var test_paper = $(this).parents("tr").children().eq(8).text();
			//해당 과목 ID 읽어오기
			$("#subject_id4").val($(this).parents("tr").children().eq(2).text());
			$("#subject_id5").val($(this).parents("tr").children().eq(2).text());
			$("#subject_id6").val($(this).parents("tr").children().eq(2).text());
			$("#testModify span.original_file").text("원본 파일명: " + test_paper  );
			$("#exam_file3").val(test_paper);
			$("#testModify h4.course-title").text(course_title + "(" + subject_period + ")");
			$("#testModify").modal();
			
		});
		
		//시험정보 등록 버튼
		$("a.add_test").on("click", function() {
			$("#test_Indiv").show();
			$("#test_Updiv").hide();
			$("#test_Dediv").hide();
			$(".add_test").addClass("btn-primary");
			$(".modify_test").removeClass("btn-primary");
			$(".delete_test").removeClass("btn-primary");
		});
		
		//시험정보 수정 버튼
		$("a.modify_test").on("click", function() {
		
			$("#test_Indiv").hide();
			$("#test_Updiv").show();
			$("#test_Dediv").hide();
			$(".add_test").removeClass("btn-primary");
			$(".modify_test").addClass("btn-primary");
			$(".delete_test").removeClass("btn-primary");
		});
		
		//시험정보 삭제 버튼
		$("a.delete_test").on("click", function() {
			$("#test_Indiv").hide();
			$("#test_Updiv").hide();
			$("#test_Dediv").show();
			
			$(".add_test").removeClass("btn-primary");
			$(".modify_test").removeClass("btn-primary");
			$(".delete_test").addClass("btn-primary");
			
		});
		$(".deletebn").on("click", function() {
			var result = confirm("정말 삭제하시겠습니까?");
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
					<h1></h1>
					<!--  <h1 class="page-header">강의스케줄 조회 > 강의 예정</h1>  -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<!-- content의 내용을 입력하시면 됩니다. table이라던지 기타 등등 집어넣으세요! -->
			<div class="row">
				<div class="col-lg-12">
					<h4>배점 관리</h4>
					<!-- /.panel-heading -->
					<hr>
					<div class="table-responsive list">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>과정명</th>
									<th>과정 기간</th>
									<th>과목 번호</th>
									<th>과목명</th>
									<th>과목 기간</th>
									<th>강의실</th>
									<th>교재명</th>
									<th>시험날짜</th>
									<th>시험문제</th>
									<th>출결</th>
									<th>필기</th>
									<th>실기</th>
									<th style="width: 50px;">배점 변경</th>
									<th style="width: 50px;">시험 변경</th>
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
									<td>-</td>
									<td>-</td>
									<td>-</td>
									<td><button class="btn btn-default btn-xs scorebn"
											title="배점 변경" data-toggle="modal" data-target="#scoreModify">배점
											변경</button></td>
									<td><button class="btn btn-default btn-xs test_change"
											title="시험 변경">시험 변경</button></td>
								</tr>
								 -->
								<c:forEach var="m" items="${list}" varStatus="status">
									<tr>
										<td>${m.course_name}</td>
										<td>${m.cr_start_date} ~ ${m.cr_end_date}</td>
										<td>${m.subject_id}</td>
										<td>${m.subject_name}</td>
										<td>${m.sj_start_date} ~ ${m.sj_end_date}</td>
										<td>${m.classroom_name}</td>
										<td>${m.book_name}</td>
										<td>${(empty test_list[status.index].test_date)?'-':test_list[status.index].test_date}</td>
										<td><a href="doDownload.it?exam=${test_list[status.index].test_paper}">${(empty test_list[status.index].test_paper)?'-':test_list[status.index].test_paper_name}</a></td>
										<td>${m.sc_attend}</td>
										<td>${m.sc_writing}</td>
										<td>${m.sc_practice}</td>
										<td><button class="btn btn-default btn-xs scorebn" title="배점 변경">배점 변경</button></td>
										<td><button class="btn btn-default btn-xs test_change" title="시험 변경">시험 변경</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- /.table-responsive -->
					</div>
						<!-- 검색 폼 -->
						<div style="text-align: center;">
							<form method="post" class="form-inline">
								<div class="form-group">
									<select class="form-control" id="skey" name="skey">
										<option value="all">전체</option>
										<option value="subject_id">과목번호</option>
										<option value="subject_name">과목명</option>
										<option value="course_name">과정명</option>
										<!-- 검색 기준 추가 -->
									</select> 
									<input type="text" class="form-control" id="svalue"
										name="svalue">
									<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
								</div>
								<button type="submit" class="btn btn-default xs seachBn_jho">
									<i class="fa fa-search"></i> Search
								</button>
								<span class="seach_info">총 <c:out value="${count}"></c:out>개의 과목정보 검색</span>
							</form>
						</div>
						<!-- stylesheet 적용->검색 폼 센터 정렬 -->

					<!-- /.panel-body -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /#page-wrapper -->
		</div>
		<!-- /#wrapper -->
	</div>
	<%-- 배점변경->등록 모달 --%>
	<div id="scoreModify" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title">배점 정보 변경</h3>
					<h4 class="modal-title"></h4>
					<ul style="list-style-type: circle">
						<li>출결 점수는 20점 이상을 입력하시기 바랍니다.</li>
						<li>출결, 필기, 실기 점수의 총합은 100점입니다.</li>
					</ul>
				</div>
				<!-- span된 버튼으로 수정 -->
				<div class="btn-group btn-group-justified"
					style="padding-bottom: 10px;">
					<a id="scoreInsert" href="#" class="btn btn-default">배점등록</a> <a
						id="scoreUpdate" href="#" class="btn btn-default">배점수정</a> <a
						id="scoreDelete" href="#" class="btn btn-default">배점삭제</a>
				</div>

				<!-- 배점등록 div  -->
				<div class="modal-body" id="scoreInsertdiv">


					<form action="ins_addScore.it" method="post" class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-2" for="subject_id1">과목번호(readonly):</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="subject_id1"
									name="subject_id1" value="" required readonly>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="attendance1">출결:</label>
							<div class="col-sm-10">
								<input type="number" min="20" max="100" class="form-control"
									id="attendance1" name="attendance1"  placeholder="20점 이상 입력하세요." required>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="exam1">필기:</label>
							<div class="col-sm-10">
								<input type="number" min="0" max="100" class="form-control"
									id="exam1" name="exam1" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="prac_exam1">실기:</label>
							<div class="col-sm-10">
								<input type="number" min="0" max="100" class="form-control"
									id="prac_exam1" name="prac_exam1" required>
							</div>
						</div>
						


						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 배점정보를 등록할까요?</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<!-- 서브밋 버튼 클릭시 hidden form의 데이터(번호)가 서버로 전송된다. -->
								<button type="button" class="btn btn-default submit">확인</button>
								<!-- 취소 버튼 클릭시 현재 모달창을 닫는다. -->
								<button type="button" class="btn btn-default close_modal"
									data-dismiss="modal">취소</button>
							</div>
						</div>
					</form>
				</div>
				<!-- 배점수정 div  -->
				<div class="modal-body" id="scoreUpdatediv">
					<form action="ins_modifyScore.it" method="post" class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-2" for="subject_id2">과목번호(readonly):</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="subject_id2"
									name="subject_id2" value="3" required readonly>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="attendance2">출결:</label>
							<div class="col-sm-10">
								<input type="number" min="20" max="100" class="form-control"
									id="attendance2" name="attendance2" required>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="exam2">필기:</label>
							<div class="col-sm-10">
								<input type="number" min="0" max="100" class="form-control"
									id="exam2" name="exam2" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="prac_exam2">실기:</label>
							<div class="col-sm-10">
								<input type="number" min="0" max="100" class="form-control"
									id="prac_exam2" name="prac_exam2" required>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 수정한 배점정보로 저장할까요?</div>
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
				<!-- 배점삭제 div  -->
				<div class="modal-body" id="scoreDeletediv">

					<form action="ins_deleteScore.it" method="post" class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-2" for="subject_id3">과목번호(readonly):</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="subject_id3"
									name="subject_id3" value="3" required readonly>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="attendance3">출결:</label>
							<div class="col-sm-10">
								<input type="number" min="20" max="100" class="form-control"
									id="attendance3" name="attendance3" required >
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="exam3">필기:</label>
							<div class="col-sm-10">
								<input type="number" min="0" max="100" class="form-control"
									id="exam3" name="exam3" required >
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="prac_exam3">실기:</label>
							<div class="col-sm-10">
								<input type="number" min="0" max="100" class="form-control"
									id="prac_exam3" name="prac_exam3" required >
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 배점정보를 삭제할까요?</div>
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
	<%-- 배점변경->등록 모달--%>

	<%-- 시험변경->등록 모달 --%>
	<div id="testModify" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title">시험 정보 변경</h3>
					<h4 class="course-title"></h4>
				</div>
				<!-- 시험등록 div  -->
				<!-- span된 버튼으로 수정 -->
				<div class="btn-group btn-group-justified">
					<a href="#" class="btn btn-default add_test">시험등록</a> <a href="#"
						class="btn btn-default modify_test">시험수정</a> <a href="#"
						class="btn btn-default delete_test">시험삭제</a>
				</div>
				<div class="modal-body" id="test_Indiv">

					<form action="ins_addTest.it" method="post" class="form-horizontal" enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label col-sm-2" for="subject_id4">과목번호(readonly):</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="subject_id4"
									name="subject_id4" value="" required readonly>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="exam_date1">시험
								날짜:</label>
							<div class="col-sm-10">
								<input type="date" class="form-control" id="exam_date1"
									name="exam_date1" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="exam_file1">시험
								문제:</label>
							<div class="col-sm-10">
								<input type="file" min="0" max="100" class="form-control"
									id="exam_file1" name="fileName" required>
			
							</div>
						</div>
						
						<!-- 강사ID -->
						<input type="hidden" value="${sessionScope.in_id}" name="instructor_id">

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 시험정보를 등록할까요?</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<!-- 서브밋 버튼 클릭시 hidden form의 데이터(번호)가 서버로 전송된다. -->
								<button type="submit" class="btn btn-default submit">확인</button>
								<!-- 취소 버튼 클릭시 현재 모달창을 닫는다. -->
								<button type="button" class="btn btn-default"
									data-dismiss="modal">취소</button>
							</div>
						</div>
					</form>
				</div>

				<!-- 시험수정 div  -->
				<div class="modal-body" id="test_Updiv">
					<form action="ins_modifyTest.it" method="post" class="form-horizontal" enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label col-sm-2" for="subject_id5">과목번호(readonly):</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="subject_id5"
									name="subject_id5" value="3" required readonly>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="exam_date2">시험
								날짜:</label>
							<div class="col-sm-10">
								<input type="date" class="form-control" id="exam_date2"
									name="exam_date2" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="exam_file2">시험
								문제:</label>
							<div class="col-sm-10">
								<input type="file" min="0" max="100" class="form-control"
									id="exam_file2" name="exam_file2" required>
										<span class="help-block original_file"></span>
							</div>
						</div>

						<input type="hidden" value="${sessionScope.in_id}" name="instructor_id">

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 수정한 시험정보로 저장할까요?</div>
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

				<!-- 시험삭제 div  -->
				<div class="modal-body" id="test_Dediv">
					<form action="ins_deleteTest.it" method="post" class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-2" for="subject_id6">과목번호(readonly):</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="subject_id6"
									name="subject_id6" value="3" required readonly>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="exam_date3">시험
								날짜:</label>
							<div class="col-sm-10">
								<input type="date" class="form-control" id="exam_date3"
									name="exam_date3" required readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="exam_file3">시험
								문제:</label>
							<div class="col-sm-10">
								<input type="text"  class="form-control"
									id="exam_file3" name="exam_file3" required readonly>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">현재 시험정보를 삭제할까요?</div>
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
				<!-- <div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div> -->
			</div>
		</div>
	</div>
	<%-- 시험변경->등록 모달--%>


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
