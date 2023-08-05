package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import com.example.assignment.Adapter.UsersAdapter;
import com.example.assignment.Fragment.TruyenTranhFragment;
import com.example.assignment.Fragment.UsersFragment;
import com.example.assignment.helpers.API;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {
    String url = API.BASE_URL_API;
    private String BASE_URL = url;

    private RecyclerView usersRecyclerView;// hiển thị lên recyclerview
    private UsersAdapter usersAdapter;

    //bottombar
    SmoothBottomBar smoothBottomBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smoothBottomBar = findViewById(R.id.bottombar);
        toolbar = findViewById(R.id.toolbar);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, new TruyenTranhFragment());
        fragmentTransaction.commit();
        //=================

        Intent intent = getIntent();
        String username = intent.getStringExtra("mUserName");

        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                if (i == 0) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout, new TruyenTranhFragment());
                    fragmentTransaction.commit();

                }
                if (i == 1) {


                    Bundle bundle = new Bundle();
                    bundle.putString("mUserName", username);
                    UsersFragment fragment = new UsersFragment();
                    fragment.setArguments(bundle);

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout,fragment);
                    fragmentTransaction.commit();


                }
                return false;
            }
        });
    }
}