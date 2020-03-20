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
      if (document.getElementsByClassName('staffList')[0]||
        document.getElementsByClassName('InsertStaffTitle')[0]||
        document.getElementsByClassName('UpdateStaffTitle')[0]||
        document.getElementById('myChart')) {
        $('.content:eq(0)').slideDown();
      }else if (document.getElementsByClassName('app_back_form')[0]) {
        $('.content:eq(1)').slideDown();
      }else if (document.getElementsByClassName('app_other')[0]) {
        $('.content:eq(5)').slideDown();
      }


    $(".collapsible").click(function() {
        $(this).next().animate({
          height:'toggle'
        });
    });  
  });