package com.example.hello_world;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SingleChoiceDialogActivity extends AppCompatActivity {

    private String[] titleArr = new String[]{"小号","默认","中号","大号","特大号"};
    private  int[] textSizeArr = new int[]{10,20,30,40,50};
    private int index=1;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_single_choice_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv=findViewById(R.id.single_tv);
    }


    public void showSingleChoiceDialog(View view) {
        new AlertDialog.Builder(this)
                .setTitle("选择字体大小")
                .setSingleChoiceItems(titleArr, index, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        index=which;
                    }
                })
                .setPositiveButton("确定 ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv.setTextSize(textSizeArr[index]);
                    }
                })
                .setNegativeButton("取消 ",null)
                .create().show();
    }
}