package com.github.nsteps.springk8shelmconcource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringK8sHelmConcourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringK8sHelmConcourceApplication.class, args);
    }

}


@RestController
class TestController {
    @GetMapping
    public void test() {
        System.out.println("test from " + Thread.currentThread().getName());
    }
}