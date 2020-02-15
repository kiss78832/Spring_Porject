<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.viewpoint.model.*"%>
<%
	SpotService spotSvc = new SpotService();
	List<SpotVO> list = spotSvc.getspot("1");
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="meminfoSvc" class="com.spot.model.SpotService"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>共用頁面</title>

<style>

 table.form-table tr{
/*  	color:#808080;  */
 	border: 2px solid black;
	font-size: 20px;
/* 	border-collapse: separate; */
	text-align:left;
	letter-spacing: 3px;
}
div.from-groupspot{
    border: 1px solid #e1e1e8;
    border-radius: 4px;
}
  
</style>
		
	
</head>
<body>

	<div class="from-groupspot">
          <span>請選擇路線:</span>
          <span ib="spotlist"></span>
		<div class="from-group">
			<table class="form-table">
				
					<c:forEach var="spotVO" items="${list}" >
 						
						<tr>${spotVO.spot_name}</tr>
 						
					</c:forEach>
					
			</table>
		</div>
	</div>
</body>
</html>