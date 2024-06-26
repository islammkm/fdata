package com.exemple.fdatabase.adminp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.exemple.fdatabase.R;
import com.exemple.fdatabase.controler.MyDatabaseHalper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Factory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Factory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Factory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Factory.
     */
    // TODO: Rename and change types and number of parameters
    public static Factory newInstance(String param1, String param2) {
        Factory fragment = new Factory();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_factory, container, false);
        TextView id = view.findViewById(R.id.factoryid);
        Button factoryadd = view.findViewById(R.id.factoryadd12);
        EditText name = view.findViewById(R.id.factoryname);
        EditText addr = view.findViewById(R.id.facrtoryaddress);
        MyDatabaseHalper db = new MyDatabaseHalper(getContext());

        factoryadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namee = String.valueOf(name.getText());
                String addrr = String.valueOf(addr.getText());
                if ( !namee.isEmpty() && !addrr.isEmpty()) {
                    long idf = db.insertFournisseur(namee, addrr);
                    id.setVisibility(View.VISIBLE);
                    id.setText("ID / "+idf);
                }
            }
        });
        return view;
    }
}