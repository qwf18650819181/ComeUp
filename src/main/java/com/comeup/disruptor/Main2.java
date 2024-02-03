package com.comeup.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @auth: qwf
 * @date: 2023年12月27日 0027
 * @description:
 */
public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        // 创建缓冲区大小 2 的 n 次幂
        int bufferSize = 1024;
        // 创建Disruptor实例
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEventFactory.INSTANCE, bufferSize, executor);
        // 设置事件处理器
        disruptor.handleEventsWith(new LongEventHandler());
        // 启动Disruptor
        disruptor.start();
        // 获取RingBuffer并创建事件生产者
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        // 模拟产生事件
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 10; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(1000);
        }
        // 关闭Disruptor
        disruptor.shutdown();
    }


    // 事件类
    @Data
    @NoArgsConstructor
    public static class LongEvent {
        private long value;
        public long getValue() {
            return value;
        }
    }
    // 事件工厂
    public enum LongEventFactory implements EventFactory<LongEvent> {
        INSTANCE;
        LongEventFactory() {
        }
        @Override
        public Main2.LongEvent newInstance() {
            return new LongEvent();
        }
    }
    // 事件处理器类
    public static class LongEventHandler implements EventHandler<LongEvent> {
        public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
            System.out.println("Event: " + event.getValue());
        }
    }
    // 事件生产者类
    public static class LongEventProducer {
        private final RingBuffer<LongEvent> ringBuffer;

        public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
            @Override
            public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
                event.setValue(bb.getLong(0));
            }
        };
        public void onData(ByteBuffer bb) {
            ringBuffer.publishEvent(TRANSLATOR, bb);
        }
    }
}
