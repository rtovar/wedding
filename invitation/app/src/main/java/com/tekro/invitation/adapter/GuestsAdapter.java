package com.tekro.invitation.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tekro.invitation.R;
import com.tekro.invitation.model.TRGuest;
import com.tekro.invitation.ui.view.GuestView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pau on 8/2/2015.
 */
public class GuestsAdapter extends BaseAdapter implements View.OnClickListener {

    private ArrayList<TRGuest> items;
    private Context context;

    public GuestsAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return items != null ? items.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(ArrayList<TRGuest> guests) {
        if (items == null) {
            items = guests;
        } else {
            items.addAll(guests);
        }
    }

    public void clear() {
        items = null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.view_guest_item, null);

            ImageButton rsvpButton = (ImageButton) view.findViewById(R.id.rsvpButton);
            Button menuButton = (Button) view.findViewById(R.id.menuButton);
            TextView firstNameTextView = (TextView) view.findViewById(R.id.firstNameTextView);
            TextView lastNameTextView = (TextView) view.findViewById(R.id.lastNameTextView);

            rsvpButton.setOnClickListener(this);
            menuButton.setOnClickListener(this);

            Typeface font = Typeface.createFromAsset(context.getAssets(), "HelveticaNeueLight.ttf");
            firstNameTextView.setTypeface(font);
            lastNameTextView.setTypeface(font);
            menuButton.setTypeface(font);

            if (getItem(position) != null) {
                TRGuest guest = (TRGuest) getItem(position);
                firstNameTextView.setText(guest.firstName);
                lastNameTextView.setText(guest.lastName);
                int menuResID = context.getResources().getIdentifier("menu_" + guest.menu, "string", context.getPackageName());
                String menuText = menuResID == 0 ? "" : (String) context.getResources().getText(menuResID);
                menuButton.setText(menuText);
                rsvpButton.setSelected(guest.rsvp);
            }
        }

        return view;
    }


    @Override
    public void onClick(View clickedview) {
        if (clickedview instanceof Button) {
            //TODO: show menu activity
        } else if (clickedview instanceof ImageButton) {
            clickedview.setSelected(!clickedview.isSelected());
        }
    }
}
