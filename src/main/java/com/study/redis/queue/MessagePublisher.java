package com.study.redis.queue;

public interface MessagePublisher {

    void publish(String message);
}
