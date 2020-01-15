<%@page import="kr.or.connect.todo.dto.TodoDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>TODO LIST</title>
	
	<!-- CSS import -->
	<link rel="stylesheet" type="text/css" href="./css/main.css">
</head>

<!-- MainServlet으로부터 데이터를 받아온다. -->
<%
	List<TodoDto> todoList = (List<TodoDto>)request.getAttribute("todoList");
	List<TodoDto> doingList = (List<TodoDto>)request.getAttribute("doingList");
	List<TodoDto> doneList = (List<TodoDto>)request.getAttribute("doneList");
%>

<body>
	<!-- 메인 페이지 전체를 감싸는 컨테이너 -->
	<div class="container">
		<!-- 기울어진 글자 부분 -->
		<div class="rotate_text">나의 해야할 일들</div>
		
		<!-- 헤더 부분 -->
		<div class="header">
			<a href="/Todo/todo/form">새로운 TODO 등록</a>
		</div>
		
		<!-- 목록 콘텐츠 부분 -->
		<div class="contents">
			<!-- TODO 영역 부분 -->
			<div class="todo">
				<div class="title">
					TODO
				</div>
				<c:forEach items="${todoList}" var="item">
					<div class="content">
						<div class="plan">
							${item.getTitle()}
						</div>
						<div class="info">
							등록날짜:${item.getRegdate() }, ${item.getName() }, 우선순위 ${item.getSequence() }
						</div>
						<button class="right_btn"> 
							->
						</button>
					</div>
				</c:forEach>
			</div>
			
			<!-- DOING 영역 부분 -->
			<div class="doing">
				<div class="title">
					DOING
				</div>
				<c:forEach items="${doingList}" var="item">
					<div class="content">
						<div class="plan">
							${item.getTitle()}
						</div>
						<div class="info">
							등록날짜:${item.getRegdate() }, ${item.getName() }, 우선순위 ${item.getSequence() }
						</div>
						<button class="right_btn"> 
							->
						</button>
					</div>
				</c:forEach>
			</div>
			
			<!-- DONE 영역 부분 -->
			<div class="done">
				<div class="title">
					DONE
				</div>
				<c:forEach items="${doneList}" var="item">
					<div class="content">
						<div class="plan">
							${item.getTitle()}
						</div>
						<div class="info">
							등록날짜:${item.getRegdate() }, ${item.getName() }, 우선순위 ${item.getSequence() }
						</div>
						<button class="right_btn"> 
							->
						</button>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>

</html>