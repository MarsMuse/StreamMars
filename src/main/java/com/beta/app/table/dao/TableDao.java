package com.beta.app.table.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.beta.app.table.entity.AreaInfor;
import com.beta.app.table.entity.MenuInfor;

public interface TableDao {

	List<AreaInfor>  getListForAreaInfor(@Param("paramInfor")Map<String , String>   paramInfor  ,  @Param("rowBounds")RowBounds rowBounds);
	
	List<MenuInfor>  getMenuInfor();
}
