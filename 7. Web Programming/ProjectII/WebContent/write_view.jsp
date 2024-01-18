<?xml version="1.0" encoding="UTF-8" ?>
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
	<div id="j_board_wrap">
		<div id="j_board_title">
			<strong>고객의견</strong>
			<p>고객님 의견의 답변을 빠르게 안내해드리겠습니다.</p>
		</div>
		<div class="j_board_write_wrap">
			<form action="write.do" method="post">
				<div class="j_board_write">
					<div class="j_write_title">
						<tr>
							<td>제목</td>
							<td><input type="text" name="bTitle" placeholder="제목 입력"></td>
						</tr>
					</div>
					<div class="j_write_info">
						<tr>
							<td>글쓴이</td>
							<td><input type="text" name="bName" placeholder="글쓴이 입력"></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="bContent" placeholder="내용 입력"></textarea></td>
						</tr>
					</div>
					<div class="j_write_content"></div>
				</div>
				<div class="j_bt_wrap">
					<INPUT type="submit" value="글쓰기" /> <a href="list.do">취소</a>
				</div>
			</form>
		</div>
	</div>
</body>

</html>