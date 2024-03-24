package com.yupi.aggregatedsearch.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 帖子视图
 *
 * @author <a href="https://github.com/7Ailun">艾伦</a>
 *  
 */
@Data
public class PictureVO implements Serializable {


    /**
     * 标题
     */
    private String searchText;

    /**
     * 图片地址
     */
    private String url;

    private static final long serialVersionUID = 1L;
}
