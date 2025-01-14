package paf.lecture.consumer.paf_25l_consumer.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import paf.lecture.consumer.paf_25l_consumer.model.Student;
import paf.lecture.consumer.paf_25l_consumer.model.Todo;

@Service
public class ConsumerService implements MessageListener {
    
    public void handleMessage(Todo todo) {
        System.out.println(todo.toString());
    }

    public void handleStudent(Student s) {
        System.out.println("Here");
        System.out.println(s);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String orderData = new String(message.getBody()); 
            System.out.println(orderData);
            
            // receiving the data into a string
            // need JSON-P to cast it back into an object

            // Call the API in day 24 using rest template to write to the MySQL database. 

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
