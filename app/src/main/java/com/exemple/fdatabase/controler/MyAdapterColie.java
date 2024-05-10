package com.exemple.fdatabase.controler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.fdatabase.ColieInf;
import com.exemple.fdatabase.ColieView;
import com.exemple.fdatabase.R;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;

import java.util.List;


public class MyAdapterColie extends RecyclerView.Adapter<MyAdapterColie.MyViewHolder> {
    List<Colie> listclient;
    Context context;

    public MyAdapterColie(List<Colie> listclient, Context context) {
        this.listclient = listclient;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapterColie.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclercmnd,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titre.setText(listclient.get(position).getCode());
        holder.etat.setText(listclient.get(position).getDate());
        holder.date.setText(String.valueOf(listclient.get(position).getLast_position()));
        holder.constraintLayoutC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, ColieInf.class);
                intent.putExtra("id_colie",listclient.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listclient.size();
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
