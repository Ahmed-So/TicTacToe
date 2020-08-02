package com.ahmedso.tictactoe.models;

public class CalculatedPoint extends Point {

    int calculatedValue;

    public CalculatedPoint(int row, int column, int calculatedValue) {
        super(row, column);
        this.calculatedValue = calculatedValue;
    }

    public CalculatedPoint(Point point, int calculatedValue) {
        super(point.getRow(), point.getColumn());
        this.calculatedValue = calculatedValue;
    }
}
