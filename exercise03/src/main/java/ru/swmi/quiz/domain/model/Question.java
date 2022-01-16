package ru.swmi.quiz.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Question {

    private final String questionText;
    private final List<String> answers;
    private final int correctAnswerId;
}
