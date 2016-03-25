package com.xwheel.xmonitor.core.collector.serverstatus.entity;

/**
 * @author: lei hang
 * @date: 2016年03月22日
 * @description:
 */

public class DiskInfoVo {

    private String devName;//盘符名称
    private String dirName;//盘符路径
    private String sysTypeName;//文件系统类型，比如 FAT32、NTFS
    private String typeName;//文件系统类型名，比如本地硬盘、光驱、网络文件系统等
    private long totalSize;//总量 MB
    private long freeSize;//剩余量 MB
    private long availSize;//可用 MB
    private long usedSize;//已用 MB
    private String usePercent; //资源利用率

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getSysTypeName() {
        return sysTypeName;
    }

    public void setSysTypeName(String sysTypeName) {
        this.sysTypeName = sysTypeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getFreeSize() {
        return freeSize;
    }

    public void setFreeSize(long freeSize) {
        this.freeSize = freeSize;
    }

    public long getAvailSize() {
        return availSize;
    }

    public void setAvailSize(long availSize) {
        this.availSize = availSize;
    }

    public long getUsedSize() {
        return usedSize;
    }

    public void setUsedSize(long usedSize) {
        this.usedSize = usedSize;
    }

    public String getUsePercent() {
        return usePercent;
    }

    public void setUsePercent(String usePercent) {
        this.usePercent = usePercent;
    }

}
