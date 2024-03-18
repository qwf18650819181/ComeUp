package com.comeup.io;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月18日 0018
 * @version: 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // note : 非阻塞只针对网络编程 文件io还是阻塞的
        Path sourceResource = Paths.get("D:\\source.text");
        Path destinationResource = Paths.get("D:\\BIO_destination.text");

        BIO.copy(sourceResource, destinationResource);

        Path nio_jvm_heap_Resource = Paths.get("D:\\NIO_jvm_heap_destination.text");
        NIOWithJvmHeap.copy(sourceResource, nio_jvm_heap_Resource);

        Path nio_direct_heap_Resource = Paths.get("D:\\NIO_direct_heap_destination.text");
        NIOWithDirectHeap.copy(sourceResource, nio_direct_heap_Resource);

        Path nio_mmap_Resource = Paths.get("D:\\NIO_mmap_destination.text");
        NIOWithMMap.copy(sourceResource, nio_mmap_Resource);
    }
}
