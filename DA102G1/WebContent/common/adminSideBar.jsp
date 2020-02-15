<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta charset="utf-8">
</head>

<style type="text/css">
      
      .keep_out{
            background-color: rgba(255,0,0,0.4);
            pointer-events: none;
      }

</style>


<body>
	<div id="left" class="col-lg-2 col-md-2 cb">

    
<br>
<h2>Management</h2>
<h2>Options</h2>

<button class="collapsible">帳號管理</button>
<div class="content">
      <a href="<%= application.getContextPath()%>/back-end/member/member_Admin.jsp">
      <button class="btnBG">一般會員管理</button><!-- F001 -->
      </a><br> 
      <a href="<%= application.getContextPath()%>/back-end/staff/listAllStaff.jsp">
      <button class="btnBG">員工帳號管理</button><!-- F002 -->
      </a>
</div>

<button class="collapsible">園區管理</button><!-- F003 -->
<div class="content">
		<a href="<%= application.getContextPath()%>/back-end/application/Application_back.jsp">
		<button class="btnBG">入園申請管理</button>
		</a>
      <br>
      <button class="btnBG">回報管理</button><br>
</div>
<button class="collapsible">商品租還管理</button><!-- F004 -->
<div class="content">
	<a href="<%= application.getContextPath()%>/back-end/product/select_page.jsp">
      <button class="btnBG">商品管理</button>
    </a><br>
      <button class="btnBG">租借管理</button><br>
      <button class="btnBG">歸還管理</button><br>
</div>

<button class="collapsible">食宿管理</button><!-- F005 -->
<div class="content">
	<a href="<%= application.getContextPath()%>/back-end/location/locationMge.jsp">
      <button class="btnBG">據點管理</button>
      </a><br>
      <a href="<%= application.getContextPath()%>/back-end/order_detail/OrderMge.jsp">
      <button class="btnBG">訂單管理</button>
      </a><br>
    <a href="<%= application.getContextPath()%>/back-end/meal/mealMge.jsp">
      <button class="btnBG">套餐管理</button>
    </a><br>
</div>

<button class="collapsible">資訊管理</button><!-- F006 -->
<div class="content">
	<button class="btnBG">園區新聞管理</button><br>
	<a href="<%= application.getContextPath()%>/back-end/route/Route_Info.jsp">
	<button class="btnBG">開放路線管理</button>
	</a>
	<br>
	<button class="btnBG">GPX地圖管理</button><br>
</div>

<button class="collapsible">檢舉管理</button><!-- F007 -->
<div class="content">
	
      <button class="btnBG">揪團檢舉管理</button><br>
      <a href="<%= application.getContextPath()%>/back-end/ati_report/ati_report.jsp">
      <button class="btnBG">資訊版檢舉管理</button>
      </a>
      <br>
</div>


<script type="text/javascript"><!-- 整合時記得遮蔽網址 -->
      
$(document).ready(function(){
	if("${sessionScope.F001}"!="F001"){
        $('#left').find(".btnBG").eq(0).addClass("keep_out");
        $('#left').find(".btnBG").eq(0).parent().attr("href","#");
        $('#left').find(".btnBG").eq(0).text("Admin Only !");
  }
	if("${sessionScope.F002}"!="F002"){
        $('#left').find(".btnBG").eq(1).addClass("keep_out");
        $('#left').find(".btnBG").eq(1).parent().attr("href","#");
        $('#left').find(".btnBG").eq(1).text("Admin Only !");
  }
    if("${sessionScope.F003}"!="F003"){
        $('#left').find(".collapsible").eq(1).addClass("keep_out");
        $('#left').find(".collapsible").eq(1).text("Admin Only !");
  }
    if("${sessionScope.F004}"!="F004"){
        $('#left').find(".collapsible").eq(2).addClass("keep_out");
        $('#left').find(".collapsible").eq(2).text("Admin Only !");
  }
    if("${sessionScope.F005}"!="F005"){
        $('#left').find(".collapsible").eq(3).addClass("keep_out");
        $('#left').find(".collapsible").eq(3).text("Admin Only !");
  }
	
    if("${sessionScope.F006}"!="F006"){
        $('#left').find(".collapsible").eq(4).addClass("keep_out");
        $('#left').find(".collapsible").eq(4).text("Admin Only !");
  }
	
      if("${sessionScope.F007}"!="F007"){
            $('#left').find(".collapsible").eq(5).addClass("keep_out");
            $('#left').find(".collapsible").eq(5).text("Admin Only !");
      }
      

      
})



</script>

<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/adminSideBar.js"></script>


    
  </div>
</body>
</html>