package ru.swmi.quiz.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

@Component
public class QuizPresenterConsole implements QuizPresenter {

    private final Scanner scanner;
    private final PrintWriter writer;

    public QuizPresenterConsole(@Value("#{ T(java.lang.System).in }") InputStream inputStream,
                                @Value("#{ T(java.lang.System).out }") OutputStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.writer = new PrintWriter(outputStream, true);
    }

    @Override
    public String inputStringValue() {
        return this.scanner.nextLine();
    }

    @Override
    public int inputIntValue() {
        return this.scanner.nextInt();
    }

    @Override
    public void print(String value) {
        this.writer.println(value);
    }

}
