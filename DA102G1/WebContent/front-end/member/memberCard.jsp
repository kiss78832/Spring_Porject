<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*" %>
<jsp:useBean id="mSvc" class="com.member.model.MemberService"/>
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
memberVO = mSvc.getOneMember(memberVO.getMember_id());
pageContext.setAttribute("memberVO", memberVO);
%>


<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png"/>
	<title>Island Peak MemberCard</title>
	<meta charset="utf-8">
	<!-- 避免快取 -->
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="0" />
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<!-- body&footer -->
<!-- member -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/memberContentForCard.css">
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/memberContent.css">
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/card.css">


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

<jsp:include page="/common/signup.jsp" flush="true"/>

<div class="container-fluid">
  <div class="row">
	
	<jsp:include page="/common/navbar.jsp" flush="true"/>

<div class="col-lg-12 contentbox" >
<img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">
  
  
    <jsp:include page="/common/memberSideBar.jsp" flush="true"/>
    
    
  <div class="col-lg-10 col-md-10 cb cright">
    
       
    <div class="container-fluid">

      <div  class="col-lg-12 card_1 row">
        <img src="<%= application.getContextPath() %>/member/BG.get"class="BG">
        <div class="cphoto">
          <img src="<%= application.getContextPath() %>/member/m_photo.get">
        </div>

        <div class="nick_name">${memberVO.nick_name}</div>
        
        <textarea class="outdoor_exp">${memberVO.outdoor_exp}</textarea>

        <div class="ADPoint">
          <span>攻略點數  :   </span><br>
          <div class="ADRate">0</div>
      </div>


        <div class="pie_chart">
        	<span>攻略進度  :   </span>
          <div class="chart" data-percent="${memberVO.raiders_rate}%">${memberVO.raiders_rate}%</div>
        </div>





      </div>

    </div>
    <div><a href="<%= application.getContextPath() %>/front-end/member/cardEdit.jsp"><button class="edCard">編輯名片</button></a></div>
    

  </div>

</div>



	<jsp:include page="/common/footer.jsp" flush="true"/>
  </div>
</div>

<script type="text/javascript">
  
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
<!-- sideBar JS -->
<!-- bootstrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>