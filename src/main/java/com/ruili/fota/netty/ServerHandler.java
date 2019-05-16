package com.ruili.fota.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruili.fota.netty.pk.*;
import com.ruili.fota.service.FirmwareService;
import com.ruili.fota.service.LoadDeviceManageService;
import com.ruili.fota.service.LoadHistoryService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: liangjingxiong
 * @date: 2019-04-09
 * @description: 消息解析器，接收到的消息在这个类中进行处理，in入站的消息处理类
 */

@Component
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {


    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    @Autowired
    private LoadDeviceManageService deviceManageService;
    @Autowired
    private FirmwareService firmwareService;
    @Autowired
    private LoadHistoryService loadHistoryService;

    /**
     * 是一个生命周期函数内发生的回调函数，当出现消息读取时自动执行调用
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //注册
        if (ifContains(msg.toString(), CommandType.RIGISTER)) {
            System.out.println("===设备注册包===");
            System.out.println(msg.toString());
            System.out.println("===============");
            RegisterPK registerPK = JSON.parseObject(msg.toString(), RegisterPK.class);
            deviceManageService.deviceRegister(registerPK);
            NettyChannelMap.add(registerPK.getImei(), (SocketChannel) ctx.channel());
            ctx.writeAndFlush(getWriteBuf(CommandType.RIGISTER_ACK.getType(), ctx));
        }
        if (ifContains(msg.toString(), CommandType.CONFIG_ACK)) {
            System.out.println("===设备配置指令回复===");
            System.out.println(msg.toString());
            System.out.println("====================");
            System.out.println("开始下发固件");
            ConfigPK_ACK ack = JSON.parseObject(msg.toString(), ConfigPK_ACK.class);
            firmwareService.downloadFirmware(ack.getImei(), 0);

        }
        if (ifContains(msg.toString(), CommandType.REQUEST_PACK)) {
            System.out.println("===设备请求包===");
            System.out.println(msg.toString());
            System.out.println("===============");
            RequestPK requestPK = JSON.parseObject(msg.toString(), RequestPK.class);
            firmwareService.downloadFirmware(requestPK.getImei(), requestPK.getPacknum());
        }
        if (ifContains(msg.toString(), CommandType.UPDATE_OK)) {
            System.out.println("===设备升级完成===");
            System.out.println(msg.toString());
            System.out.println("=================");
            UpdateokPK pk = JSON.parseObject(msg.toString(), UpdateokPK.class);
            //插入数据库一条记录
            loadHistoryService.insertLoadHistoryByProcessEntity(pk.getImei());
            //移除Map通道数据
            FotaProcessMap.removeByImei(pk.getImei());
        }
        if (ifContains(msg.toString(), CommandType.UPDATE_ERROR)) {
            System.out.println("===设备升级报错===");
            System.out.println(msg.toString());
            System.out.println("=================");
            UpdateErrorPK errorPK = JSON.parseObject(msg.toString(), UpdateErrorPK.class);
            //插入数据库一条记录
            loadHistoryService.insertLoadHistoryByProcessEntity(errorPK.getImei());
            //删除设备更新的配置内存，从头开始
            FotaProcessMap.removeByImei(errorPK.getImei());
        }
    }

    /**
     * 设备上线会调用
     * 1.检查可更新设备在线情况
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.warn("---" + ctx.channel().remoteAddress() + " is active---");
        //若是存在数据库中的设备，则更新设备的在线状态
    }

    /**
     * 终端主动断开会导致Channel不活跃，设备下线的场景在此函数进行解决
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.error("***" + ctx.channel().remoteAddress() + " is inactive***");
        String imei = NettyChannelMap.remove((SocketChannel) ctx.channel());
        if (imei != null) {
            //设备离线记录
            deviceManageService.deviceUnConnect(imei);
        }
        ctx.close();
    }

    /**
     * 检测msg中是否包含key
     *
     * @param msg
     * @param key
     * @return
     */
    public boolean ifContains(String msg, CommandType key) {
        return StringUtils.contains(msg, key.getType());
    }

    public ByteBuf getWriteBuf(String res, ChannelHandlerContext ctx) {
        ByteBuf responseBuf = ctx.alloc().buffer(4 * res.length());
        responseBuf.writeBytes(res.getBytes());
        return responseBuf;
    }

    public ByteBuf getWriteBuf(JSONObject jsonObject, ChannelHandlerContext ctx) {
        String res = jsonObject.toJSONString();
        ByteBuf responseBuf = ctx.alloc().buffer(4 * res.length());
        responseBuf.writeBytes(res.getBytes());
        return responseBuf;
    }

}
