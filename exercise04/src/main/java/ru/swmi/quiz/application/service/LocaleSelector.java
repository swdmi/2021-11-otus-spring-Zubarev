package ru.swmi.quiz.application.service;

import ru.swmi.quiz.application.config.LocalizedResource;

public interface LocaleSelector {
    LocalizedResource selectLocale();
}
