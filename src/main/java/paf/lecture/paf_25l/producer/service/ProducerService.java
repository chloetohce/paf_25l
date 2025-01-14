package paf.lecture.paf_25l.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import paf.lecture.paf_25l.producer.model.Todo;

@Service
public class ProducerService {
    @Autowired @Qualifier("todo")
    RedisTemplate<String, Todo> redisTemplate;

    @Value("${redis.topic1}")
    private String topic1;

    /**
     * To publish the comment into the redis queue.
     * @param todo to be added into Redis
     */
    public void sendMessage(Todo todo) {
        redisTemplate.convertAndSend(topic1, todo);
    }
}
