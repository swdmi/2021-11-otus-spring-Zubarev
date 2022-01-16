package ru.swmi.quiz.application.dao;

import ru.swmi.quiz.domain.model.Question;

import java.util.List;

public interface QuestionsParser {
    List<Question> parseQuestions(String source);
}
