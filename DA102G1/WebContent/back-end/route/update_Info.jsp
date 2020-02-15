<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.info.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="java.util.*"%>

<%
InfoVO infoVO = (InfoVO) request.getAttribute("infoVO");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"
	href="https://i.imgur.com/J0aPP1N.png" />
<title>Background</title>
<meta charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/vendor/assets/javascripts/jquery.datetimepicker.full.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/assets/stylesheets/ROUTE.css">
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/assets/stylesheets/body.css">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/vendor/assets/stylesheets/jquery.datetimepicker.css">

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

.add_input{
	width:50px;
}



th, td {
	width: 18%;
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

.time{
	width:10px;
	padding:0px
}

.info_td{
	width:100px;
	padding:0px 40px;
}

.info_td input{
	width:100px;
}

.time input{
	width:230px;
	margin:0px;
	padding:0px;
}

.app_table {
	text-align: center;
	margin-left: 100px;
	margin-bottom:100px;
}

button{
	margin:10px;
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
		    
		     <div class="col-lg-12 cb">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
		     <form METHOD="get" ACTION="<%=request.getContextPath() %>/info/info.do">
              <table class="app_table">
                <tr>
                  <th class="info_td">路線編號</th><th class="info_td">路線名稱</th><th class="time">開放時間</th><th class="info_td">開放狀態</th><th>功能鈕</th>
                </tr>
                <tr>  
                  <td class="info_td"><input  type="text" name="route_ID" value="<%=infoVO.getRoute_ID()%> "disabled="disabled"></td>
                  <td class="info_td"><input  type="text"  name="route_Name" value="<%=(infoVO==null)? "哈哈" : infoVO.getRoute_Name()%>"></td>
                  <td class="time" ><input type="text" id="f_date1" name="open_Time" value="<%=(infoVO==null)? "哈哈" : infoVO.getOpen_Time()%>"></td>
          
                  <td class="info_td2"><input type="radio" name="open_Status" value="0">不開放<input type="radio" name="open_Status" value="1">開放</td>
                  <td><button type="submit" >確認修改</button></td>
                </tr>
              </table>
              <input type="hidden" name="action" value="update">
              <input type="hidden" name="route_ID" value="<%=infoVO.getRoute_ID()%>">
            </form>
		     
		     </div>
		
		  </div>
		
		</div>



	<jsp:include page="/common/footer.jsp" flush="true"/>
	</div>
</div>

<script>
        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $('#f_date1').datetimepicker({
           theme: '',          //theme: 'dark',
           timepicker: true,   //timepicker: false,
           step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
	       format: 'Y-m-d H:i:s',
	       value: new Date(),
           //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	        '2017/07/10',  // 起始日
           //minDate:           '-1970-01-01', // 去除今日(不含)之前
           //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
        });
        
</script>
<script  type="text/javascript">

$().focus();
</script >



<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/sideBar.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>