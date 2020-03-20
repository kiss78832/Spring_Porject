<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.spring.application.model.*"%>
<%@page import="com.spring.member.model.*"%>
<%@page import="com.spring.group.model.*"%>
<%@page import="com.spring.info.model.*"%>
<%@page import="com.spring.article.model.*"%>
<%@page import="com.spring.message.model.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 



<% 
	ArticleService articleSvc = new ArticleService();
	pageContext.setAttribute("articleSvc",articleSvc);
	String article_id = (String)request.getAttribute("article_id");
	
	ArticleVO articleVO = articleSvc.getOneArticle(article_id);
	pageContext.setAttribute("articleVO", articleVO);
	
	MemberService mebSvc = new MemberService();
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	memberVO = mebSvc.getOneMember(memberVO.getMember_id());
	pageContext.setAttribute("memberVO", memberVO);
	pageContext.setAttribute("mebSvc", mebSvc);

	
	MessageService messageSvc = new MessageService();
	List<MessageVO> list = messageSvc.getOne_Msg(article_id);
	pageContext.setAttribute("list", list);
	
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/x-icon"
	href="https://i.imgur.com/J0aPP1N.png" />
<title>共用頁面</title>
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


/*centerbox start*/


.contentbox{
  margin-top: 80px;
  box-sizing: border-box;
  background-image: url(/DA102G1/assets/images/snowpeak/forest-1743206.jpg);
  background-size: cover;
	height: 120%;
	padding: 0px;
  

}
.contentbox .cb{/*contentbox 底下的div*/
  display: inline-block;
  position: relative;
}

/*centerbox start*/
#centerbox{
width: 90%;
background-color: rgba(52,53,55,0.7);
border: 1px solid rgba(150,150,150,0.5);
height: 1250px;
}
#centerbox div{

}



#centerbox {
	width: 60%;
	background-color: #000000;
	/*border: 1px solid rgba(150,150,150,0.5);*/
	height: 120%
}

.message_head {
	width: 907px;
    margin: 49px 0px;
}

.message_head_p {
	text-align: left;
    background: #393c37eb;
    padding: 9px 11px;
    font-size: 17px;
    width: 848px;
    margin-left: 17px;
    border: 1px solid white;
}

#message_text {
    height: 100px;
    width: 100%;
    margin-bottom: 7px;
    background: white;
}

.form-group_msg {
	margin: 0px;
	text-align: left;
    padding: 13px 59px 45px 59px;
}

.message_btn {
	float: right;
    margin-top: 5px;
    border: 1px solid;
    background: none;
    color: white;
    border-radius: 5px;
    transition: all 0.5s;
}

.message_btn:hover {
	box-shadow: -4px 3px 5px 0px;
	margin-top:3px;
	transition: all 0.5s;

}

.head_top {
    height: 143px;
    background: indianred;
    font-size: 30px;
    margin: 45px 17px 0px -49px;
    display: grid;
    text-align: left;
}

.message_text {
	height: 100%;
	background: gray;
	margin: 26px 27px 0px 27px;
	min-height: 300px;
}

.message_text p {
    width: 763px;
    text-align: left;
    margin: 0px 15px;
    padding: 10px;
}

.carousel-inner img {
	height: 490px;
	border-radius: 10px;
}
/* content end*/
.head_top div:nth-child(1) {
	font-size: 21px;
    padding: 9px 2px 0px 0px;
    letter-spacing: 5px;
    border-bottom: groove;
    height: 40px;
	
}

.head_top div:nth-child(2) {
	font-size: 25px;
	padding: 28px 0px;
	float: left;
	height: 93px;
}

.slide {
	left: 75px;
}

.like {
	background: gray;
	    margin: 0px 27px 61px 27px;
}

.message-context {
    height: 166px;
    background: #fffff5;
    width: 99.5%;
    margin: 0px 69px -3px 2px;
    color: black;
    height: 100px;
	margin-left: 3px;
}

