package com.nowcoder.community.util;

public interface CommunityConstant {

    /**
     * 激活成功
     */
    Integer ACTIVATION_SUCCESS = 0;

    /**
     * 重复激活
     */
    Integer ACTIVATION_REPEATE = 1;

    /**
     * 激活失败
     */
    Integer ACTIVATION_FAILURE = 2;

    /**
     * 默认状态的登录凭证的超时时间
     */
    Integer DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * remeberMe状态下的超时时间
     */
    Integer REMEMBERME_EXPIRED_SECONDS = 3600 * 90;

    /**
     * 实体类型  帖子
     */
    Integer ENTITY_TYPE_POST = 1;

    /**
     * 实体类型  评论
     */
    Integer ENTITY_TYPE_COMMENT = 2;


}
