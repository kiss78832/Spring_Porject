<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.spring.equipment.model.*"%>
<%@ page import="com.spring.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spring.rentodlist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
MemberDAO dao = new MemberDAO();
memberVO = dao.findByPrimaryKey(memberVO.getMember_id());
pageContext.setAttribute("memberVO", memberVO);
%>


<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
<meta charset="utf-8">
<title>Island Peak</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<!-- body -->
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/assets/stylesheets/body.css">

<script>
	$(function() {
		$('[data-toggle="tooltip"]').tooltip()
	})
</script>
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
	text-align: center;
}
/*centerbox end*/

/* content end*/
body {
	background: #f5f5f5;
	color: black;
}

.rounded-lg {
	border-radius: 1rem;
}

.nav-pills .nav-link {
	color: #555;
}

.nav-pills .nav-link.active {
	color: #fff;
}
.tab-content {
	color: black;
}
</style>

</head>
<body>

	<jsp:include page="/WEB-INF/common/signup.jsp" flush="true" />

	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true" />



			<div class="col-lg-12 contentbox">


				<div id="centerbox" class="container-fluid m-auto">

					<%
						Vector<EquipmentVO> buylist = (Vector<EquipmentVO>) session.getAttribute("shoppingcart");
						String amount = (String) session.getAttribute("amount");
					%>
					<%
					RentOdListVO rentodlistvo = (RentOdListVO) session.getAttribute("rentodlistvo");
					%>


					<div class="container py-5">

						<!-- For demo purpose -->
						<div class="row mb-4">
							<div class="col-lg-8 mx-auto text-center">
								<h1 class="display-4">訂單表格</h1>
							</div>
						</div>
						<!-- End -->


						<div class="row">
							<div class="col-lg-7 mx-auto">
								<div class="bg-white rounded-lg shadow-sm p-5">
									<!-- Credit card form tabs -->
									<ul role="tablist"
										class="nav bg-light nav-pills rounded-pill nav-fill mb-3">
										<li class="nav-item"><a data-toggle="pill"
											href="#nav-tab-card" class="nav-link active rounded-pill">
												<i class="fa fa-credit-card"></i> 信用卡
										</a></li>
									</ul>
									<!-- End -->


									<!-- Credit card form content -->
									<div class="tab-content">

										<!-- credit card info-->
										<div id="nav-tab-card" class="tab-pane fade show active">
											<p class="alert alert-success">
												<c:if test="${not empty errorMsgs}">
													<font style="color: red">請修正以下錯誤:</font><br>
													
														<c:forEach var="message" items="${errorMsgs}">
															<font style="color: red">${message}</font><br>
														</c:forEach>
													
												</c:if>
											</p>
											<form role="form"
												action="<%=request.getContextPath()%>/rentodlist/RentodlistServlet.do"
												method="POST">
												<div class="form-group">
													<label for="username">姓名</label> <input type="text"
														name="rent_name" id="formname" placeholder="${memberVO.m_name}" required
														class="form-control">
												</div>
												<div class="form-group">
													<label for="username">電話</label> <input type="phone"
														name="rent_phone" id="formphone" placeholder="${memberVO.cellphone}" required
														class="form-control">
												</div>
												<div class="form-group">
													出租日<input value="<%=rentodlistvo.getRsved_rent_date()%>" disabled >
													歸還日<input value="<%=rentodlistvo.getEx_return_date()%>" disabled >
												</div>
										</div>

										<div class="form-group">
											<label for="cardNumber">卡號</label>
											<div class="input-group">
												<input type="text" name="cardNumber" id="formcardnum"
													placeholder="Your card number" class="form-control"
													required>
												<div class="input-group-append">
													<span class="input-group-text text-muted"> <i
														class="fa fa-cc-visa mx-1"></i> <i
														class="fa fa-cc-amex mx-1"></i> <i
														class="fa fa-cc-mastercard mx-1"></i>
													</span>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-8">
												<div class="form-group">
													<label><span class="hidden-xs">到期日</span></label>
													<div class="input-group">
														<input type="number" placeholder="MM" name="" id="formupdate1"
															class="form-control" required> <input
															type="number" placeholder="YY" name="" id="formupdate2"
															class="form-control" required>
													</div>
												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group mb-4">
													<label data-toggle="tooltip"
														title="Three-digits code on the back of your card">安全碼
														<i class="fa fa-question-circle"></i>
													</label> <input type="text" id="formsafe" required class="form-control">
												</div>
											</div>



										</div>
										<input type="hidden" name="rent_payment" value="信用卡">
										<input type="hidden" name="od_total_price" value="<%=amount %>">
										<input type="hidden" name="od_status" value="2"> <input
											type="hidden" name="member_id" value="${memberVO.member_id}"> <input
											type="hidden" name="action" value=CHECKOUT>
										<button type="submit"
											class="subscribe btn btn-primary btn-block rounded-pill shadow-sm">
											Confirm</button>
										</form>
									</div>


 <input type="button" class="lazy" value="懶人按鈕">


							</div>
						</div>
					</div>
				</div>



	







			</div>

		</div>






		<jsp:include page="/WEB-INF/common/footer.jsp" flush="true" />

	</div>
	</div>









<script>
	$(document).ready(function(){
		$(".lazy").click(function(){
		$("#formname").val("david");
		$("#formphone").val("0912345678");
		$("#formcardnum").val("5111005111051128");
		$("#formupdate1").val("11");
		$("#formupdate2").val("05");
		$("#formsafe").val("825");
		});
	});
	</script>






	<!-- JS -->
	<script type="text/javascript"
		src="<%=application.getContextPath()%>/assets/javascripts/index.js"></script>

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