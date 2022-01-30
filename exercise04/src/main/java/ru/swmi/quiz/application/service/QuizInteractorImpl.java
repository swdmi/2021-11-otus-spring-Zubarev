package ru.swmi.quiz.application.service;

import org.springframework.stereotype.Service;
import ru.swmi.quiz.domain.application.QuizInteractor;
import ru.swmi.quiz.domain.model.QuestionDTO;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class QuizInteractorImpl implements QuizInteractor {

    private final IOService ioService;

    public QuizInteractorImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public int answerQuestion(QuestionDTO question) {
        String answers = IntStream.range(0, question.getAnswers().size()).mapToObj(index -> (index + 1) + ". " + question.getAnswers().get(index)).collect(Collectors.joining("\n"));
        ioService.printMessage("quiz.askQuestion", new Object[]{question.getQuestionText(), answers});
        return ioService.inputIntValue();
    }
}
