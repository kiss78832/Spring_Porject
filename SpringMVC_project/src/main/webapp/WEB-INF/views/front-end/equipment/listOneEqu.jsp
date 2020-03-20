<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.spring.equipment.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spring.equipment.model.*"%>
<%@ page import="com.spring.member.model.*"%>
<%@ page import="com.spring.dailytypetotal.model.*"%>


<% 
EquipmentVO equipmentVO = (EquipmentVO) request.getAttribute("equVO"); 
 	EquipmentService equSvc = new EquipmentService();
 	List<EquipmentVO> list1 = equSvc.getOneSize(equipmentVO.getEq_name());
 	Set<String> size = new HashSet();
 	for(int i =0; i<list1.size();i++){
 		size.add(list1.get(i).getEq_size());
 	}
 	request.setAttribute("size", size);	
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<style type="text/css">







  *, *:before, *:after {
-moz-box-sizing: border-box; -webkit-box-sizing: border-box; box-sizing: border-box;
}



#calendar {
  -webkit-transform: translate3d(0, 0, 0);
  -moz-transform: translate3d(0, 0, 0);
  transform: translate3d(0, 0, 0);
  width: 420px;
  margin: 0 auto;
  height: 630px;
  overflow: hidden;
}

.header {
  height: 50px;
  width: 420px;
  background: rgba(66, 66, 66, 1);
  text-align: center;
  position:relative;
  z-index: 100;
}

.header h1 {
  margin: 0;
  padding: 0;
  font-size: 20px;
  line-height: 50px;
  font-weight: 100;
  letter-spacing: 1px;
}

.left, .right {
  position: absolute;
  width: 0px;
  height: 0px;
  border-style: solid;
  top: 50%;
  margin-top: -7.5px;
  cursor: pointer;
}

.left {
  border-width: 7.5px 10px 7.5px 0;
  border-color: transparent rgba(160, 159, 160, 1) transparent transparent;
  left: 20px;
}

.right {
  border-width: 7.5px 0 7.5px 10px;
  border-color: transparent transparent transparent rgba(160, 159, 160, 1);
  right: 20px;
}

.month {
  /*overflow: hidden;*/
  opacity: 0;
}

.month.new {
  -webkit-animation: fadeIn 1s ease-out;
  opacity: 1;
}

.month.in.next {
  -webkit-animation: moveFromTopFadeMonth .4s ease-out;
  -moz-animation: moveFromTopFadeMonth .4s ease-out;
  animation: moveFromTopFadeMonth .4s ease-out;
  opacity: 1;
}

.month.out.next {
  -webkit-animation: moveToTopFadeMonth .4s ease-in;
  -moz-animation: moveToTopFadeMonth .4s ease-in;
  animation: moveToTopFadeMonth .4s ease-in;
  opacity: 1;
}

.month.in.prev {
  -webkit-animation: moveFromBottomFadeMonth .4s ease-out;
  -moz-animation: moveFromBottomFadeMonth .4s ease-out;
  animation: moveFromBottomFadeMonth .4s ease-out;
  opacity: 1;
}

.month.out.prev {
  -webkit-animation: moveToBottomFadeMonth .4s ease-in;
  -moz-animation: moveToBottomFadeMonth .4s ease-in;
  animation: moveToBottomFadeMonth .4s ease-in;
  opacity: 1;
}

.week {
 background: #4A4A4A;
}

.day {
  display: inline-block;
  width: 60px;
  padding: 10px;
  text-align: center;
  vertical-align: top;
  cursor: pointer;
  background: #4A4A4A;
  position: relative;
  z-index: 100;
}

.day.other {
 color: rgba(255, 255, 255, .3);
}

.day.today {
  color: rgba(156, 202, 235, 1);
}

.day-name {
  font-size: 9px;
  text-transform: uppercase;
  margin-bottom: 5px;
  color: rgba(255, 255, 255, .5);
  letter-spacing: .7px;
}

.day-number {
  font-size: 24px;
  letter-spacing: 1.5px;
}


.day .day-events {
  list-style: none;
  margin-top: 3px;
  text-align: center;
  height: 12px;
  line-height: 6px;
  overflow: hidden;
}

.day .day-events span {
  vertical-align: top;
  display: inline-block;
  padding: 0;
  margin: 0;
  width: 5px;
  height: 5px;
  line-height: 5px;
  margin: 0 1px;
}

