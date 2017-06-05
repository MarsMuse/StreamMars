package com.beta.prop.pagination.dialect;

public class OracleDialect implements Dialect{
    /**
     * oracle 数据库加锁的情况，会加上for update
     */
    private  static final String LOCK_STATEMENT  =  "for update";
    /**
     * 加锁语句长度
     */
    private static final int LOCK_STATEMENT_LEN = LOCK_STATEMENT.length();
    /**
     * 添加分页语句长度
     */
    private static final int PAGE_SQL_EXT_LEN = 100;
    @Override
    public String getPaginationSQL(String sql, int offset, int limit) {
        
        //判断加锁标志位
        boolean isForUpdate = false;
        //去掉两端空格
        String pSql = sql.trim();
        //判断如果存在加锁的语句，则先把sql加锁语句去掉，加完分页语句后再加上
        if(pSql.toLowerCase().endsWith(LOCK_STATEMENT)){
            pSql = pSql.substring(0, pSql.length()-LOCK_STATEMENT_LEN);
            isForUpdate = true;
        }
        StringBuilder pageSql = new StringBuilder(pSql.length() + PAGE_SQL_EXT_LEN);
        /**
         * 添加分页语句
         */
        pageSql.append("select * from ( select row_.*, rownum rownum_ from ( ")
            .append(pSql)
            .append(" ) row_ ) where rownum_ > ")
            .append(offset)
            .append(" and rownum_ <=")
            .append(offset + limit);
        if(isForUpdate) {
            pageSql.append(LOCK_STATEMENT);
        }
        return pageSql.toString();
    }
}
