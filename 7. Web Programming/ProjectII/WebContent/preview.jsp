<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Preview</title>
    <!--style css-->
    <link rel="stylesheet" href="./css/preview.css">
    <link rel="stylesheet" href="./css/header.footer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!--bootstrap-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="http://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">

</head>

<body>
   <!--nav-->
	<jsp:include page="./include/nav.jsp"/>

    <!--space-->
    <div class="space" style="height: 100px;"></div>

    <!--contents start-->
    <div class="container-fluid">
        <div class="totalbox"> <!--width 80% of container-fluid -->
            <div class="titlebox"><span id="title">
                    <h3>안전 미리보기</h3>
                </span>
            </div>


            <div class="sectionbox "><span id="exp1">

                    <div>서울시의 5대 범죄와 관련 요인들의 다중선형 회귀분석을 통해 예측 및 보완 정보를 보여드립니다.</div>
            </div>


            <div id="modalContainer">
                <span id="exp1_1">다중선형 회귀분석이란</span>
                <img src="./img/ques.png" id="exp_img">
                <div id="modalContent">통계학에서 설명 변수(독립변수,independent variables)가<br>둘 이상인 회귀 분석을 가리킵니다.</div>
            </div>

            <div class="space" style="height: 100px;"></div>



            <div class="row" id="guinfo">
                <div class="box-1">
                    <div class="btn-group dropend">
                        <button type="button" class="btn btn-success btn-sm dropdown-toggle" data-bs-title="자치구"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            자치구 선택
                        </button>

                        <ul class="dropdown-menu">
                            <li>
                                <h6 class="dropdown-header">자치구를 선택해주세요</h6>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><button class="dropdown-item" type="button" value="gangnam">강남구</button></li>
                            <li><button class="dropdown-item" type="button" value="gwanak">관악구</button></li>
                            <li><button class="dropdown-item" type="button" value="guro">구로구</button></li>
                            <li><button class="dropdown-item" type="button" value="youngdeungpo">영등포구</button></li>
                            <li><button class="dropdown-item" type="button" value="seodaemun">서대문구</button></li>
                        </ul>
                    </div>
                    <div class="box-1-gu">
                        <div class="col-sm-6 col-md-6 guName" id="gutext">자치구를 먼저 선택해주세요</div>
                        <ul class="col-sm-6 col-md-6 rank-people">
                            <li><span id="rank-text"> &nbsp;&nbsp;안전등급:</span><span id="grade">1</span></li><br>
                            <li><span id="people"> &nbsp;&nbsp;인구수:</span><span id="population">1234</span></li>
                        </ul>
                    </div>
                </div>

                <div class="box-2">
                    <div class="col-sm-12" id="box2-text">
                        <span class="guName">강남구</span><span>의 범죄발생에 밀접한 요인들</span>
                        <button id="showHideButton" class="btn btn-success btn-sm">확인하기</button>
                    </div>
                    <div class="col-sm-12">
                        <div class="row" id="crBox-1">
                            <div class="crBox" data-condition="0" id="crimeReason1" style="display:none">
                                <div class="compo"> 1</div>
                                <input type="button" class="btn btn-sm btn-info btnplus" value="+" style="width: 30px;">
                                <input type="button" class="btn btn-sm btn-info btnminus" value="-"
                                    style="width: 30px;">
                            </div>
                            <div class="crBox" data-condition="0" id="crimeReason2" style="display:none">
                                <div class="compo"> 2</div>
                                <input type="button" class="btn btn-sm btn-info btnplus" value="+" style="width: 30px;">
                                <input type="button" class="btn btn-sm btn-info btnminus" value="-"
                                    style="width: 30px;">
                            </div>
                            <div class="crBox" data-condition="0" id="crimeReason3" style="display:none">
                                <div class="compo"> 3</div>
                                <input type="button" class="btn btn-sm btn-info btnplus" value="+" style="width: 30px;">
                                <input type="button" class="btn btn-sm btn-info btnminus" value="-"
                                    style="width: 30px;">
                            </div>
                            <div class="crBox" data-condition="0" id="crimeReason4" style="display:none">
                                <div class="compo"> 4</div>
                                <input type="button" class="btn btn-sm btn-info btnplus" value="+" style="width: 30px;">
                                <input type="button" class="btn btn-sm btn-info btnminus" value="-"
                                    style="width: 30px;">
                            </div>
                            <div class="crBox" data-condition="0" id="crimeReason5" style="display:none">
                                <div class="compo"> 5</div>
                                <input type="button" class="btn btn-sm btn-info btnplus" value="+" style="width: 30px;">
                                <input type="button" class="btn btn-sm btn-info btnminus" value="-"
                                    style="width: 30px;">
                            </div>
                        </div>
                    </div>
                </div>
            </div>



            <!--space-->
            <!-- <div class="space"></div> -->

            <div id="chartTitle">요인들 수에 따른 범죄율 변화</div>
            <div id="chartBox">
                <canvas id="chart"></canvas>
            </div>



            <div class="container result">
                <div class="row" id="abc">
                    <div class="rank col-md-12 col-lg-5 p-3">
                        <div id="rankbox" class=" bg-info bg-opacity-10 border border-info  border-3 rounded-pill">
                            <span class="guName">강남구</span><span>예상 치안 등급:</span><span>2</span>
                        </div>
                    </div>
                    <div class="resultTitle col-md-12 col-lg-5">결과해석</div>
                </div>

                <br>
                <!-- <div>마지막값으로부터</div> -->
                <div>

                    <div class="resultbox row">
                        <div class="col-md-12 col-lg-5 resultMentbox" style="--bs-border-opacity: 20%;">
                            <div class="resultMent">
                                <span class="cr">살인은 </span>
                                <span id="result">n%</span>
                                <span id="fluc">감소</span><span>할 것으로 예상됩니다.</span><br><br>
                            </div>
                        </div>
                        <div class="col-md-12 col-lg-5 resultMentbox" style="--bs-border-opacity: 20%;">
                            <div class="resultMent">
                                <span class="cr">강도는 </span>
                                <span id="result">n%</span>
                                <span id="fluc">감소</span><span>할 것으로 예상됩니다.</span><br><br>
                            </div>
                        </div>
                    </div>
                    <div class="resultbox row">
                        <div class="col-md-12 col-lg-5 resultMentbox " style="--bs-border-opacity: 20%;">
                            <div class="resultMent">
                                <span class="cr">성범죄는 </span>
                                <span id="result">n%</span>
                                <span id="fluc">감소</span><span>할 것으로 예상됩니다.</span><br><br>
                            </div>
                        </div>
                        <div class="col-md-12 col-lg-5 resultMentbox " style="--bs-border-opacity: 20%;">
                            <div class="resultMent">
                                <span class="cr">절도는 </span>
                                <span id="result">n%</span>
                                <span id="fluc">감소</span><span>할 것으로 예상됩니다.</span><br><br>
                            </div>
                        </div>
                    </div>
                    <div class="resultbox row">
                        <div class="col-md-12 col-lg-5 resultMentbox" style="--bs-border-opacity: 20%;">
                            <div class="resultMent">
                                <span class="cr">폭력은 </span>
                                <span id="result">n%</span>
                                <span id="fluc">감소</span><span>할 것으로 예상됩니다.</span><br><br>
                            </div>
                        </div>
                        <div id="hidden" class="col-md-12 col-lg-5" style="--bs-border-opacity: 20%;">
                            <!-- <span class="cr">폭력은 </span>
                            <span id="result">n%</span>
                            <span class="fluc">감소</span><span>할 것으로 예상됩니다.</span><br><br> -->
                        </div>
                    </div>
                </div>
            </div>

            <div id="text">
                <i class="bi bi-bell"> 안전한 우리동네를 위한 추천 사이트</i>
            </div>



            <div id="re" class="swiper">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <div class="card" style="width: 18rem;">
                            <img src="./img/순찰.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">탄력순찰</h5>
                                <p class="card-text"> 온라인 '순찰신문고'홈페이지와 스마트국민제보,오프라인 지도에 순찰 희망시간과 장소를 요청. 시간'장소와 112신고량을
                                    분석한 후 우선순위'순찰주기를 결정하여 순찰계획에 반영</p>
                                <a href="#" class="btn btn-primary">더 알아보기</a>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="card" style="width: 18rem;">
                            <img src="./img/보안관.png" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">안심마을 보안관 사업</h5>
                                <p class="card-text">전직 경찰, 무술 유단자 등으로 구성된 '안심마을보안관'(2022년 총 63명 활동)이 2인1조로
                                    심야시간대(21시~다음날 2시 30분) 도보 방범순찰과 주민생활 보호활동을 펼치는 사업</p>
                                <a href="#" class="btn btn-primary">더 알아보기</a>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="card" style="width: 18rem;">
                            <img src="./img/보안등.png" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">스마트 보안등 사업</h5>
                                <p class="card-text">스마트보안등이란 IoT신호기가 내장된 LED보안등으로 스마트보안등 설치 구역에서 보행자가 앱으로 긴급신고를 하면,
                                    구조대와
                                    통합관제센터와 관할 지구대로 위급상황이 즉시 신고</p>
                                <a href="#" class="btn btn-primary">더 알아보기</a>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="card" style="width: 18rem;">
                            <img src="./img/안심이.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">안심이 앱</h5>
                                <p class="card-text">스마트폰 위치정보와 서울시 정보인프라를 활용하여 각종 범죄 위협으로부터 시민을 보호하는 사회적 안전망 모델인 안심이 앱.
                                    위기상황이 오면 스마트폰을 흔들어 보세요.
                                </p>
                                <a href="#" class="btn btn-primary">더 알아보기</a>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- If we need pagination -->
                <div class="swiper-pagination"></div>

                <!-- If we need navigation buttons -->
                <button class="carousel-control-prev" type="button">
                    <span class="carousel-control-prev-icon"></span>
                </button>
                <button class="carousel-control-next" type="button">
                    <span class="carousel-control-next-icon"></span>
                </button>








            </div><!--totalbox end-->

        </div>

    </div>

    <div class="media">


        <div class="card" style="width: 18rem;">
            <img src="./img/순찰.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">탄력순찰</h5>
                <p class="card-text"> 온라인 '순찰신문고'홈페이지와 스마트국민제보,오프라인 지도에 순찰 희망시간과 장소를 요청. 시간'장소와 112신고량을
                    분석한 후 우선순위'순찰주기를 결정하여 순찰계획에 반영</p>
                <a href="#" class="btn btn-primary">더 알아보기</a>
            </div>
        </div>


        <div class="card" style="width: 18rem;">
            <img src="./img/보안관.png" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">안심마을 보안관 사업</h5>
                <p class="card-text">전직 경찰, 무술 유단자 등으로 구성된 '안심마을보안관'(2022년 총 63명 활동)이 2인1조로
                    심야시간대(21시~다음날 2시 30분) 도보 방범순찰과 주민생활 보호활동을 펼치는 사업</p>
                <a href="#" class="btn btn-primary">더 알아보기</a>
            </div>
        </div>



        <div>

            <div class="card" style="width: 18rem;">
                <img src="./img/보안등.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">스마트 보안등 사업</h5>
                    <p class="card-text">스마트보안등이란 IoT신호기가 내장된 LED보안등으로 스마트보안등 설치 구역에서 보행자가 앱으로 긴급신고를 하면,
                        구조대와
                        통합관제센터와 관할 지구대로 위급상황이 즉시 신고</p>
                    <a href="#" class="btn btn-primary">더 알아보기</a>
                </div>
            </div>

            <div class="card" style="width: 18rem;">
                <img src="./img/안심이.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">안심이 앱</h5>
                    <p class="card-text">스마트폰 위치정보와 서울시 정보인프라를 활용하여 각종 범죄 위협으로부터 시민을 보호하는 사회적 안전망 모델인 안심이 앱.
                        위기상황이 오면 스마트폰을 흔들어 보세요.
                    </p>
                    <a href="#" class="btn btn-primary">더 알아보기</a>
                </div>
            </div>

        </div>

    </div>





    <!--sidebar-->
    <div id="sidebar">

        <a href="./Index.html" id="home" data-bs-toggle="tooltip" data-bs-placement="right" title="홈으로"
            data-bs-custom-class="custom-tooltip" class="fa fa-home"></a>
        <a href="#" id="upsideIcon" data-bs-toggle="tooltip" data-bs-placement="right" title="상단으로"
            data-bs-custom-class="custom-tooltip" class="fa-solid fa-arrow-up"></a>
        <a href="#safety_rank" id="rankIcon" data-bs-toggle="tooltip" data-bs-placement="right" title="우리동네 안전체감도 알아보기"
            data-bs-custom-class="custom-tooltip" class="fa-solid fa-chart-column"></a>
        <a href="#media" id="siteIcon" data-bs-toggle="tooltip" data-bs-placement="right" title="우리동네 공원찾기"
            data-bs-custom-class="custom-tooltip" class="fa-solid fa-link"></a>
    </div>






<!-- -------------- footer ---------------- -->
<jsp:include page="./include/footer.jsp"/>







    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-trendline"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="./js/preview.js"></script>
</body>

</html>