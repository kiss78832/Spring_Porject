<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
  <title>Island Peak</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  
  <!-- toaststr -->
  <link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.css">

  <script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
	
	


  <!-- JS -->
  <script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/ResIndex.js"></script>
  <!-- body&footer -->

  <!-- navbar -->
 
  <!-- page design and font -->
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/ResIndex.css">
  <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
  <!-- ======= -->
  <style type="text/css">
      /*本地字體引入*/
  @font-face{
    font-family: Russo;
    src:url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
     unicode-range: U+00-024F;
  }
  @font-face{
    font-family: Noto;
    src:url('/DA102G1/assets/fonts/NotoSansTC-Black.otf');
    unicode-range: U+4E00-9FFF;
  }



    /* content start*/

    .contentbox {
      margin-top: 80px;
      box-sizing: border-box;
      background-image: url("/DA102G1/assets/images/snowpeak/forest-1743206.jpg");
      background-size: cover;
      height: auto;
      padding: 0px;


    }

    .contentbox .cb {
      /*contentbox 底下的div*/
      display: inline-block;
      position: relative;
    }

    /*centerbox start*/
    #centerbox {
      width: 90%;
      background-color: rgba(52, 53, 55, 0.7);
      border: 1px solid rgba(150, 150, 150, 0.5);
      height: auto;
      padding:0px;
    }

    /* #centerbox div{
  margin-top: 15px;
  border: 2px solid red;
} */
    /*centerbox end*/


    /* content end*/
    
    
    
  /*------背景及選項圖片更換------*/
.bgPic{
	background-image: url("/DA102G1/assets/images/resIndex/resIndex1.jpg");
	margin: 0;
	padding: 25px;
	width:100%;
	height: auto;
    min-height: 1000px;
	background-size: 100% 100%;
	/*背景影像縮放 auto px % contain*/
	background-repeat: no-repeat;
	/* background-attachment: fixed; */
	transition: all 1s ease-in-out;
/* 	 opacity: 0.5; */
}

.chosePage1{
	border:1px solid seashell; 
	height: 20%;
	display: inline-block;
	text-align: center;
	font-size:35px;
	background-image: url("/DA102G1/assets/images/resIndex/resIndex2.jpg");
	margin-top: 15% ;
	padding:0;
	width:90%;
	background-size: 100% 100%;
	background-repeat: no-repeat;
	transition: all 0.5s ease-in-out;
	opacity: 0.8;
	border-radius: 25px;
	margin-right:20px;
    margin-bottom: 20px;
}
.chosePage1:hover{
	background-image: url("/DA102G1/assets/images/resIndex/resIndex11.jpg");
	background-size: 100% 100%;
	transition: all 0.5s ease-in-out;
	cursor: pointer;
	border: 3px solid black;
	opacity: 1.0;
}

.chosePage2{
	border:1px solid seashell; 
	height: 20%;
	display: inline-block;
	text-align: center;
	font-size:35px;
	background-image: url("/DA102G1/assets/images/resIndex/resIndex5.jpg");
	margin-top: 15% ;
	padding:0;
	width:90%;
	background-size: 100% 100%;
	background-repeat: no-repeat;
	transition: all 0.5s ease-in-out;
	border-radius: 25px;
	opacity: 0.8;
	margin-bottom: 20px;
}

.chosePage2:hover{
	background-image: url("/DA102G1/assets/images/resIndex/resIndex6.jpg");
	background-size: 100% 100%;
	transition: all 0.5s ease-in-out;
	cursor: pointer;
	border: 3px solid black;
	opacity: 1.0;
}


.chosePage3{
	border:1px solid seashell; 
	height: 20%;
	display: inline-block;
	text-align: center;
	font-size:35px;
	background-image: url("/DA102G1/assets/images/resIndex/resIndex7.jpg");
	margin-top: 15% ;
	padding:0;
	width:90%;
	background-size: 100% 100%;
	background-repeat: no-repeat;
	transition: all 1s ease-in-out;
	border-radius: 25px;
	margin-left:20px ;
	opacity: 0.8;
	margin-bottom: 20px;
}

.chosePage3:hover{
	background-image: url("/DA102G1/assets/images/resIndex/resIndex3.jpg");
	background-size: 100% 100%;
	transition: all 0.5s ease-in-out;
	cursor: pointer;
	border: 3px solid black;
	opacity: 1.0;
}
    
    
  </style>

</head>

<body>
  <div class="container-fluid">
    <div class="row">
