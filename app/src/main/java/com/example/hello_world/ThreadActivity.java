package com.example.hello_world;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hello_world.utils.NetUtil;

public class ThreadActivity extends AppCompatActivity {
    private Button b1;
    private TextView txt1 ;

    private Handler mhandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what==0){
                String str =(String) msg.obj;
                txt1.setText(str);
                Toast.makeText(ThreadActivity.this,"主线程收到消息",Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thread);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        b1 = findViewById(R.id.threadButton);
        txt1 = findViewById(R.id.txt1);
    }


    public void start(View view) {
        new Thread(new Runnable(){

            @Override
            public void run() {
//                String stringFromNet = getStringFromNet();
                String stringFromNet = NetUtil.doGet();
                Message message = new Message();
                message.what=0;
                message.obj=stringFromNet;
                mhandler.sendMessage(message);

            }
        }).start();
    }


    String getStringFromNet(){
         String xxx = "";
        for (int i = 0; i < 100; i++) {
            xxx =xxx+"视图"+i;
        }
        try {
            System.out.println("5秒后执行的任务");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("主线程继续执行...");
        return xxx;
    }



}