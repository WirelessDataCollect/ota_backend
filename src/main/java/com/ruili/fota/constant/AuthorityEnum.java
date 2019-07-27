package com.ruili.fota.constant;

import java.io.Serializable;


public enum AuthorityEnum implements Serializable {
    /**
     * 菜单类型 微信菜单
     */
    MENU_TYPE_WX(0, "微信菜单"),
    /**
     * 菜单类型 PC菜单
     */
    MENU_TYPE_PC(1, "PC菜单");

    private Integer type;

    private String description;

    AuthorityEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
