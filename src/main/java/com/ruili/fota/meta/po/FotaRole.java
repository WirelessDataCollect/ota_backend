package com.ruili.fota.meta.po;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
/**
* @description:
*
* @author: jingxiong.ljx
* @date: 2019-07-27
*/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FotaRole implements Serializable {

    private static final long serialVersionUID = 2411815820444142434L;

    private Integer gid;

    private String name;

    private String value;

    private String info;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtcreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtupdate;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"gid\":")
                .append(gid);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"value\":\"")
                .append(value).append('\"');
        sb.append(",\"info\":\"")
                .append(info).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"gmtcreate\":\"")
                .append(gmtcreate).append('\"');
        sb.append(",\"gmtupdate\":\"")
                .append(gmtupdate).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public JSONObject toJSONObjContent(){
        JSONObject object = new JSONObject();
        object.put("name",this.name);
        object.put("value",this.value);
        return object;
    }
}