package com.tekro.invitation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tekro.invitation.R;
import com.tekro.invitation.adapter.GuestsAdapter;

/**
 * Created by Rocío on 15/02/2015.
 */
public class InfoFragment extends Fragment {


    //--------------------------
    // Attributes
    //--------------------------


    //--------------------------
    // Fragment Methods
    //--------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        return view;
    }
}
