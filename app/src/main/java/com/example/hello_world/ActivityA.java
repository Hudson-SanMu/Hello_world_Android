package com.example.hello_world;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //resultCode 和 requestCode 不要混淆
        if (resultCode == RESULT_OK && requestCode ==100){
            String stringExtra = data.getStringExtra("result");
            Toast.makeText(this, stringExtra, Toast.LENGTH_SHORT).show();
        }

    }

    public void toB(View view) {
        Intent intent = new Intent(this,ActivityB.class);
        Bundle bundle = new Bundle();
        bundle.putString("info","这是AAA");
        bundle.putInt("age",22);
        intent.putExtras(bundle);
        //requestCode 不要写错
        startActivityForResult(intent,100);
    }
}