package com.comeup.io.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月29日 0029
 * @version: 1.0
 */
public class BIO {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8090));
        while (true) {
            System.out.println("accept");
            Socket client = serverSocket.accept();
            new Thread(() -> {
                try {
                    String read = new ObjectInputStream(client.getInputStream()).readUTF();
                    System.out.println(read);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }




}
