<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spring.equipment.model.*"%>


<%
	EquipmentService equSvc = new EquipmentService();
	List<EquipmentVO> list = equSvc.getAll();
	pageContext.setAttribute("list",list);
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

<style type="text/css">
		  /*���a�r��ޤJ*/
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
<div class="container-fluid">
	<div class="row">
	
		<jsp:include page="/WEB-INF/common/adminNavbar.jsp" flush="true"/>

		<div class="col-lg-12 contentbox" >
		  <img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">
		
		  
			<jsp:include page="/WEB-INF/common/adminSideBar.jsp" flush="true"/>
			
		  
			<div class="col-lg-10 col-md-10 cb rightContent">
		     
<table id="table-1" >
	<tr><td>
		 <h3>�Ҧ��ӫ~���</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table border=1>
 <tr>
 	<th>�˳ƽs�� </th>
 	<th>�˳ƦW��</th>
 	<th>�˳����O</th>
 	<th>�˳Ƥؤo</th>
 	<th>�˳ƫ~�P</th>
 	<th>�˳ƻ���</th>
 	<th>�˳ƪ��A</th>
 	<th>�˳ƹϤ�</th>
 	<th>�˳Ʊԭz</th>
 	<th>�˳ƺ����`�ƽs��</th>
 	<th>�ק�</th>
 	<th>�R��</th>
 </tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="equVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(equVO.eq_num==param.eq_num) ? 'bgcolor=#CCCCFF':''}>
							<td>${equVO.eq_num}</td>
							<td>${equVO.eq_name}</td>
							<td>${equVO.eq_type}</td>
							<td>${equVO.eq_size}</td>
							<td>${equVO.eq_brand}</td>
							<td>${equVO.eq_price}</td>
							<td>${equVO.eq_status}</td>
							<td><img src="<%=request.getContextPath()%>/equipment/DBGifReader4.do?eq_num=${equVO.eq_num}" height=100 weight=100></td>
							<td>${equVO.eq_detail}</td>
							<td>${equVO.type_eq_num}</td>					
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/equipment/equipment.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�"> 
			     <input type="hidden" name="eq_num"      value="${equVO.eq_num}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/equipment/equipment.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="eq_num"      value="${equVO.eq_num}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               
			     <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>














		  </div>
		
		</div>






	<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>
	</div>
</div>




<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>