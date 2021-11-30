package ru.swmi.quiz.application.dao;

import org.springframework.stereotype.Component;
import ru.swmi.quiz.domain.model.Person;
import ru.swmi.quiz.application.service.QuizPresenter;

@Component
public class PersonDaoImpl implements PersonDao {

    private final QuizPresenter quizPresenter;

    public PersonDaoImpl(QuizPresenter quizPresenter) {
        this.quizPresenter = quizPresenter;
    }

    @Override
    public Person getPerson() {
        quizPresenter.print("Enter your first name:");
        String firstName = quizPresenter.inputStringValue();
        quizPresenter.print("Enter your last name:");
        String lastName = quizPresenter.inputStringValue();
        return new Person(firstName, lastName);
    }
}
