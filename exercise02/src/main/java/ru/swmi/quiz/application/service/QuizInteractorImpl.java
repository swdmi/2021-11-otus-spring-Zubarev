package ru.swmi.quiz.application.service;

import org.springframework.stereotype.Service;
import ru.swmi.quiz.domain.application.QuizInteractor;
import ru.swmi.quiz.domain.model.QuestionDTO;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class QuizInteractorImpl implements QuizInteractor {

    private final QuizPresenter quizPresenter;

    public QuizInteractorImpl(QuizPresenter quizPresenter) {
        this.quizPresenter = quizPresenter;
    }

    @Override
    public int answerQuestion(QuestionDTO question) {
        quizPresenter.print(question.getQuestionText() + "\n" +
                IntStream.range(0, question.getAnswers().size()).mapToObj(index -> (index + 1) + ". " + question.getAnswers().get(index)).collect(Collectors.joining("\n")));
        quizPresenter.print("Please, enter the number of the correct answer: ");
        return quizPresenter.inputIntValue();
    }
}
