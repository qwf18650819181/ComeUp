package com.comeup.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月18日 0018
 * @version: 1.0
 */
public class NIOWithJvmHeap {
    public static void copy(Path sourceResource, Path nioJvmHeapResource) throws Exception {
        RandomAccessFile sourceFile = new RandomAccessFile(sourceResource.toFile(), "r");
        FileChannel sourceChannel = sourceFile.getChannel();

        RandomAccessFile destinationFile = new RandomAccessFile(nioJvmHeapResource.toFile(), "rw");
        FileChannel destinationChannel = destinationFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
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

    public static void copyShort(Path sourcePath, Path destinationPath) throws IOException {
        try (FileChannel sourceChannel = FileChannel.open(sourcePath, StandardOpenOption.READ);
             FileChannel destinationChannel = FileChannel.open(destinationPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            long position = 0;
            long count = sourceChannel.size();
            while (position < count) {
                position += sourceChannel.transferTo(position, count - position, destinationChannel);
            }
        }
    }
}
