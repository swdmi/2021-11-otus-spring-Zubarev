package ru.swmi.quiz.application.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.swmi.quiz.application.config.LocalizedResource;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class LocaleServiceImplTest {

    @Autowired
    private LocaleService localeService;

    @Autowired
    private CurrentAppConfigHolder currentAppConfigHolder;

    @MockBean
    private LocaleSelector localeSelector;

    private static LocalizedResource localizedResource;

    @BeforeAll
    static void setupClass() {
        localizedResource = new LocalizedResource();
        localizedResource.setDefaultLocale(true);
        localizedResource.setLocale("en");
        localizedResource.setSource("questions.csv");
    }

    @Test
    @DisplayName("Проверяет установку выбранной локали в приложение")
    void testSelectLocale() {
        when(localeSelector.selectLocale()).thenReturn(localizedResource);
        localeService.selectLanguage();
        assertThat(currentAppConfigHolder.getLocale()).isEqualTo(Locale.forLanguageTag(localizedResource.getLocale()));
        assertThat(currentAppConfigHolder.getSourceName()).isEqualTo(localizedResource.getSource());
    }
}