package com.nowcoder.community.dao;


import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * (DiscussPost)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-30 19:08:21
 */
@Mapper
public interface DiscussPostMapper {

    /**
     * @param userId  用户id
     * @param offset  分页查询起始行 行号
     * @param limit   分页查询 每页显示的数量
     * @return
     */
    List<DiscussPost> selectDiscussPosts(Integer userId, Integer offset, Integer limit);

    /**
     * @param注解用于参数取别名的
     * 如果只有一个参数，并且在<if>里使用，则必须使用别名
     * @param userId
     * @return
     */
    Integer selectDiscussPostRows(@Param("userId") Integer userId);

    Integer insertDiscussPost(DiscussPost discussPost);


    DiscussPost selectDiscussPostById(Integer id);


    Integer updateCommentCount(Integer id, Integer commentCount);
}

