//$(function() {
//	$(".chartContainer").CanvasJSChart({
//		title : {
//			text : "中央大學到底什麼東西好吃= ="
//		},
//		axisY : {
//			title : "要多難吃",
//			includeZero : false
//		},
//		axisX : {
//			interval : 1
//		},
//		legend : {
//			horizontalAlign : "right",
//			verticalAlign : "top",
//			fontSize : 15
//		},
//		data : [ {
//			type : "line", // try changing to column, area
//			showInLegend : true,
//			legendText : "難吃程度",
//			toolTipContent : "{label}: {y} 難吃",
//			dataPoints : [ {
//				label : "1",
//				y : 5.28
//			}, {
//				label : "2",
//				y : 3.83
//			}, {
//				label : "3",
//				y : 6.55
//			}, {
//				label : "4",
//				y : 4.81
//			}, {
//				label : "5",
//				y : 2.37
//			}, {
//				label : "6",
//				y : 2.33
//			}, {
//				label : "7",
//				y : 3.06
//			}, {
//				label : "8",
//				y : 2.94
//			}, {
//				label : "9",
//				y : 5.41
//			}, {
//				label : "10",
//				y : 2.17
//			}, {
//				label : "11",
//				y : 2.17
//			}, {
//				label : "12",
//				y : 2.80
//			}, {
//				label : "13",
//				y : 5.28
//			}, {
//				label : "14",
//				y : 3.83
//			}, {
//				label : "15",
//				y : 6.55
//			}, {
//				label : "16",
//				y : 4.81
//			}, {
//				label : "17",
//				y : 2.37
//			}, {
//				label : "18",
//				y : 2.33
//			}, {
//				label : "19",
//				y : 3.06
//			}, {
//				label : "20",
//				y : 2.94
//			}, {
//				label : "21",
//				y : 5.41
//			}, {
//				label : "22",
//				y : 2.17
//			}, {
//				label : "23",
//				y : 2.17
//			}, {
//				label : "24",
//				y : 2.80
//			}, {
//				label : "25",
//				y : 2.37
//			}, {
//				label : "26",
//				y : 2.33
//			}, {
//				label : "27",
//				y : 3.06
//			}, {
//				label : "28",
//				y : 2.94
//			}, {
//				label : "29",
//				y : 5.41
//			}, {
//				label : "30",
//				y : 2.17
//			}, {
//				label : "31",
//				y : 2.17
//			}
//
//			]
//		} ]
//	});
//});

$('.counter').each(function() {
	var $this = $(this), countTo = $this.attr('data-count');

	$({
		countNum : $this.text()
	}).animate({
		countNum : countTo
	},

	{
		duration : 1000,
		easing : 'linear',
		step : function() {
			$this.text(Math.floor(this.countNum));
		},
		complete : function() {
			$this.text(this.countNum);
			// alert('finished');
		}

	});
	
	$this.css('font-size','40px');

});


$('#order_status').on('change',function(){
	$('#getAllByOrderStatus').submit();
});

$('#payment_status').on('change',function(){
	$('#getAllByPaymentStatus').submit();
});

$('#member_idSelect').on('change',function(){
	$('#getMemberId').submit();
});

$('#group_idSelect').on('change',function(){
	$('#getGroupId').submit();
});

$('#dateRange_idSelect').on('change',function(){
	$('#getDateRange').submit();
});





var now = new Date();
var year = now.getFullYear();
var nextMonth = now.getMonth() + 1;


$('#location_id').change(function() {
	
	$('.chart').show();
	
	var location_id  = $('#location_id').val();
	
	 getMonthLocData(year, nextMonth ,location_id);

});


function getMonthLocData(year, nextMonth ,location_id) {
	
	$.ajax({
		type : "POST",
		url : "/DA102G1/roomOrder.html",
		data : createQueryString3(year, nextMonth ,location_id),
		dataType : "json",
		success : function(data) {
			console.log(data);
						
			 addChart(data);

		},
		error : function() {
			alert("AJAX-class發生錯誤囉!")
		}
	});
}

function createQueryString3(year, nextMonth ,location_id) {
	console.log("year:" + year + ";nextMonth" +nextMonth );
	var queryString = {
		"action" : "locSelectForChart",
		"year" : year,
		"nextMonth" : nextMonth,
		"location_id" :location_id
	};
	return queryString;
}

function addChart(data) {

	var dataPoints = [];

	var options =  {
		exportEnabled: true,
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "nextMonth_provided_total"
		},
		axisX: {
			interval : 1,
			valueFormatString: "DD MMM YYYY",
		},
		axisY: {
			title: "number",
			titleFontSize: 24,
			includeZero: false
		},
		legend : {
		horizontalAlign : "right",
		verticalAlign : "top",
		fontSize : 15
		},
		data: [{
			type: "line", 
			dataPoints: dataPoints
		}]
	};
		
	addData(data,options,dataPoints);
	
};



function addData(data,options,dataPoints) {
	for (var i = 0; i < data.length; i++) {
		dataPoints.push({
			x: new Date(data[i].dailyBed_date),
			y: data[i].provided_total
		});
	}
	var chart =$(".chartContainer").CanvasJSChart(options);
	chart.render();
}


$(function(){
	connect();
});
	
$( window ).unload(function() {
	disconnect();
});


/*web socket*/
var MyPoint = "/Room_Order/OrderMge";

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
		
		
		if("insertOrder"=== action){
			var count1 = $('#todayOrder').attr('data-count');
			count1++;
			console.log("count1:"+count1);
			$('#todayOrder').attr('data-count',count1.toString());
			$('#todayOrder').text(count1.toString());
			
			var count2 = $('#notPaid').attr('data-count');
			count2++;
			console.log("count2:"+count2);
			
			$('#notPaid').attr('data-count',count2.toString());
			$('#notPaid').text(count2.toString());
			
			toastr.info('會員新增了一筆訂單');
		}
		
		if("cancel"=== action){	
			
			toastr.warning('會員取消了一筆訂單!');
		}
		
		if("alreadyPay"=== action){
			var count1 = $('#paid').attr('data-count');
			count1++;
			console.log("count1:"+count1);
			$('#paid').attr('data-count',count1.toString());
			$('#paid').text(count1.toString());
			
			var count2 = $('#notPaid').attr('data-count');
			count2--;
			console.log("count2:"+count2);
			
			$('#notPaid').attr('data-count',count2.toString());
			$('#notPaid').text(count2.toString());
			
			toastr.success('一筆訂單繳費狀態更改為已繳費');
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