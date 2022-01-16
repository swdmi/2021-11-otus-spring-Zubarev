package ru.swmi.quiz.application.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.swmi.quiz.application.config.QuestionsSourceProvider;

import java.util.Locale;

@Getter
@Setter
@Component
public class CurrentAppConfigHolderImpl implements CurrentAppConfigHolder {
    private Locale locale;
    private String sourceName;

    public CurrentAppConfigHolderImpl(QuestionsSourceProvider questionsSourceProvider) {
        this.locale = Locale.forLanguageTag(questionsSourceProvider.getDefaultLocalizedResource().getLocale());
        Locale.setDefault(locale);
        this.sourceName = questionsSourceProvider. getDefaultLocalizedResource().getSource();
    }
}
