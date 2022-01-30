package ru.swmi.quiz.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Result {
    private final Person person;
    private final int score;
    private final boolean isPassed;
}
