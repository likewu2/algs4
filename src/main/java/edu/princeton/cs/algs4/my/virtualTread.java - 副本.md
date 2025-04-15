https://zhuanlan.zhihu.com/p/26068107525

package com.example.demo.example.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.EventLoopGroup;
import io.netty.handler.timeout.TimeoutException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.netty.resources.ConnectionProvider;

public class VirtualThreadHttpClientDemo {

  public static void main(String[] args) throws InterruptedException {

    int[] concurrencyLevels = { 100, 500, 1000 };

    // 测试不同实现方式
    for (int n : concurrencyLevels) {
      System.out.println("\n========= Testing concurrency level: " + n + " =========");

//      System.out.println("使用httpclient测试：");
//      System.out.println(" ");

      mainsync(n, false);
      mainsync(n, true);

      
      mainurlsync(n, false);
      mainurlsync(n, true);
      
      
      main1(n, false);
      main1(n, true);

//      System.out.println("使用OkHttpClient测试：");
//      System.out.println(" ");
      main2(n, false);
      main2(n, true);

//      System.out.println("使用WebClient测试：");   
//      main3(n, false);
//      main3(n, true);

    }
    System.exit(0);
  }

  static int c = 0;

  public static void mainsync(int size, boolean Virtual) throws InterruptedException {

    long st = System.currentTimeMillis();
    c = 0;
    // 创建HttpClient（默认使用HTTP/2）
    HttpClient client = HttpClient.newBuilder().executor(virtualExecutor).build();
    
    CountDownLatch counter = new CountDownLatch(size);

//    System.out.println("count start:" + counter.getCount());
    // 创建虚拟线程池
    var executor = Virtual ? virtualExecutor : FixedThreadPool;
    // 提交10个HTTP请求任务（虚拟线程自动管理）
    for (int i = 0; i < size; i++) {
      int taskId = i;
      executor.submit(() -> {

        try {

          c++;
          
          // 构建请求
          HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://www.baidu.com")).GET()
              .build();
          // 发送请求并获取响应（同步阻塞操作由虚拟线程自动挂起）
          HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

          c += response.statusCode();
          counter.countDown();
          // 输出结果
//          System.out.printf("Task %d - Status: %d, Body length: %d%n", taskId, response.statusCode(),
//              response.body().length());

        } catch (Exception e) {
//          System.err.printf("Task %d failed: %s%n", taskId, e.getMessage());
          counter.countDown();
        }finally {
      
        }
      

      });
    }

//      executor.shutdown();
    // 等待所有任务完成（实际项目中可移除此处阻塞）
//      executor.awaitTermination(10, TimeUnit.SECONDS);

    counter.await();
//    System.out.println(c);
//    System.out.println("count:" + counter.getCount());

    System.out.println(
        "HttpClient阻塞调用 +" + (Virtual ? "虚拟线程" : "多线程") + " 测试结束总耗时：" + (System.currentTimeMillis() - st));

  }

  
  

  public static void mainurlsync(int size, boolean Virtual) throws InterruptedException {

    long st = System.currentTimeMillis();
    c = 0;
    // 创建HttpClient（默认使用HTTP/2）
    HttpClient client = HttpClient.newBuilder().executor(virtualExecutor).build();
    
    CountDownLatch counter = new CountDownLatch(size);

//    System.out.println("count start:" + counter.getCount());
    // 创建虚拟线程池
    var executor = Virtual ? virtualExecutor : FixedThreadPool;
    // 提交10个HTTP请求任务（虚拟线程自动管理）
    for (int i = 0; i < size; i++) {
      int taskId = i;
      executor.submit(() -> {

        try {

          c++;
          
           URL url = new URL("http://www.baidu.com");
                      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                      String line;
                      while ((line = reader.readLine()) != null) {
                          // 模拟I/O操作
                          // System.out.println(line);
                      }
                      reader.close();
                      
                      
                      
          counter.countDown();
          // 输出结果
//          System.out.printf("Task %d - Status: %d, Body length: %d%n", taskId, response.statusCode(),
//              response.body().length());

        } catch (Exception e) {
//          System.err.printf("Task %d failed: %s%n", taskId, e.getMessage());
          counter.countDown();
        }finally {
      
        }
      

      });
    }

//      executor.shutdown();
    // 等待所有任务完成（实际项目中可移除此处阻塞）
//      executor.awaitTermination(10, TimeUnit.SECONDS);

    counter.await();
//    System.out.println(c);
//    System.out.println("count:" + counter.getCount());

    System.out.println(
        "URL方式阻塞调用 +" + (Virtual ? "虚拟线程" : "多线程") + " 测试结束总耗时：" + (System.currentTimeMillis() - st));

  }

  
  
