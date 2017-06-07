package com.beta.app.table.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beta.app.table.entity.MenuInfor;
import com.beta.app.table.service.TableService;
import com.beta.prop.pagination.Page;

/**
 * 
 * <p>application name:{应用名称}</p>
 * <p>application describing:{功能描述}</p
 * <p>company:neusoft</p>
 * <p>time:2017年6月4日</p>
 * @author {FireMonkey}
 * @version {1.0}
 */

@Controller
@RequestMapping(value="/tablecontroller")
public class TableController {
    
    
    @Resource
    private TableService tableService;
    
    @RequestMapping(value="/areainfor")
    @ResponseBody
    public Map<String , Object>  getListForAreaInfor(HttpServletRequest request  , HttpServletResponse response  ,Map<String , String> parameter){

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        Page page = new Page(currentPage, limit);
        return tableService.getListForAreaInfor(parameter , page);
    }
    @RequestMapping(value="/menuinfor")
    @ResponseBody
    public List<MenuInfor>  getMenuInfor(){
    	List<MenuInfor> result = null;
    	result = this.tableService.getMenuInfor();
    	
    	return result;
    	
    }
}
