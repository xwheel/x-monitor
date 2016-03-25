package com.xwheel.xmonitor.core.collector.serverstatus.entity;

/**
 * @author: lei hang
 * @date: 2016年03月23日
 * @description:
 */

public class JvmInfoVo {

    private String javaServer;
    private String serverPath;
    private String serverTime;
    private String serverName;
    private String serverOs;
    private String javaHome;
    private String javaTmpPath;
    private String javaVersion;
    private long jvmTotalMemery;
    private long jvmFreeMemery;

    public String getJavaServer() {
        return javaServer;
    }

    public void setJavaServer(String javaServer) {
        this.javaServer = javaServer;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerOs() {
        return serverOs;
    }

    public void setServerOs(String serverOs) {
        this.serverOs = serverOs;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public void setJavaHome(String javaHome) {
        this.javaHome = javaHome;
    }

    public String getJavaTmpPath() {
        return javaTmpPath;
    }

    public void setJavaTmpPath(String javaTmpPath) {
        this.javaTmpPath = javaTmpPath;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public long getJvmTotalMemery() {
        return jvmTotalMemery;
    }

    public void setJvmTotalMemery(long jvmTotalMemery) {
        this.jvmTotalMemery = jvmTotalMemery;
    }

    public long getJvmFreeMemery() {
        return jvmFreeMemery;
    }

    public void setJvmFreeMemery(long jvmFreeMemery) {
        this.jvmFreeMemery = jvmFreeMemery;
    }
}
