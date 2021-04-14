package com.example.illthinkaboutit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyHolder> {
    private static int currtitile;
    private int numbItems;
    Context context;
    ArrayList<ViewItem> items;
    static {
        currtitile=0;
    }

    public RvAdapter(int numbItems, ArrayList<ViewItem> items) {
        this.numbItems = numbItems;
        this.items=items;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        System.out.println("context"+context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view=layoutInflater.inflate((int)R.layout.rv_frame,parent,false);
        MyHolder holder=new MyHolder(view);
        holder.tv_title.setText("text:"+currtitile);
        currtitile++;
        return holder;

    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.star.setOnClickListener(view->{
            if (items.get(position).isStared()){
                items.get(position).setStared(false);
                items.get(position).setStars(items.get(position).getStars()-1);
            }
            else {
                items.get(position).setStared(true);
                items.get(position).setStars(items.get(position).getStars()+1);

            }
            holder.bind(position);
        });
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numbItems;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private TextView tv_title;
        private TextView tv_id;
        TextView stars;
        ImageView star;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_title= itemView.findViewById(R.id.tv_title);
            star= itemView.findViewById(R.id.button4);
            stars= itemView.findViewById(R.id.textView4);
            star.setColorFilter(Color.GRAY,PorterDuff.Mode.MULTIPLY);
        }
        @SuppressLint("ResourceAsColor")
        public void bind(int i){
            tv_id.setText(String.valueOf(i));
            tv_title.setText(items.get(i).getTitle());
            stars.setText(String.valueOf(items.get(i).getStars()));
            if (items.get(i).isStared())star.setColorFilter(Color.CYAN,PorterDuff.Mode.MULTIPLY);
            else star.setColorFilter(Color.GRAY,PorterDuff.Mode.MULTIPLY);
        }
        public String getId(){
        return tv_id.getText().toString();
        }
    }
}
