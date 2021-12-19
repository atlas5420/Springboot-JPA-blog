let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{ // function(){} 대신 ()=>{}사용, this를 바인딩하기 위해
			this.save();
		});
		$("#btn-delete").on("click", ()=>{ // function(){} 대신 ()=>{}사용, this를 바인딩하기 위해
			this.deleteById();
		});
		$("#btn-update").on("click", ()=>{ // function(){} 대신 ()=>{}사용, this를 바인딩하기 위해
			this.update();
		});

	},
	
	save: function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
			dataType: "json" 
		}).done(function(resp){
			alert("글쓰기 완료");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	deleteById: function(){
	var id = $("#id").text();
	
		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json" 
		}).done(function(resp){
			alert("삭제 완료");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	update: function(){
		let id =  $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
			dataType: "json" 
		}).done(function(resp){
			alert("글수정 완료");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}

}

index.init();