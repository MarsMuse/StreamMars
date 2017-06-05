package com.beta.prop.pagination.dialect;

/**
 * 
 * @ClassName:  Dialect   
 * @Description:TODO(获取到分页Sql接口，旨在适配各类数据库方言)   
 * @author: FireMonkey
 * @date:   2017年6月5日 下午3:41:26   
 *     
 * @Copyright: 2017 
 *
 */
public interface Dialect {
    /**
     * 
     * @Title: getPaginationSQL   
     * @Description: TODO(获取到分页Sql)   
     * @param: @param sql
     * @param: @param offset
     * @param: @param limit
     * @param: @return      
     * @return: String      
     * @throws
     */
    String getPaginationSQL(String sql, int offset, int limit);
}
