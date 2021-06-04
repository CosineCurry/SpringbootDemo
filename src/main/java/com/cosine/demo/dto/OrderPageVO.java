package com.cosine.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName OrderPageVO
 * @Description 传给前端的分页实体类
 * @Author cosine
 * @Date 2021/6/4 8:59
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPageVO<T> {

    private Integer pageNo;
    private Integer pageSize;
    private Long total;
    private Boolean isFirstPage;
    private Boolean isLastPage;
    private Boolean hasPreviousPage;
    private Boolean hasNextPage;
    private List<T> list;

}
