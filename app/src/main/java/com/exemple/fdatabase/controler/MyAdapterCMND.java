package com.exemple.fdatabase.controler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.fdatabase.ColieView;
import com.exemple.fdatabase.R;
import com.exemple.fdatabase.Signup;
import com.exemple.fdatabase.models.Cmd;

import java.util.List;

public class MyAdapterCMND extends RecyclerView.Adapter<MyAdapterCMND.MyViewHolder> {
    List<Cmd> listcmd;
    Context context;

    public MyAdapterCMND(List<Cmd> listcmd, Context context) {
        this.listcmd = listcmd;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclercmnd,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titre.setText(listcmd.get(position).getAdresse());
        holder.etat.setText(listcmd.get(position).getEtat());
        holder.date.setText(String.valueOf(listcmd.get(position).getCote()));
        holder.constraintLayoutC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, ColieView.class);
                intent.putExtra("id_cmnd",listcmd.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listcmd.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titre,date,etat;
        ConstraintLayout constraintLayoutC;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.cmndnom);
            date = itemView.findViewById(R.id.cmnddate);
            etat = itemView.findViewById(R.id.cmndetat);
            constraintLayoutC = itemView.findViewById(R.id.constraintLayoutc);     }
    }
}
