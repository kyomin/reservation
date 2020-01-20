<%@page import="kr.or.connect.todo.dto.TodoDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>TODO LIST</title>
	
	<!-- CSS import -->
	<link rel="stylesheet" type="text/css" href="./css/main.css">

	<!-- js 파일 include -->
	<script type="text/javascript" src="/Todo/js/main.js"></script>
</head>

<body>
	<!-- 메인 페이지 전체를 감싸는 컨테이너 -->
	<div class="container">
		<!-- 기울어진 글자 부분 -->
		<div class="rotate_text">나의 해야할 일들</div>
		
		<!-- 헤더 부분 -->
		<div class="header">
			<a href="/Todo/form">새로운 TODO 등록</a>
		</div>
		
		<!-- 목록 콘텐츠 부분 -->
		<div class="contents">
			<!-- TODO 영역 부분 -->
			<div id="todo">
				<div class="title">
					TODO
				</div>
				<c:forEach items="${requestScope.todoList}" var="item">
					<div class="content" id="${item.getId() }">
						<div class="plan">
							${item.getTitle()}
						</div>
						<div class="info">
							등록날짜:
							<fmt:parseDate var="dateString" value="${item.getRegdate()}" pattern="yyyy-MM-dd HH:mm:ss.S" />
							<fmt:formatDate pattern="yyyy.MM.dd" value="${dateString}"/>, 
							${item.getName() }, 우선순위 ${item.getSequence() }
						</div>
						<button id="btn${item.getId() }" class="right_btn" onClick="ajax('${item.getId() }', '${item.getType()}');"> 
							->
						</button>
					</div>
				</c:forEach>
			</div>
			
			<!-- DOING 영역 부분 -->
			<div id="doing">
				<div class="title">
					DOING
				</div>
				<c:forEach items="${requestScope.doingList}" var="item">
					<div class="content" id="${item.getId() }">
						<div class="plan">
							${item.getTitle()}
						</div>
						<div class="info">
							등록날짜:
							<fmt:parseDate var="dateString" value="${item.getRegdate()}" pattern="yyyy-MM-dd HH:mm:ss.S" />
							<fmt:formatDate pattern="yyyy.MM.dd" value="${dateString}"/>, 
							${item.getName() }, 우선순위 ${item.getSequence() }
						</div>
						<button id="btn${item.getId() }" class="right_btn" onClick="ajax('${item.getId() }', '${item.getType()}');"> 
							->
						</button>
					</div>
				</c:forEach>
			</div>
			
			<!-- DONE 영역 부분 -->
			<div id="done">
				<div class="title">
					DONE
				</div>
				<c:forEach items="${requestScope.doneList}" var="item">
					<div class="content" id="${item.getId() }">
						<div class="plan">
							${item.getTitle()}
						</div>
						<div class="info">
							등록날짜:
							<fmt:parseDate var="dateString" value="${item.getRegdate()}" pattern="yyyy-MM-dd HH:mm:ss.S" />
							<fmt:formatDate pattern="yyyy.MM.dd" value="${dateString}"/>, 
							${item.getName() }, 우선순위 ${item.getSequence() }
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>

</html>