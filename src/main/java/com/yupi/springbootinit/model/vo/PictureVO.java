package com.yupi.springbootinit.model.vo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yupi.springbootinit.model.entity.Post;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
