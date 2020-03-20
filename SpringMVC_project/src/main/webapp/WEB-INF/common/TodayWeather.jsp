<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.helpers.weather.model.*" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta charset="utf-8">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.js" type="text/javascript"></script>


<style type="text/css">


.weatherBox{
	height: 180px;
	width: 600px;
	overflow: hidden;
}

.weatherLoca{
	/*background-color: deepgray;*/
	position: relative;
    display: block;
	height: 180px;
	width: 600px;
/* 	border: 1px solid gray; */
	padding: 8px;
	color:white;
	/*background-color:black;*/
}

.weatherLoca h2{
	margin-bottom: 10px;
    text-align: left;
}

.weatherLoca span{
	font-size: 45px;
	/*line-height: -1rem;*/
}
.weatherLoca font{

}
.weatherLoca div{
	float: left;
	margin-right: 8px;
}

.picdown{
	    bottom: -13px;
}

.sun{
	text-align: center;
	margin-left: 10px;
}	

.WIcon{
	
}

</style>

</head>
<body>

<%
WeatherVO weatherVO1 = (WeatherVO)application.getAttribute("W1");
WeatherVO weatherVO2 = (WeatherVO)application.getAttribute("W2");
WeatherVO weatherVO3 = (WeatherVO)application.getAttribute("W3");
WeatherVO weatherVO4 = (WeatherVO)application.getAttribute("W4");
WeatherVO weatherVO5 = (WeatherVO)application.getAttribute("W5");
%>

<div class="weatherBox container col-lg-12">
	
	<!-- 1 -->
	<div class="weatherLoca col-lg-12 m-auto">
	<h2>${W1.title }</h2>
	<div class="picdown">
		<img class="WIcon" src="${W1.picSrc }">
	</div>
	
	<div>
		<font>氣溫</font><br>
		<span class="WTemp">${W1.temperature }˚C</span>
	</div>
	<div class="picdown">
		<img class="WIcon" src="<%= application.getContextPath() %>/assets/images/Icon/umbrella.png">
	</div>
	
	<div>
		<font>降雨機率</font><br>
		<span class="WRain" >${W1.rain }</span>
	</div>

	<div class="sun" style="">
		<img src="<%= application.getContextPath() %>/assets/images/Icon/sunrise.png"><br>
		<font>${W1.sunrise }</font>
	</div>
	<div class="sun" style="">
		<img src="<%= application.getContextPath() %>/assets/images/Icon/sunset.png"><br>
		<font>${W1.sunset }</font>
	</div>
</div>

	<!-- 2 -->
	<div class="weatherLoca col-lg-12 m-auto">
	<h2>${W2.title }</h2>
	<div class="picdown">
		<img class="WIcon" src="${W2.picSrc }">
	</div>
	
	<div>
		<font>氣溫</font><br>
		<span class="WTemp">${W2.temperature }˚C</span>
	</div>
	<div class="picdown">
		<img class="WIcon" src="<%= application.getContextPath() %>/assets/images/Icon/umbrella.png">
	</div>
	
	<div>
		<font>降雨機率</font><br>
		<span class="WRain" >${W2.rain }</span>
	</div>

	<div class="sun" style="">
		<img src="<%= application.getContextPath() %>/assets/images/Icon/sunrise.png"><br>
		<font>${W2.sunrise }</font>
	</div>
	<div class="sun" style="">
		<img src="<%= application.getContextPath() %>/assets/images/Icon/sunset.png"><br>
		<font>${W2.sunset }</font>
	</div>
</div>

	<!-- 3 -->
	<div class="weatherLoca col-lg-12 m-auto">
	<h2>${W3.title }</h2>
	<div class="picdown">
		<img class="WIcon" src="${W3.picSrc }">
	</div>
	
	<div>
		<font>氣溫</font><br>
		<span class="WTemp">${W3.temperature }˚C</span>
	</div>
	<div class="picdown">
		<img class="WIcon" src="<%= application.getContextPath() %>/assets/images/Icon/umbrella.png">
	</div>
	
	<div>
		<font>降雨機率</font><br>
		<span class="WRain" >${W3.rain }</span>
	</div>

	<div class="sun" style="">
		<img src="<%= application.getContextPath() %>/assets/images/Icon/sunrise.png"><br>
		<font>${W3.sunrise }</font>
	</div>
	<div class="sun" style="">
		<img src="<%= application.getContextPath() %>/assets/images/Icon/sunset.png"><br>
		<font>${W3.sunset }</font>
	</div>
</div>

	<!-- 4 -->
	<div class="weatherLoca col-lg-12 m-auto">
	<h2>${W4.title }</h2>
	<div class="picdown">
		<img class="WIcon" src="${W4.picSrc}">
	</div>
	
	<div>
		<font>氣溫</font><br>
		<span class="WTemp">${W4.temperature}˚C</span>
	</div>
	<div class="picdown">
		<img class="WIcon" src="<%= application.getContextPath() %>/assets/images/Icon/umbrella.png">
	</div>
	
	<div>
		<font>降雨機率</font><br>
		<span class="WRain" >${W4.rain}</span>
	</div>

	<div class="sun" style="">
		<img src="<%= application.getContextPath() %>/assets/images/Icon/sunrise.png"><br>
		<font>${W4.sunrise}</font>
	</div>
	<div class="sun" style="">
		<img src="<%= application.getContextPath() %>/assets/images/Icon/sunset.png"><br>
		<font>${W4.sunset}</font>
	</div>
</div>


	<!-- 5 -->
	<div class="weatherLoca col-lg-12 m-auto">
	<h2>${W5.title}</h2>
	<div class="picdown">
		<img class="WIcon" src="${W5.picSrc}">
	</div>
	
	<div>
		<font>氣溫</font><br>
		<span class="WTemp">${W5.temperature}˚C</span>
	</div>
	<div class="picdown">
		<img class="WIcon" src="<%= application.getContextPath() %>/assets/images/Icon/umbrella.png">
	</div>
	
	<div>
		<font>降雨機率</font><br>
		<span class="WRain" >${W5.rain}</span>
	</div>

	<div class="sun" style="">
		<img src="<%= application.getContextPath() %>/assets/images/Icon/sunrise.png"><br>
		<font>${W5.sunrise}</font>
	</div>
	<div class="sun" style="">
		<img src="<%= application.getContextPath() %>/assets/images/Icon/sunset.png"><br>
		<font>${W5.sunset}</font>
	</div>
</div>







</div>



<script type="text/javascript">
	
$(document).ready(function(){


$('.weatherBox').slick({
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