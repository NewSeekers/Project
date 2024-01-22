<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!doctype html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>NewSeekers</title>
        <!-- -------------- css ---------------- -->
        <link rel="stylesheet" href="./css/index.css">
        <link rel="stylesheet" href="./css/header.footer.css">

        <!-- -------------- bootstrap, map ---------------- -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
            integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
            crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>
    
      <!-- header  -->

	<jsp:include page="./include/nav.jsp" />


        <!-- 캐러셀 -->

        <div id="carousel1" class="carousel carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carousel1" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carousel1" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carousel1" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active" data-bs-interval="2000">
                    <img src="./img/carousel1.jpg" class="d-block w-100" alt="...">
                    <a href="./seoul_main.jsp"><button type="button" class="btn1 btn-outline-dark"> 바로가기 </button></a>

                </div>
                <div class="carousel-item">
                    <img src="./img/carousel2.jpg" class="d-block w-100" alt="...">
                    <a href="./gu_page.jsp"><button type="button" class="btn1 btn-outline-dark"> 바로가기 </button></a>

                </div>
                <div class="carousel-item">
                    <img src="./img/carousel3.jpg" class="d-block w-100" alt="...">
                    <a href="./preview.jsp"><button type="button" class="btn3 btn-outline-dark"> 바로가기</button></a>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carousel1" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carousel1" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

        <div class="intro container mt-5 mb-5">
            <div class="row">
                <div class="intro1 col-md-6 px-5">
                    <h4 class="mb-4"><br>우리 사이트에서는<br>서울의 사회안전지수에 대해 알려드립니다.</h4>
                    <p class="container_ment mb-4">
                        자치구별 치안 시설물과 범죄 • 검거 비율을<br>한눈에 볼 수 있게 시각적으로 제공합니다.
                    </p><a href="./seoul_main.jsp">
                        <img class="img-fluid mb-4" src="./img/siteimg.png" alt=""></a>
                </div>
                <div class="intro2 col-md-6 px-5">
                    <h4 class="mb-4"><br>고객의견<br></h4>
                    <p class="container_ment mb-4">사용 고객에게 필요한 정보를 제공합니다.<br>시민들께서는 생활안전 정보를 활용해서 안전한 하루를 보내세요!</p>
                    <div class="row">
                        <div class="table-responsive">

                            <div id="j_board_list">

                                <table id="board-table" class=" table table-bordered table-condensed table-hover">
                                    <thead>
                                        <tr class="success">
                                            <th class="num"><a href="list.do">No</a></th>
                                            <th class="title"><a href="list.do">제목</a></th>
                                            <th class="writer"><a href="list.do">작성자</a></th>
                                            <th class="date"><a href="list.do">작성일</a></th>
                                        </tr>
                                    </thead>
                                    <tbody></tbody>
                                </table>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- One -->

        <div id="one" class="container-fluid">
            <div id="one_ment">
                도심 속 생태공원.<br>
                안심하고 산책할 수 있는 걷기좋은 공원들을 소개해드려요!
            </div>


            <div id="site" class="row">
                <div id="recommend_site1" class="col-lg-12  col-xl-12 col-xxl-7 row">

                    <div class="recommend col-lg-2">
                        <div id="recommend_img1"></div>
                        <div class="menu">
                            <div>
                                <p>시를 읽으며 산책할 수 있는 시비 광장</p>
                                <p>맨발로 걷기 좋은 공원.</p>
                            </div>
                            <button type="button" class="btn"><a href="./gu_page.jsp">[ 강남구 ] 청담근린공원
                                </a></button>
                        </div>
                    </div>
                    <!--    
                    <button type="button" class="btn"><a href="./gu_page.jsp?region=강남구">[ 강남구 ] 청담근린공원
                                </a></button>
                                
                                
                    <button type="submit" class="btn"><a href="./gu_page.jsp">[ 강남구 ] 청담근린공원</a></button>
                            <form name="mainClick" action="gu_page.jsp" method="get">
                             <input type="hidden" name="region" value="강남구">
                            <button type="submit" class="btn">[ 강남구 ] 청담근린공원</button>
                        	</form>

-->

                    <div class="recommend col-lg-2">
                        <div id="recommend_img2"></div>
                        <div class="menu">
                            <div>
                                <p>시원스러운 음악분수와 맞은편에<br>개울이 조성<br>과거 OB 맥주 영등포공장 부지.</p>
                            </div>
                            <button type="button" class="btn"><a href="./gu_page.jsp">[ 영등포구 ] 영등포공원
                                </a></button>
                        </div>
                    </div>
                    <!--    
                    <button type="button" class="btn"><a href="./gu_page.jsp?region=영등포구">[ 영등포구 ] 영등포공원
                                </a></button>
                                
                    
                        <button type="button" class="btn"><a href="./gu_page.jsp">[ 영등포구 ]
                                영등포공원
                            </a></button>
                        <button type="button" class="btn"><a href="./gu_page.jsp">[
                                관악구 ] 관악산 호수공원</a></button>
                            
