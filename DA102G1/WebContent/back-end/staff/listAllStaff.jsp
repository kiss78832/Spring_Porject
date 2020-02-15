<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="sSvc" scope="page" class="com.staff.model.StaffService"/>
<jsp:useBean id="authoritySvc" scope="page" class="com.authority.model.AuthorityService"/>
<jsp:useBean id="authoritySvc2" scope="page" class="com.authority.model.AuthorityService"/>
<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService"/>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>Island Peak Staff List</title>
	<meta charset="utf-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
	<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/backgroundContext.css">

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
  
  
  .table td,th{
/*   padding:10px; */
	font-size:20px;
  background-color: rgba(200,200,200,0.7);
  
  }
  
  .table tr:hover{
  	background-color: rgba(0,0,0,0.7);
  	color:white;
  }
  
  



.auth{

	display:inline-block;
	margin-right:20px;
	text-align:left;
}

.authTr{

	height:80px;
	display:none;
/* 	pointer-events: none; */
}


</style>



</head>
<body>
<div class="container-fluid">
	<div class="row">
	
		<jsp:include page="/common/adminNavbar.jsp" flush="true"/>

	<div class="col-lg-12 contentbox" >
	  <img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">
	
	  
		<jsp:include page="/common/adminSideBar.jsp" flush="true"/>
		
	  
		<div class="col-lg-10 col-md-10 cb rightContent">
		<br><br>
	     <div>
	     	<h1 style="margin-left:135px;">員工列表&nbsp;&nbsp;<a href="<%= application.getContextPath() %>/back-end/staff/staffInsert.jsp">
	     	<button type="button" class="btn btn-outline-success">＋新增員工</button></a></h1>
	     </div>
			<br>
	     <div class="cb staffList table-responsive">
	     	<table class="table table-striped table-bordered  table-responsive-md">
	     		<thead class="thead-dark">
	     			<tr>
	     				<th>編號</th>
	     				<th>姓名</th>
	     				<th>性別</th>
	     				<th>帳號</th>
	     				<th>狀態</th>
	     				<th>電話</th>
	     				<th>Email</th>
	     				<th>修改</th>
	     				<th colspan="2">權限</th>
	     			</tr>
	     		</thead>
				<tbody>
				<c:forEach var="staffVO" items="${sSvc.all }">
					<tr>
						<td>${staffVO.sf_id}</td>
						<td>${staffVO.sf_name}</td>
						<td>${staffVO.gender == 1 ? '男':'女'}</td>
						<td>${staffVO.sf_account}</td>
						<td>${staffVO.sf_status == 1 ? '在職':'離職'}</td>
						<td>${staffVO.cellphone}</td>
						<td>${staffVO.sf_email}</td>
	<td>

	  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/staff/staff.do" style="margin-bottom: 0px;">
	    <button type="submit" class="btn btn-success">員工資料修改</button>
	    <input type="hidden" name="sf_id" value="${staffVO.sf_id}">
	    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
	    <input type="hidden" name="action" value="getOne_For_Update_staff"></FORM>
	</td>
	<td colspan="2">

		<button type="button" class="btn btn-danger authToggle">權限</button>
		<button type="button" class="btn btn-info authChange">確認修改</button>
		<input type="hidden" class="${staffVO.sf_account}">
	</td>
					</tr>
			
					<tr class="authTr">
						
						<td colspan="10">
						<form class="authForm"  action="<%= application.getContextPath()%>/authority/authority.do" method="post">
						<input type="hidden" name="action" value="authChange">
						<c:if test="${authoritySvc.findByPrimaryKey(staffVO.sf_id)==[] }">
							<c:forEach var="authorityVO" items="${functionSvc.getAll()}"><c:set var="same" value="0"/>
								<div class="auth">
								<input type="checkbox"  data-toggle="toggle" data-onstyle="warning" name="authData" 
								class="funCheck" value="${functionSvc.getOneFunction(authorityVO.fun_num).fun_num}">
								<br>${functionSvc.getOneFunction(authorityVO.fun_num).fun_name}
								</div>
							</c:forEach>
						</c:if>
						
						<c:forEach var="authorityVO" items="${functionSvc.getAll()}">
						<c:set var="same" value="0"/>
						<c:forEach var="myAuthorityVO" items="${authoritySvc.findByPrimaryKey(staffVO.sf_id) }">
						<c:set var="same" value="${same+1}"/>
						<c:if test="${(authorityVO.fun_num).equals(myAuthorityVO.fun_num)}">
							<div class="auth">
							<input type="checkbox" checked data-toggle="toggle" data-onstyle="warning" name="authData" 
							class="funCheck" value="${functionSvc.getOneFunction(authorityVO.fun_num).fun_num}">
							<br>${functionSvc.getOneFunction(authorityVO.fun_num).fun_name}
							<c:set var="same" value="0"/>
							</div>
						</c:if>	
						<c:if test="${same==authoritySvc.findByPrimaryKey(staffVO.sf_id).size()}">
							<div class="auth">
							<input type="checkbox"  data-toggle="toggle" data-onstyle="warning" name="authData" 
							class="funCheck" value="${functionSvc.getOneFunction(authorityVO.fun_num).fun_num}">
							<br>${functionSvc.getOneFunction(authorityVO.fun_num).fun_name}
							</div>
						</c:if>
						</c:forEach>
						</c:forEach>
						<input type="hidden" name="sf_id" value="${staffVO.sf_id}">
					</form>	
					</td>
					</tr>
					<!-- 自己不能改自己 -->
		<script type="text/javascript">
				if ('${staffVO.sf_account}'=='${sessionScope.admin}') {
			  		$('.${staffVO.sf_account}').parent().parent().next().find('input').attr("disabled","true");
			  		$('.${staffVO.sf_account}').prev().attr("disabled","true");
			  		$('.${staffVO.sf_account}').prev().removeClass("btn-info");
			  		$('.${staffVO.sf_account}').prev().addClass("btn-secondary");
	  			}	
		</script>
				</c:forEach>
				
				</tbody>

	     	</table>

	     </div>
		
	  </div>
	
	</div>




	<jsp:include page="/common/footer.jsp" flush="true"/>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$('.authToggle').click(function(){
			$(this).parent().parent().next().fadeToggle(200);
		})

		$('.authChange').click(function(){
			$(this).parent().parent().next().find('form').submit();
		})
		
		if("${sessionScope.F000}"!="F000"){
	        $('.authChange').addClass("btn-secondary");
	        $('.authChange').removeClass("btn-info");
	        $('.authChange').text("Admin Only");
	        $('.authChange').attr("disabled","true");
	        $('.authToggle').parent().parent().next().find('input').attr("disabled","true");
	  }
		  if('${update }'=='success'){
				swal({
					title: 'Authority Update Success !',
					type:'success',
					showConfirmButton: false,
					timer: 2000,
				}).catch(swal.noop);
				
		  }else if('${update }'=='success2'){

						swal({
							title: 'Staff Info Update Success !',
							type:'success',
							showConfirmButton: false,
							timer: 2000,
						}).catch(swal.noop);
		  }else if('${update }'=='success3'){

				swal({
					title: 'Staff Insert Success !',
					type:'success',
					showConfirmButton: false,
					timer: 2000,
				}).catch(swal.noop);
			}
		  
		  
		  <% session.removeAttribute("update");%>
	  
	})


</script>



<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>