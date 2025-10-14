package com.example.hello_world;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MultiChoiceDialogActivity extends AppCompatActivity {

    private String[]  titleArr = new String[]{"唱","跳","RAP","蓝球"};

    private boolean[] checkedArr= new boolean[]{false,false,false,false} ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_multi_choice_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void showMultiChoiceDialog(View view) {
//        Toast.makeText(this, "点击 了", Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(this)
                .setTitle("爱好")
                .setMultiChoiceItems(titleArr, checkedArr, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedArr[which] = isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < checkedArr.length; i++) {
                            if (checkedArr[i]){
                                stringBuffer.append(titleArr[i]).append(" ");
                            }
                        }


                        Toast.makeText(MultiChoiceDialogActivity.this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消",null)
                .create().show();
    }
}