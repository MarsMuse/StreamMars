package com.beta.app.table.dao;

import java.util.List;
import java.util.Map;

import com.beta.app.table.entity.AreaInfor;

public interface TableDao {

	List<AreaInfor>  getListForAreaInfor(Map<String , String>   paramInfor);
}
