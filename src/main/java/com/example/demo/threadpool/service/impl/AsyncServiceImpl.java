package com.example.demo.threadpool.service.impl;

import com.example.demo.threadpool.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author wanghao
 * @version 1.0.0
 * @ClassName AsyncServiceImpl.java
 * @createTime 2023年02月01日
 */
@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {

        String threadName = Thread.currentThread().getName();
//        log.info("start executeAsync {}",threadName);

        System.out.println("异步线程要做的事情"+threadName);
        System.out.println("可以在这里执行批量插入等耗时的事情"+threadName);
        System.out.println("子线程名称：" + threadName);

//        log.info("end executeAsync {}",threadName);
    }
}
