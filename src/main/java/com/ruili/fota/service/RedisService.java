package com.ruili.fota.service;

/**
 * Created by Yin on 2019/1/2
 */
public interface RedisService {
    /**
     * 设置key-value
     *
     * @param key
     * @param value
     */
    void setKey(String key, String value);

    /**
     * 获取key
     *
     * @param key
     * @return
     */
    String getValue(String key);

    /**
     * 删除key
     *
     * @param key
     */
    void delete(String key);

}
