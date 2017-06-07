package com.beta.app.table.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.beta.app.table.dao.TableDao;
import com.beta.app.table.entity.AreaInfor;
import com.beta.app.table.entity.MenuInfor;
import com.beta.prop.pagination.BindedRowBounds;
import com.beta.prop.pagination.Page;

@Service
public class TableService {
    
    @Resource
    private TableDao tableDao;
    
    public Map<String , Object>  getListForAreaInfor(Map<String , String>   paramInfor  , Page page){
    	 Map<String , Object> result=  new HashMap<String , Object>();
    	 BindedRowBounds rowBounds= new BindedRowBounds(page);
        List<AreaInfor>  data = tableDao.getListForAreaInfor(paramInfor , rowBounds );
        result.put("result", data);
        result.put("totalCount", rowBounds.getPage().getTotalRecords());
        return result;
    }
    
    public List<MenuInfor>  getMenuInfor(){
    	List<MenuInfor> result = null;
    	result = this.tableDao.getMenuInfor();
    	
    	return result;
    	
    }
}
