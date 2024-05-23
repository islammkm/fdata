package com.exemple.fdatabase.controler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.fdatabase.ColieView;
import com.exemple.fdatabase.R;
import com.exemple.fdatabase.models.CMNDDetails;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;
import com.exemple.fdatabase.models.Fourniseur;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterCMND extends RecyclerView.Adapter<MyAdapterCMND.MyViewHolder> {
    List<Cmd> listcmd;
    Context context;

    List<Colie> listclient = new ArrayList<>();


    public MyAdapterCMND(List<Cmd> listcmd, Context context) {
        this.listcmd = listcmd;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewcmnd2,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(listcmd.get(position) != null) {
            holder.constraintLayoutC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ColieView.class);
                    intent.putExtra("id_cmnd", listcmd.get(position).getId());
                    context.startActivity(intent);
                }
            });
            MyDatabaseHalper db = new MyDatabaseHalper(context);
            if (listcmd.get(position).getEtat().equals("liv1")) {
                holder.imageView10.setImageResource(R.drawable.livz);
                holder.dateliv6.setVisibility(View.INVISIBLE);

            } else if (listcmd.get(position).getEtat().equals("liv2")) {
                holder.imageView10.setImageResource(R.drawable.liv2e);
            } else if (listcmd.get(position).getEtat().equals("liv3")){
                holder.imageView10.setImageResource(R.drawable.livliv);


            }else if(listcmd.get(position).getEtat().equals("livc")){
                holder.imageView10.setImageResource(R.drawable.livcity);
            }else{
                holder.imageView10.setImageResource(R.drawable.livf);
            }
//            int i = listcmd.get(position).getId()
//            Colie colie = db.getColiesByCMNDId(1).get(1);
//            listcmd.get(position).getId()
//            String idString = String.valueOf(listcmd.get(0).getId());
//            int i = Integer.valueOf(idString);
//            String address = db.getColiesByCMNDId(i).get(1).getLast_position();
//            holder.dateliv6.setText(db.getColiesByCMNDId(listcmd.get(position).getId()).get(0).getDate());
//            holder.datecmnd6.setText(listcmd.get(position).getDate());
//            holder.locclient6.setText(address);
            // Ensure you have at least one command in the list
            if (!listcmd.isEmpty()) {



                String idString = String.valueOf(listcmd.get(0).getId());
                int i = Integer.parseInt(idString);
                List<Colie> colies = db.getColiesByCMNDId(i);
                if (colies.size() > 1) {
                    String address = colies.get(1).getLast_position();
                    holder.dateliv6.setText("last update : "+colies.get(0).getDate());
                    // Now you can use the address as needed
                    System.out.println("Address: " + address);
                } else {
                    // Handle the case where there are fewer than 2 colies
                    System.out.println("Not enough colies found for command ID: " + i);
                }
            } else {
                // Handle the case where the list of commands is empty
                System.out.println("No commands found.");
            }

            CMNDDetails pls = db.getCMNDDetailsByCMNDId(listcmd.get(position).getId());
            if (pls != null) {
                holder.ordername.setText(pls.getNomcmnd());
                holder.locforn6.setText(pls.getCityf());
                holder.ordersite.setText(pls.getSite());
                holder.locclient6.setText(pls.getCityc());
                holder.datecmnd6.setText(pls.getDatecmnd());

            }
//            } else {
//                holder.ordername.setText("cityName");
//                holder.datecmnd6.setText("2024-04-02");
//                holder.dateliv6.setText("2024-04-05");
//                holder.locforn6.setText("Hamburg");
//                holder.locclient6.setText("Ain Taya");
//                holder.ordersite.setText("https://www.apple.com/");
//                holder.imageView10.setImageResource(R.drawable.path1);
//            }
        }else {
            holder.removnull.setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        return listcmd.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView coliebtn,ordername,datecmnd6,locforn6,dateliv6,locclient6,ordersite;
        ConstraintLayout constraintLayoutC;
        ImageView imageView10;
        CardView remocard ;
        ConstraintLayout removnull;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ordername = itemView.findViewById(R.id.ordername);
            datecmnd6 = itemView.findViewById(R.id.datecmnd6);
            dateliv6 = itemView.findViewById(R.id.dateliv6);
            locforn6 = itemView.findViewById(R.id.locforn6);
            locclient6 = itemView.findViewById(R.id.locclient6);
            constraintLayoutC = itemView.findViewById(R.id.constraintLayoutc6);
            ordersite = itemView.findViewById(R.id.ordersite);
            imageView10 = itemView.findViewById(R.id.imageView5);
            removnull = itemView.findViewById(R.id.removnull);
            remocard = itemView.findViewById(R.id.remocard);

        }
    }

}
