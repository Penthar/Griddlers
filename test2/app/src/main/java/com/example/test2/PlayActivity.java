package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlayActivity extends Activity implements View.OnClickListener {
    Map map;
    PixelTile[][] board;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = getIntent();
        map = new Map(this, intent.getExtras().getString("Value"));
        board = new PixelTile[map.rows][map.colsumns];
        createBoard(map);

    }
    public void createBoard(Map map){
        LinearLayout layout = findViewById(R.id.boardArea);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams tileLayoutParams = new LinearLayout.LayoutParams(dpToPx(12), dpToPx(12));///CHANGE SIZE
        tileLayoutParams.setMargins(dpToPx(1), dpToPx(1), dpToPx(1), dpToPx(1));
        for (int i = 0; i < map.rows + map.longestSequenceInCol; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            layout.addView(linearLayout);
            for (int j = 0; j < map.colsumns + map.longestSequenceInRow; j++) {
                if(i < map.longestSequenceInCol && j < map.longestSequenceInRow){
                    TextView temptview = new TextView(this);
                    temptview.setLayoutParams(tileLayoutParams);
                    linearLayout.addView(temptview);
                }
                else if (i < map.longestSequenceInCol && j >= map.longestSequenceInRow){
                    TextView temptview = new TextView(this);
                    temptview.setLayoutParams(tileLayoutParams);
                    temptview.setText(""+ map.columnsSequences[j -  map.longestSequenceInRow].sequences.get(i));
                    if(temptview.getText().equals("0"))
                        temptview.setText("");
                    linearLayout.addView(temptview);
                }
                else if (i >= map.longestSequenceInCol && j < map.longestSequenceInRow){
                    TextView temptview = new TextView(this);
                    temptview.setLayoutParams(tileLayoutParams);
                    temptview.setText("" + map.rowsSequences[i -  map.longestSequenceInCol].sequences.get(j));
                    if(temptview.getText().equals("0"))
                        temptview.setText("");
                    linearLayout.addView(temptview);
                }
                else{
                    PixelTile pttemp = new PixelTile(this,0);
                    pttemp.setLayoutParams(tileLayoutParams);
                    linearLayout.addView(pttemp);
                    pttemp.setOnClickListener(this);
                    board[i - map.longestSequenceInCol][j - map.longestSequenceInRow] = pttemp;
                }
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
        ((PixelTile) view).changeStates();
    }
}