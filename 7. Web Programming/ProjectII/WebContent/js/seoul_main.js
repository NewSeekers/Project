
let level = "";
var polygons = [];
var areas = [];
var customOverlay2;
var paths = [];
var guGrade = [];
var guName;
var currentValue = 2022;
var btnValue;
var centerpoint = [];
var guCenterPoint;
var guNames = [];
var polygons2 = [];
var crimeYearArr = [];
let homicideYear = [];
let robberYear = [];
let sexualYear = [];
let theftYear = [];
let violenceYear = [];

async function fetchData() {
    try {
        const response = await fetch("./callCrime.do");
        const jsonArray = await response.text();
        const jsonData = JSON.parse(jsonArray);

        jsonData.forEach(json => {
            homicideYear.push(json.homicide);
            robberYear.push(json.robber);
            sexualYear.push(json.sexual);
            theftYear.push(json.theft);
            violenceYear.push(json.violence);
        });

        return homicideYear, robberYear, sexualYear, theftYear, violenceYear;
    } catch (error) {
        console.error("데이터를 불러오는 중 에러 발생: ", error);
    }
}
// fetchData 함수를 호출합니다.
fetchData();










(async () => {
    await fetchData();
    console.log("yearCrime" + yearCrime(2022))
    console.log("crimeYear" + crimeyear(2022))
    chart1draw(currentValue);
    chart2draw(currentValue);
    chart3draw(currentValue);
    chart4draw(currentValue);
})();

var gradeColors = [
    ["#f9ddb1", "#f5c77e", "#f1b04c", "#ee9f27", "#ec9006"], //2004년//
    ["#f7f5bc", "#fff172", "#ffe337", "#ffd000", "#ffb000"], //2007년//
    ["#e7ef9e", "#dde878", "#cfde3d", "#c2cc37", "#a09f28"], //2010년//
    ["#eeef20", "#d4d700", "#aacc00", "#80b918", "#55a630"], //2013년//
    ["#c0dfd3", "#9ccbb8", "#7cb69d", "#60a18b", "#578b74"], //2016년//
    ["#d8eaef", "#c0d5dd", "#acc8d7", "#88a9b7", "#537d90"], //2019년//
    ["#aaf2ef", "#75e9e5", "#00d5d6", "#00bdd0", "#00a0b7"],
]; //2022년//

var container = document.getElementById("s_map");
var options = {
    center: new kakao.maps.LatLng(37.5642135, 127.0016985),
    level: 9,
};
var map = new kakao.maps.Map(container, options),
    customOverlay = new kakao.maps.CustomOverlay({}),
    customOverlay2 = new kakao.maps.CustomOverlay({}),
    infowindow = new kakao.maps.InfoWindow({ removable: true });
map.setDraggable(false);
map.setZoomable(false);

var locate = JSON.parse(JSON.stringify(mapData));
var units = locate.features; // json key값이 "features"인 것의 value를 통으로 가져온다.

units.forEach((element) => {
    var geo = element.geometry;
    var coord = geo.coordinates[0];
    guName = element.properties.SGG_NM;
    guGrade = element.properties.GRADE;

    var path = [];
    var points = [];
    coord[0].forEach((point) => {
        centerPoint = new Object();
        centerPoint.x = point[1];
        centerPoint.y = point[0];
        points.push(centerPoint); // 1개 구의 좌표들

        path.push(new kakao.maps.LatLng(point[1], point[0]));

        function centroid(points) {
            var i, j, len, p1, p2, f, are, x, y;

            are = x = y = 0;

            for (i = 0, len = points.length, j = len - 1; i < len; j = i++) {
                p1 = points[i];
                p2 = points[j];

                f = p1.y * p2.x - p2.y * p1.x;
                x += (p1.x + p2.x) * f;
                y += (p1.y + p2.y) * f;
                are += f * 3;
            }
            return new kakao.maps.LatLng(x / are, y / are);
        }

        guCenterPoint = centroid(points);
    });
    var area = { guName, path, guGrade, guCenterPoint };
    paths.push(path);
    areas.push(area);
});

