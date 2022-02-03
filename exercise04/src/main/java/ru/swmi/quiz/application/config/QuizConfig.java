package ru.swmi.quiz.application.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "quiz")
@Component
public class QuizConfig implements QuizParamsProvider, QuestionsSourceProvider {
    private int passedScore;
    private int questionsQuantity;
    private Questions questions;

    @Getter
    @Setter
    public static class Questions {
        private String basePath;
        private Map<String, LocalizedResource> localizedResources;

    }

    @Override
    public String getBasePath() {
        return questions.getBasePath();
    }

    @Override
    public Map<String, LocalizedResource> getLocalizedResources() {
        return questions.getLocalizedResources();
    }

    @Override
    public LocalizedResource getDefaultLocalizedResource() {
        return questions.getLocalizedResources().values().stream()
                .filter(LocalizedResource::isDefaultLocale)
                .findFirst()
                .orElse(questions.getLocalizedResources().values().iterator().next());
    }


}
