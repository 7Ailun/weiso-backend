package com.yupi.springbootinit.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片
 *
 * @author <a href="https://github.com/7Ailun">艾伦</a>
 *  
 */
@Data
public class Picture implements Serializable {

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