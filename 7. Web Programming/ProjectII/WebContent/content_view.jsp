<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>고객의견</title>
<link rel="stylesheet" href="./css/board.css">
</head>

<body>
	<div id="j_board_wrap">

		<div id="j_board_title">
			<strong>고객의견</strong>
			<p>고객님의 의견을 빠르게 안내해드리겠습니다.</p>

		</div>
		<div id="j_board_view_wrap">
			<form action="modify.do" method="post">
				<input type="hidden" name="bId" value="${content_view.bId}">
				<div class="j_board_view">
					<div class="j_view_title">${content_view.bTitle}</div>
					<div class="j_view_info">
						<tr>
							<td>번호</td>
							<td>${content_view.bId}</td>
						</tr>
						<tr>
							<td>글쓴이</td>
							<td>${content_view.bName}</td>
						</tr>
						<tr>
							<td>작성일</td>
							<td>${content_view.bDate}</td>
						</tr>
						<tr>
							<td>조회</td>
							<td>${content_view.bHit}</td>
						</tr>
					</div>
					<div class="j_view_content">
						<textarea rows="10" name="bContent">${content_view.bContent}</textarea>
					</div>
				</div>
				<div class="j_bt_wrap">
					<input type="submit" value="수정">&nbsp;&nbsp; <a
						href="delete.do?bId=${content_view.bId}">삭제</a>&nbsp;&nbsp; <a
						href="list.do">목록보기</a>&nbsp;&nbsp; <a
						href="reply_view.do?bId=${content_view.bId}">답변</a>
					<!-- <a href="list.do" class="on">목록</a>-->
					<!--<a href="/edit.html">수정</a>-->
				</div>
			</form>
		</div>
	</div>
</body>

</html>