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