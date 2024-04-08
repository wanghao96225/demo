package com.example.demo.threadpool.controller;

import com.example.demo.threadpool.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghao
 * @version 1.0.0
 * @ClassName AsyncController.java
 * @createTime 2023年02月01日
 */
@RestController
@RequestMapping("/testThread")
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/start")
    public void async() {
        for (int i = 0; i < 10; i++) {
            asyncService.executeAsync();
        }
    }
}
