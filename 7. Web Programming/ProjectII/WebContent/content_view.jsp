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
            <div class="j_board_view">
                <div class="j_view_title">
                    ${content_view.bTitle}
                </div>
                <div class="j_view_info">
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
                <div class="j_view_content">
						${content_view.bContent}
                </div>
            </div>
            <div class="j_bt_wrap">
                <a href="list.do" class="on">목록</a>
                <!--<a href="/edit.html">수정</a>-->
            </div>
        </div>
    </div>
</body>

</html>