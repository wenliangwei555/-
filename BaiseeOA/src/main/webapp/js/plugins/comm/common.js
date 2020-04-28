/**
 * 公共js
 */
$.format = function(source, params) {
	if (arguments.length == 1)
		return function() {
			var args = $.makeArray(arguments);
			args.unshift(source);
			return $.format.apply(this, args);
		};
	if (arguments.length > 2 && params.constructor != Array) {
		params = $.makeArray(arguments).slice(1);
	}
	if (params.constructor != Array) {
		params = [ params ];
	}
	$.each(params, function(i, n) {
		source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
	});
	return source;
};
/**
 * pageType 1.首页2.上一页3.下一页4.末页 isgo
 */
/**
 * pageType 1.首页2.上一页3.下一页4.末页 isgo
 */
function go(pageNum) {
	if (!parseInt(pageNum) && parseInt(pageNum) != 0) {
		_alert("页数必须为数字！");
		return false;
	}
	var pagenumber = parseInt(pageNum);
	if (!pagenumber || pagenumber <= 0 || pagenumber > $("#_pages").text()) {
		_alert("请输入正确的页码");
		return false;
	}

	$("#pagenumber").val(pageNum);
	$("#frm").submit();
}
/**
 * 是否为空
 * 
 * @param value
 * @returns {Boolean}
 */
function Is_Null(value) {
	var j = 0;
	var Text = "";
	Text = value;
	if (Text == undefined) {
		_alert('出错了');
		return;
	}
	if (Text.length) {
		for (var i = 0; i < Text.length; i++) {
			if (Text.charAt(i) != " ")
				j = j + 1;
		}

		if (j == 0) {
			Ret = true;
		} else {
			Ret = false;
		}
	} else
		Ret = true;
	return (Ret);
}

/**
 * 全选操作
 */
function selectAllCheckBox(obj, obj2, o) {
	var flag = '';
	if (obj == null) { // 无记录

		_alert("此页面没有可选择记录!");
		return false;
	} else if (obj.length == undefined) { // 只有一条记录

		if (obj.disabled == true) {
			_alert("此页面没有可选择记录!");
			return false;
		}
		obj.checked = obj2.checked;
	} else {// 存在多条记录
		for (var i = 0; i < obj.length; i++) {
			if (obj[i].disabled == false) {
				if (o.checked) {
					$(obj[i]).parent().addClass("ks-checked");
				} else {
					$(obj[i]).parent().removeClass("ks-checked");
				}
				$(obj[i]).prop("checked", o.checked);
				flag = '1';
			}
		}
		if (flag == '') {
			_alert("此页面没有可选择记录!");
			return false;
		}
	}
}

/**
 * 修改时调用方法（试用checkbox选择弹出窗口修改）
 */
function getIdByCheckBox(checkboxId) {
	obj = document.forms[0];
	var checks = obj.elements[checkboxId];
	if (!checks) {
		_alert("没有可选项！");
		return false;
	}
	if (!isSelectCheckbox(checks)) {
		_alert("请选择您要操作的条目！");
		return false;
	}
	if (checks.length == undefined) {
		return checks.value;
	} else {
		var num = 0;
		var returnID;
		for (var i = 0; i < checks.length; i++) {
			if (checks[i].checked) {
				returnID = checks[i].value;
				num++;
			}
		}
		if (num > 1) {
			_alert("一次只能处理一条记录！");
			return false;
		} else {
			return returnID;
		}
	}
}

// 清空表单数据
function clears() {
	$(":input").not(":button,:hidden,:submit").not("#isShow").val("");
}

/*
 * 函数名：chkSames 功能介绍：比较字段跟传入值是否相等 参数说明：要检查的字段id1，textValue，消息提醒message true：没有
 * false：有
 */
function chkSames(id, textValue, message) {
	var text = $("#" + id).val();
	if (text == textValue) {
		return false;
	} else {
		_alert(message, id, true);
		return true;
	}
}

/*
 * 函数名：chkNull 功能介绍：验证是否为空 参数说明：要检查的字段id，消息提醒message true：没有 false：有
 */
function chkNull(id, message) {
	var text = $("#" + id).val();
	if (Is_Null(text)) {
		_alert(message, id, true);
		return true;
	}
	return false;
}
/*
 * 函数名：chkEmail 功能介绍：检查是否为Email 参数说明：要检查的字段id，消息提醒message true：没有 false：有
 */
function chkEmail(id, message) {
	var text = $("#" + id).val();
	if (chkemail(text, message) == 0) {
		jbox.alert(message, id, true);
		return true;
	} else {
		return false;
	}
}

// 除去字符串中指定的字符串
// example: str="12,34,56.00",symbol=",", replaceAll(str,symbol)=123456.00
function replaceAll(str, symbol) {

	while (str.indexOf(symbol) != -1) {
		str = str.replace(symbol, "");
	}
	return str;
}

