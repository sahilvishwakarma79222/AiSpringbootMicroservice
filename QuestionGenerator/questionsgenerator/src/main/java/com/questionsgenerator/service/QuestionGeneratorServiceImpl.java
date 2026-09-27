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

    private final Logger logger= LoggerFactory.getLogger(QuestionGeneratorServiceImpl.class);

    private final ChatClient chatClient;
    private final QuestionRepo questionRepo;
    public QuestionGeneratorServiceImpl(ChatClient.Builder builder,QuestionRepo questionRepo){
        this.chatClient=builder.build();
        this.questionRepo=questionRepo;
    }


//    @Override
//    public void generateAndSaveQuestions(QuizDto quizDto) {
//
//        try {
//            logger.info("Generating questions for quiz: {}", quizDto.getTitle());
//
//            List<Questions> questions =
//                    this.generateQuestion(
//                            quizDto.getTitle(),
//                            10,
//                            quizDto.getDescription()
//                    );
//
//            logger.info("Questions generated: {}", questions.size());
//
//            List<Questions> questionsList = questions.stream()
//                    .map(question -> {
////                        question.setId(null); // Important
//                        question.setQuizId(quizDto.getId());
//                        return question;
//                    })
//                    .toList();
//
//            questionRepo.saveAll(questionsList);
//
//            logger.info("Question saved successfully");
//
//            questionsList.forEach(e ->
//                    logger.info("Question: {}", e.getQuestion())
//            );
//
//        } catch (Exception e) {
//            logger.error("ERROR WHILE GENERATING/SAVING QUESTIONS", e);
//            throw e;
//        }
//    }
@Override
public void generateAndSaveQuestions(QuizDto quizDto) {

    try {

        logger.info("==========================================");
        logger.info("Generating questions for quiz: {}", quizDto.getTitle());

        List<Questions> questions = generateQuestion(
                quizDto.getTitle(),
                10,
                quizDto.getDescription()
        );

        logger.info("Questions generated: {}", questions.size());

        List<Questions> questionsList = questions.stream()
                .map(question -> {
                    question.setId(null); // Mongo generates new ObjectId
                    question.setQuizId(quizDto.getId());
                    return question;
                })
                .toList();

        logger.info("Questions to save: {}", questionsList.size());

        List<Questions> savedQuestions = questionRepo.saveAll(questionsList);

        logger.info("Saved count: {}", savedQuestions.size());

        savedQuestions.forEach(q ->
                logger.info("Saved -> id={}, question={}",
                        q.getId(),
                        q.getQuestion())
        );

        logger.info("Total documents in Mongo: {}", questionRepo.count());
        logger.info("==========================================");

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
            You are an expert Java and programming quiz creator.
            Generate high quality MCQ questions.
            """;

        String promptString = """
            Generate {numberOfQuestion} multiple choice questions for {quizName}.

            Description:
            {description}

            Rules:
            - Generate only valid MCQ questions.
            - Each question must have:
              question
              option1
              option2
              option3
              option4
              answer
            - Do not generate id field.
            - Return only JSON array.
            """;

        try {

            List<Questions> questions = this.chatClient.prompt()
                    .system(systemString)
                    .user(userSpec -> userSpec
                            .text(promptString)
                            .param("numberOfQuestion", numberOfQuestion)
                            .param("quizName", quizName)
                            .param("description", description))
                    .call()
                    .entity(new ParameterizedTypeReference<List<Questions>>() {});

            logger.info("Generated {} questions", questions.size());

            questions.forEach(q ->
                    logger.info("Question => {}", q.getQuestion())
            );

            return questions;

        } catch (Exception e) {
            logger.error("ERROR WHILE GENERATING QUESTIONS", e);
            throw e;
        }
    }

//    @Override
//    public List<Questions> generateQuestion(
//            String quizName,
//            int numberOfQuestion,
//            String description) {
//
//        String systemString = """
//            As a coding, technology, programming and framework expert,
//            your primary role is to generate high-quality questions for quizzes.
//            """;
//
//        String promptString = """
//            Generate {numberOfQuestion} questions for {quizName} quiz.
//            Having description: {description}
//            """;
//
//        List<Questions> questions = this.chatClient.prompt()
//                .system(systemString)
//                .user(promptUserSpec -> promptUserSpec
//                        .text(promptString)
//                        .param("numberOfQuestion", numberOfQuestion)
//                        .param("quizName", quizName)
//                        .param("description", description))
//                .call()
//                .entity(new ParameterizedTypeReference<List<Questions>>() {
//                });
//
//        return questions;
//    }


}
