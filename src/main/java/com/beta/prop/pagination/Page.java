package com.beta.prop.pagination;

public class Page {
    //默认的分页条数
    public static final int DEFAULT_LIMIT = 10;
  //总数据量
    private int totalRecords=0;
  //总页数
    private int totalPages=0;
  //当前页数
    private int currentPage=0;
  //偏移，即当前页起始数据的索引
    private int offset=0;
  //每页数据量
    private int limit=DEFAULT_LIMIT;
    
    /**
     * 
     * @Title:  Page   
     * @Description:    TODO(无参构造器)   
     * @param:    
     * @throws
     */
    public  Page(){};
    
    /**
     * 
     * @Title:  Page   
     * @Description:    TODO(有参构造器)   
     * @param:  @param cuurentPage
     * @param:  @param limit  
     * @throws
     */
    public Page(int currentPage  ,  int limit){
        this.currentPage = currentPage;
        this.limit = limit;
    }
    
    /**
     * 
     * @Title: refresh   
     * @Description: TODO(刷新分页信息)   
     * @param:       
     * @return: void      
     * @throws
     */
    public void refresh(){
        if(this.limit <= 0) {
            this.limit = DEFAULT_LIMIT;
        }
        if(this.totalRecords <= 0){
            this.totalPages = 0;
            this.currentPage = 0;
            this.offset = 0;
        }else{
            this.totalPages = this.totalRecords % this.limit > 0 ?
                    this.totalRecords / this.limit + 1 : this.totalRecords / this.limit;
            if(this.currentPage <= 0) {
                this.currentPage = 1;
            }else if(this.currentPage > this.totalPages) {
                this.currentPage = this.totalPages;
            }
            this.offset = (this.currentPage - 1) * this.limit;
        }
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    
    
    
}
