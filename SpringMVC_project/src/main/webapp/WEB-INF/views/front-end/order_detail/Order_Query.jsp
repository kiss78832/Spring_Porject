<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=" java.util.* "%>
<%@ page import="com.spring.order_detail.model.*"%>
<%@ page import="com.spring.room_order.model.*"%>
<%@ page import="com.spring.member.model.*"%>
<!DOCTYPE html>
<html>

<%
	HashMap<String, String> hm2 = new HashMap<String, String>();
	hm2.put("AlreadyPaid", "1");
	hm2.put("haveNotPaid", "2");
	hm2.put("cancel", "3");
	hm2.put("1", "未繳費");
	hm2.put("2", "已繳費");
	hm2.put("3", "取消訂單");
	application.setAttribute("payment_status_display", hm2);

	//get member_idVO
// 	String member = "A001";
// 	session.setAttribute("member_id", member);
//String member_id = (String) session.getAttribute("member_id");
	
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	String member_id = memberVO.getMember_id();

	Room_orderService room_orderSvc = new Room_orderService();
	List<Room_orderVO> room_orderList = (List<Room_orderVO>) room_orderSvc.getAllByMember_id(member_id);
	pageContext.setAttribute("room_orderList", room_orderList);
%>

<jsp:useBean id="order_detailSvc"
	class="com.spring.order_detail.model.Order_DetailService" />
<jsp:useBean id="memberSvc" class="com.spring.member.model.MemberService" />


<head>
<link rel="icon" type="image/x-icon"
	href="https://i.imgur.com/J0aPP1N.png" />
<title>Island Peak</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/assets/stylesheets/memberContent.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>

<!-- toastJS CSS -->

<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.css">

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>




<style type="text/css">
/*本地字體引入*/
@font-face {
	font-family: Russo;
	src: url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
	unicode-range: U+00-024F;
}

@font-face {
	font-family: Noto;
	src: url('/DA102G1/assets/fonts/NotoSansTC-Regular.otf');
	unicode-range: U+4E00-9FFF;
}

 #cright div { 
 	text-align: center;
 	margin: 15px auto; 
 } 

/* content end*/
.table {
	font-size: 22px;
}

.whichPage {
	font-size: 20px;
}

.rowNumber {
	font-size: 20px;
}

.tableArea{
	padding-top:70px;
}

</style>

