package com.beta.app.table.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beta.app.table.service.TableService;

/**
 * 
 * <p>application name:{应用名称}</p>
 * <p>application describing:{功能描述}</p>
 * <p>Copyright：Copyright 2017 东软 政府第五事业部版权所有。</p>
 * <p>company:neusoft</p>
 * <p>time:2017年6月4日</p>
 * @author {邹尧}
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
        
        Map<String , Object> map = new HashMap<String , Object>();
        
        map.put("result", tableService.getListForAreaInfor(parameter));
        map.put("totalCount",500);
        
        return map;
    }
}
