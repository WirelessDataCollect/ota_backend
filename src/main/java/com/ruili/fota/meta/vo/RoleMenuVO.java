package com.ruili.fota.meta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuVO implements Serializable {
    private static final long serialVersionUID = -5411750465583575649L;
    /**
     * 角色id
     */
    private Integer gid;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"gid\":")
                .append(gid);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"value\":\"")
                .append(value).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"mids\":")
                .append(mids);
        sb.append('}');
        return sb.toString();
    }
}
