<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.equipment.model.*"%>
<%@ page import="com.member.model.*"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentoddetail.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.rentodlist.model.RentOdListService"%>
<%@ page import="com.rentodlist.model.RentOdListVO"%>
<%	
	String rent_odnum = (String)request.getAttribute("rent_odnum");
	RentOdListService rentodlistSVC = new RentOdListService();
	RentOdListVO rentodlistVO = rentodlistSVC.getOneRentOdList(rent_odnum);	
	pageContext.setAttribute("rentodlistVO", rentodlistVO);
%>
<%	
	RentOdDetailService rentoddetailSVC = new RentOdDetailService();
	List<RentOdDetailVO> rentoddetaillist = rentoddetailSVC.getDetail(rent_odnum);	
	pageContext.setAttribute("rentoddetaillist", rentoddetaillist);
%>
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
MemberDAO dao = new MemberDAO();
memberVO = dao.findByPrimaryKey(memberVO.getMember_id());
pageContext.setAttribute("memberVO", memberVO);
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="equipmentSvc" scope="page" class="com.equipment.model.EquipmentService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>共用頁面</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<!-- body -->
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

/*centerbox start*/
#centerbox{
	width: 90%;
	background-color: rgba(52,53,55,0.7);
	border: 1px solid rgba(150,150,150,0.5);
	height: 1250px;
}
#centerbox div{
  margin-top: 15px;
  border: 2px ;
}
/*centerbox end*/


  /* content end*/
.purchase
{
    position: relative;
    background-color: WHITE;
    min-height: 680px;
    padding: 15px;
    font-family: Times New Roman;
  	color:blcak;
}
.purchase header
{
    padding: 0px 0px 0px 0px;
    margin-bottom: 0px;
    border-bottom: 1px solid #3989c6;
   
}
.purchase header img
{
    max-width: 200px;
    margin-top: 0;
    margin-bottom: 0;
}
.purchase .company-details
{
    text-align: right;
    margin-top: 0;
    margin-bottom: 0;
}
.purchase main
{
    padding: 0px 0px;
    margin-bottom: 0px;
     
}
.purchase .to-details
{
    text-align: left;
}
.purchase .to-name
{
    font-weight: bold;
}
.purchase .to-name .to-address .to-city
{
    margin-top: 0;
    margin-bottom: 0;
}
.purchase .purchase-info
{
    text-align: right;
}
.purchase-info .info-code
{
    font-weight: bold;
}
.purchase-info .info-code .info-date
{
    margin-top: 0;
    margin-bottom: 0;
}
.table thead th
{
    margin: 0 !important;
    padding-top: 0 !important;
    padding-bottom: 0 !important;
}
.table td
{
    margin: 0 !important;
    padding-top: 0 !important;
    padding-bottom: 0 !important;
    border: none;
}
.table .blank-row
{
    height: 25px !important;
    background-color: #FFFFFF;
    border: none;
}
.table tbody
{
    min-height: 1000px !important;
}

.col, .col-1, .col-10, .col-11, .col-12, .col-2, .col-3, .col-4, .col-5, .col-6, .col-7, .col-8, .col-9, .col-auto, .col-lg, .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-lg-auto, .col-md, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-md-auto, .col-sm, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-sm-auto, .col-xl, .col-xl-1, .col-xl-10, .col-xl-11, .col-xl-12, .col-xl-2, .col-xl-3, .col-xl-4, .col-xl-5, .col-xl-6, .col-xl-7, .col-xl-8, .col-xl-9, .col-xl-auto {color:black}



	</style>

