package com.example.assignment_2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView editTextName;
    TextView editTextAge;
    ImageView imageViewFeeling;
    int moodRating = -1;
    final static public String USER_KEY = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main");
        imageViewFeeling = findViewById(R.id.imageViewFeeling);

        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result != null && result.getResultCode() == RESULT_OK){
                    if(result.getData() != null && result.getData().hasExtra(SecondActivity.PROGRESS_VALUE)){
                        Intent data = result.getData();
                        moodRating = data.getIntExtra(SecondActivity.PROGRESS_VALUE, 2);

                        setImage(imageViewFeeling, moodRating);
                    }
                }
            }
        });

        findViewById(R.id.tellBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startForResult.launch(intent);
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextName = findViewById(R.id.editTextName);
                editTextAge = findViewById(R.id.editTextAge);
                String inputStringName = editTextName.getText().toString();
                String inputStringAge = editTextAge.getText().toString();
                if(inputStringAge.isEmpty() || inputStringName.isEmpty() || moodRating == -1){
                    Toast.makeText(MainActivity.this, "Must fill Name, Age, and mood", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                    intent.putExtra(USER_KEY, new User(inputStringName, Integer.parseInt(inputStringAge), moodRating));
                    startActivity(intent);
                }
            }
        });
    }

    public static void setImage(ImageView imageView, int i){
        if (i == 0){
            imageView.setImageResource(R.drawable.not_well);
        } else if (i == 1) {
            imageView.setImageResource(R.drawable.sad);
        } else if (i == 2) {
            imageView.setImageResource(R.drawable.ok);
        } else if (i == 3) {
            imageView.setImageResource(R.drawable.good);
        } else if (i == 4) {
            imageView.setImageResource(R.drawable.very_good);
        }
    }

    public static String getFeelingWord(int i){
        String string = "";
        if (i == 0){
            string = "Not Well";
        } else if (i == 1) {
            string = "Sad";
        } else if (i == 2) {
            string = "Ok";
        } else if (i == 3) {
            string = "Good";
        } else if (i == 4) {
            string = "Very Good";
        }
        return string;
    }
}