<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dfebd97b0ebbc269f439edcad3f447ac"></script>
	
  <!-- map style -->
   <style>
    .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
    .wrap * {padding: 0;margin: 0;}
    .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
    .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
    .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
    .info .close:hover {cursor: pointer;}
    .info .body {position: relative;overflow: hidden;}
    .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
    .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
    .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
    .info .link {color: #5085BB;}
	</style>
	<!-- map sytle 끝 -->
	<style>
		.div-select{
	  	width:100%;
	  	height:100px;
	  	margin: 0 auto;
	  	background-color: #3F3F3F;
	  	text-align:center;
	  	padding:35px;
	  }
	  .background-gray{
	  	background-color: #5F5F5F;
	  	color:white;
	  }
	</style>
	<script>
	$(function(){
	
		let datas = null;
		<c:if test="${dealDetailJson ne null}">
			datas = ${dealDetailJson};
		</c:if>
		let lati = 33.451475;
		let longi = 126.570528;
		if(datas != null){		
			lati = datas[0].lat;
			longi = datas[0].lng;
		}
		
		var mapContainer = document.getElementById('map'), // 지도의 중심좌표
			mapOption = { 
		    center: new kakao.maps.LatLng(lati, longi), // 지도의 중심좌표
		    level: 3 // 지도의 확대 레벨
		}; 

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		let listDatas = "<table class=\"table mt-2\">";
        listDatas += "<tr><th>번호</th><th>법정동</th><th>아파트이름</th><th>지역코드</th><th>거래금액</th>"
        +"<th>건축일자</th><th>거래연도</th><th>평수</th><th>층</th><th>지번</th></tr>"
        
		$.each(datas, function(index, item) {

			let listData = "<tr>" 
				+ "<td>"+item.no+"</td>"
				+ "<td>"+item.dong+"</td>"
				+ "<td>"+item.aptName+"</td>"
				+ "<td>"+item.code+"</td>"
				+ "<td>"+item.dealAmount+"</td>"
				+ "<td>"+item.buildYear+"</td>"
				+ "<td>"+item.dealYear+"</td>"
				+ "<td>"+item.area+"</td>"
				+ "<td>"+item.floor+"</td>"
				+ "<td>"+item.jibun+"</td>"
        + "</tr>";
    	listDatas += listData;
		
    	var content = '<div class="wrap">' + 
        '    <div class="info">' + 
        '        <div class="title">' + 
                    	item.aptName + 
        '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
        '        </div>' + 
        '        <div class="body">' + 
        '            <div class="img">' +
        '                <img src="https://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">' +
        '           </div>' + 
        '            <div class="desc">' + 
        '                <div class="ellipsis">' + item.dong + " " + item.jibun + '</div>' + 
        '                <div class="jibun ellipsis">' + "거래연도 " + item.dealYear + '</div>' + 
        '                <div>' + "거래금액 " + item.dealAmount + '</div>' + 
        '            </div>' + 
        '        </div>' + 
        '    </div>' +    
        '</div>';
		
    	var marker = new kakao.maps.Marker({
            map: map, 
            position: new kakao.maps.LatLng(parseFloat(item.lat), parseFloat(item.lng))
        });


     	var overlay = new kakao.maps.CustomOverlay({
            content: content,
            map: map,
            position: marker.getPosition()
        });

    	kakao.maps.event.addListener(marker, 'mouseover', function() {
            overlay.setMap(map);
        });
     	kakao.maps.event.addListener(marker, 'mouseout', function() {
         overlay.setMap(null);
     		});
     	overlay.setMap(null);
     	function closeOverlay() {
            if(overlay !== undefined)
                overlay.setMap(null);
        }
     	
		});//each
		
    $("#data-show").html(listDatas);
		
	});
	</script>

</head>

	<body>
	
		<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		  <a class="navbar-brand" href="./index.jsp">HappyHouse</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="collapsibleNavbar">
		    <ul class="navbar-nav">
		      <li class="nav-item">
		        <a class="nav-link" href="#">공지사항</a>
		      </li>
		      <li class="nav-item">
<!-- 		        <a class="nav-link" href="./dealDetailDatas.jsp">검색</a> -->
		      </li>
		      <li class="nav-item">
<!-- 		        <a class="nav-link" href="./dealDetailDatas.jsp">상세보기</a> -->
		      </li>    
		    </ul>
		  </div> 
		  <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
		    <ul class="navbar-nav">
		      <c:if test="${not empty userinfo}">
					<li class="nav-item"><button type="button"
							onclick="javascript:logout();">로그아웃</button></li>
					<li class="nav-item"><button type="button"
							onclick="javascript:memberDetail();">회원정보</button></li>
				</c:if>
				<c:if test="${empty userinfo}">
					<li class="nav-item"><a class="nav-link"
						href="./user/login.jsp">Login</a></li>
					<li class="nav-item"><a class="nav-link"
						href="./user/join.jsp">회원가입</a></li>
				</c:if>
		    </ul>
		  </div>  
		</nav>
	
		<br/>
		<div id="map" style="width:80%; height:500px; margin:0 auto;"></div>
		<br/>
		<div id="data-show"></div>
		
	</body>
</html>