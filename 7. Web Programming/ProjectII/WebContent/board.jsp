<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <form action="/insert.do">
                    <div class="j_board_write">
                        <div class="j_write_title">

                            <dl>
                                <dt>제목</dt>
                                <dd><input type="text" placeholder="제목 입력" value="제목을 입력해주세요"></dd>
                            </dl>
                        </div>
                        <div class="j_write_info">
                            <dl>
                                <dt>글쓴이</dt>
                                <dd><input type="text" placeholder="글쓴이 입력" value="홍길동"></dd>
                            </dl>
                            <dl>
                                <dt>비밀번호</dt>
                                <dd><input type="password" placeholder="비밀번호 입력" value=""></dd>
                            </dl>
                        </div>
                        <div class="j_write_content">
                            <textarea placeholder="내용 입력">
                        </textarea>
                        </div>
                    </div>
                    <div class="j_bt_wrap">

                        <a href="./Index.html" class="on">보내기</a>
                        <a href="view.html">취소</a>
                    </div>
                </form>
            </div>
        </div>
    </body>

    </html>