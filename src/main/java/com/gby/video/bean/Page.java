package com.gby.video.bean;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private Integer pageNum;
    private Integer pageSize;
    private List<T> rows;
    private Long total;
}
