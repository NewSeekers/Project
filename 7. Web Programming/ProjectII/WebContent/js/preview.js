const districtData = {
    gangnam: {
        facilitySelector: {
            cctvCount: [100, 110, 120, 130, 140, 150, 160, 170],
            light: [200, 220, 240, 260, 280, 300, 320, 340],
        },
        crimeData: {
            murder: [118, 114, 116, 115, 114, 113, 112, 113],
            robbery: [40, 30, 38, 33, 30, 24, 25, 20],
            sexualAssault: [100, 80, 70, 80, 60, 40, 30, 40],
            assault: [130, 122, 110, 80, 90, 70, 60, 50],
            theft: [230, 210, 220, 200, 180, 170, 130, 160]
        },
        grade: 2,
        population: 561100
    },
    gwanak: {
        facilitySelector: {
            cctvCount: [80, 90, 80, 90, 100, 110, 120, 130],
            single: [150, 160, 170, 180, 190, 200, 210, 220],
            pub: [2, 6, 4, 5, 6, 6, 6, 7],
        },

        crimeData: {
            murder: [87, 76, 65, 54, 43, 32, 21, 10],
            robbery: [97, 86, 75, 64, 53, 42, 31, 20],
            sexualAssault: [102, 91, 81, 71, 60, 50, 40, 30],
            assault: [34, 34, 35, 36, 37, 38, 39, 40],
            theft: [124, 113, 102, 92, 81, 71, 60, 50]
        },
        grade: 3,
        population: 487038
    },
    guro: {
        facilitySelector: {
            light: [865, 750, 635, 520, 405, 290, 175, 60],
            pub: [88, 86, 85, 84, 83, 82, 81, 80],
        },
        crimeData: {
            murder: [612, 537, 462, 388, 313, 239, 164, 90],
            robbery: [858, 749, 641, 533, 424, 316, 208, 100],
            sexualAssault: [23, 35, 47, 60, 72, 85, 97, 110],
            assault: [68, 76, 85, 94, 103, 112, 121, 130],
            theft: [123, 125, 127, 130, 132, 135, 137, 140]
        },
        grade: 2,
        population: 405075
    },
    youngdeungpo: {
        facilitySelector: {
            cctvCount: [23, 35, 47, 60, 72, 85, 97, 110],
            light: [68, 76, 85, 94, 103, 112, 121, 130],
            foreign: [123, 125, 127, 130, 132, 135, 137, 140],
            pub: [65, 78, 92, 105, 119, 132, 146, 160],
        },
        crimeData: {
            murder: [444, 402, 360, 318, 276, 234, 192, 150],
            robbery: [65, 78, 92, 105, 119, 132, 146, 160],
            sexualAssault: [34, 34, 35, 36, 37, 38, 39, 40],
            assault: [865, 750, 635, 520, 405, 290, 175, 60],
            theft: [87, 76, 65, 54, 43, 32, 21, 10]
        },
        grade: 3,
        population: 374920
    },
    seodaemun: {
        facilitySelector: {
            cctvCount: [65, 78, 92, 105, 119, 132, 146, 160],
            light: [123, 125, 127, 130, 132, 135, 137, 140],
            policeStationCount: [23, 35, 47, 60, 72, 85, 97, 110],
            pub: [65, 78, 92, 105, 119, 132, 146, 160],
            single: [150, 160, 170, 180, 190, 200, 210, 220]
        },
        crimeData: {
            murder: [88, 86, 85, 84, 83, 82, 81, 80],
            robbery: [612, 537, 462, 388, 313, 239, 164, 90],
            sexualAssault: [23, 35, 47, 60, 72, 85, 97, 110],
            assault: [124, 113, 102, 92, 81, 71, 60, 50],
            theft: [87, 76, 65, 54, 43, 32, 21, 10]
        },
        grade: 1,
        population: 305541
    }
};



document.addEventListener('DOMContentLoaded', function () {
    const button = document.getElementById('showHideButton');
    const boxes = document.querySelectorAll('.crBox');

    button.addEventListener('click', function () {
        boxes.forEach(crBox => {
            const condition = parseInt(crBox.getAttribute('data-condition'));
            if (condition === 1) {
                crBox.style.display = 'block'
                setTimeout(function () {
                    crBox.classList.add('show')
                }, 300);
            } else {
                crBox.style.display = 'none'
                crBox.classList.remove('show');
            }
        });
    });
});



