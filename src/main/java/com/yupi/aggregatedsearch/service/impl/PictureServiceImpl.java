package com.yupi.aggregatedsearch.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.aggregatedsearch.model.dto.picture.PictureQueryRequest;
import com.yupi.aggregatedsearch.model.vo.PictureVO;
import com.yupi.aggregatedsearch.service.PictureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {
    public Page<PictureVO> doSearchPicture(PictureQueryRequest pictureQueryRequest) {
        String searchText = pictureQueryRequest.getSearchText();
        long current = pictureQueryRequest.getCurrent();
        long pageSize = pictureQueryRequest.getPageSize();
        long num = ( current - 1) * pageSize;
        String url = "https://www.bing.com/images/search?q=" + searchText + "&form=HDRSC3&first=" +num +  "&cw=1177&ch=894";
        // 拿到 html 页面内容
        Document doc = null;
        List<PictureVO> voList = new ArrayList<>();
        try {
            doc = Jsoup.connect(url).get();
            Elements elements = doc.select(".iuscp.isv.smallheight");
            for (Element element : elements) {
                String json = element.select(".iusc").attr("m");
                Map<String,String> map = JSONUtil.toBean(json, Map.class);
                String imgUrl = map.get("murl");
                String title = map.get("t");
                PictureVO pictureVO = new PictureVO();
                pictureVO.setSearchText(title);
                pictureVO.setUrl(imgUrl);
                voList.add(pictureVO);
                if(voList.size() >= pageSize) {
                    break;
                }
            }
            // 返回数据
            Page<PictureVO> pictureVOPage = new Page<>(current, pageSize);
            pictureVOPage.setRecords(voList);
            return pictureVOPage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
