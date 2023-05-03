package com.nowcoder.community.util;

import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.Map;
import java.util.UUID;

public class CommunityUtil {

    // 生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    // MD5加密
    // hello -> abc12345
    // hello + 3e4ab(盐) -> abc842424902
    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }

        return DigestUtils.md5DigestAsHex(key.getBytes());
    }


    //返回前端json字符串处理
    public static String getJsonString(Integer code, String msg, Map<String, Object> map){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if(map != null){
            for(String key : map.keySet()){
                json.put(key, map.get(key));
            }
        }
        return json.toJSONString();
    }

    public static String getJsonString(Integer code, String msg){
        return getJsonString(code, msg, null);
    }

    public static String getJsonString(Integer code){
        return getJsonString(code);
    }


}
