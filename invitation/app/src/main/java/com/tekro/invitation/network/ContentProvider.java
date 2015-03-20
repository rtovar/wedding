package com.tekro.invitation.network;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tekro.invitation.R;
import com.tekro.invitation.helpers.InterfaceHelper;
import com.tekro.invitation.model.TRGuest;
import com.tekro.invitation.model.TRInvitation;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by Pau on 1/2/2015.
 */
public class ContentProvider {

    private static String SERVER = "http://mostrolabs.com:8090";
    private static String MY_PREFS_NAME = "TEKRO_WEDDING";

    private static ContentProvider instance;
    private APIService apiService;
    private String currentInvitationID;
    public TRInvitation currentInvitation;
    public TRGuest currentGuest;
    public static Context context;
    public OnUpdateFinishedListener onUpdateFinishedListener;

    private ContentProvider() {

        Gson gson = new GsonBuilder().create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(SERVER)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(APIService.class);
    }

    public static ContentProvider getInstance() {

        if (instance == null) {
            synchronized (ContentProvider.class) {
                // Double check
                if (instance == null) {
                    instance = new ContentProvider();
                }
            }
        }

        return instance;
    }


    //--------------------------
    // Data Methods
    //--------------------------

    public ArrayList<TRGuest> getGuests() {
        return currentInvitation != null ? currentInvitation.guests : null;
    }

    public void askForPaperInvitationWithLanguage(String language, final Callback<TRInvitation> callback) {
        currentInvitation.sendInvitation = true;
        currentInvitation.invitationLanguage = language;

        apiService.updateInvitationWithID(currentInvitationID, currentInvitation, new Callback<TRInvitation>() {
            @Override
            public void success(TRInvitation invitation, Response response) {
                currentInvitation = invitation;
                callback.success(invitation, response);
            }

            @Override
            public void failure(RetrofitError error) {
                currentInvitation.sendInvitation = false;
                callback.failure(error);
            }
        });
    }

    public void updateGuest(TRGuest guest) {
        apiService.updateGuestWithID(currentInvitationID, guest.id, guest, callbackPublishInvitation());
    }

    public void setCurrentInvitation(TRInvitation invitation) {
        this.currentInvitation = invitation;
        this.currentInvitationID = invitation.id;
        SharedPreferences.Editor editor =  context.getSharedPreferences(MY_PREFS_NAME, 0).edit();
        editor.putString("invitationID", invitation.id);
        editor.commit();
    }

    public String getCurrentInvitationID() {
        if (currentInvitationID == null) {
            SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, 0);
            currentInvitationID = prefs.getString("invitationID", null);
        }
        return currentInvitationID;
    }


    //--------------------------
    // Network Requests
    //--------------------------

    public void requestCurrentInvitation(String invitationID, final Callback<TRInvitation> callback) {
        apiService.getInvitationWithID(invitationID, new Callback<TRInvitation>() {
            @Override
            public void success(TRInvitation invitation, Response response) {
                currentInvitation = invitation;
                callback.success(invitation, response);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error);
            }
        });
    }

    private Callback<TRInvitation> callbackPublishInvitation() {
        return new Callback<TRInvitation>() {
            @Override
            public void success(TRInvitation invitation, Response response) {
                currentInvitation = invitation;
                onUpdateFinished();
            }

            @Override
            public void failure(RetrofitError error) {
                onUpdateFinished();
                new AlertDialog.Builder(context)
                    .setMessage(R.string.dialog_menu_message)
                    .setPositiveButton(R.string.ok, null)
                    .create()
                    .show();
            }
        };
    }

    private void onUpdateFinished() {
        if (onUpdateFinishedListener != null) {
            onUpdateFinishedListener.onUpdateFinished();
        }
    }

    public interface OnUpdateFinishedListener {
        public void onUpdateFinished();
    }
}