backpoly();
function backpoly() {
    backgroundPath = [
        new kakao.maps.LatLng(39, 125.4),
        new kakao.maps.LatLng(39, 128.5),
        new kakao.maps.LatLng(36, 128.5),
        new kakao.maps.LatLng(36, 125.4),
    ];

    var backgroundPoly = new kakao.maps.Polygon({
        map: map,
        path: backgroundPath,
        strokeWeight: 2,
        strokeColor: "#fff5ee",
        strokeOpacity: 1,
        fillColor: "#fff5ee",
        fillOpacity: 1,
    });
    backgroundPoly.setMap(map);
}

areas.forEach((point) => {
    var content = "<div id=guNameBox>" + point.guName + "</div>";
    var position = point.guCenterPoint;
    var temp = new kakao.maps.CustomOverlay({
        content: content,
        position: position,
    });
    guNames.push(temp);
});

displayAllArea();
guNamesSet();
function displayAllArea() {
    deletePolygon(polygons);
    deletePolygon(polygons2);
    for (var i = 0, len = areas.length; i < len; i++) {
        displayArea(areas[i]);
    }

    guNamesReset();
    guNamesSet();
}

function gradeSelec(buttonID) {
    var button = document.getElementById(buttonID);
    btnValue = button.value;
    deletePolygon(polygons);
    deletePolygon(polygons2);
    guNamesReset();

    for (var i = 0, len = areas.length; i < len; i++) {
        displayStroke(areas[i]);

        if (currentValue == 2004) {
            var j = 0;
        } else if (currentValue == 2007) {
            j = 1;
        } else if (currentValue == 2010) {
            j = 2;
        } else if (currentValue == 2013) {
            j = 3;
        } else if (currentValue == 2016) {
            j = 4;
        } else if (currentValue == 2019) {
            j = 5;
        } else if (currentValue == 2022) {
            j = 6;
        }

        if (areas[i].guGrade[j] == btnValue) {
            displayArea(areas[i]);

            guNames[i].setMap(map);
        }
    }
}

function guNamesReset() {
    for (var i = 0; (len = guNames.length), i < len; i++) {
        guNames[i].setMap(null);
    }
}

function guNamesSet() {
    for (var i = 0; (len = guNames.length), i < len; i++) {
        guNames[i].setMap(map);
    }
}

function displayStroke(area) {
    var polygon2 = new kakao.maps.Polygon({
        map: map,
        path: area.path,
        strokeWeight: 1,
        strokeColor: "#004c80",
        strokeOpacity: 1,
        fillColor: "",
        fillOpacity: 0,
    });
    polygons2.push(polygon2);
}

function displayArea(area) {
    var polygon = new kakao.maps.Polygon({
        map: map,
        path: area.path,
        strokeWeight: 1,
        strokeColor: "#004c80",
        strokeOpacity: 0.8,
        fillColor:
            gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
            area.guGrade[(parseInt(currentValue) - 2001) / 3 - 1] - 1
            ],
        fillOpacity: 0.7,
    });

    polygons.push(polygon);

    kakao.maps.event.addListener(polygon, "mouseover", function (mouseEvent) {
        polygon.setOptions({ fillColor: "#00CED1" });
        customOverlay.setContent(
            '<div class="area">' +
            "<h5>" +
            area.guName +
            "</h5><br>" +
            "<h6>안전등급 : " +
            area.guGrade[(parseInt(currentValue) - 2001) / 3 - 1] +
            "</h6>" +
            "</div>"
        );
        customOverlay.setPosition(mouseEvent.latLng);
        customOverlay.setMap(map);
    });

    kakao.maps.event.addListener(polygon, "mousemove", function (mouseEvent) {
        customOverlay.setPosition(mouseEvent.latLng);
    });

    kakao.maps.event.addListener(polygon, "mouseout", function () {
        polygon.setOptions({
            fillColor:
                gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
                area.guGrade[(parseInt(currentValue) - 2001) / 3 - 1] - 1
                ],
        });
        customOverlay.setMap(null);
    });

    kakao.maps.event.addListener(polygon, "click", function (mouseEvent) {
        var center;
        var level = map.getLevel();

        // if (level > 8) {
        //     center = mouseEvent.latLng;
        //     map.setLevel(level - 1);
        //     map.panTo(center);
        // }
        // else {
        //     center = mouseEvent.latLng;
        //     map.setLevel(8);
        //     map.panTo(center);
        // }

        // var content = '<div class="info">' +
        //     '   <div class="title">' + area.name + '</div>' +
        //     '   <div class="size">총 면적 : 약 ' + Math.floor(polygon.getArea()) + ' m<sup>2</sup></div>' +
        //     '<br>그래프삽입' + '</div>';

        // infowindow.setContent(content);
        // infowindow.setPosition(mouseEvent.latLng);
        // infowindow.setMap(map);
    });
}

