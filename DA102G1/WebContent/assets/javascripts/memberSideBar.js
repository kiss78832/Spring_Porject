// var coll = $(".collapsible");
// var i;

// for (i = 0; i < coll.length; i++) {
//   coll[i].addEventListener("click", function() {
//     this.classList.toggle("active");
//     var content = this.nextElementSibling;
//     if (content.style.display === "block") {
//       content.style.display = "none";
//     } else {
//       content.style.display = "block";
//     }
//   });
// }
  $(document).ready(function() {
      if (document.getElementsByClassName('card_1')[0]||
        document.getElementsByClassName('cright')[0]||
        document.getElementsByClassName('formBox')[0]) {//資料、名片
        $('.content:eq(0)').slideDown();
      }else if(document.getElementsByClassName('tableArea')[0]){//訂單 grouplist
    	  $('.content:eq(1)').slideDown();
      }else if(document.getElementById('appy')){//申請單
    	  $('.content:eq(2)').slideDown();
      }else if(document.getElementsByClassName('grouplist')[0]){//活動
    	  $('.content:eq(3)').slideDown();
      }else if(document.getElementById('arti')){//文章
    	  $('.content:eq(4)').slideDown();
      }
    $(".collapsible").click(function() {
        $(this).next().animate({
          height:'toggle'
        });
    });  
  });