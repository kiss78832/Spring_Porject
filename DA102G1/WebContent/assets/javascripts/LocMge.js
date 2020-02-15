$(function() {
	$('#addNew').click(
			function() {
				$('.filterList').hide();
				$('.filterList').next().hide();
				$('table').hide();
				$('.page').hide();
				
				$('.formPage').show();
				$('.formPage').attr('display', 'inline-block');
				$('#previewImg').css('background-image',
						'url(https://via.placeholder.com/700x400.png)');
				$('#meal_id').attr('disabled', false);

				$('#formGet input:nth-of-type(1)').attr('value', '');
				$('#formGet input:nth-of-type(2)').attr('value', '');
				$('#formGet input:nth-of-type(3)').attr('value', '');
				$('#formGet select').attr('value', '');
				$('#formGet textarea').empty();
				$('#formGet input:nth-of-type(4)').attr('value', '');
				$('#previewImg').css('background-image',
						'url(https://via.placeholder.com/700x400.png)');

				$('#profileImage').hide();
			});

	$('#btnCancel').click(
			function() {
				$('.filterList').show();
				$('table').show();
				$('.page').show();
				
				$('.formPage').hide();

				$('#whichAction').attr('value', 'insert');
				$('#btnSubmit').attr('value', '新增');

				$('#formGet input:nth-of-type(1)').attr('value', '');
				$('#formGet input:nth-of-type(2)').attr('value', '');
				$('#formGet input:nth-of-type(3)').attr('value', '');
				$('#formGet select').attr('value', '');
				$('#formGet textarea').empty();
				$('#formGet input:nth-of-type(4)').attr('value', '');
				$('#previewImg').css('background-image',
						'url(https://via.placeholder.com/700x400.png)');

				$('#profileImage').hide();
			});

});

$(function() {
	var back;
	$('input').focus(function() {
		back = $(this).attr('placeholder');
		$(this).attr('placeholder', '');
		$(this).blur(function() {
			$(this).attr('placeholder', back);
		});
	});

});

function readURL(input, previewId) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$(previewId)
					.css('background-image', 'url(' + e.target.result + ')');
			keepUrl = e.target.result;
			$(previewId).hide();
			$(previewId).fadeIn(650);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

$(function() {
	$("#imageUpload").change(function() {
		readURL(this, '#previewImg');
	});
});

$(function() {
	$('#location_id').change(function() {
		$('#getSelect').submit();
	})

});

$(function() {
	$('#loc_type').change(function() {
		$('#getAllByType').submit();
	});

	$('#location_status').change(function() {
		$('#getAllByStatus').submit();
	});

});


$('.dataCancelImg').on('click',function(e){
	
	var which = $('.dataCancelImg').index(this);
	
	e.preventDefault();
	Swal.fire({
		  title: '確定要刪除據點嗎?',
		  text: "據點一但刪除後無法返回操作",
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '確定'
		}).then((result) => {
		  if (result.value) {
			  $('.locCancel:eq('+which+')').submit();
		  }
		})
	
	
});

