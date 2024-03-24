package com.yupi.aggregatedsearch.model.dto.search;

import com.yupi.aggregatedsearch.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchQueryRequest extends PageRequest implements Serializable {
    /**
     * 查询参数
     */
    private String searchText;
    /**
     * 查询类型
     */
    private String type;

    private static final long serialVersionUID = 1L;

}
