$(document)
		.ready(
				function() {
					
					connect();

//					$('.bed_num:eq(0)').prop("placeholder", "請先選擇據點");
//					$('.bed_num:eq(0)').prop("disabled", true);
					
					
					$('.bed_num').prop("placeholder", "請先選擇據點");
					$('.bed_num').prop("disabled", true);
					
					$('.meal').prop("disabled", true);
					$('.meal_num').prop("disabled", true);
					
//					$('.meal:eq(0)').prop("disabled", true);
//					$('.meal_num:eq(0)').prop("disabled", true);
					$('#addDateDiv')
							.click(
									function() {
										if ($('.dateDiv:eq(0)').is(':hidden')) {
											$('.dateDiv:eq(0)').show();
											$('.dateDiv:eq(0)').css('border' ,'');
										} else {
											$(".dateDiv:last").clone(true)
													.insertAfter(
															".dateDiv:last");
											
											$('.date:last').prop("value", "");
											$('.location:last').empty();
											$('.location:last')
													.append(
															"<option value='-1' disabled selected>請選擇據點</option>");
											
											$('.bed_num:last').prop("value", "");
											$('.bed_num:last').prop("placeholder", "請先選擇據點");
											$('.bed_num:last').prop("disabled",
													true);
											$('.meal:last').prop("value", 0);
											$('.meal:last').prop("disabled",
													true);
											$('.meal_num:last').prop("value", 0);
											$('.meal_num:last').prop("disabled",
													true);
											
											$('.dateDiv:last').css('border' ,'');
										}
									});

					$('.cancel').click(function() {
						var which = $('.cancel').index(this);
						if (which == 0) {
							
							$('.dateDiv:eq(0)').hide();
							
							$('.date:eq(0)').prop("value", "");
							$('.location:eq(0)').empty();
							$('.location:eq(0)')
									.append(
											"<option value='-1' disabled selected>請選擇據點</option>");
							
							$('.bed_num:eq(0)').prop("value", "");
							$('.bed_num:eq(0)').prop("placeholder", "請先選擇據點");
							$('.bed_num:eq(0)').prop("disabled",
									true);
							$('.meal:eq(0)').prop("value", 0);
							$('.meal:eq(0)').prop("disabled",
									true);
							$('.meal_num:eq(0)').prop("value", 0);
							$('.meal_num:eq(0)').prop("disabled",
									true);
							
							$('.dateDiv:eq(0)').css('border' ,'');
							
						
						} else {
							$('div').remove(".dateDiv:eq(" + which + ")");
						}
					});

//					$('#pageChange_1').click(function() {
//						$('#reserved').show();
//						$('#addDateDiv').show();
//						$('#detail').hide();
//						$('#submit_detail').hide();
//					});

					// $('#pageChange_2').click(function () {
					// $('#reserved').hide();
					// $('#addDateDiv').hide();
					// $('#detail').show();
					// $('#submit_detail').show();
					// });

					// $('#btnCommit').click(function(){
					// $('#lightBox1').hide();
					// $('#detail').hide();
					// $('#reserved').hide();
					// $('#pageChange').hide();
					// $('#commitSuccess').show();
					// });
				});

$('.dateDiv').on('mouseover',function(){
	var which = $('.dateDiv').index(this);
	$('.cancel:eq(' + which + ')').css('visibility' ,'visible');
	
});

$('.dateDiv').on('mouseout',function(){
	var which = $('.dateDiv').index(this);
	$('.cancel:eq(' + which + ')').css('visibility' ,'hidden');
	
});


$( window ).unload(function() {
	disconnect();
});


/* choose date start */
function getOrderDate(getYear, getMonth, getDate, which) {
	$.ajax({
		type : "POST",
		url : "/DA102G1/orderDetail.html",
		data : createQueryString(getYear, getMonth, getDate),
		dataType : "json",
		success : function(data) {

			clearSelect(which);
			console.log(data);
			$(data).each(
					function(i, item) {
						$('.location:eq(' + which + ')').append(
								"<option value='" + item.location_id + "'>"
										+ item.loc_name + "</option>");
					});

		},
		error : function() {
			//alert("您目前所選的日期"+getYear+"-"+getMonth+"-"+getDate+"沒有開放預訂或是已沒有可預訂床位的據點了!");
			Swal.fire({
					  type: 'error',
					  title: '很抱歉!',
					  text:'您目前所選的日期'+getYear+'-'+getMonth+'-'+getDate+'沒有開放預訂或是已沒有可預訂床位的據點了!'
					});
			
		}
	});
}

function createQueryString(getYear, getMonth, getDate) {
	console.log("year:" + getYear + ";month:" + getMonth + "; date:" + getDate);
	var queryString = {
		"action" : "dateSelect",
		"year" : getYear,
		"month" : getMonth,
		"date" : getDate
	};
	return queryString;
}

