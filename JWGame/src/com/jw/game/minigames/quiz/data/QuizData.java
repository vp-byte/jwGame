package com.jw.game.minigames.quiz.data;

import java.util.ArrayList;
import java.util.List;

public class QuizData {

    public static List<String> lines() {
        List<String> lines = new ArrayList<>();
        lines.add("veryeasy;How many children had Noah?;a:one;b:tree;c:seven;d:ten;b");
        lines.add("veryeasy;Where live Jehova?;a:Haven;b:House;c:Temple;d:Sea;a");
        lines.add("easy;Who was the long livers men?;a:Noah;b:Isaak;c:Mathusal;d:Moses;c; Mathusal was 987 jears old");
        return lines;
    }
}
