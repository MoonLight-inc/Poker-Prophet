package com.moonlight.pokerprophet.frags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moonlight.pokerprophet.R;


public class RulesFragment extends Fragment {

    public RulesFragment() {
        // Required empty public constructor
    }


    View root;
    LinearLayout body, stage0, stage1, stage2, stage3;
    TextView txt;

    private FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_rules, container, false);

        fab = root.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).popBackStack();
            }
        });


        body = root.findViewById(R.id.body);
        body.setAlpha(0);
        //System.out.println(body.getChildCount()+);

        stage0 = (LinearLayout) body.getChildAt(1);
        ((TextView) stage0.getChildAt(0)).setText(R.string.stage0);
        ((ImageView) stage0.getChildAt(1)).setImageResource(R.drawable.question);
        ((TextView) stage0.getChildAt(2)).setText(R.string.stage0text);

        stage1 = (LinearLayout) body.getChildAt(2);
        ((TextView) stage1.getChildAt(0)).setText(R.string.stage1);
        ((ImageView) stage1.getChildAt(1)).setImageResource(R.drawable.question);
        ((TextView) stage1.getChildAt(2)).setText(R.string.stage1text);

        stage2 = (LinearLayout) body.getChildAt(3);
        ((TextView) stage2.getChildAt(0)).setText(R.string.stage2);
        ((ImageView) stage2.getChildAt(1)).setImageResource(R.drawable.question);
        ((TextView) stage2.getChildAt(2)).setText(R.string.stage2text);

        stage3 = (LinearLayout) body.getChildAt(4);
        ((TextView) stage3.getChildAt(0)).setText(R.string.stage3);
        ((ImageView) stage3.getChildAt(1)).setImageResource(R.drawable.question);
        ((TextView) stage3.getChildAt(2)).setText(R.string.stage3text);
        body.animate().alpha(1).setDuration(300).start();

        return root;
    }


}
