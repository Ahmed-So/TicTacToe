package com.ahmedso.tictactoe.models;

public class MiniMax {

    public static final int AI_CELL_VALUE = 1;
    public static final int USER_CELL_VALUE = -1;

    private int complexity;

    private MiniMax(int complexity) {
        this.complexity = complexity;
    }

    public static Point start(TicTacToe board, Difficulty difficulty) {
        return new MiniMax(difficulty.getComplexity()).max(board, difficulty.getDepth(), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private CalculatedPoint max(TicTacToe board, int depth, int alpha, int beta) {
        if (board.isFinished())
            return new CalculatedPoint(board.getLastPoint(), calcBoard(board, depth));

        int bestScore = Integer.MIN_VALUE;
        int result = 0;

        TicTacToe tempBoard;
        int score;
        for (int count = 0; count < board.getEmptyPoints().size(); count++) {
            tempBoard = board.clone();
            tempBoard.checkPoint(new Point(board.getEmptyPoints().get(count)), AI_CELL_VALUE);
            if (depth != 0)
                score = min(tempBoard, depth - 1, alpha, beta).calculatedValue;
            else
                score = calcBoard(tempBoard, depth);

            if (score > bestScore) {
                bestScore = score;
                result = count;
            }
            if (bestScore >= beta)
                break;
            if (bestScore > alpha)
                alpha = bestScore;
        }

        return new CalculatedPoint(new Point(board.getEmptyPoints().get(result)), bestScore);
    }

    private CalculatedPoint min(TicTacToe board, int depth, int alpha, int beta) {
        if (board.isFinished())
            return new CalculatedPoint(board.getLastPoint(), calcBoard(board, depth));

        int bestScore = Integer.MAX_VALUE;
        int result = 0;

        TicTacToe tempBoard;
        int score;
        for (int count = 0; count < board.getEmptyPoints().size(); count++) {
            tempBoard = board.clone();
            tempBoard.checkPoint(new Point(board.getEmptyPoints().get(count)), USER_CELL_VALUE);
            if (depth != 0)
                score = max(tempBoard, depth - 1, alpha, beta).calculatedValue;
            else
                score = calcBoard(tempBoard, depth);

            if (score < bestScore) {
                bestScore = score;
                result = count;
            }
            if (bestScore <= alpha) break;
            if (bestScore < beta)
                beta = bestScore;
        }

        return new CalculatedPoint(new Point(board.getEmptyPoints().get(result)), bestScore);
    }

    private int calcBoard(TicTacToe board, int depth) {
        int result = 0;
        for (int count = 0; count < 8; count++)
            result += getLineScore(getLine(board.getBoard(), count));

        if (depth > 1 && board.getWinLine() > -1)
            return result * (depth + 1);
        else
            return result;
    }

    private int[] getLine(int[][] board, int lineId) {
        switch (lineId) {
            case 0:
                return board[0];
            case 1:
                return board[1];
            case 2:
                return board[2];
            case 3:
                return new int[]{board[0][0], board[1][0], board[2][0]};
            case 4:
                return new int[]{board[0][1], board[1][1], board[2][1]};
            case 5:
                return new int[]{board[0][2], board[1][2], board[2][2]};
            case 6:
                return new int[]{board[0][0], board[1][1], board[2][2]};
            case 7:
                return new int[]{board[0][2], board[1][1], board[2][0]};
        }
        return null;
    }

    private int getLineScore(int[] line) {
        int sum = line[0] + line[1] + line[2];
        if (sum == 0)
            return 0;
        int mul = line[0] * line[1] * line[2];
        if (sum + mul == 0)
            return 0;

        return ((int) Math.pow(complexity, Math.abs(sum))) * (sum / Math.abs(sum));
    }
}
