package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.model.dto.picture.PictureQueryRequest;
import com.yupi.springbootinit.model.vo.PictureVO;

import java.util.List;

public interface PictureService {
    Page<PictureVO> doSearchPicture(PictureQueryRequest pictureQueryRequest);
}
