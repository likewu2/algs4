https://zhuanlan.zhihu.com/p/26656485343

package com.example;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class BenchController {

  private final TaskService taskService;

  public BenchController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/io")
  public CompletableFuture<String> ioTask() {
    return taskService.simulateIO();
  }

  @GetMapping("/json")
  public ResponseEntity<Map> dbQuery() {
    int c = taskService.userRepository.countUsers();
    
    
    Map map=new HashMap();
    map.put("c", c);
    map.put("time", System.currentTimeMillis());
    
    return ResponseEntity.ok(map);
  }

  // 数据库查询测试
  @GetMapping("/db")
  public CompletableFuture<Integer> json() {

    return taskService.executeDbTask();
  }

  @GetMapping("/cpu")
  public CompletableFuture<Long> cpuTask() {
    return taskService.calculate(35);
  }
}

@Service
class TaskService {

  public final UserRepository userRepository;

  public TaskService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @org.springframework.scheduling.annotation.Async
  public CompletableFuture<Integer> executeDbTask() {

    return CompletableFuture.completedFuture(userRepository.countUsers());
  }

  @Async
  public CompletableFuture<String> simulateIO() {

    try {
      Thread.sleep(50);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return CompletableFuture.completedFuture("IO Complete");
  }

  @Async
  public CompletableFuture<Long> calculate(int n) {
    return CompletableFuture.completedFuture(fib(n));
  }

  private long fib(int n) {
    if (n <= 1)
      return n;
    return fib(n - 1) + fib(n - 2);
  }
}


package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBoot

Application;

@SpringBootApplication
public class Test2Application {
    public static void main(String[] args) {
        SpringApplication.run(Test2Application.class, args);
    }
    
}

//# IO密集型测试（使用ab工具）
//ab -n 10000 -c 200 http://localhost:8080/io
//
//# CPU密集型测试
//ab -n 100 -c 10 http://localhost:8080/cpu




//运行测试说明
//启动虚拟线程

模式：
//
//在IDE中配置运行参数：-Dspring.profiles.active=virtual
//直接运行 Test2Application 类
//启动线程池模式：
//
//不需要特殊参数，直接运行 Test2Application 类


package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import java.util.concurrent.Executors;

@Configuration
public class ThreadConfig {

    // 虚拟线程模式 (使用 -Dspring.profiles.active=virtual 激活)
    @Bean
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "virtual")
    public AsyncTaskExecutor virtualThreadExecutor() {
      System.out.println("虚拟线程启动");
        return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
    }

    @Bean
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "virtual")
    public TomcatProtocolHandlerCustomizer<?> virtualThreadCustomizer() {
      System.out.println("虚拟线程2启动");
        return protocolHandler -> 
            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
    }

    // 传统线程池模式 (默认)
    @Bean
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "pool", matchIfMissing = true)
    public AsyncTaskExecutor threadPoolExecutor() {
      System.out.println("线程池模式启动");
        return new TaskExecutorAdapter(Executors.newFixedThreadPool(200));
    }
}


package com.example;


import org.springframework.jdbc.core.JdbcTemplate

;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countUsers() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
    }
}


# 服务器配置
server.port=8080
server.tomcat.max-threads=200
server.tomcat.accept-count=1000



# 数据库配置jdbc:h2:D:\db
spring.datasource.url=jdbc:h2:D:\\db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=123456
spring.sql.init.mode=always

# H2控制台
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# 线程池配置
spring.task.execution.pool.core-size=200

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>test2</artifactId>
  <version>1.0.0</version>
  <name>VirtualThreadBenchmark</name>
  <packaging>jar</packaging>
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.5</version>
  </parent>

  <properties>
    <java.version>21</java.version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
    </dependency>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>

<!-- 
  
    
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
        <version>3.2.5</version>
    </dependency>-->
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <mainClass>com.example.Test2Application</mainClass>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <release>21</release>
        </configuration>
      </plugin>
    </plugins>
  </build>


</project>

结果：

C:\Users\Admin>D:\迅雷下载\httpd-2.4.63-250207-win64-VS17\Apache24\bin\ab -n 500000 -c 10000 http://localhost:8080/json
This is ApacheBench, Version 2.3 <$Revision: 1923142 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 50000 requests
Completed 100000 requests
Completed 150000 requests
Completed 200000 requests
Completed 250000 requests
Completed 300000 requests
Completed 350000 requests
Completed 400000 requests
Completed 450000 requests
Completed 500000 requests
Finished 500000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /json
Document Length:        28 bytes

