<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=" java.util.* "%>
<%@ page import="com.order_detail.model.*"%>
<%@ page import="com.room_order.model.*"%>
<%@ page import="com.location.model.*"%>
<!DOCTYPE html>
<html>

<%
	HashMap<String,String> hm = new HashMap<String,String>();

	hm.put("orderMakeNotPay", "1");
	hm.put("orderMakePay0", "2");
	hm.put("orderSuccess", "3");
	hm.put("orderNotWork", "4");
	hm.put("orderCancel", "5");

	hm.put("1", "訂單成立未付款 ");
	hm.put("2", "訂單成立已付款");
	hm.put("3", "訂單完成");
	hm.put("4", "無效訂單");
	hm.put("5", "客戶取消訂單");
	application.setAttribute("order_status_display", hm);

	HashMap<String,String> hm2 = new HashMap<String,String>();
	hm2.put("AlreadyPaid", "1");
	hm2.put("haveNotPaid", "2");
	hm2.put("cancel", "3");
	hm2.put("1", "未繳費");
	hm2.put("2", "已繳費");
	hm2.put("3", "取消訂單");
	application.setAttribute("payment_status_display", hm2);

	Room_orderService room_orderSvc = new Room_orderService();
	List<Room_orderVO> room_orderList = (List<Room_orderVO>)request.getAttribute("paymentStatusList");
	pageContext.setAttribute("room_orderList", room_orderList);
	
	List<String> member_idList = room_orderSvc.getAllDifferentMember();
	pageContext.setAttribute("member_idList", member_idList);
	
	List<String> group_idList = room_orderSvc.getAllDifferentGroup();
	pageContext.setAttribute("group_idList", group_idList);
	
	LocationService locationSvc = new LocationService();
	List<LocationVO> locList = locationSvc.getAll();
	pageContext.setAttribute("locList", locList);
	
%>

<jsp:useBean id="order_detailSvc"
	class="com.order_detail.model.Order_DetailService" />
<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />	
	

<head>
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>Island Peak admin</title>
	<meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/backgroundContext.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- canvasJS jquery line chart -->
<script type="text/javascript"
	src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
<!-- ========== -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>

<!-- toastr -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.css">

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>


<!-- right side css -->
<link rel="stylesheet" type="text/css"
	href="<%= application.getContextPath() %>/assets/stylesheets/OrderMge.css">

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

#cright div {
/* 	border: 2px solid red; */
	margin: 15px auto;
}

/* content end*/
</style>

</head>

