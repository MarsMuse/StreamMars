
/**
*
*
**bootstrap table的辅助服务，进一步封装，为页面表格展示提供方便
*
*
**/
//类FmPage  用于创建存储分页数据的对象
var FmPage = function(currentPage, limit){
    if(currentPage){
        if(!isNaN(currentPage)){
            this.currentPage = currentPage;
        }else{
            try{
                this.currentPage = Number(currentPage);
            }catch(err){
                this.currentPage = 1;
            }
        }
    }else{
        this.currentPage = 1;
    }
    if(limit){
        if(!isNaN(currentPage)){
            this.limit = limit;
        }else{
            try{
                this.limit = Number(limit);
            }catch(err){
                this.limit = 10;
            }
        }
    }else{
        this.limit = 10;
    }
    this.totalRecords = 0;
    this.totalPages = 0;
    this.offset = 0;
};

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
        //将第一列设置为唯一主键
        uniqueId = info.field;
    }
    
    for(var i = 0 ; i<columnList.length ; i++){
        var temp = columnList[i];
        //如果不存在配置垂直位置
        if(!temp.hasOwnProperty("valign")){
            temp.valign = "middle"
        }
    }
};

//配置请求参数
var queryParams = function(){
    var param = {
        pageindex : this.pageNumber,
        pageSize : this.pageSize
    }
    return param;
};

//对数据进行预处理
var dataHandler = function(data){

    if(data){
        return {
            "rows" : data.result,
            "total" : data.totalCount
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
        method: 'get',                         //请求方式，默认使用get请求
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，为了保证数据一致性，禁用此功能
        pagination: true,                   //是否显示分页
        sortable: true,                     //是否启用排序功能
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页
        paginationLoop:false,               //分页按钮是否循环
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //默认每页的记录行数
        pageList: [10, 15, 30, 50],         //可供选择的每页的行数
        search: false,                       //是否显示表格搜索，此搜索是只是在客户端进行搜索，不会请求服务端
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 1,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        undefinedText:"",
        queryParams : queryParams,            //拼接请求参数
        responseHandler: dataHandler        //在渲染列表之前预处理后台数据
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


var  TableByAjax    =   function(){
    //配置默认的表格加载参数
    var defControl = {
        method: 'get',                         //请求方式，默认使用get请求
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，为了保证数据一致性，禁用此功能
        pagination: true,                   //是否显示分页
        sortable: true,                     //是否启用排序功能
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页
        paginationLoop:false,               //分页按钮是否循环
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //默认每页的记录行数
        pageList: [10, 15, 30, 50],         //可供选择的每页的行数
        search: false,                       //是否显示表格搜索，此搜索是只是在客户端进行搜索，不会请求服务端
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 1,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        resizable: false,                        //是否可以拉动每一列
        paginationVAlign:"bottom",    //由于resize与分页栏在chrome中会有冲突，会出现table的水平滚动轴与分页栏后重合的情况，所以，当业务需求必须resize时，将分页栏调至上部，这个问题得到解决
        undefinedText:""                   //为空值时显示字符串
        
    };
    
    //新建一个表格初始化对象
    var  tableInit = new Object();

    //对表格进行初始化
    tableInit.grid = function(id , tbControl){
        var tableId = id;
        //判断表格是否存在
        if(!tableId) {
            //当表格ID不存在是无法初始化表格
            console.error("调用此服务必须提供表格ID");
            return;
        }
        
        if(!tbControl.dataHandler) {
            //当表格ID不存在是无法初始化表格
            console.error("调用此服务必须提供数据处理器接口");
            return;
        }

        //将#号去掉，规避在传入tableId时将#传入出现错误
        while(tableId.search("#") != -1){
            tableId = tableId.replace("#","");
        }
        
      //处理列参数与唯一标志位
        dealColumnsAndUniqueId(tbControl.options.uniqueId , tbControl.options.columns);
        
        //将自定义的参数与默认参数结合
        $.extend(defControl,tbControl.options);
        
        //获取到传入的数据处理器
        var  dataHandler  =  tbControl.dataHandler;
        //自定义bootstrap ajax
        defControl.ajax = function(params){
            
            //创建一个表格处理器
            var  gridHandler  =  function(data){
                //创建一个bootstrap需要的数据模型
                var result = {};
                if(data){
                    result.total  =  data.totalCount;
                    result.rows = data.result;
                }
                else{
                    result.total  =  0;
                    result.rows = [];
                }
                params.success(result);
                
            };
            //调用传入的数据处理器通过配置的分页端
            if(defControl.sidePagination === 'server'){
                var paramData = JSON.parse(params.data);
                var currentPage = paramData.offset / paramData.limit + 1;
                var page = new FmPage(currentPage, paramData.limit);
                dataHandler(gridHandler , page);
            }else{
                dataHandler(gridHandler);
            }
            
        };

        //表格控制器接口对象
        var  gridController = {};
        
        //向外界提供查询按钮函数
        gridController.query = function(){
            
            $("#"+tableId).bootstrapTable("refresh");
        };
        
        gridController.getSelections = function(){
            return $("#"+tableId).bootstrapTable('getSelections');
        };
        //渲染列表
        $("#"+tableId).bootstrapTable(defControl);
        
        return gridController;
    };
    return tableInit;
};