Concurrency Level:      10000
Time taken for tests:   59.156 seconds
Complete requests:      500000
Failed requests:        0
Total transferred:      66500000 bytes
HTML transferred:       14000000 bytes
Requests per second:    8452.19 [#/sec] (mean)
Time per request:       1183.125 [ms] (mean)
Time per request:       0.118 [ms] (mean, across all concurrent requests)
Transfer rate:          1097.79 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.2      0       1
Processing:   482 1166  81.2   1162    1416
Waiting:       94  582 282.4    578    1339
Total:        482 1166  81.2   1162    1416

Percentage of the requests served within a certain time (ms)
  50%   1162
  66%   1183
  75%   1195
  80%   1205
  90%   1251
  95%   1274
  98%   1359
  99%   1387
 100%   1416 (longest request)

C:\Users\Admin>D:\迅雷下载\httpd-2.4.63-250207-win64-VS17\Apache24\bin\ab -n 500000 -c 10000 http://localhost:8080/json
This is ApacheBench, Version 2.3 <$Revision: 1923142 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 50000 requests
Completed 100000 requests
Completed 150000 requests
Completed 200000 requests
Completed 250000 requests
Completed 300000 requests
Completed 350000 requests
Completed 400000 requests
Completed 450000 requests
Completed 500000 requests
Finished 500000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /json
Document Length:        28 bytes

Concurrency Level:      10000
Time taken for tests:   66.297 seconds
Complete requests:      500000
Failed requests:        0
Total transferred:      66500000 bytes
HTML transferred:       14000000 bytes
Requests per second:    7541.85 [#/sec] (mean)
Time per request:       1325.935 [ms] (mean)
Time per request:       0.133 [ms] (mean, across all concurrent requests)
Transfer rate:          979.56 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.7      0     509
Processing:   493 1309 380.6   1171    2729
Waiting:       97  658 375.5    622    2372
Total:        493 1309 380.6   1171    2729

Percentage of the requests served within a certain time (ms)
  50%   1171
  66%   1191
  75%   1209
  80%   1232
  90%   2094
  95%   2305
  98%   2493
  99%   2682
 100%   2729 (longest request)

C:\Users\Admin>D:\迅雷下载\httpd-2.4.63-250207-win64-VS17\Apache24\bin\ab -n 500000 -c 10000 http://localhost:8080/json
This is ApacheBench, Version 2.3 <$Revision: 1923142 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 50000 requests
Completed 100000 requests
Completed 150000 requests
Completed 200000 requests
Completed 250000 requests
Completed 300000 requests
Completed 350000 requests
Completed 400000 requests
Completed 450000 requests
Completed 500000 requests
Finished 500000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /json
Document Length:        28 bytes

Concurrency Level:      10000
Time taken for tests:   60.268 seconds
Complete requests:      500000
Failed requests:        0
Total transferred:      66500000 bytes
HTML transferred:       14000000 bytes
Requests per second:    8296.25 [#/sec] (mean)
Time per request:       1205.364 [ms] (mean)
Time per request:       0.121 [ms] (mean, across all concurrent requests)
Transfer rate:          1077.54 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.2      0       1
Processing:   485 1188  93.1   1182    1717
Waiting:       94  596 288.9    595    1581
Total:        485 1188  93.1   1182    1717

Percentage of the requests served within a certain time (ms)
  50%   1182
  66%   1196
  75%   1205
  80%   1210
  90%   1231
  95%   1264
  98%   1462
  99%   1638
 100%   1717 (longest request)חיים מיום ליוםC:\Users\Admin>D:\迅雷下载\httpd-2.4.63-250207-win64-VS17\Apache24\bin\ab -n 500000 -c 10000 http://localhost:8080/json
This is ApacheBench, Version 2.3 <$Revision: 1923142 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 50000 requests
Completed 100000 requests
Completed 150000 requests
Completed 200000 requests
Completed 250000 requests
Completed 300000 requests
Completed 350000 requests
Completed 400000 requests
Completed 450000 requests
Completed 500000 requests
Finished 500000 requests

结论：50万个访问1万并发

用ab测试工具，测试 ab -n 500000 -c 10000 http://localhost:8080/json db查询+json序列化

jdk24：
虚拟线程耗时：59秒

线程池耗时：57秒

jdk21：

虚拟线程：66秒

线程池：60秒

虚拟线程性能接近，某些情况下优于线程池（因为线程池大小配置参数限制）但是毫无亮点！



另外补上go的Gin框架测试，同样访问首页什么逻辑都不做。输出hello world。

ab测试：D:\迅雷下载\httpd-2.4.63-250207-win64-VS17\Apache24\bin\ab -n 500000 -c 10000 http://localhost:8080/

go1.24的成绩：58.4-65秒 左右（ReleaseMode）

springboot3.2.5虚拟线程：54.6-62秒左右

springboot3.2.5线程池：58.4-62秒左右

javalin6.4框架虚拟线程：65-70秒左右（）性能还不如springboot+tomcat 让我感到很意外。