</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true" />

			<div class="col-lg-12 contentbox">
				<img
					src="<%=application.getContextPath()%>/assets/images/snowpeak/forest-1743206.jpg">

				<jsp:include page="/WEB-INF/common/memberSideBar.jsp" flush="true" />
				<div id="cright" class="col-lg-10 col-md-10 cb">
					<!-- ==================== -->
					<!-- 右側區塊Table -->
					<!-- ==================== -->


					<!-- 資料顯示 -->
					<div class="col-md-12 col-lg-12 col-sm-12">

						<div class="tableArea">
							<table class="table table-hover table-dark table-striped table-bordered table-responsive-lg">
								<thead>
									<tr>
										<th>訂單編號</th>
										<th>會員姓名</th>
										<th>揪團編號</th>
										<th>訂單價格</th>
										<th>繳費狀態</th>
										<th>訂單產生時間</th>
										<th>預訂天數</th>
										<th>繳費</th>
										<th>取消</th>
									</tr>
								</thead>
								<tbody>
									<%@ include file="page.file"%>
									<c:forEach var="room_orderVO" items="${room_orderList}"
										varStatus="s" begin="<%=pageIndex %>"
										end="<%= pageIndex + rowsPerPage-1 %>">

										<tr>
											<td><A
												href="<%=request.getContextPath()%>/orderDetail.html?order_id=${room_orderVO.order_id}&action=memberGetDetails&whichPage=<%=whichPage%>">${room_orderVO.order_id}</A></td>
											<td class="txt-oflo">${memberSvc.getOneMember(room_orderVO.member_id).m_name}</td>
											<td>${room_orderVO.group_id}</td>
											<td><span class="text-success">${room_orderVO.order_price}</span></td>
											<td class="txt-oflo"><c:if
													test="${room_orderVO.payment_status == 1}">
															${payment_status_display['1']}
													
															</c:if> <c:if test="${room_orderVO.payment_status == 2}">
															${payment_status_display['2']}
													
															</c:if> <c:if test="${room_orderVO.payment_status == 3}">
															${payment_status_display['3']}
													
															</c:if></td>
											<td class="txt-oflo">${room_orderVO.order_time}</td>
											<td class="txt-oflo">${room_orderVO.booking_day}</td>

											<td><c:choose>
													<c:when test="${room_orderVO.payment_status == 1}">
														<a class="btn"
															href="<%=request.getContextPath()%>/front-end/order_detail/payment.jsp">
															<img
															src="<%=request.getContextPath()%>/assets/images/Icon/pay2.png"
															class="payImg">
														</a>
														<input type="hidden" name="order_idUseForPay"
															value="${room_orderVO.order_id}">
													</c:when>

													<c:when test="${room_orderVO.payment_status == 2}">
													已繳費
												</c:when>

													<c:otherwise>
													已取消
												</c:otherwise>

												</c:choose></td>


											<td><img
												src="<%=request.getContextPath()%>/assets/images/Icon/trash2.png"
												class="dataCancelImg">
												<FORM METHOD="post"
													ACTION="<%=application.getContextPath()%>/roomOrder.html"
													class="MemberCancel">
													<input type="hidden" name="order_id"
														value="${room_orderVO.order_id}"> <input
														type="hidden" name="action" value="MemberCancel">
												</FORM></td>

										</tr>

									</c:forEach>

								</tbody>
							</table>

							<div>
								<%
									if (pageNumber > 0) {
								%>
								<b><font color="black" class="whichPage">第<%=whichPage%>/<%=pageNumber%>頁
								</font></b>
								<%
									}
								%>
								<b style="color: black" class="rowNumber">●符 合 查 詢 條 件 如 下 所
									示: 共<font color=red><%=rowNumber%></font>筆
								</b>
							</div>
							<%@ include file="page2.file"%>
						</div>
					</div>
					<!-- 資料顯示 -->

				</div>

			</div>

		</div>

		<c:if test="${openModal!=null}">

			<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
				aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog"  style="max-width:50%!important; text-align:center;">
					<div class="modal-content">

						<div class="modal-body">

							<jsp:include page="MemberOrderDetail.jsp" />

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

		<jsp:include page="/WEB-INF/common/footer.jsp" flush="true" />
	</div>


	<script> 
	
		$('.dataCancelImg').on('click',function(e){
			
			var which = $('.dataCancelImg').index(this);
			
			e.preventDefault();
			Swal.fire({
				  title: '確定要取消訂單嗎?',
				  text: "訂單一但取消後無法重新做出修改",
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: '確定'
				}).then((result) => {
				  if (result.value) {
					  $('.MemberCancel:eq('+which+')').submit();
				  }
				})
			
			
		});
	

		$('.payImg').click( function(){
			
				var which = $('.payImg').index(this);
				var order_id = $('input[name=order_idUseForPay]:eq('+which+')').val();
				
				console.log(which , order_id);
				
				
				 update(order_id, which);
				 

		});
		
	
		function update(order_id,which) {
			$.ajax({
				type : "POST",
				async: false,
				url : "/DA102G1/roomOrder.html",
				data : createQueryString(order_id),
				success: function(data){ 

				 },
				error : function(){
					Swal.fire({
					  type: 'error',
					  title: 'AJAX發生錯誤!',
					  text:'更新狀態失敗!'
					})
					
				}
				 
			});
		}

		function createQueryString(order_id) {
			console.log("order_id:" + order_id );
			var queryString = {
				"action" : "getRoomOrder",
				"order_id" : order_id
				
			};
			return queryString;
		}

		
		$(function(){
			
			connect();
			
		});		
		
		$( window ).unload(function() {
			disconnect();
		});



		var MyPoint = "/Meal/OrderQuery";

		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0,path.indexOf('/',1));
		var endPointURL = "wss://"+window.location.host + webCtx + MyPoint;

		var webSocket;

		function connect(){
			webSocket = new WebSocket(endPointURL);
			
			
			webSocket.onopen = function(event) {
			};

			webSocket.onmessage = function(event) {
				var JSONobj = JSON.parse(event.data);
				
				var action = JSONobj.action;
				
				toastr.options = {
				  "closeButton": true,
				  "debug": false,
				  "newestOnTop": false,
				  "progressBar": true,
				  "positionClass": "toast-top-right",
				  "preventDuplicates": false,
				  "onclick": null,
				  "showDuration": "300",
				  "hideDuration": "5000",
				  "timeOut": "10000",
				  "extendedTimeOut": "10000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "fadeIn",
				  "hideMethod": "fadeOut"
				}
				
				if("insertMeal"=== action){
				toastr.info('新增了一份套餐，快來看看吧!',JSONobj.meal_name +'   售價:'+JSONobj.meal_price);
				}
				
				if("updateMeal"=== action){
					toastr.info('更新了一份套餐，快來看看吧!',JSONobj.meal_name);
				}
				
				
			};

			webSocket.onclose = function(event) {
			
			};
		}


		function sendMessage() {
			
		}

		function disconnect() {
			webSocket.close();
		}
		
		
		

		
	</script>


	<%
		String cancelState = (String) request.getAttribute("cancelState");
		pageContext.setAttribute("cancelState", cancelState);
	%>

	<c:if test="${cancelState eq 'success' }">
		<script>
			$(function(){
					Swal.fire(
					      	'取消成功',
					      	'您的訂單已被取消',
					      	'success'
					    	)
				});
			
		</script>

	</c:if>

	<c:if test="${cancelState eq 'duplicate' }">
		<script>
			$(function(){
					Swal.fire(
					      	'很抱歉!',
					      	'您已取消過此筆訂單',
					      	'error'
					    	)
				});
			
		</script>
	</c:if>



	<%
		String paymentState = (String) request.getAttribute("paymentState");
		pageContext.setAttribute("paymentState", paymentState);
	%>

	<c:if test="${paymentState eq 'success' }">
		<script>
			$(function(){
					Swal.fire(
					      	'繳費成功',
					      	'您的訂單繳費狀態已更新',
					      	'success'
					    	)
				});
			
			</script>
	</c:if>

	<c:if test="${paymentState eq 'fail' }">
		<script>
			$(function(){
					Swal.fire(
					      	'很抱歉!',
					      	'訂單繳費失敗',
					      	'error'
					    	)
				});
			
			</script>
	</c:if>



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