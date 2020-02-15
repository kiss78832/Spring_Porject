<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.spot.model.*"%>
<jsp:useBean id="spotSvc" class="com.spot.model.SpotService"/>
<% List<SpotVO> spotList = spotSvc.getAll(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>Park info</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- body -->
	<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/body.css">


	<style type="text/css">
		  /*本地字體引入*/
  @font-face{
    font-family: Russo;
    src:url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
     unicode-range: U+00-024F;
  }
  @font-face{
    font-family: Noto;
    src:url('/DA102G1/assets/fonts/NotoSansTC-Medium.otf');
    unicode-range: U+4E00-9FFF;
  }

  /* content start*/

.contentbox{
  margin-top: 80px;
  box-sizing: border-box;
  background-image: url(/DA102G1/assets/images/snowpeak/forest-1743206.jpg);
  background-size: cover;
  height: auto;
  padding: 0px;
  

}
.contentbox .cb{/*contentbox 底下的div*/
  display: inline-block;
  position: relative;
}

/*centerbox start*/
#centerbox{
width: 100%;
background-color: rgba(52,53,55,0.7);
border: 1px solid rgba(150,150,150,0.5);
height: auto;
}
#centerbox div{
/*   margin-top: 15px; */
/*   border: 2px solid red; */
}
/*centerbox end*/


  /* content end*/
  
  
  /*地圖邊界*/
  html, body { height: 535px; margin: 0; padding: 0;}
      #map-canvas{
      	height: 535px;
      	width: 100%;
      	margin:10px auto 30px auto;
      	padding:20px;
      	color:black;
      }
    #map-canvas{
    border-radius: 20px;
    border:2px solid gray;
    }
	.maptitle{
	font-size:30px;
	display: inline-block; 
	}
	.infoPic{
	width: 350px; 
	height: 220px;  
	border-radius: 5px;
    border:2px solid gray;
	}
	.infoText{
	max-width:350px;
	}
 
	</style>

