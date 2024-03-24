package com.yupi.springbootinit.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.model.enums.SearchTypeEnum;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataSourceRegistry {
    @Resource
    private PostDataSource postDataSource;
    @Resource
    private UserDataSource userDataSource;
    @Resource
    private PictureDataSource pictureDataSource;
    private static Map<String,DataSource<T>> dataSourceMap;

    @PostConstruct
    public void initDataSource(){
        dataSourceMap = new HashMap() {{
            put(SearchTypeEnum.USER.getValue(), userDataSource);
            put(SearchTypeEnum.PICTURE.getValue(), pictureDataSource);
            put(SearchTypeEnum.POST.getValue(), postDataSource);

        }};

    }

    public DataSource getDataSource(String type){
        DataSource dataSource = dataSourceMap.get(type);
        if(dataSource == null) {
            return null;
        }
        return dataSource;
    }
}
