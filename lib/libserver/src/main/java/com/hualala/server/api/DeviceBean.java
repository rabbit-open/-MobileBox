package com.hualala.server.api;

/**
 * 设备Bean
 * 只要IP一样，则认为是同一个设备
 */
public class DeviceBean {
    public String ip; // IP地址
    public int port; // 端口
    public String name; // 设备名称
    public String room; // 设备所在房间

    public DeviceBean(String ip, int port) {
        super();
        this.ip = ip;
        this.port = port;
    }

    public DeviceBean() {
    }

    @Override
    public int hashCode() {
        return ip.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeviceBean) {
            return this.ip.equals(((DeviceBean) o).getIp());
        }
        return super.equals(o);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
