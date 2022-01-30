package ru.swmi.quiz.application.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class LocalizedResource {
    private String locale;
    private String source;
    private boolean defaultLocale;
}
