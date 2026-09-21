package com.questionsgenerator.service;

import com.questionsgenerator.collections.Questions;
import com.questionsgenerator.functions.QuizDto;
import com.questionsgenerator.repo.QuestionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionGeneratorServiceImpl implements  QuestionGeneratorService{

    private Logger logger= LoggerFactory.getLogger(QuestionGeneratorServiceImpl.class);

    private ChatClient chatClient;
    private QuestionRepo questionRepo;
    public QuestionGeneratorServiceImpl(ChatClient.Builder builder,QuestionRepo questionRepo){
        this.chatClient=builder.build();
        this.questionRepo=questionRepo;
    }


    @Override
    public void generateAndSaveQuestions(QuizDto quizDto) {

        try {
            logger.info("Generating questions for quiz: {}", quizDto.getTitle());

            List<Questions> questions =
                    this.generateQuestion(
                            quizDto.getTitle(),
                            10,
                            quizDto.getDescription()
                    );

            logger.info("Questions generated: {}", questions.size());

            List<Questions> questionsList = questions.stream()
                    .map(question -> {
                        question.setQuizId(quizDto.getId());
                        return question;
                    })
                    .toList();

            questionRepo.saveAll(questionsList);

            logger.info("Question saved successfully");

            questionsList.forEach(e ->
                    logger.info("Question: {}", e.getQuestion())
            );

        } catch (Exception e) {
            logger.error("ERROR WHILE GENERATING/SAVING QUESTIONS", e);
            throw e;
        }
    }

    @Override
    public List<Questions> generateQuestion(
            String quizName,
            int numberOfQuestion,
            String description) {

        String systemString = """
            As a coding, technology, programming and framework expert,
            your primary role is to generate high-quality questions for quizzes.
            """;

        String promptString = """
            Generate {numberOfQuestion} questions for {quizName} quiz.
            Having description: {description}
            """;

        List<Questions> questions = this.chatClient.prompt()
                .system(systemString)
                .user(promptUserSpec -> promptUserSpec
                        .text(promptString)
                        .param("numberOfQuestion", numberOfQuestion)
                        .param("quizName", quizName)
                        .param("description", description))
                .call()
                .entity(new ParameterizedTypeReference<List<Questions>>() {
                });

        return questions;
    }


}
