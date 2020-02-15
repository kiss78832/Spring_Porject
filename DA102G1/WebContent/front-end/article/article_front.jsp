<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.application.model.*"%>
<%@page import="com.group.model.*"%>
<%@page import="com.ati_report.model.*"%>
<%@page import="com.article.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<jsp:useBean id="mbrSvc" class="com.member.model.MemberService"/>
<%
	Ati_reportService atiSvc = new Ati_reportService();
	pageContext.setAttribute("atiSvc",atiSvc);
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll();
	pageContext.setAttribute("list", list);

	

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/x-icon"
	href="https://i.imgur.com/J0aPP1N.png" />
<title>Island Peak Article Index</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.9/css/all.css"
	integrity="sha384-5SOiIsAziJl6AWe0HWRKTXlfcSHKmYV4RBF18PPJ173Kzn7jzMyFuTtk8JA7QQG1"
	crossorigin="anonymous"></link>

<!-- body -->
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/assets/stylesheets/body.css">




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
	height: 1250px;
	padding: 0px;
}

.contentbox .cb { /*contentbox 底下的div*/
	display: inline-block;
	position: relative;
}

/*centerbox start*/
#centerbox {
	width: 90%;
	background-color: rgba(52, 53, 55, 0.7);
	border: 1px solid rgba(150, 150, 150, 0.5);
	height: 1250px;
}

#centerbox div {
/* 	margin-top: 1px; */
/* 	border: 0.5px solid red; */
}
/*centerbox end*/
.post-module {
	position: relative;
    z-index: 1;
    display: block;
    background: #FFFFFF;
    width: 340px;
    height: 457px;
    box-shadow: 10px 9px 8px 0px rgb(0, 0, 0);
    transition: all 0.3s linear 0s;
    border-radius: 20px 20px 3px 3px;
}

.post-module:hover {
	opacity:1;
}

.post-module .thumbnail {
    background: #000000;
    height: 248px;
    overflow: hidden;
    border-radius: 20px 20px 0px 0px;
}

.post-module .thumbnail .date {
	position: absolute;
	top: 20px;
	right: 20px;
	z-index: 1;
	background: #e74c3c;
	width: 55px;
	height: 55px;
	padding: 1px 0;
	-webkit-border-radius: 100%;
	-moz-border-radius: 100%;
	border-radius: 100%;
	color: #FFFFFF;
	font-weight: 700;
	text-align: center;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

/*日期*/
.post-module .thumbnail .date .day {
	font-size: 18px;
}

.post-module .thumbnail .date .month {
	font-size: 12px;
	text-transform: uppercase;
}

/* 控制文章下面區塊位置 */
.post-module .post-content {
	border-radius: 0 0 20px 20px;
	position: absolute;
	bottom: 65px;
	background: #FFFFFF;
	width: 100%;
	padding: 8px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	transition: all 0.3s;
	-moz-transition: all 0.3s cubic-bezier(0.37, 0.75, 0.61, 1.05) 0s;
	-ms-transition: all 0.3s cubic-bezier(0.37, 0.75, 0.61, 1.05) 0s;
	-o-transition: all 0.3s cubic-bezier(0.37, 0.75, 0.61, 1.05) 0s;
	transition: all 0.3s cubic-bezier(0.37, 0.75, 0.61, 1.05) 0s;
	height: 137px;
}

.post-module .post-content .category {
	position: absolute;
	top: -34px;
	left: 0;
	background: #e74c3c;
	padding: 10px 15px;
	color: #FFFFFF;
	font-size: 14px;
	font-weight: 600;
	text-transform: uppercase;
}

.post-module .post-content .title {
	margin: 4px 0px 0px 0px;
    padding: 22px;
    color: darkslateblue;
    font-size: 26px;
    font-weight: 800;
    text-shadow: 0 0 0.2em darkorange;
    text-align: left;
    background: #8deb874a;
    border-radius: 9px 9px 0px 0px;
}

.post-module .post-content .sub_title {
    margin: 0px;
    padding: 0;
    color: darkolivegreen;
    font-size: 13px;
    font-weight: 400;
    text-align: right;
    width: 100%;
    background: #f5d28491;
    padding: 0px 8px 0px 0px;
    border-radius: 0px 0px 6px 6px;
}

.post-module .post-content .description {
	display: none;
	color: #666666;
	font-size: 14px;
	line-height: 1.8em;
}

.post-module .post-content .post-meta {
	margin: 0;
	color: #999999;
	height: 30px;
	width: 319px;
	text-align: left;
}

.post-module .post-content .post-meta a {
	color: #999999;
	text-decoration: none;
}

 .post-content .description {
	display: block !important;
	height: auto !important;
	opacity: 1 !important;
}

/*容器*/
.container {
	max-width: 800px;
	min-width: 640px;
	margin: 0 auto;
}

.container:before, .container:after {
	content: '';
	display: block;
	clear: both;
}

.container .column {
	width: 50%;
	padding: 0 25px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	float: left;
}

.container .column .demo-title {
	margin: 0 0 15px;
	color: #666666;
	font-size: 18px;
	font-weight: bold;
	text-transform: uppercase;
}

.read:hover {
	opacity:1;
}



#carouselExampleIndicators {
	width: 105%;
	height: 97%;
	margin: auto;
	bottom: 16px;
}

