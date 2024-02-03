package com.comeup.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.ByteBuffer;

/**
 * @auth: qwf
 * @date: 2023年12月27日 0027
 * @description:
 */
public class Main3 {
    public static void main(String[] args) {
        int bufferSize = 1024;
        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
        // Connect the handler
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println(event.getValue()));
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++)
        {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
            // 不推荐 原因是这是一个capturing lambda, 每一个lamda会产生一个对象来承接bb，这样会产生大量的小对象
            // ringBuffer.publishEvent((event, sequence) -> event.setValue(bb.getLong(0)));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Data
    @NoArgsConstructor
    public static class LongEvent {
        private long value;
        public long getValue() {
            return value;
        }
    }
}
