package com.zys.day131.result;

import com.zys.day131.param.PageParam;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Page<E> implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页数
    private int currentPage = 0;
    //总页数
    private long totalPage;
    //总记录数
    private long totalNumber;
    //结果集
    private List<E> list;

    public Page() {
    }

    /**
     *
     * @param beginLine     当前页数
     * @param totalNumber   总记录数
     * @param pageSize      页大小
     * @param list          页数据
     */
    public Page(int beginLine, long totalNumber, int pageSize, List<E> list) {
        super();
        this.currentPage = beginLine / pageSize + 1;
        this.totalNumber = totalNumber;
        this.list = list;
        this.totalPage = totalNumber % pageSize == 0 ? totalNumber / pageSize : totalNumber / pageSize + 1;
    }

    public Page(PageParam pageParam, long totalNumber, List<E> list){
        super();
        this.currentPage = pageParam.getCurrentPage();
        this.totalNumber = totalNumber;
        this.list = list;
        this.totalPage = totalNumber % pageParam.getPageSize() == 0 ? totalNumber
                / pageParam.getPageSize() : totalNumber / pageParam.getPageSize() + 1;
    }
}
