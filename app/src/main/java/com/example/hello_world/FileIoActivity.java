package com.example.hello_world;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_file_io);

    }

    public void outer_write_data(View view) throws Exception {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            File path = Environment.getExternalStorageDirectory();
            File file = new File(path,"outerData.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("名为爱莉希雅的故事!".getBytes());

            fileOutputStream.close();
            Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();

        }


    }

    public void outer_read_data(View view) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            File path = Environment.getExternalStorageDirectory();

            try {
                File file = new File(path,"outerData.txt");
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[fileInputStream.available()];
                fileInputStream.read(bytes);
                Toast.makeText(this, bytes.toString(), Toast.LENGTH_SHORT).show();


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();

        }
    }
}