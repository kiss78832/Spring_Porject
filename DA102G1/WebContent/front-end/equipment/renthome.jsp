<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.equipment.model.*"%>
<%@ page import="com.member.model.*"%>
<% 
 	EquipmentService equSvc = new EquipmentService();
 	List<EquipmentVO> list = equSvc.getAllnorepeat();
 	pageContext.setAttribute("list", list);
%>

<%	
	Vector<EquipmentVO> buylist = new Vector<EquipmentVO>();
	if(session.getAttribute("shoppingcart")!=null){
	buylist = (Vector<EquipmentVO>) session.getAttribute("shoppingcart");
	}
%>

<%-- <jsp:useBean id="equipmentDAO" scope="page" --%>
<%-- 	class="com.equipment.model.EquipmentDAO" /> --%>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/x-icon"
	href="https://i.imgur.com/J0aPP1N.png" />
<title>Rent Home</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- body&footer -->
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/assets/stylesheets/body.css">



<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />





<script type="text/javascript">
	function ShowInfo(number) {
		$('.showProduct').css('display', 'flex');
		$('.showProduct').addClass('show');

	};

	function close1234() {
		$('.showProduct').css('display', 'none');
		$('.showProduct').removeClass('show');
	};

	$(function() {
		$('#myTab li:last-child a').tab('show')
	})

	function showImage(a) {
		$.ajax({
			url : "/equipment/equipment.do",
			type : 'post',
			//data: {page: a},
			success : function(result) {
				// var a = parse(result);
				//var strHtml=''; 
				//for(int i=0;i<=a.length;i++){
				//strHtml+=;
				//strHtml+=;
			}
		//先刪除tag再新增

		//          $("#div1").html(strHtml);
		//}
		});
	}

	// }
</script>












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
	height: 100%;
	padding: 0px;
}

.contentbox .cb { /*contentbox 底下的div*/
	display: inline-block;
	position: relative;
}
/*左側區塊*/
#cleft {
	background-color: rgba(52, 53, 55, 0.2);
	border: 1px solid rgba(150, 150, 150, 0.5);
	height: auto;
    float: left;
    min-height: 1250px;
}

#cleft div {
/* 	border: 2px solid red; */
	margin: 15px auto;
}

/*右側區塊*/
#cright {
	background-color: rgba(72, 73, 75, 0.9);
	border: 1px solid rgba(150, 150, 150, 1);
	height: 100%;
	float: left;
}

#cright div {
	border: 2px;
}
/* content end*/
.show {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background-color: rgba(0, 0, 0, .2);
	justify-content: center;
	align-items: center;
	resize:both;
	overflow:auto;
}

.full-screen {
	margin-left:36%;
	width: 90%;
	max-width: 500px;
	background: #fff;
	padding: 20px;
	position: relative;
	border-radius: 10px;
	color: black;

}

.proinfo {
	position: absolute;
	left: 0px;
}

.proname {
	padding: 5px;
}

.card-cascade{
color:black;
display:inline-block;
width:100;
height:100;
}


