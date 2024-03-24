package com.yupi.aggregatedsearch.model.dto.picture;

import com.yupi.aggregatedsearch.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author <a href="https://github.com/7Ailun">艾伦</a>
 *  
 */
@Data
public class PictureQueryRequest extends PageRequest implements Serializable {


    /**
     * 标题
     */
    private String searchText;

    private static final long serialVersionUID = 1L;
}