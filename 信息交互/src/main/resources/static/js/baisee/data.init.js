var dataTable = (function(window) {
        var dataTable = function(idField,url,columns) {
            return new dataTable.fn.init(idField,url,columns);
        }

        dataTable.fn = dataTable.prototype = {
            constructor: dataTable,
            init: function(idField,url,columns) {
                this.url = url;
                this.columns = columns;
                this.idField = idField;
                this.init = function() {
                    this.init_data();
                }
            },
            init_data: function() {
            	var t = $("#table_server").bootstrapTable(
						{
							url : this.url,
							dataField : 'list',
							method : 'POST',
							contentType : "application/x-www-form-urlencoded",
							dataType : "json",
							striped : true,
							undefinedText : "空",
							pagination : true, 
							showToggle : "true",
							showColumns : "true",
							pageNumber : 1,
							pageSize : 10,
							pageList : [ 5, 10, 20, 40 ], 
							paginationPreText : '‹',
    						paginationNextText: '›',
							search : false, 
							toolbar : "#toolbar",
							data_local : "zh-US",
							sidePagination : "server", 
							queryParams : function(params) {//自定义参数，这里的参数是传给后台的，我这是是分页用的
								var param = {};
								$('#query-form').find('[name]').each(function() {
									var value = $(this).val();
									if (value != '') {
										param[$(this).attr('name')] = value;
									}
								});
								param['pageSize'] = this.pageSize;   //页面大小
							    param['pageNumber'] = this.pageNumber;  
							    param['limit'] = this.limit; 
							    param['offset'] = this.offset; 
								//页码
								return param;
							},
							idField : this.idField,
							columns : this.columns
							});
            		}
            }

		dataTable.fn.init.prototype = dataTable.fn;

        return dataTable;
})();