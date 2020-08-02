package com.ahmedso.tictactoe.ui.pickSide;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ahmedso.tictactoe.R;
import com.ahmedso.tictactoe.databinding.ActivityPickSideBinding;
import com.ahmedso.tictactoe.ui.play.PlayActivity;

public class PickSideActivity extends AppCompatActivity implements PickSideHandlers {

    public static final int ACTIVITY_REQUEST_CODE = 1112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPickSideBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_pick_side);
        binding.setLifecycleOwner(this);
        binding.setHandlers(this);
    }

    @Override
    public void pickSide(String side) {
        setResult(RESULT_OK, new Intent().putExtra(PlayActivity.INTENT_FIRST_PLAYER_KEY, side));
        finish();
    }
}