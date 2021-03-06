package com.example.oose.routemaker.CreateTrip;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.oose.routemaker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment for day buttons in EditScheduleActivity.
 */
public class EditScheduleDayFragment extends Fragment implements View.OnClickListener {

    /** Buttons from day1 - day 7. */
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;

    /** list containing buttons */
    List<Button> day_buttons = new ArrayList<>();

    /** the selected button */
    Button selected_button;

    /** Communicator to send request to EditScheduleActivity */
    EditCommunicator comm;

    /** the number of days of the trip */
    int diff = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day_schedule, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (EditCommunicator) getActivity();
        diff = (int) comm.getDiff();
        button_1 = (Button) getActivity().findViewById(R.id.edit_day_1_button);
        button_1.setOnClickListener(this);
        button_2 = (Button) getActivity().findViewById(R.id.edit_day_2_button);
        button_2.setOnClickListener(this);
        button_3 = (Button) getActivity().findViewById(R.id.edit_day_3_button);
        button_3.setOnClickListener(this);
        button_4 = (Button) getActivity().findViewById(R.id.edit_day_4_button);
        button_4.setOnClickListener(this);
        button_5 = (Button) getActivity().findViewById(R.id.edit_day_5_button);
        button_5.setOnClickListener(this);
        button_6 = (Button) getActivity().findViewById(R.id.edit_day_6_button);
        button_6.setOnClickListener(this);
        button_7 = (Button) getActivity().findViewById(R.id.edit_day_7_button);
        button_7.setOnClickListener(this);

        selected_button = button_1;

        day_buttons.add(button_1);
        day_buttons.add(button_2);
        day_buttons.add(button_3);
        day_buttons.add(button_4);
        day_buttons.add(button_5);
        day_buttons.add(button_6);
        day_buttons.add(button_7);

        // removes view according to number of days in the trip
        for (int i = 6; i > diff - 1; i--) {
            day_buttons.get(i).setVisibility(View.GONE);
            day_buttons.remove(i);
        }

    }

    /**
     * Action when button is clicked.
     * @param v the view of the button
     */
    @Override
    public void onClick(View v) {
        int day_id = 1;
        if (!(v.isSelected())) {
            setAllOthersFalse();
            v.setSelected(true);
            day_id = day_buttons.indexOf(v) + 1;
            comm.resetTransport(day_id);
        }
    }

    /**
     * Set all other day buttons as not selected
     */
    private void setAllOthersFalse() {
        for (Button b : day_buttons) {
            b.setSelected(false);
        }
    }
}
