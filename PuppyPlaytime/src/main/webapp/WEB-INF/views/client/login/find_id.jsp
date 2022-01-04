<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<!-- 모바일 웹 페이지 설정 끝 -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script type="text/javascript">
	function idFind() {
		//유효성
		var form = document.findId;
		var checkEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/; //Email 유효성 검사 정규식
		//값 불러오기
		var userEmail = document.getElementById("m_email"); //이메일
		var userID = document.getElementById("m_id"); //아이디

		if (form.m_email.value == "") {
			alert("이메일을 입력하세요.");
			form.m_email.focus();//이메일 박스로 이동.
			return;
		}
		if (!checkEmail.test(userEmail.value)) {
			alert("이메일 ---@---.com 형식으로 입력 가능합니다.");
			return;

		} else {
			$.ajax({
				url : "/client/login/find_id",
				type : "POST",
				data : "m_email=" + $("#m_email").val(),
				datatype : "text",
				success : function(resultData) {
					if (resultData == "ok") {//해당되는 이메일이 없을 경우 
						alert("해당되는 이메일이 없습니다.");
						$("#m_email").val("");
						$("#m_email").focus();//이메일 박스로 이동
					} else {	
						alert("아이디 : "+resultData);
						$("#m_email").attr("readOnly", "readOnly");
						$('#findId').attr('disabled', false);
						
					}
				},
				error : function() {
					alert('에러입니다.')
				}
			});
		}

	}
</script>
</head>
<body>
	<form name="findId" id="findId" action="/client/login/find_id"
		method="post">
		<div class="form-group">
			<label class="form-label mt-4">아이디 찾기</label>
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="m_email" name="m_email"
					placeholder="이메일을 입력해주세요"> <label for="m_email">이메일</label>
				<button type="button" value="찾기" onclick="idFind()"
					class="col-sm-2 control-label">찾기</button>

			</div>

			<button type="button" class="btn btn-primary"
				onclick="location.href='http://localhost:8080/client/login/login'">돌아가기</button>
			<button type="button" class="btn btn-primary" onclick="location.href='http://localhost:8080/client/login/find_id'">다시입력</button>
		</div>
	</form>


</body>
</html>