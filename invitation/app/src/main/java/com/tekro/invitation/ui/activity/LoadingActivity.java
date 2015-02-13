package com.tekro.invitation.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.tekro.invitation.R;
import com.tekro.invitation.adapter.TabPagerAdapter;
import com.tekro.invitation.model.TRInvitation;
import com.tekro.invitation.network.ContentProvider;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Pau on 1/2/2015.
 */
public class LoadingActivity extends Activity implements View.OnClickListener {

    //--------------------------
    // Attributes
    //--------------------------

    private Button buttonScan;
    private ProgressBar progressBar;
    private LinearLayout contentLayout;


    //--------------------------
    // FragmentActivity Methods
    //--------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        buttonScan = (Button)findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(this);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        contentLayout = (LinearLayout)findViewById(R.id.contentLayout);
    }


    //--------------------------
    // Navigation Methods
    //--------------------------

    private Callback<TRInvitation> callbackRequestInvitation(final Activity activity) {
        return new Callback<TRInvitation>() {
            @Override
            public void success(TRInvitation invitation, Response response) {
                ContentProvider.getInstance().currentInvitation = invitation;
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                contentLayout.setVisibility(View.VISIBLE);
                new AlertDialog.Builder(activity)
                        .setMessage(R.string.error_request_invitation_message)
                        .setPositiveButton(R.string.ok, null)
                        .create()
                        .show();
            }
        };
    }


    //--------------------------
    // Listeners Methods
    //--------------------------

    @Override
    public void onClick(View v) {
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(LoadingActivity.this, getString(R.string.qr_popup_title), getString(R.string.qr_popup_message), getString(R.string.qr_popup_yes_button), getString(R.string.qr_popup_no_button)).show();
        }
    }

    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String invitationID = intent.getStringExtra("SCAN_RESULT"); // This will contain your scan result
                contentLayout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                ContentProvider.getInstance().requestCurrentInvitation(invitationID, callbackRequestInvitation(this));
            }
        }
    }
}
