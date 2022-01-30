package ru.swmi.quiz.application.dao;

import ru.swmi.quiz.domain.model.Question;

import java.util.List;

public interface QuestionsDao {
    List<Question> getQuestions(String sourceName, int quantity);
}
