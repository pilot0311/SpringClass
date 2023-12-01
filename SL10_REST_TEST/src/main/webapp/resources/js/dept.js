console.log('살려줘...')

var deptService = (function(){
	function add(dept,callback,error){
		console.log('add')
		
		$.ajax({
			url: "/scott/dept/new",	
			method: "POST",
			data: JSON.stringify(dept),
			contentType: "application/json; charset=utf-8",
			//dataType: "json",
			cache: false,
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr, status, er){
				if(error){
					error(er)
				}
			}
		})
		
	}
	
	function remove(deptno,callback,error){
		console.log('remove'+ deptno)
		$.ajax({
			url: "/scott/dept/"+deptno,	
			method: "DELETE",
			cache: false,
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr, status, er){
				if(error){
					error(er)
				}
			}
		})
	}

	return {
		add: add,
		remove: remove
	};

})();