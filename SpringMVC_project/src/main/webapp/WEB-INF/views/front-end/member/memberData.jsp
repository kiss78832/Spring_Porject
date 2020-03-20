<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.spring.member.model.*" %>
<%    // prevent browser cache jsp     
  response.setHeader("Pragma", "No-cache"); // HTTP 1.0
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
  response.setDateHeader("Expires", -1); // proxies
%>
<jsp:useBean id="mSvc" class="com.spring.member.model.MemberService"/>
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
memberVO = mSvc.getOneMember(memberVO.getMember_id());
pageContext.setAttribute("memberVO", memberVO);
%>



<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
  <title>Island Peak MemberData</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<!-- body&footer -->

<!-- member -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/memberContent.css">
<!-- memberFormData -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/memberForm.css">

  <style type="text/css">
      /*本地字體引入*/
  @font-face{
    font-family: Russo;
    src:url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
     unicode-range: U+00-024F;
  }
  @font-face{
    font-family: Noto;
    src:url('/DA102G1/assets/fonts/NotoSansTC-Regular.otf');
    unicode-range: U+4E00-9FFF;
  }

  </style>

</head>
<body>

<jsp:include page="/WEB-INF/common/signup.jsp" flush="true"/>

<div class="container-fluid">
  <div class="row">

	<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true"/>

<div class="col-lg-12 contentbox" >
<img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">
  
  <jsp:include page="/WEB-INF/common/memberSideBar.jsp" flush="true"/>

  <div id="cright" class="col-lg-10 col-md-10 cb">

     <div class="cb col-lg-12"><h1 class="memData">個人檔案</h1></div>

    <div class="col-lg-10 formBox cb">
       <form class="menberData" action="<%= application.getContextPath() %>/member/member.do" method="POST">
      <table class="table-hover">
     
         <label>
          <tr>
            <th>帳號 ： </th>
            <td><input type="text" name="member_id" readonly="readonly" style="border:0px;"
            value="${memberVO.member_id}" class="form-control-plaintext idInput"></td>
          </tr>
        </label>
        
         <label>
          <tr>
            <th>密碼 ： </th>
            <td><input type="password" name="password" placeholder="請輸入密碼"
            value="${memberVO.password}" readonly><font style="color:red"></font>
            <div class="">
            	<p style="font-size:15px;color:red;">雙擊修改密碼</p> 
            </div>
        </td>
          </tr>
        </label>
        
        <label>
          <tr>
            <th>姓名 ： </th>
            <td><input type="text" name="m_name" placeholder="請輸入姓名"
             value="${memberVO.m_name}"></td>
          </tr>
        </label>


        <label>
          <tr>
            <th>性別 ： </th>
            <td>
              <input class="gender" type="radio" name="gender" value="1"> 男<br>
              <input class="gender" type="radio" name="gender" value="2"> 女

            </td>
          </tr>
        </label>

        <label>
          <tr>
            <th>生日 ： </th>
            <td><input type="date" name="birthday" placeholder="2019-06-06"
            value="${memberVO.birthday}"></td>
          </tr>
        </label>

        <label>
          <tr>
            <th>手機 ： </th>
            <td><input type="number" name="cellphone" placeholder="請輸入手機號碼"
            value="${memberVO.cellphone}"><font style="color:red"></font></td>
          </tr>
        </label>

        <label>
          <tr>
            <th>email ： </th>
            <td><input type="email" name="m_email" placeholder="請輸入Email"
            value="${memberVO.m_email}" id="ema"><font style="color:red"></font></td>
          </tr>
        </label>

      </table>
      <br>
			<input type="submit" name="" value="submit">
			<input type="hidden" name="action" value="UPDATE">
     </form>
    </div>

    

  </div>

</div>



	<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>
  </div>
</div>




<script type="text/javascript">
	$(document).ready(function(){
		//alert($(".formBox input:eq(1)").val());
		$('input[type=password]').dblclick(function(){
			$(this).attr("readonly",false);
		})
		
        var ii =  "${memberVO.gender}"
            var gender = document.getElementsByClassName('gender');
            if(ii==1){
              gender[0].checked = true; 
            }else if(ii==2){
              gender[1].checked = true; 
            }
		
	
		$('.menberData').submit(function(e){
	    var cellphone = $('input[type=number]').val();
	    var re = /^09[0-9]{8}$/;
	  if(cellphone.length!=0&& !re.test(cellphone)){
	    $('input[type=number]').next().html("  *格式錯誤");
	    e.preventDefault();
	      return;
	  }
	  
	    var password = $(".formBox input:eq(1)").val();
	    //alert(password);
	  if(password.length<8){
		  $(".formBox input:eq(1)").next().html("  *密碼過短");
	    alert("wewq");
	    alert(password);
	    e.preventDefault();
	      return;
	  }
	  
		var memail = $('#ema').val();
		if(memail.length == 0){
			$('#ema').next().html("  *不得為空");
		    e.preventDefault();
		     return;
		}
	  })

	  if('${update }'=='success'){
			swal({
				title: '會員資料更新成功 ! ',
				type:'success',
				showConfirmButton: false,
				timer: 2000,
			}).catch(swal.noop);
			<% session.removeAttribute("update");%>
	  }
	
	})

</script>
<!-- nav JS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>
<!-- sideBar JS -->

<!--  -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>