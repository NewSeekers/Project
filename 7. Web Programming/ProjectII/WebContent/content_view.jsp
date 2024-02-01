<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>고객의견</title>
<!-- css -->
<link rel="stylesheet" href="./css/board.css">
<link rel="stylesheet" href="./css/header.footer.css">
	<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>

<body>
	<!-- header -->
	<jsp:include page="./include/nav.jsp" />
	<div class="board_wrap">
		<div class="board_title">
			<strong>고객의견</strong>
			<p>고객님 의견의 답변을 빠르게 안내해드리겠습니다.</p>
		</div>
		<div class="board_view_wrap">
			<form action="modify.jsp" method="post" accept-charset="utf-8">
				<input type="hidden" name="bId" value="${content_view.bId}"><input
					type="hidden" name="bName" value="${content_view.bName}"><input
					type="hidden" name="bTitle" value="${content_view.bTitle}"><input
					type="hidden" name="bDate" value="${content_view.bDate}"><input
					type="hidden" name="bHit" value="${content_view.bHit}">
				<div class="board_view">
					<div class="title">${content_view.bTitle}</div>
					<div class="info">
						<dl>
							<dt>번호</dt>
							<dd>${content_view.bId}</dd>
						</dl>
						<dl>
							<dt>글쓴이</dt>
							<dd>${content_view.bName}</dd>
						</dl>
						<dl>
							<dt>작성일</dt>
							<dd>${content_view.bDate}</dd>
						</dl>
						<dl>
							<dt>조회</dt>
							<dd>${content_view.bHit}</dd>
						</dl>
					</div>
					<div class="cont">
						<div id="content_view" name="bContent">${content_view.bContent}</div>
					</div>
				</div>



				<c:if test="${not empty sessionScope.ValidMem}">
					<div class="bt_wrap">


						<c:if test="${showEditButton}">
							<input type="submit" value="수정">&nbsp;&nbsp; &nbsp;&nbsp;
							
							<a href="delete.do?bId=${content_view.bId}">삭제</a>
						</c:if>


						<a href="list.do?page=1">목록보기</a>&nbsp;&nbsp; <a
							href="reply_view.jsp?bId=${content_view.bId}&bName=${content_view.bName}&bTitle=${content_view.bTitle}
						&bDate=${content_view.bDate}&bHit=${content_view.bHit}&bContent=${content_view.bContent}
						&bGroup=${content_view.bGroup}&bIndent=${content_view.bIndent}&bStep=${content_view.bStep}">답변</a>
					</div>
				</c:if>


				<c:if test="${empty sessionScope.ValidMem}">
					<div class="bt_wrap">
						<a href="list.do?page=1">목록보기</a>
					</div>
				</c:if>



			</form>
		</div>
	</div>
	<!-- footer -->
	<jsp:include page="./include/footer.jsp" />
	<!-- bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
		integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
		crossorigin="anonymous"></script>
</body>

</html>