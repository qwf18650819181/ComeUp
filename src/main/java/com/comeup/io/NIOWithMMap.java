package com.comeup.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * 功能描述: note: mmap只适用于 系统级别的文件 远程文件不适用
 *
 * @author: qiu wanzi
 * @date: 2024年3月18日 0018
 * @version: 1.0
 */
public class NIOWithMMap {
    public static void copy(Path sourceResource, Path nioMmapResource) throws Exception {
        RandomAccessFile sourceFile = new RandomAccessFile(sourceResource.toFile(), "r");
        FileChannel sourceChannel = sourceFile.getChannel();

        RandomAccessFile destinationFile = new RandomAccessFile(nioMmapResource.toFile(), "rw");
        FileChannel destinationChannel = destinationFile.getChannel();

        long size = sourceChannel.size();
        MappedByteBuffer sourceFileMap = sourceChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);

        // note: 这个不需要mmap 看下面的demo
        MappedByteBuffer destinationFileMap = destinationChannel.map(FileChannel.MapMode.READ_WRITE, 0, size);
        for (int i = 0; i < size; i++) {
            destinationFileMap.put(sourceFileMap.get());
        }
        sourceFile.close();
        destinationFile.close();
    }

    public static void copyShort(Path sourcePath, Path destinationPath) throws IOException {
        try (FileChannel sourceChannel = FileChannel.open(sourcePath, StandardOpenOption.READ);
             FileChannel destinationChannel = FileChannel.open(destinationPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            long fileSize = sourceChannel.size();
            MappedByteBuffer sourceBuffer = sourceChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);
            destinationChannel.write(sourceBuffer);
        }
    }
}
