package com.xwheel.xmonitor.portal.entity;

import java.io.Serializable;

/**
 * @author: lei hang
 * @date: 2015年12月07日
 * @description: 权限树
 */

public class SysAuthTree implements Serializable {
    //id
    private String id;
    //name
    private String name;
    //父节点
    private String pId;
    //是否选择，true，选中
    private boolean checked;
    //目录是否展开，true：展开
    private boolean open;

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

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
