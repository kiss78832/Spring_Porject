<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@page import="com.application.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="com.group.model.*"%>
<%@page import="com.info.model.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<jsp:useBean id="mbrSvc" class="com.member.model.MemberService"/>
<%
	
	GroupVO groupVO = (GroupVO) session.getAttribute("groupVO");
	InfoVO infoVO = (InfoVO) session.getAttribute("route_ID");
	
	
	ApplicationService appSvc = new ApplicationService();
	ApplicationVO appVO = (ApplicationVO) request.getAttribute("appVO");
	request.setAttribute("appVO", appVO);
	
	GroupService gSvc = new GroupService();
	groupVO = (GroupVO)gSvc.getOneGroupById("G000001");
	session.setAttribute("groupVO", groupVO );
	
	InfoService infoSvc = new InfoService();
	infoVO = (InfoVO)infoSvc.getOneInfo("R001");
	session.setAttribute("infoVO",infoVO);
	pageContext.setAttribute("infoSvc", infoSvc);
	
	MemberVO mbrVO = (MemberVO) session.getAttribute("memberVO");
	pageContext.setAttribute("mbrVO", mbrVO);
	pageContext.setAttribute("mbrSvc", mbrSvc);
	%>  
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
  height: 900px;
  padding: 0px;
  

}
.contentbox .cb{/*contentbox 底下的div*/
  display: inline-block;
  position: relative;
  
}

/*centerbox start*/
#centerbox{
width: 90%;
background-color: rgba(190,190,190,0.7);;
border: 1px solid rgba(150,150,150,0.5);
height: 900px;
}
#centerbox div{
  margin-top: 15px;

}
/*centerbox end*/
-moz-placeholder {
    color: blue;
}
 
-webkit-input-placeholder {
    color: blue;
}

focus{
  outline: none;

}


.contact_form ul {
    width:750px;  
    list-style-type:none;
    list-style-position:outside;
    margin:0px;
    padding:0px;
}
.contact_form li{
    padding:20px; 
    border-bottom:1px solid #eee;
    position:relative;
}
 
.contact_form li : first-child,.contact_form li : last-child {
    border-bottom:1px solid #777;
}

.contact_form h2 {
    margin:0;
    display: inline;
}
.required_notification {
    color:#d45252; 
    margin:5px 0 0 0; 
    display:inline;
    float:right;
}
.contact_form label {
    width:150px;
    margin-top: 3px;
    display:inline-block;
    float:left;
    padding:3px;
    font-size: 18px;
}
.contact_form input {
    height:20px; 
    width:220px; 
    padding:5px 8px;
}
.contact_form textarea {
  padding:8px; 
  width:300px;
}

.contact_form button {
  margin-left:156px;

}

.contact_form input, .contact_form textarea { 
    border:1px solid #aaa;
    box-shadow: 0px 0px 3px #ccc, 0 10px 15px #eee inset;
    border-radius:2px;
}
.contact_form input : focus, .contact_form textarea : focus {
    background: #fff; 
    border:1px solid #555; 
    box-shadow: 0 0 3px #aaa; 
}

.contact_form input, .contact_form textarea {
    padding-right:30px;
}

input:required, textarea:required {
    background: #fff url(/DA102G1/assets/images/app_image/requirced.png) no-repeat 98% center;
}