  public static void main1(int size, boolean Virtual) throws InterruptedException {

    long st = System.currentTimeMillis();

    // 创建HttpClient（默认使用HTTP/2）
//    HttpClient client = HttpClient.newHttpClient();

    HttpClient client = HttpClient.newBuilder().executor(virtualExecutor).build();
    CountDownLatch counter = new CountDownLatch(size);
//    System.out.println("count start:" + counter.getCount());
    // 创建虚拟线程池
    var executor = Virtual ? virtualExecutor : FixedThreadPool;
    // 提交10个HTTP请求任务（虚拟线程自动管理）
    for (int i = 0; i < size; i++) {
      int taskId = i;
      executor.submit(() -> {

        try {
          // 构建请求
          HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://www.baidu.com")).GET()
              .build();
          // 发送请求并获取响应（同步阻塞操作由虚拟线程自动挂起）
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

          // 3. 发送异步请求
          CompletableFuture<HttpResponse<String>> future = client.sendAsync(request,
              HttpResponse.BodyHandlers.ofString());

          // 4. 处理响应
          future.thenApply(response -> {
//                        System.out.println("状态码: " + response.statusCode());
//                        System.out.println("响应体: " + response.body());
            counter.countDown();
            return response;
          }).exceptionally(e -> {
                        System.err.println("请求失败: " + e.getMessage());

            counter.countDown();
            return null;
          });

//            CompletableFuture f=    client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//            
//            f.runAsync(null, virtualExecutor);

          // 输出结果
//          System.out.printf("Task %d - Status: %d, Body length: %d%n", taskId, response.statusCode(),
//              response.body().length());

        } catch (Exception e) {
//            System.err.printf("Task %d failed: %s%n", taskId, e.getMessage());
        }finally {
        
        }

      });
    }
    
//    executor.awaitTermination(st, null)
//      executor.shutdown();
    // 等待所有任务完成（实际项目中可移除此处阻塞）
//      executor.awaitTermination(10, TimeUnit.SECONDS);

    counter.await();
//    System.out.println("count:" + counter.getCount());
    System.out.println(
        "HttpClient异步+" + (Virtual ? "虚拟线程" : "多线程") + " 测试结束总耗时：" + (System.currentTimeMillis() - st));

  }

  public static void main2(int size, boolean Virtual) throws InterruptedException {

    OkHttpClient client2 =  new OkHttpClient().newBuilder().dispatcher(new Dispatcher(virtualExecutor)).build();
    
//    client2.dispatcher=(new Dispatcher(virtualExecutor));
    CountDownLatch counter = new CountDownLatch(size);

    
    // OkHttpClient优化配置
//    var client = OkHttpClient.Builder()
//        .connectionPool(ConnectionPool(200, 5, TimeUnit.MINUTES)) // 扩大连接池
//        .dispatcher(Dispatcher(Executors.newVirtualThreadPerTaskExecutor())) // 虚拟线程调度
//        .build()
        
        
//    System.out.println("count start:" + counter.getCount());
    long st = System.currentTimeMillis();
    // 创建虚拟线程池
    var executor = Virtual ? virtualExecutor : FixedThreadPool;
    // 提交10个HTTP请求任务（虚拟线程自动管理）
    for (int i = 0; i < size; i++) {
      int taskId = i;
      executor.submit(() -> {

        try {
          Request request2 = new Request.Builder().url("http://www.baidu.com").build();

          Call call = client2.newCall(request2);
          call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
              counter.countDown();
              response.close();
            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
              counter.countDown();
            }
          });
        } catch (Exception e) {
//            System.err.printf("Task %d failed: %s%n", taskId, e.getMessage());
        }

      });
    }

