package com.beta.prop.pagination;

import org.apache.ibatis.session.RowBounds;

/**
 * 
 * @ClassName:  BindedRowBounds   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: FireMonkey
 * @date:   2017年6月5日 下午3:11:26   
 *     
 * @Copyright: 2017 
 *
 */
public class BindedRowBounds  extends  RowBounds{
    
    /**
     * 分页对象
     */
    private  Page page;
    
    public  BindedRowBounds(){};
    
    /**
     * 
     * @Title:  BindedRowBounds   
     * @Description:    TODO(创建一个rowBounds并且与page进行绑定)   
     * @param:  @param page  
     * @throws
     */
    public  BindedRowBounds(Page  page){
        super(page.getOffset() ,  page.getLimit());
        this.page  = page;
    }
    
    /**
     * 
     * @Title: getPage   
     * @Description: TODO(获取到Page对象)   
     * @param: @return      
     * @return: Page      
     * @throws
     */
    public Page  getPage(){
        return this.page;
    }
}
