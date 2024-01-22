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
			<strong>고객의견 수정</strong>
			<p>수정사항을 입력해주세요.</p>
		</div>
		<div class="j_board_write_wrap">
			<form action="modify.do" method="post">
				<div class="j_board_write">
					<div class="j_write_title">
						<tr>
							<td>제목</td>
							<td>${modify.bTitle}</td>
						</tr>
					</div>
					<div class="j_write_info">
						<tr>
							<td>글쓴이</td>
							<td>${modify.bName}</td>
						</tr>
						<tr>
							<td>작성일</td>
							<td>${modify.bDate}</td>
						</tr>
						<tr>
							<td>조회</td>
							<td>${modify.bHit}</td>
						</tr>
					</div>
					<div class="j_write_content">
						<textarea name="bContent">
                    ${modify.content}
                    </textarea>
					</div>
				</div>
				<div class="j_bt_wrap">
					<input type="submit" value="수정" /><a href="list.do">취소</a>
				</div>
			</form>
		</div>
	</div>
</body>

</html>
</html>