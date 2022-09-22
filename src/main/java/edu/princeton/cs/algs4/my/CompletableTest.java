package edu.princeton.cs.algs4.my;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableTest {
    public static void main(String[] args) {
        String[] a= {"", ""};
        System.out.println("a: "+ a);
        long ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static long parse(String[] origin) {
        List<String> filePaths = Arrays.asList(origin);
        List<CompletableFuture<String>> fileFutures = filePaths.stream()
                .map(filePath->{
                    return CompletableFuture.supplyAsync(()->{
                        return filePath;
                    });
                })
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(fileFutures.toArray(new CompletableFuture[fileFutures.size()]));

        try {
            allFutures.join();
        } catch(Exception ex) {

        }

        return -1;
    }
}
