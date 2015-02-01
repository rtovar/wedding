package com.tekro.invitation.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Pau on 1/2/2015.
 */
public class TRInvitation {
    @SerializedName("_id")
    public String id;
    public ArrayList<TRGuest> guests;
    public boolean sendInvitation;
    public String invitationLanguage;
}
