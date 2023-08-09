package ru.hogwarts.school.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

import static ru.hogwarts.school.service.StudentService.logger;


@RestController
@RequestMapping("/info")
public class InfoController {

    private final int port;

    public InfoController(@Value("${server.port}") int port) {
        this.port = port;
    }

    @GetMapping("/getPort")
    public int getPort() {
        return port;
    }

    @GetMapping("/sumIntTest")
    public int sumIntTest() {
        long startTime = System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a + 1).limit(1_000_000).reduce(0, (a, b) -> a + b);
        long time = System.currentTimeMillis() - startTime;
        logger.info("Function execution time " + time);
        return sum;
    }

    @GetMapping("/sumIntParallelTest")
    public int sumIntParallelTest() {
        long startTime = System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a + 1).limit(1_000_000).parallel().reduce(0, (a, b) -> a + b);
        long time = System.currentTimeMillis() - startTime;
        logger.info("Execution time of the parallel method function " + time);
        return sum;
    }
}
