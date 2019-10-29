package com.moonlight.pokerprophet.frags;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.moonlight.pokerprophet.R;
import com.moonlight.pokerprophet.listener.SwipeListner;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {


    private Button omaha_btn;
    private Button holdem_btn;
    private CardView holdem_card;
    private CardView omaha_card;
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
        //omaha_btn.setClickable(false);  //TODO

        omaha_card = view.findViewById(R.id.omaha_card);

        holdem_card = view.findViewById(R.id.holdem_card);
        holdem_btn = view.findViewById(R.id.holdem_btn);
        //holdem_btn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_holdemFragment));


        view.setOnTouchListener(new SwipeListner(getContext()) {
            public void onSwipeRight() {
                Toast.makeText(getContext(), "HOLDEM", Toast.LENGTH_SHORT).show();
                holdem_btn.setPressed(true);
                //holdem_btn.animate().x(500).setDuration(500).start();
                omaha_card.animate().alpha(0).setDuration(200).start();
                //holdem_card.animate().scaleX(2).scaleY(2).alpha(1).setDuration(200).start();
                holdem_card.animate().scaleYBy(3).scaleXBy(3).alpha(0f).setDuration(400).start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Navigation.findNavController(view).navigate(R.id.action_startFragment_to_holdemFragment);
                    }
                }, 400);

            }

            public void onSwipeLeft() {
                Toast.makeText(getContext(), "OMAHA SOON", Toast.LENGTH_SHORT).show();
                omaha_btn.setPressed(true);
                //holdem_btn.animate().x(500).setDuration(500).start();
                holdem_card.animate().alpha(0).setDuration(200).start();
                //holdem_card.animate().scaleX(2).scaleY(2).alpha(1).setDuration(200).start();
                omaha_card.animate().scaleYBy(3).scaleXBy(3).alpha(0f).setDuration(400).start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Navigation.findNavController(view).navigate(R.id.startFragment);
                        Toast.makeText(getContext(), "COMING SOON", Toast.LENGTH_LONG).show();
                    }
                }, 400);
            }
        });

//        holdem_btn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                GestureDetector gestureDetector = new GestureDetector(getContext(),new CustomGestureListner());
//                //Toast.makeText(getContext(), "КАСАНИЕ", Toast.LENGTH_SHORT).show();
//                return gestureDetector.onTouchEvent(motionEvent);
//            }
//        });


        return view;
    }

//    class CustomGestureListner extends SimpleOnGestureListener {
//
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//             int SWIPE_MIN_DISTANCE = 12;
//             int SWIPE_MAX_OFF_PATH = 100;
//             int SWIPE_THRESHOLD_VELOCITY = 100;
//            Toast.makeText(getContext(), "FLING", Toast.LENGTH_SHORT).show();
//            try {
//                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
//                    Toast.makeText(getContext(), "?? Swipe", Toast.LENGTH_SHORT).show();
//                // right to left swipe
//                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                    Toast.makeText(getContext(), "Left Swipe", Toast.LENGTH_SHORT).show();
//                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                    Toast.makeText(getContext(), "Right Swipe", Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e) {
//                // nothing
//            }
//            return false;
//
//        }
//    }

}


