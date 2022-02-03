package ru.swmi.quiz.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.swmi.quiz.domain.model.Person;

@ShellComponent
@RequiredArgsConstructor
public class QuizCommands {

    private final QuizService quizService;
    private final PersonService personService;
    private final LocaleService localeService;
    private final MessageService messageService;

    private Person person;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login() {
        localeService.selectLanguage();
        this.person = personService.getPerson();
        return messageService.getMessage("shell.login.success", new Object[]{person});
    }

    @ShellMethod(value = "Quiz starting command", key = {"s", "start"})
    @ShellMethodAvailability(value = "isQuizStartCommandAvailable")
    public void startQuiz() {
        quizService.start(this.person);
    }

    private Availability isQuizStartCommandAvailable() {
        return person == null? Availability.unavailable("Сначала введите имя и фамилию"): Availability.available();
    }
}
