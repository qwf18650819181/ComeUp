package com.comeup.io;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月18日 0018
 * @version: 1.0
 */
public class NIOWithDirectHeap {
    public static void copy(Path sourceResource, Path nioDirectHeapResource) throws Exception {
        RandomAccessFile sourceFile = new RandomAccessFile(sourceResource.toFile(), "r");
        FileChannel sourceChannel = sourceFile.getChannel();

        RandomAccessFile destinationFile = new RandomAccessFile(nioDirectHeapResource.toFile(), "rw");
        FileChannel destinationChannel = destinationFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        while (sourceChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                destinationChannel.write(byteBuffer);
            }
            byteBuffer.clear();
        }
        sourceFile.close();
        destinationFile.close();
    }
}
