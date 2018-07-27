package com.hy.demo.common.utils;

import org.apache.commons.lang3.ThreadUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ExecutorUtils
 *
 * @author silent
 * @date 2018/4/20
 */
public class ExecutorUtils {

    private static ExecutorService executorService;

    ThreadLocal<String> threadLocal = new ThreadLocal<>();


    static {
        executorService = new ThreadPoolExecutor(1, 2, 0,
                TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(), new ThreadFac("executorPool"));
    }

    public static void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    public static class ThreadFac implements ThreadFactory {
        private String threadName;

        private AtomicInteger id = new AtomicInteger(0);

        public ThreadFac(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.setDaemon(false);
            thread.setName(threadName + "/" + id.incrementAndGet());

            thread.setUncaughtExceptionHandler((thread1, throwable) -> {
                //日志记录
                System.err.println(String.format("thread(%s) err : %s", thread.getName(), throwable.getMessage()));
            });
            return thread;
        }
    }
}
