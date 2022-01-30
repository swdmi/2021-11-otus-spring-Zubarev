package ru.swmi.quiz.application.service;

import org.springframework.stereotype.Service;
import ru.swmi.quiz.application.config.QuizParamsProvider;
import ru.swmi.quiz.domain.business.Quiz;
import ru.swmi.quiz.domain.model.Person;
import ru.swmi.quiz.domain.model.Result;

import javax.annotation.PostConstruct;

@Service
public class QuizServiceImpl implements QuizService {

    private final PersonService personService;
    private final QuestionService questionService;
    private final LocaleService localeService;
    private final QuizInteractorImpl quizInteractor;
    private final IOService ioService;
    private final int passedScore;
    private final int questionsQuantity;

    public QuizServiceImpl(QuizParamsProvider quizParamsProvider,
                           PersonService personService, QuestionService questionService,
                           QuizInteractorImpl quizInteractor, LocaleService localeService, IOService ioService) {
        this.passedScore = quizParamsProvider.getPassedScore();
        this.questionsQuantity = quizParamsProvider.getQuestionsQuantity();
        this.personService = personService;
        this.questionService = questionService;
        this.quizInteractor = quizInteractor;
        this.localeService = localeService;
        this.ioService = ioService;
    }

    @PostConstruct
    public void init() {

    }

    @Override
    public void start() {
        localeService.selectLanguage();
        Person newPerson = personService.getPerson();
        Quiz quiz = new Quiz(questionService.getQuestions(questionsQuantity), newPerson, passedScore);
        quiz.start(quizInteractor);
        Result result = quiz.getResult();
        ioService.printMessage(result.isPassed() ? "quiz.result.passed" : "quiz.result.notPassed",
                new Object[]{result.getPerson().getFirstName(),
                        result.getPerson().getLastName(),
                        result.getScore()});
    }
}