span#hastag {
    top: 428px;
    position: absolute;
    float: unset;
    width: 300px;
    color: red;
    right: 22px;
    text-align: left;
}

.hashtage_search {
	float: left;
	width: 360px;
	height: 50px;
	padding: 0px 23px 0 10px;
	outline: 0;
	background: #fff;
	font-size: 26px;
	letter-spacing: 1px;
	font-family: monospace;
	font-style: italic;
}

.hashtag_submit {
	float: left;
	width: 122px;
	height: 50px;
	color: rgb(255, 255, 255);
	background: brown;
	line-height: 53px;
	letter-spacing: 6px;
	text-transform: uppercase;
	border: none;
}

.hashtag_form span {
	float: left;
	width: 53px;
	height: 50px;
	background: #4a4a4a7a;
	font-size: 30px;
	text-align: center;
	line-height: 50px;
}

.hashtag_form {
/* 	width: 539px; */
/*  	margin: 6px 0 6px 34%; */
 	margin:20px ;
	display: block;
}

.watch {
	text-align: end;
	color: blue;
}

.img_row img {
	width: 105%;
    margin: auto;
    height: 247px;
    border: dashed 6px dimgrey;
    border-radius: 19px 19px 0px 0px;
    padding: 12px;
}

.column {
    margin: 0px 14px 34px 27px;
}

h2{
	text-align: right;
}

.read {
	background-color: floralwhite;
    text-decoration: none;
    color: #000000a6;
    border-radius: 0.25rem;
    text-align: center;
    display: inline-block;
    transition: all 0.3s;
    width: 87px;
    border: 1px solid black;
    padding: 6px 0px 6px 0px;
    float: right;
    margin: 5px 0px 6px 0px;
    opacity: 0.6;
}
.img_row{
	padding:55px
}
/*新增*/
.Publish{
    width: 69px;
    position: absolute;
    top: 14px;
    left: 97px;
    padding: 6px;
    border: none;
    background: black;
    font-size: 25px;
    border-radius: 65px;
    opacity: 0.8;
}



/* content end*/
</style>

<script type="text/javascript">
$('.slide').carousel({
	  interval: false
	})
</script>


</head>
<body>

	<jsp:include page="/common/signup.jsp" flush="true" />

	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/common/navbar.jsp" flush="true" />



			<div class="col-lg-12 contentbox">


				<div id="centerbox" class="container-fluid m-auto">

					<div class="row">
				
					<div class="col-6">
						<a href="<%=application.getContextPath()%>/front-end/article/article_Edit.jsp">
							<input type="image" class="Publish" value="發表文章" src="<%=request.getContextPath()%>/assets/images/Icon/article_pen.jpg">
						</a>
						
					</div>
					
					
					<div class="col-6">
						<form class="hashtag_form" action="<%=application.getContextPath()%>/article/article.do" method="post">
							<span>#</span> 
								<input type="text" placeholder="Hashtag" class="hashtage_search" name="element"> 
								<input type="submit" class="hashtag_submit" value="Start">
								<input type="hidden" name="action" value="findtag">
						</form>
					
					</div>
				</div>
					<div class="row img_row">
						<c:forEach var="articleVO" items="${list}">
	<c:if test="${atiSvc.getOneAti(articleVO.article_id).report_status == 0 || atiSvc.getOneAti(articleVO.article_id).report_status == null  || atiSvc.getOneAti(articleVO.article_id).report_status == 1}">
							<div class="col-lg-4">

								<!-- Normal Demo-->
								<div class="column">
									<!-- Post-->
									<div class="post-module">
										<!-- Thumbnail-->
										<div class="thumbnail">
											<div class="date">
												<div class="day"><fmt:formatDate value="${articleVO.article_time}" pattern="dd"/></div>
												<div class="month"><fmt:formatDate value="${articleVO.article_time}" pattern="MMM"/></div>
											</div>
											<div id="${articleVO.article_id}" class="carousel slide"  data-interval="false" data-wrap="false" data-pause="false">
												<ol class="carousel-indicators">
													<li data-target="#${articleVO.article_id}"
														data-slide-to="0"  class="active"></li>
													<li data-target="#${articleVO.article_id}"
														data-slide-to="1"></li>
													<li data-target="#${articleVO.article_id}"
														data-slide-to="2"></li>
												</ol>
												<div class="carousel-inner">
													<div class="carousel-item active">

														<img src="${articleVO.article_image}"
															class="d-block w-100" alt="" style="${articleVO.image_css}">
													</div>
													<div class="carousel-item">
														<img src="${articleVO.article_image_2}"
															class="d-block w-100" alt="" style="">
													</div>
													<div class="carousel-item">
														<img src="${articleVO.article_image_3}" class="d-block w-100" alt="" style="">
													</div>
												</div>
												<a class="carousel-control-prev"
													href="#${articleVO.article_id}" role="button"
													data-slide="prev"> <span
													class="carousel-control-prev-icon" aria-hidden="true"></span>
													<span class="sr-only">Previous</span>
												</a> <a class="carousel-control-next"
													href="#${articleVO.article_id}" role="button"
													data-slide="next"> <span
													class="carousel-control-next-icon" aria-hidden="true"></span>
													<span class="sr-only">Next</span>
												</a>
											</div>

		
										</div>
										<!-- Post Content-->
										<div class="post-content">
											<div class="category">Photos</div>
											<h1 class="title">${articleVO.article_title}</h1>
											<h2 class="sub_title">編輯者:${mbrSvc.getOneMember(articleVO.member_id).m_name}</h2>

											<div class="row">
											
											<div class="col-12">
											<form action="<%=application.getContextPath()%>/article/article.do" method="post" >
												<button class="read" type="submit" name="action" value="read">詳細閱讀</button>
												<input type="hidden" name="article_id" value="${articleVO.article_id}">
