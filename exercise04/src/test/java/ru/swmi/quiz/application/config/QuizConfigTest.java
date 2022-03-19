package ru.swmi.quiz.application.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("french")
class QuizConfigTest {

    @Autowired
    private QuizConfig quizConfig;

    @Test
    @DisplayName("Возращает дефолтную локаль, когда ни одна из локалей не задана в конфиге как дефолтная")
    void getNonSetDefaultLocalizedResource() {
        LocalizedResource expectedLocalizedResource = new LocalizedResource();
        expectedLocalizedResource.setLocale("fr");
        expectedLocalizedResource.setSource("questions.csv");
        assertThat(quizConfig.getDefaultLocalizedResource()).usingRecursiveComparison().isEqualTo(expectedLocalizedResource);
    }
}