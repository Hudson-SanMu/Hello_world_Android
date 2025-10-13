package com.example.hello_world;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RadioButtonActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_radio_button);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radioGroup=findViewById(R.id.rd_1);
        textView=findViewById(R.id.selectCity);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String city="";
                if (checkedId==R.id.beijing){
                    city="北京";
                }else if (checkedId==R.id.shanghai){
                    city="上海";
                }else if (checkedId==R.id.guangzhou){
                    city="广州";
                }else if (checkedId==R.id.shenzhen){
                    city="深圳";
                }
                textView.setText(city);
            }

        });

    }

}