<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.equipment.model.*"%>
<%@ page import="com.member.model.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/x-icon"
	href="https://i.imgur.com/J0aPP1N.png" />
<title>Island Peak</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>



<link   rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/vendor/assets/stylesheets/jquery.datetimepicker.css" />
<script src="<%= application.getContextPath()%>/vendor/assets/javascripts/jquery.datetimepicker.full.js"></script>
<script src="<%= application.getContextPath()%>/vendor/assets/javascripts/scrdatepicker.js"></script>


<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">






<style type="text/css">
/*本地字體引入*/
@font-face {
	font-family: Russo;
	src: url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
	unicode-range: U+00-024F;
}

@font-face {
	font-family: Noto;
	src: url('/DA102G1/assets/fonts/NotoSansTC-Medium.otf');
	unicode-range: U+4E00-9FFF;
}

/* content start*/
.contentbox {
	margin-top: 80px;
	box-sizing: border-box;
	background-image:
		url(/DA102G1/assets/images/snowpeak/forest-1743206.jpg);
	background-size: cover;
	height: 1250px;
	padding: 0px;
}

.contentbox .cb { /*contentbox 底下的div*/
	display: inline-block;
	position: relative;
}

/*centerbox start*/
#centerbox {
	width: 90%;
	background-color: rgba(52, 53, 55, 0.7);
	border: 1px solid rgba(150, 150, 150, 0.5);
	height: 1250px;
}

#centerbox div {
	margin-top: 15px;
	border: 2px;
}
/*centerbox end*/

/* content end*/
#cart {
	color: white;
}

.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

</head>
<body>
	<%
		Vector<EquipmentVO> buylist = (Vector<EquipmentVO>) session.getAttribute("shoppingcart");
		String amount = (String) session.getAttribute("amount");
	%>
	<%
		if (buylist != null && (buylist.size() > 0)) {
			int count = 0;
	%>

		<jsp:include page="/common/signup.jsp" flush="true" />

		<div class="container-fluid">
			<div class="row">

				<jsp:include page="/common/navbar.jsp" flush="true" />



				<div class="col-lg-12 contentbox">


					<div id="centerbox" class="container-fluid m-auto">


						<div class="container">
							<form name="checkoutForm"
		action="<%=request.getContextPath()%>/equipment/ShoppingCartServlet.do"
		method="POST">
							<table id="cart" class="table table-hover table-condensed"
								border="1px">
								<thead>
								
									<tr>
										<th style="width: 50%">商品</th>
										<th style="width: 10%">價格</th>
										<th style="width: 8%">數量</th>
										<th style="width: 8%">規格</th>
										<th style="width: 22%" class="text-center">總計</th>
										<th style="width: 15%"></th>
									</tr>
								</thead>



								<%
									for (int index = 0; index < buylist.size(); index++) {
											EquipmentVO order = buylist.get(index);
											
											count++;
								%>
								<tbody>

									<tr>

										<td data-th="Product">

											<div class="row">

												<div class="col-sm-2" height="100px" weight="100px" hidden-xs>
													<img
														src="<%=request.getContextPath()%>/equipment/DBGifReader4.do?eq_num=<%=order.getEq_num()%>"
														alt="..." class="img-responsive" height="100px"
														weight="100px" />
												</div>
												<div class="col-sm-10">
													<h4 class="nomargin"><%=order.getEq_name()%>
													</h4>
													<p>deatail</p>
												</div>
											</div>
										</td>

										<td data-th="Price" style="vertical-align: middle;"><%=order.getEq_price()%></td>
										<td data-th="Quantity" style="vertical-align: middle;"><input
											type="number" class="zero" name="quantity<%=count - 1%>"
											value="<%=order.getQuantity()%>"  ></td>
										<td data-th="Size" style="vertical-align: middle;"><%=order.getEq_size()%></td>
										<td data-th="Subtotal" class="text-center"
											style="vertical-align: middle;"><%=order.getEq_price() * order.getQuantity()%></td>
										<td class="actions" data-th="" style="vertical-align: middle;">

												<button class="btn btn-danger btn-sm" onclick = "checkremove()">
													<i class="fa fa-trash-o"></i>
												</button>
												<input type="hidden" name="action" value="DELETE" class="del">
												<input type="hidden" name="del" value="<%=index%>">
												<input type="hidden" name="requestURL" value="<%=request.getContextPath()+request.getServletPath()%>">
										</td>
									</tr>
								</tbody>
								<%
									}
								%>
								<tfoot>

									<tr>
										<td><a
											href="<%=request.getContextPath()%>/front-end/equipment/renthome.jsp"
											class="btn btn-warning"><i class="fa fa-angle-left"></i>
												Continue Shopping</a></td>
										<td colspan="4" class="hidden-xs">租借日<input
											name="rsved_rent_date" id="f_date1" type="text"><br>
											歸還日<input name="ex_return_date" id="f_date2" type="text">
										</td>


										<td><input type="hidden" name="action" value="CHECKOUT" class="checkout"> 
											<input type="submit" value="Checkout" class="btn btn-success btn-block" onclick = "delremove()">
											</td>

									</tr>

								</tfoot>

							</table>
	</form>
	<%
		}
	%>
	</div>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<br>

		<c:forEach var="message" items="${errorMsgs}">
			<font style="color: red">${message}</font>
			<br>
		</c:forEach>

	</c:if>

	</div>

	</div>


	<jsp:include page="/common/footer.jsp" flush="true" />

	</div>
	</div>


	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script>
		$.datetimepicker.setLocale('zh');
		$('#f_date1').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : false, //timepicker:true,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d', //format:'Y-m-d H:i:s',
			value : new Date(),

		});
		$('#f_date2').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : false, //timepicker:true,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d', //format:'Y-m-d H:i:s',
			value : new Date(),

		});
		
		

             var somedate1 = new Date('2019-09-18');
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});
             
             
             

             var somedate1 = new Date('2019-09-18');
             $('#f_date2').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});
		
		
		

		function checkremove() {
			$(".checkout").remove();
		}
		function delremove() {
			$(".del").remove();
		}
		
		
// 			var x = document.getElementsByClassName("zero");
// 			x[0].onclick = smallzero;
// 			for(int i = 0 ;i<x.length;i++){
// 				x[i].onchange = smallzero;
// 			}
// 			 function smallzero(e){
// 				 alert(123);
// 				 var x = document.getElementsByClassName("zero");
// 				 if(x[0].value <1)
// 					 x[0].value = 1;
// 			 } 
// 		}
								$(".zero").keyup(function(){
									var a=$(this).val();
									
									var b=$(this).parent().prev().text()
									
									var c=$(this).parent().next().next().text(a*b)
									
									
								});
	</script>

</body>
</html>