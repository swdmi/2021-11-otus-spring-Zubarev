package ru.swmi.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.swmi.quiz.application.service.QuizService;

@SpringBootApplication
public class Exercise03Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Exercise03Application.class, args);
        QuizService quizService = applicationContext.getBean(QuizService.class);
        quizService.start();
    }

}
