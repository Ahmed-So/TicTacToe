package com.ahmedso.tictactoe.ui.play;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ahmedso.tictactoe.R;
import com.ahmedso.tictactoe.databinding.ActivityPlayBinding;
import com.ahmedso.tictactoe.models.Difficulty;
import com.ahmedso.tictactoe.models.MiniMax;
import com.ahmedso.tictactoe.models.Point;

public class PlayActivity extends AppCompatActivity implements PlayHandlers {

    public static final String INTENT_PLAYING_MODE_KEY = "Playing Mode";
    public static final boolean INTENT_PLAYING_MODE_SINGLE_PLAYER = false;
    public static final boolean INTENT_PLAYING_MODE_MULTI_PLAYER = true;
    public static final String INTENT_FIRST_PLAYER_KEY = "First Player";
    public static final String INTENT_FIRST_PLAYER_X = "X starts";
    public static final String INTENT_FIRST_PLAYER_O = "O starts";

    private ActivityPlayBinding binding;
    private PlayViewModel viewModel;
    private Handler aiHandler;
    private Runnable aiRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_play);
        viewModel = new ViewModelProvider(this).get(PlayViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.setHandlers(this);
        fetchData();
        initEvents();
    }

    private void fetchData() {
        viewModel.setMultiPlayer(getIntent().getBooleanExtra(INTENT_PLAYING_MODE_KEY, INTENT_PLAYING_MODE_SINGLE_PLAYER));
        if (!viewModel.isMultiPlayer()) {
            viewModel.setStartPlayer(getIntent().getStringExtra(INTENT_FIRST_PLAYER_KEY));
            aiHandler = new Handler();
            aiRunnable = () -> {
                check(MiniMax.start(viewModel.getTicTacToe(), viewModel.getDifficulty()));
                viewModel.setBoardEnabled(true);
            };
        }
    }

    private void initEvents() {
        binding.rgDifficulty.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.rb_difficulty_medium:
                    changeDifficulty(Difficulty.MEDIUM);
                    break;
                case R.id.rb_difficulty_hard:
                    changeDifficulty(Difficulty.HARD);
                    break;
                case R.id.rb_difficulty_expert:
                    changeDifficulty(Difficulty.EXPERT);
                    break;
                default:
                    changeDifficulty(Difficulty.EASY);
            }
        });
    }

    @Override
    public void check(int row, int column) {
        if (viewModel.getTicTacToe().isFinished() || !viewModel.isBoardEnabled())
            return;
        check(new Point(row, column));
    }

    private void check(Point point) {
        viewModel.check(point);

        if (viewModel.getTicTacToe().isFinished()) {
            binding.btnPlayNewGame.setVisibility(View.VISIBLE);
            int winLine = viewModel.getTicTacToe().getWinLine();
            if (winLine > -1)
                drawWinLine(winLine);
        } else if (!viewModel.isMultiPlayer() && viewModel.getCurrentTurn().getValue() == MiniMax.AI_CELL_VALUE)
            startAI();
    }

    private void changeDifficulty(Difficulty difficulty) {
        if (!viewModel.isBoardEnabled())
            return;
        viewModel.setDifficulty(difficulty);
        playAgain();
    }

    @Override
    public void playAgain() {
        viewModel.startNewGame();
        binding.btnPlayNewGame.setVisibility(View.GONE);
        if (!viewModel.isMultiPlayer() && viewModel.getFirstPlayer() == MiniMax.AI_CELL_VALUE)
            startAI();

        binding.vPlayWinLineX.setRotation(0);
        binding.vPlayWinLineX.setVisibility(View.GONE);
        binding.vPlayWinLineY.setVisibility(View.GONE);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) binding.getRoot());
        constraintSet.setVerticalBias(R.id.v_play_win_line_x, 0.5f);
        constraintSet.setHorizontalBias(R.id.v_play_win_line_y, 0.5f);
        constraintSet.applyTo((ConstraintLayout) binding.getRoot());
    }

    private void startAI() {
        viewModel.setBoardEnabled(false);
        aiHandler.postDelayed(aiRunnable, 700);
    }

    private void drawWinLine(int winLine) {
        if (winLine >= 3 && winLine <= 5) {
            binding.vPlayWinLineY.setVisibility(View.VISIBLE);
            if (winLine != 4) {
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone((ConstraintLayout) binding.getRoot());
                constraintSet.setHorizontalBias(R.id.v_play_win_line_y, winLine == 3 ? 0.16f : 0.84f);
                constraintSet.applyTo((ConstraintLayout) binding.getRoot());
            }
        } else {
            binding.vPlayWinLineX.setVisibility(View.VISIBLE);
            if (winLine == 0 || winLine == 2) {
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone((ConstraintLayout) binding.getRoot());
                constraintSet.setVerticalBias(R.id.v_play_win_line_x, winLine == 0 ? 0.16f : 0.84f);
                constraintSet.applyTo((ConstraintLayout) binding.getRoot());
            } else if (winLine == 6)
                binding.vPlayWinLineX.setRotation(45);
            else if (winLine == 7)
                binding.vPlayWinLineX.setRotation(135);
        }
    }
}