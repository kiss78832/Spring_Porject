<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=" java.util.* "%>
<%@ page import="com.spring.location.model.*"%>
<!DOCTYPE html>

<%
	LocationService locSvc = new LocationService();
	List<LocationVO> list = locSvc.getAll();
	pageContext.setAttribute("list", list);

	HashMap<String,String> hm = new HashMap<String,String>();
	hm.put("cabin", "0");
	hm.put("camp", "1");
	hm.put("all", "3");
	hm.put("0", "山屋");
	hm.put("1", "營地");
	application.setAttribute("loc_type_display", hm);

	HashMap<String,String> hm2 = new HashMap<String,String>();
	hm2.put("checked", "1");
	hm2.put("unchecked", "2");
	hm2.put("all", "3");
	hm2.put("1", "須審核");
	hm2.put("2", "不須審核");
	application.setAttribute("loc_status_display", hm2);
%>

<html>
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

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>




<!-- right side css -->
<link rel="stylesheet" type="text/css"
	href="<%= application.getContextPath() %>/assets/stylesheets/LocMge.css">

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


#cright div {
	/*border: 2px solid red;*/
	/*margin: 5px auto 5px auto;*/
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
										<div class="locSelect">
											<div class="locSelect_inner">
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/location.html"
													style="color: black;" id="getSelect">
													<input type="hidden" name="action" value="getSelect">
													<select size="1" name="location_id" class="form-control1"
														id="location_id">
														<option disabled="disabled" selected>請選擇據點
															<c:forEach var="locationVO" items="${list}">
																<option value="${locationVO.location_id}">${locationVO.location_name}
															</c:forEach>
													</select>
												</FORM>
											</div>
											<div class="locSelect_inner2">
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/location.html"
													style="color: black;" id="getAllByType">
													<select size="1" name="loc_type" class="form-control1"
														id="loc_type">
														<option disabled="disabled" selected>據點類型
														<option value="${loc_type_display['cabin']}">山屋
														<option value="${loc_type_display['camp']}">營地
														<option value="${loc_type_display['all']}">全部據點
													</select> <input type="hidden" name="action" value="getAllByType">
												</FORM>
											</div>

											<div class="locSelect_inner3">
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/location.html"
													style="color: black;" id="getAllByStatus">
													<select size="1" name="location_status"
														class="form-control1" id="location_status">
														<option disabled="disabled" selected>據點狀態
														<option value="${loc_status_display['checked']}">須審核

														
														<option value="${loc_status_display['unchecked']}">不須審核

														
														<option value="${loc_status_display['all']}">全部據點

														
													</select> <input type="hidden" name="action" value="getAllByStatus">
												</FORM>
											</div>
											
											<div class="locSelect_inner4">
																<button id="addNew">新增據點</button>
											</div>
										
														</div>

									</div>
									<div class="page">
										<table class="table table-hover table-dark table-striped table-bordered table-responsive-lg " style="color:white;">
											<thead>
												<tr>
													<th>據點編號</th>
													<th>據點名稱</th>
													<th>床位總數</th>
													<th>床位價格</th>
													<th>據點圖片</th>
													<th>據點類型</th>
													<th>據點狀態</th>
													<th>修改</th>
													<th>刪除</th>
												</tr>
											</thead>
											<tbody>
												<%@ include file="page.file"%>
												<c:choose>
													<c:when
														test="${type_selected == '2' or status_selected == '2' }">
														<%
															System.out.println("run by selected list");
														%>
														<c:forEach var="locationVO" items="${list}"
															begin="<%=pageIndex %>"
															end="<%= pageIndex + rowsPerPage-1 %>">
															<tr>
																<td>${locationVO.location_id}</td>
																<td>${locationVO.location_name}</td>
																<td>${locationVO.bedTotal_num}</td>
																<td>${locationVO.bed_price}</td>
																<td style="width: 15%"><img
																	src="<%=request.getContextPath()%>/getLocationPic?location_id=${locationVO.location_id}"
																	style="width: 50%"></td>
																<td><c:if test="${locationVO.loc_type == 0}">
															${loc_type_display['0']}
													
														</c:if> <c:if test="${locationVO.loc_type == 1}">
															${loc_type_display['1']}
														
														</c:if></td>
																<td><c:if test="${locationVO.location_status == 1}">
															${loc_status_display['1']}
													
														</c:if> <c:if test="${locationVO.location_status == 2}">
															${loc_status_display['2']}
														
														</c:if></td>

																<td>

																	<FORM METHOD="post"
																		ACTION="<%=application.getContextPath()%>/location.html">
																		<input type="image" name="clickUpdate"
																			value="clickUpdate"
																			src="<%=request.getContextPath()%>/assets/images/Icon/edit2.png"
																			class="dataEditImg"> <input type="hidden"
																			name="location_id" value="${locationVO.location_id}">
																		<input type="hidden" name="action"
																			value="getOne_For_Update">
																	</FORM>
																</td>

																<td>
																	<FORM METHOD="post"
																		ACTION="<%=request.getContextPath()%>/location.html"
																		class="locCancel">
																		<input type="hidden" name="clickDelete"
																			value="clickDelete"
																			class="dataEditImg"> <input type="hidden"
																			name="location_id" value="${locationVO.location_id}">
																		<input type="hidden" name="action" value="delete">
																	</FORM>
																	<img class="dataCancelImg" src="<%=request.getContextPath()%>/assets/images/Icon/trash2.png">
																</td>
															</tr>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<c:if
															test="${status_selected == 'one' }">
															<%
																List<LocationVO> list2 = (List<LocationVO>) session.getAttribute("getOneLocation");
																			pageContext.setAttribute("list", list2);
																			System.out.println("list was changed by one location");
															%>
														</c:if>
														<c:forEach var="locationVO" items="${list}"
															begin="<%=pageIndex %>"
															end="<%= pageIndex + rowsPerPage-1 %>">
															<tr>
																<td>${locationVO.location_id}</td>
																<td>${locationVO.location_name}</td>
																<td>${locationVO.bedTotal_num}</td>
																<td>${locationVO.bed_price}</td>
																<td style="width:15%"><img
																	src="<%=request.getContextPath()%>/getLocationPic?location_id=${locationVO.location_id}"
																	style="width: 50%"></td>
																<td><c:if test="${locationVO.loc_type == 0}">
															${loc_type_display['0']}
													
														</c:if> <c:if test="${locationVO.loc_type == 1}">
															${loc_type_display['1']}
														
														</c:if></td>
																<td><c:if test="${locationVO.location_status == 1}">
															${loc_status_display['1']}
													
														</c:if> <c:if test="${locationVO.location_status == 2}">
															${loc_status_display['2']}
														
														</c:if></td>

																<td>

																	<FORM METHOD="post"
																		ACTION="<%=application.getContextPath()%>/location.html">
																		<input type="image" name="clickUpdate"
																			value="clickUpdate"
																			src="<%=request.getContextPath()%>/assets/images/Icon/edit2.png"
																			class="dataEditImg"> <input type="hidden"
																			name="location_id" value="${locationVO.location_id}">
																		<input type="hidden" name="action"
																			value="getOne_For_Update">
																	</FORM>
																</td>

																<td>
																	<FORM METHOD="post"
																		ACTION="<%=request.getContextPath()%>/location.html"
																		class="locCancel">
																		<input type="hidden" name="clickDelete"
																			value="clickDelete"
																			class="dataEditImg"> <input type="hidden"
																			name="location_id" value="${locationVO.location_id}">
																		<input type="hidden" name="action" value="delete">
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
										ACTION="<%=application.getContextPath()%>/location.html"
										enctype="multipart/form-data" id="formGet">
										<p>新增據點</p>
										<%-- 錯誤表列 --%>
										<c:if test="${not empty errorMsgs}">
											<font style="color: red">請修正以下錯誤:</font>
											<ul>
												<c:forEach var="message" items="${errorMsgs}">
													<li style="color: red; font-size: 20px;">${message}</li>
												</c:forEach>
											</ul>
										</c:if>

										據點編號: <input type="text" name="location_id"
											value="${locationVO.location_id}" placeholder="據點編號 "
											id="location_id" ${disabled== true?'readonly="readonly"':'' }><br>
										據點名稱: <input type="text" name="location_name"
											value="${locationVO.location_name}" placeholder="據點名稱 "><br>
										床位總數: <input type="text" name="bedTotal_num"
											value="${locationVO.bedTotal_num}" placeholder="床位總數"><br>
										床位價格: <input type="text" name="bed_price"
											value="${locationVO.bed_price}" placeholder="床位價格"><br>
										<br> 據點圖片<br> <input type="file" id="imageUpload"
											name="location_pic" accept=".png, .jpg, .jpeg, .gif"
											style="background: rgb(228, 215, 198);"><br>
										<div id="previewImg"
											style="background-image:url('<%=request.getContextPath()%>/getLocationPic?location_id=${locationVO.location_id}');">

											<img id="profileImage"
												src="data:image/jpg;base64,${base64Img}">
										</div>
										<br>
										據點類型: <select name="loc_type">
											<option value="${loc_type_display.cabin}">山屋</option>
											<option value="${loc_type_display.camp}"
												${locationVO.loc_type ==1?'selected="selected"' : ''}>營地</option>
										</select><br>
										 據點狀態: <select name="location_status">
											<option value="${loc_status_display.checked}">須審核</option>
											<option value="${loc_status_display.unchecked}"
												${locationVO.location_status ==2?'selected="selected"' : ''}>不須審核</option>
										</select><br> 
										<input type="hidden" name="action" value="insert"
											id="whichAction"> <input type="submit" id="btnSubmit"
											value="新增"> <input type="button" id="btnCancel"
											name="btnCancel" value="取消">
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

				});
			</script>
			<%
				}
			%>





	<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>
		</div>
	</div>



	<script
		src="<%= application.getContextPath() %>/assets/javascripts/LocMge.js"></script>


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