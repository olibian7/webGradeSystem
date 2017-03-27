<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");

	/* String course_id = request.getParameter("course_id"); */
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

				
				var sid;
				var subject_name;
				var coursedate;
				var name;
				var gradecheck;
				var test;
				var room;
				var book;
				var title;
				
				//상세보기에 대한 모달창 액션
				$("button.choose").on(
						"click",
						function() {	
							//검색버튼 초기화
							$("#skey1").val("all").attr("selected", "selected");
							$("#svalue1").val("");
							
							sid = $(this).parents("tr").children().eq(0).text();
							subject_name = $(this).parents("tr").children().eq(1).text();
							coursedate =$(this).parents("tr").children().eq(2).text();
							name =$(this).parents("tr").children().eq(3).text();
							gradecheck=$(this).parents("tr").children().eq(4).text();
							test=$(this).parents("tr").children().eq(5).text();
							room=$(this).parents("tr").children().eq(6).text();
							book=$(this).parents("tr").children().eq(7).text();
							title=$("#cs_name").text() +" "+$("#cs_date").text();
							
							$("#sid").text(sid);
							$("#subject_name").text(subject_name);
							$("#coursedate").text(coursedate);
							$("#name").text(name);
							$("#gradecheck").text(gradecheck);
							$("#test").text(test);
							$("#room").text(room);
							$("#book").text(book);
							$("#title").text(title);						
							
							var subject_id = $("#sid").text();
							
							$.post("admin_grade_course_detail_ajax.it", {subject_id:subject_id}, function(data){
								
								console.log(data);

								var myObj = JSON.parse(data);

								var obj_length = myObj.student.length;
								
								console.log(obj_length);
								
								var result = "";

								for (var i = 0; i < obj_length; i++) {
									/* result += "<option>"
											+ myObj.classroom[i].classroom_name
											+ "</option>"; */
											
									var s =	myObj.student[i]
									console.log(s.st_id);
											
									result += "<tr>"
									result += "<td>"+s.st_id+"</td>";
									result += "<td>"+s.name+"</td>";
									result += "<td>"+s.phone+"</td>";
									result += "<td>"+s.gr_attend+"</td>";
									result += "<td>"+s.gr_writing+"</td>";
									result += "<td>"+s.gr_practice+"</td>";
									result += "</tr>"
									totalCount = s.totalCount;
								}
								
								console.log("-------------------");
								console.log(result);
								if ($.trim(data) != "") {
								$("#stList > tbody").html(result);
								$("#studentcount").text(totalCount);
								}
							});
							
							$("#admin_add_course_modal").modal();
							
						});
	
	
				//과정해당 수강생정보보기 모달 창 안의 검색버튼에 대한 액션 
				$("button.modalsearch").on("click", function() {
					
					$("#sid").text(sid);
					$("#subject_name").text(subject_name);
					$("#coursedate").text(coursedate);
					$("#name").text(name);
					$("#gradecheck").text(gradecheck);
					$("#test").text(test);
					$("#room").text(room);
					$("#book").text(book);
					$("#title").text(title);					
					
					var subject_id = $("#sid").text();

					var skey = $("#skey1").val();
					var svalue = $("#svalue1").val();		
					
					$.post("admin_grade_course_detail_ajax.it", {subject_id:subject_id,skey:skey,svalue : svalue}, function(data){
						
						console.log(data);

						var myObj = JSON.parse(data);

						var obj_length = myObj.student.length;
						
						console.log(obj_length);
						
						var result = "";
						var totalCount = ""; 
						
						for (var i = 0; i < obj_length; i++) {
							/* result += "<option>"
									+ myObj.classroom[i].classroom_name
									+ "</option>"; */
									
							var s =	myObj.student[i]
							console.log(s.totalCount);
									
							result += "<tr>"
							result += "<td>"+s.st_id+"</td>";
							result += "<td>"+s.name+"</td>";
							result += "<td>"+s.phone+"</td>";
							result += "<td>"+s.gr_attend+"</td>";
							result += "<td>"+s.gr_writing+"</td>";
							result += "<td>"+s.gr_practice+"</td>";
							result += "</tr>"
							totalCount = s.totalCount;
						}
						
						if ($.trim(data) != "") {
							$("#stList > tbody").html(result);
							$("#studentcount").text(totalCount);
						}
						
						
						//검색 진행후 검색 키워드가 화면에 그대로 남아있도록 액션 추가
						//-> 검색 폼 부분에 기존 검색 키워드를 재설정
						$("#skey1").on("change", function() {
							$("#svalue1").val("");
						});

						//JSON으로 가져온 skey, svalue
						var skey1 = myObj.keyvalue[0].skey;
						var svalue1 = myObj.keyvalue[0].svalue;

						$("#skey1").val(skey1).attr("selected", "selected");
						$("#svalue1").val(svalue1);
							
					});
					
					$("#admin_add_course_modal").modal();
					
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

/* 사용자 style 설정 영역(박새미) */
/* 강의가능과목 늘어날 시 스크롤바 생성 */
.list {
	max-height: 350px;
	overflow: auto;
	margin-bottom: 20px;
}
.list2 {
	max-height: 300px;
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
	right: 25px;
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
					<h4 style="padding-top: 10px">성적 조회 > 과목기준조회</h4>
					<hr>
					<!-- /.panel-heading -->
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>과정ID</th>
									<th>과정명</th>
									<th>과정기간</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cs" items="${cs_list}">
									<tr>
										<td id="course_id">${cs.course_id}</td>
										<td id="cs_name">${cs.course_name}</td>
										<td id="cs_date">${cs.cr_start_date} ~ ${cs.cr_end_date}</td>
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
						<div class="panel-heading"><h4 style="font-weight:bold">과목조회</h4></div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive list">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>과목ID</th>
											<th>과목명</th>
											<th>과목기간</th>
											<th>강사명</th>
											<th>성적등록여부</th>
											<th>시험문제</th>
											<th>강의실명</th>
											<th>교재명</th>
											<th style="width: 20px">성적</th>
										</tr>
									</thead>
									<tbody>
										<!-- <tr>
											<td>sj001</td>
											<td>자바</td>
											<td>2016-11-01 ~ 2017-03-31</td>
											<td>이흔호</td>
											<td>O</td>
											<td><a href="#">test.zip</a></td>
											<td>101호</td>
											<td>자바ABCD</td>
											<td><button class="btn btn-default btn-xs choose">보기</button></td>
										</tr>
										<tr>
											<td>sj002</td>
											<td>오라클</td>
											<td>2016-12-01 ~ 2017-04-31</td>
											<td>이민종</td>
											<td>O</td>
											<td><a href="#">test.zip</a></td>
											<td>104호</td>
											<td>오라클ABCD</td>
											<td><button class="btn btn-default btn-xs choose">보기</button></td>
										</tr> -->
										<c:forEach var = "i" begin="1" end="${sj_list_size}">
										<c:set var ="sj" value="${sj_list}" />
										<c:set var ="g" value="${gradeCheck}" />
										<tr>
											<td>${sj[i-1].subject_id}</td>
											<td>${sj[i-1].subject_name}</td>
											<td>${sj[i-1].sj_start_date} ~ ${sj[i-1].sj_end_date}</td>
											<td>${sj[i-1].in_name}</td>
											<td>${g[i-1]}</td>
											<td><a href="doDownload.it?exam=${sj[i-1].test_paper}">${empty sj[i-1].test_paper_name?"-":sj[i-1].test_paper_name}</a></td>
											<td>${sj[i-1].classroom_name}</td>
											<td><a href="#" class="bookImage">${sj[i-1].book_name}</a>
											<input type="hidden" value="${sj[i-1].book_img}"></td>
											<td><button class="btn btn-default btn-xs choose">보기</button></td>
										</tr>
										</c:forEach>
									</tbody>
								</table>


							</div>	<!-- /.table-responsive -->
								<!-- 검색 폼 -->
								<div style="text-align: center;">
									<form class="form-inline" method="post">
										<div class="form-group">
											<select class="form-control" id="skey" name="skey">
												<option value="all">전체</option>
												<option value="subject_id">과목ID</option>
												<option value="subject_name">과목명</option>
												<!-- 검색 기준 추가 -->
											</select> <input type="text" class="form-control" id="svalue"
												name="svalue">
												<input type="hidden" class="form-control" name="course_id" value="${course_id}">
											<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
										</div>
										<button type="submit" class="btn btn-default xs seachBn_jho">
											<i class="fa fa-search"></i> Search
										</button>
										<span class="seach_info">총 ${totalCount}개의 과목정보 검색</span>
									</form>
								</div>
						
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

	<!-- 상세정보 -->
	<div id="admin_add_course_modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="title">홍길동(st001)</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<tr>
							<th>과정ID</th>
							<th>과정명</th>
							<th>과목기간</th>
							<th>강사명</th>
							<th>성적등록여부</th>
							<th>시험문제</th>
							<th>강의실명</th>
							<th>교재명</th>
						</tr>
						<tr>
							<td id="sid">sj001</td>
							<td id="subject_name">자바</td>
							<td id="coursedate">2016-11-01~2017-02-28</td>
							<td id="name">이흔호</td>
							<td id="gradecheck">O</td>
							<td id="test"></td>
							<td id="room">101호</td>
							<td id="book">자바ABCD</td>
						</tr>
					</table>
					<hr>
					<p>
						<b>학생조회</b>
					</p>
					<div class="list2">
						<table class="table table-striped" id="stList">
						<thead>
							<tr>
								<th>수강생ID</th>
								<th>이름</th>
								<th>전화번호</th>
								<th>출결</th>
								<th>필기</th>
								<th>실기</th>
							</tr>
							</thead>
							<tbody>
							<!-- <tr>
								<td>st001</td>
								<td>박길동</td>
								<td>010-1234-1234</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
							</tr>
							<tr>
								<td>st002</td>
								<td>김길동</td>
								<td>010-4234-1234</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
							</tr> -->
							</tbody>
						</table>
					</div>

					<!-- 검색 폼 -->
					<div style="text-align: center;">
						<form class="form-inline">
							<div class="form-group">
								<select class="form-control" id="skey1" name="skey">
									<option value="all">전체</option>
									<option value="student_id">수강생ID</option>
									<option value="name">이름</option>
									<option value="phone">전화번호</option>
									<!-- 검색 기준 추가 -->
								</select> <input type="text" class="form-control" id="svalue1"
									name="svalue">
								<!-- svalue 랑 svalue매칭 시키는 액션 필요 -->
							</div>
							<button type="button" class="btn btn-default xs modalsearch"
								style="margin-right: 166px">
								<i class="fa fa-search"></i> Search
							</button>
							<span class="seach_info">총 <span id="studentcount"></span>명의 학생정보 검색</span>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">확인</button>
				</div>
			</div>

		</div>
	</div>
	<!-- /#wrapper -->
	
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
