package com.ahmedso.tictactoe.ui.play;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedso.tictactoe.models.Difficulty;
import com.ahmedso.tictactoe.models.MiniMax;
import com.ahmedso.tictactoe.models.Point;
import com.ahmedso.tictactoe.models.TicTacToe;

public class PlayViewModel extends ViewModel {

    private int xValue = MiniMax.AI_CELL_VALUE;
    private int oValue = MiniMax.USER_CELL_VALUE;
    private MutableLiveData<Integer> currentTurn = new MutableLiveData<>(MiniMax.USER_CELL_VALUE);
    private MutableLiveData<Integer> xWinsCount = new MutableLiveData<>(0);
    private MutableLiveData<Integer> oWinsCount = new MutableLiveData<>(0);
    private boolean multiPlayer = false;
    private boolean boardEnabled = true;
    private int firstPlayer = MiniMax.USER_CELL_VALUE;
    private TicTacToe ticTacToe = new TicTacToe();
    private MutableLiveData[][] boardUI = new MutableLiveData[3][];
    private Difficulty difficulty = Difficulty.EASY;

    public PlayViewModel() {
        for (int row = 0; row < 3; row++) {
            boardUI[row] = new MutableLiveData[3];
            for (int col = 0; col < 3; col++)
                boardUI[row][col] = new MutableLiveData<>(0);
        }
    }

    public int getXValue() {
        return xValue;
    }

    public int getOValue() {
        return oValue;
    }

    public void setStartPlayer(String firstPlayer) {
        xValue = firstPlayer.equals(PlayActivity.INTENT_FIRST_PLAYER_X) ? MiniMax.USER_CELL_VALUE : MiniMax.AI_CELL_VALUE;
        oValue = xValue * -1;
    }

    public LiveData<Integer> getCurrentTurn() {
        return currentTurn;
    }

    public LiveData<Integer> getXWinsCount() {
        return xWinsCount;
    }

    public LiveData<Integer> getOWinsCount() {
        return oWinsCount;
    }

    public boolean isMultiPlayer() {
        return multiPlayer;
    }

    public void setMultiPlayer(boolean multiPlayer) {
        this.multiPlayer = multiPlayer;
    }

    public boolean isBoardEnabled() {
        return boardEnabled;
    }

    public void setBoardEnabled(boolean boardEnabled) {
        this.boardEnabled = boardEnabled;
    }

    public int getFirstPlayer() {
        return firstPlayer;
    }

    public void swapFirstPlayer() {
        this.firstPlayer *= -1;
    }

    public TicTacToe getTicTacToe() {
        return ticTacToe;
    }

    public LiveData<Integer>[][] getBoardUI() {
        return boardUI;
    }

    public void updateBoardUI(int row, int column, int val) {
        this.boardUI[row][column].setValue(val);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void startNewGame() {
        swapFirstPlayer();
        currentTurn.setValue(firstPlayer);
        ticTacToe = new TicTacToe();
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                updateBoardUI(row, col, 0);
    }

    public void check(Point point) {
        if (ticTacToe.getBoard()[point.getRow()][point.getColumn()] != 0)
            return;

        int winLine = ticTacToe.checkPoint(point, currentTurn.getValue());
        updateBoardUI(point.getRow(), point.getColumn(), currentTurn.getValue());
        if (winLine > -1) {
            if (currentTurn.getValue() == xValue)
                xWinsCount.setValue(xWinsCount.getValue() + 1);
            else
                oWinsCount.setValue(oWinsCount.getValue() + 1);
        } else
            this.currentTurn.setValue(currentTurn.getValue() * -1);
    }
}
