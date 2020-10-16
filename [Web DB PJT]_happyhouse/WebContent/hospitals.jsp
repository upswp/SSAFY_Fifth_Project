<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<!-- header호출 -->
	<jsp:include page="./common/header.jsp" />
	<script>

		$(function(){
			
			let lati = 37.5642135;
			let longi = 127.0016985;
			
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(lati, longi), // 지도의 중심좌표
		        level: 7 // 지도의 확대 레벨
		    };  
	
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();
			
			// 주소로 좌표를 검색합니다
			let hospitals = null;
			<c:if test="${hospitalJson ne null}">
				hospitals = ${hospitalJson};
			</c:if>
		
			if(hospitals != null){
				
				let listDatas = "<table class=\"table mt-2\">";
		        listDatas += "<tr><th>기준날짜</th><th>시도</th><th>구군</th><th>이름</th>"
		        +"<th>주소</th><th>타입</th><th>번호</th></tr>"
				
				$.each(hospitals, function(index, item) {
					
			      let listData = "<tr>" 
						+ "<td>"+item.date+"</td>"
						+ "<td>"+item.sido+"</td>"
						+ "<td>"+item.gugun+"</td>"
						+ "<td>"+item.name+"</td>"
						+ "<td>"+item.address+"</td>"
						+ "<td>"+item.type+"</td>"
						+ "<td>"+item.tel+"</td>"
		        + "</tr>";
		    		listDatas += listData;
					
					geocoder.addressSearch(item.address, function(result, status) {
					
					    // 정상적으로 검색이 완료됐으면 
					     if (status === kakao.maps.services.Status.OK) {
					
					        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
					
					        // 결과값으로 받은 위치를 마커로 표시합니다
					        var marker = new kakao.maps.Marker({
					            map: map,
					            position: coords
					        });
					
					        // 인포윈도우로 장소에 대한 설명을 표시합니다
					        var infowindow = new kakao.maps.InfoWindow({
					            content: '<div style="width:150px;text-align:center;padding:6px 0;"> '+ item.name +'</div>'
					        });
					        infowindow.open(map, marker);
					
					        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					        map.setCenter(coords);
				    	
					    } 
					});    
					
				}); //each
				
		    $("#data-show").html(listDatas);
			}
			
		});
	
	</script>

</head>
<body>
	<!-- nav 호출 -->
	<jsp:include page="./common/nav.jsp" />
	
	<!-- contents start -->
	
	<br/>
	<div id="map" style="width:80%; height:500px; margin:0 auto;"></div>
	<br/>
	<div id="data-show"></div>
	<!-- contents end -->
	
	
	<!-- footer호출 -->
	<jsp:include page="./common/footer.jsp" />

</body>
</html>