.demo{padding:45px 0}
.product-grid2{font-family:'Open Sans',sans-serif;position:relative}
.product-grid2 .product-image2{overflow:hidden;position:relative}
.product-grid2 .product-image2 a{display:block}
.product-grid2 .product-image2 img{width:100%;height:250px; border-radius:20px}
.product-image2 .pic-1{opacity:1;transition:all .5s}
.product-grid2:hover .product-image2 .pic-1{opacity:0.5}
.product-image2 .pic-2{width:100%;height:100%;opacity:0;position:absolute;top:0;left:0;transition:all .5s}
.product-grid2:hover .product-image2 .pic-2{opacity:1}
.product-grid2 .social{padding:0;margin:0;position:absolute;bottom:50px;right:25px;z-index:1}
.product-grid2 .social li{margin:0 0 10px;display:block;transform:translateX(100px);transition:all .5s}
.product-grid2:hover .social li{transform:translateX(0)}
.product-grid2:hover .social li:nth-child(2){transition-delay:.15s}
.product-grid2:hover .social li:nth-child(3){transition-delay:.25s}
.product-grid2 .social li a{color:#505050;background-color:#fff;font-size:17px;line-height:45px;text-align:center;height:45px;width:45px;border-radius:50%;display:block;transition:all .3s ease 0s}
.product-grid2 .social li a:hover{color:#fff;background-color:#3498db;box-shadow:0 0 10px rgba(0,0,0,.5)}
.product-grid2 .social li a:after,.product-grid2 .social li a:before{content:attr(data-tip);color:#fff;background-color:#000;font-size:12px;line-height:22px;border-radius:3px;padding:0 5px;white-space:nowrap;opacity:0;transform:translateX(-50%);position:absolute;left:50%;top:-30px}
.product-grid2 .social li a:after{content:'';height:15px;width:15px;border-radius:0;transform:translateX(-50%) rotate(45deg);top:-22px;z-index:-1}
.product-grid2 .social li a:hover:after,.product-grid2 .social li a:hover:before{opacity:1}
.product-grid2 .add-to-cart{color:#fff;background-color:#404040;font-size:15px;text-align:center;width:100%;padding:10px 0;display:block;position:absolute;left:0;bottom:-100%;transition:all .3s}
.product-grid2 .add-to-cart:hover{background-color:#3498db;text-decoration:none}
.product-grid2:hover .add-to-cart{bottom:0}
.product-grid2 .product-new-label{background-color:#3498db;color:#fff;font-size:17px;padding:5px 10px;position:absolute;right:0;top:0;transition:all .3s}
.product-grid2:hover .product-new-label{opacity:0}
.product-grid2 .product-content{padding:20px 10px;text-align:center}
.product-grid2 .title{font-size:17px;margin:0 0 7px}
.product-grid2 .title a{color:black}
.product-grid2 .title a:hover{color:#3498db}
.product-grid2 .price{color:black;font-size:15px}
@media screen and (max-width:990px){.product-grid2{margin-bottom:30px}
}



.product-grid2 .title a {color:white}
.product-grid2 .price {color:white}

  .vcontainter{
/*     margin-top: 80px; */
    background-color: rgba(52,53,55,0.7)

  }

.video-container {
/*   	padding: 15px; */
    height: 30vh;
    position: relative;
    overflow: hidden;
    /*background-color: rgba(0,0,0,1)*/
    /*background-image:url("snowpeak/lake-sara-1892494.jpg");*/
    /*background-color: rgba(52,53,55,0.7);*/
}
video {
    object-fit: cover;
    /*position: absolute;*/
    height: 100%;
    width: 100%;
    top: 0;
    left: 0;
}

.callout {
position: absolute;
    display: inline-block;
    text-align: center;
    color: black;
    right: 0;
    left: 25%;
    top: 25%;
/*     background-color: rgba(180,180,180,0.3); */
    width: 44%;
    border-radius: 20px;
}

.list-group-item{
	position: relative;
	display: block;
	padding: .75rem 1.25rem;
	margin-bottom: -1px;
	background-color: rgba(50,50,50,0.5);
	border: 1px solid rgba(0,0,0,.125);
	color: white;
}

.breadcrumb{
	display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    padding: .75rem 1rem;
    margin-bottom: 1rem;
    list-style: none;
    background-color: rgba(110,110,111,0.8);
    border-radius: .25rem;
}



</style>

</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/common/navbar.jsp" flush="true" />

			<div class="col-lg-12 contentbox">


				<div id="cleft" class="col-lg-2 col-md-2 cb">
					<!-- 左側區塊 可放按鈕等等 -->






					<div class="list-group">
						<a href="<%=request.getContextPath()%>/front-end/equipment/renthome.jsp" class="list-group-item list-group-item-action">全部商品</a>
						 <a href="<%=request.getContextPath()%>/front-end/eqtypetotal/tent.jsp" class="list-group-item list-group-item-action">帳篷</a> 
						 <a href="<%=request.getContextPath()%>/front-end/eqtypetotal/trekkingPole.jsp" class="list-group-item list-group-item-action">登山杖</a>
						  <a href="#" class="list-group-item list-group-item-action">睡袋</a>
						   <a href="#"class="list-group-item list-group-item-action">配件</a>
					</div>



				</div>
				<div id="cright" class="col-lg-10 col-md-10 cb">


  <div class="vcontainter">
    <section class="video-container container">
    <video src="https://player.vimeo.com/external/181445574.hd.mp4?s=d24f32d879305e931468d55e4d7ce6efb5a95c39&amp;profile_id=119" autoplay loop muted></video>
    <div class="callout">
      <h1>Island Peak</h1>
      <div>Find where you truly belong</div>
    </div>
  </section>

</div>

					<div class="path">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="">全部商品 &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
								 &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
								  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
								   &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
								    &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
								     &nbsp &nbsp &nbsp </a></li>
								<li ><a href="">購物車數量(<%= buylist.size() %>)</a></li>
							</ol>
						</nav>
					</div>



<div class="container">
    <div class="row">

    	<c:forEach var="equVO" items="${list}">
        <div class="col-md-3 col-sm-6">
            <div class="product-grid2">
                <div class="product-image2">
                    <a href="#">
                        <img class="pic-1" src="<%=request.getContextPath()%>/equipment/DBGifReader4.do?eq_num=${equVO.eq_num}">
                    </a>
					 <a class="add-to-cart" href="<%=request.getContextPath()%>/equipment/equipment.do?eq_num=${equVO.eq_num}&requestURL=<%=request.getServletPath()%>&action=getOne_From06">Add to cart</a>
                </div>
                <div class="product-content">
                    <h3 class="title"><a href="#">商品名稱:${equVO.eq_brand} ${equVO.eq_name}</a></h3>
                    <span class="price">價錢 : ${equVO.eq_price}</span>
                </div>
            </div>
        </div>
</c:forEach>
    </div>
</div>


				</div>

			</div>


			<jsp:include page="/common/footer.jsp" flush="true" />


		</div>
	</div>

















<c:if test="${openModal!=null}">


		<jsp:include page="listOneEqu.jsp" />







		</div>
		</div>



</c:if>





















	<!-- JS -->
	<script type="text/javascript"
		src="<%=application.getContextPath()%>/assets/javascripts/index.js"></script>
	<script type="text/javascript"
		src="<%=application.getContextPath()%>/assets/javascripts/sideBar.js"></script>

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