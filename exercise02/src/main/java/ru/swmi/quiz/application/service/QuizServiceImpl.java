package ru.swmi.quiz.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.swmi.quiz.application.dao.PersonDao;
import ru.swmi.quiz.application.dao.QuestionsDao;
import ru.swmi.quiz.domain.model.Person;
import ru.swmi.quiz.domain.business.Quiz;
import ru.swmi.quiz.domain.model.Result;

@Service
public class QuizServiceImpl implements QuizService {

    private final String resourceName;
    private final PersonDao personDao;
    private final QuestionsDao questionsDao;
    private final QuizPresenter quizPresenter;
    private final QuizInteractorImpl quizInteractor;
    private final int passedScore;
    private final int questionsAmount;

    public QuizServiceImpl(@Value("${quiz.questions.resourceName}") String resourceName,
                           @Value("${quiz.passedScore}") int passedScore,
                           @Value("${quiz.questions.quantity}") int questionsAmount,
                           PersonDao personDao, QuestionsDao questionsDao, QuizPresenter quizPresenter,
                           QuizInteractorImpl quizInteractor) {
        this.resourceName = resourceName;
        this.passedScore = passedScore;
        this.questionsAmount = questionsAmount;
        this.personDao = personDao;
        this.questionsDao = questionsDao;
        this.quizPresenter = quizPresenter;
        this.quizInteractor = quizInteractor;
    }

    @Override
    public void start() {
        Person newPerson = personDao.getPerson();
        Quiz quiz = new Quiz(questionsDao.getQuestions(resourceName, questionsAmount), newPerson, passedScore);
        quiz.start(quizInteractor);
        Result result = quiz.getResult();
        quizPresenter.print(String.format("Dear, %s %s. Your test result is %d. Test is %s",
                result.getPerson().getFirstName(),
                result.getPerson().getLastName(),
                result.getScore(),
                result.isPassed() ? "passed" : "not passed"));
    }
}
