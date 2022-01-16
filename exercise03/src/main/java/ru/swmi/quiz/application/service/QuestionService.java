package ru.swmi.quiz.application.service;

import ru.swmi.quiz.domain.model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions(int quantity);
}
