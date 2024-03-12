package com.study.redis.controller;


import com.study.redis.queue.MessagePublisher;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
@AllArgsConstructor
public class QueueController {

    private final MessagePublisher publisher;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postMessage(@PathVariable Long id) {
        publisher.publish("Message: " + id);
    }
}
