package com.example.assignment.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.assignment.R;
import com.example.assignment.helpers.API;

import java.util.ArrayList;

public class DocTruyenActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private NoiDungTruyenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);

        Intent intent = getIntent();
        ArrayList<String> list = intent.getStringArrayListExtra("anhnoidungtruyen");

        recyclerView = findViewById(R.id.noidungtruyenRecyclerView);
        adapter = new NoiDungTruyenAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

class NoiDungTruyenAdapter extends RecyclerView.Adapter<NoiDungTruyenAdapter.MyViewHolder> {
    private ArrayList<String> list;
    String url = API.URL_IP;

    private Context context;

    public NoiDungTruyenAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.noidungtruyen_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String item = list.get(position);
        Log.i("zzzz", item);
        Glide.with(context).load("http://" + url + ":3000"+item).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.anhnoidungtruyenImage);
        }
    }
}
