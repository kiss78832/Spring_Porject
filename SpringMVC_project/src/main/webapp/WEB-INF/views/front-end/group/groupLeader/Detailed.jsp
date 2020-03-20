<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spring.group.model.*"%>
<%@ page import="com.spring.itinerary.model.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.spring.memberinfo.model.*"%>

<jsp:useBean id="memberinfoSvc" class="com.spring.memberinfo.model.MemberinfoService"/>
<jsp:useBean id="itinerarySvc" class="com.spring.itinerary.model.ItineraryService"/>
	<%
	GroupVO groupVO = (GroupVO)request.getAttribute("groupVO");
	GroupVO igroupVO = (GroupVO)session.getAttribute(groupVO.getGroup_id());
	%>
	
	<%
	ItineraryVO itineraryVO = (ItineraryVO)request.getAttribute("itineraryVO");
	%>
	
	<%
	MemberinfoVO memberinfoVO = (MemberinfoVO)request.getAttribute("memberinfoVO");
	%>
	
	<% 
	List<MemberinfoVO> list = memberinfoSvc.memberinfoDetail("igroupVO");
	pageContext.setAttribute("list", list);
	%>
	
	<%
	HashMap st = new HashMap();
	st.put("R001", "玉山群峰線");
	st.put("R002", "南二段線");
	st.put("R003", "南橫三山及關山線");
	st.put("R004", "新康橫斷線");
	pageContext.setAttribute("status", st);
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>Island Peak Group Detail</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- datePicker -->
<link   rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/vendor/assets/stylesheets/jquery.datetimepicker.css" />

<script src="<%= application.getContextPath() %>/vendor/assets/javascripts/jquery.datetimepicker.full.js"></script>
<script src="<%= application.getContextPath() %>/vendor/assets/javascripts/scrdatepicker.js"></script>
<!-- datePicker -->


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


/*******formstyle*******/
div.tab-content{
	background-color: rgba(255,255,255,0.6);
	border-radius: 8px;
	padding: 60px 100px 60px 100px;
	font-size: 20px;
	color:black;
	font-weight: 0;
	height: auto;
	text-align:left;
	width:auto;
	margin-bottom:80px;
	margin-top: 30px; 
}

div.title-content span{
	padding: 20px;
	font-size: 30px;
	font-weight: 0;
}

.REDWD_b{
	color:red;
	/*font-weight:bold;*/
	font-size:15px;
	font-family:'微軟正黑體';
	text-align:left;
}


span.REDWD_r{
	color:blue;
	/*font-weight:bold;*/
	font-size:15px;
	font-family:'DFKai-sb';
	text-align:left;
}



#end_date,#start_date{
	border-radius: 5px;
	text-align:center;
}
.from-group,.form-input{
/* 	width: 100%; */
	margin-top: 20px;
	margin-bottom: 20px;
	border-radius: 5px;
}

.from-lable{
	padding-top:.2em;
}

.from-control{
	border-radius: 5px;
}
div.from-buttom{
	padding-top: 15px;
	padding-bottom: 5px;
	text-align:center;
}

.BU{
	text-align: center;
	margin-top: 50px;
    margin-bottom: 50px;
}

.cell{
	border-bottom: 1px solid black;
}
/*******form style*******/

/*centerbox start*/
#centerbox{
/* width: 700px; */
background-color: rgba(52,53,55,0.7);
border: 1px solid rgba(150,150,150,0.5);
height: auto;
}

#end_date,#start_date{
	background-color: rgba(0,0,0,0);
	disabled:true;
	pointer-events: none;
	border:0;
}

.schedule{
    background-color: rgba(150,150,150,0.8);
    border-radius: 10px;
    padding: 10px;
}

</style>

</head>
<body>
	
	<jsp:include page="/WEB-INF/common/signup.jsp" flush="true"/>

	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true"/>

			<div class="col-lg-12 contentbox" >

			  