-->

                    <div class="recommend col-lg-2">
                        <div id="recommend_img3"></div>
                        <div class="menu">
                            <div>
                                <p>산내음과 졸졸졸 흐르는 물소리와 함께<br>마음과 정신이 힐링이 되는 곳
                                    <br>서울에 있는 청정계곡.
                                </p>
                            </div>
                            <button type="button" class="btn"><a href="./gu_page.jsp">[ 관악구 ] 관악산 호수공원
                                </a></button>
                        </div>
                    </div>
                </div>

                <!-- <button type="button" class="btn"><a href="./gu_page.jsp?region=관악구">[ 관악구 ] 관악산 호수공원
                                </a></button> -->

                <div id="recomend_site2" class="col-lg-12 col-xl-12 col-xxl-5 row">
                    <div class="recommend col-lg-4">
                        <div id="recommend_img4"></div>
                        <div class="menu">
                            <div>
                                <p>전통정자와 산책로·주민 휴식 체육시설</p>
                                <p>연희동 둘레길.</p>
                            </div>
                            <button type="button" class="btn"><a href="./gu_page.jsp">[ 서대문구 ] 궁동근린공원
                                </a></button>
                        </div>
                    </div>
                    <!-- 
						 <button type="button" class="btn"><a href="./gu_page.jsp?region=서대문구">[ 서대문구 ] 궁동근린공원
                                </a></button>
						 -->
                    <div class="recommend col-lg-4">
                        <div id="recommend_img5"></div>
                        <div class="menu">
                            <div>
                                <p>서울시 최초로 조성되는 시립수목원</p>
                                <p>도심 속 생태공원.</p>
                            </div>
                            <button type="button" class="btn"><a href="./gu_page.jsp">[ 구로구 ] 서울푸른수목원
                                </a></button>
                        </div>
                    </div>
                </div>
            </div>
            <!--
						<button type="button" class="btn"><a href="./gu_page.jsp">[ 구로구 ] 서울푸른수목원
                                </a></button>
						 -->

        </div>

        <div id="safety" class="container-fluid">
            <div class="safe_ment">
                우리는 작은 습관으로<br>
                큰 문제를 해결할 수 있습니다.
            </div>
            <div id="safetyMenu">
                <div id="safetyMenu1">
                    <div>
                        <div class="icon"><img src="./img/icon1.PNG" alt=""></div>
                        <div class="icon_ment1">문단속, 창문단속</div>
                        <div class="icon_ment2">침입으로 인한 강도범죄<br>대부분은 깜빡하고 나온<br>문을 통해 이루어집니다.<br> 단순하지만 문을
                            잠두었는지<br>확인하는것만으로도<br>피해를 예방할 수 있습니다.
                        </div>
                    </div>
                    <div>
                        <div class="icon"><img src="./img/icon2.PNG" alt=""></div>
                        <div class="icon_ment1">어두운 길피하기</div>
                        <div class="icon_ment2">어두운 밤,<br>시야가 확보되지않은 상황에<br>어두운 길을 걷는것은 위험합니다.<br>가로등이나 밝은 상가가<br>많은 거리를
                            걷는것이<br>안전에 도움이 됩니다. </div>
                    </div>
                </div>
                <div id="safetyMenu2">
                    <div>
                        <div class="icon"><img src="./img/icon3.PNG" alt=""></div>
                        <div class="icon_ment1">걸어다닐때<br> 핸드폰하지않기</div>
                        <div class="icon_ment2"> 생활의 편리를 제공해주는<br>스마트 폰이지만<br>안전사고로<br>위험해 질 수 있는 스마트폰!<br>보행중에는 스마트폰을
                            사용을<br>지양시길
                            당부드립니다.
                        </div>
                    </div>
                    <div>
                        <div class="icon"><img src="./img/icon4.PNG" alt=""></div>
                        <div class="icon_ment1">우리집 주변의<br>치안시설<br> 인지하고 있기</div>
                        <div class="icon_ment2">주변의 경찰서, cctv 위치,<br>안심벨 위치 등을 알고있음으로<br>안전에 유의할 수 있습니다.
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- footer -->
        <jsp:include page="./include/footer.jsp" />

        <!-- script -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
            integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
            crossorigin="anonymous"></script>
        <script src="./js/list.js"></script>
    </body>

    </html>