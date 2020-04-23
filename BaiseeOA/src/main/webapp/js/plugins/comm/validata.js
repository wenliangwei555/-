function BmsValidata() {
}
var bmsValidata = new BmsValidata();
//校验日期
BmsValidata.prototype.compareDate = function(endDate,beginDate){
	if(endDate==null||beginDate==null){
		return false;
	}
	var OneMonth = beginDate.substring(5,beginDate.lastIndexOf ("-"));
  	var OneDay = beginDate.substring(beginDate.length,beginDate.lastIndexOf ("-")+1);
  	var OneYear = beginDate.substring(0,beginDate.indexOf ("-"));

  	var TwoMonth = endDate.substring(5,endDate.lastIndexOf ("-"));
  	var TwoDay = endDate.substring(endDate.length,endDate.lastIndexOf ("-")+1);
  	var TwoYear = endDate.substring(0,endDate.indexOf ("-"));

  if (Date.parse(OneMonth+"/"+OneDay+"/"+OneYear) <= Date.parse(TwoMonth+"/"+TwoDay+"/"+TwoYear)){
		return true;
	}else{
		return false;
	}
};
//校验邮箱
BmsValidata.prototype.isEmail = function(str) {
	var regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	return matchCheck(regex,str);
};
//校验IP
BmsValidata.prototype.isIP = function(str){
	var num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
	var regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
	return matchCheck(regex, str);
};
//校验url
BmsValidata.prototype.isUrl = function(str) {
	var regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
	return matchCheck(regex, str);
};
//校验电话号码
BmsValidata.prototype.isTelephone = function(str) {
	var regex = "^(\\d{3,4}-)?\\d{6,8}$";
	return matchCheck(regex, str);
};
//校验密码条件(字符与数据同时出现)
BmsValidata.prototype.isPassword = function(str) {
	var regex = "[A-Za-z]+[0-9]";
	return matchCheck(regex, str);
};
//校验密码长度(6-18位)
BmsValidata.prototype.isPasswLength = function(str) {
	var regex = "^\\d{6,18}$";
	return matchCheck(regex, str);
};
//校验邮政编号
BmsValidata.prototype.isPostalcode = function(str) {
	var regex = "^\\d{6}$";
	return matchCheck(regex, str);
};
//校验手机号码
BmsValidata.prototype.isHandset = function(str) {
	var regex = "^[1][0-9]{10}$";
	return matchCheck(regex, str);
};
//校验身份证号
BmsValidata.prototype.isIDcard = function(str) {
	var regex = "(^\\d{18}$)|(^\\d{15}$)";
	return matchCheck(regex, str);
};
//校验输入两位小数
BmsValidata.prototype.isDecimal = function(str) {
	var regex = "^[0-9]+(.[0-9]{2})?$";
	return matchCheck(regex, str);
};
//校验一年的12个月
BmsValidata.prototype.isMonth = function(str) {
	var regex = "^(0?[[1-9]|1[0-2])$";
	return matchCheck(regex, str);
};
//校验一个月的31天
BmsValidata.prototype.isDay = function(str) {
	var regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
	return matchCheck(regex, str);
};
//校验日期时间
BmsValidata.prototype.isDate = function(str) {
	var regex = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
	return matchCheck(regex, str);
};
//校验数字输入
BmsValidata.prototype.isNumber = function(str) {
	var regex = "^[0-9]*$";
	return matchCheck(regex, str);
};
//校验非零的正整数
BmsValidata.prototype.isIntNumber = function(str) {
	var regex = "^\\+?[1-9][0-9]*$";
	return matchCheck(regex, str);
};
//校验大写字母
BmsValidata.prototype.isUpChar = function(str) {
	var regex = "^[A-Z]+$";
	return matchCheck(regex, str);
};
//校验小写字母
BmsValidata.prototype.isLowChar = function(str) {
	var regex = "^[a-z]+$";
	return matchCheck(regex, str);
};
//校验输入字母
BmsValidata.prototype.isLetter = function(str) {
	var regex = "^[A-Za-z]+$";
	return matchCheck(regex, str);
};
//校验输入汉字
BmsValidata.prototype.isChinese = function(str) {
	var regex = "^[\u4e00-\u9fa5],{0,}$";
	return matchCheck(regex, str);
};
//校验输入字符串
BmsValidata.prototype.isLength = function(str) {
	var regex = "^.{8,}$";
	return matchCheck(regex, str);
};
//校验0-100整数
BmsValidata.prototype.isZeroToOneHundred = function(str) {
	var regex = "^(0|[1-9]\\d?|100)$";
	return matchCheck(regex, str);
};
//match
function matchCheck(regex, str){
	if(str.match(regex)==null){
		return false;
	}else{
		return true;
	}
}

