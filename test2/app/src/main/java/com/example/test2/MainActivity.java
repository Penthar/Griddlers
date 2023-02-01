package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {
    Button bCreate;
    Button bPlay;
    Button bColor;
    EditText input;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bCreate = findViewById(R.id.create);
        bPlay = findViewById(R.id.play);
        bCreate.setOnClickListener(this);
        bPlay.setOnClickListener(this);
        input = findViewById(R.id.data);
        bColor = findViewById(R.id.color);
        bColor.setOnClickListener(this);
        sharedPref  = getSharedPreferences("MyPref",MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == bCreate.getId()){
            Intent intent = new Intent(MainActivity.this, CreateActivity.class);
            intent.putExtra("Value", input.getText().toString());
            startActivity(intent);
        }
        else if(view.getId() == bColor.getId()){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("color", input.getText().toString());
            editor.apply();
            input.setText("");

        }
        else{
            Intent intent = new Intent(MainActivity.this, PlayActivity.class);
            intent.putExtra("Value", input.getText().toString());
            startActivity(intent);
        }
    }
}