package com.ruili.fota.meta.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yin on 2019/3/8
 * 角色-菜单 对应关系
 */
public class RoleMenuVO {
    /**
     * 角色id
     */
    private Integer id;
    /**
     * 角色 中文名
     */
    private String name;
    /**
     * 角色 英文名
     */
    private String value;
    /**
     * 角色的状态
     */
    private Integer status;
    /**
     * 菜单ID们
     */
    private List<Integer> mids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Integer> getMids() {
        return mids;
    }

    public void setMids(String mids) {
        this.mids = new ArrayList<>();

        if (mids == null || mids.length() == 0) {
            return;
        }
        String[] midsStr = mids.split(",");

        for (String midStr : midsStr) {
            this.mids.add(Integer.parseInt(midStr));
        }

    }
}
