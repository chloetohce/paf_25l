package paf.lecture.consumer.paf_25l_consumer.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import paf.lecture.consumer.paf_25l_consumer.model.Student;
import paf.lecture.consumer.paf_25l_consumer.model.Todo;
import paf.lecture.consumer.paf_25l_consumer.service.ConsumerService;

@Configuration
public class RedisConfig {
    @Value("${redis.topic1}")
    private String redisTopic;

    @Value("${redis.topic2}")
    private String topic2;

    @Bean("todoTemplate")
    RedisTemplate<String, Todo> redisTemplate(RedisConnectionFactory connFac, @Qualifier("todoSerializer") Jackson2JsonRedisSerializer<Todo> serializer) {
        RedisTemplate<String, Todo> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connFac);
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean("todoSerializer")
    public Jackson2JsonRedisSerializer<Todo> jackson2JsonRedisSerializer() {
        return new Jackson2JsonRedisSerializer<>(Todo.class);
    }

    @Bean("todoContainer")
    public RedisMessageListenerContainer listenerContainer(@Qualifier("todoAdapter") MessageListenerAdapter messageListenerAdapter, RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListenerAdapter, new PatternTopic(redisTopic));
        return container;
    }

    @Bean("todoAdapter")
    public MessageListenerAdapter listenerAdapater(ConsumerService redisConsumerService) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(redisConsumerService, "handleMessage");
        adapter.setSerializer(new Jackson2JsonRedisSerializer<>(Todo.class));
        return adapter;
    }

    @Bean("studentTemplate")
    RedisTemplate<String, Student> studentTemplate(RedisConnectionFactory connFac, Jackson2JsonRedisSerializer<Student> serializer) {
        RedisTemplate<String, Student> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connFac);
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public Jackson2JsonRedisSerializer<Student> jackson2JsonRedisSerializerStudent() {
        return new Jackson2JsonRedisSerializer<>(Student.class);
    }

    @Bean
    public RedisMessageListenerContainer listenerContainerStudent(@Qualifier("studentAdapter") MessageListenerAdapter studentAdapter, 
            
            RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(studentAdapter, new PatternTopic(topic2));
        // container.addMessageListener(messageListenerAdapter, new PatternTopic(redisTopic));
        return container;
    }

    @Bean("studentAdapter")
    public MessageListenerAdapter listenerAdapaterStudent(ConsumerService redisConsumerService) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(redisConsumerService, "handleStudent");
        adapter.setSerializer(new Jackson2JsonRedisSerializer<>(Student.class));
        return adapter;
    }
}
