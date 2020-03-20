
  $('.bar').on("mouseenter",function(){
      var p = $(this).val();
      $(this).on("click",function(){
      p = $(this).val();
      bg(p);

      $(this).on("mousemove",function(){
      p = $(this).val();
      bg(p);    
      })
      });
  });

  function bg(p){
      $('.bar').css({
        'background-image':'-webkit-linear-gradient(left ,#f22 0%,#f22 '+p+'%,#fff '+p+'%, #fff 100%)'
      
      });
  }