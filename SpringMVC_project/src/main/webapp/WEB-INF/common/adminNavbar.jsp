<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
        <!-- sweetalert -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
    
    <!--  navbar -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/navbar.css">
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/body.css">
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>
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
                <li class="nav-item" id="home">
                    <a href="<%= application.getContextPath() %>/back-end/admin_Index/admin_Index.jsp" class="nav-link alink">Home</a>
                </li>
                 <li class="nav-item" id="signout">
                    <a class="nav-link alink" href="<%= application.getContextPath() %>/StaffSigninHandler">Sign Out</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<script type="text/javascript">
    
$(document).ready(function(){
    if('${admin}'==''){
    	$('#home').hide();
        $('#signout').hide();
    }else if('${admin}'!=''){
    	$('#home').show();
        $('#signout').show();
    }
})



</script>
</body>
</html>