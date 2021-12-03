package ru.swmi.quiz.domain.model;

import java.util.List;
import java.util.Objects;

public class Question {

    private final String questionText;
    private final List<String> answers;
    private final int correctAnswerId;

    public Question(String questionText, List<String> answers, int correctAnswerId) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerId = correctAnswerId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return correctAnswerId == question.correctAnswerId && Objects.equals(questionText, question.questionText) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText, answers, correctAnswerId);
    }
}