.blue { background: rgba(156, 202, 235, 1); }
.orange { background: rgba(247, 167, 0, 1); }
.green { background: rgba(153, 198, 109, 1); }
.yellow { background: rgba(249, 233, 0, 1); }

.details {
  position: relative;
  width: 420px;
  height: 75px;
  background: rgba(164, 164, 164, 1);
  margin-top: 5px;
  border-radius: 4px;
}

.details.in {
  -webkit-animation: moveFromTopFade .5s ease both;
  -moz-animation: moveFromTopFade .5s ease both;
  animation: moveFromTopFade .5s ease both;
}

.details.out {
  -webkit-animation: moveToTopFade .5s ease both;
  -moz-animation: moveToTopFade .5s ease both;
  animation: moveToTopFade .5s ease both;
}

.arrow {
  position: absolute;
  top: -5px;
  left: 50%;
  margin-left: -2px;
  width: 0px;
  height: 0px;
  border-style: solid;
  border-width: 0 5px 5px 5px;
  border-color: transparent transparent rgba(164, 164, 164, 1) transparent;
  transition: all 0.7s ease;
}

.events {
  height: 75px;
  padding: 7px 0;
  overflow-y: auto;
  overflow-x: hidden;
}

.events.in {
  -webkit-animation: fadeIn .3s ease both;
  -moz-animation: fadeIn .3s ease both;
  animation: fadeIn .3s ease both;
}

.events.in {
  -webkit-animation-delay: .3s;
  -moz-animation-delay: .3s;
  animation-delay: .3s;
}

.details.out .events {
  -webkit-animation: fadeOutShrink .4s ease both;
  -moz-animation: fadeOutShink .4s ease both;
  animation: fadeOutShink .4s ease both;
}

.events.out {
  -webkit-animation: fadeOut .3s ease both;
  -moz-animation: fadeOut .3s ease both;
  animation: fadeOut .3s ease both;
}

.event {
  font-size: 16px;
  line-height: 22px;
  letter-spacing: .5px;
  padding: 2px 16px;
  vertical-align: top;
}

.event.empty {
  color: #eee;
}

.event-category {
  height: 10px;
  width: 10px;
  display: inline-block;
  margin: 6px 0 0;
  vertical-align: top;
}

.event span {
  display: inline-block;
  padding: 0 0 0 7px;
}

.legend {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 30px;
  background: rgba(60, 60, 60, 1);
  line-height: 30px;

}

.entry {
  position: relative;
  padding: 0 0 0 25px;
  font-size: 13px;
  display: inline-block;
  line-height: 30px;
  background: transparent;
}

.entry:after {
  position: absolute;
  content: '';
  height: 5px;
  width: 5px;
  top: 12px;
  left: 14px;
}

.entry.blue:after { background: rgba(156, 202, 235, 1); }
.entry.orange:after { background: rgba(247, 167, 0, 1); }
.entry.green:after { background: rgba(153, 198, 109, 1); }
.entry.yellow:after { background: rgba(249, 233, 0, 1); }

/* Animations are cool!  */
@-webkit-keyframes moveFromTopFade {
  from { opacity: .3; height:0px; margin-top:0px; -webkit-transform: translateY(-100%); }
}
@-moz-keyframes moveFromTopFade {
  from { height:0px; margin-top:0px; -moz-transform: translateY(-100%); }
}
@keyframes moveFromTopFade {
  from { height:0px; margin-top:0px; transform: translateY(-100%); }
}

@-webkit-keyframes moveToTopFade {
  to { opacity: .3; height:0px; margin-top:0px; opacity: 0.3; -webkit-transform: translateY(-100%); }
}
@-moz-keyframes moveToTopFade {
  to { height:0px; -moz-transform: translateY(-100%); }
}
@keyframes moveToTopFade {
  to { height:0px; transform: translateY(-100%); }
}

@-webkit-keyframes moveToTopFadeMonth {
  to { opacity: 0; -webkit-transform: translateY(-30%) scale(.95); }
}
@-moz-keyframes moveToTopFadeMonth {
  to { opacity: 0; -moz-transform: translateY(-30%); }
}
@keyframes moveToTopFadeMonth {
  to { opacity: 0; -moz-transform: translateY(-30%); }
}

@-webkit-keyframes moveFromTopFadeMonth {
  from { opacity: 0; -webkit-transform: translateY(30%) scale(.95); }
}
@-moz-keyframes moveFromTopFadeMonth {
  from { opacity: 0; -moz-transform: translateY(30%); }
}
@keyframes moveFromTopFadeMonth {
  from { opacity: 0; -moz-transform: translateY(30%); }
}

