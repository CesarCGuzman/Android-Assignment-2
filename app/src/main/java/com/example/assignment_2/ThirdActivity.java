package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    TextView editTextName;
    TextView getEditTextAge;
    TextView feelingNumber;
    TextView feelingWord;
    ImageView imageViewFeeling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setTitle("Profile");
        editTextName = findViewById(R.id.editTextName);
        getEditTextAge = findViewById(R.id.editTextAge);
        feelingNumber = findViewById(R.id.feelingNumber);
        feelingWord = findViewById(R.id.feelingWord);
        imageViewFeeling = findViewById(R.id.imageViewFeeling);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(MainActivity.USER_KEY)){
            User user = (User) getIntent().getSerializableExtra(MainActivity.USER_KEY);

            editTextName.setText(user.getName());
            getEditTextAge.setText(String.valueOf(user.getAge()));
            feelingNumber.setText(user.getMood() + " out of 4");
            feelingWord.setText(MainActivity.getFeelingWord(user.getMood()));
            MainActivity.setImage(imageViewFeeling, user.getMood());
        }

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}