package com.ahmedso.tictactoe.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TicTacToe {

    private int[][] board;
    private ArrayList<String> emptyPoints;
    private Point lastPoint;
    private int winLine = -1;

    public TicTacToe() {
        board = new int[3][];
        board[0] = new int[3];
        board[1] = new int[3];
        board[2] = new int[3];
        emptyPoints = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8"));
        Collections.shuffle(emptyPoints);
        lastPoint = new Point(-1, -1);
    }

    private TicTacToe(int[][] board, ArrayList<String> emptyPoints, Point lastPoint) {
        this.board = board;
        this.emptyPoints = emptyPoints;
        this.lastPoint = lastPoint;
    }

    public boolean isFinished() {
        return winLine > -1 || emptyPoints.isEmpty();
    }

    public int[][] getBoard() {
        return board;
    }

    public List<String> getEmptyPoints() {
        return emptyPoints;
    }

    public Point getLastPoint() {
        return lastPoint;
    }

    public int getWinLine() {
        return winLine;
    }

    public int checkPoint(Point point, int val) {
        lastPoint = point;
        board[point.getRow()][point.getColumn()] = val;
        emptyPoints.remove(point.toString());

        int result = checkWin(point);
        if (result > -1)
            winLine = result;
        return result;
    }

    private int checkWin(Point point) {
        if (board[point.getRow()][0] == board[point.getRow()][1] &&
                board[point.getRow()][1] == board[point.getRow()][2])
            return point.getRow();

        if (board[0][point.getColumn()] == board[1][point.getColumn()] &&
                board[1][point.getColumn()] == board[2][point.getColumn()])
            return point.getColumn() + 3;

        if (point.getRow() == point.getColumn())
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
                return 6;

        if (point.getRow() + point.getColumn() == 2)
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
                return 7;

        return -1;
    }

    @NonNull
    @Override
    public TicTacToe clone() {
        int[][] tempBoard = new int[3][];

        tempBoard[0] = board[0].clone();
        tempBoard[1] = board[1].clone();
        tempBoard[2] = board[2].clone();

        return new TicTacToe(tempBoard, new ArrayList<>(emptyPoints), lastPoint.clone());
    }
}
