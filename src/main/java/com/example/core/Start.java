package com.example.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/17 21:56
 */
@Component
@Order(1)
public class Start implements CommandLineRunner {
    @Autowired
    private Sandbox sandbox;
    @Override
    public void run(String... args) throws Exception {
        sandbox.query();
    }
}
