<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
	<%@page import="memberModel.MemberDto"%>
<%@page import="memberModel.MemberDao"%>
<%
	String id = (String) session.getAttribute("id");
	MemberDao dao = MemberDao.getInstance();
	MemberDto dto = dao.getMember(id);
%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>reply_page</title>
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
		<div class="board_write_wrap">
			<div class="board_view">
				<div class="title">${param.bTitle}</div>
				<div class="info">
					<dl>
						<dt>번호</dt>
						<dd>${param.bId}</dd>
					</dl>
					<dl>
						<dt>글쓴이</dt>
						<dd>${param.bName}</dd>
					</dl>
					<dl>
						<dt>작성일</dt>
						<dd>${param.bDate}</dd>
					</dl>
					<dl>
						<dt>조회</dt>
						<dd>${param.bHit}</dd>
					</dl>
				</div>
				<div class="cont">
					<dl>
						<dt>내용</dt>
						<dd>${param.bContent}</dd>
					</dl>
				</div>
			</div>
			<form action="reply.do" method="post">
				<input type="hidden" name="bId" value="${param.bId}"> <input
					type="hidden" name="bGroup" value="${param.bGroup}"> <input
					type="hidden" name="bStep" value="${param.bStep}"> <input
					type="hidden" name="bIndent" value="${param.bIndent}">

				<div class="board_write">
					<div class="title">
						<dl>
							<dt>제목</dt>
							<dd>
								<input type="text" name="bTitle">
							</dd>
						</dl>
					</div>
					<div class="info">
						<dl>
							<dt>글쓴이</dt>
							<dd>
								<input type="hidden" name="bName" value="<%= dto.getId() %>>">
								<%=dto.getId() %>
							</dd>
						</dl>

					</div>
					<div class="cont">
						<textarea name="bContent"></textarea>
					</div>
				</div>
				<div class="bt_wrap">
					<input type="submit" value="답변"><a href="list.do?page=1">목록</a>
				</div>
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