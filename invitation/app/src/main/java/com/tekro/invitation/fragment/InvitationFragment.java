package com.tekro.invitation.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ProgressBar;

import com.tekro.invitation.MainActivity;
import com.tekro.invitation.R;
import com.tekro.invitation.model.TRInvitation;
import com.tekro.invitation.network.ContentProvider;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Rocío on 26/01/2015.
 */
public class InvitationFragment extends Fragment implements View.OnClickListener, DialogInterface.OnClickListener {


    //--------------------------
    // Attributes
    //--------------------------

    private Button invitationButton;
    private Button paperInvitationButton;
    private ProgressBar progressBar;


    //--------------------------
    // Fragment Methods
    //--------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_invitation, container, false);

        findViews(view);
        setListeners();
        setupPaperInvitationButton();

        return view;
    }


    //--------------------------
    // USer Interface
    //--------------------------

    private void findViews(View contentView) {
        invitationButton = (Button) contentView.findViewById(R.id.buttonInvitation);
        paperInvitationButton = (Button) contentView.findViewById(R.id.buttonPaperInvitation);
        progressBar = (ProgressBar) contentView.findViewById(R.id.progressBar);
    }

    private void setListeners() {
        invitationButton.setOnClickListener(this);
        paperInvitationButton.setOnClickListener(this);
    }

    private void setupPaperInvitationButton() {
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "HelveticaNeueLight.ttf");
        paperInvitationButton.setTypeface(font);

        if (ContentProvider.getInstance().getCurrentInvitation() != null && ContentProvider.getInstance().getCurrentInvitation().sendInvitation) {
            paperInvitationButton.setEnabled(false);
            paperInvitationButton.setBackgroundColor(Color.TRANSPARENT);
            paperInvitationButton.setTextColor(getActivity().getResources().getColor(R.color.theme_purple));
            paperInvitationButton.setText(R.string.button_paper_invitation_disabled);
        } else {
            paperInvitationButton.setText(R.string.button_paper_invitation_enabled);
        }
    }


    //--------------------------
    // Listeners Methods
    //--------------------------

    @Override
    public void onClick(View clickedView) {
        if (clickedView.equals(invitationButton)) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.pushPhotoActivity();
        } else if (clickedView.equals(paperInvitationButton)) {
            showDialogChooseLanguage(this);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        paperInvitationButton.setText("");
        progressBar.setVisibility(View.VISIBLE);

        ContentProvider.getInstance().askForPaperInvitationWithLanguage(which == DialogInterface.BUTTON_POSITIVE ? "CAT" : "ES", callbackPaperInvitation());
    }


    //--------------------------
    // Dialogs Methods
    //--------------------------

    private void showDialogChooseLanguage(DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(R.string.dialog_lang_message)
                .setPositiveButton(R.string.dialog_lang_cat, listener)
                .setNegativeButton(R.string.dialog_lang_cast, listener)
                .create()
                .show();
    }


    //--------------------------
    // Network Methods
    //--------------------------

    private Callback<TRInvitation> callbackPaperInvitation() {
        return new Callback<TRInvitation>() {
            @Override
            public void success(TRInvitation invitation, Response response) {
                onPaperInvitationCallFinished();
            }

            @Override
            public void failure(RetrofitError error) {
                onPaperInvitationCallFinished();
                new AlertDialog.Builder(getActivity())
                        .setMessage(R.string.error_request_paper_invitation)
                        .setPositiveButton(R.string.ok, null)
                        .create()
                        .show();
            }
        };
    }

    private void onPaperInvitationCallFinished() {
        progressBar.setVisibility(View.GONE);
        setupPaperInvitationButton();
    }
}
