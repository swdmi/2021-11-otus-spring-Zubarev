package ru.swmi.quiz.domain.business;

import ru.swmi.quiz.domain.application.QuizInteractor;
import ru.swmi.quiz.domain.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class Quiz {

    private final List<Answer> answers;
    private final Person person;
    private int score;
    private boolean started;
    private final int passedScore;

    public Quiz(List<Question> questions, Person person, int passedScore) {
        this.answers = questions.stream().map(Answer::new).collect(Collectors.toList());
        this.person = person;
        this.passedScore = passedScore;
    }

    public void start(QuizInteractor quizInteractor) {
        this.started = true;
        for (Answer answer : answers) {
            answer.setAnswerId(quizInteractor.answerQuestion(new QuestionDTO(answer.getQuestion())));
            if (answer.isCorrect()) {
                score++;
            }
        }
    }

    public Result getResult() {
        if (!started) {
            throw new QuizNotYetStarterException("Test not yet started");
        } else {
            return new Result(person, this.score, this.isPassed());
        }
    }

    private boolean isPassed() {
        return score >= passedScore;
    }
}