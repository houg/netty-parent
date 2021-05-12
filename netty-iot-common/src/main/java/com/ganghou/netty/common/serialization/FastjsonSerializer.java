package com.ganghou.netty.common.serialization;

import com.alibaba.fastjson.JSON;

/**
 * @Description: Fastjson序列化
 * @author hougang_ahut@163.com
 * @date 2021年5月12日
 * @version V1.0
 */
public class FastjsonSerializer  extends  AbstractSerialize {

    private static  FastjsonSerializer serializer = new FastjsonSerializer();

    public static FastjsonSerializer getSingleton(){
        return  serializer;
    }


    public <T> byte[] serialize(T obj) {
        if (obj  == null){
            throw new NullPointerException();
        }

        String json = JSON.toJSONString(obj);
        byte[] data = json.getBytes();
        return data;
    }

    public <T> T deserialize(byte[] data, Class<T> clazz) {

        T obj = JSON.parseObject(new String(data),clazz);
        return obj;
    }
}
