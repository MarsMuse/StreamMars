
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
    tableInit.currentOffset = 0 ;
    //对表格进行初始化
    tableInit.grid = function(id , tbControl){
        //表格ID
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
                tableInit.currentOffset = paramData.offset;
                console.log(tableInit.currentOffset);
                dataHandler(gridHandler , page);
            }else{
                dataHandler(gridHandler);
            }
        };

        //表格控制器接口对象
        var  gridController = {};
        
        //向外界提供查询按钮函数
        gridController.query = function(){
            $("#"+tableId).bootstrapTable('selectPage' , 1);
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
    var MENU_EXPAND_ICON_DEFCLNAME = 'fa-chevron-left';
    var MENU_DEEXPAND_ICON_DEFCLNAME = 'fa-chevron-down';
    var MENU_ITEM_EXPAND_ICON_DEFCLNAME = 'fa-caret-left';
    var MENU_ITEM_DEEXPAND_ICON_DEFCLNAME = 'fa-caret-down';
    var MENU_ICONCL_NAME = "glyphicon glyphicon-th-list";
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
        if(!option){
            console.error("调用此服务必须提供option");
            return;
        }
        
        if(!option.data && !option.dataHandler){
            //如果即不传入数据，又不传入获取数据的方法，退出渲染
            console.error("调用此服务必须提供数据处理器接口(dataHandler)或者数据(data)");
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
                    var menuIconClName = menuItemStack[stackLength-1].icon ?  menuItemStack[stackLength-1].icon : MENU_ICONCL_NAME;
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
                        //折叠面板对象
                        var  collapse = {};
                        //面板ID
                        collapse.id  = "collapse_"+index;
                        menuDom = $('<li class="list-group-item st-list-menu-container"><div class="panel panel-default"><div class="panel-heading"  onclick="changeExpandByMenu(\''+collapse.id+'\',\'expend_id_'+index+'\' ,\''+option.menuExpandIconClName+'\',\''+option.menuDeExpandIconClName+'\',\''+option.menuItemExpandIconClName+'\',\''+option.menuItemDeExpandIconClName+'\')"  onmouseenter="addIndicatorActive(\'indicator_id_'+index+'\')"   onmouseleave="removeIndicatorActive(\'indicator_id_'+index+'\')"  ><div  id="indicator_id_'+index+'" class="indicator" >&nbsp;</div><div class="menu-item"> <span class="'  + MENU_ICON_DEFCLNAME + '">' + menuIndentSpace + '</span><span class="'+ menuIconClName + '">' + menuIconSpace + '</span> &nbsp;' + menuItemStack[stackLength-1].name +'<span id="expend_id_'+index+'" class="pull-right glyphicon fm-pull-right-icon  fa  '+menuDeExpandIconClName+' " ></span></div> </div><div class="panel-body fm-collapse-flag  collapse"  id="'+collapse.id+'"   collapse="true"><ul class="list-group"></ul></div></div></li>');
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

            //将#号去掉，规避在传入option.menuContainerId时将#传入出现错误
            while(option.menuContainerId.search("#") != -1){
                option.menuContainerId = option.menuContainerId.replace("#","");
            }
            $("#"+option.menuContainerId).append(rootElement);
            
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
};
var  removeIndicatorActive  =  function(id){
    $("#"+id).removeClass("active");
};

//展开与关闭菜单
var changeExpandByMenu= function(collapseId , id , expend , deexpend,itemExpend , itemDeexpend){
    //修改样式
    if($("#"+id).hasClass(expend) || $("#"+id).hasClass(deexpend)){
        $("#"+id).toggleClass(expend).toggleClass(deexpend);
    }
    else{
        $("#"+id).toggleClass(itemExpend).toggleClass(itemDeexpend);
    }
    
    $("#"+collapseId).slideToggle();
    //获取这个菜单的父级菜单的ID（这些菜单不改变）
    var parentCollapseList=[collapseId];
    $.each($("#"+collapseId).parents(".fm-collapse-flag") , function(index , value){
        parentCollapseList.push($(value).attr("id"));
    });
    //获取到该菜单全部的父级菜单ID
    var allCollapseList=[];
    $.each($(".st-list-menu-root").find(".fm-collapse-flag") , function(index , value){
        allCollapseList.push($(value).attr("id"));
    });
    //获取到需要处理的ID集合
    for(var i = 0 ; i <allCollapseList.length ; i++){
        for(var j = 0 ; j < parentCollapseList.length ; j++){
            if(allCollapseList[i] == parentCollapseList[j]){
                allCollapseList.splice(i ,1);
                i--;
                parentCollapseList.splice(j ,1);
                j--;
                break;
            }
        }
        if( parentCollapseList.length ==0){
            break;
        }
    }
    for(var i = 0 ; i < allCollapseList.length ; i++){
        var tempId = allCollapseList[i];
        if($("#"+tempId).is(':visible')){
            $("#"+tempId).slideUp();
            var iconIdIndex = "expend_id_"+tempId.replace("collapse_","");
            //var iconObj = $("#"+id).siblings(".panel-heading").find(".fm-pull-right-icon");
            var iconObj = $("#"+iconIdIndex);
            if(iconObj.hasClass(expend)){
                iconObj.removeClass(expend).addClass(deexpend);
            }else if(iconObj.hasClass(itemExpend)){
                iconObj.removeClass(itemExpend).addClass(itemDeexpend);
            }
        }
    }
};
var menuClick = function(id , url){
    $(".st-list-menu-root").find('.indicator.selected').removeClass('selected');
    $("#"+id).addClass('selected');
    
};




/*******************************************************************************/
/**********************************全局的日期格式转换函数***************************/
/******************************************************************************/


//日期转换函数
function globalDateFormat(jsonDate , dateMatch) {
    if(!jsonDate || jsonDate==''){
        return "";
    }
    //json日期格式转换为正常格式
    //此处用到toString（）是为了让传入的值为字符串类型，目的是为了避免传入的数据类型不支持.replace（）方法
    var jsonDateStr = jsonDate.toString();
    //console.log(jsonDateStr);
    try {
        var k = parseInt(jsonDateStr.replace("/Date(", "").replace(")/", ""), 10);
        if (k < 0) 
            return "";

        var date = new Date(parseInt(jsonDateStr.replace("/Date(", "").replace(")/", ""), 10));
        var year = date.getFullYear();
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
        var milliseconds = date.getMilliseconds();

        if(dateMatch){
          //转化为大写
          dateMatch = dateMatch.toUpperCase();
          //替换年
          if(dateMatch.search("YYYY") != -1){
            dateMatch = dateMatch.replace("YYYY",year);
          }else if(dateMatch.search("YYY") != -1){
            dateMatch = dateMatch.replace("YYY",year.toString().substring(1));
          }else if(dateMatch.search("YY") != -1){
            dateMatch = dateMatch.replace("YY",year.toString().substring(2));
          }else if(dateMatch.search("Y") != -1){
            dateMatch = dateMatch.replace("Y",year.toString().substring(3));
          }
          //替换月
          if(dateMatch.search("MM") != -1){
            dateMatch = dateMatch.replace("MM",month);
          }
          //替换日期
          if(dateMatch.search("DD") != -1){
            dateMatch = dateMatch.replace("DD",day);
          }
          //替换小时
          if(dateMatch.search("HH") != -1){
            dateMatch = dateMatch.replace("HH",hours);
          }else if(dateMatch.search("HH24") != -1){
            dateMatch = dateMatch.replace("HH24",hours);
          }else if(dateMatch.search("HH12") != -1){
            dateMatch = dateMatch.replace("HH12",hours);
          }
          //替换分钟
          if(dateMatch.search("MI")!= -1){
            dateMatch = dateMatch.replace("MI",minutes);
          }
          //替换秒
          if(dateMatch.search("SS")!= -1){
            dateMatch = dateMatch.replace("SS",seconds);
          }
          return dateMatch;

        }else{
          //返回默认的格式
          return  year+ "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
        }
    }
    catch (ex) {
        return "时间格式转换错误";
    }
};

/*******************************************************************************/
/**********************************弹出一个树形菜单窗口***************************/
/******************************************************************************/


/**
 * 将数据处理成有序集合方法
 * @param data
 */

function  detailNodeInfor(dataList , root ,idKey){
    if(!dataList || dataList.length ==0){
        return ;
    }
    //获取到根节点ID
    var rootPId = root?root:'1';
    var pIdKey  = idKey ? idKey :'parentId';
    if(dataList.length == 1){
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
        $.each(dataList ,function(outerIndex , outerValue){
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
        dataList = cloneList;
    };
    
    //逐层往下遍历，获取到菜单在内存中的dom
    while(layerList.length >0){
        dealData();
    }
    return sortedList;
};



/**
 * 树形弹窗对象
 */
var  fmTreeModel  =  function(selfOption){
    
    if(!selfOption  || !selfOption.inputId){
        console.error("请设置必要的参数");
        return false;
    }
    var  bindId = selfOption.inputId;
    //增加用户体验
    $("#"+bindId).css("cursor","pointer");
    var  tempBindData = {
            value:'',
            data:''
    };
    
    //inputId再加上一个随机数
    var  treeId = "tree_"+selfOption.inputId+Math.floor(Math.random()*10+1);
    //树对象(后续用户操作选择)
    
    var zTreeObj  =  null;
    //$("#content").append(treeElemnt);
    //树形结构的数据
    var  treeNode  =  null;
    //设置参数
    var setting  =  null;
    //获取到树点击事件响应函数
    var  bindDataToInput  =  function(event , treeId , treeNode){
        tempBindData.value = treeNode.id;
        tempBindData.data  = treeNode.name;
    };
    
    
    
    //数据处理器
    var dataHandler  =  function(data){
        //数据进行预处理
        treeNode  =  detailNodeInfor(data , selfOption.rootPId , selfOption.pIdKey);
        //配置参数
        setting= {
            data: {
                simpleData: {
                    enable: true,
                    idKey:'id',
                    pIdKey:selfOption.pIdKey ? selfOption.pIdKey :'parentId',
                    rootPId:selfOption.rootPId?selfOption.rootPId:'0'
                }
            },
            callback: {
                onClick: bindDataToInput
            }
        };
    };
    
    //获取到数据请求的方法
    var  dataHelper  =  selfOption.dataHelper?selfOption.dataHelper:officeDefaultQuery;
    
    //请求数据
    dataHelper(dataHandler);
    
    //弹窗元素
    var  layerElemnt  =  null;
    
    //处理弹窗的对象
    var  treeOperateWindow  =  new Object();
    
    //开启一个弹窗
    treeOperateWindow.open = function(){
        if(!treeNode){
            console.error("数据未初始化完成");
            return false;
        }
        layerElemnt  =  layer.open({
            title:selfOption.title ?selfOption.title:'组织机构信息',
            type: 1,
            closeBtn: 1,
            area:['300px','480px'],
            shadeClose: true,
            move :false,
            resize :false,
            btn: ['确定', '清空', '取消'],
            btnAlign: 'r',
            yes: function(index, layero){
                if(tempBindData && tempBindData.data != ''){
                    $("#"+bindId).val(tempBindData.data);
                    $("#"+bindId).attr("bindValue" , tempBindData.value)
                }
                
                layer.close(index);
            },
            btn2: function(index, layero){
                tempBindData.data = '';
                tempBindData.value = '';
                $("#"+bindId).val('');
                $("#"+bindId).attr("bindValue" , '');
            },
            cancel: function(index, layero){ 
               layer.close(index)
               return false; 
            },
            success:function(){
                 /*if(!zTreeObj){
                     zTreeObj =  $.fn.zTree.init($("#"+treeId), setting, treeNode);
                 }*/
                 zTreeObj =  $.fn.zTree.init($("#"+treeId), setting, treeNode);
            },
            
            content:'<div  style="padding:8px ;font-size:12px;"><ul  id="'+treeId+'"  class="ztree" ></ul></div>' /*<div><span>已选择：</span><span  id="treeDemoSelect"></span></div>*/
        });
        
    };
    return  treeOperateWindow;
};



/*****************************************************************/
/**********************全局的Ajax请求预处理函数*********************/
/****************************************************************/
var  globalAjaxLoading  =  function(){
    var  loadLayer  =  undefined;
    var  loadCount  =  0;
    
    //发送Ajax请求绑定的函数
    $(document).ajaxSend(function() {
        if(!loadLayer){
            loadLayer  =   layer.load();
            
        }
        loadCount++;
    });
    
    
    //成功响应的函数
    $(document).ajaxSuccess(function() {
        loadCount --;
        if(loadCount <= 0 &&  loadLayer){
            layer.close(loadLayer);
            loadCount = 0;
            loadLayer  = undefined;
        }
    });
    
    
    //响应错误的函数
    $(document).ajaxError(function() {
        loadCount --;
        if(loadCount <= 0 &&  loadLayer){
            layer.close(loadLayer);
            loadCount = 0;
            loadLayer  = undefined;
        }
    });
    
    
};

