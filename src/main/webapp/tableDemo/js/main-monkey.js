
/**
*
*
**bootstrap table的辅助服务，进一步封装，为页面表格展示提供方便
*
*
**/


//处理列配置数组与唯一标志位列
var  dealColumnsAndUniqueId  =  function(uniqueId , columnList){
	//如果不存在，直接返回
	if( !columnList || !(columnList instanceof Array) || columnList.length <1){
		console.error("列初始化异常");
		return ;
	}
	//如果每一行唯一标识不存在，则将第一列数据设置为唯一标识
	if(!uniqueId){
		var info = columnList[0];
		uniqueId = info.field;
	}
};

//配置请求参数
var queryParams = function(){
    var param = {
	    pageindex : this.pageNumber,
	    pageSize : this.pageSize
    }
    return param；
};

//对数据进行预处理
var dataHandler = function(data){

	if(data){
		return {
	        "rows" : res.result,
	        "total" : res.totalCount
	        };
    }else {
	    return {
	        "rows" : [],
	        "total" : 0
	    };
    }
};



//列表渲染对象
var TableInstance  =  function(){
	//配置默认的表格加载参数
	var defaultOption = {
		method: 'get', 						//请求方式，默认使用get请求
		striped: true,                      //是否显示行间隔色
	    cache: false,                       //是否使用缓存，默认为true，为了保证数据一致性，禁用此功能
	    pagination: true,                   //是否显示分页
	    sortable: true, 					//是否启用排序功能
	    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页
	    pageNumber:1,                       //初始化加载第一页，默认第一页
	    pageSize: 10,                       //默认每页的记录行数
	    pageList: [10, 15, 30, 50],         //可供选择的每页的行数
	    search: true,                       //是否显示表格搜索，此搜索是只是在客户端进行搜索，不会请求服务端
	    strictSearch: true,
	    showColumns: true,                  //是否显示所有的列
	    showRefresh: true,                  //是否显示刷新按钮
	    minimumCountColumns: 1,             //最少允许的列数
	    clickToSelect: true,                //是否启用点击选中行
	    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
	    cardView: false,                    //是否显示详细视图
	    detailView: false,                  //是否显示父子表
	    queryParams : queryParams,			//拼接请求参数
	    responseHandler: dataHandler		//在渲染列表之前预处理后台数据

	};

	//新建一个表格初始化对象
	var  tableInit = new Object();

	//对表格进行初始化
	tableInit.grid = function(tableId , handler){
		//判断表格是否存在
		if(!tableId) {
			//当表格ID不存在是无法初始化表格
			console.error("表格ID不存在");
			return;
		}
		//判断后台URL是否存在
		if(!handler.url) {
			//当URL不存在是无法获取到后台数据
			console.error("后台URL不存在");
			return;
		}

		//将#号去掉，规避在传入tableId时将#传入出现错误
		while(tableId.search("#") != -1){
	        tableId = tableId.replace("#","");
	    }

	    //处理列参数与唯一标志位
	    dealColumnsAndUniqueId(handler.uniqueId , handler.columns);

	    //将自定义的参数与默认参数结合
	    $.extend(defaultOption,handler);

	    //渲染列表
	    $("#"+tableId).bootstrapTable(defaultOption);

	};


	return tableInit;
};


