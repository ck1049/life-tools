package com.loafer.common.utils;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * @author loafer
 * @since 2023-11-04 22:14:47
 **/
public class ThreadPoolUtil {

    public static ExecutorService getExecutorService(String name) {
        return new ThreadPoolExecutor(10, 20, 60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2000),
                new DefaultThreadFactory(name),
        new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
