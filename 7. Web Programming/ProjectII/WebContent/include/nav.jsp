<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="headbar fixed-top ">
	<nav class="navbar navbar-expand-md">
		<div id="logo">
			<a href="./Index.jsp"><img src="./img/1logo.png" alt=""></a>
		</div>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navMenu" aria-controls="navMenu"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navMenu">
			<ul class="navbar-nav mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="./seoul_main.jsp">
						<p>서울시 안전지도</p>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="./gu_page.jsp">
						<p>우리동네 돋보기</p>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="./preview.jsp">
						<p>안전 미리보기</p>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="list.do?page=1">
						<p>게시판</p>
				</a></li>
			</ul>
		</div>
	</nav>
</div>

