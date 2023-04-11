<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<div>
		<a href="../home/main">메인페이지로 이동</a>
	</div>

	<h1>회원가입</h1>
	
	<script>
	var JoinForm__submitDone = false;
	function JoinForm__submit(form) {
		//중복 submit을 방지하기 위한 조건문
		if(JoinForm__submitDone){
			alert('처리중입니다.');
			return;
		}
		
		var loginId = form.loginId.value.trim();
		var loginPw = form.loginPw.value.trim();
		var loginPwConfirm = form.loginPwConfirm.value.trim();
		var name = form.name.value.trim();
		
		if(loginId.length == 0) {
			alert('아이디를 입력해주세요');
			form.loginId.focus();
			return;
		}
		
		if(loginPw.length == 0) {
			alert('비밀번호를 입력해주세요');
			form.loginPw.focus();
			return;
		}
		
		if(loginPwConfirm.length == 0) {
			alert('비밀번호확인을 입력해주세요');
			form.loginPwConfirm.focus();
			return;
		}
		
		if(loginPw != loginPwConfirm) {
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.focus();
			return;
		}
		
		if(name.length == 0) {
			alert('이름을 입력해주세요');
			form.name.focus();
			return;
		}
		JoinForm__submitDone = true;
		form.submit();
	}
	</script>

	<form method="post" action="doJoin" onsubmit="JoinForm__submit(this); return false;">
	
		<div>
			아이디 : <input autocomplete="off" type="text" placeholder="아이디를 입력해주세요" name="loginId" />
		</div>
		<div>
			비밀번호 :
			<input type="password" placeholder="비밀번호를 입력하세요" name="loginPw" id="pw1"/>
			

		</div>
		<div>
			비밀번호 확인:
			<input type="password" placeholder="비밀번호확인" name="loginPwConfirm" id="pw2"/>
			

		</div>
		<div>
			이름 :
			<input type="text" placeholder="이름을 입력하세요" name="name"/>
			

		</div>
		<button type="submit">회원가입 완료</button>

	</form>


</body>
</html>