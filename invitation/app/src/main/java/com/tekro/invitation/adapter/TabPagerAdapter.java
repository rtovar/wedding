package com.tekro.invitation.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tekro.invitation.R;
import com.tekro.invitation.fragment.InvitationFragment;

/**
 * Created by Rocío on 26/01/2015.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    //--------------------------------
    // Attributes
    //--------------------------------

    private Context mContext;
    private int[] tabsTitles = { R.string.title_section_invitation, R.string.title_section_guests, R.string.title_section_emplacement };
    private InvitationFragment invitationFragment;
    private Fragment guestsFragment;
    private Fragment infoFragment;


    //--------------------------------
    // FragmentPagerAdapter Methods
    //--------------------------------

    public TabPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }


    //--------------------------------
    // FragmentPagerAdapter Methods
    //--------------------------------

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return getInvitationFragment();
            case 1:
                return getGuestsFragment();
            case 2:
                return getInfoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(tabsTitles[position]);
    }


    //--------------------------------
    // Private Methods
    //--------------------------------

    private Fragment getInvitationFragment() {
        if (invitationFragment == null) {
            invitationFragment = new InvitationFragment();
        }

        return invitationFragment;
    }

    private Fragment getGuestsFragment() {
        if (guestsFragment == null) {
            guestsFragment = new InvitationFragment();
        }

        return guestsFragment;
    }

    private Fragment getInfoFragment() {
        if (infoFragment == null) {
            infoFragment = new InvitationFragment();
        }

        return infoFragment;
    }

}
