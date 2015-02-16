package com.tekro.invitation.ui.fragment;

import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.tekro.invitation.R;
import com.tekro.invitation.adapter.GuestsAdapter;
import com.tekro.invitation.ui.activity.MainActivity;

/**
 * Created by Rocío on 15/02/2015.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {


    //--------------------------
    // Attributes
    //--------------------------
    private ImageView iconDateImageView;
    private ImageView iconTimeImageView;
    private ImageView iconPlaceImageView;
    private Button mapsButton;


    //--------------------------
    // Fragment Methods
    //--------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        findViews(view);
        setupIcons();
        mapsButton.setOnClickListener(this);

        return view;
    }

    private void findViews(View contentview) {
        iconDateImageView = (ImageView)contentview.findViewById(R.id.iconDateImageView);
        iconTimeImageView = (ImageView)contentview.findViewById(R.id.iconTimeImageView);
        iconPlaceImageView = (ImageView)contentview.findViewById(R.id.iconPlaceImageView);
        mapsButton = (Button)contentview.findViewById(R.id.buttonMaps);
    }

    private void setupIcons() {
        setupIcon(iconDateImageView);
        setupIcon(iconTimeImageView);
        setupIcon(iconPlaceImageView);
    }

    private void setupIcon(ImageView imageView) {
        imageView.setColorFilter(getActivity().getResources().getColor(R.color.theme_pink), PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onClick(View v) {
        ((MainActivity)getActivity()).pushMapsIntent();
    }
}
