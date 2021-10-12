package com.example.smarthomedashboard.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smarthomedashboard.fragment.LivingRoom.LivingRoomLightFragment;
import com.example.smarthomedashboard.MainActivity;
import com.example.smarthomedashboard.R;
import com.example.smarthomedashboard.fragment.LivingRoom.LivingRoomAirConditionerFragment;

public class LivingRoomFragment extends Fragment {

    // Declare
    CardView living_room_light;
    CardView living_room_air_conditioner;

    public LivingRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_living_room, container, false);

        // Match view
        living_room_light = view.findViewById(R.id.living_room_light);
        living_room_air_conditioner = view.findViewById(R.id.living_room_air_conditioner);



        //Call
        setUpLivingRoomLightButton(view);
        setUpLivingRoomAirConditionerButton(view);


        return view;
    }

    private void setUpLivingRoomLightButton(View context) {
        living_room_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivingRoomLightFragment lightFragment = new LivingRoomLightFragment();
//                getParentFragmentManager().beginTransaction().replace(R.id.living_room_setting_container, lightFragment).addToBackStack(null).commit();
//                Toast.makeText(getActivity(), "This is my Toast message!", Toast.LENGTH_LONG).show();
                Log.d("click", "onClick: 1");
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack("lightFragment");
                ft.replace(R.id.living_room_setting_container, lightFragment);
                ft.commit();
            }
        });
    }

    private void setUpLivingRoomAirConditionerButton(View context) {
        living_room_air_conditioner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivingRoomAirConditionerFragment airConditionerFragment = new LivingRoomAirConditionerFragment();
//                getParentFragmentManager().beginTransaction().replace(R.id.living_room_setting_container, lightFragment).addToBackStack(null).commit();
//                Toast.makeText(getActivity(), "This is my Toast message!", Toast.LENGTH_LONG).show();
                Log.d("click", "onClick: 1");
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack("airConditionerFragment");
                ft.replace(R.id.living_room_setting_container, airConditionerFragment);
                ft.commit();
            }
        });
    }


}