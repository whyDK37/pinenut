//package lmax;
//
//import com.lmax.disruptor.RingBuffer;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
///**
// * Created by why on 11/20/2016.
// */
//public class Demo {
//
//    @Test
//    public void test(){
//        Executor executor = Executors.newCachedThreadPool();
//        BatchHandler handler1 = new MyBatchHandler1();
//        BatchHandler handler2 = new MyBatchHandler2();
//        BatchHandler handler3 = new MyBatchHandler3()
//        RingBuffer ringBuffer = new RingBuffer(ENTRY_FACTORY, RING_BUFFER_SIZE);
//        ConsumerBarrier consumerBarrier1 = ringBuffer.createConsumerBarrier();
//        BatchConsumer consumer1 = new BatchConsumer(consumerBarrier1, handler1);
//        BatchConsumer consumer2 = new BatchConsumer(consumerBarrier1, handler2);
//        ConsumerBarrier consumerBarrier2 =
//                ringBuffer.createConsumerBarrier(consumer1, consumer2);
//        BatchConsumer consumer3 = new BatchConsumer(consumerBarrier2, handler3);
//        executor.execute(consumer1);
//        executor.execute(consumer2);
//        executor.execute(consumer3);
//        ProducerBarrier producerBarrier =
//                ringBuffer.createProducerBarrier(consumer3);
//
//    }
//}
