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
		$("#btn-reply-save").on("click", ()=>{ // function(){} 대신 ()=>{}사용, this를 바인딩하기 위해
			this.replysave();
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
	},
	
	replysave: function(){
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()
		};
		let boardId = $("#boardId").val();
		
		$.ajax({
			type: "POST",
			url: `/api/board/${boardId}/reply`,
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
			dataType: "json" 
		}).done(function(resp){
			alert("댓글등록 완료");
			location.href = `/board/${boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},

}

index.init();