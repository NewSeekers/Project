
window.onload = function () {
    var posts = [
        { id: 5, title: "망원동_망원시장주차장 골목이슈", author: "김**", date: "2023-12-12" },
        { id: 4, title: "여성긴급전화 1366에서 희망을 상담하세요", author: "홍**", date: "2023-12-12" },
        { id: 3, title: "경찰관분들 감사합니다", author: "박**", date: "2023-12-10" },
        { id: 2, title: "치안센터 활용방안", author: "성**", date: "2023-12-09" },
        { id: 1, title: "경찰서 사칭한 문자 전송", author: "이**", date: "2023-12-06" },
        
    ];
    var tableBody = document.querySelector("#board-table tbody");
    for (var i = 0; i < posts.length; i++) {
        var row = document.createElement("tr");
        var idCell = document.createElement("th");
        idCell.textContent = posts[i].id;

        var titleCell = document.createElement("td");
        titleCell.innerHTML = '<a href ="#">' + posts[i].title + "</a>";

        var authorCell = document.createElement("td");
        authorCell.textContent = posts[i].author;

        var dateCell = document.createElement("td");
        dateCell.textContent = posts[i].date;

        row.appendChild(idCell);
        row.appendChild(titleCell);
        row.appendChild(authorCell);
        row.appendChild(dateCell);

        tableBody.appendChild(row);

    }
};