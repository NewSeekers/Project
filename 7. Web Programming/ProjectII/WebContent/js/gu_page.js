var local = "강남구";
console.log(local)



var guName;
var guNameValue;
var arrestdata;
var safetyChart
window.onload = function () {

  guName = document.getElementById("selectbox");
  guNameValue = guName.options[guName.selectedIndex].value;
  
// json 패치
  fetch('http://localhost:8181/ProjectII/chart/guPage_chart.do' ,{
		method:'Get',
		header:{
			'Content-Type' : 'application/json'
		}
	})
	.then(response => {
		if(!response.ok){
			throw new Error('Network response was not ok'+response.statusText);
		}
		return response.json();
	})
	.then(data => {
		console.log(data);
		document.getElementById('safetyChart').innerHTML = JSON.stringify(data);
		
	})
	.catch(error => {
		console.error("Fetch error: "+error);
	});


  // 셀렉트박스 자치구 바뀔때마다 차트도 바꿔주는 함수

  // 차트 지우고 업데이트
  const removeData = (chart) => {
    chart.data.labels = [];

    chart.update();
  }



  const addData = (chart, local) => {
    removeData(chart);
    chart.data.labels = ['평균', local];
    chart.update();
  };


  // 셀렉트박스
  var selectregion = document.getElementById("selectbox");
  selectregion.addEventListener('input', function () {
    local = $("#selectbox option:selected").val();
    // console.log("2nd" + local)

    addData(securityChart1, local);
    addData(securityChart2, local);
    addData(securityChart3, local);


  });


  var security_CCTV = {
		    labels: ['평균', local],
		    // a 강남구의 범례
		    datasets: [{
		      label: [
		        ' 지역 '
		      ],

		      data: [75, 51],
		      backgroundColor: ['#43c2c2', '#4eddad'],
		    }]
		  };
		 
  
  var security_light = {
		    labels: ['평균', local],
		    // a 강남구의 범례
		    datasets: [{
		      label: [
		        ' 지역 '
		      ],

		      data: [15, 51],
		      backgroundColor: ['#43c2c2', '#4eddad'],
		    }]
		  };
		
  var security_police = {
				    labels: ['평균', local],
				    // a 강남구의 범례
				    datasets: [{
				      label: [
				        ' 지역 '
				      ],

				      data: [35, 51],
				      backgroundColor: ['#43c2c2', '#4eddad'],
				    }]
				  };  
		  
		  

  var ctx1 = document.getElementById('myChart1').getContext('2d');
  var securityChart1 = new Chart(ctx1, {
    type: 'bar',
    data: security_CCTV,
    options: {

      x: {
        // 차트 옵션, css같은 느낌 양식다름,
      },
      y: {

      },
      plugins: {
        legend: {
          display: false,
        }
      },
      // plugins안에
      maintainAspectRatio: false

    }
  });


  var ctx2 = document.getElementById('myChart2').getContext('2d');
  var securityChart2 = new Chart(ctx2, {
    type: 'bar',
    data: security_light,
    options: {
      x: {
        // 차트 옵션, css같은 느낌 양식다름,
      },
      y: {

      },
      plugins: {
        legend: {
          display: false,
        }
      },
      maintainAspectRatio: false
      // reponsive : false

    }
  });


  var ctx3 = document.getElementById('myChart3').getContext('2d');
  var securityChart3 = new Chart(ctx3, {
    type: 'bar',
    data: security_police,
    options: {
      x: {
        // 차트 옵션, css같은 느낌 양식다름,
      },
      y: {

      },
      plugins: {
        legend: {
          display: false,
        }
      },
      // 이게 사이즈 바꿔주는거
      maintainAspectRatio: false
    }
  });




  // ----------------------------------------------------------



  arrestdata = {
    labels: ['2004', '2007', '2010', '2013', '2016', '2019', '2022'],
    datasets: [{
      label: '검거 비율',
      data: [7, 5, 2, 8, 4, 2, 3], //y축 초기값
      fill: false,
      borderColor: 'rgb(75, 192, 192)',
      tension: 0.1
    }]
  };
  var ctx = document.getElementById('safetyChart').getContext('2d');
  safetyChart = new Chart(ctx, {
    type: 'line',
    data: arrestdata,
    options: {
      maintainAspectRatio: false
    }
  });




  var buttons = document.querySelectorAll('.btn-group button');

  // 각 버튼에 클릭 이벤트 리스너 등록
  buttons.forEach(function (button) {
    button.addEventListener('click', function () {
      // 클릭된 버튼의 ID 가져오기
      var buttonID = button.id;
      console.log("buttonID");
      // ID에 따라 #lower의 내용 변경
      switch (buttonID) {
        case "bt2019":
          document.getElementById("chart_resultMent3").innerHTML = "<p>2019 년도 1위</p>";
          break;
        case "bt2020":
          document.getElementById("chart_resultMent3").innerHTML = "<p>2020 년도 2위</p>";
          break;
        case "bt2021":
          document.getElementById("chart_resultMent3").innerHTML = "<p>2021 년도 4위</p>";
          break;
        case "bt2022":
          document.getElementById("chart_resultMent3").innerHTML = "<p>2022 년도 5위</p>";
          break;
        case "bt2023":
          document.getElementById("chart_resultMent3").innerHTML = "<p>2023 년도 9위</p>";
          break;
        default:
          document.getElementById("chart_resultMent3").innerHTML = "<p>기본 내용</p>";
          break;
      }
    });
  });





  var modalwindow = document.getElementById('modal')
  modalwindow.addEventListener('click', function () {
    modalwindow.style.display = "none";
  })

//돔 형식으로 바꾸려고 하다가 취소한 주석.
// });
}

function guChange() {
  guName = document.getElementById("selectbox");

  guNameValue = guName.options[guName.selectedIndex].value;

  document.getElementById("gu_name").innerText = "<" + guNameValue + ">  ";

  if (guNameValue === "강남구") {
    arrestdata.datasets[0].data = [7, 5, 2, 8, 4, 2, 3];
  } else if (guNameValue === "관악구") {
    arrestdata.datasets[0].data = [4, 6, 5, 4, 3, 2, 4];
  } else if (guNameValue === "구로구") {
    arrestdata.datasets[0].data = [7, 3, 5, 6, 3, 5, 1];
  } else if (guNameValue === "서대문구") {
    arrestdata.datasets[0].data = [7, 3, 5, 8, 5, 9, 1];
  } else if (guNameValue === "영등포구") {
    arrestdata.datasets[0].data = [7, 9, 2, 5, 3, 2, 1];
  }

  safetyChart.update();
}





var modalwindow2 = document.querySelector("#policeq")
modalwindow2.addEventListener('mouseover', function () {
  document.getElementById("modalContent2").style.display = "block";
});

modalwindow2.addEventListener('mouseout', function () {
  document.getElementById("modalContent2").style.display = "none";
});

// 왼쪽 스크롤 컨트롤러
const sidedar = document.getElementById('sidebar')
const homeIcon = document.getElementById('home')
const upIcon = document.getElementById('upIcon')
const rankIcon = document.getElementById('rankIcon')
const mapIcon = document.getElementById('mapIcon')


sidedar.addEventListener('mouseenter', function () {
  homeIcon.className = 'fa-solid fa-house-user';
  upIcon.className = 'fa-solid fa-arrow-up';
  rankIcon.className = 'fa-solid fa-award';
  mapIcon.className = 'fa-solid fa-map-location-dot';
});












