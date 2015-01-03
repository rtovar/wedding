package com.example.rocio.tekro.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rocio.tekro.R;

/**
 * Created by Rocío on 03/01/2015.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {


    // -------------------------
    // ATTRIBUTES
    // -------------------------

    private Button buttonPaperInvitation;
    private TextView textViewPaperInvitation;
    private ProgressBar progressBarPaperInvitation;
    private RelativeLayout layoutPaperInvitation;


    // -------------------------
    // BaseFragment METHODS
    // -------------------------

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_invitation;
    }

    @Override
    public void findViews(View rootView) {
        buttonPaperInvitation = (Button) rootView.findViewById(R.id.buttonGetPaperInvitation);
        textViewPaperInvitation = (TextView) rootView.findViewById(R.id.textViewPaperInvitation);
        progressBarPaperInvitation = (ProgressBar) rootView.findViewById(R.id.progressBarPaperInvitation);
        layoutPaperInvitation = (RelativeLayout) rootView.findViewById(R.id.layoutPaperInvitation);
    }

    @Override
    public void setListeners() {
        buttonPaperInvitation.setOnClickListener(this);
    }


    // -------------------------
    // LISTENERS METHODS
    // -------------------------

    @Override
    public void onClick(View clickedView) {
        if (clickedView.equals(buttonPaperInvitation)) {
            //TODO: send paper invitation request
        }
    }
}
