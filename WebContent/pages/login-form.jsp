<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SIST 성적 관리 홈페이지 - Login</title>

<!-- Bootstrap Core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- jQuery -->
	<script src="../vendor/jquery/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		/* var testid = ${user_id};
		
		console.log(testid); */
		
		$(".btn-block").on("click", function() {
		var id = $("#login_id").val();
		var pw = $("#login_pw").val();
		
			if (!id || !pw) {
				alert("아이디,비밀번호를 입력해주세요.");
				$(this).parents("form").attr("action", "loginform.it");
			}
		});
	});
</script>
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-body">
						<img src="/grade_Management_System_0120/img/logo_login.png"
							style="width: 100%;">
					</div>
					<div class="panel-body">
						<form action="login.it" method="post">
							<div class="form-group">
								<input class="form-control" placeholder="id를 입력하세요" name="id"
									type="text" id="login_id"autofocus>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="Password를 입력하세요"
									name="pw" type="password" id="login_pw">
							</div>
							<div class="radio">
								<label class="label-inline"> <input type="radio"
									name="reg_gender" value="0" checked="checked">학생
								</label> <label class="label-inline"> <input type="radio"
									name="reg_gender" value="1">강사
								</label> <label class="label-inline"> <input type="radio"
									name="reg_gender" value="2">관리자
								</label>

							</div>
							<!-- Change this to a button or input when using this as a form -->
							<button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	

	<!-- Bootstrap Core JavaScript -->
	<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="../vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>
