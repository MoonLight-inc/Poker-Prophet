package com.moonlight.pokerprophet.frags;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.moonlight.pokerprophet.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OmahaFragment extends Fragment {


    public OmahaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_omaha, container, false);
    }

}
