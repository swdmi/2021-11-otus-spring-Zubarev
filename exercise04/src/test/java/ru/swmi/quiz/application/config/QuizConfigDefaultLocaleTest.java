package ru.swmi.quiz.application.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuizConfigDefaultLocaleTest {

    @Autowired
    private QuizConfig quizConfig;

    @Test
    @DisplayName("Возвращает дефолтную локаль, заданную в конфиге")
    void getConfigDefaultLocalizedResource() {
        LocalizedResource expectedLocalizedResource = new LocalizedResource();
        expectedLocalizedResource.setDefaultLocale(true);
        expectedLocalizedResource.setLocale("en");
        expectedLocalizedResource.setSource("questions.csv");
        assertThat(quizConfig.getDefaultLocalizedResource()).usingRecursiveComparison().isEqualTo(expectedLocalizedResource);
    }
}