package com.ruili.fota.meta.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO implements Serializable {

    private static final long serialVersionUID = 9045805604574713456L;

    private Integer key;

    private String title;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<MenuVO> children;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"key\":")
                .append(key);
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"children\":")
                .append(children);
        sb.append('}');
        return sb.toString();
    }
}
