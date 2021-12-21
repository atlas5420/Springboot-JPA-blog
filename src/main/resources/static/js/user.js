let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{ // function(){} 대신 ()=>{}사용, this를 바인딩하기 위해
			this.save();
		});
		$("#btn-update").on("click", ()=>{ // function(){} 대신 ()=>{}사용, this를 바인딩하기 위해
			this.update();
		});
	},
	
	save: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		// ajax 호출시 default가 비동기 호출
		// ajax 통신을 통해 3개의 파라미터를 json으로 변경하여 insert 요청
		// ajax가 통신 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환?
		$.ajax({
		// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //json문자열
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지 (MIME)
			dataType: "json" //요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열(형식이 json이라면) => javascript 오브젝트로 변경
		}).done(function(resp){
			alert("회원가입 완료");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	update: function(){
		let data = {
			id: $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		$.ajax({
		// 회원가입 수행 요청
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), //json문자열
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지 (MIME)
			dataType: "json" //요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열(형식이 json이라면) => javascript 오브젝트로 변경
		}).done(function(resp){
			alert("회원정보 수정 완료");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		}

}

index.init();