package com.ruili.fota.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
* @author: liangjingxiong
* @date: 2019-04-03
* @description:
 * BaseMapper特别注意，该接口不能被扫描到，否则会出错
*/
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}