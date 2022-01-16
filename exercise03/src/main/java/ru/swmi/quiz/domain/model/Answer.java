package ru.swmi.quiz.domain.model;

public class Answer {

    private final Question question;
    private int answerId;

    public Answer(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public boolean isCorrect() {
        return this.question.getCorrectAnswerId() == this.answerId;
    }
}