function clearSelect(which) {
	$('.location:eq(' + which + ')').empty();
	$('.location:eq(' + which + ')').append(
			"<option value='-1' disabled selected>請選擇據點</option>");
	$('.bed_num:eq(' + which + ')').prop("disabled", true);
}

/* choose date end */

/* choose location start */

function getLocation(getYear, getMonth, getDate, location, which) {
	$.ajax({
		type : "POST",
		url : "/DA102G1/orderDetail.html",
		data : createQueryString2(getYear, getMonth, getDate, location),
		dataType : "json",
		success : function(data) {

			clearSelect2(which);
			console.log(data);
			$(data).each(
					function(i, item) {
						$('.bed_num:eq(' + which + ')').prop("min", 0);
						$('.bed_num:eq(' + which + ')').attr("max",
								item.remaining_total);
						if ("0".match(item.loc_type)) {
							$('.bed_num:eq(' + which + ')').attr("step", "1");
						} else {
							$('.bed_num:eq(' + which + ')').attr("step", "4");
						}
					});

		},
		error : function() {
			alert("AJAX-class發生錯誤囉!");
		}
	});
}

function createQueryString2(getYear, getMonth, getDate, location) {
	console.log("year:" + getYear + ";month:" + getMonth + "; date:" + getDate
			+ "location_id:" + location);
	var queryString = {
		"action" : "locSelect",
		"year" : getYear,
		"month" : getMonth,
		"date" : getDate,
		"location_id" : location
	};
	return queryString;
}

function clearSelect2(which) {
	$('.bed_num:eq(' + which + ')').prop("placeholder", "請選擇床位數");
	$('.bed_num:eq(' + which + ')').prop("disabled", false);
	$('.bed_num:eq(' + which + ')').prop("value", "");
}

/* choose location end */

function minDateFormat() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var date = now.getDate();

	if (date < 10) {
		date = '0' + date;
	}
	if (month < 10) {
		month = '0' + month;
	}

	var dateFormat = year + "-" + month + "-" + date;
	return dateFormat;
}

function maxDateFormat() {
	var now = new Date();
	var year = now.getFullYear();

	var next2month = new Date(year, now.getMonth() + 2, 1);
	next2month.setHours(next2month.getHours() - 1);

	var nextMonthLastDate = next2month.getDate();
	var nextMonth = next2month.getMonth() + 1;
	var nextMonthYear = next2month.getFullYear();

	if (nextMonth < 10) {
		nextMonth = '0' + nextMonth;
	}

	var dateFormat = nextMonthYear + "-" + nextMonth + "-" + nextMonthLastDate;
	return dateFormat;
}

$(function() {
	var minDate = minDateFormat();
	var maxDate = maxDateFormat();
	$('.date').prop("min", minDate);
	$('.date').prop("max", maxDate);

	$('.date').on('change', function() {
		var which = $('.date').index(this);
		console.log('index:'+which);
		var date = $('.date:eq(' + which + ')').val();
		var year = date.substring(0, 4);
		var month = parseInt(date.substring(5, 7));
		var date = parseInt(date.substring(8, 10));

		getOrderDate(year, month, date, which);

	});

	$('.location').on('change', function() {
		var which = $('.location').index(this);
		var location = $('.location:eq(' + which + ')').val();
		console.log('index:'+which);
		var date = $('.date:eq(' + which + ')').val();
		var year = date.substring(0, 4);
		var month = parseInt(date.substring(5, 7));
		var date = parseInt(date.substring(8, 10));

		getLocation(year, month, date, location, which);

	});

	$('.bed_num').on('change', function() {
		var which = $('.bed_num').index(this);
		console.log('index:'+which);
		
		$('.meal:eq('+which+')').prop("disabled",
				false);
		
		$('.meal_num:eq('+which+')').prop("disabled",
				false);
		
		//dateDiv css change
		
		$('.dateDiv:eq(' + which + ')').css('border' ,'3px solid green');	
		
	});
	
	$('.meal').on('change', function() {
		var which = $('.meal').index(this);
		console.log('index:'+which);
		
//		$('.meal_num:eq('+which+')').prop("disabled",
//				false);
	});
	
	$('#submit_detail').on('click' , function(){
		
		Swal.fire({
			  title: '確定進入查看明細?',
			  text: "您將會看到所選日期訂單的明細,再次按下確認後才會送出訂單",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '確定'
			}).then((result) => {
			  if (result.value) {			    
			    $('#submitOrder').submit();
			  }
			})
		
		
	});
	
	
});

//var MyPoint = "/Meal/Reserved_Bed";

var MyPoint = "/Meal/Reserved_Bed";

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
 
//		var jsonObj = {
//			"userName" : userName,
//			"message" : message
//		};
//		webSocket.send(JSON.stringify(jsonObj));
//		inputMessage.value = "";
//		inputMessage.focus();
	
}

function disconnect() {
	webSocket.close();
}
	
	
	
	
	





