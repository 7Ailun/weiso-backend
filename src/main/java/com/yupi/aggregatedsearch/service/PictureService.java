package com.yupi.aggregatedsearch.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.aggregatedsearch.model.dto.picture.PictureQueryRequest;
import com.yupi.aggregatedsearch.model.vo.PictureVO;

public interface PictureService {
    Page<PictureVO> doSearchPicture(PictureQueryRequest pictureQueryRequest);
}
