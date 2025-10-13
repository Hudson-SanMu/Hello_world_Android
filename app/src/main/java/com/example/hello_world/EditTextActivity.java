package com.example.hello_world;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditTextActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_text);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
    }
    public void login(View view) {

        String userName = username.getText().toString().trim();
        String passWord = password.getText().toString().trim();
        if (userName.length()==0){
            Toast.makeText(this, "用户名 不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passWord.length()==0){
            Toast.makeText(this, "密码 不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

    }
}