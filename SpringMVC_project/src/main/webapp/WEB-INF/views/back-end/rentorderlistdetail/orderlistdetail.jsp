<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spring.equipment.model.*"%>
<%@ page import="com.spring.member.model.*"%>
<%@ page import="com.spring.rentodlist.model.*"%>
<%@ page import="com.spring.rentoddetail.model.*"%>

		<% List<RentOdDetailVO> rodlist = (List<RentOdDetailVO>)request.getAttribute("rodlist"); 
		pageContext.setAttribute("rodlist",rodlist);
		
		EquipmentService equSVC =new EquipmentService();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>共用頁面</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- body&footer -->
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


  /* content start*/

.contentbox{
  margin-top: 80px;
  box-sizing: border-box;
  background-image: url(/DA102G1/assets/images/snowpeak/forest-1743206.jpg);
  background-size: cover;
  height: 1250px;
  padding: 0px;
    
}
.contentbox .cb{/*contentbox 底下的div*/
  display: inline-block;
  position: relative;
}
/*左側區塊*/
#cleft{
background-color: rgba(52,53,55,0.2);
border: 1px solid rgba(150,150,150,0.5);
height: 1250px;
float: left;
}
#cleft div{
border:2px solid red;
 margin: 15px auto;
}

/*右側區塊*/
#cright{
background-color: rgba(72,73,75,0.9);
border: 1px solid rgba(150,150,150,1);
height:100%;
float: left;
}
#cright div{
  border:2px ;
 margin: 15px auto;
}
  /* content end*/









@media screen and (max-width : 480px) {
	body {
		font-size:  1.5rem;
	}
}

table {
	display: flex;
	flex-flow: column nowrap;
	line-height: 1.25;
	border: 0;
}

caption {
	padding: 1rem;
	font-size: 1.5rem;
	font-weight: 900;
	color: #999;
	caption-side: top;
}

caption span {
	font-size: 1.25rem;
	font-weight: 300;
	color: #A52A2A;
}

tr {
	display: flex;
}

tr:nth-of-type(odd) {
	background-color: #E5E5E5;
}

th {
	font-weight: 700;
	background-color: #f2f2f2;
	color: #666;
}

th,
td {
	display: flex;
	flex: 1 0;
	align-items: center;
	justify-content: center;
	padding: 0.5rem 0.1rem;
	border: 1px solid #BFBFBF;
	border-collapse: collapse;
	border-spacing: 0;
}

/* This will affect only Chrome browsers 29+
	* See: http://browserhacks.com/
	* Hack because 'hyphens: auto;' is supported in Chrome only on Android & Mac 
	* and word-break 'overrides' hyphens in Firefox
	*/
.selector:not(*:root), th, td {
	word-wrap: break-word;
	overflow-wrap: break-word;
	word-break: break-all;
}

/* Breaking the numbers! */
.numbers {
	word-break: break-word;
}



















  </style>

</head>
<body>
	<div class="container-fluid">
	  <div class="row">
		
		<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true"/>
			
			<div class="col-lg-12 contentbox" >
			
			  
			  <div id="cleft" class="col-lg-2 col-md-2 cb">
			<!-- 左側區塊 可放按鈕等等 -->
			    <div class="cb col-lg-12">看你要什麼選項等.....</div>
			    
			  </div>
			  <div id="cright" class="col-lg-10 col-md-10 cb">
			    
			     <div class="cb col-lg-12">你要管理的內容（已置中,寬度內距已調）</div>
			     
			     
			     

	<table>
		<tr>
			<th>圖片</th>
			<th>敘述</th>
			<th>名稱</th>
			<th>尺寸</th>
			<th>狀態</th>
			<th>數量</th>




		</tr>
			<c:forEach var="rodVO" items="${rodlist}" >
		<tr>
			<td><c:forEach var="equVO" items="<%=equSVC.getAll()%>">                  
                    <c:if test="${rodVO.eq_num==equVO.eq_num}">
	              <img class="pic-1" src="<%=request.getContextPath()%>/equipment/DBGifReader4.do?eq_num=${equVO.eq_num}" height="100px" weight="100px">      
                    </c:if>
                </c:forEach></td>
			<td><c:forEach var="equVO" items="<%=equSVC.getAll()%>">                  
                    <c:if test="${rodVO.eq_num==equVO.eq_num}">
	              ${equVO.eq_detail}   
                    </c:if>
                </c:forEach></td>
			<td><c:forEach var="equVO" items="<%=equSVC.getAll()%>">                  
                    <c:if test="${rodVO.eq_num==equVO.eq_num}">
	              ${equVO.eq_name}   
                    </c:if>
                </c:forEach></td>
			<td><c:forEach var="equVO" items="<%=equSVC.getAll()%>">                  
                    <c:if test="${rodVO.eq_num==equVO.eq_num}">
	              ${equVO.eq_size}   
                    </c:if>
                </c:forEach></td>
			<td><c:forEach var="equVO" items="<%=equSVC.getAll()%>">                  
                    <c:if test="${rodVO.eq_num==equVO.eq_num}">
	              ${equVO.eq_status}   
                    </c:if>
                </c:forEach></td>
             			<td><c:forEach var="equVO" items="<%=equSVC.getAll()%>">                  
                    <c:if test="${rodVO.eq_num==equVO.eq_num}">
	              ${rodVO.quantity}   
                    </c:if>
                </c:forEach></td>   


		</tr>
		</c:forEach>
	</table>

			     
			     
			     
			     
			     
			     
			     
			     
			     
			     
			     
			     
			     
			
			  </div>
			
			</div>
	
	
		<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>
	
	
	  </div>
	</div>


<!-- JS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/sideBar.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>