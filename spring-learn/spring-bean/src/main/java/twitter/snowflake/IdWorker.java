package twitter.snowflake;

import java.util.concurrent.TimeUnit;

/**
 * twitter snow flake
 * https://github.com/twitter/snowflake
 * https://github.com/erans/pysnowflake
 * <p>
 * Twitter给出了 64-bit 长的 Snowflake ，它的结构是：
 * <p>
 * 1-bit reserved
 * <p>
 * 41-bit timestamp
 * <p>
 * 10-bit machine id
 * <p>
 * 12-bit sequence
 * <p>
 * 在过了不到4年，2014年的5月31日，Twitter 更新了 Snowflake 的 README，其中陈述了两个容易被忽视的事实:
 * <p>
 * "We have retired the initial release of Snowflake ..."
 * <p>
 * "... heavily relies on existing infrastructure at Twitter to run. "
 * <p>
 * 可以看出，这个方案所支持的最小划分粒度是「毫秒 * 线程」，单线程（Snowflake 里对应的概念是 Worker）的每秒容量是12-bit，也就是接近4096。
 * <p>
 * 翻一下Snowflake的 归档代码 (Scala)，可以看到：
 * <p>
 * new address:https://twitter.github.io/twitter-server/
 */
public class IdWorker {
    private final long workerId;
    private final static long twepoch = 1288834974657L;
    private long sequence = 0L;
    private final static long workerIdBits = 4L;
    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    private final static long sequenceBits = 10L;

    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    public final static long sequenceMask = -1L ^ -1L << sequenceBits;

    private long lastTimestamp = -1L;

    public IdWorker(final long workerId) {
        super();
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0",
                    this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized Long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            this.sequence = (this.sequence + 1) & this.sequenceMask;
            if (this.sequence == 0) {
                // System.out.println("###########" + sequenceMask);
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(
                        String.format(
                                "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                                this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        Long nextId = ((timestamp - twepoch << timestampLeftShift)) | (this.workerId << this.workerIdShift) | (this.sequence);
        // System.out.println("timestamp:" + timestamp + ",timestampLeftShift:"
        // + timestampLeftShift + ",nextId:" + nextId + ",workerId:"
        // + workerId + ",sequence:" + sequence);
        return nextId;
    }

//    public synchronized Long nextTimeID() {
//        this.lastTimestamp = this.timeGen();
//        // ID偏移组合生成最终的ID，并返回ID
//        return Long.parseLong(DateUtils.getFormatStr(new Date(
//                this.lastTimestamp), "yyyyMMddHHmmssSSS"));
//    }

    // 等待下一个毫秒的到来
    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        final IdWorker worker2 = new IdWorker(2);
        long pre = worker2.nextId();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long current = worker2.nextId();
                    count++;
//            System.out.println(pre + " - " + current + " " + (current > pre));
//            pre = current;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("generate count : " + count);
//        Thread.currentThread().sleep();
    }
}
