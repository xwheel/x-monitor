package com.xwheel.xmonitor.commons.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lei hang
 * @date: 2015年09月02日
 * @description: 返回的json格式数据
 */

public class JsonStore implements Serializable {

    public List rows = new ArrayList();

    public long total = 0;

    public JsonStore(List rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}