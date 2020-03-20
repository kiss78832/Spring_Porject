<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spring.group.model.*"%>
<%@ page import="com.spring.member.model.*" %>
<%@ page import="com.spring.memberinfo.model.*"%>
<%@ page import="java.util.*"%>
	<%
	GroupVO groupVO = (GroupVO)request.getAttribute("groupVO");
	MemberinfoVO memberinfoVO = (MemberinfoVO)session.getAttribute("memberinfoVO");

	%>
	<jsp:useBean id="mSvc" class="com.spring.member.model.MemberService"/>
	<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	memberVO = mSvc.getOneMember(memberVO.getMember_id());
	pageContext.setAttribute("memberVO", memberVO);
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
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
<meta charset="utf-8">
	<title>Island Peak</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- datePicker -->
<link   rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/vendor/assets/stylesheets/jquery.datetimepicker.css" />
<script src="<%= application.getContextPath()%>/vendor/assets/javascripts/jquery.datetimepicker.full.js"></script>
<script src="<%= application.getContextPath()%>/vendor/assets/javascripts/scrdatepicker.js"></script>

<!-- form style -->
<%-- <link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/assets/stylesheets/formStyle.css"> --%>
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

.contentbox{
  margin-top: 80px;
  box-sizing: border-box;
/*   background-image: url(/DA102G1/assets/images/snowpeak/forest-1743206.jpg); */
/*   background-size: cover; */
  height: auto;
  padding: 0px;
  

}
.contentbox>img{
  position: fixed;
  top: 0;
  left: 0;
  z-index: -1;
  object-fit: cover;
  height: 100%;
  width: 100%;
}

.contentbox .cb{/*contentbox 底下的div*/
  display: inline-block;
  position: relative;
}

/*centerbox start*/
#centerbox{
width: 80%;
background-color: rgba(52,53,55,0.7);
border: 1px solid rgba(150,150,150,0.5);
height: auto;
}

/*centerbox end*/


/* content end*/
 .wrap {
            position: relative;
            text-align: center;
            width: 100%;
            height: 80px;
            background-color: rgba(50,50,50,0.7);
    border-radius: 10px;
    margin: 20px 0 20px 0;
    border: 2px solid rgba(120,120,112,0.8);
        }
        .wrap div {
            line-height: 80px;
            font-size: 40px;
        }
        /*CSS伪类用法*/
        .wrap div:after, .wrap div:before {
            position: absolute;
            top: 50%;
            background: #ddd;
            content: "";
            height: 2px;
            width: 40%;
        }
        /*调整背景横线的左右距离*/
        .wrap div:before {
            left: 0;
        }
        .wrap div:after {
            right: 0;
        }
	
	 /*************************selectstart*******************************/       
  
/* Reset Select */
select {
  -webkit-appearance: none;
  -moz-appearance: none;
  -ms-appearance: none;
  appearance: none;
  outline: 0;
  box-shadow: none;
  border: 0 !important;
  background: #485a6c;
  background-image: none;
}
/* Remove IE arrow */
select::-ms-expand {
  display: none;
}
/* Custom Select */
/* .select { */
/*   position: relative; */
/*   display: flex; */
/*   width: 20em; */
/*   height: 3em; */
/*   line-height: 3; */
/*   background: #2c3e50; */
/*   overflow: hidden; */
/*   border-radius: .25em; */
/* } */

input{
  background: #485a6c;
  overflow: hidden;
  border-radius: .25em;
  color: #fff;
  outline: 0;
  border: 0 !important;
  padding-left:5px;
  cursor: pointer;
}

