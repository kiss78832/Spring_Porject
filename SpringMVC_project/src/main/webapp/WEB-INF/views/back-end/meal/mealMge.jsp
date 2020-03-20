<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=" java.util.* "%>
<%@ page import="com.spring.meal.model.*"%>
<!DOCTYPE html>

<%
	MealService mealSvc = new MealService();
	List<MealVO> list = mealSvc.getAll();
	pageContext.setAttribute("list", list);

	HashMap<String,String> hm = new HashMap<String,String>();
	hm.put("canProvide", "1");
	hm.put("notProvide", "2");
	hm.put("all", "3");
	hm.put("1", "可出餐");
	hm.put("2", "暫停供應");
	application.setAttribute("meal_status_Display", hm);
%>

<html>
<head>
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>Island Peak admin_Index</title>
	<meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/backgroundContext.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- canvasJS jquery line chart -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- ========== -->

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>



<!-- JS -->

<!-- right side css -->
<link rel="stylesheet" type="text/css"
	href="<%= application.getContextPath() %>/assets/stylesheets/MealMge.css">

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





/*右側區塊*/
#cright {
	background-color: rgba(72, 73, 75, 0.9);
	border: 1px solid rgba(150, 150, 150, 1);
	height: 100%;
	float: left;
}

#cright div {
	/*border: 2px solid red;*/
	margin: 5px auto 5px auto;
}

/* content end*/
</style>

</head>

<body>
	<div class="container-fluid">
		<div class="row">
