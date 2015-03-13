


function createAjaxObj(){
	var req;
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}else{
		req = new ActiveXObject("Msxml2.XMLHTTP");  //ie
	}
	return req;
}


function addEventHandler(target, type, func){
	
    if (window.addEventListener){ 
    	
        target.addEventListener(type, func, true);
        
    }
    else if (window.attachEvent){
    	
        target.attachEvent("on" + type, func);
        
    }
    	    
    else target["on" + type] = func;
}



function execAction(target,url,data){
	
	//alert(target.getAttribute('name'));
	
	var name =target.getAttribute('name');
	var value = target.getAttribute('value');
	if(value == null || name=="stat_month" || name=="stat_day"){
	//	alert("sdgaserg");
		
		value = target.value;
		
	}
		
	
	data[name]= value;
	if(value =="all" && name=="book"){
		
		//delete data.book ;方式一
		delete data[name];
	}
	var req = createAjaxObj();
	var values = $.toJSON(data);
	//alert(values);
	
	req.open("post",url,true);
	req.setRequestHeader("Content-Type", "application/json");
	req.send(values);
	req.onreadystatechange=function(){
    	if (req.readyState==4 && req.status==200){
    		
    		pageNav(eval("("+req.responseText+")"));
			repeatPage(eval("("+req.responseText+")"));			 
			initListener(); 
    	}
		};
}

function pageNav(content){
	var total = parseInt(content.total);
	//var pageSize =  parseInt(content.pageSize);
	var pageIndex = parseInt(content.pageIndex);
	var next = parseInt(content.next) ;
	var prev = parseInt(content.prev) ;
	var pageCount = parseInt(content.pageCount);
	var html = "";
	html +='<ul class="pagination pagination-sm">';
	html +="<li><a>共"+total+"条记录</a></li>";
	if(prev>0){
		html += "<li ><a href='#'"+" name='pageIndex'"+"value='"+prev+"'>上页</a></li>";
	}else{
		html +="<li class='disabled'><a class=''>上页</a></li>";
	}
	
	if(pageCount > 8){
		
		var start = pageIndex - 4;
		var end = pageIndex + 4 ;
		if (start <=0){
			start =1;
			end = 8;				
		}
		if(end > pageCount){
			
			end = pageCount;
			
		}
		for(var i=start;i<=end;i++){
			if(i == pageIndex){
				html += " <li class='active'><a>"+i+"</a></li >";
			}else{
				html += " <li ><a href='#' name='pageIndex' value='"+i+"'>"+i+"</a></li >";
			}
		}
	
	}else{
		for(var i=1;i<=pageCount;i++){
	
			if(i == pageIndex){
				
				html += " <li class='active'><a>"+i+"</a></li >";
				
			}else{
				
				html += " <li><a href='#' name='pageIndex' value='"+i+"'>"+i+"</a></li >";					
			}		
		}
	}
	if(next>0){
		html += "<li ><a href='#'"+" name='pageIndex'"+"value='"+next+"'>下页</a></li>";
	}else{
		html +="<li class='disabled'><a class=''>下页</a></li>";
	}
	document.getElementById("pageNav").innerHTML = html;
	
	return html;
}

function removeEventHandler(target, type, func){
    if (window.removeEventListener)
        target.removeEventListener(type, func, false);
    else if (window.detachEvent)
        target.detachEvent("on" + type, func);
    else delete target["on" + type];
}


function megreAuthor(target,data){
	var req = createAjaxObj();
	var url = '/manage/book/author/pageNavJson';	
	var name =target.getAttribute('name');
	var value = target.value;
	
	//alert(value);
	data[name]= value;
	var values = $.toJSON(data);
	//alert(values);
	
	req.open("post",url,true);
	req.setRequestHeader("Content-Type", "application/json");
	req.send(values);
	req.onreadystatechange=function(){
		
    	if (req.readyState==4 && req.status==200){    		
    		
    		initAuthor(eval("("+req.responseText+")"));
    	}
	};
	
}

function megrebook(target,data){
	var req = createAjaxObj();
	var url = '/manage/book/pageNavJson';	
	var name =target.getAttribute('name');
	var value = target.value;
	
	data[name]= value;
	var values = $.toJSON(data);	
	req.open("post",url,true);							
	req.setRequestHeader("Content-Type", "application/json");			
	req.send(values);			
	req.onreadystatechange=function(){
		
    	if (req.readyState==4 && req.status==200){    		
    		
    		initbooks(eval("("+req.responseText+")"));
    	}
	};
	
}

function initAuthor(content){
	
	var view = "";
	
	for(var index =0;index < content.list.length;index++){
		
		view += "<label class='radio-inline'>"+"<input type='radio' name='author' value='"+content.list[index].id+"'>"+content.list[index].name+"</label>";
		
		if(index==5){
			
			view += "<br>";
			
		}
				 		
	}	
	document.getElementById("authorView").innerHTML=view;
	authorRadioEvent(document.getElementsByName('author'));
}

function initbooks(content){
	var view = "";	
	for(var index =0;index < content.list.length;index++){
		
		view += "<label class='radio-inline'>"+"<input type='radio' name='book' value='"+content.list[index].id+"'>"+content.list[index].bookTitle+"</label>";
		if(index==5){			
			view += "<br>";
		}				 		
	}	
	view += "<label class='radio-inline'>"+"<input type='radio' name='book' value='all'>"+'全部'+"</label>";
	document.getElementById("bookView").innerHTML=view;
	authorRadioEvent(document.getElementsByName('book'));
}

function authorRadioEvent(list){
	for(var index = 0;index<list.length;index++){
		
		list[index].onclick = function (){
			
			execAction(this,url,data);
		};
		
	}
}

