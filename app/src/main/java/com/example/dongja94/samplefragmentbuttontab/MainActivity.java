package com.example.dongja94.samplefragmentbuttontab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Tab1Fragment.OnMessageReceiver {

    Button selectButton;

    private static final String TAB1_TAG = "tab1";
    private static final String TAB2_TAG = "tab2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn_tab1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment old = getSupportFragmentManager().findFragmentByTag(TAB1_TAG);
                if (old == null) {
                    Fragment f = Tab1Fragment.newInstance("button creation...");
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.slide_left_in, R.anim.rotate_out);
                    ft.replace(R.id.container, f, TAB1_TAG);
                    ft.commit();

                    setSelectButton((Button) v);
                }
            }
        });

        if (savedInstanceState == null) {
            Fragment f = Tab1Fragment.newInstance("onCreate ....");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, f, TAB1_TAG);
            ft.commit();
            setSelectButton(btn);
        }

        btn = (Button)findViewById(R.id.btn_tab2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment old = getSupportFragmentManager().findFragmentByTag(TAB2_TAG);
                if (old == null) {
                    Fragment f = new Tab2Fragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.slide_left_in, R.anim.rotate_out);
                    ft.replace(R.id.container, f, TAB2_TAG);
                    ft.commit();

                    setSelectButton((Button) v);
                }
            }
        });

    }

    public void onMessageReceived(String message) {
        Toast.makeText(this, "activity : " + message, Toast.LENGTH_SHORT).show();
    }

    private void setSelectButton(Button button) {
        if (selectButton != button) {
            if (selectButton != null) {
                selectButton.setSelected(false);
            }
            selectButton = button;
            selectButton.setSelected(true);
        }
    }
}
