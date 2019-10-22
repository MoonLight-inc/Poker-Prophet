package com.moonlight.pokerprophet.frags;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.moonlight.pokerprophet.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {


    private Button omaha_btn;
    private Button holdem_btn;

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        omaha_btn = view.findViewById(R.id.omaha_btn);
        omaha_btn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_omahaFragment));
        omaha_btn.setClickable(false);  //TODO

        holdem_btn = view.findViewById(R.id.holdem_btn);
        holdem_btn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_holdemFragment));

        return view;
    }

}
