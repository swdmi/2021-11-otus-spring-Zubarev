package ru.swmi.quiz.application.dao;

import org.junit.jupiter.api.BeforeAll;
import ru.swmi.quiz.domain.model.Question;

import java.util.List;

public class QuizAbstractTest {

    protected static String source;
    protected static List<Question> questions;

    @BeforeAll
    static void setUpClass() {
        source = "Text of Question 1?,1,Yes,No\r\n" +
                "Text of Question 2?,3,Yes,No,May be\r\n" +
                "Text of Question 3?,1,Yes,No\r\n" +
                "Text of Question 4?,1,Yes,No\r\n" +
                "Text of Question 5?,1,Yes,No";
        questions = List.of(
                new Question("Text of Question 1?", List.of("Yes", "No"), 1),
                new Question("Text of Question 2?", List.of("Yes", "No", "May be"), 3),
                new Question("Text of Question 3?", List.of("Yes", "No"), 1),
                new Question("Text of Question 4?", List.of("Yes", "No"), 1),
                new Question("Text of Question 5?", List.of("Yes", "No"), 1));
    }
}