<%-- 												<input type="hidden" name="member_id" value="${memberVO.member_id}"> --%>
											</form>
											</div>
												
											</div>

											<div class="row post-meta">
												<span class="col-6 timestamp">
													<div class="like liked" id="like_test">
														<i class="far fa-heart" style="cursor: pointer"
															id="${articleVO.article_id}_like"></i> <span
															class="like_count">${articleVO.like_c} <strong>
																likes</strong>
														</span>
													</div>
												</span>
												<div class="col-6 watch">
													<i class="fa fa-eye"></i> <span class="watch_count">${articleVO.watched_c}</span>
												</div>

											</div>


										</div>
										
										<c:set var="tag_string" value="${fn:split(articleVO.tag, ',')}"/>	
										<span id="hastag">
										
										  <c:if test = "${tag_string[0]!=null}"> 
											<a href="#">#${tag_string[0]}</a>
										  </c:if>
										  
									      <c:if test = "${tag_string[1]!=null}"> 
											<a href="#">#${tag_string[1]}</a>
										  </c:if>
										  
										  <c:if test = "${tag_string[2]!=null}"> 
											<a href="#">#${tag_string[2]}</a>
										  </c:if>
										  
										  <c:if test = "${tag_string[3]!=null}"> 
											<a href="#">#${tag_string[3]}</a>
										  </c:if>
										  
										  <c:if test = "${tag_string[4]!=null}"> 
											<a href="#">#${tag_string[4]}</a>
										  </c:if>
										   
										  <c:if test = "${tag_string[5]!=null}"> 
											<a href="#">#${tag_string[5]}</a>
										  </c:if>
										</span>
										

									</div>

								</div>
							</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>


			<jsp:include page="/common/footer.jsp" flush="true" />

		</div>
	</div>



	<!-- JS -->
	
<script type="text/javascript">
$(document).ready(function(){
 	$(".fa-heart").click(function(){
	 	var id = $(this).attr('id').split('_')[0];
	 	var color = $(this).css('color');
// 	 	console.log(color);
	 	var data;
	 	var $this = $(this);
	 	if(color === 'rgb(255, 0, 0)') {
		 	data = {action: "like_ajax", id: id, add: 'false'};
	 		$this.css('color', 'gray');
	        $this.attr({'class':'far fa-heart'});
	        $this.next('.like_count').html(Number($this.next('.like_count').text().split('likes')[0]) - 1 +" likes");
	 	} else {
	 		data = {action: "like_ajax", id: id, add: 'true'};
	 		$this.css('color', 'red');
	        $this.attr({'class':'fas fa-heart'});
	        $this.next('.like_count').html(Number($this.next('.like_count').text().split('likes')[0]) + 1 +" likes");
	 	}
		$.ajax({
			 type: "POST",
			 url:"<%=request.getContextPath()%>/article/article.do",
			 data: data,
			 dataType: "json",
			 success: function (data){
			 alert(data);
		     },
	         error: function(){
// 	           	alert("AJAX-class發生錯誤囉!")
// 	           	$this.css('color', 'red');
// 	           	$this.attr({'class':'fas fa-heart'});
           	}
       })
	})
})

</script>


	<script type="text/javascript" src="<%=application.getContextPath()%>/assets/javascripts/index.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>