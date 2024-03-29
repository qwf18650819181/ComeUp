package com.comeup.io.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月29日 0029
 * @version: 1.0
 */
public class Client {

    public static void main(String[] args) throws IOException {
        for (int i = 10000; i < 50000; i++) {
            SocketChannel channel = SocketChannel.open();
            channel.bind(new InetSocketAddress("172.16.12.203", i));
            channel.connect(new InetSocketAddress("127.0.0.1", 8090));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(channel.socket().getOutputStream());
            objectOutputStream.writeUTF("hello");
            channel.close();
            objectOutputStream.close();
        }
    }




}
