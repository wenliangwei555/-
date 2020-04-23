/**
 * Created by zhaoran on 2018/12/8.
 */
var Util = {
    /**
     * 1、判断非空
     * 2、获取字符串真实长度 汉字算两位
     * 3、判断参数类型
     * 4、日期格式化
     * 5、通过key获取url中的参数值
     * 6、设置cookie值
     * 7、获取cookie值
     * 8、删除cookie
     * 9、HTML编码
     * 10、HTML解码
     * 11、光标停在文字的后面，文本框获得焦点时调用
     * 12、生成一个新的GUID
     */
    /**
     * 判断非空
     * @param obj
     * @returns {boolean}
     */
    isEmpty: function (obj) {
        if (obj == undefined || obj == null || new String(obj).trim() == '') {
            return true;
        } else {
            return false;
        }
    },
 
    /**
     * 获取字符串真实长度 汉字算两位
     * @param str
     * @returns {number}
     */
    getRealLength: function (str) {
        return isEmpty(str) ? 0 : str.replace(/[^\x00-\xff]/g, "**").length;
    },
 
    /**
     * 判断参数类型
     * @param obj
     * @returns {string}
     */
    type: function (obj) {
        var class2type = {},
            toString = Object.prototype.toString;
        (function () {
            var typeArr = "Boolean,Number,String,Function,Array,Date,RegExp,Object".split(",");
            for (var i = 0; i < typeArr.length; i++) {
                var name = typeArr[i];
                class2type["[object " + name + "]"] = name.toLowerCase();
            }
        })()
        return obj == null ? String(obj) : class2type[toString.call(obj)] || "object";
    },
 
    /**
     * 日期格式化
     * @param date 日期对象
     * @param formatStr 格式化字符串 如YYYY-MM-dd hh:mm:ss
     * @returns {*}
     */
    dateFormat: function (date, formatStr) {
        var str = formatStr;
        var Week = ['日', '一', '二', '三', '四', '五', '六'];
        str = str.replace(/yyyy|YYYY/, this.getFullYear());
        str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));
        str = str.replace(/MM/, (this.getMonth() + 1) > 9 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
        str = str.replace(/M/g, (this.getMonth() + 1));
        str = str.replace(/w|W/g, Week[this.getDay()]);
        str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
        str = str.replace(/d|D/g, this.getDate());
        str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
        str = str.replace(/h|H/g, this.getHours());
        str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
        str = str.replace(/m/g, this.getMinutes());
        str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
        str = str.replace(/s|S/g, this.getSeconds());
        return str
    },
 
    /**
     * 通过key获取url中的参数值
     * @param key
     * @returns {null}
     */
    getQueryString: function (key) {
        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURIComponent(r[2]);
        return null;
    },
 
    /**
     * 设置cookie值
     * @param name 名称
     * @param value 名称对应值
     * @param Hours 过期时间
     */
    setCookie: function (name, value, Hours) {
        var d = new Date();
        var offset = 8;
        var utc = d.getTime() + (d.getTimezoneOffset() * 60000);
        var nd = utc + (3600000 * offset);
        var exp = new Date(nd);
        exp.setTime(exp.getTime() + Hours * 60 * 60 * 1000);
        document.cookie = name + "=" + encodeURIComponent(value) + ";path=/;expires=" + exp.toGMTString() + ";domain=sicd.com;";
    },
 
    /**
     * 获取cookie值
     * @param name cookie名
     * @returns {*}
     */
    getCookie: function (name) {
        var arr = document.cookie
            .match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null)
            return decodeURIComponent(arr[2]);
        return null;
    },
 
    /**
     * 删除cookie
     * @param name cookie name
     */
    delCookie: function (name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null)
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    },
 
    /**
     * HTML编码
     * @param str 待编码字符串
     * @returns {string}
     */
   /* html_encode: function (str) {
        var s = "";
        if (str.length == 0) return "";
        s = str.replace(/&/g, ">");
        s = s.replace(/</g, "<");
        s = s.replace(/>/g, ">");
        s = s.replace(/ /g, " ");
        s = s.replace(/\'/g, "'");
        s = s.replace(/\"/g, """);
        s = s.replace(/\n/g, "<br>");
        return s;
    },*/
 
    /**
     * HTML解码
     * @param str 待解码的字符串
     * @returns {string}
     */
    html_decode: function (str) {
        var s = "";
        if (str.length == 0) return "";
        s = str.replace(/>/g, "&");
        s = s.replace(/</g, "<");
        s = s.replace(/>/g, ">");
        s = s.replace(/ /g, " ");
        s = s.replace(/'/g, "\'");
        s = s.replace(/"/g, "\"");
        s = s.replace(/<br>/g, "\n");
        return s;
    },
 
    /**
     * 光标停在文字的后面，文本框获得焦点时调用
     */
    focusLast: function () {
        var e = event.srcElement;
        var r = e.createTextRange();
        r.moveStart('character', e.value.length);
        r.collapse(true);
        r.select();
    },
 
    /**
     * 生成一个新的GUID
     * @return {string} 数据类型
     * @method nuid
     */
    nuid: function () {
        return new Date().getTime().toString(36);
    }
}