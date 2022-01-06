<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이용안내 목록</title>
<style>
h2 {
	text-align: center;
}
</style>

<link rel="stylesheet" type="text/css"
	href="/resources/include/assets/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/include/assets/css/noticeList.css" />

<script type="text/javascript">

	$(function(){
		var file = "<c:out value='${cageRoomVO.c_picture}'/>";
		$("#fileImage").attr({
			src:"/roomPicture/${cageRoomVO.c_picture}",width:"450px",height:"200px"
		});
		
		/* 제목 클릭시 상세 페이지 이동을 위한 처리 이벤트 */
		$(".goDetail").click(function() {
			var c_no = $(this).parents("tr").attr("data-num");
			$("#c_no").val(c_no);
			console.log("글번호 : " + c_no);

			/* 상세 페이지로 이동하기 위해 form추가 */
			$("#detailForm").attr({
				"method" : "get",
				"action" : "/client/guide/roomDetail"
			});
			$("#detailForm").submit();
		});
	});
</script>

</head>
<body>
	<div class="tab-content">
		<div id="guideList" class="contentContainer tab-pane active">
			<div class="contentTit">
				<h2>룸 리스트</h2>
			</div>
			
			<%-- ============= 룸 상세 페이지 이동을 위한 FORM ============== --%>
			<form name="detailForm" id="detailForm" method="post">
				<input type="hidden" name="c_no" id="c_no" />
			</form>
	
			<%--============== 룸 리스트 시작 =============== --%>
			<div id="noticeList">
	
				<table border="1" summary="룸 리스트">
					<colgroup>
						<col width="40%" />
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
					
					</colgroup>
					
					<thead>
						<tr>
							<th class="tac">룸사진</th>
							<th class="tac">룸종류</th>
							<th class="tac">룸유형</th>
							<th class="order">가격</th>
							
						</tr>
					</thead>
					<tbody id="list">
					<!-- 데이터 출력 -->
						<c:choose>
							<c:when test="${not empty roomList}">
								<c:forEach var="room" items="${roomList}" varStatus="status">
								<tr class="tac" data-num="${room.c_no}">
								
									<c:if test="${empty room.c_picture}">
										<td><a href='/client/guide/roomDetail?c_no=${room.c_no}'>등록된 사진 정보가 존재하지 않습니다.</a></td>
									</c:if>
									<c:if test="${not empty room.c_picture}">
										<td><a href='/client/guide/roomDetail?c_no=${room.c_no}'><img src="/image/roomImages/${room.c_picture}"/></a></td>
									</c:if>
										<td>${room.c_kind}</td>
								
										<td>${room.c_type}</td>
								
										<td>${room.c_price} 원</td>
									
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="6" class="tac">등록된 게시물이 존재하지 않습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
	
					</tbody>
				</table>
				<%--============== 리스트 종료 =============== --%>
	
				<div class="contentTit">
					<h2>부가서비스 리스트</h2>
				</div>
				<%-- ============== 부가서비스 리스트 시작 =============== --%>
				<div id="noticeList">
	
					<table border="1" summary="부가서비스 리스트">
						<colgroup>
							<col width="15%" />
							<col width="35%" />
							<col width="20%" />
						</colgroup>
						<thead>
							<tr>
								<th class="order">서비스번호</th>
								<th class="order">서비스 이름</th>
								<th class="order">가격</th>
							</tr>
						</thead>
						<tbody id="list">
							<!-- 데이터 출력 -->
							<c:choose>
								<c:when test="${not empty exData}">
									<c:forEach var="extraservice" items="${exData}"
										varStatus="status">
										<tr class="tac" data-num2="${extraservice.s_no}">
											<td>${extraservice.s_no}</td>
											<td class="goDetail tal">${extraservice.s_name}</td>
											<td>${extraservice.s_price}</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="6" class="tac">등록된 게시물이 존재하지 않습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<%-- ============== 부가서비스 리스트 종료 =============== --%>
				</div>
			</div>
		</div>
			
		<div id="guideInfo" class="tab-pane fade">
				
			<h2>시설 안내</h2>
				
			<br/>
			<br/>
			<h2>오시는 길</h2>
			
			<table border="1">
			<tr align="center">
			<td colspan="1">주소</td>
			<td>서울특별시 성동구 왕십리로 149 도그파크에비뉴 4층 (행당동)<br/>
			※ 주차안내 : 평일주차 무료, 주말주차 30분 무료 (이후 10분 당 500원)</td>
			</tr>
			<tr>
			<td>지하철 주소</td>
			<td>왕십리역(2호선, 5호선, 분당선, 경의중앙선) 12번 출구, 도보 10분<br/>
			한양대역(2호선) 4번출구, 도보 8분</td>
			</tr>
			<tr>
			<td>버스정류장</td>
			<td>행당1동 주민센터, 성동소방서 | 행당동삼부아파트 도보 5분<br/>
				일반 2012, 2013, 2014, 2016, 2222<br/>
			  	간선 302, N62<br/>
			<br/>
			한양대앞 도보 5분<br/>
				 일반 2013, 4211<br/>
				 간선 121</td>
				</tr>
				
				</table>
				<br/>
				<br/>
				
			<section>
				<h3>지도(건물위치)</h3><br/>
				
				<img src="/resources/images/puppymap.jpg"
				style="width:450px;"
				/>
				<br/>
				<br/>
			</section>
		</div>
	</div>
</body>
</html>