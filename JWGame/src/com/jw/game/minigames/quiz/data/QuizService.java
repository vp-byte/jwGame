package com.jw.game.minigames.quiz.data;

import com.jme3.asset.AssetManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizService {

    private final AssetManager assetManager;
    List<Quiz> quizlist = null;

    public QuizService(AssetManager assetManager) {
        this.assetManager = assetManager;
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
        quizlist.stream().forEach((quiz) -> {
            for (Difficulty difficulty : difficulties) {
                if (quiz.getDifficulty() == difficulty) {
                    quizs.add(quiz);
                }
            }
        });
        return quizs;
    }

    private List<Quiz> loadFromFile() {
        QuizDataLoader quizDataLoader = new QuizDataLoader(assetManager);
        
        try {
            Locale.setDefault(Locale.GERMANY);
            Locale locale = Locale.getDefault();
            System.err.println(locale);
            if(locale.toString().equals("en_US")){
                
            } 
            return parseLineList(quizDataLoader.load("/Files/questions_ru"));
        } catch (IOException ex) {
            Logger.getLogger(QuizService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Quiz> parseLineList(List<String> lines) {
        List<Quiz> quizs = new ArrayList<>();
        lines.stream().forEach((line) -> {
            quizs.add(parseLine(line));
        });
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
