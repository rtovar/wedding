package com.tekro.invitation.ui.view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tekro.invitation.R;
import com.tekro.invitation.model.TRGuest;
import com.tekro.invitation.network.ContentProvider;
import com.tekro.invitation.ui.activity.MainActivity;

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

    public GuestView(Context context, View contentView) {
        super(context);

        rsvpButton = (ImageButton) contentView.findViewById(R.id.rsvpButton);
        menuButton = (Button) contentView.findViewById(R.id.menuButton);
        firstNameTextView = (TextView) contentView.findViewById(R.id.firstNameTextView);
        lastNameTextView = (TextView) contentView.findViewById(R.id.lastNameTextView);

        rsvpButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);

        Typeface fontLight = Typeface.createFromAsset(getContext().getAssets(), "HelveticaNeueLight.ttf");
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "HelveticaNeue.ttf");
        firstNameTextView.setTypeface(fontLight);
        lastNameTextView.setTypeface(font);
        menuButton.setTypeface(font);
    }

    public void setGuest(TRGuest guest){
        this.guest = guest;
        setupGuest();
    }


    //--------------------------
    // Listeners
    //--------------------------

    @Override
    public void onClick(View clickedview) {
        if (clickedview.equals(rsvpButton)) {
            guest.rsvp = !rsvpButton.isSelected();
            setupRSVPButton();
            ContentProvider.getInstance().updateGuest(guest);
        } else if (clickedview.equals(menuButton)) {
            ContentProvider.getInstance().currentGuest = guest;
            ((MainActivity) getContext()).pushMenuActivity();
        }
    }


    //--------------------------
    // User Interface
    //--------------------------

    private void setupGuest() {
        if (guest != null) {
            firstNameTextView.setText(guest.firstName);
            lastNameTextView.setText(guest.lastName);
            setupMenuButton();
            setupRSVPButton();
        }
    }

    private void setupMenuButton() {
        if (guest.menu == null) {
            menuButton.setText(R.string.menu_button);
        } else if (guest.menu.equals("other")) {
            menuButton.setText(guest.menuOther);
        } else {
            int menuResID = getContext().getResources().getIdentifier("menu_" + guest.menu, "string", getContext().getPackageName());
            String menuText = menuResID == 0 ? "" : (String) getContext().getResources().getText(menuResID);
            menuButton.setText(menuText);
        }
    }

    private void setupRSVPButton() {
        rsvpButton.setSelected(guest.rsvp);
        int rsvpButtonColor = getContext().getResources().getColor( guest.rsvp ? R.color.theme_purple : R.color.theme_purple_transparent);
        rsvpButton.setColorFilter(rsvpButtonColor, PorterDuff.Mode.SRC_IN);
    }
}
