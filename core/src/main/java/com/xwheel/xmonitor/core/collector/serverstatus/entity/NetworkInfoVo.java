package com.xwheel.xmonitor.core.collector.serverstatus.entity;

/**
 * @author: lei hang
 * @date: 2016年03月23日
 * @description:
 */

public class NetworkInfoVo {
    //网络设备名
    String name = null;
    //MAC
    String hwaddr = null;
    //ip地址
    String address = null;
    //子网掩码
    String netmask = null;
    //接收到的总字节数
    long rxBytes = 0L;
    //接收的总包裹数
    long rxPackets = 0L;
    //接收到的错误包数
    long rxErrors = 0L;
    //接收时丢弃的包数
    long rxDropped = 0L;
    //发送的总字节数
    long txBytes = 0L;
    //发送的总包裹数
    long txPackets = 0L;
    //发送的错误包数
    long txErrors = 0L;
    //发送的丢弃的包数
    long txDropped = 0L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHwaddr() {
        return hwaddr;
    }

    public void setHwaddr(String hwaddr) {
        this.hwaddr = hwaddr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public long getRxBytes() {
        return rxBytes;
    }

    public void setRxBytes(long rxBytes) {
        this.rxBytes = rxBytes;
    }

    public long getRxPackets() {
        return rxPackets;
    }

    public void setRxPackets(long rxPackets) {
        this.rxPackets = rxPackets;
    }

    public long getRxErrors() {
        return rxErrors;
    }

    public void setRxErrors(long rxErrors) {
        this.rxErrors = rxErrors;
    }

    public long getRxDropped() {
        return rxDropped;
    }

    public void setRxDropped(long rxDropped) {
        this.rxDropped = rxDropped;
    }

    public long getTxBytes() {
        return txBytes;
    }

    public void setTxBytes(long txBytes) {
        this.txBytes = txBytes;
    }

    public long getTxPackets() {
        return txPackets;
    }

    public void setTxPackets(long txPackets) {
        this.txPackets = txPackets;
    }

    public long getTxErrors() {
        return txErrors;
    }

    public void setTxErrors(long txErrors) {
        this.txErrors = txErrors;
    }

    public long getTxDropped() {
        return txDropped;
    }

    public void setTxDropped(long txDropped) {
        this.txDropped = txDropped;
    }
}
