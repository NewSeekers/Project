<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>reply_page</title>
<link rel="stylesheet" href="./css/board.css">
</head>

<body>
	<div class="board_wrap">
		<div class="board_title">
			<strong>고객의견</strong>
			<p>고객님 의견의 답변을 빠르게 안내해드리겠습니다.</p>
		</div>
		<div class="board_write_wrap">
			<form action="reply.do" method="post">
				<input type="hidden" name="bId" value="${reply_view.bId}"> <input
					type="hidden" name="bGroup" value="${reply_view.bGroup}"> <input
					type="hidden" name="bStep" value="${reply_view.bStep}"> <input
					type="hidden" name="bIndent" value="${reply_view.bIndent}">
				<div class="board_write">
					<div class="title">
						<dl>
							<dt>제목</dt>
							<dd>
								<input type="text" name="bTitle" value="${reply_view.bTitle}">
							</dd>
						</dl>
					</div>
					<div class="info">
						<dl>
							<dt>글쓴이</dt>
							<dd>
								<input type="text" name="bName" value="${reply_view.bName}">
							</dd>
						</dl>
						<dl>
							<dt>번호</dt>
							<dd>${reply_view.bId}</dd>
						</dl>
						<dl>
							<dt>히트</dt>
							<dd>${reply_view.bHit}</dd>
						</dl>
						<!-- <dl>
                        <dt>비밀번호</dt>
                        <dd><input type="password" placeholder="비밀번호 입력"></dd>
                    </dl> -->
					</div>
					<div class="cont">
						<textarea name="bContent">${reply_view.bContent}</textarea>
					</div>
				</div>
				<div class="bt_wrap">
					<input type="submit" value="답변"><a href="list.do">목록</a>
				</div>
			</form>
		</div>
	</div>
</body>

</html>