package com.ahmedso.tictactoe.models;

public class Difficulty {

    public static final Difficulty EASY = new Difficulty(1, 0);
    public static final Difficulty MEDIUM = new Difficulty(1, 1);
    public static final Difficulty HARD = new Difficulty(3, 0);
    public static final Difficulty EXPERT = new Difficulty(3, 3);

    private int complexity;
    private int depth;

    private Difficulty(int complexity, int depth) {
        this.complexity = complexity;
        this.depth = depth;
    }

    public int getComplexity() {
        return complexity;
    }

    public int getDepth() {
        return depth;
    }
}
