package com.ruili.fota.service.impl;

import com.ruili.fota.common.utils.DateTools;
import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.constant.OnlineStatusEnum;
import com.ruili.fota.dao.po.FotaLoaders;
import com.ruili.fota.netty.pk.RegisterPK;
import com.ruili.fota.dao.mapper.FotaLoadersMapper;
import com.ruili.fota.service.LoadDeviceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadDeviceManageServiceImpl implements LoadDeviceManageService {

    @Autowired
    private FotaLoadersMapper fotaLoadersMapper;

    @Override
    public int deviceRegister(RegisterPK registerPK) {

        FotaLoaders fotaLoader = new FotaLoaders();
        fotaLoader.setImei(registerPK.getImei());
        FotaLoaders theFotaLoader = fotaLoadersMapper.selectOne(fotaLoader);

        if (theFotaLoader != null) {
            //如果设备是旧设备
            theFotaLoader.setOnlineStatus(OnlineStatusEnum.ONLINE_STATUS.getCode());
            theFotaLoader.setGmtcreate(DateTools.currentTime());
            theFotaLoader.setGmtupdate(DateTools.currentTime());
            theFotaLoader.setGmtmodified(DateTools.currentTime());
            return fotaLoadersMapper.updateByPrimaryKeySelective(theFotaLoader);
        } else {
            //设备是新设备
            fotaLoader.setImsi(registerPK.getImsi());
            fotaLoader.setCsq(registerPK.getCsq());
            fotaLoader.setLoadStatus(LoadStatusEnum.LOAD_NO_STATUS.getCode());
            fotaLoader.setOnlineStatus(OnlineStatusEnum.ONLINE_STATUS.getCode());
            theFotaLoader.setGmtcreate(DateTools.currentTime());
            fotaLoader.setGmtupdate(DateTools.currentTime());
            fotaLoader.setGmtmodified(DateTools.currentTime());
            return fotaLoadersMapper.insert(fotaLoader);
        }
    }

    @Override
    public int deviceUnConnect(String imei) {
        FotaLoaders fotaLoader = new FotaLoaders();
        fotaLoader.setImei(imei);
        FotaLoaders theFotaLoader = fotaLoadersMapper.selectOne(fotaLoader);
        theFotaLoader.setOnlineStatus(OnlineStatusEnum.OFFINE_STATUS.getCode());
        return fotaLoadersMapper.updateByPrimaryKeySelective(theFotaLoader);
    }

    @Override
    public List<FotaLoaders> queryDeviceList() {
        return fotaLoadersMapper.selectAll();
    }
}
