package com.exemple.fdatabase.controler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.fdatabase.ColieInf;
import com.exemple.fdatabase.ColieView;
import com.exemple.fdatabase.Fournisseur;
import com.exemple.fdatabase.R;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;
import com.exemple.fdatabase.models.Fourniseur;

import java.util.List;


public class MyAdapterColie extends RecyclerView.Adapter<MyAdapterColie.MyViewHolder> {
    List<Colie> listclient;
    Context context;

    public MyAdapterColie(List<Colie> listclient, Context context) {
        listclient.add(null);
        this.listclient = listclient;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapterColie.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewcolie,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (listclient.get(position) != null) {
            MyDatabaseHalper db = new MyDatabaseHalper(context);
            Fourniseur four = db.getFournisseurById(listclient.get(position).getFournisseurId());
            Cmd cmnd = db.getCmndByCmndId(listclient.get(position).getCmndId());
            holder.coliebtn.setText(String.valueOf(listclient.get(position).getId()));
//            holder.locclient.setText(cmnd.getEtat().toString());
            Bitmap bitmap = db.getPicById(listclient.get(position).getIdPath()); // Replace yourBitmap with your actual Bitmap object
            if(bitmap != null) {
                holder.imageView10.setImageBitmap(bitmap);
            }else {
            holder.imageView10.setImageResource(R.drawable.teeeeeeb);
            }
            String name = db.getPicnameById(listclient.get(position).getIdPath());
            if( !name.isEmpty() ){
                holder.textView18.setText(name);
            }else {
                holder.textView18.setText("eror");
            }
            if (cmnd.getEtat().equals("liv3")) {
                holder.locclient.setText("En route...");
            }else{
                holder.locclient.setText("Preparing...");
            }
            holder.dateliv.setText(listclient.get(position).getDate());
            holder.prixcolie.setText(cmnd.getCote() + ".00$");
            holder.constraintLayoutC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ColieInf.class);
                    intent.putExtra("id_colie", listclient.get(position).getId());
                    context.startActivity(intent);
                }
            });
        }else {
//            holder.imageView10.setImageResource(R.drawable.headphones);
//            holder.prixcolie.setText("99.00$");
//            holder.textView18.setText("Headphones");
//            holder.locclient.setText("Ain taya");
//            holder.coliebtn.setText("15550");
//            holder.dateliv.setText("17-05-2025");
            holder.removnull2.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listclient.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titre,date,etat,dateliv,locclient,prixcolie,textView18;
        TextView coliebtn;
        ImageView imageView10;
        ConstraintLayout removnull2;
        ConstraintLayout constraintLayoutC;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            coliebtn = itemView.findViewById(R.id.textView7);
            dateliv = itemView.findViewById(R.id.dateliv);
            locclient = itemView.findViewById(R.id.locclient);
            prixcolie = itemView.findViewById(R.id.prixcolie);
            imageView10 = itemView.findViewById(R.id.imageView10);
            textView18 = itemView.findViewById(R.id.textView18);
            removnull2 = itemView.findViewById(R.id.removnull2);

            constraintLayoutC = itemView.findViewById(R.id.constraintLayoutc4);
        }
    }
}
