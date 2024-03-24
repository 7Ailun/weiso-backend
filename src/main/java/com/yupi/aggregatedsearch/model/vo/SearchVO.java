package com.yupi.aggregatedsearch.model.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchVO<T> implements Serializable {
    private Page<PostVO> postVOPage;
    private Page<UserVO> userVOPage;
    private Page<PictureVO> pictureVOPage;
    private Page<T> objectPage;

    private static final long serialVersionUID = 1L;
}
