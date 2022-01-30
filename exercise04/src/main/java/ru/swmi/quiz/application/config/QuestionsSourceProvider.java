package ru.swmi.quiz.application.config;

import java.util.Map;

public interface QuestionsSourceProvider {
    String getBasePath();
    Map<String, LocalizedResource> getLocalizedResources();
    LocalizedResource getDefaultLocalizedResource();

}
