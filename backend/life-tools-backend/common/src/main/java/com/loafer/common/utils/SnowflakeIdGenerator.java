package com.loafer.common.utils;

/**
 * @author loafer
 * @since 2023-11-04 19:54:50
 **/

public class SnowflakeIdGenerator {
    private static final int WORKER_ID_SHIFT = 41;
    private static final int WORKER_ID_MASK = ~(-1 << WORKER_ID_SHIFT);
    private static final int EPOCH_BASED_ID_SHIFT = 10;
    private static final int EPOCH_BASED_ID_MASK = ~(-1 << EPOCH_BASED_ID_SHIFT);
    private static final int UNIQUE_ID_SHIFT = 31;
    private static final int UNIQUE_ID_MASK = ~(-1 << UNIQUE_ID_SHIFT);
    private static final long EPOCH_BASED_ID = 0;
    private static final long WORKER_ID_OFFSET = 0L;
    private static final long UNIQUE_ID_OFFSET = 1L;

    private long lastTimestamp;
    private long workerId;
    private long epochBasedId;
    private long uniqueId;

    public SnowflakeIdGenerator(long workerId) {
        this.workerId = workerId & WORKER_ID_MASK;
        this.epochBasedId = EPOCH_BASED_ID;
        this.uniqueId = UNIQUE_ID_OFFSET;
        this.lastTimestamp = System.currentTimeMillis();
    }

    public long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards");
        }
        if (timestamp == lastTimestamp) {
            // 情况1：相同时间戳
            // 使用数据中心标识和唯一标识来生成ID
            long id = (workerId << EPOCH_BASED_ID_SHIFT) | (epochBasedId & EPOCH_BASED_ID_MASK) | (uniqueId & UNIQUE_ID_MASK);
            uniqueId++;
            if (uniqueId == 0) {
                epochBasedId++;
            }
            return id;
        } else {
            // 情况2：不同时间戳
            // 使用时间戳、数据中心标识和唯一标识来生成ID
            lastTimestamp = timestamp;
            long id = (timestamp - lastTimestamp) << WORKER_ID_SHIFT;
            id |= workerId << EPOCH_BASED_ID_SHIFT;
            id |= epochBasedId & EPOCH_BASED_ID_MASK;
            uniqueId++;
            if (uniqueId == 0) {
                epochBasedId++;
            }
            return id;
        }
    }
}