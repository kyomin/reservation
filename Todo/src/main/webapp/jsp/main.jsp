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

<!-- MainServlet���κ��� �����͸� �޾ƿ´�. -->
<%
	List<TodoDto> todoList = (List<TodoDto>)request.getAttribute("todoList");
	List<TodoDto> doingList = (List<TodoDto>)request.getAttribute("doingList");
	List<TodoDto> doneList = (List<TodoDto>)request.getAttribute("doneList");
%>

<body>
	<!-- ���� ������ ��ü�� ���δ� �����̳� -->
	<div class="container">
		<!-- ������ ���� �κ� -->
		<div class="rotate_text">���� �ؾ��� �ϵ�</div>
		
		<!-- ��� �κ� -->
		<div class="header">
			<a href="/Todo/todo/form">���ο� TODO ���</a>
		</div>
		
		<!-- ��� ������ �κ� -->
		<div class="contents">
			<!-- TODO ���� �κ� -->
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
							��ϳ�¥:${item.getRegdate() }, ${item.getName() }, �켱���� ${item.getSequence() }
						</div>
						<button class="right_btn"> 
							->
						</button>
					</div>
				</c:forEach>
			</div>
			
			<!-- DOING ���� �κ� -->
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
							��ϳ�¥:${item.getRegdate() }, ${item.getName() }, �켱���� ${item.getSequence() }
						</div>
						<button class="right_btn"> 
							->
						</button>
					</div>
				</c:forEach>
			</div>
			
			<!-- DONE ���� �κ� -->
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
							��ϳ�¥:${item.getRegdate() }, ${item.getName() }, �켱���� ${item.getSequence() }
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