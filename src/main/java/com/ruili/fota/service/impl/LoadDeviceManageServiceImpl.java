package com.ruili.fota.service.impl;

import com.ruili.fota.common.DateTools;
import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.constant.OnlineStatusEnum;
import com.ruili.fota.meta.po.FotaLoaders;
import com.ruili.fota.meta.vo.DeviceVO;
import com.ruili.fota.netty.pk.RegisterPK;
import com.ruili.fota.mapper.FotaLoadersMapper;
import com.ruili.fota.service.LoadDeviceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            //设备是旧设备
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
            fotaLoader.setGmtcreate(DateTools.currentTime());
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
    public List<DeviceVO> queryDeviceList() {
        List<DeviceVO> deviceVOList = new ArrayList<>();
        List<FotaLoaders> fotaLoadersList = fotaLoadersMapper.selectAll();
        for (FotaLoaders loaders : fotaLoadersList) {
            deviceVOList.add(new DeviceVO(loaders));
        }
        return deviceVOList;
    }
}
