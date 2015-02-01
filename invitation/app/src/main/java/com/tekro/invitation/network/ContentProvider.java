package com.tekro.invitation.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    private static ContentProvider instance;
    private APIService apiService;
    private TRInvitation currentInvitation;

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

    public TRInvitation getCurrentInvitation() {
        return currentInvitation;
    }

    public void askForPaperInvitationWithLanguage(String language, final Callback<TRInvitation> callback) {
        currentInvitation.sendInvitation = true;
        currentInvitation.invitationLanguage = language;

        apiService.publishInvitation(currentInvitation, new Callback<TRInvitation>() {
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

    public void publishDummie() {
        TRInvitation invitation = new TRInvitation();
        TRGuest guest = new TRGuest();
        guest.firstName = "John";
        guest.lastName = "Doe";
        guest.id = "1";
        guest.mail = "john@doe.com";
        guest.menu = "meat";
        guest.rsvp = true;
        ArrayList<TRGuest> guests = new ArrayList<>();
        guests.add(guest);
        invitation.guests = guests;
        invitation.sendInvitation = true;
        invitation.invitationLanguage = "cat";

        apiService.publishInvitation(invitation, callbackPublishInvitation());
    }

    private Callback<TRInvitation> callbackPublishInvitation() {
        return new Callback<TRInvitation>() {
            @Override
            public void success(TRInvitation invitation, Response response) {
                currentInvitation = invitation;
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO
                int i=0;
            }
        };
    }
}
