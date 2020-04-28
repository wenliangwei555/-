function getData(){
	$.ajax({
        type: "POST",
        url: "/tree/list.ht",
        data: "",
        datatype: 'text',
        success: function(data) {
        	var r = 0;
        	var da=[];
        	var resulrt= [];
        	var partantId = ''; //父子关系
        	result = data.tree;
        	var name = '银湾集团总公司';
        	da.push({'id':'root','ming':name,'link':i,'level':0,'pid':0});
        	for(var i=1;i<result.length;i++){
        		partantId = result[i].subCompanyNum;
        		if(partantId != null && partantId !=''){ //测试
        			da.push({'id':result[i].pcid,'ming':result[i].companyName,'link':i,'level':1,'pid':''});//一级
        		}else{
        			da.push({'id':result[i].pcid,'ming':result[i].companyName,'link':i,'level':2,'pid':result[i].subCompanyNum});//二级
        		}
        	}	
        	ME.st('menu',da);
        },
        error: function(err) {
            alert("网络异常，请联系管理员！");
        }
    }); 
}


