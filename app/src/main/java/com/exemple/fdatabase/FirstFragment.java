package com.exemple.fdatabase;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exemple.fdatabase.controler.MyAdapterDipo;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Path> listdipo = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private boolean select=false;
    private RecyclerView.Adapter myAdapter;
    private String selectedItem;
    public FirstFragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Button btn = view.findViewById(R.id.buttonff);
        Switch switch1 = view.findViewById(R.id.switch1);
        Button btn3 = view.findViewById(R.id.btn3);
        TextView txt = view.findViewById(R.id.textView4);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View myview = getLayoutInflater().inflate(R.layout.activity_testrecycle, null);
                builder.setView(myview);
                final AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
                recyclerView = myview.findViewById(R.id.recycleview30);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                MyDatabaseHalper db = new MyDatabaseHalper(getContext());
                listdipo = db.getAllPaths();
                myAdapter = new MyAdapterDipo(listdipo,getContext(),txt,dialog);
                recyclerView.setAdapter(myAdapter);
                selectedItem = txt.getText().toString();
                select = true ;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , Scanner.class);
                intent.putExtra("select",select);
                intent.putExtra("selectitem",selectedItem);

                startActivity(intent);
            }
        });
        return view;
    }
}