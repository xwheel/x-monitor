package com.xwheel.xmonitor.core.collector.serverstatus.entity;

/**
 * @author: lei hang
 * @date: 2016年03月22日
 * @description:
 */

public class MemoryInfoVo {
    // 物理内存信息 单位MB
    private long totalMem; //内存总量
    private long usedMem;//当前内存使用量
    private long freeMem;//当前内存剩余量
    // 系统页面文件交换区信息 单位KB
    private long totalSwap;//交换区总量
    private long usedSwap;//当前交换区使用量
    private long freeSwap;//当前交换区剩余量

    public long getTotalMem() {
        return totalMem;
    }

    public void setTotalMem(long totalMem) {
        this.totalMem = totalMem;
    }

    public long getUsedMem() {
        return usedMem;
    }

    public void setUsedMem(long usedMem) {
        this.usedMem = usedMem;
    }

    public long getFreeMem() {
        return freeMem;
    }

    public void setFreeMem(long freeMem) {
        this.freeMem = freeMem;
    }

    public long getTotalSwap() {
        return totalSwap;
    }

    public void setTotalSwap(long totalSwap) {
        this.totalSwap = totalSwap;
    }

    public long getUsedSwap() {
        return usedSwap;
    }

    public void setUsedSwap(long usedSwap) {
        this.usedSwap = usedSwap;
    }

    public long getFreeSwap() {
        return freeSwap;
    }

    public void setFreeSwap(long freeSwap) {
        this.freeSwap = freeSwap;
    }
}
