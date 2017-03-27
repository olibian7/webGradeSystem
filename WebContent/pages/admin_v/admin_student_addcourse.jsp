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
   $(document)
         .ready(
               function() {
            	   //과정등록 신청 버튼 클릭시 모달 액션
                  $("button.r​egister_course").on("click", function() {   
                     
                	 $("#register_title").text($(".student_table>tbody>tr").children().eq(0).text()+$(".student_table>tbody>tr").children().eq(1).text());
                     $("#course_id").val($(this).val());
                     $("#course_name").text($(this).parents("tr").children().eq(1).text());
                     $("#student_id").val("${list.get(0).student_id}");
                     
                     $("#admin_student_register_course").modal();
                     
                  });
                  
                	//과정등록 취소 버튼 클릭시 모달 액션
                  $("button.cancel_course").on("click", function() {   
                      
                	  $("#cancel_title").text($(".student_table>tbody>tr").children().eq(0).text()+$(".student_table>tbody>tr").children().eq(1).text());
                      $("#cancel_course_id").val($(this).val());
                      $("#cancel_course_name").text($(this).parents("tr").children().eq(1).text());
                      $("#cancel_student_id").val("${list.get(0).student_id}");
                      
                      $("#admin_student_cancel_course").modal();
                      
                   });
                  
                  $("button.info").on("click", function() {
                	  
                                 $("#title").text($(this).parents("tr").children().eq(1).text() + "(" + $(this).parents("tr").children().eq(0).text() + ")");
                                 
//                                  var keyword = $("#title").text();

								 var keyword = $(this).val();

                                 $.post("admin_student_json.it", {keyword : keyword}, function(data) {
                                	 
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

                                                   $("#tocount").html(obj_length);

                                                   $("table.student_info_data > tbody").html(result);

                                                }

                                             });

                                 $("#admin_student_info_modal").modal();
                              });

                  //신청, 취소 버튼 액션 추가
                  //console.log($("button.r​egister_course").length);
                  var len = $("button.r​egister_course").length;
                  for (var a=0; a<len; ++a) {
                	  var btn = $("button.r​egister_course").eq(a);
                	  if ($(btn).attr("disabled") == undefined) {
                    	  //console.log($(btn).attr("disabled"));
                    	  
                    	  //부모 td->형제 td->자식 button 객체의 disabled 상태 지정
                    	  $(btn).parents("td").next().find("button.cancel_course").attr("disabled","disabled");
                  
                	  }
                  }
                  
                  $("button.no_button").on("click", function(){
                	  $("#admin_student_register_course").modal("hide");
                  });
                  
                  $("button.no_button_cancle").on("click", function(){
                	  $("#admin_student_cancel_course").modal("hide");
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

/* 스크롤 생성 */
.list {
   max-height: 430px;
   overflow: auto;
}

.list_modal {
   max-height: 400px;
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
               <h4 style="padding-top: 10px">학생 관리 > 전체검색 > <span>${list.get(0).st_name}</span>(<span>${list.get(0).student_id}</span>) > 과정등록</h4>
               <hr>
               <div class="table-responsive">
                  <div>
                     <table class="table table-striped table-bordered table-hover student_table">
                        <thead>


                           <tr>
                              <th>수강생ID</th>
                              <th>이름</th>
                              <th>전화번호</th>
                              <th>등록일</th>
                              <th>수강(신청)횟수</th>
                              <th style="width: 40px">상세정보</th>
                           </tr>
                        </thead>
                        <tbody>
                           <%--
                           <tr>
                              <td>st001</td>
                              <td>홍길동</td>
                              <td>1234567</td>
                              <td>010-1234-1234</td>
                              <td>2017-01-01</td>
                              <td>-</td>
                              <td><button type="button"
                                    class="btn btn-default btn-xs info">상세정보</button></td>
                           </tr>
                        </tbody>
                        --%>
                           <c:forEach var="a" items="${list}">
                              <tr>
                                 <td>${a.student_id}</td>
                                 <td>${a.st_name}</td>
                                 <td>${a.st_phone}</td>
                                 <td>${a.st_registration_date}</td>
                                 <td>${a.countRequest}</td>
                                 <td><button type="button"
                                       class="btn btn-default btn-xs info" value="${a.student_id}">상세정보</button></td>
                              </tr>
                           </c:forEach>
                     </table>
                  </div>
                  <div class="list">
                     <table class="table table-striped table-bordered table-hover">
                        <thead>
                           <tr>
                              <th>과정ID</th>
                              <th>과정명</th>
                              <th>과정기간</th>
                              <th>강의실명</th>
                              <th>인원/정원</th>
                              <th>개설과목수</th>
                              <th>과정등록</th>
                              <th>과정등록취소</th>
                           </tr>
                        </thead>
                        <tbody>


                           <%-- JSTL, EL을 이용한 동적 데이터 출력 --%>
                           <%--                            <c:forEach var="ad" items="${list}"> --%>
                           <!--                               <tr> -->
                           <%--                                  <td>${ad.course_id}</td> --%>
                           <%--                                  <td>${ad.course_name}</td> --%>
                           <%--                                  <td>${ad.cr_start_date}${ad.cr_end_date}</td> --%>
                           <%--                                  <td>${ad.classroom_name}</td> --%>
                           <%--                                  <td>${ad.countStu}/${ad.max_num}</td> --%>
                           <%--                                  <td>${ad.countSub}</td> --%>
                           <!--                                  <td><button type="button" -->
                           <!--                                        class="btn btn-default btn-xs choose">신청</button></td> -->
                           <!--                               </tr> -->
                           <%--                            </c:forEach> --%>

                           <c:forEach var="b" items="${total_list}">
								<tr>
									<td>${b.course_id}</td>
									<td>${b.course_name}</td>
									<td><span>${b.cr_start_date}</span>~<span>${b.cr_end_date}</span></td>
									<td>${b.classroom_name}</td>
									<td><span>${b.countStu}</span>/<span>${b.max_num}</span>
									<td>${b.countSub}</td>
									
									
									
									<td><button type="button" class="btn btn-default btn-xs r​egister_course" value="${b.course_id}" <c:forEach var="a" items="${check_list}"> ${a.course_id==b.course_id?"disabled=\"disabled\"":""}</c:forEach>>신청</button></td>
									<td><button type="button" class="btn btn-default btn-xs cancel_course" value="${b.course_id}" >취소</button></td>
									
											
								</tr>
								
							</c:forEach>

                           <!--    <tr>
                              <td>cn003</td>
                              <td>[100%국비지원]자바스크립트 전문가 과정</td>
                              <td>2017-01-01~2017-05-31</td>
                              <td>102호</td>
                              <td>13/20</td>
                              <td>0</td>
                              <td><button type="button"
                                    class="btn btn-default btn-xs choose">신청</button></td>
                           </tr>  -->
                        </tbody>
                     </table>

                  </div>
                  <p align="right">
                     <span>${totalcount}</span>개의 과정 검색
                  </p>
               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- Modal 영역 시작!!!! -->
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
               <div class="list_modal">
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
                  </table>
               </div>
            </div>
            <div class="modal-footer">
               <button class="btn btn-default" data-dismiss="modal">확인</button>
            </div>
         </div>

      </div>
   </div>

   <!-- 과정등록 -->
   <div id="admin_student_register_course" class="modal fade" role="dialog">
      <div class="modal-dialog modal-lg">

         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <h4 class="modal-title" id="register_title">홍길동(st001)</h4>
            </div>
            <div class="modal-body">
               <div align="center">
                  <h4>"<span id="course_name"></span>" 을 등록하시겠습니까?</h4>
               </div>
               <div class="list_modal">
                  
                  <div align="center">
                     <form action="ad_st_add_studentCourse.it" method="post" class="form-inline">
                        <input type="hidden" id="course_id" name="course_id">
                        <input type="hidden" id="student_id" name="student_id">
                         <div class="form-group" style="width:45%">                     
                           <button type="submit" class="btn btn-default yes_button" style="width: 100%;">Yes</button>
                        </div>
                        <div class="form-group" style="width:45%">
                           <button type="button" class="btn btn-default no_button" style="width: 100%;">No</button>
                        </div>
             
                     </form>
                  </div>
               </div>
            </div>
            <div class="modal-footer">
               <button class="btn btn-default" data-dismiss="modal">확인</button>
            </div>
         </div>

      </div>
   </div>

   <!-- 과정취소 -->
   <div id="admin_student_cancel_course" class="modal fade" role="dialog">
      <div class="modal-dialog modal-lg">

         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <h4 class="modal-title" id="cancel_title">홍길동(st001)</h4>
            </div>
            <div class="modal-body">
               <div align="center">
                  <h4>"<span id="cancel_course_name"></span>" 을 등록취소하시겠습니까?</h4>
               </div>
               <div class="list_modal">
                  
                  <div align="center">
                     <form action="ad_st_delete_studentCourse.it" method="post" class="form-inline">
                        <input type="hidden" id="cancel_course_id" name="course_id">
                        <input type="hidden" id="cancel_student_id" name="student_id">
                        <div class="form-group" style="width:45%">                     
                           <button type="submit" class="btn btn-default yes_button" style="width: 100%;">Yes</button>
                        </div>
                        <div class="form-group" style="width:45%">
                           <button type="button" class="btn btn-default no_button_cancle" style="width: 100%;">No</button>
                        </div>
                     </form>
                  </div>
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