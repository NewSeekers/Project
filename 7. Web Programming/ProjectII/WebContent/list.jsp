<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>고객의견 목록</title>
<!-- -------------- css ---------------- -->
<link rel="stylesheet" href="./css/board.css">
<link rel="stylesheet" href="./css/header.footer.css">
<!-- -------------- bootstrap ---------- -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>



	<body>

		<!-- header  -->
		<jsp:include page="./include/nav.jsp" />

		<div class="board_wrap">
        <div class="board_title">
            <strong>고객의견</strong>
            <p>고객님의 의견을 빠르게 안내해드리겠습니다.</p>
        </div>
        <div class="board_list_wrap">
            
                <div class="board_list">
                    <div class="top">
                        <div class="num">번호</div>
                        <div class="title">제목</div>
                        <div class="writer">글쓴이</div>
                        <div class="date">작성일</div>
                        <div class="count">조회</div>
                    </div>
                    <c:forEach items="${list}" var="dto">
                        <div>
                            <div class="num">${dto.bId}</div>
                            <div class="title"><c:forEach begin="1"
                                    end="${dto.bIndent}">--</c:forEach> <a
                                href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a></div>
                            <div class="writer">${dto.bName}</div>
                            <!-- <li class="j_list_title"><a href="content_view.do?bId=${dto.bId}">${dto.bName}</a></li> -->
                            <div class="date">${dto.bDate}</div>
                            <div class="count">${dto.bHit}</div>
                        </div>
                    </c:forEach>

                </div>
                <div class="board_page">
                    <a href="#" class="bt first">
                        << </a>
                            <a href="#" class="bt prev">
                                < </a>
                                    <a href="#" class="num on">1</a>
                                    <a href="#" class="num">2</a>
                                    <a href="#" class="num">3</a>
                                    <a href="#" class="num">4</a>
                                    <a href="#" class="num">5</a>
                                    <a href="#" class="bt next">></a>
                                    <a href="#" class="bt last">>></a>
                </div>
                <div class="bt_wrap">
                    <a href="./write_view.jsp" class="on">글쓰기</a>
                </div>
        </div>
    </div>


<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
	integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
	crossorigin="anonymous"></script>
</body>

</html>