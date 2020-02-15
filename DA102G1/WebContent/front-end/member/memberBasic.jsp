<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
  <title>Member</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<!-- body&footer -->

<!-- member -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/memberContent.css">

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
  
  <div id="cleft" class="col-lg-2 col-md-2 cb">
<!-- 左側區塊 可放按鈕等等 -->
    <br>
    <div class="cb col-lg-12"><h2>會員管理</h2></div>

    <button class="collapsible">我的帳戶</button>
    <div class="content">
          <button class="btn">個人檔案</button><br> 
          <button class="btn">我的名片</button><br>
    </div>

    <button class="collapsible">我的訂單</button>
    <div class="content">
          <button class="btn">租借訂單</button><br> 
          <button class="btn">食宿訂單</button><br>
    </div>

    <button class="collapsible">入園申請</button>
    <div class="content">
          <button class="btn">申請進度</button><br> 
    </div>

    <button class="collapsible">活動紀錄</button>
    <div class="content">
          <button class="btn">個人活動</button><br> 
          <button class="btn">園區足跡</button><br>
          <button class="btn">攻略點數</button><br>
    </div>

    <button class="collapsible">文章管理</button>
    <div class="content">
          <button class="btn">個人文章</button><br> 
          <button class="btn">收藏文章</button><br>
    </div>
    
  </div>
  <div id="cright" class="col-lg-10 col-md-10 cb">
    
     
		<div> 你的內容 </div>
    

  </div>

</div>



		<jsp:include page="/common/footer.jsp" flush="true"/>	
  </div>
</div>

<!-- sideBar JS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/memberSideBar.js"></script>
<!-- navJS -->
<!-- bootstrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>