</head>
<body>
	
	<jsp:include page="/common/signup.jsp" flush="true"/>

	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/common/navbar.jsp" flush="true"/>
			
			

			<div class="col-lg-12 contentbox" >

			  
				<div id="centerbox" class="container-fluid m-auto">
				
								<a href="<%=request.getContextPath()%>/front-end/renthome/memberorder.jsp">查詢其他訂單</a><br>
				<a href="<%=request.getContextPath()%>/front-end/renthome/renthome.jsp">返回出租首頁</a>
					
					<div>
    <div class="purchase overflow-auto">
        <!--<div style="min-width: 600px">-->
            <header>
                <div class="row">
            	    <div class="col-sm-3 col-xs-3">
                	    <img src="https://www.qbrobotics.com/wp-content/uploads/2018/03/sample-logo-490x200.png" class="img-responsive">
                	</div>
                	<div class="col-sm-9 col-xs-9 company-details">
                	    <div>ISLANDPARK</div>
                	    <div>company phone +0987654</div>
                	    <div>company fax +098765</div>
                	</div>
            	</div>
            </header>
            <main>
                <div class="row">
                    <div class="col-sm-3 col-xs-3 to-details">
                        <div>訂單明細如下:</div>-----${rent_odnum }-----
                        <div class="to-name">租借者：${rentodlistVO.rent_name}
                       
                </div>
                        <div class="to-address">電話：${rentodlistVO.rent_phone}</div>
                        <div class="to-city">E-MAIL: <c:forEach var="memberVO" items="${memberSvc.all}">
                    <c:if test="${rentodlistVO.member_id==memberVO.member_id}">
	                    ${memberVO.m_email}
                    </c:if>
                </c:forEach></div>
                    </div>
                    <div class="col-sm-9 col-xs-9 purchase-info">
                        <h4 class="info-code">訂單編號</h4><br>
                        ${rentodlistVO.rent_odnum}
                        <div class="info-date">訂單產生日期</div><br>
                         ${rentodlistVO.odlist_createdate}
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 col-xs-12 table-responsive">
                        <table class="table table-condensed" border="0" cellspacing="0" cellpadding="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center col-xs-1 col-sm-1">商品名稱</th>
                                <th class="text-center col-xs-7 col-sm-7">規格</th>
                                <th class="text-center col-xs-1 col-sm-1">數量</th>
                                <th class="text-center col-xs-3 col-sm-3">價格</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="rentoddetailVO" items="${rentoddetaillist}">
                            <tr>
                                <td class="col-xs-1 col-sm-1 text-center"><c:forEach var="equipmentVO" items="${equipmentSvc.getAll()}">
                    <c:if test="${rentoddetailVO.eq_num==equipmentVO.eq_num}">
	                    ${equipmentVO.eq_name}
                    </c:if>
                </c:forEach></td>
                                <td class="text-center">
                                <c:forEach var="equipmentVO" items="${equipmentSvc.getAll()}">
                    <c:if test="${rentoddetailVO.eq_num==equipmentVO.eq_num}">
	                    ${equipmentVO.eq_size}
                    </c:if>
                </c:forEach>
                </td>
                                <td class="text-center">${rentoddetailVO.quantity}</td>
                                <td class="text-right"><c:forEach var="equipmentVO" items="${equipmentSvc.getAll()}">
                    <c:if test="${rentoddetailVO.eq_num==equipmentVO.eq_num}">
	                    ${equipmentVO.eq_price*rentoddetailVO.quantity}
                    </c:if>
                </c:forEach></td>
                            </tr>
                        </c:forEach>

                            
                        </tbody>
                        <tfoot>
                            <tr>
                                <th colspan="2">
                                    Information<br>
                                    Information content
                                </th>
                                <th class="text-center">總金額</th>
                                <th class="text-right">${rentodlistVO.od_total_price}</th>
                            </tr>
                        </tfoot>
                    </table>
                    </div>
                </div>
            </main>
        <!--</div>-->
    </div>
</div>
					
					
					
					
			
					
					
					
					
					
					
					
					
					
					
					
				</div>

			</div>






		  	<jsp:include page="/common/footer.jsp" flush="true"/>
		  
		</div>
	</div>

<!-- JS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>