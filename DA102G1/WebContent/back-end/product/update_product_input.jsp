<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.equipment.model.*"%>

<%
  EquipmentVO equVO = (EquipmentVO) request.getAttribute("equVO");
%>


<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>Background</title>
	<meta charset="utf-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/backgroundContext.css">
	<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/body.css">

<style type="text/css">
		  /*本地字體引入*/
  @font-face{
    font-family: Russo;
    src:url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
     unicode-range: U+00-024F;
  }
  @font-face{
    font-family: Noto;
    src:url('/DA102G1/assets/fonts/NotoSansTC-Medium.otf');
    unicode-range: U+4E00-9FFF;
  }

</style>



</head>
<body>
<div class="container-fluid">
	<div class="row">
	
		<jsp:include page="/common/adminNavbar.jsp" flush="true"/>

		<div class="col-lg-12 contentbox" >
		  <img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">
		
		  
			<jsp:include page="/common/adminSideBar.jsp" flush="true"/>
			
		  
			<div class="col-lg-10 col-md-10 cb rightContent">

<table id="table-1">
	<tr><td>
		 <h3>商品資料修改 </h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="equipment.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>裝備編號:<font color=red><b>*</b></font></td>
		<td><%=equVO.getEq_num()%></td>
		<input type="hidden" name="eq_num" value="${equVO.eq_num }">
	</tr>
	<tr>
		<td>裝備名稱:</td>
		<td><input type="TEXT" name="eq_name" size="45"  value="<%=equVO.getEq_name()%>" /></td>
	</tr>
	<tr>
		<td>裝備類別:</td>
		<td><input type="TEXT" name="eq_type" size="45" value="<%=equVO.getEq_type()%>" /></td>
	</tr>
	<tr>
		<td>裝備尺寸:</td>
		<td><input type="TEXT" name="eq_size" size="45" value="<%=equVO.getEq_size()%>" /></td>
	</tr>
	<tr>
		<td>裝備品牌:</td>
		<td><input type="TEXT" name="eq_brand" size="45" value="<%=equVO.getEq_brand()%>" /></td>
	</tr>
	<tr>
		<td>裝備價格:</td>
		<td><input type="TEXT" name="eq_price" size="45" value="<%=equVO.getEq_price()%>" /></td>
	</tr>
		<tr>
		<td>裝備狀態:</td>
		<td><input type="TEXT" name="eq_status" size="45" value="<%=equVO.getEq_status()%>" /></td>
	</tr>
	<tr>
		<td>裝備圖片:</td>
		<td><input type="file" name="eq_pic" size="45" value="<%=equVO.getEq_pic()%>" /></td>
	</tr>
	<tr>
		<td>裝備細節:</td>
		<td><input type="TEXT" name="eq_detail" size="45" value="<%=equVO.getEq_detail()%>" /></td>
	</tr>
		<tr>
		<td>裝備種類編號:</td>
		<td><input type="TEXT" name="type_eq_num" size="45" value="<%=equVO.getType_eq_num()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="eq_num"  value="<%=equVO.getEq_num()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> 
<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  
<input type="submit" value="送出修改"></FORM>




		  </div>
		
		</div>






	<jsp:include page="/common/footer.jsp" flush="true"/>
	</div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>