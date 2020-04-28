$.validator.addMethod("example", function(value, element, params) {//例子
		return this.optional(element) || BmsValidata.prototype.isEmail(value);//正则匹配数据 validate.js 中的正则校验方法
}, "请输入正确的邮箱");//默认输出信息

$.validator.addMethod("isTelephone", function(value, element, params) {//是否为固话地址
	return this.optional(element) || BmsValidata.prototype.isTelephone(value);//正则匹配数据 validate.js 中的正则校验方法
}, "请输入正确的固话号码，如：020-87654321");//默认输出信息

$.validator.addMethod("isMobile", function(value, element, params) {//是否手机号码
	return this.optional(element) || BmsValidata.prototype.isHandset(value);//正则匹配数据 validate.js 中的正则校验方法
}, "请输入手机号码，11位");//默认输出信息

$.validator.addMethod("beforeDate", function(value, element, params) {//小于日期
	return this.optional(element) || !(strToDate(value) < strToDate(params));
}, "日期不能小于当天");//默认输出信息

$.validator.addMethod("afterDate", function(value, element, params) {//超过日期
	return this.optional(element) || !(strToDate(value) > strToDate(params));
}, "日期不能大于当天");//默认输出信息

$.validator.addMethod("isBigger", function(value, element, params) {//是否比参数大
	return parseInt(value,10) >= parseInt(params,10);
}, "值太小");//默认输出信息