<body>
	<div class="container-fluid">
		<div class="row">


		<jsp:include page="/common/adminNavbar.jsp" flush="true"/>

	<div class="col-lg-12 contentbox" >
	  <img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">
	
	  
		<jsp:include page="/common/adminSideBar.jsp" flush="true"/>
		
				<div id="cright" class="col-lg-10 col-md-10 cb rightContent">
					<!-- ==================== -->
					<!-- 右側區塊Table -->
					<!-- ==================== -->
					<div class="cb col-lg-12">
						<div class="row">
							<div class="col-lg-4 col-xs-12">
								<div class="topBox">
									<h3 class="">尚未繳費訂單</h3>
									<ul class="list-inline two-part">
										<li>
											<div id="sparklinedash"></div>
										</li>
										<li class="text-center"><i
											class="ti-arrow-up text-success"></i> <span
											class="counter text-success"
											data-count="<%=room_orderSvc.getAllByNotPaid()%>" id="notPaid">0</span></li>
									</ul>
								</div>
							</div>
							<div class="col-lg-4 col-xs-12">
								<div class="topBox">
									<h3 class="">已繳費訂單</h3>
									<ul class="list-inline two-part">
										<li>
											<div id="sparklinedash2"></div>
										</li>
										<li class="text-center"><i
											class="ti-arrow-up text-purple"></i> <span
											class="counter text-purple"
											data-count="<%=room_orderSvc.getAllByAlreadyPaid()%>"  id="paid">0</span></li>
									</ul>
								</div>
							</div>
							<div class="col-lg-4 col-xs-12">
								<div class="topBox">
									<h3 class="">本日產生訂單</h3>
									<ul class="list-inline two-part">
										<li>
											<div id="sparklinedash3"></div>
										</li>
										<li class="text-center"><i class="ti-arrow-up text-info"></i>
											<span class="counter text-info" data-count="<%=room_orderSvc.getCountToday()%>"  id="todayOrder">0</span></li>
									</ul>
								</div>
							</div>
						</div>

						<!-- 折線圖選擇據點 -->

						<!-- 折線圖待處理 -->
						<div class="row">
							<div class="chartContainer"></div>
						</div>
						<!-- 待處理 -->

						<!-- 資料顯示 -->
						<div class="row">
							<div class="col-md-12 col-lg-12 col-sm-12">
							
									<div class="filterList row">
										
										<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/orderDetail.html"
													style="color: black;" id="getMemberId">
										 <input type="hidden" name="action" value="getMemberId">
											<select class="form-control1"  name="member_id" id="member_idSelect">
												<option disabled="disabled" selected>請選擇會員編號</option>
													<c:forEach var="member_id" items="${member_idList}">
														<option value="${member_id}">${member_id}
													</c:forEach>
											</select> 
										</FORM>
										
										<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/orderDetail.html"
													style="color: black;" id="getDateRange">
										 <input type="hidden" name="action" value="getDateRange">
											<select class="form-control1"  name="date_range" id="dateRange_idSelect">
												<option disabled="disabled" selected>請選擇日期範圍</option>
												<option value="1">本日</option>
												<option value="3">三天內</option>
												<option value="7">一周內</option>
												<option value="30">一個月內</option>
											</select> 
										</FORM>
										
										<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/orderDetail.html"
													style="color: black;" id="getGroupId">
										 <input type="hidden" name="action" value="getGroupId">
											<select class="form-control1"  name="group_id" id="group_idSelect">
												<option disabled="disabled" selected>請選擇揪團編號</option>
													<c:forEach var="group_id" items="${group_idList}">
														<option value="${group_id}">${group_id}
													</c:forEach>
											</select> 
										</FORM>
										
										<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/orderDetail.html"
													style="color: black;" id="getAllByOrderStatus">
										 <input type="hidden" name="action" value="getAllByOrderStatus">
										 <select class="form-control1"  name="order_status" id="order_status">
											<option disabled="disabled" selected>請選擇訂單狀態</option>
											<option value="1">訂單成立未付款 </option>
											<option value="2">訂單成立已付款</option>
											<option value="3">訂單完成</option>
											<option value="4">無效訂單</option>
											<option value="5">客戶取消訂單</option>
										</select>
										</FORM>
										<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/orderDetail.html"
													style="color: black;" id="getAllByPaymentStatus">
										 <input type="hidden" name="action" value="getAllByPaymentStatus">
										 <select class="form-control1" name="payment_status" id="payment_status">
											<option disabled="disabled" selected>請選擇繳費狀態</option>
											<option value="1">未繳費</option>
											<option value="2">已繳費</option>
										</select>
										</FORM>
										
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/roomOrder.html"
									style="color: black;" id="locSelectForChart">
									<input type="hidden" name="action" value="locSelectForChart"> <select
										size="1" name="location_id" class="form-control1"
										id="location_id">
										<option disabled="disabled" selected>請選擇據點
											<c:forEach var="locationVO" items="${locList}">
												<option value="${locationVO.location_id}">${locationVO.location_name}
											</c:forEach>
									</select>
								</form>
										
										
										 <FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/orderDetail.html"
												style="color: black;">
										 <input type="hidden" name="action" value="getAll">
										 <input type="submit" value="查詢全部" class="form-control1">
										 </FORM>
									</div>
									<div class="">
										<table class="table table-hover table-dark table-striped table-bordered table-responsive-lg">
											<thead>
												<tr>
													<th>訂單編號</th>
													<th>會員編號</th>
													<th>揪團編號</th>
													<th>訂單價格</th>
													<th>訂單狀態</th>
													<th>繳費狀態</th>
													<th>訂單產生時間</th>
													<th>預訂天數</th>
													<th>修改狀態</th>
												</tr>
											</thead>
											<tbody>
												<%@ include file="page.file"%>
												<c:forEach var="room_orderVO" items="${room_orderList}"
													varStatus="s" begin="<%=pageIndex %>"
													end="<%= pageIndex + rowsPerPage-1 %>">

													<tr>
														<td><A
															href="<%=request.getContextPath()%>/orderDetail.html?order_id=${room_orderVO.order_id}&action=getDetails&whichPage=<%=whichPage%>">${room_orderVO.order_id}</A></td>
														<td class="txt-oflo">${memberSvc.getOneMember(room_orderVO.member_id).m_name}</td>
														<td>${room_orderVO.group_id}</td>
														<td><span class="text-success">${room_orderVO.order_price}</span></td>
														<td class="txt-oflo"><c:if
																test="${room_orderVO.order_status == 1}">
															${order_status_display['1']}
													
															</c:if> <c:if test="${room_orderVO.order_status == 2}">
															${order_status_display['2']}
														
															</c:if> <c:if test="${room_orderVO.order_status == 3}">
															${order_status_display['3']}
													
															</c:if> <c:if test="${room_orderVO.order_status == 4}">
															${order_status_display['4']}
													
															</c:if> <c:if test="${room_orderVO.order_status == 5}">
															${order_status_display['5']}
													
															</c:if></td>
														<td class="txt-oflo"><c:if
																test="${room_orderVO.payment_status == 1}">
															${payment_status_display['1']}
													
															</c:if> <c:if test="${room_orderVO.payment_status == 2}">
															${payment_status_display['2']}
													
															</c:if><c:if test="${room_orderVO.payment_status == 3}">
															${payment_status_display['3']}
													
															</c:if></td>
														<td class="txt-oflo">${room_orderVO.order_time}</td>
														<td class="txt-oflo">${room_orderVO.booking_day}</td>

														<td>														<c:choose>
														 <c:when test="${room_orderVO.order_status != 4 && room_orderVO.order_status != 5}">
															<img
															src="<%=request.getContextPath()%>/assets/images/Icon/edit2.png"
															class="dataEditImg">
															<FORM METHOD="post"
																ACTION="<%=application.getContextPath()%>/orderDetail.html"
																class="getOne_For_Update">
																<input type="hidden" name="order_id"
																	value="${room_orderVO.order_id}"> <input
																	type="hidden" name="action" value="getOne_For_Update">
															</FORM>
														</c:when>
														<c:otherwise>
															已取消
														</c:otherwise> 
														</c:choose>
															</td>

													</tr>

												</c:forEach>

											</tbody>
										</table>

										<div>
											<%
												if (pageNumber > 0) {
											%>
											<b><font color=red>第<%=whichPage%>/<%=pageNumber%>頁
											</font></b>
											<%
												}
											%>
											<b style="color: black">●符 合 查 詢 條 件 如 下 所 示: 共<font
												color=red><%=rowNumber%></font>筆
											</b>
										</div>
										<%@ include file="page2.file"%>
									</div>
								</div>
						
						</div>
					</div>
					<!-- 資料顯示 -->

				</div>

			</div>

		</div>

		<c:if test="${openModal!=null}">

			<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
				aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">

						<div class="modal-body">
							<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
							<jsp:include page="OrderDetail.jsp" />
							<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Save
								changes</button>
						</div>

					</div>
				</div>
			</div>

			<script>
				$("#basicModal").modal({
					show : true
				});
			</script>
		</c:if>

