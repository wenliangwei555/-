 var deleter = (function(window) {
        var deleter = function(idField,url) {
            return new deleter.fn.init(idField,url);
        }

        deleter.fn = deleter.prototype = {
            constructor: deleter,
            init: function(idField,url) {
                this.idField = idField;
                this.url = url;
                this.init = function() {
                    this.dodelete();
                }
            },
            dodelete: function() {
            	var idField = this.idField;
            	var url = this.url;
            	swal({
        			title : "您确定要删除这条信息吗",
        			text : "删除后将无法恢复，请谨慎操作！",
        			type : "warning",
        			showCancelButton : true,
        			confirmButtonColor : "#DD6B55",
        			confirmButtonText : "删除",
        			closeOnConfirm : false
        		}, function() {
        			var rows = $("#table_server").bootstrapTable("getSelections");
        			var ids = [];
        			var len = rows.length;
        			var str = idField;
        			for (var i = 0; i < len; i++) {
        				ids.push(rows[i][str]);
        			}
        			if(ids.length==0){
        				swal("删除失败！", "请选择要删除的信息！", "warning");
        				return false;
        			}
        			$.ajax({
        				url : url,
        				dataType : "json",
        				traditional : true,//属性在这里设置
        				method : "post",
        				data : {
        					"ids" : ids
        				},
        				success : function(data) {
        					swal("删除成功！", "您已经永久删除了这条信息。", "success");
        					$("#table_server").bootstrapTable("refresh");
        				},
        				error : function() {
        					swal("删除失败！", "请重试。", "error");
        				}
        			});
        		})
            }
        }

        deleter.fn.init.prototype = deleter.fn;

        return deleter;
    })();