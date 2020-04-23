/******************************
* @Authors:              *
* @System:icip              *
* @Version:v1.0.0             *
* @update:2015-10-28 09:50:24 *
*******************************/

!function(){function t(t,n){if(t){this.indexobj=YW.addObj({printCls:"ks-print"},n||{}),this.bdhtml=window.document.body.innerHTML;var i=$(t);i.children().addClass(this.indexobj.printCls);var d=i.html();window.document.body.innerHTML=d}}t.prototype={print:function(){window.print(),window.document.body.innerHTML=this.bdhtml}},YW.extend("widget.PreView",t)}(),$(window).bind("load",function(){!function(){for(var t=$(".JsW").get(),n=0;n<t.length;n++)switch($(t[n]).attr("data-widget-type")){case"print":var i=new YW.widget.PreView(t[n],YW.parse($(t[n]).attr("data-widget-config")));setTimeout(function(){i.print()},0)}}()});

/*----------   2015-10-28 09:50:24   ----------*/