package com.example.smarthomedashboard.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.smarthomedashboard.R;

public class DiningRoomFragment extends Fragment {

    // Declare
    CardView dining_room_light, dining_room_air_conditioner;
    ConstraintLayout roomSetting, lightSetting, airConditionerSetting;
    ImageButton btn_light_back, btn_air_conditioner_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dining_room, container, false);


        // Match view
        dining_room_light = view.findViewById(R.id.dining_room_light);
        dining_room_air_conditioner = view.findViewById(R.id.dining_room_air_conditioner);

        roomSetting = view.findViewById(R.id.dining_room_setting_container);
        lightSetting = view.findViewById(R.id.dining_room_light_container);
        airConditionerSetting = view.findViewById(R.id.dining_room_air_conditioner_container);

        btn_light_back = view.findViewById(R.id.dining_room_btn_light_back);
        btn_air_conditioner_back = view.findViewById(R.id.dining_room_btn_air_conditioner_back);

        //Call
        setUpDiningRoomAirConditionerButton(view);
        setUpBtnAirConditionerBack(view);
        setUpBtnLightBack(view);
        setUpDiningRoomLightButton(view);

        return view;
    }

    private void setUpDiningRoomLightButton(View context) {
        dining_room_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomSetting.setVisibility(getView().INVISIBLE);
                lightSetting.setVisibility(getView().VISIBLE);
            }
        });
    }

    private void setUpBtnLightBack(View context){
        btn_light_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomSetting.setVisibility(getView().VISIBLE);
                lightSetting.setVisibility(getView().INVISIBLE);
            }
        });
    }

    private void setUpDiningRoomAirConditionerButton(View context) {
        dining_room_air_conditioner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomSetting.setVisibility(getView().INVISIBLE);
                airConditionerSetting.setVisibility(getView().VISIBLE);
            }
        });
    }

    private void setUpBtnAirConditionerBack(View context){
        btn_air_conditioner_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomSetting.setVisibility(getView().VISIBLE);
                airConditionerSetting.setVisibility(getView().INVISIBLE);
            }
        });
    }

}