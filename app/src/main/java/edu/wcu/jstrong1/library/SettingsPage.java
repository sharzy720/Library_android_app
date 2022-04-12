package edu.wcu.jstrong1.library;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Class for settings_page.xml
 */
public class SettingsPage extends AppCompatActivity implements View.OnClickListener{

    private Button redBut;
    private Button greenBut;
    private Button blueBut;
    private Button orangeBut;
    private Button purpleBut;
    private Button lightModeBut;
    private Button darkModeBut;

    /** App's application class */
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
        lightModeBut = findViewById(R.id.sp_light_but);
        darkModeBut = findViewById(R.id.sp_dark_but);
        mainLayout = findViewById(R.id.sp_main_layout);

        settings = (AppSettings) getApplication();

        redBut.setOnClickListener(this);
        greenBut.setOnClickListener(this);
        blueBut.setOnClickListener(this);
        orangeBut.setOnClickListener(this);
        purpleBut.setOnClickListener(this);
        lightModeBut.setOnClickListener(this);
        darkModeBut.setOnClickListener(this);

        String background = settings.getAppColor();
        int id = getResources().getIdentifier(background, "drawable", this.getPackageName());
        mainLayout.setBackgroundResource(id);
    }

    @Override
    public void onClick(View view) {
        if (view == redBut) {
            settings.setAppGradient("red");
        } else if (view == greenBut) {
            settings.setAppGradient("green");
        } else if (view == blueBut) {
            settings.setAppGradient("blue");
        } else if (view == orangeBut) {
            settings.setAppGradient("orange");
        } else if (view == purpleBut) {
            settings.setAppGradient("purple");
        } else if (view == lightModeBut) {
            settings.setDarkMode(false);
        } else if (view == darkModeBut) {
            settings.setDarkMode(true);
        }

        String background = settings.getAppColor();
        int id = getResources().getIdentifier(background, "drawable", this.getPackageName());
        mainLayout.setBackgroundResource(id);

        settings.saveSettings();
    }
}
