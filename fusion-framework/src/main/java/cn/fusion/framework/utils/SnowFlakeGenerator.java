package cn.fusion.framework.utils;

/**
 * @ClassName SnowFlakeGenerator
 * @Description 雪花算法，生成id
 * @Author ycl
 * @Date 2024/11/4 10:13
 * @Version 1.0
 */
public class SnowFlakeGenerator {
    private static final long EPOCH = 1288834974657L;
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATACENTER_ID_BITS = 5L;
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    private final long workerId;
    private final long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowFlakeGenerator(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("worker Id can't be greater than " + MAX_WORKER_ID + " or less than 0");
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException("datacenter Id can't be greater than " + MAX_DATACENTER_ID + " or less than 0");
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 默认生成16为长度的id
     *
     * @return 雪花id
     */
    public synchronized String generateUniqueId() {
        return generateUniqueId(16);
    }

    /**
     * 生成指定长度的雪花id
     *
     * @param length 指定长度，
     * @return id
     */
    public synchronized String generateUniqueId(int length) {
        if (length < 16) {
            length = 16;
        }
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        long id = ((timestamp - EPOCH) << TIMESTAMP_LEFT_SHIFT) |
                (datacenterId << DATACENTER_ID_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                sequence;

        return padLeft(Long.toHexString(id), length);
    }

    /**
     * 生成雪花id 带固定前缀（长度就变成了前缀长度+16）
     *
     * @param prefix 固定前缀
     * @return id
     */
    public synchronized String generateUniqueId(String prefix) {
        return prefix + generateUniqueId();
    }

    /**
     * 填充
     *
     * @param s      待填充的字符串
     * @param length 填充长度
     * @return 填充后的数据
     */
    private String padLeft(String s, int length) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() + s.length() < length) {
            sb.append('0');
        }
        sb.append(s);
        return sb.toString();
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
}
