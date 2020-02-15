<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*" %>

<jsp:useBean id="mSvc" class="com.member.model.MemberService"/>


<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>Island Peak admin_Index</title>
	<meta charset="utf-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
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





/*baarChart start*/

#ITEM18{

    width: auto;
  }

/*barchart end*/

/*sumCount start*/
.sumCount{
background-color: rgba(100,100,100,0.4);


}

.sumCount>span{
  display: flex;
  position: relative;
  font-size: 30px;
  margin-right: 10px;
  left: 0px;
}

/*sumCount end*/
/*memberCount start*/

  .memberCount{
    display: inline-block;
    /* width: 400px; */
    height: auto;
    background-color: rgba(100,100,100,0.4);
    position: relative;
    float: left;
  }

    .out{
      width: 350px;
      height: 50px;

      font-size: 0;

    }
    .out span{
        font-size: 16px;
        line-height: 50px;
    }

    .out div{
      display: inline-block;
      position: relative;
      margin: 0px;
    }

    .left{
      width: calc(100%*0.67);
      height: 50px;
      background-color:rgba(50,100,200,0.6);
      border-radius: 10px 0px 0px 10px;
    }
    .right{
      width: calc(100%*0.33);
      height: 50px;
      background-color:rgba(200,50,70,0.6);
      border-radius: 0px 10px 10px 0px;
    }

/*memberCount end*/


/*pieChart start*/

.pieChartOut{
    height: auto;
    width: auto;
    /*background-color: rgba(200,200,200,0.2);*/
    display: inline-block;
    position: relative;
}




/*pieChart end*/
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
		    

		     
		    <!-- 統計資料畫面 start --> 
					     
			<div class="barchart col-lg-12 cb">
<%-- 			---${sessionScope.admin }-- --%>
<%-- 			---${sessionScope.F000}-- --%>
<%-- 			---${sessionScope.F001}-- --%>
<%-- 			---${sessionScope.F002}-- --%>
<%-- 			---${sessionScope.F003}-- --%>
<%-- 			---${sessionScope.F004}-- --%>
<%-- 			---${sessionScope.F005}-- --%>
<%-- 			---${sessionScope.F006}-- --%>
<%-- 			---${sessionScope.F007}-- --%>
			
			 <div id="ITEM18">
			    <canvas id="myChart"></canvas>
			  </div>
			
			</div>
			
			
			
			
			  <div class="memberCount col-lg-6">
			    <div class="sumCount">
			      <span>網站拜訪次數 : 10933 次</span>
			      <br>
			      <span>會員總人數 : ${mSvc.memberCount() } 人</span>
			      <br>
			      <span>總程團數 : 24 團</span>
			
			    </div>
			    <span>會員男女比例</span>
			    <div class="out">
			    <div class="left"><span>男</span></div>
			    <div class="right"><span>女</span></div>
			    </div>
			    <br>
			    <div class="sumCount">
			    	<span>總食宿訂單數: 88  單</span><br>
			    	<span>總租借訂單數: 43  單</span><br>
			    </div>
			    
			  </div>
			
			
			
			<div class="pieChartOut col-lg-6">
			  <br>
			  <h2>路線申請比例</h2>
			<canvas id="chart1" height="450px" width="450px"></canvas>
			</div>
		     
		     
		     
		     
		     
		     <!-- 統計資料畫面 end -->
		
		  </div>
		
		</div>






	<jsp:include page="/common/footer.jsp" flush="true"/>
	</div>
</div>




