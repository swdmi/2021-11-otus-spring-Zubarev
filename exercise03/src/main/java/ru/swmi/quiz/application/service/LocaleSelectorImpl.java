package ru.swmi.quiz.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.swmi.quiz.application.config.LocalizedResource;
import ru.swmi.quiz.application.config.QuestionsSourceProvider;

import java.util.*;

@Component
@AllArgsConstructor
public class LocaleSelectorImpl implements LocaleSelector {

    private final QuestionsSourceProvider questionsSourceProvider;
    private final IOService ioService;

    @Override
    public LocalizedResource selectLocale() {
        Map<Integer, String> tempLocaleMap = new HashMap<>();
        int index = 0;
        List<String> localizedResources = new ArrayList<>(questionsSourceProvider.getLocalizedResources().keySet().size());
        for (Map.Entry<String, LocalizedResource> localizedResource : questionsSourceProvider.getLocalizedResources().entrySet()) {
            tempLocaleMap.put(++index,  localizedResource.getKey());
            localizedResources.add(index + ". " + Locale.forLanguageTag(localizedResource.getValue().getLocale()).getDisplayLanguage());
        }
        ioService.printMessage("quiz.locale.select", new String[] {String.join("\n", localizedResources)});
        int selectedLocaleIndex = ioService.inputIntValue();
        return questionsSourceProvider.getLocalizedResources().get(tempLocaleMap.get(selectedLocaleIndex));
    }
}
