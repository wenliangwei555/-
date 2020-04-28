/*!
* @Authors:              *
* @System:icip              *
* @Version:v1.0.0             *
* @update:2015-11-12 10:45:18 *
*/

!function(){function t(t,i){t&&(this.indexobj=YW.addObj({checkedCls:"ks-checked"},i||{}),this.elm=$(t),this.input=this.elm.find("input[type=checkbox]"),this.input.css({display:"none"}),this.setStyle(this.input.get(0).checked),this.bind())}function i(t,i){if(t){this.indexobj=YW.addObj({inputCls:"",inputAll:"",inputAllCls:"ks-inputall",wait:!1},i||{}),this.elm=$(t),this.input=this.elm.find("input[type=checkbox]");for(var n=this.input;!n.is("."+this.indexobj.inputAllCls)&&n.get(0)!=document;)n=n.parent();this.o=n,this.bool=!0,this.chInputs=this.findInputs(),this.setStyle(this.input.get(0).checked),this.setChcked(this.chInputs,this.input.get(0).checked,1),this.bind()}}function n(t,i){t&&(this.indexobj=YW.addObj({checkedCls:"ks-radio"},i||{}),this.elm=$(t),this.input=this.elm.find("input"),this.input.css({display:"none"}),this.radioGroup=$("input[name='"+this.input.get(0).name+"']"),this.setStyle(this.input.get(0).checked),this.bind())}function e(t,i){t&&(this.indexobj=YW.addObj({buttonCls:"",hoverCls:"ks-hover",downCls:"ks-down"},i||{}),this.elm=$(t),this.elm.children().length>0&&(this.button=this.elm.find("."+this.indexobj.buttonCls)),this.button||(this.button=this.elm),this.bind())}function s(t,i){t&&(this.indexobj=YW.addObj({inputCls:"ks-input",hideCls:"ks-hide",prevBtnCls:"ks-up-btn",nextBtnCls:"ks-dn-btn"},i||{}),this.elm=$(t),this.input=this.elm.find("."+this.indexobj.inputCls),this.inputHide=this.elm.find("."+this.indexobj.hideCls),this.inputHide.css({display:"none"}),this.input.index=0,this.setval(0),this.bind())}t.prototype={setStyle:function(t){t?this.elm.addClass(this.indexobj.checkedCls):this.elm.removeClass(this.indexobj.checkedCls)},bind:function(){var t=this,i=t.input.get(0);this.elm.bind("click",function(n){n.target==i||i.readOnly||i.click(),t.setStyle(i.checked)})}},YW.extend("controls.Checkbox",t),i.prototype={findInputs:function(){var t,i;t=this.indexobj.inputCls?"."+this.indexobj.inputCls:"input[type=checkbox]",i=this.indexobj.inputAll?"."+this.indexobj.inputAll:"[checkboxall]";var n=this.o.find(i).not(this.input.parent());return!n||n&&!n.get(0)?n=this.o.find(t).not(this.input):(this.allInput=n.find("input[type=checkbox]"),n=this.o.find(t).not(this.input).not(this.allInput)),n},setStyle:function(t){t?this.elm.addClass("ks-checked"):this.elm.removeClass("ks-checked")},setChcked:function(t,i,n){(i===!0&&1===n||1!==n)&&(this.allInput?this.allInput.each(function(t,n){n.checked!=i&&n.click()}):t.get&&t.get().length>0&&t.each(function(t,n){n.checked!=i&&n.click()}))},inputAllVal:function(){for(var t=this.chInputs.get(),i=!0,n=0;n<t.length;n++)t[n].checked||(i=!1);this.setStyle(i),this.input.prop("checked",i)},forfindInputs:function(){var t=this;chndobj=this.findInputs(),(!this.chInputs&&chndobj&&chndobj.get(0)||this.chInputs&&this.chInputs.get().length!=chndobj&&chndobj.get().length)&&(this.chInputs=chndobj,this.chInputs.parent().bind("click",function(){t.inputAllVal()}))},bind:function(){var t=this,i=t.input.get(0);this.elm.bind("click",function(n){t.indexobj.wait&&(t.bool=!1,t.forfindInputs()),n.target!=i?i.click():(t.setStyle(i.checked),t.setChcked(t.chInputs,i.checked))}),!this.indexobj.wait&&this.chInputs&&this.chInputs.get(0)&&this.chInputs.parent().bind("click",function(){t.inputAllVal()})}},YW.extend("controls.CheckboxAll",i),n.prototype={setStyle:function(t){t?this.elm.addClass(this.indexobj.checkedCls):this.elm.removeClass(this.indexobj.checkedCls)},bind:function(){var t=this,i=t.input.get(0);this.elm.bind("click",function(n){t.radioGroup.prop("checked",!1),t.radioGroup.parent().removeClass(t.indexobj.checkedCls),i.checked?i.checked=!1:i.checked=!0,t.setStyle(i.checked)})}},YW.extend("controls.Radio",n),e.prototype={setStyle:function(t,i,n){t?n.addClass(i):n.removeClass(i)},bind:function(){var t=this;this.button.bind(YW.mous.mousedown,function(){t.setStyle(!0,t.indexobj.downCls,$(this))}).bind(YW.mous.mouseup,function(){t.setStyle(!1,t.indexobj.downCls,$(this))}),this.button.bind("mouseover",function(){t.setStyle(!0,t.indexobj.hoverCls,$(this))}).bind("mouseout",function(){t.setStyle(!1,t.indexobj.hoverCls,$(this))}),$(document).bind("mouseup",function(){t.setStyle(!1,t.indexobj.downCls,t.button)})}},YW.extend("controls.Button",e),s.prototype={calc:function(t,i){var n;return n=t>=0?t+1*i:0,0>n&&(n=0),n},setval:function(t,i){i?this.input.index=i:this.input.index=this.calc(this.input.index,t);var n="";n=0==this.input.index?"全部":this.input.index,this.inputHide.val(this.input.index),this.input.val(n)},bind:function(){var t=this;this.input.bind("change",function(){var i=this.value.replace(/\s/g,"");this.value&&/^[0-9]+$/.test(i)?t.setval(0,i):t.setval(0)}),this.elm.find("."+this.indexobj.prevBtnCls).bind("click",function(){t.setval(1)}),this.elm.find("."+this.indexobj.nextBtnCls).bind("click",function(){t.setval(-1)})}},YW.extend("controls.Adjustment",s)}();

/*!----------   2015-11-12 10:45:18   ----------*/