package ru.swmi.quiz.domain.business;

public class QuizNotYetStarterException extends RuntimeException {
    public QuizNotYetStarterException(String message) {
        super(message);
    }
}
