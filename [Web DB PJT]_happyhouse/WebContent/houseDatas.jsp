<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<fmt:requestEncoding value="utf-8" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<!-- header호출 -->
	<jsp:include page="common/header.jsp" />
	<!-- css호출 -->
	<link rel="stylesheet" href="css/houseDatas.css" type="text/css">
	<!-- js호출 -->
	<script type="text/javascript" src ="js/houseDatas.js"></script>
</head>

<body>
<!-- nav호출 -->
<jsp:include page="nav.jsp" />		
		<!-- select 검색 시작 -->
		<div class="div-select">
			 <form id = "dong-form" action="${root}/map" method="post">
			 <input type="hidden" name='act' value='apt'/>
			 	<select id="house-type" name="house-type" class="background-gray">
		      <option value="">선택</option>
		      <option value="전체">전체</option>
		      <option value="아파트">아파트</option>
		      <option value="주택">주택</option>
		   	</select>
		    <select id="sido" name="sido" class="background-gray">
		      <option value="">선택</option>
		    </select>
		    <select id="gugun" name="gugun" class="background-gray">
		      <option value="">선택</option>
		    </select>
		    <select id="dong" name="dong" class="background-gray">
		      <option value="">선택</option>
		    </select>
		    <input type="submit" value="검색" class="background-gray">
	    </form>
		</div>
		<!-- select 검색 끝 -->
	
		<br/>
	
		<br/>
		<div id="map" style="width:80%; height:500px; margin:0 auto;"></div>
		<br/>
		<button type="button" class="btn btn-primary" onclick="javascript:hosipital();">주변 안심병원</button>
		<button type="button" class="btn btn-primary" onclick="javascript:clinic();">주변 선별진료소</button>
		<br/>
		<div id="data-show"></div>

<script type="text/javascript">

function hosipital(){
	document.location.href = "../clinic?act=showHospitals&&gugun=${gugun}";
}

function clinic(){
	document.location.href = "../hosipital?act=showClinics&&gugun=${gugun}";
}


$(function(){
	
	let datas = null;
	<c:if test="${aptJson ne null}">
		datas = ${aptJson};
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
	    level: 4 // 지도의 확대 레벨
	}; 

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	let listDatas = "<table class=\"table mt-2\">";
    listDatas += "<tr><th>번호</th><th>법정동</th><th>아파트이름</th><th>지번</th><th>지역코드</th><th>위도</th><th>경도</th><th>상세거래정보</th></tr>"
    
	$.each(datas, function(index, item) {

		let listData = "<tr>" 
			+ "<td>"+item.no+"</td>"
			+ "<td>"+item.dong+"</td>"
			+ "<td>"+item.aptName+"</td>"
			+ "<td>"+item.jibun+"</td>"
			+ "<td>"+item.code+"</td>"
			+ "<td>"+item.lat+"</td>"
			+ "<td>"+item.lng+"</td>"
			+ "<td>		<form id = 'test' action='${root}/deal' method='post' >"
			+ "<input type='hidden' name='act' value='deal-detail'/>"
			+	"<input type='hidden' name='dong' value='" +item.dong+ "'/>"
			+	"<input type='hidden' name='jibun' value='" +item.jibun+ "'/>"
			+	"<input type='hidden' name='lat' value='" +item.lat+ "'/>"
			+	"<input type='hidden' name='lng' value='" +item.lng+ "'/>"
			+	"<input type='submit' value='거래정보검색'/>"
			+" </form></td>"
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
    '                <div class="jibun ellipsis">' + item.lat + " " + item.lng + '</div>' + 
    '                <div>' + item.code + '</div>' + 
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
	</body>
</html>