package com.example.hello_world;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONStringer;

public class RecyclerListActivity extends AppCompatActivity {

    private RecyclerView listRv;

    private String[] names = {"小猫", "哈士奇", "小黄鸭"};

    private int[] icon = {R.drawable.cat, R.drawable.dog, R.drawable.duck};

    private String[] introduces = {"猫，属于猫科动物，分家猫、野猫，是全世界家庭中较为广泛的宠物", "哈士奇，贱，会拆家", "唐老鸭"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listRv = findViewById(R.id.recyclerView);

        listRv.setLayoutManager(new LinearLayoutManager(this));
        listRv.setAdapter(new MyAcdapter());

        //设置点击事件
        onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(int postion) {
                Toast.makeText(RecyclerListActivity.this, "点击了："+names[postion], Toast.LENGTH_SHORT).show();
            }
        };
    }
    //一定要设置 泛型
    class MyAcdapter extends RecyclerView.Adapter <MyAcdapter.ViewHolder>{


        @NonNull
        @Override
        public MyAcdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = View.inflate(RecyclerListActivity.this, R.layout.item_list, null);

            return new ViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAcdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            holder.iconIv.setImageResource(icon[position]);
            holder.nameTv.setText(names[position]);
            holder.introduceTv.setText(introduces[position]);

            // 添加点击事件

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });


        }

        @Override
        public int getItemCount() {
            return names.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView iconIv;
            TextView nameTv, introduceTv;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                iconIv = itemView.findViewById(R.id.iv);
                nameTv = itemView.findViewById(R.id.name);
                introduceTv = itemView.findViewById(R.id.introduce);
            }
        }
    }
    private OnItemClickListener onItemClickListener;
    //自定义点击事件
    interface OnItemClickListener {
        void onItemClick(int postion);


    }

}
