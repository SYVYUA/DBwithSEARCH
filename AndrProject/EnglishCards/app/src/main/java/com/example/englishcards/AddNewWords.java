package com.example.englishcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewWords extends AppCompatActivity {

    Button addWord, readWord, clearWord;
    EditText fieldForWord, fieldForWord2;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_words);

        addWord = findViewById(R.id.addWord);
        addWord.setOnClickListener((View.OnClickListener) this);
        readWord = findViewById(R.id.readWord);
        readWord.setOnClickListener((View.OnClickListener)this);
        clearWord = findViewById(R.id.clearWord);
        clearWord.setOnClickListener((View.OnClickListener)this);
        fieldForWord = findViewById(R.id.fieldForWord);
        fieldForWord2 = findViewById(R.id.fieldForWord2);

        dbHelper = new DBHelper(this);
    }

    public void onClick(View v){

        String word = fieldForWord.getText().toString();
        String word2 = fieldForWord2.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        switch (v.getId()){

            case R.id.addWord:
                contentValues.put(DBHelper.KEY_WORD, word);
                contentValues.put(DBHelper.KEY_WORD2,word2);

                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;
            case R.id.readWord:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if(cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int wordIndex = cursor.getColumnIndex(DBHelper.KEY_WORD);
                    int word2Index = cursor.getColumnIndex(DBHelper.KEY_WORD2);
                        do{
                            Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                    ", word - " + cursor.getString(wordIndex) +
                                    ", word2 = " + cursor.getString(word2Index));
                        } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor.close();
                break;
            case R.id.clearWord:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;
        }
        dbHelper.close();
    }
}