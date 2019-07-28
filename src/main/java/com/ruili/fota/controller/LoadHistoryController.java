package com.ruili.fota.controller;

import com.ruili.fota.constant.UserTypeEnum;
import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import com.ruili.fota.meta.po.FotaRole;
import com.ruili.fota.meta.vo.OtaHistoryVO;
import com.ruili.fota.service.AuthorityService;
import com.ruili.fota.service.LoadHistoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/history")
public class LoadHistoryController extends BaseController {

    private static String urlPrefix = "otaHistory";

    @Autowired
    private LoadHistoryService loadHistoryService;
    @Autowired
    private AuthorityService authorityService;

    @ApiOperation(value = "固件 下载 历史查询", tags = {"固件管理"}, notes = "查询设备固件升级历史")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "imei", value = "升级设备的imei", required = true),
        @ApiImplicitParam(name = "beginTime", value = "查询开始时间", required = true),
        @ApiImplicitParam(name = "endTime", value = "查询结束时间", required = true)
    })
    @PostMapping(value = "/query")
    public BaseResp<List<OtaHistoryVO>> historyQuery(@RequestParam("imei") String imei,
        @RequestParam("userName") String userName,
        @RequestParam("beginTime") String beginTime,
        @RequestParam("endTime") String endTime) {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }
        String currentUser = this.findCurrentUser().getUsername();
        List<FotaRole> roles = authorityService.getRoleByUser(userName);
        //只有超级用户才可以看到指定用户的记录
        for (FotaRole role : roles) {
            if (role.getValue().equals(UserTypeEnum.ADMIN.getType())){
                return new BaseResp(ResultStatus.SUCCESS,
                    loadHistoryService.queryLoadHistory(imei, beginTime, endTime,userName));
            }
        }
        return new BaseResp(ResultStatus.SUCCESS,
            loadHistoryService.queryLoadHistory(imei, beginTime, endTime, currentUser));
    }
}