function showchart(local, facil) {
    var ctx = document.getElementById('chart').getContext('2d');

    const crimes = ['murder', 'robbery', 'sexualAssault', 'assault', 'theft'];

    myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: districtData[local].facilitySelector[facil], // X축 레이블
            datasets: crimes.map(crime => {
                return {
                    label: crime,
                    data: districtData[local].crimeData[crime],
                    borderColor: getColor(crime), // 각 범죄별 색상 함수
                    borderWidth: 1,

                };
            })
        },
        options: {
            maintainAspectRatio: false,
            responsive: true
        }
    });
}

showchart('gangnam', "cctvCount")


function getColor(crime) {
    switch (crime) {
        case 'murder': return 'blue';
        case 'robbery': return 'red';
        case 'sexualAssault': return 'green';
        case 'assault': return 'violet';
        case 'theft': return 'orange';
        default: return 'black';
    }
}


function removeChart() {
    if (myChart) {
        myChart.destroy();
    }
}


new Swiper('.swiper', {
    autoplay: {
        delay: 2000
    },
    loop: true,
    slidesPerView: 3,
    spaceBetween: 10,
    centeredSlides: true,
    pagination: {
        el: '.swiper-pagination',
        clickable: true
    },
    navigation: {
        prevEl: '.swiper-button-prev',
        nextEl: '.swiper-button-next'
    }
})




var btnCount;
var valuesArray;
var dropdownValue = "gangnam";

var guName = "강남구";
const dropdownItems = document.querySelectorAll('.dropdown-item');
dropdownItems.forEach(item => {
    item.addEventListener('click', function () {
        btnCount = 0;
        dropdownValue = this.value;
        guName = this.innerText;

        document.querySelectorAll('.guName').forEach(item => {
            item.innerText = guName;
        })

        document.getElementById('grade').innerText = districtData[dropdownValue].grade
        document.getElementById('population').innerText = districtData[dropdownValue].population.toString()
            .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + " 명";



        const crBoxes = document.querySelectorAll('.crBox');
        const compos = document.querySelectorAll('.compo');
        const facilitySelectors = districtData[dropdownValue].facilitySelector;
        crBoxes.forEach(box => {
            box.setAttribute('data-condition', 0);
            box.style.display = 'none';
            box.classList.remove('show')
        })

        //valuesArray 치안시설 배열
        valuesArray = Object.keys(facilitySelectors);
        valuesArray.forEach((value, index) => {

            if (value == "cctvCount") {
                compos[index].textContent = "CCTV";
                crBoxes[index].setAttribute('data-condition', 1)
            } else if (value == "light") {
                compos[index].textContent = "보안등";
                crBoxes[index].setAttribute('data-condition', 1)
            } else if (value == "policeStationCount") {
                compos[index].textContent = "경찰관서"
                crBoxes[index].setAttribute('data-condition', 1)
            } else if (value == "pub") {
                compos[index].textContent = "주점";
                crBoxes[index].setAttribute('data-condition', 1)
            } else if (value == "single") {
                compos[index].textContent = "1인가구";
                crBoxes[index].setAttribute('data-condition', 1)
            }
        })



        compos.forEach(box => {
            var factitle;
            var text = box.textContent.trim()
            if (text == "CCTV") {
                factitle = "cctvCount"
            } else if (text == "보안등") {
                factitle = "light"
            } else if (text == "경찰관서") {
                factitle = "policeStationCount"
            } else if (text == "주점") {
                factitle = "pub"
            } else if (text == "1인가구") {
                factitle = "single"
            }
            box.addEventListener('click', function () {

                removeChart();
                showchart(dropdownValue, factitle)
                btnCount = 0;

            })
        })

    });


});


