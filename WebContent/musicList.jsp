<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="Dao.*"%>
<%
   List musicPlayList = (List) request.getAttribute("musicPlayList");
   String musicPath = (String) request.getAttribute("musicPath");
   List<String> musicPathList = (List<String>) request.getAttribute("musicPathList");
   MusicBean bean = (MusicBean) request.getAttribute("recommendMusic");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type='text/css'>
@import url("musicList.css");
p {
padding :0px 0px 0px 50px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ �߽� - WaterMelon</title>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
   var song = new Array();
   var idx = 1;
   function bgMusic() {
      var mplayer = document.getElementById("mplayer")
      if (idx < song.length) {
         mplayer.FileName = song[idx];
         setTimeout('mplayer.play()', 1000);
         idx++;
         return false;
      } else {
         idx = 0;
         bgMusic();
      }
   }
   function bfMusic() {
      var mplayer = document.getElementById("mplayer")
      if (idx > 0) {
         idx = idx - 2;
         bgMusic();
      } else if (idx == 0) {
         idx = song.length - 1;
         bgMusic();
      }
   }
   function reMusic() {

   }
   window.onload = function() {
      var cnt = $('.count').val();

      for (c = 0; c < cnt; c++) {
         song[c] = $('.musicPathList' + c).val();
         song[c] = "G:/Music/" + song[c] + ".mp3";
      }
   }
   function goPage() {
      location.href = "music?task=mainHome";
   }
</script>
<script for="mplayer" event="EndOfStream()" type="text/javascript">
   bgMusic();
</script>
</head>
<body>
<center>
   <input class="count" type="hidden" value="<%=musicPathList.size()%>">
   <%
      for (int j = 0; j < musicPathList.size(); j++) {
   %>
   <input class="musicPathList<%=j%>" type="hidden"
      value="<%=musicPathList.get(j)%>">
   <%
      }
   %>
   <!-- ���� ��� -->
   <object id="mplayer" width="300" height="55"
      classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95"
      codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=6,0">
      <param name="AutoStart" value="true">
      <param name="ShowControls" value="true">
      <param name="ShowStatusBar" value="true">
      <param name="TransparentAtStart" value="true">
      <param name="AnimationAtStart" value="false">
      <param name="FileName" value="G:/Music/<%=musicPath%>.mp3">
      <param name="ShowDisplay" value="false">
      <param name="ShowPositionControls" value="false">
      <param name="ShowStatusBar" value="true">
      <param name="ShowTracker" value="true">
   </object>
   <br><br>
   <button onclick="bfMusic()">
      <img src="images\btn_ejun.png" width="20" height="20" border="0">
   </button>
  <button onclick="bgMusic()">
      <img src="images\btn_daum.png" width="20" height="20" border="0">
   </button>
   
   <br><br><br><br>
   <%
      if (bean.getMusicName() != null) {
   %>
   ��õ����
   <%=bean.getMusicName()%>-<%=bean.getMusicArtist()%>�Դϴ�.
   <a
      href="<%=request.getContextPath()%>/music?task=playMusic&title=<%=bean.getMusicNum()%>"><img
      src="images\btn_play.png" width="12" height="11" border="0"></a>
   <%
      }
   %>
   </center>
   <br>
   <!-- ���� ����Ʈ �ѷ��ִ� �κ� -->
   <table class="tg">
      <tr>
         <th class="tg-qi6i">��Ƽ��Ʈ</th>
         <th class="tg-qi6i">����</th>
         <th class="tg-qi6i">����</th>
      </tr>
      <%
         for (int i = 0; i < musicPlayList.size(); i++) {
            MusicBean ml = (MusicBean) musicPlayList.get(i);
      %>
      <tr>
         <td class="tg-2oxn"><%=ml.getMusicArtist()%></td>
         <td class="tg-2oxn"><%=ml.getMusicName()%></td>
         <td class="tg-2oxn" align="center"><a
            href="<%=request.getContextPath()%>/music?task=deleteMusic&title=<%=ml.getMusicNum()%>"><img
               src="images\btn_delete.png" width="12" height="11" border="0"></a></td>
      </tr>
      <%
         }
      %>
   </table>
	<br><br>
  <a href="javascript:goPage()"><center><button>�ڷΰ���</button></center></a>
</html>