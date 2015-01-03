package com.example.rocio.tekro.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rocio.tekro.R;

/**
 * Created by Rocío on 03/01/2015.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, DialogInterface.OnClickListener {


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
            showDialogInvitationLanguage();}
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int buttonID) {
        String invitationLang;
        switch (buttonID) {
            case DialogInterface.BUTTON_POSITIVE:
                invitationLang = "cat";
                break;
            case DialogInterface.BUTTON_NEGATIVE:
            default:
                invitationLang = "es";
                break;
        }

        //TODO: send paper invitation request

    }


    // -------------------------
    // PRIVATE METHODS
    // -------------------------

    private void showDialogInvitationLanguage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.home_dialog_choose_lang_message);
        builder.setPositiveButton(R.string.home_dialog_choose_lang_cat, this);
        builder.setNegativeButton(R.string.home_dialog_choose_lang_es, this);
        builder.show();
    }
}
