package com.ruili.fota.service;


import com.ruili.fota.dao.po.FotaLoaders;
import com.ruili.fota.netty.pk.RegisterPK;

import java.util.List;

public interface LoadDeviceManageService {
    /**
     * 设备发送注册包后对设备进行：
     * 1、ConcurrentMapper加入设备连接信息(已在ServerHandler中完成)
     * 2、检查数据库是否有设备信息，insertOrUpdate设备状态
     *
     * @param registerPK
     * @return
     */
    public int deviceRegister(RegisterPK registerPK);

    /**
     * 处理设备掉线断开连接的情况：
     * 1、判断Channel的状态(已在ServerHandler中完成)
     * 2、从ConcurrentMapper去除设备连接信息(已在ServerHandler中完成)
     * 3、数据库Update设备的状态
     *
     * @param imei
     * @return
     */
    public int deviceUnConnect(String imei);

    /**
     * 查询设备的列表在前端渲染
     *
     * @return
     */
    public List<FotaLoaders> queryDeviceList();

}
