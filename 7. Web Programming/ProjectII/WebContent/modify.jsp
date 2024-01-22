<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>고객의견 작성</title>
<link rel="stylesheet" href="./css/board.css">
</head>

<body>
	<div class="board_wrap">
		<div class="board_title">
			<strong>고객의견 수정</strong>
			<p>고객님 의견의 수정사항을 입력해주세요.</p>
		</div>
		<div class="board_write_wrap">
			<form action="modify.do" method="post">

				<div class="board_write">
					<div class="title">
						<dl>
							<dt>제목</dt>
							<dd>${modify.bTitle}</dd>
						</dl>
					</div>
					<div class="info">
						<dl>
							<dt>글쓴이</dt>
							<dd>${modify.bName}</dd>
						</dl>
						<dl>
							<dt>작성일</dt>
							<dd>${modify.bDate}</dd>
						</dl>
						<dl>
							<dt>히트</dt>
							<dd>${modify.bHit}</dd>
						</dl>

					</div>
					<div class="cont">
						<textarea name="bContent">${modify.content}</textarea>
					</div>
				</div>
				<div class="bt_wrap">
					<input type="submit" value="수정"><a href="list.do">취소</a>
				</div>
			</form>
		</div>
	</div>
</body>

</html>
</html>