<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.*" %>
<%@ page import = "Dao.*" %>
<%
   List videoList = (List)request.getAttribute("videoRecommend");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
font {
   font-size: 100%;
   font: inherit;
}
#left {
   width: 300px;
   height: 800px;
   background-color: white;
   float: left;
}

#contents {
   width: 840px;
   height: 550px;
   background-color: white;
   border-top:2px solid gold;
   border-bottom:2px solid gold;
   border-left:2px solid gold;
   border-right:2px solid gold;
   padding :40px 0px 5px 90px;
   margin: 0 auto;
}
div.gallery {
    margin: auto;
    border: 1px solid #ccc;
    float: left;
    width: 190px;
}

div.gallery:hover {
    border: 1px solid #777;
}

div.gallery img {
    width: 100%;
    height: auto;
}

div.desc {
    padding: 15px;
    text-align: center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>음악의 중심 - WaterMelon</title>
</head>
<body>
   
   <!-- 뮤직비디오 추천 뿌려주는 부분 -->
   <H3 style="text-align:center">오늘의 추천 뮤직비디오</H3>
   <center><input type="button" value="다른 뮤직비디오 보기" onClick="window.location.reload()"></center>
   <br><br><br><br>
   <div id="left"></div>
   <div id="contents">
   <%
   Random random = new Random();
      for ( int i = 0; i < videoList.size(); i++ ) {
         MusicBean ml = (MusicBean)videoList.get(i);
         int jpg = random.nextInt(4);
   %>
   <% if(i == 4)  { %>
   <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
   <% } %>
   <div class="gallery">
        <a target="_blank" href="<%=request.getContextPath()%>/music?task=VideoPlay&title=<%=ml.getMusicNum() %>">
          <img src="http://img.youtube.com/vi/<%=ml.getMusicVideoPath()%>/<%=jpg%>.jpg" alt="Fjords" width="300" height="200">
        </a>
        <div class="desc"><%= ml.getMusicArtist() %> <br><%= ml.getMusicName()%> </div>
   </div>
   <% } %>
   </div>
</body>
</html>