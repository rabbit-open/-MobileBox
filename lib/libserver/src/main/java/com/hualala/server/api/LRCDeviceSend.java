package com.hualala.server.api;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;

/**
 * 歌词发送器件
 * Created by zjun on 2016/9/3.
 */
public class LRCDeviceSend extends Thread {
    private static final String TAG = LRCDeviceSend.class.getSimpleName();
    private static final int RECEIVE_TIME_OUT = 2000; // 接收超时时间
    private static final byte PACKET_TYPE_SEND_LRC = 0x20; // 发送数据
    private DatagramSocket hostSocket;
    private DeviceBean mDeviceSet;
    private String mContent;
    private byte mPackType;

    public LRCDeviceSend(DeviceBean deviceSet, String content) {
        mDeviceSet = deviceSet;
        mContent = content;
    }

    @Override
    public void run() {
        try {
            hostSocket = new DatagramSocket(); // 设置接收超时时间
            hostSocket.setSoTimeout(RECEIVE_TIME_OUT);
            byte[] sendData = new byte[1024];
            DatagramPacket sendPack =
                    new DatagramPacket(sendData, sendData.length, InetAddress.getByName(mDeviceSet.ip), mDeviceSet.port);
            mPackType = PACKET_TYPE_SEND_LRC;
            sendPack.setData(packData());
            hostSocket.send(sendPack);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (hostSocket != null) {
                hostSocket.close();
            }
        }
    }


    private byte[] packData() {
        byte[] data = new byte[1024];
        int offset = 0;
        data[offset++] = '$';
        data[offset++] = mPackType;

        byte[] ips = mContent.getBytes(Charset.forName("UTF-8"));

        data[offset++] = (byte) ips.length;
        data[offset++] = (byte) (ips.length >> 8);
        data[offset++] = (byte) (ips.length >> 16);
        data[offset++] = (byte) (ips.length >> 24);

        System.arraycopy(ips, 0, data, offset, ips.length);
        offset += ips.length;

        byte[] result = new byte[offset];
        System.arraycopy(data, 0, result, 0, offset);
        return result;
    }

}
