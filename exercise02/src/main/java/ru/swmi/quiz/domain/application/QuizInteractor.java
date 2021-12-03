package ru.swmi.quiz.domain.application;

import ru.swmi.quiz.domain.model.QuestionDTO;

public interface QuizInteractor {
    int answerQuestion(QuestionDTO question);
}
