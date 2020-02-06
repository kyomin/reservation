let category = {
		/* 		Variables	 */
		method : "GET",
		url : "api/categories",
		templateId : "categoryItem",
		parentNodeIds : ["category_tab"],
		tabMenu : document.querySelector(".section_event_tab"),
		prevCategoryId : 0,
		currentCategoryId : 0,
		defaultCategoryHTML : "<li class='item' data-category='0'><a class='anchor active' id='category0'> <span>전체리스트</span> </a></li>",
		
		/* 		Functions	 */
		handleResponse : function(jsonResponse) {
			drawTemplateToHtml.bind(this)(jsonResponse.categories, this.defaultCategoryHTML);
		}
};

// 		make ajax function for this data
const sendAjaxForCategory = ajax.bind(category);