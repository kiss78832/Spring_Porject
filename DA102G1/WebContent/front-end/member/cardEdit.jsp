<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*" %>

<%// prevent browser cache jsp     
  response.setHeader("Pragma", "No-cache"); // HTTP 1.0
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
  response.setDateHeader("Expires", -1); // proxies
%>
<jsp:useBean id="mSvc" class="com.member.model.MemberService"/>
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
memberVO = mSvc.getOneMember(memberVO.getMember_id());
pageContext.setAttribute("memberVO", memberVO);
%>
<!DOCTYPE html>
<html>
<head>
	<title>Island Peak</title>
	<meta charset="utf-8">
	<!-- 避免快取 -->
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/card.css">
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/cardEdit.css">
	<style type="text/css">

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

	<jsp:include page="/common/navbar.jsp" flush="true"/>
	
	<div class="container-fluid editcard">
		<div class="row col-lg-12 m-auto cardeditBG">
			<div class="cardEdit col-xl-6">
				<form action="<%= application.getContextPath() %>/member/member.do" method="POST"
				enctype="multipart/form-data">
					<h4>編輯名片</h4>
					
				<label>
					暱稱 :
					<input class="inputName" type="text" name="nick_name" size="30" maxlength="6" 
					placeholder="貝爾吉羅斯" value="${memberVO.nick_name}">
				</label>
				<br>
				<label>
					經歷 :<textarea class="expArea" cols="30" rows="5" placeholder="你的戶外經歷" name="outdoor_exp"
					 >${memberVO.outdoor_exp}</textarea>
				</label>	
				<br>
				<label>
					頭像 : <input type="file" name="m_photo" id="picPath">
				</label>
				<br>
				<label>
					名片背景 : <input type="file" name="back_img" id="bgPath">
				</label>
				<br>
				
					<input type="submit" class="sendCData" name="cardsubmit" value="Submit">
					<input type="hidden" name="action" value="CARDUPDATE">
					<a href="<%= application.getContextPath() %>/front-end/member/memberCard.jsp">
					<input type="button" class="cback" value="Back">
					</a>
				
					
				</form>
				

			</div>

			<div class="cardbox col-xl-6">

	<div class="col-lg-12 card_1">
		<img src="<%= application.getContextPath() %>/member/BG.get" class="BG">
		<div class="cphoto">
			<img src="<%= application.getContextPath() %>/member/m_photo.get" id="myPhoto">
		</div>

		<div class="nick_name">${memberVO.nick_name}</div>
		
		<textarea class="outdoor_exp">${memberVO.outdoor_exp}</textarea>
		
		
		
		<div class="ADPoint">
		   <span>攻略點數  :   </span><br>
		   <div class="ADRate">${memberVO.adventure_point}</div>
		</div>


		<div class="pie_chart">
			<span>攻略進度  :   </span>
		  <div class="chart" data-percent="${memberVO.raiders_rate}%">${memberVO.raiders_rate}%</div>
		</div>

	
	</div>

			</div>

		</div>


	</div>


<script type="text/javascript">
	$(document).ready(function(){


		$('#picPath').change(function(){
			
	        function getObjectURL(file) {
	            var url = null;
	            if (window.createObjcectURL != undefined) {
	                url = window.createOjcectURL(file);
	            } else if (window.URL != undefined) {
	                url = window.URL.createObjectURL(file);
	            } else if (window.webkitURL != undefined) {
	                url = window.webkitURL.createObjectURL(file);
	            }
	            return url;
	        }
	        var objURL = getObjectURL(this.files[0]);//这里的objURL就是input file的真实路径
	        $("#myPhoto").attr('src',objURL);
		})


				$('#bgPath').change(function(){
			
	        function getObjectURL(file) {
	            var url = null;
	            if (window.createObjcectURL != undefined) {
	                url = window.createOjcectURL(file);
	            } else if (window.URL != undefined) {
	                url = window.URL.createObjectURL(file);
	            } else if (window.webkitURL != undefined) {
	                url = window.webkitURL.createObjectURL(file);
	            }
	            return url;
	        }
	        var objURL = getObjectURL(this.files[0]);//这里的objURL就是input file的真实路径
	        $(".BG").attr('src',objURL);
		})
		


		$('.inputName').keyup(function(){
			$('.nick_name').text($('.inputName').val());
		})
		$('.expArea').keyup(function(){
			$('.outdoor_exp').text($('.expArea').val());
		})
		
		
		  if('${update }'=='success'){
				swal({
					title: '會員名片更新成功 ! ',
					type:'success',
					showConfirmButton: false,
					timer: 2000,
				}).catch(swal.noop);
				<% session.removeAttribute("update");%>
		  }
		
	})
		

  /*ADPoint 動畫*/
    $('.ADRate') 
   .prop('number', 0) 
   .animateNumber( 

   { 
     number: ${memberVO.adventure_point} 
   }, 
   3000 
);

</script>


<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/card.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>