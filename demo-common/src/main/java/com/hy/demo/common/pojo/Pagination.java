package com.hy.demo.common.pojo;

import org.springframework.util.Assert;

/**
 * Pagination
 * 
 * @author wb-yhy282733
 */
public class Pagination {
    
    private int page;
    
    private int limit;

    public static Pagination of(int page, int limit) {
        Assert.isTrue(page > 0 && limit > 0, "Page/Limit must > 0");
        
        Pagination pagination = new Pagination();
        pagination.page = page;
        pagination.limit = limit;
        
        return pagination;
    }
    
    public int getStartRow() {
        return (page - 1) * limit;
    }
    
    public int getPerPage() {
        return limit;
    }
    
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