var plusBtns = document.querySelectorAll('.btnplus');
var minusBtns = document.querySelectorAll('.btnminus');
var FacilityData;
var crimeData;
plusBtns.forEach(function (plusBtn) {
    plusBtn.addEventListener('click', function () {

        var text = plusBtn.parentElement.querySelector('.compo').textContent.trim();
        var facility;
        if (text == "CCTV") {
            facility = "cctvCount"
        } else if (text == "보안등") {
            facility = "light"
        } else if (text == "경찰관서") {
            facility = "policeStationCount"
        } else if (text == "주점") {
            facility = "pub"
        } else if (text == "1인가구") {
            facility = "single"
        }

        if (btnCount < 5) {
            FacilityData = myChart.data.labels.slice();
            myChart.data.labels = FacilityData;
            myChart.update();
            FacilityData.push(FacilityData[(FacilityData.length - 1)] + 10);
            myChart.update();
            btnCount++;





            const datasets = myChart.data.datasets;
            datasets.forEach((dataset, index) => {
                crimeData = dataset.data.slice();
                dataset.data = crimeData;
                myChart.update();
                crimeData.push(crimeData[(crimeData.length - 1)] - 5);
                myChart.update();
                const crimeafter = dataset.data[(dataset.data.length - 1)];


                var results = document.querySelectorAll('#result');
                var flucs = document.querySelectorAll('#fluc');

                if (crimeafter - crimestd[index] > 0) {
                    results[index].innerText = Math.round((crimeafter - crimestd[index]) / crimestd[index] * 100) + "%"
                    flucs[index].innerText = "증가"
                } else if (crimeafter - crimestd[index] == 0) {
                    results[index].innerText = "그대로"
                    flucs[index].innerText = "유지"
                } else if (crimeafter - crimestd[index] < 0) {
                    results[index].innerText = Math.round((crimestd[index] - crimeafter) / crimestd[index] * 100) + "%"
                    flucs[index].innerText = "감소"
                }
            })

        };
    });
});
minusBtns.forEach(function (minusBtn) {
    minusBtn.addEventListener('click', function () {
        var text = minusBtn.parentElement.querySelector('.compo').textContent.trim();
        var facility;
        if (text == "CCTV") {
            facility = "cctvCount"
        } else if (text == "보안등") {
            facility = "light"
        } else if (text == "경찰관서") {
            facility = "policeStationCount"
        } else if (text == "주점") {
            facility = "pub"
        } else if (text == "1인가구") {
            facility = "single"
        }

        if (btnCount > 0) {
            FacilityData.pop();
            myChart.update();
            btnCount--;


            const datasets = myChart.data.datasets;
            datasets.forEach((dataset, index) => {

                crimeData = dataset.data.slice();
                dataset.data = crimeData;
                crimeData.pop();
                myChart.update();


                var results = document.querySelectorAll('#result');
                var flucs = document.querySelectorAll('#fluc');
                const crimeafter = dataset.data[(dataset.data.length - 1)];
                if (crimeafter - crimestd[index] > 0) {
                    results[index].innerText = Math.round((crimeafter - crimestd[index]) / crimestd[index] * 100) + "%"
                    flucs[index].innerText = "증가"
                } else if (crimeafter - crimestd[index] == 0) {
                    results[index].innerText = "그대로"
                    flucs[index].innerText = "유지"
                } else if (crimeafter - crimestd[index] < 0) {
                    results[index].innerText = Math.round((crimestd[index] - crimeafter) / crimestd[index] * 100) + "%"
                    flucs[index].innerText = "감소"
                }
            })

        }





    });
})





var murderstd = districtData[dropdownValue].crimeData.murder[(districtData[dropdownValue].crimeData.murder.length - 1)]
var robstd = districtData[dropdownValue].crimeData.robbery[(districtData[dropdownValue].crimeData.robbery.length - 1)];
var sexstd = districtData[dropdownValue].crimeData.sexualAssault[(districtData[dropdownValue].crimeData.sexualAssault.length - 1)];
var assstd = districtData[dropdownValue].crimeData.assault[(districtData[dropdownValue].crimeData.assault.length - 1)];
var thestd = districtData[dropdownValue].crimeData.theft[(districtData[dropdownValue].crimeData.theft.length - 1)];

var crimestd = [murderstd, robstd, sexstd, assstd, thestd]




var modal = document.querySelector("#exp_img")
modal.addEventListener('mouseover', function () {
    document.getElementById("modalContent").style.display = "block";
});
modal.addEventListener('mouseout', function () {
    document.getElementById("modalContent").style.display = "none";
});

const sidebar = document.getElementById('sidebar')
const homeIcon = document.getElementById('home')
const upIcon = document.getElementById('upsideIcon')
const rankIcon = document.getElementById('rankIcon')
const siteIcon = document.getElementById('siteIcon')


sidebar.addEventListener('mouseenter', function () {
    homeIcon.className = 'fa fa-home';
    upIcon.className = 'fa-solid fa-arrow-up';
    rankIcon.className = 'fa-solid fa-chart-column';
    siteIcon.className = 'fa-solid fa-link';
});
