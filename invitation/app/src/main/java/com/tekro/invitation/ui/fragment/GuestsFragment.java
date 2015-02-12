package com.tekro.invitation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tekro.invitation.R;
import com.tekro.invitation.adapter.GuestsAdapter;
import com.tekro.invitation.model.TRGuest;
import com.tekro.invitation.network.ContentProvider;

/**
 * Created by Pau on 8/2/2015.
 */
public class GuestsFragment extends Fragment {


    //--------------------------
    // Attributes
    //--------------------------

    private ListView listView;
    private GuestsAdapter adapter;


    //--------------------------
    // Fragment Methods
    //--------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_guests, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new GuestsAdapter(getActivity());
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(ContentProvider.getInstance().getGuests());
        adapter.notifyDataSetChanged();
    }

    //--------------------------
    // Data Methods
    //--------------------------

    public void reloadData() {
        if (adapter.getCount() == 0 && ContentProvider.getInstance().getGuests() != null) {
            adapter.addAll(ContentProvider.getInstance().getGuests());
        }
        adapter.notifyDataSetChanged();
    }
}
