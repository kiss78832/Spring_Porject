<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.spring.member.model.*" %>

<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
MemberJDBCDAO dao = new MemberJDBCDAO();
memberVO = dao.findByPrimaryKey(memberVO.getMember_id());
pageContext.setAttribute("memberVO", memberVO);
%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- 暫放 等放入有navbar 畫面後移除 -->
<script src="https://rendro.github.io/easy-pie-chart/javascripts/jquery.easy-pie-chart.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-animateNumber/0.0.14/jquery.animateNumber.min.js"></script>

<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/card.css">
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/cardCenter.css">

	<style type="text/css">
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
			
		body{
			font-family: Russo, Noto ,'微軟正黑體',sans-serif;
			font-size: 18px;
			background-image: url(<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg);
			background-size: cover;
			background-repeat: no-repeat;
			color: white;
			margin:0px;
			text-align: center;
			
		}


	</style>
</head>
<body>
<div class="cardBack" class="container-fluid">

	<div  class="col-lg-12 card_1">
		<img src="<%= application.getContextPath() %>/assets/images/snowpeak/fire3.jpg"class="BG">
		<div class="cphoto">
			<img src="<%= application.getContextPath() %>/assets/images/Profile_picture/Bear-Grylls-quotes-1x1.jpg">
		</div>

		<div class="nick_name">${memberVO.nick_name}</div>
		
		<textarea class="outdoor_exp">${memberVO.outdoor_exp}</textarea>
		
		<div class="ADPoint">
		   <span>攻略點數  :   </span><br>
		   <div class="ADRate">${memberVO.adventure_point}</div>
		</div>


		<div class="pie_chart">
			<span>攻略進度  :   </span>
		  <div class="chart" data-percent="${memberVO.raiders_rate}%">${memberVO.raiders_rate}%</div>
		</div>

	
	</div>

</div>
<script type="text/javascript">
/*ADPoint 動畫*/
	$('.ADRate') 
	.prop('number', 0) 
	.animateNumber( 
	
	{ 
	 number: ${memberVO.adventure_point} 
	}, 
	3000 
	);
</script>


<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/card.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>