package com.cosine.demo.dto;

import java.util.List;

/**
 * 类描述：传给前端的实体类
 *
 * @ClassName OrderInfo
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/19 15:04
 * @Version 1.0
 */
public class OrderInfo<T> {
    private long total;
    private List<T> list;

    public OrderInfo(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
