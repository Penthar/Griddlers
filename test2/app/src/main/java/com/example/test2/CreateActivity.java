package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateActivity extends Activity implements View.OnClickListener {
    PixelTile[][] board;
    int cols;
    int rows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Intent intent = getIntent();
        String[] strings = intent.getExtras().getString("Value").split("X");
        rows = Integer.valueOf(strings[0]);
        cols = Integer.valueOf(strings[1]);
        board = new PixelTile[rows][cols];
        TextView tview = findViewById(R.id.title);
        tview.setText(intent.getExtras().getString("Value"));
        Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(this);
        createBoard(rows, cols);

    }
    private void createBoard(int rows, int cols){
        LinearLayout layout = findViewById(R.id.boardArea);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams tileLayoutParams = new LinearLayout.LayoutParams(dpToPx(12), dpToPx(12));///CHANGE SIZE
        tileLayoutParams.setMargins(dpToPx(1), dpToPx(1), dpToPx(1), dpToPx(1));
        for (int i = 0; i < rows; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            layout.addView(linearLayout);
            for (int j = 0; j < cols; j++) {
                PixelTile pttemp = new PixelTile(this, 0);
                pttemp.setLayoutParams(tileLayoutParams);
                linearLayout.addView(pttemp);
                pttemp.setOnClickListener(this);
                board[i][j] = pttemp;
            }
        }
    }

    private int dpToPx(int dps) {
// Get the screen's density scale
        final float scale =

                getResources().getDisplayMetrics().density;
// Convert the dps to pixels, based on density scale
        int pixels = (int) (dps * scale + 0.5f);
        return pixels;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.save){
            EditText input = findViewById(R.id.fileName);
            saveFiles(input.getText().toString());
        }
        else{
            ((PixelTile) view).changeStates();
        }
    }
    private void saveFiles(String fileName){
        File file = new File(getExternalFilesDir(null), fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(rows);
            fos.write(cols);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if(board[i][j].state == 1){
                        fos.write(1);
                        Log.d("input to file", "saveFiles: 1");
                    }
                    else{
                        fos.write(0);
                        Log.d("input to file", "saveFiles: 0");
                    }
                }
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}