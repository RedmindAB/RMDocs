
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

		$("#class_list").append('<li>' + className + '<ul id="method' + cnt +'">'
				+ '</ul>'
				+ '</li>');
		iterateMethods(val, cnt);
		cnt++;
	});
}

function iterateMethods(val, cnt){

	$.each(val.Methods, function(i, val){
		var methodName = val.MethodName;
		var method = "#method" + cnt;

		var iteration = 1;
		for (var key in val) {
			if(iteration == 1){
				$(method).append('<li>' + key +': '+ val[key] +'</li>'); 
				iteration++;
				continue;
			}
			if($.isArray(val[key])) {
				iterateMultiples(val[key], method);
				continue;
				iteration++;
			}
			$(method).append('<ul><li>' + key +': '+ val[key] +'</li>')
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



