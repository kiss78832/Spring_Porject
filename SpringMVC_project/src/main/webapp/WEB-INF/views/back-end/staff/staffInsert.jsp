<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.spring.staff.model.*" %>

<jsp:useBean id="staffVO" scope="request" class="com.spring.staff.model.StaffVO"/>

<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>StaffInsert</title>
	<meta charset="utf-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/backgroundContext.css">
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


.InsertStaffTitle{
	margin-top: 50px;
}

.InsertStaff{
	background-color: black;
	padding: 30px;
	border-radius: 20px;
}

.InsertStaff td,th{
	padding: 15px;
}
.InsertStaff th{
	font-size: 24px;
	text-align: left;
}

.InsertStaff td{
	text-align: left;
}

.InsertStaff input[type=text],input[type=password]{
	width: 300px;
	height:45px;
}
.InsertStaff input[type=radio]+span{
	font-size:24px;
}

	.modal-content{
		background-color: black;
		color: white;
		border: 1px solid #a1afbd;
		border-radius: 10px;
		text-align: left;
		font-size:20px;
	}

	.modal-header{
		border-bottom: 1px solid #495765;
	}

	.modal-footer {
		border-top: 0px;
	}

</style>



</head>
<body>

				<%-- 錯誤列表 --%>
				
<div class="modal fade" id="ModalCenter" tabindex="-1" role="dialog"aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="CenterTitle">填寫錯誤&nbsp;&nbsp;<span  style="font-family:none;"class="badge badge-warning">Warning</span></h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true" style="color: white;">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<c:if test="${not empty errorMsgs }">
			<font>請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs }">
				<li>${message }</li>
				</c:forEach>
			</ul>
		</c:if>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<div class="container-fluid">
	<div class="row">
	
		<jsp:include page="/WEB-INF/common/adminNavbar.jsp" flush="true"/>

	<div class="col-lg-12 contentbox" >
	  <img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">
	
	  
		<jsp:include page="/WEB-INF/common/adminSideBar.jsp" flush="true"/>
		
	  
		<div class="col-lg-10 col-md-10 cb rightContent">

	     <div>
	     	<h1 class="InsertStaffTitle">新增員工</h1>
	     </div>
			<br><br>
	     <div class="cb">
	     	
	     	<form class="InsertStaff" action="<%= application.getContextPath()%>/staff/staff.do" method="POST">

	     		<table>
	     			
	     			<tr>
	     				<th>帳號 : </th>
	     				<td>
							<div class="form-group">
								<input type="text" class="form-control" name="sf_account"
								 placeholder="請填帳號" value="${staffVO.sf_account }">
							</div>
	     				</td>

	     			</tr>
	     			<tr>
	     				<th>姓名 : </th>
	     				<td>
	     					<div class="form-group">
								<input type="text" class="form-control" name="sf_name"
								  placeholder="請填姓名" value="${staffVO.sf_name }">
							</div>
	     				</td>
	     			</tr>
	     			<tr>
	     				<th>性別 : </th>
	     				<td>
	     					<label>
	     						<input id="mem" class="gender" type="radio" name="gender" value="1"
	     						> <span>男</span>
	     					</label><br>
	     					<label>
	     						<input id="womem" class="gender" type="radio" name="gender" value="2"
	     						> <span>女</span>
	     					</label>
	     				</td>
	     			</tr>
	     			<tr>
	     				<th>員工狀態 : </th>
	     				<td>
							<div class="form-check">
								<label>
								  <input class="form-check-input" type="radio" name="sf_status" id="sf_status1" 
								  value="1" checked>
								    <span>在職</span>
								</label>
							</div>
							<div class="form-check">
								<label>
								  <input class="form-check-input" type="radio" name="sf_status" id="sf_status2"
								   value="2">
								    <span>離職</span>
							    </label>
							</div>
	     					<script type="text/javascript">
	     						$(document).ready(function(){
	     							if('${staffVO.gender}'=='1'){
	     								$("#mem").click();
	     							}else{
	     								$("#womem").click();
	     							}
	     							
	     							if('${staffVO.sf_status}'=='1'){
	     								$("#sf_status1").click();
	     							}else{
	     								$("#sf_status2").click();
	     							}
	     						})
	     					</script>
	     				</td>
	     			</tr>
	     			<tr>
	     				<th>手機 : </th>
	     				<td>
	     					<div class="form-group">
								<input type="text" class="form-control" name="cellphone"
								 placeholder="請填手機" value="${staffVO.cellphone }">
							</div>
	     				</td>
	     			</tr>
	     			<tr>
	     				<th>Email : </th>
	     				<td>
	     					<div class="form-group">
								<input type="text" class="form-control" name="sf_email"  
								placeholder="請填Email" value="${staffVO.sf_email }">
							</div>
	     				</td>
	     			</tr>

	     		</table>
	     		<input type="hidden" name="action" value="INSERTSTAFF">
				<button type="submit" class="btn btn-outline-success">Submit</button>&nbsp;&nbsp;&nbsp;
				<a href="<%= application.getContextPath() %>/back-end/staff/listAllStaff.jsp">
	     	<button type="button" class="btn btn-outline-success">Back to List</button></a>
	     	</form>

	     </div>
		
	  </div>
	
	</div>






	<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>
	</div>
</div>

<div style="display:none;">
<button type="button" id="clickme" class="btn btn-primary" data-toggle="modal" data-target="#ModalCenter">
  Launch demo modal
</button>
</div>

<script>
	$(document).ready(function(){
		if(${not empty errorMsgs }==true){
			$('#clickme').click();
		}
	})
</script>

<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>