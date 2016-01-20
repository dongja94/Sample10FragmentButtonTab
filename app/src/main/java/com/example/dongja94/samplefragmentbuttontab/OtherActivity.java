package com.example.dongja94.samplefragmentbuttontab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OtherActivity extends AppCompatActivity implements Tab1Fragment.OnMessageReceiver {

    TextView messageView;
    EditText inputView;

    public static final String EXTRA_MESSAGE = "message";
    public static final String RESULT_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        messageView = (TextView)findViewById(R.id.text_message);
        inputView=(EditText)findViewById(R.id.edit_result);
        Button btn = (Button)findViewById(R.id.btn_finish);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = inputView.getText().toString();
                Intent data = new Intent();
                data.putExtra(RESULT_DATA, result);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });

        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        messageView.setText(message);

    }

    @Override
    public void onMessageReceived(String message) {
        Toast.makeText(this, "other activity : " + message, Toast.LENGTH_SHORT).show();
    }
}
