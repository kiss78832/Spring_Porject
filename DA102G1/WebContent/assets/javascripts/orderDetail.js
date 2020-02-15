$(function() {
	
	connect();
	
	$('#addNew').click(function(e) {
		e.preventDefault();
		Swal.fire({
			  title: '確定要送出嗎?',
			  text: "訂單一但送出後無法做出修改",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '確定'
			}).then((result) => {
			  if (result.value) {
				  $('.loader').css('visibility','visible');
				  $('#insertOrder').submit();
			  }
			})
	});

	$('#btnCancel').click(function() {
		$('#lightBox1').hide();
	});
	
	$('#btnCommit').on('click',function(){
		$('#insertOrder').submit();
	});
	
	$('#addBack').on('click',function(){
		$('#back').submit();
	});
});


$( window ).unload(function() {
	disconnect();
});



var MyPoint = "/Meal/OrderDetail";

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
	
}

function disconnect() {
	webSocket.close();
}