package edu.wcu.jstrong1.library;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsPage extends AppCompatActivity implements View.OnClickListener{

    private Button redBut;
    private Button greenBut;
    private Button blueBut;
    private Button orangeBut;
    private Button purpleBut;

    private AppSettings settings;

    private RelativeLayout mainLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);


        redBut = findViewById(R.id.sp_red_but);
        greenBut = findViewById(R.id.sp_green_but);
        blueBut = findViewById(R.id.sp_blue_but);
        orangeBut = findViewById(R.id.sp_orange_but);
        purpleBut = findViewById(R.id.sp_purple_but);
        mainLayout = findViewById(R.id.sp_main_layout);

        settings = (AppSettings) getApplication();

        redBut.setOnClickListener(this);
        greenBut.setOnClickListener(this);
        blueBut.setOnClickListener(this);
        orangeBut.setOnClickListener(this);
        purpleBut.setOnClickListener(this);

        String background = settings.getAppColor();
        int id = getResources().getIdentifier(background, "drawable", this.getPackageName());
        mainLayout.setBackgroundResource(id);
    }


    @Override
    public void onClick(View view) {
        if (view == redBut) {
            settings.setAppColor("red");
        } else if (view == greenBut) {
            settings.setAppColor("green");
        } else if (view == blueBut) {
            settings.setAppColor("blue");
        } else if (view == orangeBut) {
            settings.setAppColor("orange");
        } else if (view == purpleBut) {
            settings.setAppColor("purple");
        }

        String background = settings.getAppColor();
        int id = getResources().getIdentifier(background, "drawable", this.getPackageName());
        mainLayout.setBackgroundResource(id);
    }
}