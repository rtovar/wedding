package com.example.rocio.tekro.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rocio on 15/12/14.
 */
public class TRGuest
{

    // ----------------------------------
    // ATTRIBUTES
    // ----------------------------------

    public String id;
    public boolean asistencia;
    @SerializedName("tipo de menu") public String menu;
    public String mail;
    public String nombre;
    public String apellidos;
}