function deletePolygon(polygons) {
    for (var i = 0; i < polygons.length; i++) {
        polygons[i].setMap(null);
    }
    polygons = [];
}

var crimeType = ["homicide", "robber", "sexcrime", "theft", "violence"]; // 범죄 유형
var Year = ["2004", "2007", "2010", "2013", "2016", "2019", "2022"]; // 범죄 발생연도

var crimeCount = ["4102", "3742", "3589", "3945", "3702", "3425", "2813"]; // 10만명당 범죄발생율



var colorset = [
    ["#00456c", "#00588a", "#006ca7", "#0080c6", "#0092e0", "#00adf3", "#33baff"],
    ["#E8FC9D", "#D5F66C", "#C1ED47", "#A3E20F", "#85C20A", "#6AA207", "#518304"],
    ["#AFFEF4", "#86FEF8", "#68F7FD", "#37E5FC", "#28B6D8", "#1B8AB5", "#116492"],
    ["#FFEBB5", "#FFDD90", "#FFCF75", "#FFB847", "#DB9433", "#B77223", "#935416"],
    ["#FFC6B1", "#FFA08A", "#FF7C6D", "#FF403D", "#DB2C38", "#B71E36", "#931332"],
    ["#FFADF9", "#FE84FF", "#F266FF", "#DE33FF", "#AF25DB", "#8519B7", "#601093"],
    ["#FFEC67", "#FFE541", "#FFD902", "#DBB701", "#B79601", "#937600", "#7A6000"],
];






function yearCrime(currentValue) {

    return (crime = [
        homicideYear[(parseInt(currentValue) - 2001) / 3 - 1],
        robberYear[(parseInt(currentValue) - 2001) / 3 - 1],
        sexualYear[(parseInt(currentValue) - 2001) / 3 - 1],
        theftYear[(parseInt(currentValue) - 2001) / 3 - 1],
        violenceYear[(parseInt(currentValue) - 2001) / 3 - 1],
    ]);
}



function crimeyear(currentValue) {
    return (crimeYearArr = [
        homicideYear[(parseInt(currentValue) - 2001) / 3 - 1],
        robberYear[(parseInt(currentValue) - 2001) / 3 - 1],
        sexualYear[(parseInt(currentValue) - 2001) / 3 - 1],
        theftYear[(parseInt(currentValue) - 2001) / 3 - 1],
        violenceYear[(parseInt(currentValue) - 2001) / 3 - 1],
    ]);
}

const removeData = (chart) => {
    chart.data.labels = [];
    chart.data.datasets.forEach((dataset) => {
        dataset.data = [];
        dataset.backgroundColor = [];
    });
    chart.update();
};

const addData = (chart, currentValue) => {
    removeData(chart);
    chart.data.labels = crimeType;
    chart.data.datasets.label = "crime";
    chart.data.datasets.forEach((dataset) => {
        dataset.data = crimeyear(currentValue);
        dataset.backgroundColor = colorset[(parseInt(currentValue) - 2001) / 3 - 1];
    });

    chart.update();
};

// 슬라이더 값에 따른 맵 단계구분도 구현
var slider = document.getElementById("slider");

var cctvCount = ["126", "267", "547", "709", "1012", "1400", "2400"];
var lightCount = ["123", "245", "324", "370", "404", "548", "809"];
var policeCount = ["103", "130", "150", "168", "210", "254", "305"];

