package com.moonlight.pokerprophet.frags


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.moonlight.pokerprophet.R


class OmahaFragment : Fragment() {

    private lateinit var root: View
    private lateinit var cards: ArrayList<ImageView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_omaha, container, false)


        return root
    }

}// Required empty public constructor
