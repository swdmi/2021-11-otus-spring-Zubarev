package ru.swmi.quiz.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.swmi.quiz.application.config.LocalizedResource;

import java.util.*;

@Component
@AllArgsConstructor
public class LocaleServiceImpl implements LocaleService {

    private final CurrentAppConfigHolder currentAppConfigHolder;
    private final LocaleSelector localeSelector;

    @Override
    public void selectLanguage() {
        LocalizedResource selectedLocalizedResource = localeSelector.selectLocale();
        currentAppConfigHolder.setLocale(Locale.forLanguageTag(selectedLocalizedResource.getLocale()));
        currentAppConfigHolder.setSourceName(selectedLocalizedResource.getSource());
    }
}
