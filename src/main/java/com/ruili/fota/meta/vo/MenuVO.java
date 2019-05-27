package com.ruili.fota.meta.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class MenuVO {

    private Integer key;

    private String title;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<MenuVO> children;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVO> children) {
        this.children = children;
    }
}
