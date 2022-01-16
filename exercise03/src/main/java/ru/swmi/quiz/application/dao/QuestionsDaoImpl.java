package ru.swmi.quiz.application.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.swmi.quiz.domain.model.Question;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class QuestionsDaoImpl implements QuestionsDao {

    private final ContentLoader contentLoader;
    private final QuestionsParser questionsParser;

    @Override
    public List<Question> getQuestions(String sourceName, int quantity) {
        List<Question> questions = questionsParser.parseQuestions(contentLoader.readResource(sourceName));

        return getRandomNumbers(quantity, questions.size()).stream().map(questions::get).collect(Collectors.toList());
    }

    private Set<Integer> getRandomNumbers(int requiredAmount, int range) {
        Random random = new Random();
        Set<Integer> numbersSet = new HashSet<>(requiredAmount);

        while (numbersSet.size() < requiredAmount) {
            while (!numbersSet.add(random.nextInt(range))) ;
        }
        return numbersSet;
    }
}
