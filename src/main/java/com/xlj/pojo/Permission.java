package com.xlj.pojo;

import java.io.Serializable;

/**
 * @Author XuLeJun
 * @Date 2020/7/21 19:48
 */

public class Permission implements Serializable {

    private String id;
    private String name;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
