package com.comeup.io.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 功能描述:
 * +jvm参数:
 * -Djava.nio.channels.spi.SelectorProvider=sun.nio.ch.PollSelectorProvider
 *
 * @author: qiu wanzi
 * @date: 2024年3月29日 0029
 * @version: 1.0
 */
public class NIO_SelectPoll {

    private ServerSocketChannel serverChannel;
    private Selector selector;

    private void init() throws IOException {
        serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8090));
        serverChannel.configureBlocking(false);
        // select  poll  *epoll  优先选择：epoll  但是可以 -Djava.nio.channels.spi.SelectorProvider=sun.nio.ch.PollSelectorProvider
        selector = Selector.open();
        // server 约等于 listen状态的 fd4
        // select | poll jvm里开辟一个数组 fd4 放进去
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void start() throws IOException {
        init();
        while (true) {
            Set<SelectionKey> keys = selector.keys();
            System.out.println(keys.size() + "   size");
            //1,调用多路复用器(select,poll  or  epoll  (epoll_wait))
                /*
                select()是啥意思：
                1，select，poll  其实  内核的select（fd4）  poll(fd4)
                2，epoll：  其实 内核的 epoll_wait()
                *, 参数可以带时间：没有时间，0  ：  阻塞，有时间设置一个超时
                selector.wakeup()  结果返回0

                懒加载：
                其实再触碰到selector.select()调用的时候触发了epoll_ctl的调用

                 */
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();//返回的有状态的fd集合
                //so，管你啥多路复用器，你呀只能给我状态，我还得一个一个的去处理他们的R/W。同步好辛苦！！！！！！！！
                //  NIO  自己对着每一个fd调用系统调用，浪费资源，那么你看，这里是不是调用了一次select方法，知道具体的那些可以R/W了？
                //幕兰，是不是很省力？
                //我前边可以强调过，socket：  listen   通信 R/W
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();//set  不移除会重复循环处理
                    if (next.isAcceptable()) {
                        acceptHandler(next);
                        //看代码的时候，这里是重点，如果要去接受一个新的连接
                        //语义上，accept接受连接且返回新连接的FD对吧？
                        //那新的FD怎么办？
                        //select，poll，因为他们内核没有空间，那么在jvm中保存和前边的fd4那个listen的一起
                        //epoll： 我们希望通过epoll_ctl把新的客户端fd注册到内核空间
                    } else if (next.isReadable()) {
                        readHandler(next);//连read 还有 write都处理了
                        //在当前线程，这个方法可能会阻塞  ，如果阻塞了十年，其他的IO早就没电了。。。
                        //所以，为什么提出了 IO THREADS
                        //redis  是不是用了epoll，redis是不是有个io threads的概念 ，redis是不是单线程的
                        //tomcat 8,9  异步的处理方式  IO  和   处理上  解耦
                    } else {
                        next.channel().close();
                    }
                }
            }
        }
    }

    public void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel client = ssc.accept(); //来啦，目的是调用accept接受客户端  fd7
            client.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(8192);  //前边讲过了
            // 0.0  我类个去
            //你看，调用了register
            /*
            select，poll：jvm里开辟一个数组 fd7 放进去
            epoll：  epoll_ctl(fd3,ADD,fd7,EPOLLIN
             */
            client.register(selector, SelectionKey.OP_READ, buffer);
            System.out.println("-------------------------------------------");
            System.out.println("新客户端：" + client.getRemoteAddress());
            System.out.println("-------------------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        int read = 0;
        try {
            while (true) {
                read = client.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (read == 0) {
                    break;
                } else {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        NIO_SelectPoll nioSelectPoll = new NIO_SelectPoll();
        nioSelectPoll.start();
    }


}
