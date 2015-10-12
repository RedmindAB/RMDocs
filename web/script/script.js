	
$(function () {

	getJSONObject();

});


function getJSONObject(){

	$.getJSON("http://127.0.0.1:8000/MyProject.json", function(result){

		$("#proj_name").text(result.ProjectName);

		iterateClasses(result);
	});
}

function iterateClasses(result){
	var cnt = 1;
	$.each(result.Classes, function(i, val){

		var className = val.ClassName;
		var collapseId = "collapse" + cnt;
		var listId = "ulId" + cnt;
		
		$("#class_list").append('<li>' + className + '<ul id="method' + cnt +'">'
				+ '</ul>'
				+ '</li>');
//		$("#sidebar").append('<li><a data-toggle="collapse" href="" class="list-group-item">' + className +'</a></li>');
		
		$("#sidebar").append('<li>'
				+ '<a data-toggle="collapse" href="#'+ collapseId +'" class="list-group-item">'+ className +'</a>'
					+ '<div id="'+ collapseId +'" class="panel-collapse collapse">'
						+ '<ul class="list-group" id="'+ listId +'">'
//						+ '<li class="list-group-item">One</li>'
//						+ '<li class="list-group-item">Two</li>'
//						+ '<li class="list-group-item">Three</li>'
						+ '</ul>'
					+ '</div>'
				+ '</li>');
	     
		iterateMethods(val, cnt, listId);
		cnt++;
	});
}

function iterateMethods(val, cnt, listId){

	$.each(val.Methods, function(i, val){
		var methodName = val.MethodName;
		var method = "#method" + cnt;
		console.log(listId);
		$("#" + listId).append('<li class="list-group-item">' + methodName +'</li>');
		
		var iteration = 1;
		for (var key in val) {
			if(iteration == 1){
				$(method).append('<li>' + key +': '+ val[key] +'</li>'); 
				$(listId).append('<li class="list-group-item">' + key +': '+ val[key] +'</li>');
				iteration++;
				continue;
			}
			if($.isArray(val[key])) {
				iterateMultiples(val[key], method);
				continue;
				iteration++;
			}
			$(method).append('<ul><li>' + key +': '+ val[key] +'</li>')
			$(listId).append('<li class="list-group-item">' + key +': '+ val[key] +'</li>');
			iteration++;
		}

	});
}

function iterateMultiples(val, method){

	$.each(val, function(i, val){
		for(var key in val){
			$(method).append('<ul><li>' + key +': '+ val[key] +'</li>'); 
		}
	});
}



