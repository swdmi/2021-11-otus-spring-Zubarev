package ru.swmi.quiz.domain.model;

import java.util.List;

public class QuestionDTO {
    private final String questionText;
    private final List<String> answers;

    public QuestionDTO(Question question) {
        this.questionText = question.getQuestionText();
        this.answers = question.getAnswers();
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
