<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
	function checkValue() {
		var userinput = eval("document.userinput");
		if (!userinput.userId.value) {
			alert("아이디를 입력하세요.");
			return false;
		}

		if (!userinput.userPW.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
	}

	function openConfirmid(userinput) {
		if (userinput.userId.value == "") {
			alert("아이디를 입력하세요");
		}

		url = "confirmId.jsp?id=" + userinput.userId.value;

		open(url, "confirm", "toolbar = no, location = no, status = no,"
				+ "menubar = no, scrollbars = no, resizable = no,"
				+ "width = 300, height = 200");
	}
</script>
<style type='text/css'>
@import url("join.css");

#btn {
	width: 200px;
	margin: auto;
	padding: 5px;
}
</style>
<link rel="stylesheet" href="join.css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>음악의 중심 - WaterMelon</title>
</head>
<body>
	<form action="UserServlet" method="post" name="userinput"
		onsubmit="return checkValue()">
		<fieldset>
			<legend>개인정보 입력</legend>
			<ol>
				<li><label for="userID">아이디</label> <input type="text"
					name="userId"></li>
				<li><input type="button" name="confirm_id" value="중복확인"
					onclick="openConfirmid(this.form)"></li>
				<li><label for="userPW">패스워드</label> <input type="password"
					name="userPW"></li>
				<li><label for="userName">이름</label> <input type="text"
					name="userName"></li>
				<li><label for="phone">전화번호</label> <input type="text"
					name="userPhone"></li>
			</ol>
		</fieldset>
		<button type="submit" id="btn">회원가입</button>
	</form>
	<br>
	<br>
	<br>
	<br>
	<a href="javascript:history.go(-1)"><input type=button value="뒤로"></a>
</body>
</html>