<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta charset="utf-8">
	<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/memberSideBar.js"></script>
</head>
<body>
  <div id="cleft" class="col-lg-2 col-md-2 cb">
<!-- 左側區塊 可放按鈕等等 -->
    <br>
    <div class="cb col-lg-12"><h2>會員管理</h2></div>

    <button class="collapsible">我的帳戶</button>
    <div class="content">
    	<a href="<%= application.getContextPath() %>/front-end/member/memberData.jsp">
        <button class="mbtn">個人檔案</button><br> 
      </a>
      <a href="<%= application.getContextPath() %>/front-end/member/memberCard.jsp">
        <button class="mbtn">我的名片</button><br>
      </a>
    </div>

    <button class="collapsible">我的訂單</button>
    <div class="content">
          <button class="mbtn">租借訂單</button><br>
          <a href="<%= application.getContextPath() %>/front-end/order_detail/Order_Query.jsp"> 
          <button class="mbtn">食宿訂單</button><br>
          </a>
    </div>

    <button class="collapsible">入園申請</button>
    <div class="content">
          <button class="mbtn">申請進度</button><br>
         <a href="<%= application.getContextPath() %>/front-end/application/Application_display.jsp">
          <button class="mbtn">入園歷史紀錄</button>
         </a>
    </div>

    <button class="collapsible">活動紀錄</button>
    <div class="content">
          <a href="<%= application.getContextPath() %>/front-end/group/groupLeader/groupList.jsp">
          	<button class="mbtn">個人活動</button>
          </a>
          <br> 
          <button class="mbtn">園區足跡</button><br>
          <button class="mbtn">攻略點數</button><br>
    </div>

    <button class="collapsible">文章管理</button>
    <div class="content">
          <a href="<%= application.getContextPath() %>/front-end/article/article.jsp">
          	<button class="mbtn">個人文章</button><br>
          </a> 
          <button class="mbtn">收藏文章</button><br>
    </div>
    
  </div>

</body>
</html>