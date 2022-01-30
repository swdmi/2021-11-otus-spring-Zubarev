package ru.swmi.quiz.application.service;

public interface IOService {

    String inputStringValue();

    int inputIntValue();

    void printMessage(String message, Object[] args);
}
