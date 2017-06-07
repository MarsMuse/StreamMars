
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
                }else{
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


/**
 * 
 *stfListMenu 菜单（浅封装了Bootstrap与SuperFish.js）
 * 
 * */

var fmListMenu  =  function(){
    /**
    **默认的一些配置，此版本中开放自定义配置父级菜单右侧图标
    **/
    var MENU_ICON_DEFSPACE = '&nbsp;';
    var MENU_ICON_DEFCLNAME = 'glyphicon';
    var MENU_EXPAND_ICON_DEFCLNAME = 'fa fa-chevron-left';
    var MENU_DEEXPAND_ICON_DEFCLNAME = 'fa fa-chevron-down';
    var MENU_ITEM_EXPAND_ICON_DEFCLNAME = 'fa fa-caret-left';
    var MENU_ITEM_DEEXPAND_ICON_DEFCLNAME = 'fa fa-caret-down';
    var MENU_DEF_PID_KEY = 'parentId';
    //默认的根节点ID
    var ROOT_DEF_NODE_PID = '0';
    //默认的外部菜单的容器
    var MENU_CONTAINER_DEFID  =   "fm-list-menu";
    //创建一个初始化对象
    var listMenu  =   new Object();
    
    /**
    *
    **/

    listMenu.init =  function(option){
        /**
        **初步判断
        **/
        if(!option.data && !option.dataHandler){
            //如果即不传入数据，又不传入获取数据的方法，退出渲染
            return;
        }
        //内部渲染菜单的函数
        var initMenu =  function(menuData){
            //剔除空数据
            if(!menuData || menuData.length == 0){
                return;
            }
            //简简单单才是真
            var data = $.extend(true, {}, menuData);

            //获取到自定义的父级未展开时图标
            option.menuExpandIconClName = option.menuExpandIconClName ?
                option.menuExpandIconClName : MENU_EXPAND_ICON_DEFCLNAME;
            //获取到自定义的父级展开时是图标
            option.menuDeExpandIconClName = option.menuDeExpandIconClName ?
                option.menuDeExpandIconClName : MENU_DEEXPAND_ICON_DEFCLNAME;
            //获取到自定义的子级未展开时图标        
            option.menuItemExpandIconClName = option.menuItemExpandIconClName ?
                option.menuItemExpandIconClName: MENU_ITEM_EXPAND_ICON_DEFCLNAME;
            //获取到自定义的子级未展开时图标
            option.menuItemDeExpandIconClName = option.menuItemDeExpandIconClName?
                option.menuItemDeExpandIconClName : MENU_ITEM_DEEXPAND_ICON_DEFCLNAME;
            //获取到父级目录主键属性名
            option.pIdKey = option.pIdKey ? option.pIdKey : MENU_DEF_PID_KEY;
            //获取到菜单外部容器ID名
            option.menuContainerId = option.menuContainerId ? 
                option.menuContainerId : MENU_CONTAINER_DEFID;
            //获取到根节点ID
            option.rootPId = option.rootPId ? option.rootPId : ROOT_DEF_NODE_PID;
            //处理后菜单数据
            var menuExtData = {};
            //使用jquery创建一个菜单根元素先在内存虚拟一个DOM，等初始化结束后再渲染到页面上，加快渲染
            var rootElement = $('<ul class="list-group st-list-menu-root"></ul>');
            //获取到根节点的ID(最外层菜单父级ID)
            var rootPId = option.rootPId;
            var pIdKey = option.pIdKey;
            //开始将后台获取到的数据进行排序
            data  = (function(menuList){
                if(menuList.length == 1){
                    return;
                }
                //有序菜单数据容器
                var sortedList = [];
                //创建一个根节点
                var rootNode = {id:rootPId};
                //同一层级菜单集合
                var layerList = [{node:rootNode , childList:[]}];
                //克隆菜单数据
                var cloneList;
                //下一层级菜单集合
                var nextLayerList;
                //将结点按照顺序排列在集合中
                var sortedInsertList =  function(list , node){
                    var insertFlag = 0;
                    //循环遍历到比较数组的排序字段
                    $.each(list , function(index , value){
                        //升序排列
                        if(node.displayOrder < value.displayOrder){
                            //插入到此位置
                            list.splice(index , 0 , node);
                            //置标志位为1
                            insertFlag = 1;
                            return false;
                        }
                    });
                    //如果未在循环中插入
                    if(insertFlag == 0){
                        list.push(node);
                    }

                };

                //插入子节点集合函数
                var insertChildrenNodeList =  function(list, node, childList){
                    if(childList.length == 0 ){
                        return;
                    }
                    $.each(list , function(outerIndex , outerValue){
                        if(node.id == outerValue.id){
                            $.each(childList, function(innerIndex , innerValue){
                                list.splice(outerIndex+innerIndex+1 , 0 , innerValue);
                            });
                            return false;
                        }

                    });
                };

                //逐层分解数据
                var dealData = function(){

                    cloneList = [];
                    nextLayerList = [];
                    //循环遍历传入的数据
                    $.each(menuList ,function(outerIndex , outerValue){
                        //设置寻找到的标志位
                        var findFlag = 0 ;
                        $.each(layerList , function(innerIndex , innerValue){
                            //如果上层结点的ID是下层节点的父级ID则将此数据插入的该节点的子节点集合中
                            if(innerValue.node.id == outerValue[pIdKey]){
                                nextLayerList.push({node:outerValue , childList:[]});
                                sortedInsertList(innerValue.childList , outerValue);
                                findFlag = 1 ;
                                return false;
                            }

                        });
                        //如果不属于上层的子节点，则放入备份集合中
                        if(findFlag == 0){
                            cloneList.push(outerValue);
                        }

                    });
                    //如果还没有值，表示此时需要存入真正的最外层菜单
                    if(sortedList.length == 0 ){
                        $.each(layerList[0].childList , function(index , value){
                            sortedList.push(value);
                        });
                    }else{
                        //若已经存在了，则需要逐层存入
                        $.each(layerList , function(index , value){
                            insertChildrenNodeList(sortedList , value.node , value.childList);
                        });
                    }

                    //层级交替
                    layerList = nextLayerList;
                    //从备份集合中获取需要分配的数据
                    menuList = cloneList;
                };
                //逐层往下遍历，获取到菜单在内存中的dom
                while(layerList.length >0){
                    dealData();
                }
                console.log(sortedList);
                return sortedList;
            })(data);
            //数据处理结束，开始通过虚拟DOM拼接菜单
            var menuItemStack = [];
            $.each(data , function(index , value){
                var stackLength = menuItemStack.length;
                if(stackLength == 0 ){
                    menuItemStack.push(value);
                }else{
                    //上一轮菜单的
                    var menuDom;
                    //图标
                    var menuIconClName = menuItemStack[stackLength-1].icon;
                    //图标的空格
                    var menuIconSpace = '';
                    //菜单缩进
                    var menuIndentSpace = '';
                    //当前未展开的图标
                    var menuExpandIconClName;
                    //当前展开的图标
                    var menuDeExpandIconClName;
                    if(menuIconClName == undefined || menuIconClName ==''){
                        //没有图标信息的菜单
                        menuIconClName = MENU_ICON_DEFCLNAME;   
                        menuIconSpace = MENU_ICON_DEFSPACE;
                    }
                    //子菜单对于父菜单缩进
                    if(stackLength >2) {
                        for(var i=0; i<stackLength-2; i++){
                            menuIndentSpace += '&nbsp;';
                        }
                    }
                    //预设父菜单应用的右侧图标
                    if(stackLength === 1){
                        menuExpandIconClName = option.menuExpandIconClName;
                        menuDeExpandIconClName = option.menuDeExpandIconClName;
                    }else{
                        menuExpandIconClName = option.menuItemExpandIconClName;
                        menuDeExpandIconClName = option.menuItemDeExpandIconClName;
                    }
                    //判定如果下一级菜单是本级菜单的子菜单则预先添加面板
                    if(value[pIdKey] == menuItemStack[stackLength-1].id){

                        menuDom = $('<li class="list-group-item st-list-menu-container"><div class="panel panel-default"><div class="panel-heading"  onclick="changeExpandByMenu(event,\'expend_id_'+index+'\' ,\''+menuExpandIconClName+'\',\''+menuDeExpandIconClName+'\')"  onmouseenter="addIndicatorActive(\'indicator_id_'+index+'\')"   onmouseleave="removeIndicatorActive(\'indicator_id_'+index+'\')"  ><div  id="indicator_id_'+index+'" class="indicator" >&nbsp;</div><div class="menu-item"> <span class="'  + MENU_ICON_DEFCLNAME + '">' + menuIndentSpace + '</span><span class="'+ menuIconClName + '">' + menuIconSpace + '</span> &nbsp;' + menuItemStack[stackLength-1].name +'<span id="expend_id_'+index+'" class="pull-right glyphicon" ></span></div> </div><div class="panel-body collapse" collapse="true"><ul class="list-group"></ul></div></div></li>');
                        //将自身的DOM备份起来
                        menuItemStack[stackLength-1].menuDomTemp = menuDom;
                        if(stackLength>1){
                            menuItemStack[stackLength-2].menuDomTemp.children('.panel').children('.panel-body').children('.list-group').append(menuDom);
                        }
                        else{
                            rootElement.append(menuDom);
                        }
                        //将下一菜单加入到栈中
                        menuItemStack.push(value);

                    }else{//如果已经是最底层菜单则直接加跳转元素
                        menuDom = $('<li class="list-group-item" onmouseenter="addIndicatorActive(\'indicator_id_'+index+'\')"   onmouseleave="removeIndicatorActive(\'indicator_id_'+index+'\')"  onclick="menuClick(\'indicator_id_'+index+'\',  \'' + menuItemStack[stackLength-1].url +'\')"><div id="indicator_id_'+index+'" class="indicator" >&nbsp;</div><div class="menu-item"><span class="' + MENU_ICON_DEFCLNAME + '">' + menuIndentSpace + '</span><span class="' + menuIconClName + '">' + menuIconSpace + '</span> &nbsp;' + menuItemStack[stackLength-1].name + '</div></li>');
                        //将自身的DOM备份起来
                        menuItemStack[stackLength-1].menuDomTemp = menuDom;
                        if(stackLength>1){
                            menuItemStack[stackLength-2].menuDomTemp.children('.panel').children('.panel-body').children('.list-group').append(menuDom);
                        }
                        else{
                            rootElement.append(menuDom);
                        }
                        //如果本层结束了DOM加载，逐级网上返，继续加载剩余DOM
                        while(menuItemStack.length >0){
                            if(menuItemStack[menuItemStack.length-1].id == value[pIdKey]){
                                break;
                            }
                            menuItemStack.pop();
                        }
                        //加入下一级菜单，继续加载
                        menuItemStack.push(value);
                    }
                }
            });

            //剩余的栈中长度
            var lastStackLength  =  menuItemStack.length;
            //如果栈中还剩余节点则继续处理
            if(lastStackLength > 0){
                //获取到图标
                var menuDom;
                var index = data.length;
                var menuIconClName = menuItemStack[lastStackLength-1].icon;
                var menuIconSpace = '';
                var menuIndentSpace = '';   //菜单缩进
                if(menuIconClName === undefined || menuIconClName === ''){
                    menuIconClName = MENU_ICON_DEFCLNAME;   //没有图标信息的菜单
                    menuIconSpace = MENU_ICON_DEFSPACE;
                }
                if(lastStackLength >2) {
                    for(var i=0; i<lastStackLength-2; i++){
                        menuIndentSpace += '&nbsp;';
                    }
                }
                menuDom=$('<li class="list-group-item" onmouseenter="addIndicatorActive(\'indicator_id_'+index+'\')"   onmouseleave="removeIndicatorActive(\'indicator_id_'+index+'\')"  onclick="menuClick(\'indicator_id_'+index+'\', \'' + menuItemStack[lastStackLength-1].url +'\')"><div id="indicator_id_'+index+'" class="indicator" >&nbsp;</div><div class="menu-item"><span class="' + MENU_ICON_DEFCLNAME + '">' + menuIndentSpace + '</span><span class="' + menuIconClName + '">' + menuIconSpace + '</span> &nbsp;' + menuItemStack[lastStackLength-1].name + '</div></li>');
                //将自身的DOM备份起来
                menuItemStack[lastStackLength-1].menuDomTemp = menuDom;
                if(lastStackLength>1){
                    menuItemStack[lastStackLength-2].menuDomTemp.children('.panel').children('.panel-body').children('.list-group').append(menuDom);
                }
                else{
                    rootElement.append(menuDom);
                }
            }

            console.log(rootElement);
            
            //将#号去掉，规避在传入option.menuContainerId时将#传入出现错误
            while(option.menuContainerId.search("#") != -1){
                option.menuContainerId = option.menuContainerId.replace("#","");
            }
            $("#"+option.menuContainerId).append(rootElement);
            
            $("#"+option.menuContainerId).children('.st-list-menu-root').superfish();
        };


        /***********************************************************************/
        /**************************开始渲染菜单*********************************/
        /***********************************************************************/

        //如果直接传入的数据则将数据初始化
        if(option.data){
            initMenu(option.data);
        }else{
            //获取到数据处理函数
            var  dataHandler = option.dataHandler;
            //配置回调函数
            var menuData = function(data){
                initMenu(data);
            };
            //从后台请求数据
            dataHandler(menuData);
        }
    };
    return listMenu;

};

var  addIndicatorActive  =  function(id){
    $("#"+id).addClass("active");
}
var  removeIndicatorActive  =  function(id){
    $("#"+id).removeClass("active");
}

var changeExpandByMenu= function(event , id , expend , deexpend){
    $("#"+id).toggleClass(expend).toggleClass(deexpend);
    console.log($(event)[0].target);
}
var menuClick = function(id , url){
    $(".st-list-menu-root").find('.indicator.selected').removeClass('selected');
    $("#"+id).addClass('selected');
    console.log(url);
}

