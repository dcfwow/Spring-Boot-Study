package com.zys.day132.param;

import lombok.Data;

@Data
public class PageParam {
    //起始行
    private int beginLine;
    private Integer pageSize = 3;
    // 当前页
    private Integer currentPage=0;
}
