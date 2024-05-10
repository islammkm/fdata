package com.exemple.fdatabase.controler;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.fdatabase.R;
import com.exemple.fdatabase.models.Vec;

import java.util.List;


public class MyAdapterVec extends RecyclerView.Adapter<MyAdapterVec.MyViewHolder> {
    List<Vec> listvec;
    Context context;
    TextView textView;
    AlertDialog dialog;

    public MyAdapterVec(List<Vec> listdipo, Context context, TextView textView, AlertDialog dialog) {
        this.listvec = listdipo;
        this.context = context;
        this.textView = textView;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewdipo,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titre.setText(listvec.get(position).getNom());
        holder.constraintLayoutC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(listvec.get(position).getNom());
                dialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listvec.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titre,textView4;
        ConstraintLayout constraintLayoutC;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.cmndnom2);
            constraintLayoutC = itemView.findViewById(R.id.constraintlayoutc2);     }
    }
}
