package com.example.demo.threadpool.service;

/**
 * @author wanghao
 * @version 1.0.0
 * @ClassName AsyncService.java
 * @createTime 2023年02月01日
 */
public interface AsyncService {

    /** * 执行异步任务 * 可以根据需求，自己加参数拟定，我这里就做个测试演示 */
    void executeAsync();
}
