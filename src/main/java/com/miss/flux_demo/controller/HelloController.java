package com.miss.flux_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


/**
 * @author miss
 * <p>
 * Created by miss on 2018/6/21
 */
@RestController
@RequestMapping("api")
public class HelloController {
private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @GetMapping("hello")
    public Mono<String> hello(@RequestParam(value = "name")String name)
    {
        logger.debug("hello  "+name);
        return Mono.just("hello  "+name);
    }
}
