<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spring.group.model.*"%>
<%@ page import="com.spring.member.model.*" %>
<%@ page import="com.spring.memberinfo.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="mSvc" class="com.spring.member.model.MemberService"/>
	<%
		GroupVO groupVO = (GroupVO)request.getAttribute("groupVO");
		MemberinfoVO memberinfoVO = (MemberinfoVO)session.getAttribute("memberinfoVO");
	%>
	
	<%
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
// 		memberVO.getMember_id();
	%>
	
	<%
		HashMap<Integer,String> st = new HashMap<Integer,String>();
		st.put(1, "男");
		st.put(2, "女");
		pageContext.setAttribute("gender", st);
    %>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
	<title>共用頁面</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- datePicker -->
<link   rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/vendor/assets/stylesheets/jquery.datetimepicker.css" />

<script src="<%= application.getContextPath()%>/vendor/assets/javascripts/jquery.datetimepicker.full.js"></script>
<script src="<%= application.getContextPath()%>/vendor/assets/javascripts/allpowerfulDate.js"></script>
<!-- datePicker -->

<!-- form style -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/assets/stylesheets/formStyle.css">
<!-- form style -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/assets/stylesheets/body.css">
<!-- body -->


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
  height: auto;
  padding: 0px;
  

}
.contentbox .cb{/*contentbox 底下的div*/
  display: inline-block;
  position: relative;
}

/*centerbox start*/
#centerbox{
width: 750px;
background-color: rgba(52,53,55,0.7);
border: 1px solid rgba(150,150,150,0.5);
height: auto;
}
/*
#centerbox div{
  margin-top: 15px;
  border: 2px solid red;
}
*/


</style>

</head>
<body>
	
	<jsp:include page="/WEB-INF/common/signup.jsp" flush="true"/>

	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true"/>
			
			

			<div class="col-lg-12 contentbox" >

			  
<div id="centerbox" class="container-fluid m-auto">
		<!-- <div class="col-lg-12 cb">樣板 你要放的內容</div> -->
					
      

        <div class="tab-content">
          <h5 style="text-align: center">參團資料填寫</h5>
          <span class="REDWD_b">請詳實填寫個人資料勿重複 </span><br>

      <form METHOD="POST" ACTION="<%= request.getContextPath()%>/member/memberinfo.do" id="form1">         

          <div class="from-group">
            <label class="from-lable">
         		     姓名:&emsp;    
              </label>
            <div class="from-input">
             <input type="TEXT" name="m_name" class="from-control" id="m_name"
             value="${memberVO.m_name}" size="11">
            </div>
          </div>
            
 
        
          <div class="from-group">
            <label class="from-lable">
             	 性別&emsp;    
            </label>
            <div class="from-input">

           	<input type="TEXT" name="gender" class="from-control" 
             value="${gender[memberVO.gender]}" size="11">
               
            
            </div>
          </div>
          
     
         <div class="from-group">
            <label class="from-lable">
         		    生日:&emsp;    
              </label>
            <div class="from-input">
             <input type="TEXT" name="birthday" class="from-control" id="f_date1" 
             value="${memberVO.birthday}" size="16">
            </div>
          </div>
          
          
          <div class="from-group">
            <label class="from-lable">
          	   	行動電話&emsp;    
            </label>
            <div class="from-input">
             <input type="TEXT" name="cellphone" class="from-control" 
             value="${memberVO.cellphone}" size="11">
            </div>
          </div>
          

          <div class="from-group">
            <label class="from-lable">
         	     Email&emsp;    
            </label>
            <div class="from-input">
             <input type="TEXT" name="m_email" class="from-control" 
             value="${memberVO.m_email}">
            </div>
          </div>
          
    
         <div class="from-group">
         <span class="REDWD_b">*</span>
            <label class="from-lable">
         	            地址&emsp;    
            </label>
            <div class="from-input">
            	 <input type="TEXT" name="address" class="from-control" id="address"
            	 	value="${memberinfoVO.address}" size="25">
            </div>
          </div>
          
          
          <div class="from-group">
            <label class="from-lable">
              <span class="REDWD_b">*</span>
         		     身分證號碼:&emsp;    
              </label>
            <div class="from-input">
             <input type="TEXT" name="identity" class="from-control" id="identity"
             value="${memberinfoVO.identity}" size="16">
            </div>
          </div>
         
		
          
          <div class="from-group">
          	<span class="REDWD_b">*</span>
            <label class="from-lable">
         	     緊急聯絡人&emsp;    
            </label>
            <div class="from-input">
             <input type="TEXT" name="egc_contact" class="from-control" id="egc_contact"
             value="${memberinfoVO.egc_contact}" size="16">
            </div>
          </div>
          
          
          <div class="from-group">
          	<span class="REDWD_b">*</span>
            <label class="from-lable">
         	     緊急聯絡人電話&emsp;    
            </label>
            <div class="from-input">
             <input type="TEXT" name="egc_phone" class="from-control" id="egc_phone"
             value="${memberinfoVO.egc_phone}" size="11">
            </div>
            
            <span class="REDWD_r">「緊急聯絡人以自己家人為主，否則無法受理，緊急聯絡人或留守人員為不隨隊伍上山之家人」 </span><br>
          </div>
          <input type="button" class="mag" value="懶人按鈕">
          <div class="BU">
        <input type="hidden" name="action"  value="insert">
        <input type="hidden" name="group_id"  value="${group_id}">
        <input type="hidden" name="member_id"  value="${memberVO.member_id}">
        <button type="submit" class="btn btn-success btn-lg">送出</button>
<!--         <input type="submit" value="確認送出"> -->
      </div>
          
    </div>
  </form>
  
				</div>

			</div>

		  	<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>
		  
		</div>
	</div>

<!-- JS -->
<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$('#f_date1').datetimepicker({
   theme: '',          //theme: 'dark',
   timepicker: false,   //timepicker: false,
   step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
   format: 'Y-m-d ',
   value: new Date(),
   
});

// 神奇小按鈕
$(document).ready(function(){
	$(".mag").click(function(){
	$("#egc_contact").val("劉哲豪");
	$("#egc_phone").val("0900368258");
	$("#identity").val("A123456789");
	$("#address").val("337桃園市大園區航站南路15號");
	});
});


</script>
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>