<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.info.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="java.util.*"%>

<%
	InfoVO infoVO = (InfoVO) request.getAttribute("infoVO");
	InfoService infoSvc = new InfoService();
	List<InfoVO> list = infoSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"
	href="https://i.imgur.com/J0aPP1N.png" />
<title>Island Peak</title>
<meta charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/vendor/assets/javascripts/jquery.datetimepicker.full.js"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/vendor/assets/stylesheets/jquery.datetimepicker.css">
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/backgroundContext.css">

	
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/assets/stylesheets/ROUTE.css">


<style type="text/css">
/*���a�r��ޤJ*/
@font-face {
	font-family: Russo;
	src: url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
	unicode-range: U+00-024F;
}

@font-face {
	font-family: Noto;
	src: url('/DA102G1/assets/fonts/NotoSansTC-Medium.otf');
	unicode-range: U+4E00-9FFF;
}

#info_top_add {
	text-align: center;
 	min-width: 800px; 
	margin: auto;
	margin-bottom: 100px;
}

.add_input {
 	width: 50px; 
}

th, td {
 	width: 10%; 
	padding: 10px 0px;
}

th {
	color: brown;
	background: lightgray;
}

tr {
	border-top: 4px solid #fff;
	background: dimgray;
}

.time {
	width: 10px;
	padding: 0px
}

.info_td {
	width: 60px;
	
}

.info_td input {
	width: 100px;
}

.time input {
	width: 180px;
	margin: 0px;
	padding: 0px;
}

.app_table {
	display: none;
	padding: 0px;
	margin: 200px;
}

.info_td2{
	width:150px;
}
</style>



</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/common/adminNavbar.jsp" flush="true" />

			<div class="col-lg-12 contentbox">
				<img
					src="<%=application.getContextPath()%>/assets/images/snowpeak/forest-1743206.jpg">


				<jsp:include page="/common/adminSideBar.jsp" flush="true" />


				<div class="col-lg-10 col-md-10 cb rightContent">

					<div class="col-lg-12 cb">
						<table id="info_top_add">

							<%-- ���~��C --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">�Эץ��H�U���~:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>


							<tr>
								<th class="info_td">���u�s��</th>
								<th class="info_td">���u�W��</th>
								<th class="time">�}��ɶ�</th>
								<th class="info_td">�}�񪬺A</th>
								<th>�\��s</th>
								<th>�\��s</th>
							</tr>

							<c:forEach var="infoVO" items="${list}">

								<tr id="talbe_info">
									<td class="info_td">${infoVO.route_ID}</td>
									<c:set scope="request" var="route_ID">
									</c:set>
									<td class="info_td">${infoVO.route_Name}</td>
									<td class="time">${infoVO.open_Time}</td>
									<td class="status"><c:if test="${infoVO.open_Status==0}">���}��</c:if>
										<c:if test="${infoVO.open_Status==1}">�}��</c:if></td>

									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/info/info.do"
											style="margin-bottom: 0px;">
											<input class="update_info" type="submit" value="�ק�">
											<input type="hidden" name="route_ID"
												value="${infoVO.route_ID}"> 
											<input type="hidden"
												name="action" value="getOne_For_Update">
										</FORM>
									</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/info/info.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="�R��"> <input type="hidden"
												name="route_ID" value="${infoVO.route_ID}"> <input
												type="hidden" name="action" value="delete">
										</FORM>
									</td>
								</tr>
							</c:forEach>
						</table>



						<form METHOD="get" ACTION="<%=request.getContextPath()%>/info/info.do" name="form">
							<table class="app_table">
								<tr>
									<th class="info_td">���u�s��</th>
									<th class="info_td">���u�W��</th>
									<th class="time">�}��ɶ�</th>
									<th class="info_td">�}�񪬺A</th>
									<th>�\��s</th>
								</tr>
								<tr>
									<td class="info_td"><input type="text" name="route_ID" id="route_id" ></td>
									<td class="info_td"><input type="text" name="route_Name"></td>
									<td class="time"><input name="open_Time" id="f_date1" type="text" ></td>
									<td class="info_td2"><input type="radio" name="open_Status" value="0">���}��<input type="radio" name="open_Status" value="1">�}��
									</td>
									<td><button type="submit" >�T�{�s�W</button></td>
								</tr>
							</table>
							<input type="hidden" name="action" value="insert">
							
						</form>

						

						<div>
							<button id="add" >�s�W</button>
						</div>



					</div>

				</div>

			</div>

		</div>
	</div>



	<script type="text/javascript">
		$("#add").click(function() {
			$(".app_table").css({
				"margin-right" : "210px",
				"display" : "inline"
			})
		$("#route_id").focus();
		});
		
	</script>

<script type="text/javascript">
$.datetimepicker.setLocale('zh'); // kr ko ja en
$('#f_date1').datetimepicker({
   theme: '',          //theme: 'dark',
   timepicker: true,   //timepicker: false,
   step: 1,            //step: 60 (�o�Otimepicker���w�]���j60����)
   format: 'Y-m-d H:i:s',
   value: new Date(),
   //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
   //startDate:	        '2017/07/10',  // �_�l��
   //minDate:           '-1970-01-01', // �h������(���t)���e
   //maxDate:           '+1970-01-01'  // �h������(���t)����
});

</script>	
												
	


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>

