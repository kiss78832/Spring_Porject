<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <% session.setAttribute("location", request.getRequestURI()); %> --%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <!--  navbar -->
    <!-- 數字動畫 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-animateNumber/0.0.14/jquery.animateNumber.min.js"></script>
    <!-- 圓形刻度 -->
    <script src="https://rendro.github.io/easy-pie-chart/javascripts/jquery.easy-pie-chart.js"></script>
    
    <!-- sweetalert -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
    
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/navbar.css">
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/body.css">
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>
<style type="text/css">
.m_photo {
    width: 55px;
    height: 55px;
    border-radius: 68px;
/*     border: 5px solid paleturquoise; */
	display: inline-block;
    margin: 0;
    padding: 0;
    border: 2px solid white;
</style>
</head>
<body>
    <nav id="main-nav" class="navbar navbar-expand-sm navbar-dark fixed-top">
    <div class="container-fluid">
      <img src="<%= application.getContextPath() %>/assets/images/Icon/bear.png" id="logo">
      <a href="<%= application.getContextPath() %>/front-end/welcome/index.jsp" class="navbar-brand" id="mainlogo">Island Peak</a>
      
        <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div id="navbarCollapse" class="collapse navbar-collapse" >
            <ul class="navbar-nav ml-auto">
           		 <li>
					<img src="<%= application.getContextPath() %>/member/m_photo2.get?member_id=${memberVO==null?'':memberVO.member_id}" class="m_photo">
				</li>

                <li class="nav-item">
                    <a href="<%= application.getContextPath() %>/front-end/member/memberData.jsp" class="nav-link alink">Member Area</a>
                </li>
                <li class="nav-item" id="signin">
                    <a class="nav-link alink" style="cursor: pointer;">Sign In</a>
                </li>
                 <li class="nav-item" id="signout">
                    <a class="nav-link alink" href="<%= application.getContextPath() %>/signinhandler">Sign Out</a>
                </li>
                <li class="nav-item">
                   <a href="<%=request.getContextPath()%>/front-end/equipment/shoppingCart.jsp" class="nav-link alink">
                   <img src="<%= application.getContextPath() %>/assets/images/Icon/cart.png">
                   </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>