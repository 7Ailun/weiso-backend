package com.yupi.aggregatedsearch.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface DataSource <T>{
    Page<T> doSearch(String searchText,long current,long pageSize);
}