@-webkit-keyframes moveToBottomFadeMonth {
  to { opacity: 0; -webkit-transform: translateY(30%) scale(.95); }
}
@-moz-keyframes moveToBottomFadeMonth {
  to { opacity: 0; -webkit-transform: translateY(30%); }
}
@keyframes moveToBottomFadeMonth {
  to { opacity: 0; -webkit-transform: translateY(30%); }
}

@-webkit-keyframes moveFromBottomFadeMonth {
  from { opacity: 0; -webkit-transform: translateY(-30%) scale(.95); }
}
@-moz-keyframes moveFromBottomFadeMonth {
  from { opacity: 0; -webkit-transform: translateY(-30%); }
}
@keyframes moveFromBottomFadeMonth {
  from { opacity: 0; -webkit-transform: translateY(-30%); }
}

@-webkit-keyframes fadeIn  {
  from { opacity: 0; }
}
@-moz-keyframes fadeIn  {
  from { opacity: 0; }
}
@keyframes fadeIn  {
  from { opacity: 0; }
}

@-webkit-keyframes fadeOut  {
  to { opacity: 0; }
}
@-moz-keyframes fadeOut  {
  to { opacity: 0; }
}
@keyframes fadeOut  {
  to { opacity: 0; }
}

@-webkit-keyframes fadeOutShink  {
  to { opacity: 0; padding: 0px; height: 0px; }
}
@-moz-keyframes fadeOutShink  {
  to { opacity: 0; padding: 0px; height: 0px; }
}
@keyframes fadeOutShink  {
  to { opacity: 0; padding: 0px; height: 0px; }
}

.full-screen{
	width: 30%;
    position: absolute;
    top: 100px;
/*     left: 35%; */
    background-color: rgba(80,80,80,0.9);
    padding: 30px;
    border-radius: 20px;
    border: 1px solid rgba(200,200,200,0.6);
}

.show {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background-color: rgba(0, 0, 0, .2);
	justify-content: center;
	align-items: center;
	resize:both;
	overflow:auto;
}

.eqbtn,.eqText{
	margin:30px;
}

</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

</head>
<body>






	<form name="shoppingForm"
		action="<%=request.getContextPath()%>/equipment/ShoppingCartServlet.do"
		method="POST" onclick="return false">

		<div class="showProduct show">
			<div class="full-screen">
				<button type="button" class="close" onclick="close1234()">
					<!--  aria-label="Close"  -->
					<!-- <span aria-hidden="true">&times;</span> -->
					&times;

				</button>
				<img
					src="<%=request.getContextPath()%>/equipment/DBGifReader4.do?eq_num=${equVO.eq_num}"
					alt="" class="img-fluid">
				<div class="eqText">
					${equVO.eq_name}<br>數量：<input type="text" id= "smallzero" name="quantity" size="3" value=1>
		尺寸:			
		<select size="1" name="eq_size">
         <c:forEach var="equVO1" items="${size}" > 
          <option value="${equVO1}">${equVO1}
         </c:forEach>   
       </select>
				</div>
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item"><a class="nav-link active" id="home-tab"
						data-toggle="tab" href="#home" role="tab" aria-controls="home"
						aria-selected="true">簡介</a></li>
				</ul>

				<div class="tab-content">
					<div class="tab-pane active" id="home" role="tabpanel"
						aria-labelledby="home-tab">${equVO.eq_detail}</div>
					<div class="tab-pane" id="profile" role="tabpanel"
						aria-labelledby="profile-tab">${equVO.eq_type}<br>${equVO.eq_brand}</div>
					<div class="tab-pane" id="messages" role="tabpanel"
						aria-labelledby="messages-tab">
						<div id="calendar" style="display: none"></div>
						</div>
					<div class="tab-pane" id="settings" role="tabpanel"
						aria-labelledby="settings-tab">...</div>
				</div>
				<button type="button" class="btn btn-success button eqbtn">加入購物車</button>
				<button type="button" class="btn btn-secondary" onclick="close1234()">返回購物</button>
