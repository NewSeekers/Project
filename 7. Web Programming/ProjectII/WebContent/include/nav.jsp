<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<div class="headbar fixed-top ">
		<nav class="navbar navbar-expand-md">
			<div id="logo">
				<a href="./Index.jsp"><img src="./img/1logo.png" alt=""></a>
			</div>

			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu"
				aria-controls="navMenu" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navMenu">
				<ul class="navbar-nav lg-2">
					<li class="nav-item"><a class="nav-link active" aria-current="page" href="./seoul_main.jsp">
							<p>서울시 안전지도</p>
						</a></li>
					<li class="nav-item"><a class="nav-link" href="./gu_page.jsp">
							<p>우리동네 돋보기</p>
						</a></li>
					<li class="nav-item"><a class="nav-link" href="./preview.jsp">
							<p>예측서비스</p>
						</a></li>
					<li class="nav-item"><a class="nav-link" href="list.do?page=1">
							<p>커뮤니티</p>
						</a></li>
					<!-- 세션 체크 -->
					<% if (session.getAttribute("ValidMem") !=null) { %>
						<!-- 사용자가 로그인한 경우 -->
						<li class="nav-item"><a class="nav-link" id="myPageButton" data-bs-toggle="modal"
								data-bs-target="#myPageModal">
								<p>마이페이지</p>
							</a></li>


						<% } else { %>
							<!-- 로그인하지 않은 경우 -->
							<li class="nav-item"><a class="nav-link" href="login.do">
									<p>로그인</p>
								</a></li>
							<% } %>
				</ul>
			</div>
		</nav>
	</div>