</head>
<body>
	
	<jsp:include page="/common/signup.jsp" flush="true"/>

	<div class="container-fluid">
		<div class="row">
			<jsp:include page="/common/navbar.jsp" flush="true"/>
			
			<div class="col-lg-12 contentbox" >		  
				<div id="centerbox" class="container-fluid m-auto">
					<div class="col-lg-12 cb">
						<!-- Title -->
						<span class="maptitle" >
						<img src="<%= application.getContextPath() %>/assets/images/Icon/インフォメーションアイコン3.png"
						style="display:inline-block; margin: 0px;width: 40px; height: 40px;"> 
						園區資訊</span>
						
						<!-- show Maps -->
						<div id="map-canvas" class="container"></div>
										
					<script type="text/javascript"
					      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDttmpijU7V-hOejCotE7WVMDVuftzm6eo">
					</script>
					<script type="text/javascript">
					
							var map;
							var markers = [];
							var position = [
							  {lat:23.47633081,lng:120.9000346,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/塔塔加登山口.jpg'><h3>塔塔加登山口</h3><p class='infoText'>位於塔塔加鞍部，海拔約2,610公尺，為南北向之狹長草原稜線腹地廣大。可東眺玉山山塊、西望阿里山山脈，具有高山分水嶺地形景觀。</p>"}, 
							  {lat:23.47194444,lng:120.9102778,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/孟祿亭.jpg'><h3>孟祿亭</h3><p class='infoText'>此處距塔塔加步道口約1.7公里處，海拔約2,838公尺，為溫帶林及寒帶林之交界處，目前有一休憩亭提供遊客登山中途休憩使用。</p>"},
							  {lat:23.475761,lng:120.917463,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/玉山前峰.jpg'><h3>玉山前峰</h3><p class='infoText'>海拔約為3,239公尺，登山口位於玉山步道2.7公里處，坡陡頂瘦，峰頂腹地不大，滿生箭竹，南側山坡有完整白木林景觀。由此可眺望阿里山山脈及鹿林山一帶。</p>"},
							  {lat:23.46388889,lng:120.9305556,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/白木林.jpg'><h3>白木林</h3><p class='infoText'>距步道口約5k處，海拔3,096公尺，此處曾發生森林火災，目前殘存鐵杉及冷杉樹幹白化後所形成的白木林景觀，此處有一景觀平台可供遊客登山休憩賞景。</p>"},
							  {lat:23.467602,lng:120.94026,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/玉山大峭壁.jpg'><h3>玉山大峭壁</h3><p class='infoText'>距排雲山莊約1.8公里處，其構成的岩層為板岩夾變質砂岩，大峭壁上尚滯留海生物化石及波紋狀痕跡，即是台灣經歐亞大陸板塊及菲律賓板塊互相擠壓自海中隆起最好的證明。</p>"},
							  {lat:23.29332,lng:121.034128,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/020.jpg'><h3>嘉明湖</h3><p class='infoText'>海拔3300公尺的嘉明湖，有人說它是天使的眼淚，布農族人說它是月亮的鏡子，每年吸引上萬人，<br>但也發生多起離奇山難，有人罹難後化作石頭，守護25年。</p>"},
							  {lat:23.474036,lng:121.038689,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/大水窟山.jpg'><h3>大水窟山</h3><p class='infoText'>海拔約為3,642公尺，位於中央山脈中段之轉折點，峰頂廣閣平緩，全為香青與箭竹覆蓋，具優美之草原景觀東北側為險地斷崖，東眺拉庫拉庫溪，西望玉山山塊全景展望極佳。</p>"},
							  {lat:23.297246,lng:121.028647,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/三叉山.jpg'><h3>三叉山</h3><p class='infoText'>海拔約3,496公尺，中央山脈在此分出新康支稜，全峰起伏較緩，為開闊的草原地形，為中央山脈三大草坡之一。</p>"},
							  {lat:23.549935,lng:121.032871,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/駒盆山.jpg'><h3>駒盆山</h3><p class='infoText'>海拔約為3,109公尺為馬博拉斯山向西北分出的支稜，山頂平緩，滿生淺箭竹草，展望良好，此山峰距馬博拉斯山路途較遠，攀登者不多。</p>"},
							  {lat:23.533723,lng:121.065431,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/馬博拉斯山.jpg'><h3>馬博拉斯山</h3><p class='infoText'>海拔約3,785公尺，為中央山脈第二高峰，原名為「烏拉孟山」，其山勢高聳，峻拔雄偉，亦列為台灣高山十峻之一，此山峰為中央山脈稜脊東、南轉折點，東、南側為斷崖地形，西為茂密原始林，冬季白雪皚皚，令登山者望之怯步。</p>"},
							  {lat:23.471839,lng:121.188467,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/喀西帕南山.jpg'><h3>喀西帕南山</h3><p class='infoText'>海拔約為3,264公尺，為玉山國家公園東界，主峰與北峰、南峰共同形成雄峙之稜脊及太平谷，山峰南北狹長、東西窄，形成不同之視角景觀。</p>"},
							  {lat:23.483959,lng:121.173957,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/馬西山.jpg'><h3>馬西山</h3><p class='infoText'>海拔約為3,443公尺，為玉山國家公園東北界山，峰頂平坦寬闊，為中央山脈三大廣闊草坡之一（馬西山、三叉山及東郡大山），具有平廣草原山峰景觀，間有二葉松及各種高山植物，風景極為優美。</p>"},
							  {lat:23.391461,lng:120.99807,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/轆轆山.jpg'><h3>轆轆山</h3><p class='infoText'>海拔約為3,267公尺，為由東西雙峰構成，主峰位於中央山脈主脊偏西，峰頂圓形為草生地，半為原始林，稜脊起伏平纋，呈東北蜿蜒，由此可西眺荖濃溪源頭。</p>"},
							  {lat:23.353904,lng:120.975812,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/雲峰.jpg'><h3>雲峰</h3><p class='infoText'>海拔約為3,564公尺，由主峰、東峰、中峰及北峰等構成，位於中央山脈雲峰支脈上，山形雄偉，山勢高聳，南北下臨深谷。峰頂密布板岩，淺竹灌木稀疏，頂脊長平，略有起伏。</p>"},
							  {lat:23.345697,lng:121.010482,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/南雙頭山.jpg'><h3>南雙頭山</h3><p class='infoText'>海拔約為3,288公尺，為中央山脈之主脊山峰，由雙峰東西並峙得名，一般係指西側之山頭。滿山淺竹，稜脊東側有水池，中央山脈至此形成曲折之凹字形稜線，具地形之美。</p>"},
							  {lat:23.4868081,lng:120.9957474,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/八通關.jpg'><h3>八通關</h3><p class='infoText'>海拔約2,800公尺，為八通關古道之重站附近有清兵舊營址，早期為野生動物之原始生育地，目前為高山箭竹之草原地，亦為荖濃溪與陳有蘭溪之分水嶺，極富地形景觀之研究價值。</p>"},
							  {lat:23.546928,lng:120.964139,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/雲龍瀑布.jpg'><h3>雲龍瀑布</h3><p class='infoText'></p>"},
							  {lat:23.229636,lng:120.911665,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/關山.jpg'><h3>關山</h3><p class='infoText'>海拔3,668公尺，為中央山脈大水窟以南最高之山峰，有「南台首嶽」之稱，亦為玉山國家公園南面界山，山脊平直，滿山短竹灌木極稀，由玉山望之如金字塔狀削入天際，氣勢軒昂。登山口亦位在南橫公路進涇橋。</p>"},
							  {lat:23.25830376,lng:120.8981484,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/庫哈諾辛山.jpg'><h3>庫哈諾辛山</h3><p class='infoText'></p>"},
							  {lat:23.26989846,lng:120.9316648,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/塔關山.jpg'><h3>塔關山</h3><p class='infoText'>海拔3,222公尺，山頂距南橫公路147.3公里處之登山口僅2.2公里，登山者多輕裝往返，山峰西為絕壁，南為險瘦稜線，可近睹著名關山大斷崖險惡風貌。</p>"},
							  {lat:23.31993122,lng:121.1203511,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/新康山.jpg'><h3>新康山</h3><p class='infoText'>海拔3,335公尺，為台灣東側最高峰，亦是台灣高山十峻之一，山形為南北走向，山勢獨特，地形陡峭，狹如屏風，自西望之猶如堡壘，氣勢深雄，有「東台一霸」之稱。</p>"},
							  {lat:23.470018,lng:120.957372,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/玉山主峰.jpg'><h3>玉山主峰</h3><p class='infoText'>海拔約為3,952公尺，為台灣第一高峰，展望良好，自峰頂眺望，四方群峰盡在眼底，溪谷、草坡、森林、翠嵐，一覽無遺。全峰為毳碎裂崩解的頁岩構成，裸岩重疊，具有雄偉岩峰景觀。</p>"},
							  {lat:23.446527,lng:120.958574,label:"<img class='infoPic' src='<%= application.getContextPath() %>/assets/images/spot_pic/玉山南峰.jpg'><h3>玉山南峰</h3><p class='infoText'>海拔約為3,844公尺，為若干相連參差尖銳的岩峰組成，峰與峰之間，隔以陡峭岩溝，有苦橫排之巨刃連峰，主峰轉折，狀若游龍，故又名閉鎖曲線峰，並列為台灣高山十峻之一。</p>"}
							];
							
							
							// var locations = [
							//   ["Split",     43.5148515, 16.4490835],
							//   ["Zagreb",    45.840196,  15.9643316],
							//   ["Dubrovnik", 42.6457256, 18.094058] 
							// ];
							
							      function initialize() {
							        var mapOptions = {/*初始位置*/
							          center: {lat: 23.435446, lng: 121.016210},
							          zoom:11, 
							          clickableIcons:true,/*點擊後顯示資訊*/
							          mapTypeId:google.maps.MapTypeId.terrain
							        };
							        /*指定地圖區塊*/
							        map = new google.maps.Map(
							            document.getElementById('map-canvas'),
							            mapOptions); 
								
							 for (var i = 0; i < position.length; i++) {
							    addMarker(i);
							  }
							      }
							
							function addMarker(e) {
							  markers[e] = new google.maps.Marker({
							    position: {
							      lat: position[e].lat,
							      lng: position[e].lng
							    },
							    map: map,
							    animation: google.maps.Animation.DROP
							  });
							  
							var infowindow = new google.maps.InfoWindow();/*註冊資訊窗*/
							      google.maps.event.addListener(markers[e], 'click', function(){
							        infowindow.close(); // Close previously opened infowindow
							        infowindow.setContent("<br><div id='infowindow'>" + position[e].label + "</div>");/*內容*/  
							        infowindow.open(map, markers[e]);
							    });
							}
					      google.maps.event.addDomListener(
					          window, 'load', initialize);
					    </script>
					</div>
				</div>
			</div>
<%-- 		  	<jsp:include page="/common/footer.jsp" flush="true"/> --%>
		</div>
	</div>

<!-- JS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>