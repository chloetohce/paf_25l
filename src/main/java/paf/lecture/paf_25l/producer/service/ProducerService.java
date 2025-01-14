package paf.lecture.paf_25l.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import paf.lecture.paf_25l.producer.model.Student;
import paf.lecture.paf_25l.producer.model.Todo;

@Service
public class ProducerService {
    @Autowired @Qualifier("todo")
    RedisTemplate<String, Todo> redisTemplate;

    @Autowired
    RedisTemplate<String, Student> studentTemplate;

    @Autowired
    @Qualifier("order")
    RedisTemplate<String, Order> orderTemplate;

    @Value("${redis.topic1}")
    private String topic1;

    @Value("${redis.topic2}")
    private String topic2;

    @Autowired
    ChannelTopic channelTopic; // Using a bean instead. Same thing. 

    /**
     * To publish the comment into the redis queue.
     * @param todo to be added into Redis
     */
    public void sendMessage(Todo todo) {
        redisTemplate.convertAndSend(topic1, todo);
    }

    public void sendStudent(Student s) {
        studentTemplate.convertAndSend(topic2, s);
    }

    public long publish(Order oder) {
        return orderTemplate.convertAndSend(channelTopic.getTopic(), order);
    }
}
