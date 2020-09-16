<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Dao.*"%>
<%
	List musicList = (List)request.getAttribute("musicRank");
	String userId = (String)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<script src="jquery-1.1.3.1.pack.js" type="text/javascript"></script>
<script src="jquery.history_remote.pack.js" type="text/javascript"></script>
<script src="jquery.tabs.pack.js" type="text/javascript"></script>
<link rel="stylesheet" href="jquery.tabs.css" type="text/css"
	media="print, projection, screen">
<script language='javascript'>
	function logout()
	{
		location.href="UserServlet?task=logout";
	}
	function goPlayList()
	{
		location.href="music?task=goplayList";
	}
	</script>
<style type="text/css" media="screen, projection">
body {
	font-size: 16px; /* @ EOMB */
}

* html body {
	font-size: 100%; /* @ IE */
}

body * {
	font-size: 87.5%;
	font-family: "Trebuchet MS", Trebuchet, Verdana, Helvetica, Arial,
		sans-serif;
}

body * * {
	font-size: 100%;
}

h1 {
	margin: 1em 0 1.5em;
	font-size: 18px;
}

h2 {
	margin: 2em 0 1.5em;
	font-size: 16px;
}

p {
	margin: 0;
}

pre, pre+p, p+p {
	margin: 1em 0 0;
}

code {
	font-family: "Courier New", Courier, monospace;
}
</style>
<style type='text/css'>
@import url("login.css");
</style>
<style>
#ulstyle{
	width: 350px;
	margin: 0 auto;
}
</style>
<link rel="stylesheet" href="login.css">

<title>음악의 중심 - WaterMelon</title>
</head>
<body>
<br><br>
	<!-- 로고  -->
	<h2 style="text-align:center"><img width="150" height="90" src="images/logo.png" />WaterMelon</h2>
	<h3 style="text-align:right"><%= userId %>님 환영합니다.</h3>
	<span style="float:right"><input type=button value="로그아웃" OnClick="javascript:logout();"></span>
	<span style="float:right"><input type=button value="My Playlist" OnClick="javascript:goPlayList();"></span>
	<br><br>
	<hr size="3" color="#00FF00" />
	<br><br>
	<div id="container-5" >
		<ul id="ulstyle">
			<li><a href="#fragment-1"><span>음악 차트</span></a></li>
			<li><a href="#fragment-2"><span>뮤직비디오</span></a></li>
			<li><a href="#fragment-3"><span>게시판</span></a></li>
			<li><a href="#fragment-4"><span>고객센터</span></a></li>
		</ul>

		<!-- 음악 탭 -->
		<div id="fragment-1">
			<jsp:include page = "musicRank.jsp"/>
		</div>

		<!-- 뮤직비디오 탭 -->
		<div id="fragment-2">
			<jsp:include page = "musicVideo.jsp" flush = "true"/>
		</div>

		<!-- 게시판 탭 -->
		<div id="fragment-3">
			<jsp:include page = "Board_list.jsp" flush = "true"/>
		</div>

		<!-- 고객센터 탭 -->
		<div id="fragment-4">
			<jsp:include page = "serviceCenter.jsp" flush = "true"/>
		</div>

	</div>
	<script type="text/javascript">
      $(function() {
         $('#container-5').tabs({
            fxSlide : true,
            fxFade : true,
            fxSpeed : 'normal'
         });
      });
   </script>
</body>
</html>