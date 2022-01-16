package ru.swmi.quiz.application.service;

import org.springframework.stereotype.Service;
import ru.swmi.quiz.application.config.QuestionsSourceProvider;
import ru.swmi.quiz.application.dao.QuestionsDao;
import ru.swmi.quiz.domain.model.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionsDao questionsDao;
    private final String questionsSourceBasePath;
    private final CurrentAppConfigHolder currentAppConfigHolder;

    public QuestionServiceImpl(QuestionsSourceProvider questionsSourceProvider, QuestionsDao questionsDao, CurrentAppConfigHolder currentAppConfigHolder) {
        this.questionsDao = questionsDao;
        this.questionsSourceBasePath = questionsSourceProvider.getBasePath();
        this.currentAppConfigHolder = currentAppConfigHolder;
    }

    @Override
    public List<Question> getQuestions(int quantity) {
        String sourceName = questionsSourceBasePath + "/" + currentAppConfigHolder.getSourceName();
        return questionsDao.getQuestions(sourceName, quantity);
    }
}
