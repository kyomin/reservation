<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>TODO FORM</title>
	
	<!-- CSS import -->
	<link rel="stylesheet" type="text/css" href="./css/todoForm.css">
</head>

<body>
	<!-- 서버 제출 form 페이지 전체를 감싸는 컨테이너 -->
	<div class="todo_form_wrap">
		<!-- form 작성 영역! -->
		<div class = "todo_form">
			<h1>할일 등록</h1> 
			<form action="/Todo/add" method="post">
				<div class="form_box_line">
					<!-- title 입력! -->
					<label class="input-label" for="input_title">어떤일인가요?</label>
					<input
						type="text"
						id="input_title"
						class="text_input"
						placeholder="swift 공부하기(24자까지)"
						maxlength="24"
						required
						name="title"
					/>
					
					<!-- name 입력! -->
					<label class="input-label" for="input_name">누가 할일인가요?</label>
					<input
						type="text"
						id="input_name"
						class="text_input"
						placeholder="홍길동"
						required
						name="name"
					/>
					
					<!-- sequence 입력! -->
					<label class="input-label" for="input_sequence">우선순위를 선택하세요</label>
					<input type="radio" name="sequence" value="1" required>1순위
					<input type="radio" name="sequence" value="2">2순위
					<input type="radio" name="sequence" value="3">3순위
				
					<!-- 버튼 영역! -->
					<div class="btn_area">
						<!-- 제출 -->
						<input type="submit" value="제출" class="submit_btn">
					
						<!-- 내용지우기 -->
						<input type="reset" value="내용지우기" class="erase_btn">
					
						<!-- 이전 버튼 -->
						<a href="/Todo/main" class="prev_btn"> < 이전 </a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>