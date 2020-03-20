<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.spring.application.model.*"%>
<%@page import="com.spring.member.model.*"%>
<%@page import="com.spring.group.model.*"%>
<%@page import="com.spring.info.model.*"%>
<%@page import="com.spring.article.model.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<% 
	ArticleService articleSvc = new ArticleService();
	pageContext.setAttribute("articleSvc",articleSvc);
	ArticleVO articleVO_id = (ArticleVO) request.getAttribute("articleVO");
	String article_id = articleVO_id.getArticle_id();
	ArticleVO articleVO = articleSvc.getOneArticle(article_id);
	pageContext.setAttribute("articleVO", articleVO);
	
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/x-icon"
	href="https://i.imgur.com/J0aPP1N.png" />
<title>Island Peak Member Article Edit</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<link rel="stylesheet"
	href="https://apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
<script src="https://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="https://apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.9/css/all.css"
	integrity="sha384-5SOiIsAziJl6AWe0HWRKTXlfcSHKmYV4RBF18PPJ173Kzn7jzMyFuTtk8JA7QQG1"
	crossorigin="anonymous"></link>


<!-- body -->
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/assets/stylesheets/body.css">


<style type="text/css">
/*本地字體引入*/
@font-face {
	font-family: Russo;
	src: url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
	unicode-range: U+00-024F;
}

@font-face {
	font-family: Noto;
	src: url('/DA102G1/assets/fonts/NotoSansTC-Medium.otf');
	unicode-range: U+4E00-9FFF;
}

/* content start*/
.contentbox {
	margin-top: 80px;
	box-sizing: border-box;
	background-image:
		url(/DA102G1/assets/images/snowpeak/forest-1743206.jpg);
	background-size: cover;
	height: 1900px;
	padding: 0px;
}

.contentbox .cb { /*contentbox 底下的div*/
	display: inline-block;
	position: relative;
}

/*centerbox start*/
#centerbox {
	width: 65%;
	background-color: rgba(52, 53, 55, 0.9);
	border: 1px solid rgba(150, 150, 150, 0.5);
	height: 100%;
}

#centerbox div {
	
	padding-right: 13px;
}
/*centerbox end*/
.row {
	box-shadow: 0px 0px 30px rgba(0, 0, 0, .4);
	border-radius: 15px;
	margin-top: 10px;
}

span {
	border: 1px solid gray;
}

img {
	max-width: 100%;
	padding: 5px;
	/*filter:blur(5px) saturate(10%);*/
}

.change input:hover {
	box-shadow: 0px 5px 3px rgba(255, 2455, 255, .9);
	transform: translateY(-5px);
	filter: opacity(100%);
	border: 2px solid;
}

.change {
    display: inline-table;
    max-width: 100%;
    border-radius: 15px;
    margin-right: 13px;
    margin-top: 0px;
}

.change input {
    width: 70px;
    height: 69px;
    padding: 11px;
    margin-left: 8px;
    transition: all 220ms;
    border-radius: 192px;
    background-color: white;
    border: 1px dashed;
    filter: opacity(65%);
}

ul {
	list-style-type: none;
	padding: 0px;
}

.bar[type="range"] {
	-webkit-appearance: none;
	border-radius: 2px;
	width:341px;
	height: 3px;
	box-shadow: inset #ebb 0 0 5px;
	outline: none;
	transition: .1s;
}

.bar[type="range"]::-webkit-slider-thumb {
	-webkit-appearance: none;
	width: 10px;
	height: 10px;
	background: #f22;
	border-radius: 50%;
}

.bar[type="range"]::-webkit-slider-thumb:hover, .bar[type="range"]::-webkit-slider-thumb:active
	{
	width: 16px;
	height: 16px;
}

#article div {
	border-radius: 15px;
	height: 800px;
	width: 946px;
	padding: 0px;
	border: none;
}


div.item {
    position: relative;
    overflow: hidden;
    margin-top: 2px;
    min-width: 100%;
    max-width: 310px;
    width: 60%;
    background: space;
    text-align: center;
    height: 255px;
    left: 12px;
}

div.item input {
	opacity: 0;
    position: absolute;
    width: 57px;
    margin-left: 10px;
    align-items: center;
    z-index: 9999;
    right: 168px;
    height: 64px;
    top: 113px;
    cursor: pointer;
    font-size: 0px;
}

div.item * {
	box-sizing: border-box;
}

