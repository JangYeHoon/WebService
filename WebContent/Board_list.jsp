<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Dao.*"%>
<%
   List boardList = (List)request.getAttribute("boardlist");
   int listcount = ((Integer)request.getAttribute("listcount")).intValue();
   int nowpage = ((Integer)request.getAttribute("page")).intValue();
   int maxpage = ((Integer)request.getAttribute("maxpage")).intValue();
   int startpage = ((Integer)request.getAttribute("startpage")).intValue();
   int endpage = ((Integer)request.getAttribute("endpage")).intValue();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
   a:link {text-decoration: none; color: black;}
   a:visited {text-decoration: none; color: black;}
   a:active {text-decoration: none; color: black;}
   a:hover {text-decoration: underline; color: black;}
</style>
<style>
  table.type01 {
    width: 100%;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
    padding: 10px;
  }
</style>
   <title>음악의 중심 - WaterMelon</title>
</head>
<body>
<table class = "type01" width=50% border="0" cellpadding="0" cellspacing="0">
   <tr align="center" valign="middle">
      <td colspan="4">게시판</td>
      <td align = right>
         <font size=2>글 개수 : ${listcount}</font>
      </td>
   </tr>
   
   <tr align="center" valign="middle" bordercolor="#333333"> 
        <td style="font-family:Tahoma;font-size:8pt;" width="8%" height="26"> 
            <div align="center">번호</div> 
        </td> 

        <td style="font-family:Tahoma;font-size:8pt;" width="50%"> 
            <div align="center">제목</div> 
        </td> 

        <td style="font-family:Tahoma;font-size:8pt;" width="14%"> 
            <div align="center">작성자</div> 
        </td> 

        <td style="font-family:Tahoma;font-size:8pt;" width="17%"> 
            <div align="center">날짜</div> 
        </td> 

        <td style="font-family:Tahoma;font-size:8pt;" width="11%"> 
            <div align="center">조회수</div> 
        </td> 
    </tr> 

    <% 
        for(int i=0;i<boardList.size();i++){ 
            BoardBean bl=(BoardBean)boardList.get(i); 
    %> 
    <tr align="center" valign="middle" bordercolor="#333333" 
        onmouseover="this.style.backgroundColor='F8F8F8'" 
        onmouseout="this.style.backgroundColor=''"> 
        <td height="23" style="font-family:Tahoma;font-size:10pt;"> 
            <%= bl.getBoardNum() %>
        </td> 

        <td style="font-family:Tahoma;font-size:10pt;"> 
            <div align="left"> 
                <a href="<%=request.getContextPath()%>/BoardServlet?task=BoardView&num=<%=bl.getBoardNum()%>"><%=bl.getBoardName()%> </a>
            </div>
        </td> 

        <td style="font-family:Tahoma;font-size:10pt;"> 
            <div align="center"><%=bl.getBoardWriter() %></div> 
        </td> 

        <td style="font-family:Tahoma;font-size:10pt;"> 
            <div align="center"><%=bl.getBoardDate()%></div> 
        </td>     

        <td style="font-family:Tahoma;font-size:10pt;"> 
            <div align="center"><%=bl.getBoardCount() %></div>
        </td> 
    </tr> 
    <%} %> 
    </table>
    
   
    <p align=center > 
        <font style=font-family:Tahoma;font-size:10pt;> </font>
            <%if(nowpage<=1){ %> 
            [이전]&nbsp; 
            <%}else{ %> 
            <a href="<%=request.getContextPath()%>/music?task=mainHome&page=<%=nowpage-1 %>">[이전]</a>&nbsp; 
            <%} %> 

            <%for(int a=startpage;a<=endpage;a++){ 
                if(a==nowpage){%> 
                [<%=a %>] 
                <%}else{ %> 
                <a href="<%=request.getContextPath()%>/music?task=mainHome&page=<%=a %>">[<%=a %>]</a>&nbsp; 
                <%} %> 
            <%} %> 
            <%if(nowpage>=maxpage){ %> 
            [다음] 
            <%}else{ %> 
            <a href="<%=request.getContextPath()%>/music?task=mainHome&page=<%=nowpage+1 %>">[다음]</a> 
            <%} %> 
   </p>
    <p align="right">
          <a href="Board_write.jsp">[글쓰기]</a>
    </p>
</body>
</html>