package com.example.assignment.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.assignment.Models.Truyen;
import com.example.assignment.Models.User;
import com.example.assignment.R;
import com.example.assignment.activity.DocTruyenActivity;
import com.example.assignment.helpers.API;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.ViewHolder> {
    Context context;
    private List<Truyen> truyen;
    String url = API.URL_IP;

    public TruyenAdapter(List<Truyen> truyen, Context context) {
        this.truyen = truyen;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.truyen_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Truyen truyens = truyen.get(position);
        holder.tentruyenTextView.setText(truyens.getTentruyen());


        Glide.with(holder.itemView.getContext())
                .load("http://" + url + ":3000/" + truyens.getAnhbia())
                .into(holder.anhbiaImageView);

        holder.linearlayoutTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zzzz", "đã ấn vào truyện: " + truyens.getTentruyen());

                openDialogChiTietTruyen(truyens);
            }
        });


    }

    @Override
    public int getItemCount() {
        return truyen.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tentruyenTextView;

        public ImageView anhbiaImageView;

        public LinearLayout linearlayoutTruyen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tentruyenTextView = itemView.findViewById(R.id.tentruyenTextView);
            anhbiaImageView = itemView.findViewById(R.id.anhbiaImageView);
            linearlayoutTruyen = itemView.findViewById(R.id.linearlayoutTruyen);


        }

    }

    public void openDialogChiTietTruyen(Truyen truyen) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogchitiettruyen, null);
        builder.setView(view);

        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bo_goc_dialog);
        dialog.show();

        TextView tentruyencctTextView, tentacgiacctTextView,
                namxuatbancctTextView, motacctTextView;
        ImageView anhcctImageView;
        Button dimissButton, doctruyenButton;

        tentruyencctTextView = view.findViewById(R.id.tentruyencctTextView);
        tentacgiacctTextView = view.findViewById(R.id.tentacgiacctTextView);
        namxuatbancctTextView = view.findViewById(R.id.namxuatbancctTextView);
        motacctTextView = view.findViewById(R.id.motacctTextView);
        anhcctImageView = view.findViewById(R.id.anhcctImageView);
        dimissButton = view.findViewById(R.id.dimissButton);
        doctruyenButton = view.findViewById(R.id.doctruyenButton);

        tentruyencctTextView.setText(truyen.getTentruyen());
        tentacgiacctTextView.setText("Tên tác giả: " + truyen.getTentacgia());
        namxuatbancctTextView.setText("Năm xuất bản: " + truyen.getNamxuatban());
        motacctTextView.setText("Mô tả: " + truyen.getMotangan());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(context)
                .load("http://" + url + ":3000/" + truyen.getAnhbia())
                .apply(options)
                .into(anhcctImageView);


        doctruyenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zzzz", "đọc truyện: " + truyen.get_id());

                ArrayList<String> list = new ArrayList<>();
                list.addAll(truyen.getAnhnoidungtruyen());

                Intent intent = new Intent(context, DocTruyenActivity.class);
                intent.putStringArrayListExtra("anhnoidungtruyen", list);
                context.startActivity(intent);
                dialog.dismiss();



            }
        });


        dimissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }
}
