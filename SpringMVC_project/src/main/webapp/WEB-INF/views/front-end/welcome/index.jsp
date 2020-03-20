<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%    // prevent browser cache jsp     
  response.setHeader("Pragma", "No-cache"); // HTTP 1.0
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
  response.setDateHeader("Expires", -1); // proxies
%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
<meta charset="utf-8">
<title>Island Peak</title>
<!-- Bootstrap & JQuery -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- index&login JS -->


<!-- body -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/body.css">
<!-- index -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/index.css">

<style type="text/css">
 /*本地字體引入 無法用css檔外包*/
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

  

/*小於992 轉折*/
	@media (max-width: 992px) {
		.carousel-caption p{
			color: red;
		}
	}
/*小於768*/
  @media (max-width: 700px) {
      .pictext {
      font-size: 5px;
      font-weight: 100;
    }
  }
</style>
</head>

<body>

<jsp:include page="/WEB-INF/common/signup.jsp" flush="true"/>

<div class="container-fluid">
	<div class="row">
<!-- header -->	
	<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true"/>
	
<!-- 輪播牆 -->
<div id="carouselExampleIndicators"  class="carousel slide carousel-fade" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="<%= application.getContextPath() %>/assets/images/snowpeak/cpic2.png" class="d-block w-100 cpic" alt="...">
      <div class="pictext">
        <button class="picbtn">Go Hike</button>
        <p>To see the world, things dangerous to come to, to see behind walls, <br/>draw closer,to find each other and to feel. That is the purpose of life.</p>
      </div>
    </div>
    
      <div class="carousel-item">
        <img src="<%= application.getContextPath() %>/assets/images/snowpeak/cpic1.png" class="d-block w-100 cpic" alt="...">
        <div class="pictext">
           <button class="picbtn">Go Hike</button>
           <p>Life is about courage and going to the unknown.</p>
        </div>
      </div>
      
    <div class="carousel-item">
      <img src="<%= application.getContextPath() %>/assets/images/snowpeak/cpic3.png" class="d-block w-100 cpic" alt="...">
      <div class="pictext">
        <button class="picbtn">Go Hike</button>
        <p>Don't stuck in your own little world because the purpose of life is to explore and experience.</p>
      </div>
    </div>
  </div>
  
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>


<!-- 服務功能選單 -->
  <div class="col-lg-12 centertitle">
    <h1>Island Peak</h1> 
    <p>Find where you truly belong.</p> 
  </div>
<div id="functionbody">
  <div class="col-lg-12 container">
    <div class="col-lg-4" >
      <div class="col-lg-12 topbox">
        <img src="<%= application.getContextPath() %>/assets/images/snowpeak/bonfire-1867275.jpg">
        <a href="<%= application.getContextPath() %>/front-end/group/joinGroupList.jsp"><button class="btn">揪　　團</button></a><!-- 內含全行空白 -->
      </div>
      <div class="col-lg-6 leftbox">
        <a href="<%= application.getContextPath() %>/front-end/route/roadState.jsp">
        <img src="<%= application.getContextPath() %>/assets/images/Icon/ルートアイコン.png" style="display:block; margin: auto;">路線開放資訊
        </a> 
    </div>
      <div class="col-lg-6 rightbox">
        <a href="<%= application.getContextPath() %>/front-end/application/Application.jsp">
          <img src="<%= application.getContextPath() %>/assets/images/Icon/証明書のフリーアイコン.png" style="display:block; margin: auto;">申請入園
        </a> 
      </div>
    </div>

    <div class="col-lg-4">
      <div class="col-lg-12 topbox">
        <img src="<%= application.getContextPath() %>/assets/images/snowpeak/hiking-1312226.jpg">
        <a href="<%= application.getContextPath() %>/front-end/equipment/renthome.jsp">
        <button class="btn">裝備租借</button>
        </a>
      </div>
      <div class="col-lg-6 leftbox">
        <a href="<%= application.getContextPath() %>/front-end/article/article_front.jsp">
          <img src="<%= application.getContextPath() %>/assets/images/Icon/WEBサイトアイコン.png" style="display:block; margin: auto;">資訊討論版
        </a> 
      </div>
      <div class="col-lg-6 rightbox">
      <a href="<%= application.getContextPath() %>/assets/images/forDownload/玉山全域圖.jpg" download="玉山全域圖">
     <img src="<%= application.getContextPath() %>/assets/images/forDownload/玉山全域圖.jpg" alt="玉山全域圖" style="display:none;">
          <img src="<%= application.getContextPath() %>/assets/images/Icon/方位磁石のフリーアイコン素材 2.png" style="display:block; margin: auto;">下載園區地圖
        </a> 
      </div>
    </div>

    <div class="col-lg-4">
      <div class="col-lg-12 topbox">
        <img src="<%= application.getContextPath() %>/assets/images/snowpeak/alps-21844.jpg">
        <a href="<%= application.getContextPath() %>/front-end/resIndex/ResIndex.jsp">
        <button class="btn">床位預定</button>
        </a>
      </div>
      <div class="col-lg-6 leftbox">
        <a href="<%= application.getContextPath() %>/front-end/info/Info.jsp">
          <img src="<%= application.getContextPath() %>/assets/images/Icon/インフォメーションアイコン3.png" style="display:block; margin: auto;">園區資訊
        </a> 
      </div>
      <div class="col-lg-6 rightbox">
        <a>
          <img src="<%= application.getContextPath() %>/assets/images/Icon/cloudy.png" style="display:block; margin: auto;">天氣資訊
        </a> 
      </div>
    </div>
  </div>
