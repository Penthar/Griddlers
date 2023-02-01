package com.example.test2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

public class PixelTile extends View {
    SharedPreferences sharedPref;
    int state;
    public PixelTile(@NonNull Context context, int State) {
        super(context);
        this.state=State;

        sharedPref  = context.getSharedPreferences("MyPref",context.MODE_PRIVATE);
        this.setBackgroundColor(this.getColor());
    }
    private int getColor(){
        if(state == 0)
            return Color.parseColor("#FFFFFF");
        else if (state==1) {
            Log.d("COLOR", sharedPref.getString("color", "#00000000"));
            return Color.parseColor(sharedPref.getString("color", "#000000"));
        }
            //return R.color.black;
        else
            return Color.parseColor("#FF0000");
    }
    public void changeStates(){
        state = (state+1)%3;
        this.setBackgroundColor(this.getColor());
    }
}
