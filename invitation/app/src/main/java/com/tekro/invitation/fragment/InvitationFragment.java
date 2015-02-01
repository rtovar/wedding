package com.tekro.invitation.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tekro.invitation.MainActivity;
import com.tekro.invitation.R;

/**
 * Created by Rocío on 26/01/2015.
 */
public class InvitationFragment extends Fragment implements View.OnClickListener {


    //--------------------------
    // Attributes
    //--------------------------

    private Button invitationButton;


    //--------------------------
    // Fragment Methods
    //--------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_invitation, container, false);

        invitationButton = (Button) view.findViewById(R.id.buttonInvitation);
        invitationButton.setOnClickListener(this);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "HelveticaNeueLight.ttf");
        invitationButton.setTypeface(font);

        return view;
    }


    //--------------------------
    // Listeners Methods
    //--------------------------

    @Override
    public void onClick(View clickedView) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.pushPhotoActivity();
    }
}
