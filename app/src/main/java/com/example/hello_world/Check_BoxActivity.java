package com.example.hello_world;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Check_BoxActivity extends AppCompatActivity {
    private CheckBox cb1, cb2, cb3, cb4;
    private TextView tv1;
    private Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_check_box);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv1 = findViewById(R.id.cb_tv1);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);

        bt = findViewById(R.id.cb_bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> selectHobby = new ArrayList<>();
                if (cb1.isChecked()) {
//                    selectHobby.add(cb1.getText().toString());
                }
                if (cb2.isChecked()) {
//                    selectHobby.add(cb2.getText().toString());
                }
                if (cb3.isChecked()) {
//                    selectHobby.add(cb3.getText().toString());
                }
                if (cb4.isChecked()) {
//                    selectHobby.add(cb4.getText().toString());
                }
                if (selectHobby.isEmpty()) {
                    Toast.makeText(Check_BoxActivity.this, "请至少选择一个 爱好", Toast.LENGTH_SHORT).show();
                    return;
                }
//                tv1.setText("您的爱好是：" + selectHobby);
            }
        });


    }
}