</div>


<div class="col-lg-6 row">
<!-- 天氣區塊 -->
<jsp:include page="/WEB-INF/common/TodayWeather.jsp" flush="true"/>
<!-- 新聞區塊 -->
<jsp:include page="/WEB-INF/common/news.jsp" flush="true"/>
</div>

<!-- 即時影像 -->
<div class="col-lg-6" id="livevedio">
  <h2>園區即時影像</h2>
  <ul class="nav nav-pills mb-3 justify-content-center" id="pills-tab" role="tablist">
  <li class="nav-item vedio">
        <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact1" role="tab" aria-controls="pills-contact" aria-selected="false">水里遊客中心</a>
  </li>
  <li class="nav-item vedio">
     <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-contact2" role="tab" aria-controls="pills-profile" aria-selected="false">塔塔加</a>
  </li>
  <li class="nav-item vedio">
    <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact3" role="tab" aria-controls="pills-contact" aria-selected="false">南安遊客中心</a>
  </li>
  
   <li class="nav-item vedio">
    <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact4" role="tab" aria-controls="pills-contact" aria-selected="false">小風口</a>
  </li>
  
  <li class="nav-item vedio">
    <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact5" role="tab" aria-controls="pills-contact" aria-selected="false">觀霧</a>
  </li>
  
</ul>

<div class="tab-content" id="pills-tabContent">

  <div class="tab-pane fade show active" id="pills-contact1" role="tabpanel" aria-labelledby="pills-home-tab">
    <img src="http://117.56.165.182/mjpg/video.mjpg" alt="水里遊客中心" onerror="this.src='https://cctv.taskinghouse.com/assets/maintenance.jpg';" class="single-img">
  </div>
  
  <div class="tab-pane fade" id="pills-contact2" role="tabpanel" aria-labelledby="pills-profile-tab">
   <img src="http://61.218.184.67/mjpg/video.mjpg" alt="塔塔加遊客中心" onerror="this.src='https://cctv.taskinghouse.com/assets/maintenance.jpg';" class="single-img">
  </div>
  
  <div class="tab-pane fade" id="pills-contact3" role="tabpanel" aria-labelledby="pills-contact-tab"> 
    <img src="http://59.120.50.34/mjpg/video.mjpg" alt="南安遊客中心" onerror="this.src='https://cctv.taskinghouse.com/assets/maintenance.jpg';" class="single-img">
  </div>
  
  <div class="tab-pane fade" id="pills-contact4" role="tabpanel" aria-labelledby="pills-contact-tab"> 
    <img src="http://117.56.55.25/T14A-36K+560/" alt="小風口" onerror="this.src='https://cctv.taskinghouse.com/assets/maintenance.jpg';" class="single-img">
  </div>
  
  <div class="tab-pane fade" id="pills-contact5" role="tabpanel" aria-labelledby="pills-contact-tab"> 
    <img src="http://210.241.53.147/abs2mjpg/mjpg?camera=8" alt="觀霧遊客中心" onerror="this.src='https://cctv.taskinghouse.com/assets/maintenance.jpg';" class="single-img">
  </div>
  
</div>

</div>

	
	<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>

	</div>
</div>



<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>
<!-- bootstrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>