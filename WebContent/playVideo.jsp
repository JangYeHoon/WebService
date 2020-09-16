<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ page import="Dao.*"%>
<%
   MusicBean videoPlay = (MusicBean)request.getAttribute("videoPlay");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothiccoding.css);
p { font-size: 24px; }
.ngc { font-family: 'Nanum Gothic Coding'; }

body {
   text-align:left;
}
#left {
   width: 100px;
   height: 300px;
   background-color: white;
   float: left;
}

#contents {
   width: 860px;
   height: 490px;
   border-top: 2px;
   border-bottom: 2px;
   border-left: 2px;
   border-right: 2px;
   padding: 15px 15px 15px 15px;
   float: left;
}
</style>
<script>
function goPage() {
    location.href = "music?task=mainHome#fragment-2";
 }
function goPage1() {
    location.href = "music?task=mainHome#fragment-1";
 }
</script>
<title>음악의 중심 - WaterMelon</title>

</head>
<body>
   <!-- 뮤직비디오 보여주는 부분 -->
   <br>
   <div id="left"></div>
   <div id="contents">
      <hr size="3" color=#000000>
      <iframe width="854" height="480" align="middle"
         src="<%= videoPlay.getMusicVideoPath() %>" frameborder="0"
         allowfullscreen></iframe>
      <hr size="3" color=#000000>
   <p class="ngc"> <%= videoPlay.getMusicArtist() %> - <%= videoPlay.getMusicName() %></p>
   <center><a href="javascript:goPage1()"><input type=button value="음악차트로가기"></a>&nbsp;&nbsp;
   <a href="javascript:goPage()"><input type=button value="뮤직비디오로가기"></a></center>
   </div>
</body>
</html>