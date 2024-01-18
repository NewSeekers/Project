<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>고객의견 목록</title>
<link rel="stylesheet" href="./css/board.css">
</head>

<div id="j_board_wrap">

	<body>
		<div id="j_board_title">
			<!-- 제목 -->
			<strong>고객의견</strong>
			<!-- 내용 -->
			<p>고객님의 의견을 빠르게 안내해드리겠습니다.</p>

		</div>
		<div id="j_board_list_wrap">
			<div id="j_board_list">
				<!-- title 열 -->
				<ul class="j_board_top">
					<li class="j_list_num">번호</li>
					<li class="j_list_title">제목</li>
					<li class="j_list_writer">글쓴이</li>
					<li class="j_list_date">날짜</li>
					<li class="j_list_count">조회수</li>
				</ul>

				<!-- 게시글 추가로 들어오는 위치 -->

				<c:forEach items="${list }" var="dto">
					<ul>
						<li class="j_list_num">${dto.bId}</li>
						<li class="j_list_title"><a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a></li>
						<li class="j_list_writer">${dto.bName}</li>
						<li><c:forEach begin="1" end="${dto.bIndent}">-</c:forEach> <a
							href="content_view.do?bId=${dto.bId}"></a></li>
						<li class="j_list_date">${dto.bDate}</li>
						<li class="j_list_count">${dto.bHit}</li>
					</ul>
				</c:forEach>
			</div>


			<div id="j_board_page">

				<ul class="pagination">
					<li></li>
					<a href="#" class="j_bt first"> <- </a>
					<a href="#" class="j_bt prev"> / </a>
					<a href="#" class="j_num on">1</a>
					<a href="#" class="j_num">2</a>
					<a href="#" class="j_num">3</a>
					<a href="#" class="j_num">4</a>
					<a href="#" class="j_num">5</a>
					<a href="#" class="j_bt next"> ></a>
					<a href="#" class="j_bt last"> >></a>

				</ul>

			</div>
			<div class="j_bt_wrap">
				<a href="./write_view.jsp" class="j_on">글쓰기</a>

			</div>
		</div>
</div>

</body>

</html>