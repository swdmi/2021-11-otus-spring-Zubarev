package ru.swmi.quiz.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.swmi.quiz.domain.model.Person;

@AllArgsConstructor
@Component
public class PersonServiceImpl implements PersonService {

    private final IOService ioService;

    @Override
    public Person getPerson() {
        ioService.printMessage("quiz.person.firstname", null);
        String firstName = ioService.inputStringValue();
        ioService.printMessage("quiz.person.lastname", null);
        String lastName = ioService.inputStringValue();
        return new Person(firstName, lastName);
    }
}
