package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    SeekBar seekBarMood;
    TextView seekMoodProgress;
    public ImageView imageViewImage;
    final static public String PROGRESS_VALUE = "PROGRESS_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Select Mood");
        seekBarMood = findViewById(R.id.seekBarMood);
        seekMoodProgress = findViewById(R.id.seekMoodProgress);
        imageViewImage = findViewById(R.id.imageViewImage);

        seekBarMood.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekMoodProgress.setText(String.valueOf(i));

                MainActivity.setImage(imageViewImage, i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        findViewById(R.id.btnMoodSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progressValue = seekBarMood.getProgress();

                Intent intent = new Intent();
                intent.putExtra(PROGRESS_VALUE, progressValue);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}