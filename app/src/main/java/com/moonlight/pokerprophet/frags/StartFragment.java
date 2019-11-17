package com.moonlight.pokerprophet.frags;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moonlight.pokerprophet.R;


public class StartFragment extends Fragment {


    private MaterialButton omaha_btn;
    private MaterialButton holdem_btn;
    private MaterialCardView holdem_card;
    private MaterialCardView omaha_card;

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);


        final FloatingActionButton share_btn = view.findViewById(R.id.share_btn);
        final FloatingActionButton info_btn = view.findViewById(R.id.info_btn);

        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                AlertDialog alertDialog = builder.setView(R.layout.dialog_about)
                        .show();
                alertDialog.getWindow()
                        .setBackgroundDrawable(null);
            }
        });

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "ДелисЪ");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        omaha_btn = view.findViewById(R.id.omaha_btn);
        omaha_card = (MaterialCardView) omaha_btn.getParent();
        omaha_btn.setClickable(false);  //TODO

        holdem_btn = view.findViewById(R.id.holdem_btn);
        holdem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(container);
                omaha_card.setVisibility(View.GONE);
                holdem_btn.animate().alpha(0).setDuration(1000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Navigation.findNavController(view).navigate(R.id.action_startFragment_to_holdemFragment);
                    }
                }).start();
            }
        });
        omaha_btn.setClickable(false);
        //holdem_card.setClickable(false);

//        view.setOnTouchListener(new SwipeListner(getContext()) {
//            public void onSwipeRight() {
//                Toast.makeText(getContext(), "HOLDEM", Toast.LENGTH_SHORT).show();
//                TransitionManager.beginDelayedTransition(container);
//                omaha_card.setVisibility(View.GONE);
//                holdem_btn.animate().alpha(0).setDuration(1000).withEndAction(new Runnable() {
//                    @Override
//                    public void run() {
//                        Navigation.findNavController(view).navigate(R.id.action_startFragment_to_holdemFragment);
//                    }
//                }).start();
//
//            }
//
//            public void onSwipeLeft() {
//                Toast.makeText(getContext(), "OMAHA SOON", Toast.LENGTH_SHORT).show();
//                //omaha_btn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_omahaFragment));
//
//                holdem_card.animate().alpha(0).setDuration(200).start();
//                omaha_card.animate().scaleYBy(3).scaleXBy(3).alpha(0f).setDuration(400).start();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Navigation.findNavController(view).navigate(R.id.startFragment);
//                        Toast.makeText(getContext(), "COMING SOON", Toast.LENGTH_LONG).show();
//                    }
//                }, 400);
//            }
//        });

        return view;
    }

}


