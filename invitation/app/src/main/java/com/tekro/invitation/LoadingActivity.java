package com.tekro.invitation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

import com.tekro.invitation.adapter.TabPagerAdapter;
import com.tekro.invitation.model.TRInvitation;
import com.tekro.invitation.network.ContentProvider;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Pau on 1/2/2015.
 */
public class LoadingActivity extends Activity {



    //--------------------------
    // FragmentActivity Methods
    //--------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ContentProvider.getInstance().requestCurrentInvitation("54ce5d7b362ac02776f6ffd8", callbackRequestInvitation(this));
    }


    //--------------------------
    // Navigation Methods
    //--------------------------

    private Callback<TRInvitation> callbackRequestInvitation(final Activity activity) {
        return new Callback<TRInvitation>() {
            @Override
            public void success(TRInvitation invitation, Response response) {
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                new AlertDialog.Builder(activity)
                        .setMessage(R.string.error_request_invitation_message)
                        .setPositiveButton(R.string.ok, null)
                        .create()
                        .show();
            }
        };
    }
}
