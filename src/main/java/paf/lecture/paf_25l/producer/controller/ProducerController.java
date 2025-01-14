package paf.lecture.paf_25l.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paf.lecture.paf_25l.producer.model.Todo;
import paf.lecture.paf_25l.producer.service.ProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/messages")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @PostMapping("")
    public ResponseEntity<String> sendMessage(@RequestBody Todo todo) {
        producerService.sendMessage(todo);
        return new ResponseEntity<>("Message sent.", HttpStatus.OK);
    }
    
}
