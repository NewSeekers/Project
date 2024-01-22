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
	<div id="j_board_wrap">
		<div id="j_board_title">
			<strong>답변페이지</strong>
			<p>고객님 의견을 답변으로 작성해주세요.</p>
		</div>
		<div class="j_board_write_wrap">

			<form action="reply.do" method="post">

				<input type="hidden" name="bId" value="${reply_view.bId}"> <input
					type="hidden" name="bGroup" value="${reply_view.bGroup}"> <input
					type="hidden" name="bStep" value="${reply_view.bStep}"> <input
					type="hidden" name="bIndent" value="${reply_view.bIndent}">
				<div class="j_board_write">
					<div class="j_write_title">
						<div class="j_write_title">
							<tr>
								<td>번호</td>
								<td>${reply_view.bId}</td>
							</tr>
						</div>
						<div class="j_write_info">
							<tr>
								<td>히트</td>
								<td>${reply_view.bHit}</td>
							</tr>
						</div>
					
						<div class="j_write_info">
							<tr>
								<td>제목</td>
								<td><input type="text" name="bTitle"
									value="${reply_view.bTitle}"></td>
							</tr>
						</div>
							<div class="j_write_info">
							<tr>
								<td>이름</td>
								<td><input type="text" name="bTitle"
									value="${reply_view.bName}"></td>
							</tr>
						</div>
						<div id="reply_content">
							<tr>
								<td><textarea rows="10" name="bContent">${reply_view.bContent}</textarea></td>
							</tr>
						</div>
						<div id="j_reply_content"></div>
					</div>

				</div>
				<div class="j_bt_wrap">
					<input type="submit" value="답변"><a href="list.do">목록</a>

				</div>
			</form>
		</div>
	</div>
</body>

</html>