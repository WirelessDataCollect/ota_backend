package com.ruili.fota.constant;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: jingxiong.ljx
 * @Date: 2019-07-28
 */
@AllArgsConstructor
@NoArgsConstructor
public enum UserTypeEnum {

    ADMIN("ADMIN", 5),

    USER("USER", 3);

    private String type;
    private Integer code;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