<jsp:include page="/WEB-INF/common/adminNavbar.jsp" flush="true"/>

	<div class="col-lg-12 contentbox" >
	  <img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">
	
	  
		<jsp:include page="/WEB-INF/common/adminSideBar.jsp" flush="true"/>
		
				<div id="cright" class="col-lg-10 col-md-10 cb rightContent">
					<!-- ==================== -->
					<!-- 右側區塊Table -->
					<!-- ==================== -->
					<div class="cb col-lg-12">
						<!-- 資料顯示 -->
						<div class="row">
							<div class="col-md-12 col-lg-12 col-sm-12">
								
									<div class="filterList row">
										<div class="mealSelect">
											<div class="mealSelect_inner">
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/meal.html"
													style="color: black;" id="getSelect">
													<input type="hidden" name="action" value="getSelect">
													<select size="1" name="meal_id" class="form-control1"
														id="meal_id">
														<option disabled="disabled" selected>請選擇套餐
															<c:forEach var="mealVO" items="${list}">
																<option value="${mealVO.meal_id}">${mealVO.meal_name}
															</c:forEach>
													</select>
												</FORM>
											</div>
											<div class="mealSelect_inner2">
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/meal.html"
													style="color: black;" id="getAllByStatus">
													<select size="1" name="meal_status" class="form-control1"
														id="meal_status">
														<option disabled="disabled" selected>套餐狀態
														<option value="${meal_status_Display['canProvide']}">可出餐


														
														<option value="${meal_status_Display['notProvide']}">暫停供應


														
														<option value="${meal_status_Display['all']}">全部套餐


														
													</select> <input type="hidden" name="action" value="getAllByStatus">
												</FORM>
											</div>
											<div class="mealSelect_inner3">
												<button id="addNew">新增套餐</button>
											</div>
										</div>

									</div>
									<div class="page">
										<table class="table table-hover table-dark table-striped table-bordered table-responsive-lg " style="color:white;">
											<thead>
												<tr>
													<th>套餐編號</th>
													<th>套餐名稱</th>
													<th>套餐價錢</th>
													<th>套餐狀態</th>
													<th>套餐內容</th>
													<th>套餐圖片</th>
													<th>修改</th>
													<th>刪除</th>
												</tr>
											</thead>
											<tbody>
												<%@ include file="page.file"%>
												<c:choose>
													<c:when test="${status_selected == '2' }">
														<%
															System.out.println("runnn");
														%>
														<c:forEach var="mealVO" items="${list}"
															begin="<%=pageIndex %>"
															end="<%= pageIndex + rowsPerPage-1 %>">
															<tr>
																<td>${mealVO.meal_id}</td>
																<td>${mealVO.meal_name}</td>
																<td>${mealVO.meal_price}</td>
																<td><c:if test="${mealVO.meal_status == 1}">
															${meal_status_Display['1']}
													
														</c:if> <c:if test="${mealVO.meal_status == 2}">
															${meal_status_Display['2']}
														
														</c:if></td>
																<td>${mealVO.meal_content}</td>
																<td style="width: 15%"><img
																	src="<%=request.getContextPath()%>/getMealPic?meal_id=${mealVO.meal_id}"
																	style="width: 100%"></td>
																<td>

																	<FORM METHOD="post"
																		ACTION="<%=application.getContextPath()%>/meal.html">
																		<input type="image" name="clickUpdate"
																			value="clickUpdate"
																			src="<%=request.getContextPath()%>/assets/images/Icon/edit2.png"
																			class="dataEditImg"> <input type="hidden"
																			name="meal_id" value="${mealVO.meal_id}"> <input
																			type="hidden" name="action" value="getOne_For_Update">
																	</FORM>
																</td>

																<td>
																	<FORM METHOD="post"
																		ACTION="<%=request.getContextPath()%>/meal.html"
																		class="mealCancel">
																		<input type="hidden" name="clickDelete"
																			value="clickDelete"> <input type="hidden"
																			name="meal_id" value="${mealVO.meal_id}"> <input
																			type="hidden" name="action" value="delete">
																	</FORM>
																	<img class="dataCancelImg" src="<%=request.getContextPath()%>/assets/images/Icon/trash2.png">
																</td>
															</tr>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<c:if test="${status_selected == '4' }">
															<%
																List<MealVO> list2 = (List<MealVO>) session.getAttribute("getOneMeal");
																			pageContext.setAttribute("list", list2);
																			System.out.println("runnnnnnnnn2222222");
															%>
														</c:if>
														<c:forEach var="mealVO" items="${list}"
															begin="<%=pageIndex %>"
															end="<%= pageIndex + rowsPerPage-1 %>">
															<tr>
																<td>${mealVO.meal_id}</td>
																<td>${mealVO.meal_name}</td>
																<td>${mealVO.meal_price}</td>
																<td><c:if test="${mealVO.meal_status == 1}">
															${meal_status_Display['1']}
													
														</c:if> <c:if test="${mealVO.meal_status == 2}">
															${meal_status_Display['2']}
														
														</c:if></td>
																<td>${mealVO.meal_content}</td>
																<td style="width: 15%"><img
																	src="<%=request.getContextPath()%>/getMealPic?meal_id=${mealVO.meal_id}"
																	style="width: 100%"></td>
																<td>

																	<FORM METHOD="post"
																		ACTION="<%=application.getContextPath()%>/meal.html">
																		<input type="image" name="clickUpdate"
																			value="clickUpdate"
																			src="<%=request.getContextPath()%>/assets/images/Icon/edit2.png"
																			class="dataEditImg"> <input type="hidden"
																			name="meal_id" value="${mealVO.meal_id}"> <input
																			type="hidden" name="action" value="getOne_For_Update">
																	</FORM>
																</td>

																<td>
																	<FORM METHOD="post"
																		ACTION="<%=request.getContextPath()%>/meal.html"
																		class="mealCancel">
																		<input type="hidden" name="clickDelete"
																			value="clickDelete"> <input type="hidden"
																			name="meal_id" value="${mealVO.meal_id}"> <input
																			type="hidden" name="action" value="delete">
																	</FORM>
																	<img class="dataCancelImg" src="<%=request.getContextPath()%>/assets/images/Icon/trash2.png">
																</td>
															</tr>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</tbody>
										</table>
										<div class="page">
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

								<div class="formPage">
									<form METHOD="post"
										ACTION="<%=application.getContextPath()%>/meal.html"
										enctype="multipart/form-data" id="formGet">
										<p class="lightBoxTitle">新增套餐</p>
										<%-- 錯誤表列 --%>
										<c:if test="${not empty errorMsgs}">
											<font style="color: red">請修正以下錯誤:</font>
											<ul>
												<c:forEach var="message" items="${errorMsgs}">
													<li style="color: red; font-size: 20px;">${message}</li>
												</c:forEach>
											</ul>
										</c:if>

										套餐編號: <input type="text" name="meal_id"
											value="${mealVO.meal_id}" placeholder="套餐編號 " id="add_meal_id"
											${disabled== true?'readonly="readonly"':'' }><br>
										套餐名稱: <input type="text" name="meal_name"
											value="${mealVO.meal_name}" placeholder="套餐名稱 " id="add_meal_name"><br>
										套餐價格: <input type="text" name="meal_price"
											value="${mealVO.meal_price}" placeholder="套餐價格" id="add_meal_price"><br>
										套餐狀態: <select name="meal_status" id="add_meal_status">
											<option value="${meal_status_Display.canProvide}">可出餐</option>
											<option value="${meal_status_Display.notProvide}"
												${mealVO.meal_status ==2?'selected="selected"' : ''}>暫停供應</option>
										</select> <br> 套餐內容: <br>
										<textarea name="meal_content" class="meal_content" id="add_meal_content">
												${mealVO.meal_content}</textarea>
										<br> 套餐圖片<br> <input type="file" id="imageUpload"
											name="meal_pic" accept=".png, .jpg, .jpeg, .gif"
											style="background: rgb(228, 215, 198);"><br>
										<div id="previewImg"
											style="background-image:url('<%=request.getContextPath()%>/getMealPic?meal_id=${mealVO.meal_id}');">

											<img id="profileImage"
												src="data:image/jpg;base64,${base64Img}">
										</div>

										<input type="hidden" name="action" value="insert"
											id="whichAction"> <input type="submit" id="btnSubmit"
											value="新增"> <input type="button" id="btnCancel"
											name="btnCancel" value="取消">
											<input type="button" id="btnQuickInsert"
											name="btnQuickInsert" value="✔">
									</form>

								</div>
							</div>
						</div>
						<!-- 資料顯示 -->


				</div>

			</div>
			<%-- Java code會先執行 可以控制JS!!!!!!!!!!!!!!!!!! --%>

			<%
				String showFail = (String) request.getAttribute("errorShow");

				if ("fail".equals(showFail)) {
			%>
			<script type="text/javascript">
				$(function() {
					$('.filterList').hide();
					$('table').hide();
					$('.page').hide();
					
					$('.formPage').show();
					$('#profileImage').show();

				});
			</script>
			<%
				}
			%>

			<%
				String showUpdate = (String) request.getAttribute("updateShow");
				if ("update".equals(showUpdate)) {
			%>
			<script type="text/javascript">
				$(function() {
					$('.filterList').hide();
					$('table').hide();
					$('.page').hide();
					
					$('.formPage').show();
					$('#whichAction').attr('value', 'update');
					$('#btnSubmit').attr('value', '更新');

					$('#btnSubmit').mouseenter(function() {
						$('#imageUpload').prop('disabled', true);
						$('#whichAction').attr('value', 'updateNotPic');

					});

					$('#btnSubmit').mouseout(function() {
						$('#imageUpload').prop('disabled', false);
						$('#whichAction').attr('value', 'update');

					});

					$('#imageUpload').change(function() {
						$('#btnSubmit').off('mouseenter');
					});
					
					$('.lightBoxTitle').text('修改套餐');

				});
			</script>
			<%
				}
			%>





<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>
		</div>
	</div>




	<script
		src="<%= application.getContextPath() %>/assets/javascripts/MealMge.js"></script>


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