package edu.princeton.cs.algs4.my;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestPlatformThread {
    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(0);
        // 创建一个固定200个线程的线程池
        try {
            //ExecutorService executor =  Executors.newFixedThreadPool(200);
            //通过虚拟线程实现
            ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
            List<Future<Integer>> futures = new ArrayList<>();
            long begin = System.currentTimeMillis();
            // 向线程池提交1000个sleep 1s的任务
            for (int i=0; i<1_000; i++) {
                    Future future = executor.submit(() -> {
                    Thread.sleep(1000);
                    return a.addAndGet(1);
                });
 
                futures.add(future);
            }
            // 获取这1000个任务的结果
            for (Future<Integer> future : futures) {
                Integer integer = future.get();
                if(integer % 100 ==0){
                    System.out.println(integer + " ");
                }
            }
            // 打印总耗时
            System.out.println("Exec finish!!!");
            System.out.printf("Exec time: %dms.%n", System.currentTimeMillis() - begin);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void virtualThreadTest1() {
        Thread.startVirtualThread(() -> {
            // 这里放置你的任务代码
            System.out.println("Method ONE");
        });
    }

    public void virtualThreadTest2() {
        Thread.ofVirtual()
                .name("virtualThreadTest")//为虚拟线程设置名称
                .uncaughtExceptionHandler((t,e)-> System.out.println("线程[" + t.getName() + "发生了异常。message:" + e.getMessage()))//处理线程异常
                .start(()->{
                    System.out.println("Method TWO");
                });//创建时直接启动
    }

    public void virtualThreadTest3() {
        ThreadFactory factory = Thread.ofVirtual().factory();
        factory.newThread(() -> {
            // 这里放置你的任务代码
            System.out.println("Method THREE");
        }).start();
    }
}
