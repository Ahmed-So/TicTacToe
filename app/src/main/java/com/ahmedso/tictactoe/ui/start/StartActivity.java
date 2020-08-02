package com.ahmedso.tictactoe.ui.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ahmedso.tictactoe.R;
import com.ahmedso.tictactoe.databinding.ActivityStartBinding;
import com.ahmedso.tictactoe.ui.pickSide.PickSideActivity;
import com.ahmedso.tictactoe.ui.play.PlayActivity;

public class StartActivity extends AppCompatActivity implements StartHandlers {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStartBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_start);
        binding.setLifecycleOwner(this);
        binding.setHandlers(this);
    }

    @Override
    public void startSinglePlayerMode() {
        startActivityForResult(new Intent(this, PickSideActivity.class), PickSideActivity.ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void startMultiPlayerMode() {
        startActivity(new Intent(this, PlayActivity.class)
                .putExtra(PlayActivity.INTENT_PLAYING_MODE_KEY, PlayActivity.INTENT_PLAYING_MODE_MULTI_PLAYER));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PickSideActivity.ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent(this, PlayActivity.class);
            intent.putExtra(PlayActivity.INTENT_PLAYING_MODE_KEY, PlayActivity.INTENT_PLAYING_MODE_SINGLE_PLAYER);
            intent.putExtra(PlayActivity.INTENT_FIRST_PLAYER_KEY, data.getStringExtra(PlayActivity.INTENT_FIRST_PLAYER_KEY));
            startActivity(intent);
        }
    }
}