// = slider.value;
slider.addEventListener("input", function () {
    currentValue = slider.value;
    document.querySelector("#s_title").innerHTML = currentValue + "년";
    document.getElementById("chartTitle1").innerHTML =
        currentValue + "년 범죄비율";
    document.getElementById("chartTitle2").innerHTML =
        currentValue + "년 범죄 발생수";

    document.getElementById("btn1").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
        document.getElementById("btn1").value - 1
        ];
    document.getElementById("btn2").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
        document.getElementById("btn2").value - 1
        ];
    document.getElementById("btn3").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
        document.getElementById("btn3").value - 1
        ];
    document.getElementById("btn4").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
        document.getElementById("btn4").value - 1
        ];
    document.getElementById("btn5").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
        document.getElementById("btn5").value - 1
        ];

    document.getElementById("cctvNum").innerText =
        cctvCount[(parseInt(currentValue) - 2001) / 3 - 1];
    document.getElementById("lightNum").innerText =
        lightCount[(parseInt(currentValue) - 2001) / 3 - 1];
    document.getElementById("policeNum").innerText =
        policeCount[(parseInt(currentValue) - 2001) / 3 - 1];

    document.getElementById("cctvNumHead").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][0];
    document.getElementById("lightNumHead").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][0];
    document.getElementById("policeNumHead").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][0];

    displayAllArea();

    removeData(myChart1);
    addData(myChart1, currentValue);

    removeData(myChart2);
    addData(myChart2, currentValue);

    removeData(myChart4);
    addData(myChart4, currentValue);
});

// 셀렉트박스 값에 따른 맵 단계구분도 구현
var selectbox = document.querySelector("#selector");
selectbox.addEventListener("input", function () {
    currentValue = $("#selector option:selected").val();
    document.querySelector("#s_title").innerHTML = currentValue + "년";
    document.getElementById("chartTitle1").innerHTML =
        currentValue + "년 범죄비율";
    document.getElementById("chartTitle2").innerHTML =
        currentValue + "년 범죄 발생수";

    document.getElementById("btn1").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
        document.getElementById("btn1").value - 1
        ];
    document.getElementById("btn2").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
        document.getElementById("btn2").value - 1
        ];
    document.getElementById("btn3").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
        document.getElementById("btn3").value - 1
        ];
    document.getElementById("btn4").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
        document.getElementById("btn4").value - 1
        ];
    document.getElementById("btn5").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][
        document.getElementById("btn5").value - 1
        ];
    displayAllArea();

    removeData(myChart1);
    addData(myChart1, currentValue);

    removeData(myChart2);
    addData(myChart2, currentValue);

    removeData(myChart4);
    addData(myChart4, currentValue);

    document.getElementById("cctvNum").innerText =
        cctvCount[(parseInt(currentValue) - 2001) / 3 - 1];
    document.getElementById("lightNum").innerText =
        lightCount[(parseInt(currentValue) - 2001) / 3 - 1];
    document.getElementById("policeNum").innerText =
        policeCount[(parseInt(currentValue) - 2001) / 3 - 1];

    document.getElementById("cctvNumHead").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][0];
    document.getElementById("lightNumHead").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][0];
    document.getElementById("policeNumHead").style.backgroundColor =
        gradeColors[(parseInt(currentValue) - 2001) / 3 - 1][0];
});

//창 폭에 따른 카카오맵 레벨 및 중심좌표 수정
// let delay = 300;
// let timer = null;
window.addEventListener("resize", function () {
    // clearTimeout(timer);
    // timer = setTimeout(function () {
    map.setCenter(new kakao.maps.LatLng(37.5642135, 127.0016985));
    if (1200 < window.innerWidth) {
        map.setLevel(9);
        const temp2 = document.querySelector("#btn1");
        temp2.innerHTML = "<h6>1등급</h6>";
        const temp3 = document.querySelector("#btn2");
        temp3.innerHTML = "<h6>2등급</h6>";
        const temp4 = document.querySelector("#btn3");
        temp4.innerHTML = "<h6>3등급</h6>";
        const temp5 = document.querySelector("#btn4");
        temp5.innerHTML = "<h6>4등급</h6>";
        const temp6 = document.querySelector("#btn5");
        temp6.innerHTML = "<h6>5등급</h6>";
        const temp7 = document.querySelector("#btn6");
        temp7.innerHTML = "<h6>전체등급</h6>";
        document.getElementById("s_map").style.height = "40vw";
    } else if (768 < window.innerWidth && window.innerWidth <= 1200) {
        map.setLevel(9);
        const temp2 = document.querySelector("#btn1");
        temp2.innerHTML = "<h6>1등급</h6>";
        const temp3 = document.querySelector("#btn2");
        temp3.innerHTML = "<h6>2등급</h6>";
        const temp4 = document.querySelector("#btn3");
        temp4.innerHTML = "<h6>3등급</h6>";
        const temp5 = document.querySelector("#btn4");
        temp5.innerHTML = "<h6>4등급</h6>";
        const temp6 = document.querySelector("#btn5");
        temp6.innerHTML = "<h6>5등급</h6>";
        const temp7 = document.querySelector("#btn6");
        temp7.innerHTML = "<h6>전체등급</h6>";
        document.getElementById("s_map").style.height = "60vw";
        document.getElementById("s_grade").style.marginTop = "0px";
    } else if (window.innerWidth <= 768) {
        map.setLevel(10);
        const temp2 = document.querySelector("#btn1");
        temp2.innerHTML = "<h4>1</h4>";
        const temp3 = document.querySelector("#btn2");
        temp3.innerHTML = "<h4>2</h4>";
        const temp4 = document.querySelector("#btn3");
        temp4.innerHTML = "<h4>3</h4>";
        const temp5 = document.querySelector("#btn4");
        temp5.innerHTML = "<h4>4</h4>";
        const temp6 = document.querySelector("#btn5");
        temp6.innerHTML = "<h4>5</h4>";
        const temp7 = document.querySelector("#btn6");
        temp7.innerHTML = "<h4>전체</h4>";
        document.getElementById("s_map").style.height = "75vw";
        document.getElementById("s_grade").style.marginTop = "0px";
    }
    // }, delay);
});

