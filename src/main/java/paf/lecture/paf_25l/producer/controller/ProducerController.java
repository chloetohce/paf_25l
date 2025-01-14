package paf.lecture.paf_25l.producer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import paf.lecture.paf_25l.producer.model.Todo;
import paf.lecture.paf_25l.producer.service.ProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import paf.lecture.paf_25l.producer.model.Student;



@RestController
@RequestMapping("/api")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @GetMapping("/messages")
    public ResponseEntity<String> sendMessage() {
        for (int i = 0; i < 10; i++) {
			Todo todo = new Todo();
			todo.setId(i);
			todo.setTask(" " + i);
			producerService.sendMessage(todo);
		}
        return new ResponseEntity<>("Message sent.", HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<String> sendStudent(@RequestBody Student s) {
        System.out.println(s.getName());
        producerService.sendStudent(s);
        producerService.sendStudent(new Student(2, "test", LocalDate.now()));
        return new ResponseEntity<>("Students sent. ", HttpStatus.OK);
    }
    
    
}
