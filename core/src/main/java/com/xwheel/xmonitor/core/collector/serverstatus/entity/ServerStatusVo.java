package com.xwheel.xmonitor.core.collector.serverstatus.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lei hang
 * @date: 2016年03月22日
 * @description:
 */

public class ServerStatusVo {

    private List<CpuInfoVo> cpuInfoVos = new ArrayList<>();
    private List<DiskInfoVo> diskInfoVos = new ArrayList<>();
    private MemoryInfoVo memoryInfoVos;
    private JvmInfoVo jvmInfoVo;
    private NetworkInfoVo networkInfoVo;

    public List<CpuInfoVo> getCpuInfoVos() {
        return cpuInfoVos;
    }

    public void setCpuInfoVos(List<CpuInfoVo> cpuInfoVos) {
        this.cpuInfoVos = cpuInfoVos;
    }

    public List<DiskInfoVo> getDiskInfoVos() {
        return diskInfoVos;
    }

    public void setDiskInfoVos(List<DiskInfoVo> diskInfoVos) {
        this.diskInfoVos = diskInfoVos;
    }

    public MemoryInfoVo getMemoryInfoVos() {
        return memoryInfoVos;
    }

    public void setMemoryInfoVos(MemoryInfoVo memoryInfoVos) {
        this.memoryInfoVos = memoryInfoVos;
    }

    public JvmInfoVo getJvmInfoVo() {
        return jvmInfoVo;
    }

    public void setJvmInfoVo(JvmInfoVo jvmInfoVo) {
        this.jvmInfoVo = jvmInfoVo;
    }

    public NetworkInfoVo getNetworkInfoVo() {
        return networkInfoVo;
    }

    public void setNetworkInfoVo(NetworkInfoVo networkInfoVo) {
        this.networkInfoVo = networkInfoVo;
    }
}