// 比较两个日期的大小

// 目前只支持年－月－日这样的格式
function compareDate(endDate, beginDate) {
	var OneMonth = beginDate.substring(5, beginDate.lastIndexOf("-"));
	var OneDay = beginDate.substring(beginDate.length, beginDate.lastIndexOf("-") + 1);
	var OneYear = beginDate.substring(0, beginDate.indexOf("-"));

	var TwoMonth = endDate.substring(5, endDate.lastIndexOf("-"));
	var TwoDay = endDate.substring(endDate.length, endDate.lastIndexOf("-") + 1);
	var TwoYear = endDate.substring(0, endDate.indexOf("-"));

	if (Date.parse(OneMonth + "/" + OneDay + "/" + OneYear) <= Date.parse(TwoMonth + "/" + TwoDay + "/" + TwoYear)) {
		return true;
	} else {
		return false;
	}
}
/**
 * 删除操作 url 跳转所用url formname form表单的name
 */
function del(formId, _url, msg) {
	if (!formId || typeof _url === "String")
		return;
	if (!_url || typeof _url === "String")
		return;
	if (!$("input[name='ids']")) {
		_alert("没有可选项");
		return false;
	}
	if ($("#" + formId + " input[name='ids']:checked").length <= 0) {
		_alert("请选择您要删除的记录");
		return false;
	}

	if (!msg) msg = '是否删除所选记录';
	_confirm("<span style='color:#FE6600'>" + msg + "</span>", function() {
		var form = document.getElementById(formId);
		form.action = _url;
		var oinput = document.createElement("input");
		oinput.setAttribute('type', 'submit');  //定义类型是文本输入
		oinput.setAttribute('id', 'bsubmit');  //定义类型是文本输入
		document.getElementById(formId).appendChild(oinput ); 
		$("#bsubmit").click();
	});
}

/**
 * 校验多选框的选择
 */
function isSelectCheckbox(ids) {
	if (ids.length == "-1" || typeof (ids.length) == "undefined")
		return ids.checked;
	for (i = 0; i < ids.length; i++) {
		if (ids[i].checked) {
			return true;
		}
	}
	return false;
}

// 输入框错误提示样式
var inputError = function(id) {
	clearTimeout(inputError.timer);
	var num = 0;
	var fn = function() {
		inputError.timer = setTimeout(function() {
			if ($("#" + id).css("borderColor") == '#3aa1f2') {
				$("#" + id).css("borderColor", "#fe6600");
			} else {
				$("#" + id).css("borderColor", "#3aa1f2");
			}
			if (num === 5) {
				$("#" + id).css("borderColor", "#3aa1f2");
				$("#" + id).blur(function() {
					$("#" + id).css("borderColor", "#fff");
				})
				$("#" + id).focusin(function() {
					$("#" + id).css("borderColor", "#3aa1f2");
				})
				$("#" + id).mouseover(function() {
					if ($("#" + id).css("borderColor") == '#3aa1f2') {
						return;
					}
					$("#" + id).css("borderColor", "#B2DBF8");
				})
				$("#" + id).mouseout(function() {
					if ($("#" + id).css("borderColor") == '#3aa1f2') {
						return;
					}
					$("#" + id).css("borderColor", "#fff");
				})
			} else {
				fn(num++);
			}
			;
		}, 150);
	};
	fn();
};

/** fileUrl - 文件下载地址， fileDisplayName 下载文件的显示名称 */
var downloadFile = function(action, fileUrl, fileDisplayName) {
	if (!action) {
		action = 'fileDownload.ht';
	}
	$.ajax({
		url : action,
		data : {
			fileUrl : fileUrl,
			fileName : fileDisplayName
		},
		success : function(data) {
			if (data.code == 1) {
				_alert(data.message);
			} else {
				window.location.href = action + "?fileUrl=" + fileUrl + "&fileName=" + fileDisplayName;
			}
		}
	});
	return false;
};

/**
 * 
 * @Title: checkReg 传入正则表达式 验证结果是否满足
 * @Description: TODO
 * @param
 * @param reg
 *            正则表达式
 * @param
 * @param msg
 *            提醒信息
 * @param
 * @param checkval
 *            待验证的值
 * @return void 返回类型
 * @throws
 */
function checkReg(reg, msg, checkval) {
	if (msg == null || msg == '' || msg == undefined) {
		msg = '参数检验不正确';
	}
	if (!reg.test(checkval)) {
		_alert(msg);
		return true;
	}
	return false;
}

function _alert(msg, fnc) {
	if (fnc) {
		layer.msg(msg, {
			time : 2000
		}, fnc);
	} else {
		layer.msg(msg, {
			time : 2000
		});
	}
	hideProccessBar();
}

