package com.ahmedso.tictactoe.models;

import androidx.annotation.NonNull;

public class Point {

    private int row;
    private int column;

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Point(String number) {
        int pointNumber = Integer.parseInt(number);
        this.row = pointNumber / 3;
        this.column = pointNumber % 3;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @NonNull
    @Override
    protected Point clone() {
        return new Point(row, column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return row == point.row && column == point.column;
    }

    @Override
    public String toString() {
        return String.valueOf((row * 3) + column);
    }
}
