package com.moonlight.pokerprophet.frags;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.google.android.material.card.MaterialCardView;
import com.moonlight.pokerprophet.Card;
import com.moonlight.pokerprophet.CustomAdapter;
import com.moonlight.pokerprophet.DataUtil;
import com.moonlight.pokerprophet.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HoldemFragment extends Fragment {


    //private MaterialCardView hand1, hand2, table1, table2, table3, table4, table5, clicked;
    private AlertDialog.Builder builder;
    private AlertDialog dial;
    private ImageButton reset;
    private ImageButton back;
    private TextView adCounter, adviceTxt;
    private Drawable bg;
    private Handler delayRun = new Handler();


    private MaterialCardView card1, card2, card3;
    private ArrayList<MaterialCardView> cards = new ArrayList<>();

    public HoldemFragment() {
        // Required empty public constructor
    }

    //private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_holdem, container, false);
        System.out.println("onCreateView, АЛО");
        cards.add(root.findViewById(R.id.hand1));
        cards.add(root.findViewById(R.id.hand2));
        cards.add(root.findViewById(R.id.table1));
        cards.add(root.findViewById(R.id.table2));
        cards.add(root.findViewById(R.id.table3));
        cards.add(root.findViewById(R.id.table4));
        cards.add(root.findViewById(R.id.table5));
        card1 = root.findViewById(R.id.card1);
        card2 = root.findViewById(R.id.card2);
        card3 = root.findViewById(R.id.card3);
        adviceTxt = root.findViewById(R.id.textView);
        cards.get(0).getCardBackgroundColor();
        TransitionManager.beginDelayedTransition(container);

//        back = root.findViewById(R.id.back_btn);
//
//        reset = root.findViewById(R.id.reset_btn);
        //adCounter = root.findViewById(R.id.adCounter);
        //adCounter.setText(DataUtil.adCounter.toString());

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().onBackPressed();
//            }
//        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataUtil.ranks_c = new ArrayList<>(DataUtil.ranks);
                DataUtil.ranks_h = new ArrayList<>(DataUtil.ranks);
                DataUtil.ranks_s = new ArrayList<>(DataUtil.ranks);
                DataUtil.ranks_d = new ArrayList<>(DataUtil.ranks);
                DataUtil.cards_curr.clear();
                cards.forEach(c -> {
                    String tag = (String) c.getTag();
                    if (tag != null)
                        switch ("" + tag.charAt(tag.length() - 1)) {
                            case "c":
                                DataUtil.ranks_c.remove(tag.substring(0, tag.length() - 1));
                                DataUtil.cards_curr.add(new Card("c", tag.substring(0, tag.length() - 1)));
                                break;
                            case "h":
                                DataUtil.ranks_h.remove(tag.substring(0, tag.length() - 1));
                                DataUtil.cards_curr.add(new Card("h", tag.substring(0, tag.length() - 1)));
                                break;
                            case "s":
                                DataUtil.ranks_s.remove(tag.substring(0, tag.length() - 1));
                                DataUtil.cards_curr.add(new Card("s", tag.substring(0, tag.length() - 1)));
                                break;
                            case "d":
                                DataUtil.ranks_d.remove(tag.substring(0, tag.length() - 1));
                                DataUtil.cards_curr.add(new Card("d", tag.substring(0, tag.length() - 1)));
                                break;
                        }
                });

                builder = new AlertDialog.Builder(root.getContext());
                AlertDialog alertDialog = builder.setView(R.layout.alert_picker)
                        .show();
                alertDialog.getWindow()
                        .setBackgroundDrawable(null);

                //alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if ((cards.get(0).getTag() != null) && (cards.get(1).getTag() != null)) {
//                            cards.subList(2, 7).forEach((c) -> {
//                                c.setClickable(true);
//                                c.setBackgroundColor (0x50FFFFFF);
//                            });
                            TransitionManager.beginDelayedTransition(container);
                            card2.setVisibility(View.VISIBLE);
                            delayRun.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    adviceTxt.setText("Я ХЗ, ДУМАЙ САМ");
                                }
                            }, 500);
                            delayRun.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    TransitionManager.beginDelayedTransition(container);
                                    card1.setVisibility(View.VISIBLE);
                                }
                            }, 2000);

                        }
                    }
                });

                initRecycler(alertDialog, (MaterialCardView) view);
            }
        };

        cards.forEach(c -> c.setOnClickListener(onClickListener));
//        cards.subList(2, 7).forEach((c) -> {
//            c.setClickable(false);
//            c.setBackgroundColor(0x10FFFFFF);
//        });


//        reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (DataUtil.adCounter == 1) {
//                    DataUtil.adCounter = 5;
//                    //TODO add AD
//                } else
//                    DataUtil.adCounter--;
//                DataUtil.reset();
//                cards.clear();
//                getFragmentManager().beginTransaction()
//                        .detach(HoldemFragment.this)
//                        .attach(HoldemFragment.this)
//                        .commit();
//            }
//        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        card3.setVisibility(View.VISIBLE);
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