<div id="centerbox" class="container m-auto">
					<!-- <div class="col-lg-12 cb">樣板 你要放的內容</div> -->
	  <div class ="title-content">
        
      </div>
      
      <form METHOD="POST" ACTION="<%= request.getContextPath()%>/group/group.do" id="form1">

        <div class="tab-content">
          <h3 style="text-align: left">揪團資料</h3>
          <span class="REDWD_b">登山路線進入國家公園生態保護區須要申請，非國家公園生態保護區不須申請 </span><br>

          <div class="row cell">
          
          <div class="from-group col">
            <label class="from-lable">
              <span class=""></span>
              <input type="hidden" name="gp_name" id="gp_name" type="text" size="10" class="from-control"
                value="${groupVO.gp_name}" readonly="readonly">
         		     隊伍名稱&emsp;:&emsp; ${groupVO.gp_name}
              </label>

          </div>
          
          
            <div class="from-group col">
            <label class="from-lable">
              <span class=""></span>
              <input type="hidden" name="target_loca" class="from-control" 
             	value="${groupVO.target_loca}">
         		    目標地點&emsp;:&emsp; ${groupVO.target_loca} 
              </label>
          </div>
          
          
          </div>
            <div class="row cell">
          
          <div class="from-group col">
            <div class="from-input">
            <span class=""></span>入園日期
             <input name="first_day" id="start_date" type="text" size="10" 
             value="${groupVO.first_day}">

<%-- 			<span>${groupVO.route_id}</span> --%>
			<input name="route_id" id="route_id" type="hidden" size="10" 
             value="${groupVO.route_id}">
            </div>
          </div>
          
          </div>
          
          
          <div class="row cell">
	          <div class="from-group col">
	            <label class="from-lable">
	              <span class=""></span>
	         		    路線&emsp;:&emsp;${status[groupVO.route_id]}
	              </label>
	          </div>
	          
	          <!--住宿點 -->
				<input type="hidden" class="from-control" name="trip_break" value="${itineraryVO.trip_break}">
			  <!--住宿點 -->
	          	
	          
	          
	          <div class="from-group col">
	            <label class="from-lable">
	            <input type="hidden" class="from-control" name="gp_nbp" value="${groupVO.gp_nbp}">
	             	 隊伍人數&emsp;:&emsp;${groupVO.gp_nbp}
	            </label>
	          </div>
          </div>
          
          
          <div class="row cell">
	          <div class="from-group col">
	            <label class="from-lable">
	             <input type="hidden" name="satellite" class="from-control" 
             		value="<%= groupVO.getSatellite()%>" readonly="readonly">
	          	    衛星電話&emsp;:&emsp;${groupVO.satellite}   
	            </label>
	          </div>

	          <div class="from-group col">
	            <label class="from-lable">
	            <input type="hidden" name="radio" class="from-control" 
             value="<%= groupVO.getRadio()%>" readonly="readonly">
	         	     無線電頻&emsp;:&emsp;${groupVO.radio}
	            </label>
	          </div>
          </div>
          
			<div class="from-group">
            <label class="from-lable">
             	 行程&emsp;:&emsp;
            </label>
            <div class="from-input schedule">
             <span>${itineraryVO.trip_schedule}</span>
            </div>
          </div>
			


       
		

          
          <div class="from-buttom">
          	<button type="submit" class="btn btn-info btn-lg">送出入園申請</button>
          	
           	<a href="<%= application.getContextPath()%>/front-end/group/groupLeader/groupList.jsp" target="_self">
<!--            	<button class="btn btn-secondary btn-lg">返回</button> -->
					<img src="<%= application.getContextPath()%>/assets/images/Icon/exit.png">
           	</a>
          </div>
    </div>
      <div class="BU">
        <input type="hidden" name="action"  value="insterApplication">
        <input type="hidden" name="group_id" value="<%= groupVO.getGroup_id() %>">
        <input type="hidden" name="gp_leader" value="<%= groupVO.getGp_leader() %>">
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