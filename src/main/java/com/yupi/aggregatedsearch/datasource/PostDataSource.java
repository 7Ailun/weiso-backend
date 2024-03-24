package com.yupi.aggregatedsearch.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.aggregatedsearch.model.dto.post.PostQueryRequest;
import com.yupi.aggregatedsearch.model.vo.PostVO;
import com.yupi.aggregatedsearch.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 搜索帖子数据源
 *
 */
@Service
@Slf4j
public class PostDataSource implements DataSource<PostVO> {

    @Resource
    private PostService postService;
    @Override
    public Page<PostVO> doSearch(String searchText, long current, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(current);
        postQueryRequest.setPageSize(pageSize);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Page<PostVO> postVOPage = postService.listPostVOByPage(postQueryRequest, request);
        return postVOPage;
    }
}




