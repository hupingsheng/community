package com.nowcoder.community.service;


import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    public List<DiscussPost> findDiscussPosts(Integer userId, Integer offset, Integer limit){
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    public Integer selectDiscussPostRows(Integer userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }

    public Integer addDiscussPost(DiscussPost post){
        if(post == null){
            throw new IllegalArgumentException("参数不能为空");
        }

        //转义HTML标记
        post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
        post.setContent((HtmlUtils.htmlEscape(post.getContent())));

        //过滤敏感词
        post.setTitle(sensitiveFilter.filter(post.getTitle()));
        post.setContent(sensitiveFilter.filter(post.getContent()));

        return discussPostMapper.insertDiscussPost(post);

    }

    public DiscussPost findDiscussPostById(Integer id){
        return discussPostMapper.selectDiscussPostById(id);
    }

    public Integer updateCommentCount(Integer id, Integer commentCount){
        return discussPostMapper.updateCommentCount(id, commentCount);
    }

}
