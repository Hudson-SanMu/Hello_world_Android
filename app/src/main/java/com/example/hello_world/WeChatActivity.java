package com.example.hello_world;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hello_world.fragment.AddressBookFragment;
import com.example.hello_world.fragment.DiscoveryFragment;
import com.example.hello_world.fragment.HomeFragment;
import com.example.hello_world.fragment.MeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class WeChatActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_we_chat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new AddressBookFragment());
        list.add(new DiscoveryFragment());
        list.add(new MeFragment());
        bottomNavigationView = findViewById(R.id.bottom_bar);

        showFragment(list.get(0));

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.menu_home){
                    showFragment(list.get(0));
                }else if (itemId == R.id.menu_address_book){
                    showFragment(list.get(1));
                }else if (itemId == R.id.menu_discovery){
                    showFragment(list.get(2));
                } else if (itemId == R.id.menu_me) {
                    showFragment(list.get(3));
                }
                return true;
            }
        });

    }

    private void showFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
}