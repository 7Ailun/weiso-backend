package com.yupi.springbootinit.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.model.dto.picture.PictureQueryRequest;
import com.yupi.springbootinit.model.vo.PictureVO;
import com.yupi.springbootinit.service.PictureService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 搜索图片数据源
 */
@Service
public class PictureDataSource implements DataSource<PictureVO> {
    @Resource
    private PictureService pictureService;

    @Override
    public Page<PictureVO> doSearch(String searchText, long current, long pageSize) {
        PictureQueryRequest pictureQueryRequest = new PictureQueryRequest();
        pictureQueryRequest.setSearchText(searchText);
        pictureQueryRequest.setCurrent(current);
        pictureQueryRequest.setPageSize(pageSize);
        Page<PictureVO> pictureVOPage = pictureService.doSearchPicture(pictureQueryRequest);
        return pictureVOPage;
    }
}