input::after {
content: '\25BC';
  position: absolute;
  top: 0;
  right: 0;
  padding: 0 1em;
  background: #485a6c;
  cursor: pointer;
}
select {
  flex: 1;
  padding: 0 .5em; 
  color: #fff;
  cursor: pointer;
}
/* Arrow */
.select::after {
  content: '\25BC';
  position: absolute;
  top: 0;
  right: 0;
  padding: 0 1em;
  background: #485a6c;
  cursor: pointer;
  pointer-events: none;
  -webkit-transition: .25s all ease;
  -o-transition: .25s all ease;
  transition: .25s all ease;
}
/* Transition */
.select:hover::after {
  color: #f39c12;
}
	.tab-content input,select{
	margin-bottom: 30px;

	}
	
	div.tab-content{
	background-color: rgba(255,255,255,0.6);
	border-radius: 8px;
	font-size: 20px;
	font-weight: 0;
	height: auto;
	text-align:initial;
	width:auto;
	margin: 50px 200px;
    padding: 50px;
    color: black;
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
	width: 60%;
	margin: .3rem;
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

/*路線規劃*/
.schedule{
	font-size:16px;
	padding: 10px;
	background-color: rgba(100,100,100,0.3);
    width: 100%;
    height: auto;
}

.schedule input{
margin-bottom:0px;
}

.listBox{
	border: 1px solid darkgray;
    border-radius: 3px;
    padding: 5px;
    box-sizing: border-box;
    margin-top: 10px;
}

.listBox>p{
 	color:rgba(180,47,35,0.8);
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
	<div id="centerbox" class="container-fluid m-auto">
	
					<!-- <div class="col-lg-12 cb">樣板 你要放的內容</div> -->
     <div class="wrap">
    <div>建立揪團</div>
	</div>
      
      <form METHOD="POST" ACTION="<%= request.getContextPath()%>/group/group.do" id="form1">

        <div class="tab-content">

          <h6 style="text-align: left">路線行程規劃</h6>
          <span class="REDWD_b">登山路線進入國家公園生態保護區須要申請，非國家公園生態保護區不須申請 </span><br>
		
		<!-- 錯誤處理 -->
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
		</c:if>	
		<!-- 錯誤處理 -->
          <div class="row">
          
		<!-- 團主資料 -->
		  <input type="hidden" name="m_name" class="from-control" 
             value="${memberVO.m_name}" size="11">
          <input type="hidden" name="gender" class="from-control" 
             value="${gender[memberVO.gender]}" size="11">
          <input type="hidden" name="birthday" class="from-control" id="f_date1" 
             value="${memberVO.birthday}" size="16">
          <input type="hidden" name="cellphone" class="from-control" 
             value="${memberVO.cellphone}" size="11">
          <input type="hidden" name="m_email" class="from-control" 
             value="${memberVO.m_email}">

        <!-- 團主資料 --> 
          
          
          
          <div class="from-group col">
            <label class="from-lable">
              <span class="REDWD_b">*</span>
         		     隊伍名稱&emsp;    
              </label>
            <div class="from-input">
             <input type="TEXT" name="gp_name" class="from-control" id="gp_name" placeholder="台灣隊"
             value="${groupVO.gp_name}">
            </div>
          </div>
          
          
            <div class="from-group col">
            <label class="from-lable">
              <span class="REDWD_b">*</span>
         		    目標地點&emsp;    
              </label>
            <div class="from-input">
             <input type="TEXT" name="target_loca" class="from-control" id="target_loca" 
             value="${groupVO.target_loca}">
            </div>
          </div>
          
          
          </div>
          
          <div class="from-group">
            <div class="from-input">
            <span class=""></span>入園日期
             <input name="first_day" id="start_date" type="text" size="10" autocomplete="off"
             value="${groupVO.first_day}">
            </div>
          </div>
          
          <div class="row">
 
           <div class="from-group col">
            <label class="from-lable">
              <span class="REDWD_b">*</span>
             	 隊伍人數&emsp;    
            </label>
            <div class="from-input">
             <select class="from-control" name="gp_nbp" 
             value="${groupVO.gp_nbp=='' ? 1 : groupVO.gp_nbp}">
               <option selected="selected" >請選擇</option>
               <option value="1">1</option>
               <option value="2">2</option>
               <option value="3">3</option>
               <option value="4">4</option>
               <option value="5">5</option>
               <option value="6">6</option>
             </select>
            </div>
          </div>
 

          
		<div class="from-group col">
            <label class="from-lable">
              <span class="REDWD_b">*</span>
         		    路線&emsp;    
              </label>
            <div class="from-input">
             <select onchange="javascript:doGet()" class="mainRoad" name="route_id">
             	<option selected="selected" >請選擇路線</option>
             	<option value="R001">玉山主峰線</option>
             	<option value="R002">南二段線</option>
             	<option value="R003">南橫三山及關山線</option>
             	<option value="R004">新康橫斷線</option>
		

             	
             </select>
            </div>
          </div>
          
          <div class="from-group col">
    		<label class="from-lable">
           		<span class="REDWD_b">*</span>
            		天數&emsp;    
        	</label>
          <div class="from-input">
             <select class="from-control total_day" name="total_day" >
               <option selected="selected" >請選擇天數</option>
               <option value="1">1</option>
               <option value="2">2</option>
               <option value="3">3</option>
               <option value="4">4</option>
               <option value="5">5</option>
               <option value="6">6</option>
               <option value="7">7</option>
               <option value="8">8</option>
             </select>
         </div>
      	 </div>	
          
          </div><!-- 1 row end -->
          
          <div class="from-group schedule">
        	<span class="REDWD_b">*</span>
            <label class="from-lable">
           	   路線規劃&emsp;:
            </label><br>
            <span id="placeHolder"></span>
            
            <input type="hidden" class="trip_schedule" name="trip_schedule">
            
            <div class="listBox">
            <p>請選擇下一個地點：</p>
            <table>
            	<tbody>
          
            	</tbody>
            </table>
            <button type="button" name=""  id="complete" class="btn btn-success">完成今日路線</button>&nbsp;&nbsp;
            <button type="button" name=""  id="goBack" class="btn btn-success">返回上一個地點</button>&nbsp;&nbsp;
            <button type="button" name=""  id="clear" class="btn btn-danger">重新規劃</button>
			<!-- 住宿點 -->
            <input type="text" name="trip_break"  class="trip_break" value="">
            </div>
	 	  </div>
	
    	
      


        <div class="row">
          <div class="from-group col">
            <label class="from-lable">
          	    衛星電話&emsp;    
            </label>
            <div class="from-input">
             <input type="TEXT" name="satellite" class="from-control" id="satellite"
             value="${groupVO.satellite}">
            </div>
          </div>

          <div class="from-group col">
            <label class="from-lable">
         	     無線電頻&emsp;    
            </label>
            <div class="from-input">
             <input type="TEXT" name="radio" class="from-control" id="radio"
             value="${groupVO.radio}">
            </div>
          </div>
          
          
          </div>
          
        <div class="BU">
        
        <input type="hidden" name="action"  value="insertGroup">
        <button type="submit" class="btn btn-success btn-lg">送出</button>&nbsp;&nbsp;&nbsp;
        <a href="<%= application.getContextPath() %>/front-end/group/joinGroupList.jsp">
        <button type="button" class="btn btn-dark btn-lg">返回</button>
        </a>
      </div>
      </form>
      <input type="button" class="mag" value="懶人按鈕">
	</div>
          
    </div>

</div>

		</div>
	</div>
		  	<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>
		  	
<script type="text/javascript">
var locationHold = "";/*上次的行程*/
var optionHold = "";/*上次的選項們*/
var Road = $('.mainRoad').val();/*主路線*/
var whereAmI = "";/*當次地點*/
var htmlWhereAmI = "";/*當次地點包含html*/
var count = 1;/*計天次*/
/*=======================選擇新地點 start==========================*/    
	function doPost(option,location){
		console.log(location);
	   	var str = {
	            "action":"getPlace",
	            "option": option,
	            "location":location,
	            };
	   	tbodyHold = $('.listBox').find('tbody').html();/*上一次的選項們*/
	   	locationHold = $('#placeHolder').html();/*上一次的行程*/
	   	optionHold = option;
	   	
// 	   	console.log("locationHold = "+$('#placeHolder').html());
// 	   	console.log("optionHold = "+optionHold)
	   	
    	$.ajax({
      		 type: "POST",
      		 url: "<%= application.getContextPath() %>/PlaceHolder.do",
      		 data:str ,
      		 dataType: "json",
      		 success: function (data){

      			$('.listBox').find('tbody').html("");/*清空選項*/
      			$('#placeHolder').html($('#placeHolder').html()+'→'+data.location);/*抓到原行程 加上 新地點*/
      	/************************記住當次地點**************************/	
      			htmlWhereAmI = data.location;
      			var index = data.location.indexOf(">");
      			whereAmI = data.location.substring(index+1);
		/**************************************************************/			
      			$.each(data.option, function(key, value){/*依序放入選項*/
      				$('.listBox').find('tbody').append(value);/*新的地點陣列*/
      			})
      	     },
                 error: function(){alert("AJAX-class發生錯誤囉!")}
             })
             
 /*=======================選擇新地點 end==========================*/   
 
 
 
 /*=======================保留上一點紀錄 start==========================*/            
 		   	var str = {
            "action":"placeHoldBack",
            "tbodyHold": tbodyHold,
            "locationHold":locationHold,
            };
	
	$.ajax({
  		 type: "POST",
  		 url: "<%= application.getContextPath() %>/PlaceHoldBack.do",
  		 data:str ,
  		 dataType: "json",
  		 success: function (data){

  			 console.log(data);
  	     },
             error: function(){alert("AJAX-class發生錯誤囉!")}
         });

	}
/*=======================保留上一點紀錄 end==========================*/
	



	$(document).ready(function() {	
/*=======================清空行程start==========================*/	
		$("#clear").click(function(){
			$('.listBox').find('tbody').html("");
			$('#placeHolder').html("");
			$('.trip_break').val("");
			$('#complete').attr("disabled",false);
			$('#goBack').attr("disabled",false);
			count=1;
			
 		   	var str = {
 		            "action":"clear",
 		            };
 			
 			$.ajax({
 		  		 type: "POST",
 		  		 url: "<%= application.getContextPath() %>/PlaceHoldBack.do",
 		  		 data:str ,
 		  		 dataType: "json",
 		  		 success: function (data){

 		  			 console.log(data.clear);
 		  	     },
 		             error: function(){alert("AJAX-class發生錯誤囉!")}
 		         });

			doGet();
		})
/*=======================清空行程 end==========================*/	




/*=======================完成當日行程 start==========================*/		
		
		$("#complete").click(function(){
		   	var str = {
		            "action":"endDay",
		            "whereAmI":whereAmI,
		            };
			
		   	console.log("whereAmI = "+whereAmI);
		   
			$.ajax({
	      		 type: "POST",
	      		 url: "<%= application.getContextPath() %>/PlaceHoldBack.do",
	      		 data:str ,
	      		 dataType: "json",
	      		 success: function (data){

					var allDays = $('.total_day').val();
					
	      			if(data.lastEnd==true){
	      				if(count==1){
	      					if(count==allDays){ theEnd(); return;}
	      					if(data.Hiking_Gate == true){Hiking_Gate(); return;}
		      				$('#placeHolder').append('<br>第二天行程 : ');
		      				doPost(data.endOption,htmlWhereAmI);
		      				$('.trip_break').val($('.trip_break').val()+whereAmI+",");
		      				count=2;
	      				}else if(count==2){
	      					if(count==allDays){ theEnd(); return;}
	      					if(data.Hiking_Gate == true){Hiking_Gate(); return;}
		      				$('#placeHolder').append('<br>第三天行程 : ');
		      				doPost(data.endOption,htmlWhereAmI);
		      				$('.trip_break').val($('.trip_break').val()+whereAmI+",");
		      				count=3;
	      				}else if(count==3){
	      					if(count==allDays){ theEnd(); return;}
	      					if(data.Hiking_Gate == true){Hiking_Gate(); return;}
		      				$('#placeHolder').append('<br>第四天行程 : ');
		      				doPost(data.endOption,htmlWhereAmI);
		      				$('.trip_break').val($('.trip_break').val()+whereAmI+",");
		      				count=4;
	      				}else if(count==4){
	      					if(count==allDays){ theEnd(); return;}
	      					if(data.Hiking_Gate == true){Hiking_Gate(); return;}
		      				$('#placeHolder').append('<br>第五天行程 : ');
		      				doPost(data.endOption,htmlWhereAmI);
		      				$('.trip_break').val($('.trip_break').val()+whereAmI+",");
		      				count=5;
	      				}else if(count==5){
	      					if(count==allDays){ theEnd(); return;}
	      					if(data.Hiking_Gate == true){Hiking_Gate(); return;}
		      				$('#placeHolder').append('<br>第六天行程 : ');
		      				doPost(data.endOption,htmlWhereAmI);
		      				$('.trip_break').val($('.trip_break').val()+whereAmI+",");
		      				count=6;
	      				}else if(count==6){
	      					if(count==allDays){ theEnd(); return;}
	      					if(data.Hiking_Gate == true){Hiking_Gate(); return;}
		      				$('#placeHolder').append('<br>第七天行程 : ');
		      				doPost(data.endOption,htmlWhereAmI);
		      				$('.trip_break').val($('.trip_break').val()+whereAmI+",");
		      				count=7;
	      				}else if(count==7){
	      					if(count==allDays){ theEnd(); return;}
	      					if(data.Hiking_Gate == true){Hiking_Gate(); return;}
		      				$('#placeHolder').append('<br>第八天行程 : ');
		      				doPost(data.endOption,htmlWhereAmI);
		      				$('.trip_break').val($('.trip_break').val()+whereAmI+",");
		      				count=8;
	      				}

	      				
	      				
	      			}else if(data.lastEnd==false){
	    				swal({
	    					title: '只有宿營地\n才能完成今日路線 !',
	    					type:'warning',
	    					showConfirmButton: false,
	    					timer: 2000,
	    				}).catch(swal.noop);
	      			}
	      			
	      			function Hiking_Gate(){
	    				swal({
	    					title: '只有宿營地\n才能完成今日路線 !',
	    					type:'warning',
	    					showConfirmButton: false,
	    					timer: 2000,
	    				}).catch(swal.noop);
	      			}
	      			 
	      			
	      			function theEnd(){
// 	      				console.log("***********  "+whereAmI);
						/******切割字串*******/

	      				
	      				
	      				/******添加登山出口*******/
	      				if(whereAmI=="塔塔加登山口"
	      						||whereAmI=="東埔登山口"
	      						||whereAmI=="南橫公路向陽登山口"||whereAmI=="南橫公路進涇橋登山口"||whereAmI=="大關山隧道東側登山口"||whereAmI=="南橫公路塔關山登山口"
	      						||whereAmI=="向陽登山口"){
	      					$('#complete').attr("disabled",true);
	      					$('#goBack').attr("disabled",true);
		      				var long = $('.trip_break').val().length;
		      				console.log("long = "+long);
		      				

		      				/******最終結果*******/
		      				$('.trip_break').val($('.trip_break').val().substring(0,long-1));
		      				$('.listBox').find('tbody').html("");
		      				
		      				swal({
		    					title: '行程完成 !',
		    					type:'success',
		    					showConfirmButton: false,
		    					timer: 2000,
		    				}).catch(swal.noop);
		      				
	      				}else{
		      				swal({
		    					title: '最後一天必須為登山口 !',
		    					type:'error',
		    					showConfirmButton: false,
		    					timer: 2000,
		    				}).catch(swal.noop);
	      				}

	      			}
	      			
	      	     },
	                 error: function(){alert("已經回到起點囉 !")}
	             })
	             
		   	
			
		})
/*=======================完成當日行程 end==========================*/	

  

/*=======================回到上一點 start==========================*/
		
		$("#goBack").click(function(){
		   	var str = {
		            "action":"getBack",
		            };
		   	if($('.listBox').find('tbody').text().trim()==""){//已經回到原點
		   		doGet();
		   	}else if($('#placeHolder').html()=="12312"){
		   		doGet();
		   	}else{
			
			$.ajax({
	      		 type: "POST",
	      		 url: "<%= application.getContextPath() %>/PlaceHoldBack.do",
	      		 data:str ,
	      		 dataType: "json",
	      		 success: function (data){
	      			 console.log("data = "+data);
	      			console.log("data.tbodyHold = "+data.tbodyHold);
	      			

	      			$('.listBox').find('tbody').html(data.tbodyHold);
	      			$('#placeHolder').html(data.locationHold);
	      			
	      	     },
	                 error: function(){alert("已經回到起點囉 !")}
	             })
	             
		   	}
		})
/*=======================回到上一點 end==========================*/		
		
	})


	</script>
	<script type="text/javascript">
	
	function doGet(){/*判斷主路線選項*/
		
		Road = $('.mainRoad').val();
		if (Road=='R001') {//onclick="javascript:doPost('3','圓峰山屋')
<%-- 		$('#placeHolder').html('第一天行程 : <img src="<%= application.getContextPath() %>/assets/images/Icon/hiking.png">塔塔加登山口'); --%>
// 		$('.listBox').find('tbody').html(
<%-- 			'<tr><td><input type="radio" onclick="javascript:doPost(2,$(this).next().html())">&nbsp;<label><img src="<%= application.getContextPath() %>/assets/images/Icon/placeholder.png">玉山前峰</label></td></tr>'+ --%>
<%-- 			'<tr><td><input type="radio" onclick="javascript:doPost(1,$(this).next().html())">&nbsp;<label><img src="<%= application.getContextPath() %>/assets/images/Icon/spruce.png">白木林</label></td></tr>'+ --%>
<%-- 			'<tr><td><input type="radio" onclick="javascript:doPost(1,$(this).next().html())">&nbsp;<label><img src="<%= application.getContextPath() %>/assets/images/Icon/spruce.png">大峭壁</label></td></tr>'+ --%>
<%-- 			'<tr><td><input type="radio" onclick="javascript:doPost(3,$(this).next().html())">&nbsp;<label><label><img src="<%= application.getContextPath() %>/assets/images/Icon/sleep.png">排雲山莊</label></td></tr>'+ --%>
<%-- 			'<tr><td><input type="radio" onclick="javascript:doPost(2,$(this).next().html())">&nbsp;<label><label><img src="<%= application.getContextPath() %>/assets/images/Icon/placeholder.png">玉山西峰</label></td></tr>'+ --%>
<%-- 			'<tr><td><input type="radio" onclick="javascript:doPost(3,$(this).next().html())">&nbsp;<label><label><img src="<%= application.getContextPath() %>/assets/images/Icon/placeholder.png">玉山主峰</label></td></tr>'+ --%>
<%-- 			'<tr><td><input type="radio" onclick="javascript:doPost(5,$(this).next().html())">&nbsp;<label><label><img src="<%= application.getContextPath() %>/assets/images/Icon/sleep.png">圓峰山屋</label></td></tr>'); --%>
			$('#placeHolder').html('第一天行程 : ');
			doPost(1,"<img src='<%= application.getContextPath() %>/assets/images/Icon/hiking.png'>塔塔加登山口");

		
		}if(Road=='R002'){
			$('#placeHolder').html('第一天行程 : ');
			doPost(7,"<img src='<%= application.getContextPath() %>/assets/images/Icon/hiking.png'>東埔登山口");
			
		}if(Road=='R003'){
			$('#placeHolder').html('第一天行程 : ');
			doPost(13,"<img src='<%= application.getContextPath() %>/assets/images/Icon/hiking.png'>南橫公路進涇橋登山口");

		}if(Road=='R004'){
			$('#placeHolder').html('第一天行程 : ');
			doPost(16,"<img src='<%= application.getContextPath() %>/assets/images/Icon/hiking.png'>向陽登山口");

		}if(Road=='E5'){
// 			$('#placeHolder').html('第一天行程 : ');
<%-- 			doPost(19,"<img src='<%= application.getContextPath() %>/assets/images/Icon/hiking.png'>東埔登山入口"); --%>

		}if(Road=='A6'){

		}

	}
/*===========================取得地點===========================================*/
	$(document).ready(function(){
		$("#complete").click(function(){
		var	spot= $("#placeHolder").html();
		$(".trip_schedule").val(spot);
		});
	});
	
	$(document).ready(function(){
		$(".mag").click(function(){
		$("#gp_name").val("冠宏彗星");
		$("#target_loca").val("玉山主峰線");
		$("#satellite").val("+8862345697415");
		$("#radio").val("144.426");
		});
	});
    
	</script>
<!-- JS -->
<script type="text/javascript" src="<%= application.getContextPath()%>/assets/javascripts/index.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>