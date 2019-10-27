package com.moonlight.pokerprophet.frags;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.moonlight.pokerprophet.Card;
import com.moonlight.pokerprophet.CustomAdapter;
import com.moonlight.pokerprophet.DataUtil;
import com.moonlight.pokerprophet.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HoldemFragment extends Fragment {


    private MaterialCardView hand1, hand2, table1, table2, table3, table4, table5, clicked;
    private AlertDialog.Builder builder;
    private AlertDialog dial;
    private ImageButton reset;
    private ImageButton back;
    private TextView adCounter;

    public HoldemFragment() {
        // Required empty public constructor
    }

    //private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_holdem, container, false);

        hand1 = root.findViewById(R.id.hand1);
        hand2 = root.findViewById(R.id.hand2);
        table1 = root.findViewById(R.id.table1);
        table2 = root.findViewById(R.id.table2);
        table3 = root.findViewById(R.id.table3);
        table4 = root.findViewById(R.id.table4);
        table5 = root.findViewById(R.id.table5);

        back = root.findViewById(R.id.back_btn);

        reset = root.findViewById(R.id.reset_btn);
        adCounter = root.findViewById(R.id.adCounter);
        adCounter.setText(DataUtil.adCounter.toString());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(root.getContext());
                AlertDialog alertDialog = builder.setView(R.layout.alert_picker)
                        .show();
                alertDialog.getWindow()
                        .setBackgroundDrawable(null);
                initRecycler(alertDialog, (MaterialCardView) view);
            }
        };
        hand1.setOnClickListener(onClickListener);
        hand2.setOnClickListener(onClickListener);
        table1.setOnClickListener(onClickListener);
        table2.setOnClickListener(onClickListener);
        table3.setOnClickListener(onClickListener);
        table4.setOnClickListener(onClickListener);
        table5.setOnClickListener(onClickListener);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (DataUtil.adCounter == 1) {
                    DataUtil.adCounter = 5;
                    //TODO add AD
                } else
                    DataUtil.adCounter--;

                DataUtil.reset();
                getFragmentManager().beginTransaction()
                        .detach(HoldemFragment.this)
                        .attach(HoldemFragment.this)
                        .commit();
            }
        });

        return root;
    }

    private void initRecycler(AlertDialog alertDialog, MaterialCardView view) {
        RecyclerView recyclerSpades = alertDialog.findViewById(R.id.spades);
        RecyclerView recyclerDiamonds = alertDialog.findViewById(R.id.diamonds);
        RecyclerView recyclerHearts = alertDialog.findViewById(R.id.hearts);
        RecyclerView recyclerClubs = alertDialog.findViewById(R.id.clubs);


        recyclerSpades.setLayoutManager(getLayoutManager(alertDialog));
        recyclerDiamonds.setLayoutManager(getLayoutManager(alertDialog));
        recyclerHearts.setLayoutManager(getLayoutManager(alertDialog));
        recyclerClubs.setLayoutManager(getLayoutManager(alertDialog));

        List<Card> cardsC = DataUtil.getCards("c");
        List<Card> cardsH = DataUtil.getCards("h");
        List<Card> cardsS = DataUtil.getCards("s");
        List<Card> cardsD = DataUtil.getCards("d");

        recyclerClubs.setAdapter(new CustomAdapter(cardsC, alertDialog, view));
        recyclerDiamonds.setAdapter(new CustomAdapter(cardsD, alertDialog, view));
        recyclerHearts.setAdapter(new CustomAdapter(cardsH, alertDialog, view));
        recyclerSpades.setAdapter(new CustomAdapter(cardsS, alertDialog, view));

        new LinearSnapHelper().attachToRecyclerView(recyclerClubs);
        new LinearSnapHelper().attachToRecyclerView(recyclerDiamonds);
        new LinearSnapHelper().attachToRecyclerView(recyclerHearts);
        new LinearSnapHelper().attachToRecyclerView(recyclerSpades);

    }

    private RecyclerView.LayoutManager getLayoutManager(AlertDialog alertDialog) {
        RecyclerView.LayoutManager rvlm = new LinearLayoutManager(alertDialog.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvlm.scrollToPosition(Integer.MAX_VALUE / 2);
        return rvlm;
    }


}
