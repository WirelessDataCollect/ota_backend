package com.ruili.fota.service.impl;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * Created by Yin on 2019/2/11
 */
public class BaseService {

    /**
     * 通用 根据属性查找
     *
     * @param property
     * @param value
     * @param objectMapper
     * @param objectClass
     * @return
     */
    public List findObjectsByProperty(String property, Object value, Mapper<?> objectMapper, Class<?> objectClass) {
        Example example = new Example(objectClass);
        example.createCriteria().andEqualTo(property, value);
        return objectMapper.selectByExample(example);
    }

    /**
     * 通用 根据属性【们】查找
     *
     * @param properties
     * @param objectMapper
     * @param objectClass
     * @return
     */
    public List findObjectsByProperty(Map<String, Object> properties, Mapper<?> objectMapper, Class<?> objectClass) {
        Example example = new Example(objectClass);
        Example.Criteria criteria = example.createCriteria();

        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            criteria.andEqualTo(entry.getKey(), entry.getValue());
        }

        return objectMapper.selectByExample(example);
    }

    //object转double
    public double oToD(Object o) {
        double d = Double.parseDouble(o.toString());
        return d;
    }
}
