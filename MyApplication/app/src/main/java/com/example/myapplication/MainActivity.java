package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.message_edit_text);
        final TextView textView = (TextView) findViewById(R.id.textView);
        final Switch options = (Switch) findViewById(R.id.options);
        final RadioGroup fonts = (RadioGroup) findViewById(R.id.chooser);

        textView.append("Your last messages are:\n");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = String.valueOf(editText.getText());
                editText.setText("");
                textView.append(message + "\n");
            }
        });

        options.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(),
                        "You turn the options to " + (isChecked? "visible":"invisible"),
                        Toast.LENGTH_SHORT).show();
                if (isChecked) {
                    textView.setVisibility(View.INVISIBLE);
                } else {
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });

        fonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.bigFont:
                        textView.setTextSize(R.dimen.BigTextSize);
                        break;
                    case R.id.mediumFont:
                        textView.setTextSize(R.dimen.MediumTextSize);
                        break;
                    case R.id.smallFont:
                        textView.setTextSize(R.dimen.SmallTextSize);
                        break;
                    default:
                        break;
                }
            }
        });
    }

}

