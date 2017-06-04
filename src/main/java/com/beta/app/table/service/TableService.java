package com.beta.app.table.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.beta.app.table.dao.TableDao;
import com.beta.app.table.entity.AreaInfor;

@Service
public class TableService {
    
    @Resource
    private TableDao tableDao;
    
    public List<AreaInfor>  getListForAreaInfor(Map<String , String>   paramInfor){
        List<AreaInfor> result=  null;
        result = tableDao.getListForAreaInfor(paramInfor);
        return result;
    }
}
