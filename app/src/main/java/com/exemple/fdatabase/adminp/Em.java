package com.exemple.fdatabase.adminp;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.fdatabase.MainActivity;
import com.exemple.fdatabase.R;
import com.exemple.fdatabase.controler.MyDatabaseHalper;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Em#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Em extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Em() {
        // Required empty public constructor
    }
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;

    public static String generateCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            code.append(randomChar);
        }

        return code.toString();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Em.
     */
    // TODO: Rename and change types and number of parameters
    public static Em newInstance(String param1, String param2) {
        Em fragment = new Em();
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
        View view = inflater.inflate(R.layout.fragment_em, container, false);
        TextView textcode = view.findViewById(R.id.emcode);
        ImageView copierCode = view.findViewById(R.id.emcopy);
        Button generbtn = view.findViewById(R.id.emgener);
        textcode.setVisibility(View.INVISIBLE);
        copierCode.setVisibility(View.INVISIBLE);

        generbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String randomCode = generateCode();
                textcode.setText(randomCode);
                textcode.setVisibility(View.VISIBLE);
                copierCode.setVisibility(View.VISIBLE);
                MyDatabaseHalper db = new MyDatabaseHalper(getContext());
                db.insertPath(randomCode,"em");
                copierCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        copyToClipboard(requireContext(),randomCode);
                        Toast.makeText(getContext(), "Code copied to clipboard", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
    private void copyToClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Generated Code", text);
        clipboard.setPrimaryClip(clip);
    }
}