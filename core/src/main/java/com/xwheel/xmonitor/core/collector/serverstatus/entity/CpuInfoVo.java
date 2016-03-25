package com.xwheel.xmonitor.core.collector.serverstatus.entity;

import java.math.BigDecimal;

/**
 * @author: lei hang
 * @date: 2016年03月22日
 * @description: 数据库对象信息：
 * 将多核CPU视为一个CPU:同一型号CPU
 * http://cpansearch.perl.org/src/DOUGM/hyperic-sigar-1.6.3-src/docs/javadoc/org/hyperic/sigar/CpuInfo.html
 * vendor: 获得CPU的卖主
 * model :获得CPU的类别
 * mhz :总量MHz
 * cacheSize :缓冲存储器数量
 * totalCores :Get the Total CPU cores (logical).
 * totalSockets :Get the Total CPU sockets (physical).
 * coresPerSocket: Get the Number of CPU cores per CPU socket.
 */

public class CpuInfoVo {

    private int totalMhz;//总量MHz
    private String vendor;//获得CPU的卖主
    private String model;//获得CPU的类别
    private long cacheSize;//缓冲存储器数量
    private double idle;//空闲率
    private double used;//使用率
    private int coreCount;//核数
    //

    public int getTotalMhz() {
        return totalMhz;
    }

    public void setTotalMhz(int totalMhz) {
        this.totalMhz = totalMhz;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(long cacheSize) {
        this.cacheSize = cacheSize;
    }

    public double getIdle() {
        return idle;
    }

    public void setIdle(double idle) {
        this.idle = idle;
    }

    public double getUsed() {
        return used;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }

    /**
     * 空闲率百分比值
     */
    public double getIdlePercent() {
        return new BigDecimal(idle * 100 / coreCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 使用率百分比值
     */
    public double getUsedPercent() {
        return new BigDecimal(used * 100 / coreCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