/* Button Style */
button.submit {
    background-color: #68b12f;
    background: -webkit-gradient(linear, left top, left bottom,from(#68b12f),to(#50911e));
    background: -webkit-linear-gradient(top, #68b12f, #50911e);
    background: -moz-linear-gradient(top, #68b12f, #50911e);
    background: -ms-linear-gradient(top, #68b12f, #50911e);
    background: -o-linear-gradient(top, #68b12f, #50911e);
    background: linear-gradient(top, #68b12f, #50911e);
    border: 1px solid #509111;
    border-bottom: 1px solid #5b992b;
    border-radius: 3px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    -ms-border-radius: 3px;
    -o-border-radius: 3px;
    box-shadow: inset 0 1px 0 0 #9fd574;
    -webkit-box-shadow: 0 1px 0 0 #9fd574 inset ;
    -moz-box-shadow: 0 1px 0 0 #9fd574 inset;
    -ms-box-shadow: 0 1px 0 0 #9fd574 inset;
    -o-box-shadow: 0 1px 0 0 #9fd574 inset;
    color: white;
    font-weight: bold;
    padding: 6px 20px;
    text-align: center;
    text-shadow: 0 -1px 0 #396715;
    margin-left:395px; 
}
button.submit:hover {
    opacity:.85;
    cursor: pointer; 

}
button.submit:active {
    border: 1px solid #20911e;
    box-shadow: 0 0 10px 5px #356b0b inset; 
    -webkit-box-shadow:0 0 10px 5px #356b0b inset ;
    -moz-box-shadow: 0 0 10px 5px #356b0b inset;
    -ms-box-shadow: 0 0 10px 5px #356b0b inset;
    -o-box-shadow: 0 0 10px 5px #356b0b inset;
     
}



.contact_form input, .contact_form textarea {
    -moz-transition: padding .25s; 
    -webkit-transition: padding .25s; 
    -o-transition: padding .25s;
    transition: padding .25s;
}

-webkit-validation-bubble-message {
    padding: 1em;
}

.contact_form input:focus:invalid{ 
  /*圖片只顯示一次no-repeat*/
    background: #fff url(/DA102G1/assets/images/app_image/invalid.png) no-repeat 100% center; 
    box-shadow: 0 0px 5px #d45252;
    border-color: #b03535;
    outline: none;
}

.contact_form input:required:valid { 
    background: #fff url(/DA102G1/assets/images/app_image/valid.jpg) no-repeat 100% center;
    box-shadow: 0 0 10px #5cd053;
    border-color: #28921f;
    outline: none;
}


.form_hint {
    background: #d45252;
    border-radius: 3px 3px 3px 3px;
    color: white;
    margin-left:8px;
    padding: 1px 6px;
    z-index: 999;  
    position: absolute; 
    display: none;
}

.form_hint:before{
    content: "\25C0"; 
    color:#d45252;
    position:absolute;
    top:1px;
    left:-6px;
}

.contact_form input:focus + .form_hint {
  display: inline;
}

.contact_form input:required:valid + .form_hint {
  background: #28921f;
} 

.contact_form input:required:valid + .form_hint::before {
  color:#28921f;

}

.app_table{
	color: #7e7e7e;
    margin: auto;
    width: 590px;
}

.app_other{
    width: 196px;
    background: #747474;
    font-size: 22px;
    padding: 9px;
    border: 1px solid white;
    border-radius: 10px 0px 0px 10px;
}
  /* content end*/

.app_table td {
	border: 1px dashed darkseagreen;
    background: whitesmoke;	
}


	</style>

</head>
<body>
	
	<jsp:include page="/common/signup.jsp" flush="true"/>
	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/common/navbar.jsp" flush="true"/>
			

			<div class="col-lg-12 contentbox" >

				<div id="centerbox" class="container-fluid m-auto">
					<div class="col-lg-12 cb">
<form class="contact_form" action="<%=application.getContextPath() %>/app/app.do" method="post" style="margin-left: 20%" >
    <ul>
        <li>
             <h2>入園申請單</h2>
             <span class="required_notification">* 為必填資料</span>
             
             <%-- 錯誤表列 --%>

			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
				    <c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
             
             
        </li>
        <li>
            <label for="APPLICTION_NUM">會員暱稱:</label>
            <input type="text" id="APPLICTION_NUM" name="member_id" required  value = "<%= (appVO==null)?  mbrVO.getMember_id() : mbrVO.getMember_id()%>"/><!-- mbrVO.getMember_id() -->
        </li>

        <li>
            <label for="name">姓名:</label>
            <input type="text" id="name" required name="m_name" placeholder="請輸入完整姓名" value = "<%= (appVO==null)? mbrVO.getM_name() : mbrVO.getM_name()%>" /> <!-- mbrVO.getM_name() -->
        </li>
        <li>
            <label for="route_id">申請路線:</label>
            <input type="text" id="route_id" name="route_id" value="<%= (appVO==null)? "" : appVO.getRoute_id()%>" required  />
        </li>

        <li>
           <label for="email">電子信箱:</label>
           <!-- required" HTML5功能，此欄位不得為空 --> 
           <input type="text" id="email" name="e-mail" value="<%= (appVO==null)? mbrVO.getM_email() : mbrVO.getM_email() %>" required />  <!-- pattern="[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-z]{3}" -->
           <span class="form_hint">正確格式:"EX:XXXXXX@gmail.com"</span>
        </li>

        <li>
            <label for="EGC_CONTACT">緊急連絡人:</label>
            <input type="text" id="EGC_CONTACT" required  pattern="{5}" name="egc_contact" value = "<%= (appVO==null)? "" : appVO.getEgc_contact()%>"/>  <!-- 中文不行 -->
        </li>

        <li>
            <label for="EGC_PHONE">緊急連絡人電話:</label>
            <input type="text" id="EGC_PHONE"  required  name="egc_phone" value='<%= (appVO==null)? "": appVO.getEgc_phone()%>' />
            <span class="form_hint">EX:0966XXXXXX</span>
        </li>

        <li>
            <label for="SATELLITE">衛星電話:</label>
            <input type="text" id="SATELLITE" name="satellite" value="<%= (appVO==null)? "" : appVO.getSatellite()%>" />
        </li>
        <li>
            <label for="RADIO">無線電頻:</label>
            <input type="text" id="RADIO" name="radio" value="<%= (appVO==null)? "" : appVO.getRadio()%>" />
        </li>
        <li>
            <label for="APPLICTION_DAYS">申請天數:</label>
            <input type="text" id="APPLICTION_DAYS" name="app_days" value="<%= (appVO==null)? "" : appVO.getApp_days()%>" required pattern="[0-9]{2}"/>
            <span class="form_hint">兩位數 EX:03.10.12...</span>
        </li>
        <li>
            <label for="FIRST_DAY">起始日期:</label>
            <input type="text" id="FIRST_DAY"  required name="first_day" value="<%= (appVO==null)? "" : appVO.getFirst_day()%>" />
        </li>
        
        <li>
          <input type="hidden" name="action" value="insert">
          	 <button class="submit" type="submit">送出表單</button>
        </li>
    </ul>
    
            <input type="hidden"  name="app_status" value="0" />
            <input type="hidden"  name="member_id" value="A001" />
  </form>
  
					</div>
				</div>

			</div>

		  	<jsp:include page="/common/footer.jsp" flush="true"/>
		  
		</div>
	</div>

<!-- JS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
				
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">The Bootstrap modal-header</h3>
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->

						<table class="app_table">
							<tr> 
								<th class="app_other">會員名稱</th><td>${mbrSvc.getOneMember(appVO.member_id).m_name}</td>
							</tr>
							<tr> 		
								<th class="app_other">路線名稱</th><td>${infoSvc.getOneInfo(appVO.route_id).route_Name}</td>
							</tr>
							<tr> 
								<th class="app_other">緊急連絡人</th><td>${appVO.egc_contact}</td>
							</tr>
							<tr> 
								<th class="app_other">緊急連絡人電話</th><td>${appVO.egc_phone}</td>
							</tr>
							<tr> 
								<th class="app_other">衛星電話</th><td>${appVO.satellite}</td>
							</tr>
							<tr> 
								<th class="app_other">無線電頻率</th><td>${appVO.radio}</td>
							</tr>
							<tr> 
								<th class="app_other">申請天數</th><td>${appVO.app_days}</td>
							</tr>
							<tr> 
								<th class="app_other">申請日期</th><td>${appVO.first_day}</td>
							</tr>

						</table>
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <a href="<%=application.getContextPath()%>/front-end/welcome/index.jsp"><button type="button" class="btn btn-primary">回首頁</button></a>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
        </script>
 </c:if>

</body>
</html>