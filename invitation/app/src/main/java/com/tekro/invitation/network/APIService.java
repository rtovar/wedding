package com.tekro.invitation.network;

import com.tekro.invitation.model.TRGuest;
import com.tekro.invitation.model.TRInvitation;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Rocío on 28/01/2015.
 */
public interface APIService {

    @GET("/invite/{id}")
    public void getInvitationWithID(@Path("id") String id, Callback<TRInvitation> callback);

    @POST("/invite")
    public void publishInvitation(@Body TRInvitation invitation, Callback<TRInvitation> callback);

    @POST("/invite/{id}/{guest_id}")
    public void updateGuestWithID(@Path("id") String id, @Path("guest_id") String guestID, @Body TRGuest guest, Callback<TRInvitation> callback);

    @POST("/invite/{id}")
    public void updateInvitationWithID(@Path("id") String id, @Body TRInvitation invitation, Callback<TRInvitation> callback);

}
