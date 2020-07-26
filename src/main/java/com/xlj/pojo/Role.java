package com.xlj.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author XuLeJun
 * @Date 2020/7/21 19:47
 */

public class Role implements Serializable {

    private String id;
    private String name;

    // 某个角色所对应的多个权限
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
