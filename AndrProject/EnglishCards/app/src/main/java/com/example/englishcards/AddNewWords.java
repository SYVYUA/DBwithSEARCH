package com.example.englishcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewWords extends AppCompatActivity {

    Button addWord, readWord, clearWord;
    EditText fieldForWord, fieldForWord2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_words);

        addWord = findViewById(R.id.addWord);
        addWord.setOnClickListener(this);
        readWord = findViewById(R.id.readWord);
        readWord.setOnClickListener(this);
        clearWord = findViewById(R.id.clearWord);
        clearWord.setOnClickListener(this);
        fieldForWord = findViewById(R.id.fieldForWord);
        fieldForWord2 = findViewById(R.id.fieldForWord2);

    }

    public void onClick(View v){

        String word = fieldForWord.getText().toString();
        String word2 = fieldForWord2.getText().toString();

        switch (v.getId()){

            case R.id.addWord:

                break;
            case R.id.readWord:

                break;
            case R.id.clearWord:

                break;
        }

    }
}