package ru.swmi.quiz.application.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.swmi.quiz.application.presenter.QuizPresenter;

@AllArgsConstructor
@Service
public class IOServiceImpl implements IOService {
    private final MessageService messageService;
    private final QuizPresenter quizPresenter;

    @Override
    public String inputStringValue() {
        return quizPresenter.inputStringValue();
    }

    @Override
    public int inputIntValue() {
        return quizPresenter.inputIntValue();
    }

    @Override
    public void printMessage(String message, Object[] args) {
        String message1 = messageService.getMessage(message, args);
        quizPresenter.print(message1);
    }
}