<script type="text/javascript">
  $(document).ready(function(){

Chart.defaults.global.defaultFontColor = 'white';
Chart.defaults.global.defaultFontSize = 15;
Chart.defaults.global.title.fontSize = 24;
Chart.defaults.global.title.fontColor = "deeppink";
Chart.defaults.global.title.fontStyle = "bold";
var ctx = $("#myChart");
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["Jan", "Feb", "Mar", "Apr", "Jun", "Jul","Aug","Sep","Oct","Nov","Dec"],
        datasets: [{
            label: '月份入園人數',  
            data: [30, 19, 3, 15, 2, 3,6,24,15,22,13,21],
            backgroundColor: [
                'rgba(255, 99, 132, 0.5)',
                'rgba(54, 162, 235, 0.5)',
                'rgba(153, 102, 255, 0.5)',
                'rgba(255, 206, 86, 0.5)',
                'rgba(255, 99, 132, 0.5)',
                'rgba(75, 192, 192, 0.5)',
                'rgba(153, 102, 255, 0.5)',
                'rgba(54, 162, 235, 0.5)',
                'rgba(255, 206, 86, 0.5)',
                'rgba(54, 162, 235, 0.5)',
                'rgba(255, 159, 64, 0.5)',
                'rgba(255, 206, 86, 0.5)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(255, 99, 132, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(255, 206, 86, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {

      title: {
            display: true,
            text: '年度入園人數分佈圖'
        },
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});
  })
</script>

<!-- barChar end -->

<!-- pieChart start -->
<script type="text/javascript">
  
$(document).ready(function(){



//資料標題
    var labels = ['玉山主線', '南二段', '八通關', '馬博拉斯', '南橫關山' ,'新康橫斷'];
    var total1 = 0;

    var ctx = document.getElementById('chart1').getContext('2d');
    var pieChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: labels,
            datasets: [{
                //預設資料
                borderWidth:1,
                fontColor: 'black',
                data: [80, 20, 30, 40,50,20],
                backgroundColor: [
                    //資料顏色
                    "rgba(179, 212, 255, 0.4)",
                    "rgba(255, 246, 74, 0.4)",
                    "rgba(105, 215, 255, 0.4)",
                    "rgba(255, 120, 120, 0.4)",
                    "rgba(192, 255, 158, 0.4)",
                    "rgba(255, 61, 61, 0.4)"
                ],
            }],
        },
        options: {

            animation: {
                animateScale: true,
                animateRotate: true
            },
            // 關於滑過後的 顯示
            tooltips: {
                callbacks: {
                    label: function (tooltipItem, data) {
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        //計算總和
                        var sum = dataset.data.reduce(function (previousValue, currentValue, currentIndex, array) {
                            return previousValue + currentValue;
                        });
                        var currentValue = dataset.data[tooltipItem.index];
                        var percent = Math.round(((currentValue / sum) * 100));
                        return " " + data.labels[tooltipItem.index] + ":" + currentValue + " (" + percent + " %)";
                    }
                }
            },

            //提示項目的處理
            legend: {
                display: true,
                position: 'left',
                labels: {
                    generateLabels: function (chart) {
                        var data = chart.data;
                        if (data.labels.length && data.datasets.length) {
                            return data.labels.map(function (label, i) {
                                var ds = data.datasets[0];
                                var arc = chart.getDatasetMeta(0).data[i];
                                var custom = arc && arc.custom || {};
                                var getValueAtIndexOrDefault = Chart.helpers.getValueAtIndexOrDefault;
                                var arcOpts = chart.options.elements.arc;
                                var fill = custom.backgroundColor ? custom.backgroundColor : getValueAtIndexOrDefault(ds.backgroundColor, i, arcOpts.backgroundColor);
                                var stroke = custom.borderColor ? custom.borderColor : getValueAtIndexOrDefault(ds.borderColor, i, arcOpts.borderColor);
                                var bw = custom.borderWidth ? custom.borderWidth : getValueAtIndexOrDefault(ds.borderWidth, i, arcOpts.borderWidth);

                                var value = chart.config.data.datasets[chart.getDatasetMeta(0).data[i]._datasetIndex].data[chart.getDatasetMeta(0).data[i]._index];

                                return {
                                    text: label + " : " + value,
                                    fillStyle: fill,
                                    strokeStyle: stroke,
                                    lineWidth: bw,
                                    hidden: isNaN(ds.data[i]) || chart.getDatasetMeta(0).data[i].hidden,
                                    index: i
                                };
                            });
                        } else {
                            return [];
                        }
                    }
                }
            }
        },

    });




})

</script>

<!-- pieChart end -->





<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>