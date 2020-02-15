<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>共用頁面</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<!-- body -->
	<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/body.css">


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



  /* content start*/

.contentbox{
  margin-top: 80px;
  box-sizing: border-box;
  background-image: url(/DA102G1/assets/images/snowpeak/forest-1743206.jpg);
  background-size: cover;
  height: 1250px;
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
  margin-top: 15px;
  border: 2px solid red;
}
/*centerbox end*/


  /* content end*/


	</style>

</head>
<body>
	
	<jsp:include page="/common/signup.jsp" flush="true"/>

	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/common/navbar.jsp" flush="true"/>
			
			

			<div class="col-lg-12 contentbox" >

			  
				<div id="centerbox" class="container-fluid m-auto">
					<div class="col-lg-12 cb">樣板 你要放的內容</div>
				</div>

			</div>






		  	<jsp:include page="/common/footer.jsp" flush="true"/>
		  
		</div>
	</div>

<!-- JS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>