<jsp:include page="/common/footer.jsp" flush="true"/>
	</div>

	<script>
		var coll = $(".collapsible");
		var i;

		for (i = 0; i < coll.length; i++) {
			coll[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var content = this.nextElementSibling;
				if (content.style.display === "block") {
					content.style.display = "none";
				} else {
					content.style.display = "block";
				}
			});
		}
	</script>

	<script> 

		$('.dataEditImg').click( function(e){
			e.preventDefault();
			 Swal.fire({
			  title: '更改訂單狀態',
			  input: 'select',
			  inputOptions: {
				 1: '訂單成立未付款',
				  2: '訂單成立已付款',
				  3: '訂單完成',
				 4: '無效訂單',
				  5: '客戶取消訂單'
			  }, 
			  inputPlaceholder: 'Select a order_status',
			  showCancelButton: true,
			 }).then((result) => {
				 if(result.value != null){
					 
					 	var which = $('.dataEditImg').index(this);
					 	var order_id = $('input[name=order_id]:eq('+which+')').val();
					 	var order_status = result.value;
					 	console.log(which+order_id+order_status);

					 	update(order_id,order_status);
					 }
			 });

		});
		
	
		function update(order_id,order_status) {
			$.ajax({
				type : "POST",
				url : "/DA102G1/orderDetail.html",
				data : createQueryString2(order_id,order_status),
				dataType : "json",
				success : function(data) {
					console.log(data.order_status);
					var status='';
					
					switch(data.order_status) {
					  case '1':
						  status='訂單成立未付款';
					    break;
					  case '2':
						  status='訂單成立已付款';
					    break;
					  case '3':
						  status='訂單完成';
						    break;
					  case '4':
						  status='無效訂單';
						    break;
					  case '5':
						  status='客戶取消訂單';
						    break;
					}
					
					console.log(status);
					Swal.fire(
						      '送出成功',
						      '你已將訂單'+data.order_id+'更新為'+ status+'!',
						      'success'
						    )
					window.setTimeout(function() {
						window.location.href = '<%=request.getContextPath()%>/back-end/order_detail/OrderMge.jsp';
					}, 1000);
				},
				error :  function(){
					Swal.fire({
						  type: 'error',
						  title: 'AJAX-class發生錯誤!',
						  text:'更新狀態失敗!'
						})
					}
			});
		}

		function createQueryString2(order_id,order_status) {
			console.log("order_id:" + order_id + ";order_status" +order_status );
			var queryString = {
				"action" : "getOne_For_Update",
				"order_id" : order_id,
				"order_status" : order_status,
				
			};
			return queryString;
		}
		
		
		
		
	</script>


	<script
		src="<%= application.getContextPath() %>/assets/javascripts/OrderMge.js"></script>



	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>

</html>