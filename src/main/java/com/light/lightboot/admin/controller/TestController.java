package com.light.lightboot.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author wb
 * @Date: 2022/03/23/
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String testHello(){
        return "hello world";
    }
}
