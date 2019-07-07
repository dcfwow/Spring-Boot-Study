package com.zys.day131.param;

import lombok.Data;

@Data
public class PageParam {
    //起始行
    private int beginLine;
    private Integer pageSize = 3;
    //当前页
    private Integer currentPage = 0;

    public int getBeginLine(){
        //自动计算起始行
        return pageSize*currentPage;
    }
}