div.item img {
	max-width: 100%;
	vertical-align: center;
	height: 96%;
	width: 100%;
	right: 0px;
	bottom: -2px;
	position: absolute;
	background: rgba(0,0,0,0.6);
	border-radius: 28px;
	
	
	
}

div.item img:before {  
  content: "請選擇照片";
  display: block;
  margin-bottom: 10px;
}


div.item i {
	position: absolute;
	top: 50%;
	left: 50%;
	border-radius: 50%;
	font-size: 34px;
	color: #000000;
	width: 60px;
	height: 60px;
	line-height: 60px;
	background: #ffffff;
}

div.item:hover img {
	opacity: 0.3;
	filter: grayscale(100%);
}

div.item * {
	transition: all .35s ease-in-out;
}

div.item i {
	transform: translate(-50%, -50%) scale(0);
	transition: transform 300ms 0ms cubic-bezier(0.6, -0.28, 0.735, 0.045);
	color: #e74c3c;
}

div.item:hover i {
	transform: translate(-50%, -50%) scale(1);
	transition: transform 300ms 100ms
		cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.show_image {
    width: 465px;
    height: 309px;
    right: 34px;
    position: absolute;
    top: 39px;
}

.change_div{
    top: 393px;
    position: absolute;
    width: 674px;
    left: -42px;
}




.change_btn {
	display: none;
    top: -10px;
    right: -5px;
}

.change p{
	transform: translateY(-5px);
	filter: opacity(80%);
	padding: 4px;
}


.button_tag {
    display: inline;
    text-align: left;
    vertical-align: middle;
    padding: 2px 2px;
    background: space;
    color: #ffffff;
    text-align: center;
/*     text-decoration: none; */
    float: left;
    margin:17px 6px;
    border-radius: 5px;
    width: 14%;
}

.button_tag:hover {
    background: #8a5a5a;
    background: -webkit-gradient(linear, left top, left bottom, from(#8a5a5a), to(#1c1212));
    background: -moz-linear-gradient(top, #8a5a5a, #1c1212);
    background: linear-gradient(to bottom, #7e7e7edb, #00000073);
    color: #ffffff;
    text-decoration: none;
    text-transform: uppercase
}

.button_tag:before{
    content: "\0000a0";
    display: inline-block;
    height:21px;
    width:23px;
    line-height: 24px;
    margin: 0px -9px 3px 4px;
    position: relative;
    top: 0px;
    left: -9px;
     background: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAA7EAAAOxAGVKw4bAAADXUlEQVRIiY2Wz25bRRSHvzN1ncS2aFKClSYgxW6gKEg0RTwAUqX+WeGUqmxaXqD7Sn2CiAdgSatIUaRaUHWDWGXPikYVSlgUhyBEaGrHjQNNLV3PYTFz595r3wJHuvKx587vnPlm5hwLYMix3d0nWGsXgOvARWARqPrhfWALZAO0KSKt+fnzeTKID2DTgXZ2NuvAiog0QAu5M72pEgGPgLu12tLTlI4FTDoA29vbZny8fxP4CqiIiBdRYj8RTn5TVYC/gNtjY7o2O3shTtiGrL34HRFZBSrxRD85+Kqkfkt8kAqw2u/Lnb29zZB0vHwzPt6/KSIrwEi2+Sa5viorr17xB7AWjxjP/LGIhMxdDMn4g8Me/V9+yw03tjDPiTcqAZcq5+v1pZbs7j4x1toHuNPyWht0unTuNRn0jkK+CtipU5juIZPXrlL66AMCMfgG9HNjrV0QkYaI8Lpn0OnSud/E9o4yQfX0JMVby+jpyYAqngM0QOoGuK7qjuLwhgJEPnN7eBQgx5/m4AXS/A5z8CK9B4AiIgURbhRwlygjHlvUPghYgnB8AERQIPrzeXpNIYiIosrFArCYd2qi9kHAIilhHXkzbUJWShcNUB3OfhhLRjSFKONnhsP36aEyIE786wcMDnuZkXQQzQgqWi5hZ6vhdqtKmFEA9kVkBhyF45+fOiz/67KBLU8gn16iVJ1O0pSwqLYBtjKIFt8lmptxvn9GfJ+fLTnxmfcXMMb47NUHEYAt40pugqgyeYryZ1cZvH0GTaHJ+KpoaQJZvhzE47rkggSMGwa0CRLFSxMRpt56k/K1KwzeOZN78bRSQhqXmDl3NqBME/WlJQKaRkRaoI+GN2+qOk15+coIrv/CkmjoQ1XTyhQ7V3IVVwPdi93nHf7+9ntO/L6HlksOy7mzAYu/tUO+9oALtdpSKzScnZ3NL4DV5PS4QKpKd79N74cfKb5XD1jymlFceVW5VastrUPSD5iYKKwdH0ezqrqSZelxXf6Ek8WTGGMywjEW54Mqd4vFaN1L2EzLfPbsJ/PyZZRpmaN85d+w3C4Wo/W5uY9tci7zm/488CXQEIlXGe+LDGEhAn2Ia/q/MmSZFXjze/LYgNRFuKEa/rbE17UNbAEbQFPVtOr1D+2QlgHsP6gE7YBp7t2eAAAAAElFTkSuQmCC") no-repeat left center transparent;
    background-size: 100% 100%;
}

#sub_btn{
  background-color:#2e2e2e;
  width: 90px;
  border: 0;
  padding: 10px 0;
  text-align: center;
  color: #fff;
  font-weight: bold;
  transition: all 0.5s;
  border-radius: 6px;
  float: right;
  margin: 10px 15px;
}

#sub_btn:hover{
  background-color:#2ebc3f;
  width: 90px;
  border: 0;
  padding: 10px 0;
  text-align: center;
  color: #fff;
  font-weight: bold;
  margin: 8px 15px 0px 15px;
 
}

#sub_reset{
  background-color:#2e2e2e;
  width: 90px;
  border: 0;
  padding: 10px 0;
  text-align: center;
  color: #fff;
  font-weight: bold;
  transition: all 0.5s;
  border-radius: 6px;
  float: right;
  margin-top: 9px;
}

#sub_reset:hover{
  background-color: #ff0000ad;
  width: 90px;
  border: 0;
  padding: 10px 0;
  text-align: center;
  color: #fff;
  font-weight: bold;
  margin:8px 0px;
 
}

.form_sub{
	background-color: #f0ffff00;
    width: 100%;
    height: 64px;
}

.title{
	border: solid darkgrey;
    border-radius: 7px;
    font-size: 23px;
    height: 58px;
    padding: 0px 0px 0px 39px;
    font-weight: bold;
    width:100%;
    background-color: gray !important !important;
}

.ajax_tag{
	margin: -4px 0px 3px -160px;
    width: 300px;
    height: 40px;
    background: white;
    color: gray;
    border-radius: 12px;
    padding: 9px 0px 7px 5px;
    font-weight: bold;
    font-size: 18px;
    border: solid darkgrey;
}
/* content end*/
</style>

</head>
<body>

	<jsp:include page="/WEB-INF/common/signup.jsp" flush="true" />

	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true" />


			<div class="col-lg-12 contentbox">

<FORM action="<%=application.getContextPath() %>/article/article.do" method="post" enctype="multipart/form-data" >
				<div id="centerbox" class="container-fluid m-auto">
					<div class="col-lg-12 cb">
					<div class="row">
					
					<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
					
					
					    <input type="text"  name="article_title" class="title" placeholder="請輸入文章標題" value='<%=(articleVO==null)? "" : articleVO.getArticle_title()%>'/>
						
					</div>
					
					
						<div class="row">
							<div class="col-7">
								<img src="" class="show_image" alt="">
								<div class="change_div">
									<div class="change">
									<input id="blur" type="image"
										src="<%=request.getContextPath()%>/assets/images/article/blur.png" onclick='return false;'  ><p>blur</p>
								</div>
								<div class="change">
									<input id="grayscale" type="image"
										src="<%=request.getContextPath()%>/assets/images/article/grayscale.png" onclick='return false;'><p>grayscale</p>
								</div>
								<div class="change">
									<input id="hueRotate" type="image"
										src="<%=request.getContextPath()%>/assets/images/article/grayscale.jpg" onclick='return false;'><p>hueRotate</p>
								</div>
								<div class="change">
									<input id="invert" type="image"
										src="<%=request.getContextPath()%>/assets/images/article/invert.png" onclick='return false;'><p>invert</p>
								</div>
								<div class="change">
									<input id="sepia" type="image"
										src="<%=request.getContextPath()%>/assets/images/article/Sepia.png" onclick='return false;'><p>sepia</p>
								</div>
								<div class="change">
									<input id="brightness" type="image"
										src="<%=request.getContextPath()%>/assets/images/article/brightness.jpg" onclick='return false;'><p>brightness</p>
								</div>
								<div class="change">
									<input id="contrast" type="image"
										src="<%=request.getContextPath()%>/assets/images/article/contrast.jpg" onclick='return false;'><p>contrast</p>
								</div>
								<div class="change">
									<input id="opacity" type="image"
										src="<%=request.getContextPath()%>/assets/images/article/opacity.png" onclick='return false;'><p>opacity</p>
								</div>
								<div class="change">
									<input id="saturate" type="image"
										src="<%=request.getContextPath()%>/assets/images/article/saturate.png" onclick='return false;'><p>saturate</p>
								</div>
					
								<div class="col-8 change_btn">
									<input name="blur" class="bar" type="range" min="0" max="100"
										value="0" onclick='return false;'>
									<script
										src="<%=request.getContextPath()%>/assets/images/article/ChangeInput.js"
										type="text/javascript"></script>
								</div>
								<div class="col-4 change_btn">
									<button class="btnn" type="button">確定</button>
									<button>取消</button>
								</div>
					</div>
								
							</div>
							
			
							<div class="col-5">
							
								<ul>
									<li>
										<div class="input_div1 item">
											<i class="fa fa-camera"></i> 
											<input type="file"accept="image/*" id="file_input1" name="article_image" /> 
											<img src='<%=(articleVO==null)? "" : articleVO.getArticle_image()%>' id="img_1" alt="" style="${articleVO.image_css}">
										</div>
									</li>
									<li>

										<div class="input_div2 item">
											<i class="fa fa-camera"></i> 
											<input type="file" accept="image/*" id="file_input2" name="article_image_2" /> 
											<img src='<%=(articleVO==null)?  "" : articleVO.getArticle_image_2()%>' id="img_2" alt="" >
												</div>
									</li>
									<li>
										<div class="input_div3 item">
											<i class="fa fa-camera"></i> 
											<input type="file" accept="image/*" id="file_input3" name="article_image_3" /> 
											<img src='<%=(articleVO==null)? "" : articleVO.getArticle_image_3()%>' id="img_3" alt=""> 
										</div>
									</li>
								</ul>
							
							</div>
							
				
						</div>
						

							

						<div class="row">
							<div id="article">

								<textarea class="ckeditor" name="article_content">
									<%=(articleVO==null)? "" : articleVO.getArticle_content()%>
								</textarea>

							</div>
						</div>

						<div class="row">
							<div class="col ajax_div">
								<c:set var="tag_string" value="${fn:split(articleVO.tag, ',')}"/>
								<c:if test = "${tag_string[0]!=null}"> 
								<input class="button_tag" type="text" name="tag" value="${tag_string[0]}" />
								</c:if>
								<c:if test = "${tag_string[1]!=null}"> 
								<input class="button_tag" type="text" name="tag" value="${tag_string[1]}" />
								</c:if>
								<c:if test = "${tag_string[2]!=null}"> 
								<input class="button_tag" type="text" name="tag" value="${tag_string[2]}" />
								</c:if>
								<c:if test = "${tag_string[3]!=null}"> 
								<input class="button_tag" type="text" name="tag" value="${tag_string[3]}" />
								</c:if>
								<c:if test = "${tag_string[4]!=null}"> 
								<input class="button_tag" type="text" name="tag" value="${tag_string[4]}" />
								</c:if>
								<c:if test = "${tag_string[5]!=null}"> 
								<input class="button_tag" type="text" name="tag" value="${tag_string[5]}" />
								</c:if>
							</div>
						</div>



					</div>
					
					<div class="row">
						<div class="col-6" style="margin: auto; float:left;">    
						<input type="text" class="ajax_tag"  placeholder="請輸入標籤，可增加搜尋次數" onkeypress="if (window.event.keyCode==13) return false;">
						
						</div>
						
						
						<div class="col-6 form_sub">
						<button id="sub_reset">取消修改</button>
						<button type="submit" id="sub_btn">確認修改</button>
						<input type="hidden" name="like_c" value="${articleVO.like_c}" />
						<input type="hidden" name="watched_c" value="${articleVO.watched_c}" />
						<input type="hidden" name="member_id" value="${articleVO.member_id}" />
						<input type="hidden" name="article_id" value="${articleVO.article_id}" />
						<input type="hidden" class="imgcss"  name="image_css" />
						<input type="hidden" name="action" value="update_final" />
						
						</div>
					</div>
						
				</div>
</FORM>
			</div>


			<jsp:include page="/WEB-INF/common/footer.jsp" flush="true" />

		</div>
	</div>
	

	<!-- JS -->
	<script type="text/javascript"
		src="<%=application.getContextPath()%>/assets/javascripts/index.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

	<!-- JS自訂 -->
<script type="text/javascript">
	 
/*------------------------ tag新增標籤 1 ------------------------------ */	 
	 $('.ajax_tag').keydown(function(event){
		 if(event.which == 13){
		 var tag1 = $('.ajax_tag').val();
		 var tag = "<input class='button_tag' type='text' name='tag' value="+tag1+">"
			 $('.ajax_div').append(tag);
			 $('.ajax_tag').val("");
		 }
	  });
	 
</script>




<script type="text/javascript">
var btn_short = 0;
var btn_tall = 0;
var changeName;
var blur = 0;
var brightness = 100;
var contrast = 100;
var grayscale = 0;
var hueRotate = 0;
var invert = 0;
var opacity = 100;
var saturate = 100;
var sepia = 0;

$(document).ready(function() {
  $(".container-fluid").on("input", update);//當你使用到input
});

$(".change input").click(function(){ 
  changeName = $(this).attr("id");

  $(".change_btn").css("display","inline");
});

$("button").click(function(){
  $(".change_btn").hide();
 
});

function update(ctrl) {
  
  btn_short = $(".bar").val();

//目前狀態可融合，但希望每按到一個值都能歸零
  if(changeName == 'blur'){
    blur = btn_short; 
  }else if(changeName == 'brightness'){
    brightness = btn_short;
  }else if(changeName == 'contrast'){
    contrast = btn_short;
  }else if(changeName == 'grayscale'){
    grayscale = btn_short;
  }else if(changeName == 'hueRotate'){
    hueRotate = btn_short;
  }else if(changeName == 'invert'){
    invert = btn_short;
  }else if(changeName == 'opacity'){
    opacity = btn_short;
  }else if(changeName == 'saturate'){
    saturate = btn_short;
  }else if(changeName == 'sepia'){
    sepia = btn_short;
  }


  $(".show_image").css(
    "filter",'blur('+blur+'px) brightness('+brightness+'%) contrast('+contrast+'%) grayscale('+grayscale+'%) hue-rotate('+hueRotate+'deg) invert('+invert+'%) opacity('+opacity+'%) saturate('+saturate+'%) sepia('+sepia+'%)'
  );

}


$('.btnn').mouseleave(function(e) {
		console.log(btn_short);
	$(".bar").val("0");
		console.log(btn_short);
		
	});

</script>

	<script type="text/javascript">
		$('#file_input1').change(function() {
			var file = $('#file_input1')[0].files[0];
			var reader = new FileReader;
			reader.onload = function(e) {
				$('.input_div1 img').attr('src', e.target.result);
			};
			reader.readAsDataURL(file);
		});

		$('#file_input2').change(function() {
			var file = $('#file_input2')[0].files[0];
			var reader = new FileReader;
			reader.onload = function(e) {
				$('.input_div2 img').attr('src', e.target.result);
			};
			reader.readAsDataURL(file);
		});

		$('#file_input3').change(function() {
			var file = $('#file_input3')[0].files[0];
			var reader = new FileReader;
			reader.onload = function(e) {
				$('.input_div3 img').attr('src', e.target.result);
			};
			reader.readAsDataURL(file);
		});
	</script>


	<script type="text/javascript">
	
	var img_name;
	
		$(function() {
			$("div.item img").click(function() {
			var this_tag = $(this).attr("id");
			img_name = "#"+this_tag;
				var $imgSrc = $(this).attr('src');
				$(".show_image").attr('src', $imgSrc);

				

			});
		})

		$('.btnn').mousedown(function(e) {
			$('.show_image').attr('src','');
			var $imgSrc = $(".show_image").attr('style');
			alert($imgSrc);
			$('input[name="image_css"]').val($imgSrc);
			console.log();
			$(img_name).attr('style', $imgSrc);
			$('.show_image').attr('style','');			
			img_name = '';
		
		})
		
		
	</script>


	<script>
		function processData() {
			var data = CKEDITOR.instances.content.getData()
			alert(data);
		}
	</script>





	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

	<script type="text/javascript" src="<%=request.getContextPath()%>/vendor/assets/stylesheets/ckeditor/ckeditor.js"></script>
</body>
</html>