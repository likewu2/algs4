package edu.princeton.cs.algs4.my;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CountDownLatchTest {
    private static final int threadcount = 6;

    public static void main(String[] args) throws InterruptedException {
        String[] a= {"", ""};
        System.out.println("a: "+ a);
        long ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static long parse(String[] origin) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(threadcount);

        ExecutorService threadpool = Executors.newFixedThreadPool(10);

        for (int i=0;i<threadcount;i++) {
            threadpool.execute(()->{
                try {

                //} catch (InterruptedException ex) {
                //    ex.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        threadpool.shutdown();

        return -1;
    }
}
