package util;

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
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
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
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                        this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        // ID偏移组合生成最终的ID，并返回ID
        Long nextId = ((timestamp - twepoch << timestampLeftShift))
                | (this.workerId << this.workerIdShift) | (this.sequence);
        return nextId;
    }

//    public synchronized Long nextTimeID() {
//        this.lastTimestamp = this.timeGen();
//        // ID偏移组合生成最终的ID，并返回ID
//        return Long.parseLong(DateUtils.getFormatStr(new Date(this.lastTimestamp), "yyyyMMddHHmmssSSS"));
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

}
