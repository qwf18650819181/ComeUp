package com.comeup.io.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月29日 0029
 * @version: 1.0
 */
public class NIO {

    public static void main(String[] args) throws IOException {
        List<SocketChannel> socketChannels = new ArrayList<>();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8090));
        serverChannel.configureBlocking(false);
        while (true) {
            SocketChannel clientChannel = serverChannel.accept();
            if (clientChannel == null) {
                System.out.println("null");
                continue;
            }
            clientChannel.configureBlocking(false);
            socketChannels.add(clientChannel);
            socketChannels.forEach(socketChannel -> {
                try (ObjectInputStream inputStream = new ObjectInputStream(socketChannel.socket().getInputStream())) {
                    System.out.println(inputStream.readUTF());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }




}
