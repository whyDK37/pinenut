package lmax;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonHelper {
  /**
   * ringBuffer的容量，必须是2的N次方
   */
  private static final int BUFFER_SIZE = 256;
  private static PersonHelper instance;
  private static boolean inited = false;
  private RingBuffer<PersonEvent> ringBuffer;
  private SequenceBarrier sequenceBarrier;
  private PersonEventHandler handler;
  private BatchEventProcessor<PersonEvent> batchEventProcessor;

  public PersonHelper() {
    //参数1 事件
    //参数2 单线程使用
    //参数3 等待策略
    EventFactory<PersonEvent> eventFactory = PersonEvent.EVENT_FACTORY;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    int ringBufferSize = 4; // RingBuffer 大小，必须是 2 的 N 次方；

    Disruptor<PersonEvent> disruptor = new Disruptor<>(eventFactory,
            ringBufferSize, executor, ProducerType.SINGLE,
            new YieldingWaitStrategy());

    ringBuffer = disruptor.getRingBuffer();
    //获取生产者的位置信息
    sequenceBarrier = ringBuffer.newBarrier();
    //消费者
    handler = new PersonEventHandler();
    //事件处理器，监控指定ringBuffer,有数据时通知指定handler进行处理
    batchEventProcessor = new BatchEventProcessor<>(ringBuffer, sequenceBarrier, handler);
    //传入所有消费者线程的序号
//        ringBuffer.setGatingSequences(batchEventProcessor.getSequence());

  }

  /**
   * 启动消费者线程，实际上调用了AudioDataEventHandler中的onEvent方法进行处理
   */
  public static void start() {
    instance = new PersonHelper();
    final Thread thread = new Thread(instance.batchEventProcessor);
    thread.start();
    inited = true;
  }

  /**
   * 停止
   */
  public static void shutdown() {
    if (!inited) {
      throw new RuntimeException("EncodeHelper还没有初始化！");
    } else {
      instance.doHalt();
    }
  }

  /**
   * 生产者压入生产数据
   *
   * @param person
   */
  public static void produce(Person person) {
    if (!inited) {
      throw new RuntimeException("EncodeHelper还没有初始化！");
    } else {
      instance.doProduce(person);
    }
  }

  public static void stop() {

  }

  private void doHalt() {
    batchEventProcessor.halt();
  }

  private void doProduce(Person person) {
    //获取下一个序号
    long sequence = ringBuffer.next();
    System.out.println("sequence:" + sequence);
    //写入数据
    ringBuffer.get(sequence).setPerson(person);
    //通知消费者该资源可以消费了
    ringBuffer.publish(sequence);
  }
}

