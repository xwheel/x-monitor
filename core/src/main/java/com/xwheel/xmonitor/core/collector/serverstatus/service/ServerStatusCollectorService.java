package com.xwheel.xmonitor.core.collector.serverstatus.service;

import com.xwheel.xmonitor.core.collector.serverstatus.entity.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hyperic.sigar.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.*;

/**
 * @author: lei hang
 * @date: 2016年03月22日
 * @description:
 */
@Service
public class ServerStatusCollectorService {

    private static final Logger logger = LoggerFactory.getLogger(ServerStatusCollectorService.class);

    Sigar sigar = null;
    ServerStatusVo serverStatusVo = null;

    public void collectStatus() {
//        logger.debug("java.library.path is : {}", System.getProperty("java.library.path"));
        long startTime = System.currentTimeMillis();
        try {
            sigar = new Sigar();
            serverStatusVo = new ServerStatusVo();
            getSystemCpuInfo(serverStatusVo);
            getSystemDiskInfo(serverStatusVo);
            getSystemMemInfo(serverStatusVo);
            getSystemNetworkInfo(serverStatusVo);
            getJvmInfo(serverStatusVo);


        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("collectStatus finish , cost time : {}" , System.currentTimeMillis()-startTime);
    }

    private void getJvmInfo(ServerStatusVo serverStatusVo) throws SigarException {
        JvmInfoVo jvmInfoVo = new JvmInfoVo();
        jvmInfoVo.setServerTime(DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
        jvmInfoVo.setServerName(System.getenv().get("COMPUTERNAME"));
        Runtime rt = Runtime.getRuntime();
        jvmInfoVo.setJvmTotalMemery(rt.totalMemory() / (1024 * 1024));
        jvmInfoVo.setJvmFreeMemery(rt.freeMemory() / (1024 * 1024));

        Properties props = System.getProperties();
        jvmInfoVo.setServerOs(props.getProperty("os.name") + " " + props.getProperty("os.arch") + " " + props.getProperty("os.version"));
        jvmInfoVo.setJavaHome(props.getProperty("java.home"));
        jvmInfoVo.setJavaVersion(props.getProperty("java.version"));
        jvmInfoVo.setJavaTmpPath(props.getProperty("java.io.tmpdir"));

        serverStatusVo.setJvmInfoVo(jvmInfoVo);
    }

    /**
     * 采集网络信息，只采集一个Ip地址的情况
     *
     * @param serverStatusVo
     * @throws SigarException
     */
    private void getSystemNetworkInfo(ServerStatusVo serverStatusVo) throws Exception {
        NetworkInfoVo networkInfoVo = new NetworkInfoVo();
        // 取到当前机器的IP地址
        String address = InetAddress.getLocalHost().getHostAddress();
        networkInfoVo.setAddress(address);
        //获取所有网卡信息
        String[] ifaces = sigar.getNetInterfaceList();
        for (int i = 0; i < ifaces.length; i++) {
            NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
                    || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
            if (address.equals(cfg.getAddress())) {
                networkInfoVo.setName(ifaces[i]);
                networkInfoVo.setAddress(cfg.getAddress());// IP地址
                networkInfoVo.setNetmask(cfg.getNetmask());// 子网掩码
                //MAC地址
                networkInfoVo.setHwaddr(cfg.getHwaddr());
                //流量信息
                NetInterfaceStat stat = sigar.getNetInterfaceStat(ifaces[i]);
                networkInfoVo.setRxPackets(stat.getRxPackets());// 接收的总包裹数
                networkInfoVo.setTxPackets(stat.getTxPackets());// 发送的总包裹数
                networkInfoVo.setTxBytes(stat.getTxBytes());// 发送的总字节数
                networkInfoVo.setRxErrors(stat.getRxErrors());// 接收到的错误包数
                networkInfoVo.setTxErrors(stat.getTxErrors());// 发送数据包时的错误数
                networkInfoVo.setRxDropped(stat.getRxDropped());// 接收时丢弃的包数
                networkInfoVo.setTxDropped(stat.getTxDropped());// 发送时丢弃的包数
                break;
            }
        }
        serverStatusVo.setNetworkInfoVo(networkInfoVo);
    }

    /**
     * 获取系统磁盘信息
     *
     * @param serverStatusVo
     * @throws SigarException
     */
    private void getSystemDiskInfo(ServerStatusVo serverStatusVo) throws SigarException {

        List<DiskInfoVo> diskInfoVos = new ArrayList<>();
        FileSystem fslist[] = sigar.getFileSystemList();
        FileSystemUsage usage = null;
        for (int i = 0; i < fslist.length; i++) {
            FileSystem fs = fslist[i];
            switch (fs.getType()) {
                case 0: // TYPE_UNKNOWN ：未知
                case 1: // TYPE_NONE
                case 3:// TYPE_NETWORK ：网络
                case 4:// TYPE_RAM_DISK ：闪存
                case 5:// TYPE_CDROM ：光驱
                case 6:// TYPE_SWAP ：页面交换
                    break;
                case 2: // TYPE_LOCAL_DISK : 本地硬盘
                    DiskInfoVo disk = new DiskInfoVo();
                    disk.setDevName(fs.getDevName());
                    disk.setDirName(fs.getDirName());
                    usage = sigar.getFileSystemUsage(fs.getDirName());
                    disk.setTotalSize(usage.getTotal() / (1024 * 1024));
                    disk.setFreeSize(usage.getFree() / (1024 * 1024));
                    disk.setAvailSize(usage.getAvail() / (1024 * 1024));
                    disk.setUsedSize(usage.getUsed() / (1024 * 1024));
                    disk.setUsePercent(usage.getUsePercent() * 100D + "%");
                    disk.setTypeName(fs.getTypeName());
                    disk.setSysTypeName(fs.getSysTypeName());
                    diskInfoVos.add(disk);
            }
        }
        serverStatusVo.getDiskInfoVos().addAll(diskInfoVos);
    }

    private void getSystemMemInfo(ServerStatusVo serverStatusVo) throws SigarException {
        // 物理内存信息
        Mem mem = sigar.getMem();
        MemoryInfoVo memoryInfoVo = new MemoryInfoVo();
        memoryInfoVo.setTotalMem(mem.getTotal() / 1024L / 1024);
        memoryInfoVo.setUsedMem(mem.getUsed() / 1024L / 1024);
        memoryInfoVo.setFreeMem(mem.getFree() / 1024L / 1024);
        Swap swap = sigar.getSwap();
        memoryInfoVo.setTotalSwap(swap.getTotal() / 1024L);
        memoryInfoVo.setUsedSwap(swap.getUsed() / 1024L);
        memoryInfoVo.setFreeSwap(swap.getFree() / 1024L);
        serverStatusVo.setMemoryInfoVos(memoryInfoVo);
    }

    /**
     * 获取系统CPU信息
     *
     * @param serverStatusVo
     * @throws SigarException
     */
    private void getSystemCpuInfo(ServerStatusVo serverStatusVo) throws SigarException {

        List<CpuInfoVo> cpuDatas = new ArrayList<>();
        Map<String, CpuInfoVo> cpuMap = new HashMap<>();
        //cpu number
        CpuInfo cpuInfos[] = sigar.getCpuInfoList();
        //cpu状态
        CpuPerc cpuPercs[] = sigar.getCpuPercList();
        CpuInfoVo vo;
        for (int i = 0; i < cpuInfos.length; i++) {
            CpuInfo cpuInfo = cpuInfos[i];
            CpuPerc cpuPerc = cpuPercs[i];
            if (!cpuMap.containsKey(cpuInfo.getModel())) {
                vo = new CpuInfoVo();
                vo.setCacheSize(cpuInfo.getCacheSize());
                vo.setModel(cpuInfo.getModel());
                vo.setTotalMhz(cpuInfo.getMhz());
                vo.setVendor(cpuInfo.getVendor());
                cpuMap.put(cpuInfo.getModel(), vo);
            } else {
                vo = cpuMap.get(cpuInfo.getModel());
            }
            //计算使用量
            vo.setUsed(vo.getUsed() + cpuPerc.getCombined());
            vo.setIdle(vo.getIdle() + cpuPerc.getIdle());
            vo.setCoreCount(vo.getCoreCount() + 1);
            cpuDatas.add(vo);
        }
        serverStatusVo.getCpuInfoVos().addAll(cpuMap.values());
    }

}
