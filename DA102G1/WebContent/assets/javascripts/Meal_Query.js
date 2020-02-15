$(document).ready(function () {

  // trigger when mouse over
  $('.col-lg-3').hover(
    function(){
      $(this).animate({
        marginTop:"-=1%",
      },200);
    },

    // trigger when mouse out
    function(){
      $(this).animate({
        marginTop:"0%",
      },200);
    }
  );

});

