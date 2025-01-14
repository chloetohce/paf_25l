package paf.lecture.paf_25l.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import paf.lecture.paf_25l.producer.model.Todo;
import paf.lecture.paf_25l.producer.service.ProducerService;

@SpringBootApplication
public class Paf25lApplication {

	public static void main(String[] args) {
		SpringApplication.run(Paf25lApplication.class, args);
	}

}
