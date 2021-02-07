package com.xtn.common;

import java.util.List;

/**
 * 分页类
 * 保存分页信息
 */

public class Pagination<T> {

    private Integer total;  //总页数
    private List<T> dataList;  //获取的数据

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

}