<jsp:include page="/common/navbar.jsp" flush="true"/>

      <div class="col-lg-12 contentbox">


        <div id="centerbox" class="container m-auto">
          <!-- 要放的內容 -->
          <div class=" bgPic col-lg-12 cb">
            <div class="titleWord">
              To see the world, things dangerous to come to, to see behind walls, to draw closer, to find each other
              and to feel. That is the purpose of life.
            </div>
            
            <a href="<%= application.getContextPath() %>/front-end/dailyBed/Reserved_Query.jsp">
            <div class="chosePage1 col-lg-3" ><p>床位瀏覽</p></div>
            </a>
            <a href="<%= application.getContextPath() %>/front-end/meal/Meal_Query.jsp">
            <div class="chosePage2 col-lg-3"><p>套餐瀏覽</p></div>
            </a>
            <a href="<%= application.getContextPath() %>/front-end/room_order/Reserved_Bed.jsp">
            <div class="chosePage3 col-lg-3"><p>預訂床位</p></div>
            </a>
          </div>
        </div>

      </div>






<jsp:include page="/common/footer.jsp" flush="true"/>
    </div>
  </div>

	<script>
	$(function(){

		  $('.chosePage1').hover(function(){
		    $('.bgPic').css('background-image','url("<%=request.getContextPath()%>/assets/images/resIndex/resIndex1.jpg")');
		  });

		  $('.chosePage2').hover(function(){
		    $('.bgPic').css('background-image','url("<%=request.getContextPath()%>/assets/images/resIndex/resIndex9.jpg")');
		  });

		  $('.chosePage3').hover(function(){
		    $('.bgPic').css('background-image','url("<%=request.getContextPath()%>/assets/images/resIndex/resIndex10.jpg")');
		  });
		  
		  
// 		  $('.chosePage1').on('click',function(){
// 			  window.location='${pageContext.request.contextPath}/front-end/dailyBed/Reserved_Query.jsp';
// 		  });
		  
// 		  $('.chosePage2').on('click',function(){
// 			  window.location='${pageContext.request.contextPath}/front-end/meal/Meal_Query.jsp';
// 		  });
		  
// 		  $('.chosePage3').on('click',function(){
// 			  window.location='${pageContext.request.contextPath}/front-end/room_order/Reserved_Bed.jsp';
// 		  });
		  
		  
		});
	
	
	var MyPoint = "/Meal/ResIndex";

	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0,path.indexOf('/',1));
	var endPointURL = "wss://"+window.location.host + webCtx + MyPoint;

	var webSocket;

	function connect(){
		webSocket = new WebSocket(endPointURL);
		
		
		webSocket.onopen = function(event) {
			
		};

		webSocket.onmessage = function(event) {
			
			var JSONobj = JSON.parse(event.data);
			
			var action = JSONobj.action;
			
			toastr.options = {
			  "closeButton": true,
			  "debug": false,
			  "newestOnTop": false,
			  "progressBar": true,
			  "positionClass": "toast-top-right",
			  "preventDuplicates": false,
			  "onclick": null,
			  "showDuration": "300",
			  "hideDuration": "5000",
			  "timeOut": "10000",
			  "extendedTimeOut": "10000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
			
			if("insertMeal"=== action){
			toastr.info('<a href="<%= application.getContextPath() %>/front-end/meal/Meal_Query.jsp">'+'新增了一份套餐，快來看看吧!',JSONobj.meal_name +'   售價:'+JSONobj.meal_price+'</a>');
			}
			
			if("updateMeal"=== action){
				toastr.info('<a href="<%= application.getContextPath() %>/front-end/meal/Meal_Query.jsp">'+'更新了一份套餐，快來看看吧!',JSONobj.meal_name+'</a>');
			}
			
			
		};

		webSocket.onclose = function(event) {
		
		};
	}

	function disconnect() {
		webSocket.close();
	}

	$(function(){
		connect();	
	});

	$( window ).unload(function() {
		disconnect();
	});
	
	
	
	
	</script>



  <script>

    $(document).ready(function () {
      $('#test1').keyup(function () {
        $('#test2').text($('#test1').val());
      })
    })
    var coll = $(".collapsible");
    var i;

    for (i = 0; i < coll.length; i++) {
      coll[i].addEventListener("click", function () {
        this.classList.toggle("active");
        var content = this.nextElementSibling;
        if (content.style.display === "block") {
          content.style.display = "none";
        } else {
          content.style.display = "block";
        }
      });
    }
  </script>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"></script>
</body>

</html>