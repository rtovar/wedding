package com.example.rocio.tekro.network;

import com.example.rocio.tekro.model.TRGuest;
import com.example.rocio.tekro.model.TRInvitation;
import com.example.rocio.tekro.model.TRInvitations;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Rocio on 15/12/14.
 */


//    - GET     /invite                                    -> Da una lista completa de invitaciones {"invitations" : [{...}, ...]}
//    - GET     /invite/:invitationId                  -> Da una invitación específica {...}
//    - POST   /invite                                   -> Guarda el body como invitación nueva y la regresa como haciendo un GET {...}
//    - POST   /invite/:invitationId/:personId   -> Sobreescribe a una persona específica, regresa la invitación actualizada como GET {...}
public interface ServiceInvitations
{

    /**
     * Returns the complete list of invitations.
     *
     * @param callback The callback to handle the server response.
     */
    @GET("/invite")
    public void getInvitations(Callback<TRInvitations> callback);

    /**
     * Returns the invitation with the given ID.
     *
     * @param invitationID The ID of the invitation we want to consult.
     * @param callback The callback to handle the server response.
     *
     * @return An invitation or null in case no invitation is found with the given ID.
     */
    @GET("/invite/{invitationID}")
    public void getInvitations(@Path("invitationID") String invitationID, Callback<TRInvitation> callback);

    /**
     * Posts the given invitation.
     *
     * @param invitation The invitation to be posted to the server.
     * @param callback The callback to handle the server response.
     */
    @POST("/invite")
    public void createInvitation(@Body TRInvitation invitation, Callback<TRInvitation> callback);

    /**
     * Replaces the guest with the given ID with the new guest.
     *
     * @param invitationID The ID of the invitation the new guest belongs to.
     * @param personId The ID of the person to be replaced.
     * @param callback The callback to handle the server response.
     *
     * @return The invitation with the given ID and all their guests updated.
     */
    @POST("/invite/{invitationId}/{personId}")
    public TRInvitation createInvitation(@Path("invitationID") String invitationID, @Path("personId") String personId, @Body TRGuest guest, Callback<TRInvitation> callback);
}
