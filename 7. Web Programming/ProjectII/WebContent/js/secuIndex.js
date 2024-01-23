
fetch('/exampleServlet')
            .then(response => response.json())
            .then(data => {
                // 가져온 데이터를 화면에 출력
                document.getElementById('data-container').innerText = data.message;
            })
            .catch(error => console.error('Error fetching data:', error));