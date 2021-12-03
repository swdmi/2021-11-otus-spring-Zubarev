package ru.swmi.quiz;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.swmi.quiz.application.service.QuizService;

@ComponentScan
@PropertySource("classpath:application.properties")
public class SpringQuiz {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringQuiz.class);
        QuizService quizService = context.getBean(QuizService.class);
        quizService.start();
    }
}
