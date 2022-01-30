package ru.swmi.quiz.domain.model;

import lombok.Getter;

import java.util.List;

@Getter
public class QuestionDTO {
    private final String questionText;
    private final List<String> answers;

    public QuestionDTO(Question question) {
        this.questionText = question.getQuestionText();
        this.answers = question.getAnswers();
    }
}
