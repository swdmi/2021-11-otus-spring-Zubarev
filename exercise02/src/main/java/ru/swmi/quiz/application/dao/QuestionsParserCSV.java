package ru.swmi.quiz.application.dao;

import org.springframework.stereotype.Component;
import ru.swmi.quiz.domain.model.Question;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class QuestionsParserCSV implements QuestionsParser {
    private final int QUESTION_TEXT_INDEX = 0;
    private final int CORRECT_ANSWER_INDEX = 1;

    @Override
    public List<Question> parseQuestions(String source) {
        return source
                .lines()
                .map(line -> {
                    String[] values = line.split(",");
                    return new Question(values[QUESTION_TEXT_INDEX],
                            IntStream.range(QUESTION_TEXT_INDEX, values.length)
                                    .filter(index -> index > CORRECT_ANSWER_INDEX)
                                    .mapToObj(index -> values[index])
                                    .collect(Collectors.toList()),
                            Integer.parseInt(values[CORRECT_ANSWER_INDEX]));
                })
                .collect(Collectors.toList());
    }
}