crimeType = ["homicide", "robber", "sexcrime", "theft", "violence"]; // 범죄 유형
year = ["2004", "2007", "2010", "2013", "2016", "2019", "2022"]; // 범죄 발생연도

crimeCount = ["4102", "3742", "3589", "3945", "3702", "3425", "2813"]; // 10만명당 범죄발생율

year2 = ["2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022"];
catchYear = ["79", "77", "87", "86", "83", "86", "87", "88", "87", "89", "75", "65", "60", "60", "61", "66", "72", "75", "74", "73", "72", "73", "73", "20"];





//첫번째 그래프

// drawchart(2022);

function chart1draw(currentValue) {
    var ctx = document.getElementById("cRateChart").getContext("2d");
    myChart1 = new Chart(ctx, {
        type: "doughnut",
        data: {
            labels: crimeType,
            datasets: [
                {
                    label: "Crime Rate",
                    data: yearCrime(currentValue),
                    backgroundColor: colorset[(parseInt(currentValue) - 2001) / 3 - 1],
                },
            ],
        },
        options: {
            // responsive: false,
            maintainAspectRatio: false,
        },
    });
}

// setTimeout(drawchart(2022), 5000);


function chart2draw(currentValue) {
    var ctx = document.getElementById("cNumChart").getContext("2d");
    myChart2 = new Chart(ctx, {
        type: "bar",
        data: {
            labels: crimeType,
            datasets: [
                {
                    label: "Number of Crime",
                    data: yearCrime(currentValue),
                    backgroundColor: colorset[(parseInt(currentValue) - 2001) / 3 - 1],
                },
            ],
        },
        options: {
            // responsive: false,
            maintainAspectRatio: false,
        },
    });
}


//세번째 그래프
function chart3draw(currentValue) {
    var ctx = document.getElementById("cYearChart").getContext("2d");
    myChart3 = new Chart(ctx, {
        type: "line",
        data: {
            labels: year,
            datasets: [
                {
                    label: "Homicide",
                    data: homicideYear,
                    borderColor: "darkorange",
                    borderWidth: 3,
                },
                {
                    label: "Robber",
                    data: robberYear,
                    borderColor: "blue",
                    borderWidth: 3,
                },
                {
                    label: "SexCrime",
                    data: sexualYear,
                    borderColor: "green",
                    borderWidth: 3,
                },
                {
                    label: "Theft",
                    data: theftYear,
                    borderColor: "navy",
                    borderWidth: 3,
                },
                {
                    label: "Violence",
                    data: violenceYear,
                    borderColor: "skyblue",
                    borderWidth: 3,
                },
            ],
        },
        options: {
            // responsive: false,
            maintainAspectRatio: false,
        },
    });
}


function chart4draw(currentValue) {
    var ctx = document.getElementById("catchYearChart").getContext("2d");
    myChart4 = new Chart(ctx, {
        type: "line",
        data: {
            labels: year2,
            datasets: [
                {
                    label: "CatchRate",
                    data: catchYear,
                    borderColor: "rgba(16,163,127,1)",
                    backgroundColor: "rgba(16,163,127,0.2)",
                    borderWidth: 1,
                    fill: true,
                    tension: 0.4,
                },
            ],
        },
        options: {
            // responsive: false,
            maintainAspectRatio: false,
        },
    });
}

















