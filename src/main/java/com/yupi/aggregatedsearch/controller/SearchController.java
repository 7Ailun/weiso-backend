package com.yupi.aggregatedsearch.controller;

import com.yupi.aggregatedsearch.common.BaseResponse;
import com.yupi.aggregatedsearch.common.ResultUtils;
import com.yupi.aggregatedsearch.manager.SearchFacade;
import com.yupi.aggregatedsearch.model.dto.search.SearchQueryRequest;
import com.yupi.aggregatedsearch.model.vo.SearchVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 聚合搜索接口
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchQueryRequest searchQueryRequest, HttpServletRequest request) {
        SearchVO searchVO = searchFacade.searchAll(searchQueryRequest, request);
        return ResultUtils.success(searchVO);
    }
}
