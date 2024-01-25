<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>고객의견</title>
<link rel="stylesheet" href="./css/board.css">
</head>

<body>
	<div class="board_wrap">
		<div class="board_title">
			<strong>고객의견</strong>
			<p>고객님 의견의 답변을 빠르게 안내해드리겠습니다.</p>
		</div>
		<div class="board_view_wrap">
			<form action="modify.jsp" method="post">
				<input type="hidden" name="bId" value="${content_view.bId}">
				<input type="hidden" name="bName" value="${content_view.bName}">
				<input type="hidden" name="bTitle" value="${content_view.bTitle}">
				<input type="hidden" name="bDate" value="${content_view.bDate}">
				<input type="hidden" name="bHit" value="${content_view.bHit}">

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
						<textarea rows="10" name="bContent">${content_view.bContent}</textarea>
					</div>
				</div>
				<div class="bt_wrap">
					<input type="submit" value="수정">&nbsp;&nbsp; <a
						href="delete.do?bId=${content_view.bId}">삭제</a>&nbsp;&nbsp; <a
						href="list.do?page=1">목록보기</a>&nbsp;&nbsp; <a
						href="reply_view.jsp?bId=${content_view.bId}&bName=${content_view.bName}&bTitle=${content_view.bTitle}
						&bDate=${content_view.bDate}&bHit=${content_view.bHit}&bContent=${content_view.bContent}
						&bGroup=${content_view.bGroup}&bIndent=${content_view.bIndent}&bStep=${content_view.bStep}">답변</a>
					<!-- <a href="list.do" class="on">목록</a>-->
					<!--<a href="/edit.html">수정</a>-->
				</div>
			</form>
		</div>
	</div>
</body>

</html>