let observer = new IntersectionObserver((e) => {
    e.forEach((content) => {
        if (content.isIntersecting) {
            content.target.style.opacity = 1;
        }
    });
});
let img = document.querySelectorAll("#secuRank");
observer.observe(img[0]); // html요소 감시

const modal1 = document.getElementById("modalContainer1");
const icon1 = document.getElementById("question1");
const modal2 = document.getElementById("modalContainer2");
const icon2 = document.getElementById("question2");

icon1.addEventListener("mouseover", () => {
    modal1.classList.remove("hidden");
});

icon1.addEventListener("mouseout", () => {
    modal1.classList.add("hidden");
});
icon2.addEventListener("mouseover", () => {
    modal2.classList.remove("hidden");
});

icon2.addEventListener("mouseout", () => {
    modal2.classList.add("hidden");
});

const sidebar = document.getElementById("sidebar");
const homeIcon = document.getElementById("home");
const chartIcon = document.getElementById("chartIcon");
const rankIcon = document.getElementById("rankIcon");
const upIcon = document.getElementById("upsideIcon");

sidebar.addEventListener("mouseleave", function () {
    // this.style.width = '60px';
    // 아이콘 원래대로 변경
    homeIcon.className = "fa fa-home";
    chartIcon.className = "fa-solid fa-chart-simple";
    rankIcon.className = "fa-solid fa-ranking-star";
    upIcon.className = "fa-solid fa-chevron-up";
});

// 슬라이드바가 펼쳐질 때 아이콘 변경
sidebar.addEventListener("mouseenter", function () {
    homeIcon.className = "fa-solid fa-house-user";
    chartIcon.className = "fa-solid fa-chart-pie";
    rankIcon.className = "fa-solid fa-award";
    upIcon.className = "fa-solid fa-arrow-up";
});

// }

(function () {
    var tooltipTriggerList = [].slice.call(
        document.querySelectorAll('[data-bs-toggle="tooltip"]')
    );
    tooltipTriggerList.forEach(function (tooltipTriggerEl) {
        new bootstrap.Tooltip(tooltipTriggerEl);
    });
})();

document.getElementById("yearOne").addEventListener("click", function () {
    document.getElementById("sFirst").innerText = "종로구";
    document.getElementById("sSec").innerText = "용산구";
    document.getElementById("sThird").innerText = "서초구";
    document.getElementById("sFourth").innerText = "송파구";
    document.getElementById("sFifth").innerText = "수서구";

    document.getElementById("tFirst").innerText = "양천구";
    document.getElementById("tSec").innerText = "종로구";
    document.getElementById("tThird").innerText = "금천구";
    document.getElementById("tFourth").innerText = "강동구";
    document.getElementById("tFifth").innerText = "마포구";
});

document.getElementById("yearTwo").addEventListener("click", function () {
    document.getElementById("sFirst").innerText = "마포구";
    document.getElementById("sSec").innerText = "성동구";
    document.getElementById("sThird").innerText = "강남구";
    document.getElementById("sFourth").innerText = "양천구";
    document.getElementById("sFifth").innerText = "강동구";

    document.getElementById("tFirst").innerText = "서초구";
    document.getElementById("tSec").innerText = "노원구";
    document.getElementById("tThird").innerText = "금천구";
    document.getElementById("tFourth").innerText = "마포구";
    document.getElementById("tFifth").innerText = "종로구";
});

document.getElementById("yearThree").addEventListener("click", function () {
    document.getElementById("sFirst").innerText = "노원구";
    document.getElementById("sSec").innerText = "서초구";
    document.getElementById("sThird").innerText = "강남구";
    document.getElementById("sFourth").innerText = "동작구";
    document.getElementById("sFifth").innerText = "송파구";

    document.getElementById("tFirst").innerText = "도봉구";
    document.getElementById("tSec").innerText = "서초구";
    document.getElementById("tThird").innerText = "금천구";
    document.getElementById("tFourth").innerText = "종로구";
    document.getElementById("tFifth").innerText = "용산구";
});
