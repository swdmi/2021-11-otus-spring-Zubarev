package ru.swmi.quiz.application.service;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;
    private final CurrentAppConfigHolder currentConfigHolder;

    @Override
    public String getMessage(String placeholder, Object[] args) {
        return messageSource.getMessage(placeholder, args, currentConfigHolder.getLocale());
    }
}
