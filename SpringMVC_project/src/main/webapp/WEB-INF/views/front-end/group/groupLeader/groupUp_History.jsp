<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spring.group.model.*"%>

	<%
	GroupVO groupVO = (GroupVO)request.getAttribute("groupVO");
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>Group History</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- datePicker -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/vendor/assets/stylesheets/jquery.datetimepicker.css" />

<script src="<%= application.getContextPath() %>/vendor/assets/javascripts/jquery.datetimepicker.full.js"></script>
<script src="<%= application.getContextPath() %>/vendor/assets/javascripts/scrdatepicker.js"></script>
<!-- datePicker -->

<!-- form style -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/formStyle.css">
<!-- form style -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/body.css">
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
width: 700px;
background-color: rgba(52,53,55,0.7);
border: 1px solid rgba(150,150,150,0.5);
height: auto;
}
/*

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
	  <div class ="title-content">
        <span>詳細資料</span>
      </div>
      
      <form METHOD="POST" ACTION="<%= request.getContextPath()%>/group/group.do" id="form1">

        <div class="tab-content">
          <h3 style="text-align: left">揪團資料</h3>
          <span class="REDWD_b">登山路線進入國家公園生態保護區須要申請，非國家公園生態保護區不須申請 </span><br>

          <div class="from-group">
            <label class="from-lable">
              
         		     隊伍名稱&emsp;    
              </label>
            <div class="from-input">
             <span>${groupVO.gp_name}</span>
            </div>
          </div>
            
          
          <div class="from-group">
            <label  for="form" class="from-lable" >
              		入園日期&emsp;    
            </label>
            <div class="from-input">
             <input name="first_day" id="start_date" type="text" size="10" 
             value="<%= groupVO.getFirst_day()%>" readonly="readonly">
<!--              <span class="from-lable">~離開日期</span> -->
<!--              <span> -->
<!--                <input name="end_day" id="end_date" type="text" size="10" -->
<%--                 value="<%= groupVO.getEnd_day()%>" readonly="readonly"> --%>
<!--              </span> -->
            </div>
          </div>
          
          
          <div class="from-group">
            <label class="from-lable">
              <span class="REDWD_b">*</span>
         		    目標路線&emsp;    
              </label>
            <div class="from-input">
             <input type="TEXT" name="target_loca" class="from-control" 
             value="${groupVO.target_loca}">
            </div>
          </div>
          


         <div class="from-group">
          <label class="from-lable">
           
         	   登山路線&emsp;    
            </label>

          <div class="from-input">
         	   主路線: 
         	   <%--incould 行程 --%>
         </div>
        </div>
        
         <div class="from-group">
            <label class="from-lable">
              <span class="REDWD_b">*</span>
              路線規劃&emsp;    
            </label>
            <div class="from-input">
                <span class="test-danger">第一天行程<br>
                  第二天行程
                </span>
                <div class="highlight">
                </div>
            </div>
          </div>
        
          <div class="from-group">
            <label class="from-lable">
              
             	 隊伍人數&emsp;    
            </label>
            <div class="from-input">
             <input class="from-control" name="gp_nbp" 
             value="<%= groupVO.getGp_nbp()%>" readonly="readonly" size="1" align="center" >
               
             </select>
            </div>
          </div>

		
          <div class="from-group">
            <label class="from-lable">
          	    衛星電話&emsp;    
            </label>
            <div class="from-input">
             <input type="TEXT" name="satellite" class="from-control" 
             value="<%= groupVO.getSatellite()%>" readonly="readonly">
            </div>
          </div>

          <div class="from-group">
            <label class="from-lable">
         	     無線電頻&emsp;    
            </label>
            <div class="from-input">
             <input type="TEXT" name="radio" class="from-control" 
             value="<%= groupVO.getRadio()%>" readonly="readonly">
            </div>
          </div>
          
          <div class="from-buttom">
           	<a href="<%= application.getContextPath()%>/front-end/group/groupLeader/groupList.jsp" target="_self"><img src="<%= application.getContextPath() %>/assets/images/Icon/home.png" class="pic"></a>
          </div>
    </div>
  </form>
		</div>
			</div>

		  	<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>
		  
		</div>
	</div>

<!-- JS -->
<script type="text/javascript" src="<%= application.getContextPath()%>/assets/javascripts/index.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>