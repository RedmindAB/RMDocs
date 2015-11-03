	
$(function () {

	getJSONObject();

	$(document).on("click", "label.tree-toggler", function() {
			$(this).parent().children('ul.tree').toggle(300);
    		console.log('clicked nav list');
    });

});


function getJSONObject(){

	$.getJSON("/MyProject.json", function(result){

		$("#project-name").text(result.ProjectName);

		iterateClasses(result);
	});
}

var innerTableCnt = 1;

function iterateClasses(result){
	var cnt = 1;
	$.each(result.Classes, function(i, val){

		var className = val.ClassName;
		var collapseId = "collapse" + cnt;
		var listId = "ulId" + cnt;

		$('#main-container').append('<h3 class="method-class" id="#'+ className +'">' + className + '</h3>')

		$("#sidebar").append('<li><label class="tree-toggler nav-header" for="'+ className +'">'+ className + '</label>'
				+ '<ul class="nav nav-list tree" id="'+ listId +'" style="display: none;">'
				+ '</ul></li>');

		iterateMethods(val, cnt, listId);
		cnt++;
	});
}

function iterateMethods(val, cnt, listId){
	var tableCnt = 1;

	$.each(val.Methods, function(i, val){

		var methodName = val.MethodName;
		var method = "#method" + cnt;
		var table = "tbody" + tableCnt;

		if($("#" + methodName).length){
			methodName += '1';
		}
		
		console.log(listId);
		//Append a new list item for each method
		$("#" + listId).append('<li><a href="#'+ methodName +'">' + methodName +'</a></li>');

		//Append a new table for each method
		$('#main-container').append('<table class="table table-striped table-bordered table-hover table-condensed" id="'+ methodName +'">'
		 + '<thead><tr><th colspan="2">' + methodName + '</th></tr></thead>'
		 + '<tbody></tbody></table>');

		
		var iteration = 1;
		for (var key in val) {
			if(iteration == 1){
				iteration++;
				continue;
			}
			if($.isArray(val[key])) {
				iterateMultiples(key, val[key], methodName, innerTableCnt);
				innerTableCnt++;
				iteration++;
				continue;

			}
			//Append table row to table
			$('#'+methodName).append('<tr><td>'+ key + '</td><td>' + val[key] + '</td></tr>');
			iteration++;
		}

	});

}

function iterateMultiples(key, val, method, innerTableCnt){

	$('#' + method).append('<thead class="inner-table-head"><tr><th colspan="2">' + key + '</thead></th><tr><tbody class="inner-table" id="innerTable' + innerTableCnt +'">');

	$.each(val, function(i, val){

		for(var key2 in val){
		$('#innerTable'+ innerTableCnt).append('<tr><td>' + key2 + '</td><td>' + val[key2] + '</td><tr>');
		}
	});

	$('#' + method).append('</tbody>');
}
