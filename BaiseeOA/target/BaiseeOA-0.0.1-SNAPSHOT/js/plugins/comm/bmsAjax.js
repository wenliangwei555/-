var coding_debug = false; // debug

var bmsAjaxOptions = {
	beforeSubmit : checkFormFileds, // 提交前的回调函数
	success : function() {
	}, // 提交后的回调函数
	url : "", // 默认是form的action， 如果申明，则会覆盖
	type : "post", // 默认是form的method（get or post），如果申明，则会覆盖
	dataType : "json", // html(默认), xml, script, json...接受服务端返回的类型
	clearForm : false, // 成功提交后，清除所有表单元素的值
	resetForm : false, // 成功提交后，重置所有表单元素的值
	timeout : 30000, // 限制请求的时间，当请求大于30秒后，跳出请求
	contentType : "application/x-www-form-urlencoded; charset=UTF-8",
	global : false,
	async : true,
	data : {},
	complete : function(XMLHttpRequest, textStatus) {
		/* layer.closeAll('loading'); */
		if (XMLHttpRequest.status == 200) {
			var executeStatus = XMLHttpRequest.getResponseHeader("executeStatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
			var url = XMLHttpRequest.getResponseHeader("url");//
			// var isLogin = XMLHttpRequest.getResponseHeader("isLogin");//
			if (executeStatus == "sessionTimeout") {
				// 如果超时就处理 ，指定要跳转的页面
				_alert("页面过期，请重新登录", function() {
					window.top.location.href = url;
				});
			} else if (executeStatus == "unallowed") {
				_alert("权限不足无法访问");
			} else if (executeStatus == "isRepeatSubmit") {
				_alert("请勿重复提交");
			} else if (executeStatus == "noResourceFound") {
				_alert("您访问的资源不存在");
			} else {
				if (XMLHttpRequest.responseText.indexOf("<") == 0) {
					var objE = document.createElement("div");
					objE.innerHTML = XMLHttpRequest.responseText;
					var code = $(objE).find("#bms_global_errorCode").val();
					if (code) {
						showErrorMsg(XMLHttpRequest.responseText);
					}
				}
			}
		} else if (XMLHttpRequest.statusText == 'timeout' || XMLHttpRequest.statusText == 'Gateway Time-out' || XMLHttpRequest.status == '504') {
			_alert("连接超时，请稍后再试");
		} else if (XMLHttpRequest.statusText == '404') {
			_alert("您访问的资源不存在");
		} else if (!XMLHttpRequest.responseText) {
			_alert("系统异常");
		} else {
			showErrorMsg(XMLHttpRequest.responseText);
		}
	}
};

var bms_formValidateOptions = null;

function setValidate(fromObj, options) {
	if (options) {
		bms_formValidateOptions = options;
		bms_formValidateOptions.errorElement = "span";
		bms_formValidateOptions.errorPlacement = function(error, element) {
			var s = element.parent().find("span[htmlFor='" + element.attr("id") + "']");
			if (s != null) {
				s.remove();
			}
			error.appendTo(element.parent().parent());
		};
		fromObj.validate(options);
	}
}

$(function() {
	// ajax前置条件
	$.ajaxSetup(bmsAjaxOptions);
	if($.fn.select2){
		$.fn.select2.defaults.set("language", "zh-CN");
	}
	
	$("form").submit(function() {// form表单统一AJAx提交
		return bmsAjaxForm_base($(this));
	});

	window.onbeforeunload = function() {
		showProccessBar();
	};
	if (!coding_debug) {
		$("a").click(function(e) {// 覆盖a标签点击方法
			if (e.stopPropagation) {
				e.stopPropagation();
				e.preventDefault();
			} else {
				e.cancelBubble = true;
				e.returnValue = false;
			}
			var clickFnc;
			var event = $._data(this, "events").click;// $(this).data('events');
			var url = this.href;
			if (this.onclick) {
				clickFnc = this.onclick;
			} else if (event.length > 1) {
				clickFnc = event[1].handler;
				e.stopImmediatePropagation();
			}
			if (clickFnc && typeof clickFnc === "function" && isUrl(url)) {
				if (clickFnc.call(this, e)) {
					locationHref(url);
				}
				return false;
			} else if (isUrl(url)) {
				locationHref(url);
				return false;
			} else {
				return false;
			}
			return true;
		});
	}
});

function isUrl(str) {
	if (!str || str.indexOf("javascript") == 0 || str.indexOf("#") == 0) {
		return false;
	}
	return true;
}

var bmsAjaxForm_base = function(formObj) {
	bmsAjaxForm(formObj, function(responseText, statusText) {
		processResult(responseText, statusText);
	}, {
		dataType : "html"
	});
	return false;
};

var bmsAjaxFormII = function(formObj, fn_success, options) {
	processOtpions(formObj, fn_success, options);
	bmsAjaxOptions.data = formObj.formSerialize();
	if (checkFormFileds(formObj.formToArray(), formObj, bmsAjaxOptions)) {
		$.ajax(bmsAjaxOptions);
	}
	return false;
};

var bmsAjaxForm = function(formObj, fn_success, options) {
	var fn_proxy = function(data) {
		fn_success(data);
		hideProccessBar();
	};
	processOtpions(formObj, fn_proxy, options);
	formObj.ajaxSubmit(bmsAjaxOptions);
	return false;
};

function checkFormFileds(formData, jqForm, options) {
	if ($(jqForm).valid()) {
		showProccessBar();
		return true;
	}
	return false;
}

var locationHref = function(url, fnc) {
	showProccessBar();
	if (coding_debug) {
		window.location.href = url;
	} else {
		$.ajax({
			url : url,
			type : "GET",
			dataType : 'html',
			success : function(responseText, statusText) {
				processResult(responseText, statusText, fnc);
			}
		});
	}
};

function processResult(responseText, statusText, fnc) {

	var code = $(responseText).find("#bms_global_errorCode").html();
	if (!code) {
		if (responseText) {
			if (fnc && typeof fnc === 'function') {
				fnc(responseText);
			} else {
				if ($dp)
					$dp = null;
				var newDoc = document.open("text/html", "replace");
				newDoc.write(responseText);
				newDoc.close();
			}
		}
	}
	hideProccessBar();
}

function processOtpions(formObj, fn_success, options) {
	if (options) {
		if (options.url) {
			bmsAjaxOptions.url = options.url;
		}
		if (options.type) {
			bmsAjaxOptions.type = options.type;
		}
		if (options.dataType) {
			bmsAjaxOptions.dataType = options.dataType;
		}
		if (options.clearForm) {
			bmsAjaxOptions.clearForm = options.clearForm;
		}
		if (options.resetForm) {
			bmsAjaxOptions.clearForm = options.resetForm;
		}
	}
	if (!bmsAjaxOptions.url)
		bmsAjaxOptions.url = formObj[0].action;
	bmsAjaxOptions.success = fn_success;
}

var showErrorMsg = function(responseText) {
	var objE = document.createElement("div");
	objE.innerHTML = responseText;
	var msg = $(objE).find("#bms_global_errorMsg").val();
	var code = $(objE).find("#bms_global_errorCode").val();
	var exceptionMsg = $(objE).find("#bms_global_exceptionMsg").val();
	
	var text = "<span style='float:left;'>【" + msg + "】</span>";
	if (coding_debug) {
		text += "<br/><span style='float:left;'>【异常信息】：" + exceptionMsg + "</span>";
	}
	layer.msg(text, {
		time : 5000,
	});
	hideProccessBar();
};

var showProccessBar = function() {
	// $(window.top.document).find("#_proccesBar").css("visibility", "visible");
	var top = document.documentElement.clientHeight / 2 - 70;
	$(".load-pop-box").css("top", top);
	var bar = $(window.top.document).find("#_proccesBar");
	if (bar.is(':hidden'))
		bar.css("display", "block");
};

var hideProccessBar = function() {
	// $(window.top.document).find("#_proccesBar").css("visibility", "hidden");
	var bar = $(window.top.document).find("#_proccesBar");
	if (!bar.is(':hidden'))
		bar.hide();
};

