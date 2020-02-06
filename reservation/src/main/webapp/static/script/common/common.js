function drawTemplateToHtml(datas, defaultTemplate) {
	var template = document.getElementById(this.templateId).innerHTML;
	var bindTemplate = Handlebars.compile(template);

	var parentNodeCount = this.parentNodeIds.length;
	this.parentNodeIds.forEach( (parentNodeId, parentNodeIndex) => {
		var parentNode = document.getElementById(parentNodeId);
		var parentNodeHTML = parentNode.innerHTML + defaultTemplate;
		parentNode.innerHTML = datas.filter( (data, dataIndex) => {
			if (dataIndex % parentNodeCount === parentNodeIndex) {
				return true;
			}
		}).reduce( (prevHTML, data) => {
			return prevHTML + bindTemplate(data);
		}, parentNodeHTML);
	});
};

function removeInnerHtml() {
	this.parentNodeIds.forEach( (parentNodeId) => {
		var parentNode = document.getElementById(parentNodeId);
		parentNode.innerHTML = "";
	});
};

function ajax() {
	var oReq = new XMLHttpRequest();
	
	oReq.addEventListener("load", function() {
		var jsonResponse = JSON.parse(oReq.responseText);
		this.handleResponse(jsonResponse);
	}.bind(this));
	
	oReq.open(this.method, this.url);
	oReq.send();
};

// 현재 페이지의 url로부터 key : value를 얻는다.
function getParams(str) {
	  var params = {};
	  var keyValPairs = str.split("?")[1] && str.split("?")[1].split("&");

	  if (keyValPairs !== undefined) {
	    for (var i = 0; i < keyValPairs.length; i++) {
	      params[keyValPairs[i].split("=")[0]] = decodeURI(
	        keyValPairs[i].split("=")[1],
	        "UTF-8"
	      );
	    }
	  }

	  return params;
};