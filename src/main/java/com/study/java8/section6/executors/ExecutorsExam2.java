package com.study.java8.section6.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsExam2 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(getRunnable("Hi"), 1, 2, TimeUnit.SECONDS); // 1초 뒤, 2초마다 실행
    }

    private static Runnable getRunnable(String message) {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": " + message);
            }
        };
    }
}
