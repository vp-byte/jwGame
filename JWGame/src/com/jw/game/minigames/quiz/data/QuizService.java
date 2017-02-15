package com.jw.game.minigames.quiz.data;

import java.util.ArrayList;
import java.util.List;

public class QuizService {

    List<Quiz> quizlist = null;

    public QuizService() {
        quizlist = loadFromFile();
    }

    public List<Quiz> getQuizlist() {
        return quizlist;
    }

    public void setQuizlist(List<Quiz> quizlist) {
        this.quizlist = quizlist;
    }

    public List<Quiz> getAllQuizByDifficulty(Difficulty... difficulties) {
        List<Quiz> quizs = new ArrayList<>();
        for (Quiz quiz : quizlist) {
            for (Difficulty difficulty : difficulties) {
                if (quiz.getDifficulty() == difficulty) {
                    quizs.add(quiz);
                }
            }
        }
        return quizs;
    }

    public List<Quiz> getAllQuizByDifficulty(Difficulty difficulty) {
        List<Quiz> quizs = new ArrayList<>();
        for (Quiz quiz : quizlist) {
            if (quiz.getDifficulty() == difficulty) {
                quizs.add(quiz);
            }
        }
        return quizs;
    }

    public List<Quiz> loadFromFile() {
        // TODO load data from file (win/linux/mac/android)
        return parseLineList(QuizData.lines());
    }

    public List<Quiz> parseLineList(List<String> lines) {
        List<Quiz> quizs = new ArrayList<>();
        for (String line : lines) {
            quizs.add(parseLine(line));
        }
        return quizs;
    }

    public Quiz parseLine(String line) {
        String[] values = line.split(";");
        Quiz quiz = new Quiz();
        quiz.setDifficulty(Difficulty.parseIgnoreCase(values[0]));
        quiz.setQuestion(values[1]);
        quiz.setAnswer_a(values[2].split(":")[1]);
        quiz.setAnswer_b(values[3].split(":")[1]);
        quiz.setAnswer_c(values[4].split(":")[1]);
        quiz.setAnswer_d(values[5].split(":")[1]);
        quiz.setRight(Right.parseIgnoreCase(values[6]));
        if (values.length >= 8) {
            quiz.setDescription(values[7]);
        }
        return quiz;
    }
}
