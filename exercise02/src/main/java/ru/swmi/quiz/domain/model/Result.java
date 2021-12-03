package ru.swmi.quiz.domain.model;

public class Result {

    private final Person person;
    private final int score;
    private final boolean isPassed;

    public Result(Person person, int score, boolean isPassed) {
        this.person = person;
        this.score = score;
        this.isPassed = isPassed;
    }

    public int getScore() {
        return score;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public Person getPerson() {
        return person;
    }
}
