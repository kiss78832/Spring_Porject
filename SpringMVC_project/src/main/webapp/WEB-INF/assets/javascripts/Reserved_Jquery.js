
/* ajax part */

$(function() {

	connect();

	var now = new Date();
	var getYear = now.getFullYear();
	var getMonth = now.getMonth();
	var location_id = $('#location_idSelect').val();
	console.log(location_id + getYear + getMonth);

	init();

	getdailyBedData(location_id, getYear, getMonth);
	console.log("jquery ready in");

	$('#up').click(function() {
		console.log('original' + getYear + 'and' + getMonth);

		var location_id = $('#location_idSelect').val();

		getMonth++;

		if (getMonth > 11) {
			getMonth = 0;
			getYear += 1;
		}

		clear();
		addDate();

		document.getElementsByTagName("th")[5].innerHTML = getMonth + 1;
		document.getElementsByTagName("th")[2].innerHTML = getYear;

		console.log(location_id + getYear + getMonth);
		getdailyBedData(location_id, getYear, getMonth);

	});

	$('#down').click(function() {
		console.log('original' + getYear, getMonth);

		var location_id = $('#location_idSelect').val();

		getMonth--;

		if (getMonth < 0) {
			getMonth = 11;
			getYear -= 1;
		}

		clear();
		addDate();

		document.getElementsByTagName("th")[5].innerHTML = getMonth + 1;
		document.getElementsByTagName("th")[2].innerHTML = getYear;

		console.log(location_id + getYear + getMonth);
		getdailyBedData(location_id, getYear, getMonth);
	});

	var getDay;

	function init() {

		document.getElementsByTagName("th")[2].innerHTML = getYear;
		document.getElementsByTagName("th")[5].innerHTML = getMonth + 1;

		addDate();

	}

	function addDate() {
		var nextMonth = new Date(getYear, getMonth + 1, 1);

		nextMonth.setHours(nextMonth.getHours() - 2);
		var preTotalDay = nextMonth.getDate();

		var nowMonth = new Date(getYear, getMonth, 1);
		getDay = nowMonth.getDay();
		var day = 1;
		for (var i = getDay; i < getDay + preTotalDay; i++) {
			document.getElementsByTagName("td")[i].innerHTML = day;

			document.getElementsByTagName("td")[i].onmouseover = mouseLight;
			document.getElementsByTagName("td")[i].onmouseout = mouseNormal;
			day++;
		}
	}

	function clear() {
		$('td').html("");
	}

	function mouseLight(e) {
		e.target.style.color = "gold";
	}
	function mouseNormal(e) {
		e.target.style.color = "white";
	}

	$('#monthChange').change(function() {

		clear();

		getMonthString = $('#monthChange').val();
		getMonth = parseInt(getMonthString);

		init();

		var location_id = $('#location_idSelect').val();

		getdailyBedData(location_id, getYear, getMonth);

	})

	/* 選擇據點類型  */
	$('#location_status').change(function() {
		var location_status = $('#location_status').val();
		
		clearLoc();
		
		getLocData(location_status);

	})

});

function clearLoc() {
	$('#location_id').empty();
	$('#location_id').append(
			"<option value='-1' disabled selected>請選擇據點</option>");
}


function getdailyBedData(location_id, getYear, getMonth) {
	$.ajax({
		type : "POST",
		url : "/DA102G1/dailyBed.html",
		data : createQueryString(location_id, getYear, getMonth),
		dataType : "json",
		success : function(data) {
			$(data).each(function(i, item) {
				replace(item);
			});

		},
		error : function() {
			alert("AJAX-class發生錯誤囉!")
		}
	});
}

function replace(item) {
	var dailyBed_date = parseInt(item.dailyBed_date.substring(8, 10));
	var dailyBed_month = parseInt(item.dailyBed_date.substring(5, 7))
	var remaining_total = item.remaining_total;
	var provided_total = item.provided_total;
	var dates = $('td');

	// console.log(item.dailyBed_date.substring(8,10));
	// console.log(item.dailyBed_date.substring(5,7));
	// console.log(remaining_total);
	// console.log( provided_total);

	for (var i = 0; i < dates.length; i++) {
		if ($('#month').html() == dailyBed_month) {
			console.log('pass id =month');
			if ($('td').eq(i).html() == dailyBed_date) {
				console.log('pass td date');
				$('td').eq(i).append(
						'<br><span>已預訂床位數:' + provided_total
								+ '</span><br><span>剩餘床位總數:' + remaining_total
								+ '</span>');

			}
		}

	}
}

function createQueryString(location_id, getYear, getMonth) {
	console.log("loc:" + location_id + ";year:" + getYear + "; month:"
			+ getMonth);
	var queryString = {
		"action" : "getSelect",
		"location_id" : location_id,
		"year" : getYear,
		"month" : getMonth
	};
	return queryString;
}

$('#location_id').change(function() {

	$('#locSelect').submit();

});

$(window).unload(function() {
	disconnect();
});

/* select loc_type */

function getLocData(location_status) {
	$.ajax({
		type : "POST",
		url : "/DA102G1/dailyBed.html",
		data : createQueryString2(location_status),
		dataType : "json",
		success : function(data) {
			console.log(data);
			$(data).each(function(i, item) {
				$('#location_id').append(
						"<option value='" + item.location_id + "'>"
								+ item.location_name + "</option>");
			});

		},
		error : function() {
			alert("系統忙線中請稍後,很抱歉造成您的困擾!");
		}
	});
}

function createQueryString2(location_status) {
	var queryString = {
		"action" : "locStatusSelect",
		"location_status" : location_status

	};
	return queryString;
}

/* web socket */

var MyPoint = "/Meal/Reserved_Jquery";

var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;

var webSocket;

function connect() {
	webSocket = new WebSocket(endPointURL);

	webSocket.onopen = function(event) {
	};

	webSocket.onmessage = function(event) {
		var JSONobj = JSON.parse(event.data);

		var action = JSONobj.action;

		toastr.options = {
			"closeButton" : true,
			"debug" : false,
			"newestOnTop" : false,
			"progressBar" : true,
			"positionClass" : "toast-top-right",
			"preventDuplicates" : false,
			"onclick" : null,
			"showDuration" : "300",
			"hideDuration" : "5000",
			"timeOut" : "10000",
			"extendedTimeOut" : "10000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		}

		if ("insertMeal" === action) {
			toastr.info('新增了一份套餐，快來看看吧!', JSONobj.meal_name + '   售價:'
					+ JSONobj.meal_price);
		}

		if ("updateMeal" === action) {
			toastr.info('更新了一份套餐，快來看看吧!', JSONobj.meal_name);
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
