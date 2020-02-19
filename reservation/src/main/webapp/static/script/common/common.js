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

// Convert JavaScript Date Type Into MySQL Date Type
(function() {
    Date.prototype.toMySQLDateFormat = Date_toYMD;
    function Date_toYMD() {
        var year, month, day, hours, minutes, seconds;        
        year = String(this.getFullYear());
        
        month = String(this.getMonth() + 1);
        if(month.length === 1) {
            month = "0" + month;
        }
        
        day = String(this.getDate());
        if(day.length === 1) {
            day = "0" + day;
        }
        
        hours = String(this.getHours());
        if(hours.length === 1) {
        	hours = "0" + hours;
        }
        
        minutes = String(this.getMinutes());
        if(minutes.length === 1) {
        	minutes = "0" + minutes;
        }
        
        seconds = String(this.getSeconds());
        if(seconds.length === 1) {
        	seconds = "0" + seconds;
        }
        
        return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
    }
})();