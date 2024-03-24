package com.yupi.springbootinit.service;
import java.io.IOException;
import java.util.*;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.model.dto.post.PostQueryRequest;
import com.yupi.springbootinit.model.entity.Post;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子服务测试
 *
 * @author <a href="https://github.com/7Ailun">艾伦</a>
 *  
 */
@SpringBootTest
class PostServiceTest {

    @Resource
    private PostService postService;
    @Test
    void reptileImage() throws IOException {
        String searchText = "蔡徐坤";
        String url = "https://www.bing.com/images/search?q=" + searchText + "&form=HDRSC3&first=1&cw=1177&ch=894";
        // 拿到 html 页面内容
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".iuscp.isv.smallheight");
        for (Element element : elements) {
            String json = element.select(".iusc").attr("m");
            Map<String,String> map = JSONUtil.toBean(json, Map.class);
            String imgUrl = map.get("murl");
            String title = map.get("t");
            System.out.println("title = " + title);
            System.out.println("imgUrl = " + imgUrl);

        }
    }

    @Test
    void reptileText() {
        String body = "{\"current\":1,\"pageSize\":8,\"sortField\":\"createTime\",\"sortOrder\":\"descend\",\"category\":\"文章\",\"tags\":[],\"reviewStatus\":1}";
        String url = "https://www.code-nav.cn/api/post/search/page/vo";
        String result = HttpRequest
                .post(url)
                .header(Header.CONTENT_TYPE, "application/json")
                .body(body)
                .execute()
                .body();

        System.out.println("result = " + result);
        Map<String,Object> map = JSONUtil.toBean(result, Map.class);
        JSONObject data = (JSONObject)map.get("data");
        JSONArray records = (JSONArray)data.get("records");
        List<Post> list = new ArrayList<>();
        for (Object object : records) {
            JSONObject tempObject = (JSONObject) object;
            String title = tempObject.get("title").toString();
            String content = tempObject.get("content").toString();
            JSONArray tags = (JSONArray)tempObject.get("tags");
            String jsonTags = JSONUtil.toJsonStr(tags);
            Post post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.setTags(jsonTags);
            post.setUserId(1769394210533842946L);
            list.add(post);
        }
        boolean isTrue = postService.saveBatch(list);
        Assert.isTrue(isTrue);
    }

    @Test
    void searchFromEs() {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setUserId(1L);
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        Assertions.assertNotNull(postPage);
    }

}