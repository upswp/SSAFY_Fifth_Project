<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<fmt:requestEncoding value="utf-8" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>HappyHouse</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dfebd97b0ebbc269f439edcad3f447ac"></script>

<!--  카카오 맵 style 설정 -->
<style>
.wrap {
	position: absolute;
	left: 0;
	bottom: 40px;
	width: 288px;
	height: 132px;
	margin-left: -144px;
	text-align: left;
	overflow: hidden;
	font-size: 12px;
	font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
	line-height: 1.5;
}

.wrap * {
	padding: 0;
	margin: 0;
}

.wrap .info {
	width: 286px;
	height: 120px;
	border-radius: 5px;
	border-bottom: 2px solid #ccc;
	border-right: 1px solid #ccc;
	overflow: hidden;
	background: #fff;
}

.wrap .info:nth-child(1) {
	border: 0;
	box-shadow: 0px 1px 2px #888;
}

.info .title {
	padding: 5px 0 0 10px;
	height: 30px;
	background: #eee;
	border-bottom: 1px solid #ddd;
	font-size: 18px;
	font-weight: bold;
}

.info .close {
	position: absolute;
	top: 10px;
	right: 10px;
	color: #888;
	width: 17px;
	height: 17px;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');
}

.info .close:hover {
	cursor: pointer;
}

.info .body {
	position: relative;
	overflow: hidden;
}

.info .desc {
	position: relative;
	margin: 13px 0 0 90px;
	height: 75px;
}

.desc .ellipsis {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.desc .jibun {
	font-size: 11px;
	color: #888;
	margin-top: -2px;
}

.info .img {
	position: absolute;
	top: 6px;
	left: 5px;
	width: 73px;
	height: 71px;
	border: 1px solid #ddd;
	color: #888;
	overflow: hidden;
}

.info:after {
	content: '';
	position: absolute;
	margin-left: -12px;
	left: 50%;
	bottom: 0;
	width: 22px;
	height: 12px;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
}

.info .link {
	color: #5085BB;
}
</style>
<!--  카카오 맵 style 설정 끝 -->
<style>
.carousel-inner img {
	-webkit-filter: grayscale(90%);
	filter: grayscale(90%); /* make all photos black and white */
	width: 100%; /* Set width to 100% */
	margin: auto;
}

.div-select {
	width: 100%;
	height: 100px;
	margin: 0 auto;
	background-color: #3F3F3F;
	text-align: center;
	padding: 35px;
}

.background-gray {
	background-color: #5F5F5F;
	color: white;
}

.contents {
	width: 80%;
	margin: 0 auto;
}
</style>

<!-- 카카오 맵 script -->
<script>
	$(function() {
		var mapContainer = document.getElementById('map'), // 지도의 중심좌표
		mapOption = {
			center : new kakao.maps.LatLng(33.451475, 126.570528), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		// 지도에 마커를 표시합니다 
		var marker = new kakao.maps.Marker({
			map : map,
			position : new kakao.maps.LatLng(33.450701, 126.570667)
		});

		// 커스텀 오버레이에 표시할 컨텐츠 입니다
		// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		// 별도의 이벤트 메소드를 제공하지 않습니다 
		var content = '<div class="wrap">'
				+ '    <div class="info">'
				+ '        <div class="title">'
				+ '            카카오 스페이스닷원'
				+ '            <div class="close" onclick="closeOverlay()" title="닫기"></div>'
				+ '        </div>'
				+ '        <div class="body">'
				+ '            <div class="img">'
				+ '                <img src="https://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">'
				+ '           </div>'
				+ '            <div class="desc">'
				+ '                <div class="ellipsis">제주특별자치도 제주시 첨단로 242</div>'
				+ '                <div class="jibun ellipsis">(우) 63309 (지번) 영평동 2181</div>'
				+ '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>'
				+ '            </div>' + '        </div>' + '    </div>'
				+ '</div>';

		// 마커 위에 커스텀오버레이를 표시합니다
		// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		var overlay = new kakao.maps.CustomOverlay({
			content : content,
			map : map,
			position : marker.getPosition()
		});

		kakao.maps.event.addListener(marker, 'mouseover', function() {
			overlay.setMap(map);
		});
		kakao.maps.event.addListener(marker, 'mouseout', function() {
			overlay.setMap(null);
		});

		function closeOverlay() {
			if (overlay !== undefined)
				overlay.setMap(null);
		}

	});
</script>
<!-- 카카오 맵 script 끝-->
<!-- 시군구 읍면동 얻어오기 시작 -->
<script>
	$(document).ready(
			function() {
				$.get("${pageContext.request.contextPath}/map", {
					act : "sido"
				}, function(data, status) {
					$.each(data, function(index, vo) {
						$("#sido").append(
								"<option value='"+vo.sido_code+"'>"
										+ vo.sido_name + "</option>");
					});//each
				}//function
				, "json");//get
			});//ready
	$(document).ready(
			function() {
				$("#sido").change(
						function() {
							$.get("${pageContext.request.contextPath}/map", {
								act : "gugun",
								sido : $("#sido").val()
							}, function(data, status) {
								$("#gugun").empty();
								$("#gugun").append(
										'<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#gugun").append(
											"<option value='"+vo.gugun_code+"'>"
													+ vo.gugun_name
													+ "</option>");
								});//each
							}//function
							, "json");//get
						});//change
				$("#gugun").change(
						function() {
							$.get("${pageContext.request.contextPath}/map", {
								act : "dong",
								gugun : $("#gugun").val()
							}, function(data, status) {
								$("#dong").empty();
								$("#dong").append(
										'<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#dong").append(
											"<option value='"+vo.dong+"'>"
													+ vo.dong + "</option>");
								});//each
							}//function
							, "json");//get
						});//change
			});//ready
