package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.message_edit_text);
        final ListView messages = (ListView) findViewById(R.id.listView);

        final Switch options = (Switch) findViewById(R.id.options);
        final RadioGroup fonts = (RadioGroup) findViewById(R.id.chooser);

        final ArrayList<String> ListItems = new ArrayList<>();
        final TextAdapter<String> adapter = new TextAdapter<>(this,
                android.R.layout.simple_list_item_1, ListItems);

        adapter.add(getString(R.string.first_message));
        adapter.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.MediumTextSize));
        messages.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = String.valueOf(editText.getText());
                editText.setText("");
                adapter.add(message);
            }
        });

        options.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(),
                        "You turn the options to " + (isChecked ? "visible" : "invisible"),
                        Toast.LENGTH_SHORT).show();
                if (isChecked) {
                    messages.setVisibility(View.INVISIBLE);
                    fonts.setVisibility(View.VISIBLE);
                } else {
                    messages.setVisibility(View.VISIBLE);
                    fonts.setVisibility(View.INVISIBLE);
                }
            }
        });

        fonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.bigFont:
                        adapter.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.BigTextSize));
                        break;
                    case R.id.mediumFont:
                        adapter.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.MediumTextSize));
                        break;
                    case R.id.smallFont:
                        adapter.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.SmallTextSize));
                        break;
                    default:
                        break;
                }
            }
        });
     }

    class TextAdapter<T> extends ArrayAdapter<T> {

        float textSize = 0;
        int unit = 0;

        TextAdapter(Context context, int resource, List<T> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView text = (TextView) view.findViewById(android.R.id.text1);

            if (text != null && textSize > 0) {
                text.setTextSize(unit, textSize);
            }

            return view;
        }

        void setTextSize(int unit, float size) {
            this.unit = unit;
            this.textSize = size;
            this.notifyDataSetChanged();
        }
    }
}