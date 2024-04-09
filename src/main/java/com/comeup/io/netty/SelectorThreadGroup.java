package com.comeup.io.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年4月9日 0009
 * @version: 1.0
 */
public class SelectorThreadGroup {

    SelectorThread[] bossThreads;
    ServerSocketChannel server = null;
    AtomicInteger increaseId = new AtomicInteger(0);

    SelectorThreadGroup workerThreads = this;

    public void setWorker(SelectorThreadGroup workerThreads) {
        this.workerThreads = workerThreads;

    }

    SelectorThreadGroup(int num) {
        bossThreads = new SelectorThread[num];
        for (int i = 0; i < num; i++) {
            bossThreads[i] = new SelectorThread(this);
            new Thread(bossThreads[i]).start();
        }
    }


    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            chooseBossOrWorker(server);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void chooseBossOrWorker(Channel channel) {
        try {
            if (channel instanceof ServerSocketChannel) {
                SelectorThread nextWorkerTread = nextBossThread();
                nextWorkerTread.queue.put(channel);
                nextWorkerTread.setWorker(workerThreads);
                nextWorkerTread.selector.wakeup();
            } else {
                SelectorThread nextWorkerTread = nextWorkerTread();
                nextWorkerTread.queue.add(channel);
                nextWorkerTread.selector.wakeup();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private SelectorThread nextBossThread() {
        int index = increaseId.incrementAndGet() % bossThreads.length;
        return bossThreads[index];
    }

    private SelectorThread nextWorkerTread() {
        int index = increaseId.incrementAndGet() % workerThreads.bossThreads.length;
        return workerThreads.bossThreads[index];
    }

}