</script>
<!-- 시군구 읍면동 얻어오기 끝 -->

</head>
<body>

	<!-- 네이게이션 바 시작 -->
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="./index.jsp">HappyHouse</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#">공지사항</a></li>
				<!-- 				<li class="nav-item"><a class="nav-link" href="./dealDetailDatas.jsp">검색</a></li> -->
				<!-- 				<li class="nav-item"><a class="nav-link" href="./dealDetailDatas.jsp">상세보기</a></li> -->
			</ul>
		</div>
		<div class="collapse navbar-collapse justify-content-end"
			id="collapsibleNavbar">
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

	<!-- 네비게이션바 끝 -->

	<!-- carousel 시작 -->
	<div id="demo" class="carousel slide" data-ride="carousel"
		data-interval="3000">
		<ul class="carousel-indicators">
			<li data-target="#demo" data-slide-to="0" class="active"></li>
			<li data-target="#demo" data-slide-to="1"></li>
			<li data-target="#demo" data-slide-to="2"></li>
		</ul>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="./img/home1.jpg" alt="Los Angeles" width="1100"
					height="500">
				<div class="carousel-caption">
					<h3>Los Angeles</h3>
					<p>We had such a great time in LA!</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="./img/home2.jpg" alt="Chicago" width="1100" height="500">
				<div class="carousel-caption">
					<h3>Chicago</h3>
					<p>Thank you, Chicago!</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="./img/home3.jpg" alt="New York" width="1100" height="500">
				<div class="carousel-caption">
					<h3>New York</h3>
					<p>We love the Big Apple!</p>
				</div>
			</div>
		</div>
		<a class="carousel-control-prev" href="#demo" data-slide="prev"> <span
			class="carousel-control-prev-icon"></span>
		</a> <a class="carousel-control-next" href="#demo" data-slide="next">
			<span class="carousel-control-next-icon"></span>
		</a>
	</div>
	<!-- carousel 끝 -->

	<!-- select 검색 시작 -->
	<div class="div-select">
		<form id="dong-form" action="/happyhouse/map" method="post">
			<input type="hidden" name='act' value='apt' /> <select
				id="house-type" name="house-type" class="background-gray">
				<option value="">선택</option>
				<option value="전체">전체</option>
				<option value="아파트">아파트</option>
				<option value="주택">주택</option>
			</select> <select id="sido" name="sido" class="background-gray">
				<option value="">선택</option>
			</select> <select id="gugun" name="gugun" class="background-gray">
				<option value="">선택</option>
			</select> <select id="dong" name="dong" class="background-gray">
				<option value="">선택</option>
			</select> <input type="submit" value="검색" class="background-gray">
		</form>
	</div>
	<!-- select 검색 끝 -->

	<br />
	<!-- 카카오 맵 시작 -->
	<div id="map" style="width: 80%; height: 350px; margin: 0 auto;"></div>
	<!-- 카카오 맵 끝 -->

	<br />

	<!-- contents 시작 -->
	<div class="container-fluid contents">
		<div class="row">
			<div class="col-sm-8">
				<h3>게시판</h3>
				<table class="table table_striped">
					<thead>
						<tr>
							<th class="title">제목</th>
							<th>작성자</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>덕분에 좋은 집 얻고갑니다!</td>
							<td>윤기현</td>
							<td>1756</td>
						</tr>
						<tr>
							<td>저희 부모님이 너무 좋아하세요!</td>
							<td>박지현</td>
							<td>998</td>
						</tr>
						<tr>
							<td>구해줘 홈즈는 필요없다!</td>
							<td>한혜원</td>
							<td>856</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-sm-4">
				<h3>[ News! ]</h3>
				<table class="table table_bordered table_hover">
					<thead>
						<tr>
							<th class="title">제목</th>
							<th>작성자</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>정부 아파트정책 Happay Hou..</td>
							<td>기자</td>
							<td>12</td>
						</tr>
						<tr>
							<td>문재인 대통령 HH대표에게 표창장을..</td>
							<td>기자</td>
							<td>998</td>
						</tr>
						<tr>
							<td>30년만에 내집마련 이곳에서!</td>
							<td>기자</td>
							<td>856</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- contents 끝 -->
	<br />

	<!-- footer 시작 -->
	<div style="background-color: #DFDFDF">
		<br />
		<footer id="footer" class="contents">
			<h5 class="title">Find Us</h5>
			<ul class="list-icons">
				<li><i class="fa fa-map-marker pr-2 text-default"></i>(SSAFY)
					서울시 강남구 테헤란로 멀티스퀘어</li>
				<li><i class="fa fa-phone pr-2 text-default"></i> 1544-9001</li>
				<li><a href="#"><i class="fa fa-envelope-o pr-2"></i>admin@ssafy.com</a></li>
			</ul>
			<!-- .subfooter start -->
			<!-- ================ -->
			<div class="subfooter">
				<div class="container">
					<div class="subfooter-inner">
						<div class="row">
							<div class="col-md-12">
								<p class="text-center">Copyright by SSAFY. All rights
									reserved.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- .subfooter end -->

		</footer>
	</div>
	<!-- footer 끝 -->
	<script type="text/javascript">
		function logout() {
			document.location.href = "${root}/member?act=logout";
		}
		function memberDetail() {
			document.location.href = "${root}/member?act=memberDetail";
		}
	</script>
</body>

</html>
