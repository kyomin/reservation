<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>TODO FORM</title>
	
	<!-- CSS import -->
	<link rel="stylesheet" type="text/css" href="../css/todoForm.css">
</head>

<body>
	<!-- ���� ���� form ������ ��ü�� ���δ� �����̳� -->
	<div class="todo_form_wrap">
		<!-- form �ۼ� ����! -->
		<div class = "todo_form">
			<h1>���� ���</h1>
			<form action="/Todo/todo/add" method="post">
				<div class="form_box_line">
					<!-- title �Է�! -->
					<label class="input-label" for="input_title">����ΰ���?</label>
					<input
						type="text"
						id="input_title"
						class="text_input"
						placeholder="swift �����ϱ�(24�ڱ���)"
						maxlength="24"
						required
						name="title"
					/>
					
					<!-- name �Է�! -->
					<label class="input-label" for="input_name">���� �����ΰ���?</label>
					<input
						type="text"
						id="input_name"
						class="text_input"
						placeholder="ȫ�浿"
						required
						name="name"
					/>
					
					<!-- sequence �Է�! -->
					<label class="input-label" for="input_sequence">�켱������ �����ϼ���</label>
					<input type="radio" name="sequence" value="1" required>1����
					<input type="radio" name="sequence" value="2">2����
					<input type="radio" name="sequence" value="3">3����
				
					<!-- ��ư ����! -->
					<div class="btn_area">
						<!-- ���� -->
						<input type="submit" value="����" class="submit_btn">
					
						<!-- ��������� -->
						<input type="reset" value="���������" class="erase_btn">
					
						<!-- ���� ��ư -->
						<a href="/Todo/todo" class="prev_btn"> < ���� </a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>