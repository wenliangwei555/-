		   var code;
		   var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};

			function setCheck() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
				py = $("#py").attr("checked")? "p":"",
				sy = $("#sy").attr("checked")? "s":"",
				pn = $("#pn").attr("checked")? "p":"",
				sn = $("#sn").attr("checked")? "s":"",
				type = { "Y":py + sy, "N":pn + sn};
				zTree.setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };//type;
				showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
			}
			function showCode(str) {
				if (!code) code = $("#code");
				code.empty();
				code.append("<li>"+str+"</li>");
			}