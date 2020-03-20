<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.js" type="text/javascript"></script>

<style type="text/css">



.newsBox{
	height: 180px;
	width: 600px;
	overflow: hidden;
/* 	display:block; */
}

.news{
	background-color: deepgray;
	position: relative;
    display: block;
	height: 180px;
	width: 600px;
/* 	border: 1px solid gray; */
	padding: 8px;
	color:white;
	/*background-color:black;*/
}

.news::before{
	content: ' '; 
     display: inline-block;
     height: 100%;
     width: 0;
     vertical-align: middle;
}

.news span{
	font-size: 17px;
    vertical-align: middle;
    text-align: left;
    display: inline-block;
}

.loudspeaker{
	margin: 10px;
	position: relative;
    left: -40px;
}

  @media (max-width:1220px){
      .news span{
          font-size: 15px;
      }
  }

</style>

</head>
<body>



<div class="newsBox col-lg-12 container">
	<div class="news row">
		<img class="loudspeaker" src="<%= application.getContextPath() %>/assets/images/Icon/loudspeaker.png">
		<span>台21線87公里處因連日豪雨，造成土石坍崩，<br>
			道路暫時封閉，相關單位正全力搶修中，請民眾請繞道而行。<br>
			道路開通後將盡速發布新聞通知。
		</span>
	</div>

	<div class="news row">
		<img class="loudspeaker" src="<%= application.getContextPath() %>/assets/images/Icon/loudspeaker.png">
		<span>玉山國家公園台灣黑熊數量增加，在瓦拉米步道連日現蹤，<br>
			請民眾不要靠近、餵食，以免變成飼料。</span>
	</div>

	<div class="news row">
		<img class="loudspeaker" src="<%= application.getContextPath() %>/assets/images/Icon/loudspeaker.png">
		<span>為慶祝山域開放，玉山國家公園將於12月31日在塔塔加舉辦<br>
			營火音樂晚會，邀請所有民眾共襄盛舉 !
		</span>
	</div>
</div>



<script type="text/javascript">
	
$(document).ready(function(){



$('.newsBox').slick({
    autoplay: true,
    arrows: false,
    dots: false,
    slidesToShow: 1,
    centerPadding: "10px",
    draggable: false,
    infinite: true,
    pauseOnHover: false,
    swipe: false,
    touchMove: false,
    vertical: true,
    speed: 1000,
    autoplaySpeed: 3000,
    useTransform: true,
    cssEase: 'cubic-bezier(0.645, 0.045, 0.355, 1.000)',
    adaptiveHeight: true,
  });


})



</script>

</body>
</html>