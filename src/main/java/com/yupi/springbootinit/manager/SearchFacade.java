package com.yupi.springbootinit.manager;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.datasource.*;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.model.dto.search.SearchQueryRequest;
import com.yupi.springbootinit.model.enums.SearchTypeEnum;
import com.yupi.springbootinit.model.vo.PictureVO;
import com.yupi.springbootinit.model.vo.PostVO;
import com.yupi.springbootinit.model.vo.SearchVO;
import com.yupi.springbootinit.model.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class SearchFacade {
    @Resource
    private PostDataSource postDataSource;
    @Resource
    private UserDataSource userDataSource;
    @Resource
    private PictureDataSource pictureDataSource;
    @Resource
    private DataSourceRegistry dataSourceRegistry;

    public SearchVO searchAll(SearchQueryRequest searchQueryRequest, HttpServletRequest request) {
        if (searchQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不合法");
        }
        // 拿到查询参数、当前页、页面大小
        String searchText = searchQueryRequest.getSearchText();
        long current = searchQueryRequest.getCurrent();
        long pageSize = searchQueryRequest.getPageSize();
        String type = searchQueryRequest.getType();
        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);

        DataSource dataSource = null;
        SearchVO searchVO = new SearchVO();
        if (StringUtils.isBlank(type)) {
            Page<PostVO> postVOPage = postDataSource.doSearch(searchText, current, pageSize);
            Page<PictureVO> pictureVOPage = pictureDataSource.doSearch(searchText, current, pageSize);
            Page<UserVO> userVOPage = userDataSource.doSearch(searchText, current, pageSize);
            searchVO.setPictureVOPage(pictureVOPage);
            searchVO.setUserVOPage(userVOPage);
            searchVO.setPostVOPage(postVOPage);
        } else {
            ThrowUtils.throwIf(BeanUtil.isEmpty(searchTypeEnum), ErrorCode.PARAMS_ERROR, "类型错误");
            dataSource = dataSourceRegistry.getDataSource(searchTypeEnum.getValue());
            Page<Object> page = dataSource.doSearch(searchText, current, pageSize);
            searchVO.setObjectPage(page);
        }
        return searchVO;
    }
}
