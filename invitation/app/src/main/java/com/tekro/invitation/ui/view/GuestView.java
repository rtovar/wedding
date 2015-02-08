package com.tekro.invitation.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tekro.invitation.R;
import com.tekro.invitation.model.TRGuest;

/**
 * Created by Pau on 8/2/2015.
 */
public class GuestView extends View implements View.OnClickListener {


    //--------------------------
    // Attributes
    //--------------------------

    private TRGuest guest;
    private ImageButton rsvpButton;
    private Button menuButton;
    private TextView firstNameTextView;
    private TextView lastNameTextView;


    //--------------------------
    // Constructor
    //--------------------------

    public GuestView(Context context, TRGuest guest) {
        super(context);

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View contentView = layoutInflater.inflate(R.layout.view_guest_item, null);
        rsvpButton = (ImageButton) contentView.findViewById(R.id.rsvpButton);
        menuButton = (Button) contentView.findViewById(R.id.menuButton);
        firstNameTextView = (TextView) contentView.findViewById(R.id.firstNameTextView);
        lastNameTextView = (TextView) contentView.findViewById(R.id.lastNameTextView);

        rsvpButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);

        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "HelveticaNeueLight.ttf");
        firstNameTextView.setTypeface(font);
        lastNameTextView.setTypeface(font);
        menuButton.setTypeface(font);

        this.guest = guest;
        setupGuest();
    }


    //--------------------------
    // Listeners
    //--------------------------

    @Override
    public void onClick(View clickedview) {
        if (clickedview.equals(rsvpButton)) {
            rsvpButton.setSelected(!rsvpButton.isSelected());
            //TODO: send invitation modification
        } else if (clickedview.equals(menuButton)) {
            //TODO: show menu activity
        }
    }


    //--------------------------
    // User Interface
    //--------------------------

    private void setupGuest() {
        if (guest != null) {
            firstNameTextView.setText(guest.firstName);
            lastNameTextView.setText(guest.lastName);
            int menuResID = getResources().getIdentifier("menu_" + guest.menu, "string", getContext().getPackageName());
            String menuText = menuResID == 0 ? "" : (String) getResources().getText(menuResID);
            menuButton.setText(menuText);
            rsvpButton.setSelected(guest.rsvp);
        }
    }
}