//      executor.shutdown();
    // 等待所有任务完成（实际项目中可移除此处阻塞）
//      executor.awaitTermination(10, TimeUnit.SECONDS);

    counter.await();
//    System.out.println("count:" + counter.getCount());
    System.out.println(
        " OkHttpClient异步 +" + (Virtual ? "虚拟线程" : "多线程") + " 测试结束总耗时：" + (System.currentTimeMillis() - st));

  }

  static ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
  static ExecutorService FixedThreadPool = Executors.newFixedThreadPool(40);

  public static void main3(int size, boolean Virtual) throws InterruptedException {

//    WebClient webClient = WebClient.builder().build();

//    ExchangeFilterFunction globalErrorFilter = ExchangeFilterFunction.ofResponseProcessor(response -> {
//        if (response.statusCode().isError()) {
//            return response.bodyToMono(String.class)
//                .flatMap(errorBody -> Mono.empty());
//        }
//        return Mono.just(response);
//    });
//
//    WebClient webClient = WebClient.builder()
//        .filter(globalErrorFilter)
//        .build();

    ExchangeStrategies strategies = ExchangeStrategies.builder()
        .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
        .build();

//           ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();

    // 示例：调整连接池参数
    ConnectionProvider provider = ConnectionProvider.builder("custom-pool").maxConnections(10000) // 最大活跃连接数
        .pendingAcquireMaxCount(-1) // 无限制等待队列（或设为更高值，如 5000）
        .pendingAcquireTimeout(Duration.ofSeconds(30)) // 等待获取连接超时时间
        .maxIdleTime(Duration.ofSeconds(30)) // 空闲连接超时释放
        .build();
    reactor.netty.http.client.HttpClient httpClient = reactor.netty.http.client.HttpClient.create(provider)
        .responseTimeout(Duration.ofSeconds(10));
//           httpClient.runOn(new TaskExecutorAdapter(virtualExecutor));

//    ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
    Scheduler virtualThreadScheduler = Schedulers.fromExecutor(virtualExecutor);

//           httpClient .runOn(virtualThreadScheduler); // 关键：绑定虚拟线程调度器

    WebClient webClient = WebClient.builder().exchangeStrategies(strategies)
        .clientConnector(new ReactorClientHttpConnector(httpClient)).build();

    CountDownLatch counter = new CountDownLatch(size);

    Hooks.onErrorDropped(e -> {
//      e.printStackTrace();

      counter.countDown();
    });

//    System.out.println("count start:" + counter.getCount());
    long st = System.currentTimeMillis();
    // 创建虚拟线程池
    var executor = Virtual ? virtualExecutor : FixedThreadPool;
    // 提交10个HTTP请求任务（虚拟线程自动管理）
    for (int i = 0; i < size; i++) {
      int taskId = i;
      executor.submit(() -> {

        try {

          webClient.get().uri("http://www.baidu.com").retrieve().bodyToMono(String.class).onErrorResume(e -> {

            counter.countDown();
            return Mono.just("Fallback");
          }).subscribeOn(virtualThreadScheduler) // 使用弹性线程池（可替换为虚拟线程池）
              .subscribe(response -> {

                counter.countDown();

//                  System.out.println("异步响应: " + response);

              }, error -> {
                counter.countDown();

              });

//            Mono mono = webClient.get().uri("http://www.baidu.com").retrieve().bodyToMono(String.class);
//                mono.onErrorResume(Exception.class, e -> {
//                  // 处理超时异常（如降级或重试）
//                  return Mono.empty();
//                }).onErrorResume(InterruptedException.class, e -> {
//                  // 处理中断异常（如释放资源）
////                    releaseResources();
//                  return Mono.empty();
//                });

//            mono.onErrorResume(e -> Mono.just("Fallback"));
//            mono.onErrorResume(Exception.class, ex -> {
//              // 判断异常是否是PrematureCloseException
//              // 其他异常继续抛出
//              counter.countDown();
//              return null;
//            });
//            mono.doOnError(e -> {
//              counter.countDown();
////              counter.countDown();
//              
//            });

//            mono.onErrorReturn("error");
//            mono.onErrorResume(Exception.class, e -> {
//                    // 自定义处理逻辑（如降级、日志记录）
//                    return Mono.empty();
//                });
//            mono.subscribe(
////                  data -> System.out.println("成功: " + data),
//                  error -> {
////                    System.out.println("捕获错误: " + error);
//                  counter.countDown();}// 必须定义
//              );

//            mono.subscribe(error -> {
//              // 其他异常处理逻辑（此处不会处理PrematureCloseException）
////              System.err.println("其他错误: " + error);
//
//              counter.countDown();
//            });
//
//            mono.subscribe(response -> {
//              counter.countDown();
//            });
//            mono.subscribeOn(virtualThreadScheduler);
        } catch (Exception e) {
          System.err.printf("Task %d failed: %s%n", taskId, e.getMessage());
        }

      });
    }

//      executor.shutdown();
    // 等待所有任务完成（实际项目中可移除此处阻塞）
//      executor.awaitTermination(10, TimeUnit.SECONDS);

    counter.await();
//    executor.shutdown();
//    System.out.println("count:" + counter.getCount());
    System.out
        .println("WebClient异步+" + (Virtual ? "虚拟线程" : "多线程") + " 测试结束总耗时：" + (System.currentTimeMillis() - st));

  }
}

