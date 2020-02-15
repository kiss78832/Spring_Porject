<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.equipment.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.rentodlist.model.RentOdListVO"%>
<%@ page import="com.rentoddetail.model.*"%>
<%
	RentOdListVO rolVO = (RentOdListVO) request.getAttribute("rolVO");
  List<RentOdDetailVO> rodlist = (List<RentOdDetailVO>) request.getAttribute("rodlist");
  EquipmentService equSVC =new EquipmentService();
	Integer count = 0; 
	
	HashMap<String, Integer> hm = new HashMap<String, Integer>();
	hm.put("訂單成立",0);
	hm.put("取消訂單",1);
	hm.put("出租中",2);
	hm.put("未歸還",3);
	hm.put("已歸還 訂單結束",4);

	application.setAttribute("hm", hm);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>共用頁面</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- body&footer -->
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
/*左側區塊*/
#cleft{
background-color: rgba(52,53,55,0.2);
border: 1px solid rgba(150,150,150,0.5);
height: 1250px;
float: left;
}
#cleft div{
border:2px solid red;
 margin: 15px auto;
}

/*右側區塊*/
#cright{
background-color: rgba(72,73,75,0.9);
border: 1px solid rgba(150,150,150,1);
height:100%;
float: left;
}
#cright div{
  border:2px ;
 margin: 15px auto;
}
  /* content end*/









@media screen and (max-width : 480px) {
	body {
		font-size:  1.5rem;
	}
}

table {
	display: flex;
	flex-flow: column nowrap;
	line-height: 1.25;
	border: 0;
}

caption {
	padding: 1rem;
	font-size: 1.5rem;
	font-weight: 900;
	color: #999;
	caption-side: top;
}

caption span {
	font-size: 1.25rem;
	font-weight: 300;
	color: #A52A2A;
}

tr {
	display: flex;
}

tr:nth-of-type(odd) {
	background-color: #E5E5E5;
}

th {
	font-weight: 700;
	background-color: #f2f2f2;
	color: #666;
}

th,
td {
	display: flex;
	flex: 1 0;
	align-items: center;
	justify-content: center;
	padding: 0.5rem 0.1rem;
	border: 1px solid #BFBFBF;
	border-collapse: collapse;
	border-spacing: 0;
}

/* This will affect only Chrome browsers 29+
	* See: http://browserhacks.com/
	* Hack because 'hyphens: auto;' is supported in Chrome only on Android & Mac 
	* and word-break 'overrides' hyphens in Firefox
	*/
.selector:not(*:root), th, td {
	word-wrap: break-word;
	overflow-wrap: break-word;
	word-break: break-all;
}

/* Breaking the numbers! */
.numbers {
	word-break: break-word;
}



















  </style>

</head>
<body>
	<div class="container-fluid">
	  <div class="row">
		
		<jsp:include page="/common/navbar.jsp" flush="true"/>
			
			<div class="col-lg-12 contentbox" >
			
			  
			  <div id="cleft" class="col-lg-2 col-md-2 cb">
			<!-- 左側區塊 可放按鈕等等 -->
			    <div class="cb col-lg-12">看你要什麼選項等.....</div>
			    
			  </div>
			  <div id="cright" class="col-lg-10 col-md-10 cb">
			    
			     <div class="cb col-lg-12">你要管理的內容（已置中,寬度內距已調）</div>
			     
			     
			     

	<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rentodlistbackend/rentodlistbackend.do" >
<table style="color:black;">
	<tr>
		<td>租借訂單編號:<font color=red><b>*</b></font></td>
		<td><%=rolVO.getRent_odnum()%></td>
		<input type="hidden" name="rent_odnum" value="${rolVO.rent_odnum}">
	</tr>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td></td>
		<td><%=rolVO.getMember_id()%></td>
		<input type="hidden" name="member_id"  value="${rolVO.member_id}" >
	</tr>
	<tr>
		<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="rent_name"  value="${rolVO.rent_name}"></td>
	</tr>
			<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="rent_phone"  value="${rolVO.rent_phone}"></td>
	</tr>
	<tr>
		<td>訂單產生時間:</td><font color=red><b>*</b></font></td>
		<td><%=rolVO.getOdlist_createdate()%></td>
		<input type="hidden" name="odlist_createdate" value="${rolVO.odlist_createdate}">
	</tr>
	<tr>
		<td>預約出租日期:</td><font color=red><b>*</b></font></td>
		<td><%=rolVO.getRsved_rent_date()%></td>
		<input type="hidden" name="rsved_rent_date" id="f_date1"">
	</tr>
	<tr>
		<td>實際出租日期:</td><font color=red><b>*</b></font></td>
		<td><%=rolVO.getReal_rent_date()%></td>
		<input type="hidden" name="real_rent_date" id="f_date2"">
	</tr>
	<tr>
		<td>預計歸還日期:</td><font color=red><b>*</b></font></td>
		<td><%=rolVO.getEx_return_date()%></td>
		<input type="hidden" name="ex_return_date" id="f_date3"">
	</tr>
	<tr>
		<td>實際歸還日期:</td><font color=red><b>*</b></font></td>
		<td><%=rolVO.getReal_return_date()%></td>
		<input type="hidden" name="real_return_date" id="f_date4"">
	</tr>
	
	<tr>
		<td>訂單狀態:</td>
		<td><select size="1" name="od_status" class="form-control1">
			<option disabled="disabled" selected>訂單狀態
			<option value="${hm['訂單成立']}">訂單成立											
			<option value="${hm['取消訂單']}">取消訂單
			<option value="${hm['出租中']}">出租中
			<option value="${hm['未歸還']}">未歸還
			<option value="${hm['已歸還 訂單結束']}">已歸還 訂單結束													
			</select> </td>
	</tr>
		<tr>
		<td>付款模式:</td>
		<td><input type="TEXT" name="rent_payment" size="45" value="${rolVO.rent_payment}"></td>
	</tr>
		</tr>



	<c:forEach var="rodVO" items="${rodlist}">	
		<tr>
		<td><c:forEach var="equVO" items="<%=equSVC.getAll()%>">  
            
                    <c:if test="${rodVO.eq_num==equVO.eq_num}">
                    <% count++; %> 
	              ${equVO.eq_name} - ${equVO.eq_size}      
                    </c:if>
                </c:forEach>
		<td><input type="TEXT" name="quantity<%=count - 1%>" size="45" value="${rodVO.quantity}"></td>
	</tr>
	</c:forEach>		
	<tr>
		<td>總金額:<font color=red><b>*</b></font></td>
		<td><%=rolVO.getOd_total_price()%></td>
		<input type="hidden" name="od_total_price" size="45" value="${rolVO.od_total_price}">


</table>
<br>
<input type="hidden" name="action" value="update">
<%-- <input type="hidden" name="eq_num"  value="<%=equVO.getEq_num()%>"> --%>
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> 
<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  
<input type="submit" value="送出修改"></FORM>
			     
			     
			     
			     
			     
			     
			     
			     
			     
			     
			     
			
			  </div>
			
			</div>
	
	
		<jsp:include page="/common/footer.jsp" flush="true"/>
	
	
	  </div>
	</div>


<!-- JS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/sideBar.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>






<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/rentodlist/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/rentodlist/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/rentodlist/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=rolVO.getRsved_rent_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=rolVO.getReal_rent_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=rolVO.getEx_return_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date4').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=rolVO.getReal_return_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   

        
</script>











</html>