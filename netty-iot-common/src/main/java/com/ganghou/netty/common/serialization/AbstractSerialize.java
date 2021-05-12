package com.ganghou.netty.common.serialization;

/**
 * @Description: 对象序列化抽象类
 * @author hougang_ahut@163.com
 * @date 2021年5月12日
 * @version V1.0
 */
public abstract class AbstractSerialize {

    public  abstract   <T> byte[] serialize(T obj);
    public abstract  <T> T deserialize(byte[] data, Class<T> clazz);

}
