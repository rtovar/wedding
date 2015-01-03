package com.example.rocio.tekro.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rocío on 03/01/2015.
 */
public abstract class BaseFragment extends Fragment {

    // -------------------------
    // ABSTRACT METHODS
    // -------------------------

    public abstract int getLayoutResID();
    public abstract void findViews(View rootView);
    public abstract void setListeners();


    // -------------------------
    // Fragment METHODS
    // -------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(getLayoutResID(), container);

        findViews(fragmentView);
        setListeners();

        return fragmentView;
    }
}
