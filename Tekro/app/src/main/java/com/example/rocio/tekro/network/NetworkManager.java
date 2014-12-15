package com.example.rocio.tekro.network;

import com.example.rocio.tekro.model.TRInvitation;
import com.example.rocio.tekro.model.TRInvitations;

import retrofit.RestAdapter;

/**
 * Created by Rocio on 15/12/14.
 */
public class NetworkManager {

    // ----------------------------------
    // CONSTANTS
    // ----------------------------------

    private static final String BASE_URL = "http://mostrolabs.com:8090";


    // ----------------------------------
    // ATTRIBUTES
    // ----------------------------------

    private static NetworkManager mInstance = null;
    private RestAdapter restAdapter;
    public ServiceInvitations apiService;


    // ----------------------------------
    // CONSTRUCTORS AND INIT METHODS
    // ----------------------------------

    public static NetworkManager getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkManager();
            mInstance.init();
        }

        return mInstance;
    }

    public void init() {
        restAdapter = new RestAdapter.Builder().setEndpoint(BASE_URL).build();
        apiService = restAdapter.create(ServiceInvitations.class);
    }
}
