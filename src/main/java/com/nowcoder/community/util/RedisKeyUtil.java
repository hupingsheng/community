package com.nowcoder.community.util;

public class RedisKeyUtil {

    private static final String SPLIT = ":";

    private static final String PREFIX_ENTITY_LIKE = "like:entity";

    private static final String PREFIX_USER_LIKE = "like:user";

    private static final String PREFIX_FOLLOWEE = "followee";

    private static final String PREFIX_FOLLOWER = "follower";

    private static final String PREFIX_KAPTCHA = "kaptcha";

    private static final String PREFIX_TICKET = "ticket";

    //在redis中表示某个实体的赞
    //like:entity:entity_type:entity_id -> set(userId)
    public static String getEntityLikeKey(Integer entityType, Integer entityId){
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    //某个用户的赞
    public static String getUserLikeKey(Integer userId){
        return PREFIX_USER_LIKE + SPLIT + userId;
    }

    //某个用户关注的实体【用户，帖子】   关注的人、帖子
    // followee:userId:entityType -> zset(entityId, now)
    public static String getFolloweeKey(Integer userId, Integer entityType){
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    //某个实体【用户，帖子】拥有的粉丝    谁关注的我【我：帖子、人】
    //follower:entityType:entityId -> zset(userId, now)
    public static String getFollowerKey(Integer entityType, Integer entityID){
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityID;
    }

    //登录验证码
    public static String getKaptchaKey(String owner){
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    //登录凭证
    public static String getTicketKey(String ticket){
        return PREFIX_TICKET + SPLIT + ticket;
    }
}
