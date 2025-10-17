package com.example.hello_world;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hello_world.databinding.ActivitySqliteBinding;

public class SqliteActivity extends AppCompatActivity {
    private ActivitySqliteBinding binding;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySqliteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MyHeler myHeler = new MyHeler(this);
        db = myHeler.getWritableDatabase();
    }

    public class MyHeler extends SQLiteOpenHelper {
        public MyHeler(@Nullable Context context) {
            super(context, "bitcat.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "create table user(id integer primary key autoincrement,name varchar(10),phone integer)";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    public void addUser(View view) {

        String name = binding.nameEt.getText().toString().trim();
        String age = binding.phoneEt.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age)){
            Toast.makeText(this, "姓名或者年龄为空", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",age);
        db.insert("user",null,values);
    }

    public void isUSer(View view){
        String name = binding.nameEt.getText().toString().trim();
        binding.contentTv.setText("");
        if (name.isEmpty()){
            findAll(view);
        }else {
            findUserByName(view);
        }
    }

    public void findAll(View view) {
        binding.contentTv.setText("");
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            binding.contentTv.append("\n" + "姓名："+cursor.getString(1) + "      电话："+cursor.getString(2));
        }
    }
    public void updateUser(View view) {
        String phone = binding.phoneEt.getText().toString().trim();
        String name = binding.nameEt.getText().toString().trim();
        ContentValues values = new ContentValues();
        values.put("phone",phone);

        db.update("user",values,"name=?",new String[]{name});
    }
    public void deleteUser(View view) {
        String name = binding.nameEt.getText().toString().trim();
        db.delete("user","name=?",new String[]{name});
        findAll(null);
    }
    //根据用户名查询用户
    public void findUserByName(View view) {
        String name = binding.nameEt.getText().toString().trim();
        Cursor cursor = db.query("user", null, "name=?", new String[]{name}, null, null, null);
        while (cursor.moveToNext()){
            binding.contentTv.setText("姓名："+cursor.getString(1) + "      电话："+cursor.getString(2));
        }
    }
}