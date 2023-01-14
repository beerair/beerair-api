package com.beerair.core.common.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageResponseDto<T> implements Serializable {
    private List<T> data;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private boolean firstPage;
    private boolean lastPage;

    protected PageResponseDto() {
    }

    public PageResponseDto(final Page<T> page) {
        this.data = page.getContent();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.firstPage = page.isFirst();
        this.lastPage = page.isLast();
    }
}