function _confirm(msg, success) {
	var fnc = function(index) {
		success();
		layer.close(index);
	};
	layer.confirm(msg, {
		icon : 3,
		title : '温馨提示'
	}, fnc);
}
/**
 * 
 * @param msg
 * @param idOrClass
 *            #{id} 或者 .{class}
 * @return
 */
function _tip(msg, idOrClass) {
	layer.tips(msg, idOrClass);
}

function showUploadTip(url, buttonDiv) {
	if (url) {
		$("#" + buttonDiv + "-button").append("<img src='"  + url + "'/>");
		/*$("#" + buttonDiv + "-button").css({
			'background' : 'url("' + url + '") no-repeat center center'
		});*/
	}
}
/**
 * 打开一个指定大小的窗体
 */
var jbox = {
	open : function(url, title, iHeight, iWidth, id) {
		if (id == null || id == '' || id == undefined) {
			id = 'openId';
		}
		if (document.getElementById("query") != undefined) {
			setData("query", $("#query"));// query为列表查询按钮的id值，目的是用他来对按钮触发onclick操作
			removeData("reflush");// 每次打开时清除共享区域reflush对象
		}
		layer.open({
			type : 2,
			title : title,
			shadeClose : true,
			shade : false,
			maxmin : true, // 开启最大化最小化按钮
			area : [ '850px', '650px' ],
			content : url,
			end : function(){hideProccessBar();}
		});
	}
};

function showDialog(obj) {
	$("#" + obj).css("display", "block");
}

function closeDialog(obj) {
	$("#" + obj).clearForm(true);
	$("#" + obj).css("display", "none");
}

function strToDate(value) {
	var curDate;
	if (value.indexOf("-") > -1) {
		var TwoMonth = value.substring(5, value.lastIndexOf("-"));
		var TwoDay = value.substring(value.length, value.lastIndexOf("-") + 1);
		var TwoYear = value.substring(0, value.indexOf("-"));
		curDate = Date.parse(TwoMonth + "/" + TwoDay + "/" + TwoYear);
	} else {
		var year = value.substring(0, 4);
		var month = value.substring(4, 6);
		var day = Math.round(value.substring(6, 8)) + 1;
		curDate = Date.parse(month + "/" + day + "/" + year);
	}
	return curDate;
}

function dateToStr(date) {
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();
	return year + "-" + month + "-" + day;
}

function changePwd(obj,frm){
	var _str = $("input[name='oldPassword']").val();
	var _str1 =$("input[name='newPassword']").val();
	var _str2 =$("input[name='checkPassword']").val();
	if(_str==null||_str==""){
		_alert("请输入原密码");
		return false;
	}else
	if(!checkPwdLength(_str1)){
		_alert("请确认，新密码长度6-20位");
		return false;
	}else	
	if(!checkPwdLength(_str2)){
		_alert("请确认，确认密码长度6-20位");
		return false;
	}else
	if(_str1 != _str2){
		_alert("两次输入的密码不一致");
		return false;
	}else{
		
		var request = new XMLHttpRequest();
		
		request.open('get', 'user/changePwd.ht?'+"oldPassword="+_str +"&newPassword="+_str1+ "&checkPassword="+_str2, true);
		request.send();
		request.onreadystatechange= function (){
			if(request.readyState == 4){
				if(request.status == 200){
					var dates = request.responseText;
					if(dates == '4'){
						layer.confirm('您修改的密码已经成功,请重新登录!', {
					            btn : [ '确定'], //按钮
					            cancel : function (){
					            	window.location.href = "login.html";
						            return true;
					            }
					        }, function(index) {
					            //此处请求后台程序，下方是成功后的前台处理……
					           var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: false});//0代表加载的风格，支持0-2
					        	
					            window.location.href = "login.html";
					            return true;
					        });
						
						
						 
						
						
						
					}
					if(dates == '3'){
						_alert("修改失败!");
						return false;
					}
					if(dates == '2'){
						_alert("新密码不能跟原密码一致!");
						return false;
					}
					if(dates == '1'){
						_alert("原密码输入错误!");
						return false;
					}
				}
			}
		}
	}
	
}


function formatDate(longDate){
    var date = new Date(longDate);
    var yyyy = date.getFullYear();
    var mm = date.getMonth() + 1;
    if (mm < 10) {
        mm = "0" + mm;
    }
    var dd = date.getDate();
    if (dd < 10) {
        dd = "0" + dd;
    }
    var hh = date.getHours();
    if (hh < 10) {
        hh = "0" + hh;
    }
    var mmm = date.getMinutes();
    if (mmm < 10) {
        mmm = "0" + mmm;
    }
    var ss = date.getSeconds();
    if (ss < 10) {
    	ss = "0" + ss;
    }
    return yyyy + "-" + mm + "-" + dd+" "+hh+":"+mmm+":"+ss;
}


function checkPwdLength(str){
	if(str==null||str=="")
		return false;
	else{
		if(str.length<6||str.length>20)
			return false;
		else
			return true;
	}
}