jdk24执行结果：

PS D:\git\eatStar\server\pure>  & 'C:\Program Files\Java\jdk-24\bin\java.exe' '@C:\Users\Admin\AppData\Local\Temp\cp_25q6o1ij1pv2jlenbsp07v7jx.argfile' 'com.example.demo.example.services.VirtualThreadHttpClientDemo'   

========= Testing concurrency level: 100 =========
HttpClient阻塞调用 +多线程 测试结束总耗时：404
HttpClient阻塞调用 +虚拟线程 测试结束总耗时：109
URL方式阻塞调用 +多线程 测试结束总耗时：86
URL方式阻塞调用 +虚拟线程 测试结束总耗时：1036
HttpClient异步+多线程 测试结束总耗时：97
HttpClient异步+虚拟线程 测试结束总耗时：109
 OkHttpClient异步 +多线程 测试结束总耗时：203
 OkHttpClient异步 +虚拟线程 测试结束总耗时：149

========= Testing concurrency level: 500 =========
HttpClient阻塞调用 +多线程 测试结束总耗时：128
HttpClient阻塞调用 +虚拟线程 测试结束总耗时：7051
URL方式阻塞调用 +多线程 测试结束总耗时：139
URL方式阻塞调用 +虚拟线程 测试结束总耗时：3131
HttpClient异步+多线程 测试结束总耗时：3512
HttpClient异步+虚拟线程 测试结束总耗时：3201
 OkHttpClient异步 +多线程 测试结束总耗时：2071
 OkHttpClient异步 +虚拟线程 测试结束总耗时：1570

========= Testing concurrency level: 1000 =========
HttpClient阻塞调用 +多线程 测试结束总耗时：205
HttpClient阻塞调用 +虚拟线程 测试结束总耗时：15145
URL方式阻塞调用 +多线程 测试结束总耗时：252
URL方式阻塞调用 +虚拟线程 测试结束总耗时：21055
HttpClient异步+多线程 测试结束总耗时：15209
HttpClient异步+虚拟线程 测试结束总耗时：21113
 OkHttpClient异步 +多线程 测试结束总耗时：1510
 OkHttpClient异步 +虚拟线程 测试结束总耗时：1463

附上ai分析
