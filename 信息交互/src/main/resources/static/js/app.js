/**
 * 字符串缓冲区
 */
function StringBuffer(){
	var $this = this;
	$this.arr = [];
	$this.append=function(str){
		$this.arr.push(str);
		return $this;
	};
	$this.toString=function(){
		var len = $this.arr.length;
		var str='';
		for(var i = 0; i < len; i++){
			str += $this.arr[i];
		}
		return str;
	};
}

function DateFormat(dt){
	var $this = this;
	$this.date = dt || new Date();
	// 将 Date 转化为指定格式的String
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	// 例子： 
	// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
	// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
    $this.format = function(fmt){
    	var o = {
	        "M+": $this.date.getMonth() + 1, //月份 
	        "d+": $this.date.getDate(), //日 
	        "h+": $this.date.getHours(), //小时 
	        "m+": $this.date.getMinutes(), //分 
	        "s+": $this.date.getSeconds(), //秒 
	        "q+": Math.floor(($this.date.getMonth() + 3) / 3), //季度 
	        "S": $this.date.getMilliseconds() //毫秒 
	    };
    	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, ($this.date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };
    $this.setDate = function(dt){
		$this.date = dt;
	};
}

/**
 * 缩写字符
 * @param str{String}
 * @param len{Int}
 * @returns
 */
function abbr(str,len){
	var _len = str.length;
	if(_len < len){
		return str
	}
	return str.substr(0,len);
}

App = {
	sess_key:'_sess_quser',
	isEmpty:function(str){
		if(!str || str === '' || str.length === 0){
			return true;
		}
		return false;
	},
	redirect:function(url){
		window.location.href=url;
	},
	putAttr:function(k,v){
		window.localStorage.setItem(k,v);
	},
	getAttr:function(k){
		return window.localStorage.getItem(k);
	},
	form2json:function(selector) {  
	   var o = {};  
	   var a = $(selector).serializeArray();  
	   $.each(a, function() {  
	       if (o[this.name]) {  
	           if (!o[this.name].push) {  
	               o[this.name] = [o[this.name]];  
	           }  
	           o[this.name].push(this.value || '');  
	       } else {  
	           o[this.name] = this.value || '';  
	       }  
	   });  
	   return o;  
	},
	json2form:function(frmid,json){
		var _frm = document.getElementById(frmid);
		var elems = _frm.elements;
		var len = elems.length;
		for(var i=0; i<len; i++){
			var elem=elems[i];
			if(App._isbutton(elem)){
				continue;
			}else if(App._isRadio(elem)){
				
			}else if(App._isCheckbox(elem)){
				
			}else{
				var val = json[elem.name];
				if(typeof(val)=='undefined'){
					elem.value='';
				}else{
					elem.value=val;
				}
			}
		}
	},
	_checkElemType:function(elem,type){
		if(App.isEmpty(type)){
			alert('未指定要校验的表单元素名称');
			return false;
		}
		var tag=elem.tagName;
		var ltag = tag.toLowerCase();
		var ltype = type.toLowerCase();
		if(ltag===ltype){
			return true;
		} 
		return false;
	},
	_isInput:function(elem){
		return App._checkElemType(elem,'input');
	},
	_checkInputType:function(elem,type){
		if(App.isEmpty(type)){
			alert('未指定要校验的Input类型[type]');
			return false;
		}
		var etype = elem.type;
		var letype = etype.toLowerCase();
		var ltype = type.toLowerCase();
		if(letype===ltype){
			return true;
		}
		return false;
	},
	_isbutton:function(elem){
		return App._checkElemType(elem,'button') || App._isInputButton(elem);
	},
	_isRadio:function(elem){
		if(App._isInput(elem)){
			var type = elem.type;
			return App._checkInputType(elem,'radio');
		}
		return false;
	},
	_isCheckbox:function(elem){
		if(App._isInput(elem)){
			var type = elem.type;
			return App._checkInputType(elem,'checkbox');
		}
		return false;
	},
	_isInputButton:function(elem){
		if(App._isInput(elem)){
			var type = elem.type;
			return App._checkInputType(elem,'button');
		}
		return false;
	},
	get:function(url,data,success,error){
		$.ajax({
			url:url,
			data:data,
			type:'get',
			success:function(resp){
				if(success && jQuery.isFunction(success)){
					success(resp);
				};
			},
			error:function(resp){
				if(error && jQuery.isFunction(error)){
					error(resp);
				};
			}
		});
	},
	post:function(url,data,success,error){
		$.ajax({
			url:url,
			type:'POST',
			contentType:'application/json;charset=utf-8',
			data:JSON.stringify(data),
			dataType: "json", 
			success:function(resp){
				if(success && jQuery.isFunction(success)){
					success(resp);
				};
			},
			error:function(resp){
				if(error && jQuery.isFunction(error)){
					error(resp);
				};
			}
		});
	}
};