<!-- 				<input type="submit" class="button" value="加入購物車"> -->
				<input type="hidden" name="eq_num" value="${equVO.eq_num}">
				<input type="hidden" name="eq_name" value="${equVO.eq_name}">
				<input type="hidden" name="type_eq_num" value="${equVO.type_eq_num}">
				<input type="hidden" name="requestURL" value="<%=request.getContextPath()+request.getServletPath()%>">
				<input type="hidden" name="action" value="ADD">
	</form>



  <script src='https://cdn.jsdelivr.net/vue/1.0.15/vue.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.1/moment.min.js'></script>
<script>
!function() {

	  var today = moment();

	  function Calendar(selector, events) {
	    this.el = document.querySelector(selector);
	    this.events = events;
	    this.current = moment().date(1);
	    this.events.forEach(function(ev) {
	     ev.date = moment(ev.date);
	    });
	    this.draw();
	    var current = document.querySelector('.today');
	    if(current) {
	      var self = this;
	      window.setTimeout(function() {
	        self.openDay(current);
	      }, 500);
	    }
	    
	  }

	  Calendar.prototype.draw = function() {
	    //Create Header
	    this.drawHeader();

	    //Draw Month
	    this.drawMonth();

	    this.drawLegend();
	  }

	  Calendar.prototype.drawHeader = function() {
	    var self = this;
	    if(!this.header) {
	      //Create the header elements
	      this.header = createElement('div', 'header');
	      this.header.className = 'header';

	      this.title = createElement('h1');

	      var right = createElement('div', 'right');
	      right.addEventListener('click', function() { self.nextMonth(); });

	      var left = createElement('div', 'left');
	      left.addEventListener('click', function() { self.prevMonth(); });

	      //Append the Elements
	      this.header.appendChild(this.title); 
	      this.header.appendChild(right);
	      this.header.appendChild(left);
	      this.el.appendChild(this.header);
	    }

	    this.title.innerHTML = this.current.format('MMMM YYYY');
	  }

	  Calendar.prototype.drawMonth = function() {
	    var self = this;
	    
	    
	    if(this.month) {
	      this.oldMonth = this.month;
	      this.oldMonth.className = 'month out ' + (self.next ? 'next' : 'prev');
	      this.oldMonth.addEventListener('webkitAnimationEnd', function() {
	        self.oldMonth.parentNode.removeChild(self.oldMonth);
	        self.month = createElement('div', 'month');
	        self.backFill();
	        self.currentMonth();
	        self.fowardFill();
	        self.el.appendChild(self.month);
	        window.setTimeout(function() {
	          self.month.className = 'month in ' + (self.next ? 'next' : 'prev');
	        }, 16);
	      });
	    } else {
	        this.month = createElement('div', 'month');
	        this.el.appendChild(this.month);
	        this.backFill();
	        this.currentMonth();
	        this.fowardFill();
	        this.month.className = 'month new';
	    }
	  }

	  Calendar.prototype.backFill = function() {
	    var clone = this.current.clone();
	    var dayOfWeek = clone.day();

	    if(!dayOfWeek) { return; }

	    clone.subtract('days', dayOfWeek+1);

	    for(var i = dayOfWeek; i > 0 ; i--) {
	      this.drawDay(clone.add('days', 1));
	    }
	  }

	  Calendar.prototype.fowardFill = function() {
	    var clone = this.current.clone().add('months', 1).subtract('days', 1);
	    var dayOfWeek = clone.day();

	    if(dayOfWeek === 6) { return; }

	    for(var i = dayOfWeek; i < 6 ; i++) {
	      this.drawDay(clone.add('days', 1));
	    }
	  }

	  Calendar.prototype.currentMonth = function() {
	    var clone = this.current.clone();

	    while(clone.month() === this.current.month()) {
	      this.drawDay(clone);
	      clone.add('days', 1);
	    }
	  }

	  Calendar.prototype.getWeek = function(day) {
	    if(!this.week || day.day() === 0) {
	      this.week = createElement('div', 'week');
	      this.month.appendChild(this.week);
	    }
	  }

	  Calendar.prototype.drawDay = function(day) {
	    var self = this;
	    this.getWeek(day);

	    //Outer Day
	    var outer = createElement('div', this.getDayClass(day));
	    outer.addEventListener('click', function() {
	      self.openDay(this);
	    });

	    //Day Name
	    var name = createElement('div', 'day-name', day.format('ddd'));

	    //Day Number
	    var number = createElement('div', 'day-number', day.format('DD'));


	    //Events
	    var events = createElement('div', 'day-events');
	    this.drawEvents(day, events);

	    outer.appendChild(name);
	    outer.appendChild(number);
	    outer.appendChild(events);
	    this.week.appendChild(outer);
	  }

	  Calendar.prototype.drawEvents = function(day, element) {
	    if(day.month() === this.current.month()) {
	      var todaysEvents = this.events.reduce(function(memo, ev) {
	        if(ev.date.isSame(day, 'day')) {
	          memo.push(ev);
	        }
	        return memo;
	      }, []);

	      todaysEvents.forEach(function(ev) {
	        var evSpan = createElement('span', ev.color);
	        element.appendChild(evSpan);
	      });
	    }
	  }

	  Calendar.prototype.getDayClass = function(day) {
	    classes = ['day'];
	    if(day.month() !== this.current.month()) {
	      classes.push('other');
	    } else if (today.isSame(day, 'day')) {
	      classes.push('today');
	    }
	    return classes.join(' ');
	  }

	  Calendar.prototype.openDay = function(el) {
	    var details, arrow;
	    var dayNumber = +el.querySelectorAll('.day-number')[0].innerText || +el.querySelectorAll('.day-number')[0].textContent;
	    var day = this.current.clone().date(dayNumber);

	    var currentOpened = document.querySelector('.details');

	    //Check to see if there is an open detais box on the current row
	    if(currentOpened && currentOpened.parentNode === el.parentNode) {
	      details = currentOpened;
	      arrow = document.querySelector('.arrow');
	    } else {
	      //Close the open events on differnt week row
	      //currentOpened && currentOpened.parentNode.removeChild(currentOpened);
	      if(currentOpened) {
	        currentOpened.addEventListener('webkitAnimationEnd', function() {
	          currentOpened.parentNode.removeChild(currentOpened);
	        });
	        currentOpened.addEventListener('oanimationend', function() {
	          currentOpened.parentNode.removeChild(currentOpened);
	        });
	        currentOpened.addEventListener('msAnimationEnd', function() {
	          currentOpened.parentNode.removeChild(currentOpened);
	        });
	        currentOpened.addEventListener('animationend', function() {
	          currentOpened.parentNode.removeChild(currentOpened);
	        });
	        currentOpened.className = 'details out';
	      }

	      //Create the Details Container
	      details = createElement('div', 'details in');

	      //Create the arrow
	      var arrow = createElement('div', 'arrow');

	      //Create the event wrapper

	      details.appendChild(arrow);
	      el.parentNode.appendChild(details);
	    }

	    var todaysEvents = this.events.reduce(function(memo, ev) {
	      if(ev.date.isSame(day, 'day')) {
	        memo.push(ev);
	      }
	      return memo;
	    }, []);

	    this.renderEvents(todaysEvents, details);

	    arrow.style.left = el.offsetLeft - el.parentNode.offsetLeft + 27 + 'px';
	  }

	  Calendar.prototype.renderEvents = function(events, ele) {
	    //Remove any events in the current details element
	    var currentWrapper = ele.querySelector('.events');
	    var wrapper = createElement('div', 'events in' + (currentWrapper ? ' new' : ''));

	    events.forEach(function(ev) {
	      var div = createElement('div', 'event');
	      var square = createElement('div', 'event-category ' + ev.color);
	      var span = createElement('span', '', ev.eventName);

	      div.appendChild(square);
	      div.appendChild(span);
	      wrapper.appendChild(div);
	    });

	    if(!events.length) {
	      var div = createElement('div', 'event empty');
	      var span = createElement('span', '', '');
	      
	      div.appendChild(span);
	      wrapper.appendChild(div);
	    }

	    if(currentWrapper) {
	      currentWrapper.className = 'events out';
	      currentWrapper.addEventListener('webkitAnimationEnd', function() {
	        currentWrapper.parentNode.removeChild(currentWrapper);
	        ele.appendChild(wrapper);
	      });
	      currentWrapper.addEventListener('oanimationend', function() {
	        currentWrapper.parentNode.removeChild(currentWrapper);
	        ele.appendChild(wrapper);
	      });
	      currentWrapper.addEventListener('msAnimationEnd', function() {
	        currentWrapper.parentNode.removeChild(currentWrapper);
	        ele.appendChild(wrapper);
	      });
	      currentWrapper.addEventListener('animationend', function() {
	        currentWrapper.parentNode.removeChild(currentWrapper);
	        ele.appendChild(wrapper);
	      });
	    } else {
	      ele.appendChild(wrapper);
	    }
	  }

	  Calendar.prototype.drawLegend = function() {
	    var legend = createElement('div', 'legend');
	    var calendars = this.events.map(function(e) {
	      return e.calendar + '|' + e.color;
	    }).reduce(function(memo, e) {
	      if(memo.indexOf(e) === -1) {
	        memo.push(e);
	      }
	      return memo;
	    }, []).forEach(function(e) {
	      var parts = e.split('|');
	      var entry = createElement('span', 'entry ' +  parts[1], parts[0]);
	      legend.appendChild(entry);
	    });
	    this.el.appendChild(legend);
	  }

	  Calendar.prototype.nextMonth = function() {
	    this.current.add('months', 1);
	    this.next = true;
	    this.draw();
	  }

	  Calendar.prototype.prevMonth = function() {
	    this.current.subtract('months', 1);
	    this.next = false;
	    this.draw();
	  }

	  window.Calendar = Calendar;

	  function createElement(tagName, className, innerText) {
	    var ele = document.createElement(tagName);
	    if(className) {
	      ele.className = className;
	    }
	    if(innerText) {
	      ele.innderText = ele.textContent = innerText;
	    }
	    return ele;
	  }
	}();

	!function() {
	  var data = [
	    { eventName: 'Lunch Meeting w/ Mark', calendar: 'Work', color: 'orange', date: '2014-02-08' },
	    { eventName: 'Interview - Jr. Web Developer', calendar: 'Work', color: 'orange', date: '2014-03-08' },
	    { eventName: 'Demo New App to the Board', calendar: 'Work', color: 'orange', date: '2014-02-13' },
	    { eventName: 'Dinner w/ Marketing', calendar: 'Work', color: 'orange', date: '2014-02-19' },

	    { eventName: 'Game vs Portalnd', calendar: 'Sports', color: 'blue', date: '2014-02-28' },
	    { eventName: 'Game vs Houston', calendar: 'Sports', color: 'blue', date: '2014-03-19' },
	    { eventName: 'Game vs Denver', calendar: 'Sports', color: 'blue', date: '2014-02-04' },
	    { eventName: 'Game vs San Degio', calendar: 'Sports', color: 'blue', date: '2014-02-01' },

	    { eventName: 'School Play', calendar: 'Kids', color: 'yellow', date: '2014-02-25' },
	    { eventName: 'Parent/Teacher Conference', calendar: 'Kids', color: 'yellow', date: '2014-02-19' },
	    { eventName: 'Pick up from Soccer Practice', calendar: 'Kids', color: 'yellow', date: '2014-03-31' },
	    { eventName: 'Ice Cream Night', calendar: 'Kids', color: 'yellow', date: '2014-02-20' },

	    { eventName: 'Free Tamale Night', calendar: 'Other', color: 'green', date: '2014-02-08' },
	    { eventName: 'Bowling Team', calendar: 'Other', color: 'green', date: '2014-02-10' },
	    { eventName: 'Teach Kids to Code', calendar: 'Other', color: 'green', date: '2014-03-04' },
	    { eventName: 'Startup Weekend', calendar: 'Other', color: 'green', date: '2014-03-17' }
	  ];

	  

	  

	  var calendar = new Calendar('#calendar', data);

	}();
	
	<% 
	DailyTotalService dtSVC =new DailyTotalService(); 
	List <DailyTotalVO> dtList=dtSVC.getOneEqDate(equipmentVO.getType_eq_num()); 
	for(DailyTotalVO dtVO: dtList){
// 		System.out.println(dtVO.getEq_date().toString().substring(8));
	%>
	
	$(".day-number").click(function() {
		if($(this).text()==<%= dtVO.getEq_date().toString().substring(8)%>)
			$(".events.in.new .event.empty span").html("")
		  $(".events.in.new .event.empty span").text("剩餘<%= dtVO.getDaily_eq_qty()-dtVO.getStart_qty()%>個");
		});
	
	
	<%}%>
// 	if($(".day-number").text()==19){
// 		 $(this).css("background-color","powderblue");
// 	}





 $(document).ready(function(){
    $(".button").click(function(){
        if($("#smallzero").val()==""){
            alert("你尚未填寫數量");
            eval("document.shoppingForm['quantity'].focus()");       
        }else if($("#smallzero").val()<1){
            alert("數量請填填寫大於1的數字");
            eval("document.shoppingForm['quantity'].focus()");         
        }else{
            document.shoppingForm.submit();
        }
    })
 })





</script>













</body>
</html>