.style-one {
	border: 0;
	height: 1px;
	background: #333;
	background-image: linear-gradient(to right, #333, #ccc, #333);
}

hr.style-two {
	border: 0;
    height: 0;
    background: azure;
    border-top: 1px solid rgba(0, 0, 0, 0.1);
    border-bottom: 1px solid rgba(255, 255, 255, 0.3);
    margin: 6px 0px 3px 0px;
}

.msg_time {
    text-align: right;
    padding: 0px 29px 0px 0px;
}

.report_btn {
    border: none;
    margin: 0px 19px;
    position: absolute;
    top: 143px;
    right: 134px;

    background: floralwhite;
    padding: 6px 12px;
    border-radius: 10px;
    box-shadow: 2px 4px 4px 2px;

}

.hashtag{
	text-align:left;
}

.report_topbtn{
	margin: 0px 0px 0px 177px;
    text-align: right;
}

.report_topbtn button{
    font-size: 30px;
    border: none;
    background: black;
    color: white;
    font-weight: bold;
}

.message-body {
    margin-left: 17px;
    width: 848px;
    border: 1px solid white;
}

.row.msg_context {
	margin: 0px -7px 1px -7px;
    color: cadetblue;
    padding: 0px 0px 0px 100px;
}

.msg_name{
	text-align: left;
}

span.msg_count {
    float: right;
}

.head_top span{
	float:right;
}
/*修改*/
.img_icon{
	width: 69px;
    border-radius: 60px;
    position: absolute;
    top: 41px;
    right: 144px;
}

i.fas.fa-heart {
    padding: 0px 44px;
}

.msg_photo{
	width: 55px;
    height: 55px;
    border-radius: 68px;
    border: 5px solid paleturquoise;
}

</style>

</head>
<body>

	<jsp:include page="/WEB-INF/common/signup.jsp" flush="true" />

	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true" />



			<div class="col-lg-12 contentbox">


				<div id="centerbox" class="container-fluid m-auto">
					<div class="col-lg-12 cb">
					
						<div class="row">
							<div class="col-9 head_top">
								<div><fmt:formatDate value="${articleVO.article_time}" pattern="yyyy-mm-dd"/><span>編輯者:  ${mebSvc.getOneMember(articleVO.member_id).m_name}</span></div>
								<div>${articleVO.article_title}</div>
							</div>
							
							<div class="col-3 ">
							<div><a href="<%=application.getContextPath()%>/front-end/article/article_front.jsp"><input type="image" class="img_icon" src="<%=request.getContextPath()%>/assets/images/Icon/home1.png"></a></div>
								<form action="<%=application.getContextPath()%>/article/article.do" method="post">
									<button type="submit" class="report_btn" name="action" value="report">檢舉</button>
									<input type="hidden" name="member_id" value="A002" /> 
									<input type="hidden" name="article_id" value="${articleVO.article_id}" />
								</form>
								
							</div>
					
							<div class="col-10">
								<hr class="style-one" />
								<div id="carouselExampleIndicators" class="carousel slide"  data-interval="false" data-wrap="false" data-pause="false">
									<ol class="carousel-indicators">
										<li data-target="#carouselExampleIndicators" data-slide-to="0"
											class="active"></li>
										<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
										<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
									</ol>
									<div class="carousel-inner">
										<div class="carousel-item active">
											<img src="${articleVO.article_image}" class="d-block w-100" alt="" style="${articleVO.image_css}"> 
										</div>
										<div class="carousel-item">
											<img src="${articleVO.article_image_2}" class="d-block w-100" alt="">
										</div>
										<div class="carousel-item">
											<img src="${articleVO.article_image_3}" class="d-block w-100" alt="">
										</div>
									</div>
									<a class="carousel-control-prev"
										href="#carouselExampleIndicators" role="button"
										data-slide="prev"> <span
										class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									</a> <a class="carousel-control-next"
										href="#carouselExampleIndicators" role="button"
										data-slide="next"> <span
										class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									</a>
								</div>



							</div>
						</div>

						<div class="row-col-7 message_text">
							<p>
								${articleVO.article_content}
							</p>
						</div>

						<div class="row like">
							<div class="col-6 hashtag">
								<c:set var="tag_string" value="${fn:split(articleVO.tag, ',')}"/>
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
							</div>
							<div class="col-6">
							<i class="fas fa-heart">${articleVO.like_c}</i>
							<i class="fa fa-eye">${articleVO.watched_c}</i>
							<span class="msg_count">留言數( ${articleSvc.getArticleMsg(articleVO.article_id)} )</span>
							</div> 


						</div>
						<hr class="style-one" />
<c:forEach var="messageVO" items="${list}">
						<!-- 一組留言 -->
						<div class="row-col-5">
							<div class="message-context">
								<div class="row">
									<div class="col-8 msg_name">『 ${mebSvc.getOneMember(messageVO.member_id).m_name} 』：</div>
									
									<div class="col-4">
									<img class="msg_photo" src="<%= application.getContextPath() %>/member/m_photo2.get?member_id=${messageVO.member_id}">
									</div>
									
								</div>
								<div class="row msg_context">
									<span>${messageVO.msg_content}</span>
								</div>
								<div class="row msg_time">
									<div class="col-12"><fmt:formatDate value="${messageVO.msg_time}" pattern="YYYY-MM-dd hh:dd:ss"/></div>
								</div>
							</div>
						</div>
						<hr class="style-two" />
</c:forEach>					


						<div class="row">
							<div class="col2 message_head">
								<div class="message_head_p">
									<span> 留言標頭 </span>
								</div>
								<div class="message-body">
									<form action="<%=application.getContextPath() %>/message/message.do" method="post" id="message-form">
										<div class="form-group_msg">
											<label>Message:</label>
											<textarea id="message_text" type="text" class="form-control" name="msg_content"></textarea>
											<button class="message_btn" name="action" value="insert_msg">送出留言</button>
										</div>
										<input type="hidden"  name="member_id" value="${memberVO.member_id}" />
										<input type="hidden"  name="article_id" value="${articleVO.article_id}"  />
									</form>
								</div>




							</div>
						</div>



					</div>
				</div>

			</div>






			<jsp:include page="/WEB-INF/common/footer.jsp" flush="true" />

		</div>
	</div>

	<!-- JS -->
<script type="text/javascript">
$(document).ready(
	$('.report_btn').click(function(){
		alert('您已檢舉成功，將返回文章首頁');
	}));
	
</script>
	
	
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
</body>
</html>