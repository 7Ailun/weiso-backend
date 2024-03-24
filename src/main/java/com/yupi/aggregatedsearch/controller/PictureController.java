package com.yupi.aggregatedsearch.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.aggregatedsearch.common.BaseResponse;
import com.yupi.aggregatedsearch.common.ErrorCode;
import com.yupi.aggregatedsearch.common.ResultUtils;
import com.yupi.aggregatedsearch.exception.ThrowUtils;
import com.yupi.aggregatedsearch.model.dto.picture.PictureQueryRequest;
import com.yupi.aggregatedsearch.model.vo.PictureVO;
import com.yupi.aggregatedsearch.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 图片接口
 *
 * @author <a href="https://github.com/7Ailun">艾伦</a>
 *  
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;


    /**
     * 分页获取列表（封装类）
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<PictureVO>> listPostVOByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                          HttpServletRequest request) {
        long current = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<PictureVO> pictureVOPage = pictureService.doSearchPicture(pictureQueryRequest);
        return ResultUtils.success(pictureVOPage);
    }

}
