package ru.swmi.quiz.application.service;

import java.util.Locale;

public interface CurrentAppConfigHolder {
    Locale getLocale();
    void setLocale(Locale locale);
    String getSourceName();
    void setSourceName(String sourceName);
}
