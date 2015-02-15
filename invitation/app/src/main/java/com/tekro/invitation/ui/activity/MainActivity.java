package com.tekro.invitation.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tekro.invitation.R;
import com.tekro.invitation.adapter.TabPagerAdapter;
import com.tekro.invitation.network.ContentProvider;
import com.tekro.invitation.ui.fragment.GuestsFragment;


public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, ContentProvider.OnUpdateFinishedListener {

    //--------------------------
    // Attributes
    //--------------------------

    private ViewPager viewPager;
    private TabPagerAdapter mAdapter;


    //--------------------------
    // FragmentActivity Methods
    //--------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initilization
        ContentProvider.getInstance().onUpdateFinishedListener = this;
        ContentProvider.context = this;
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagerTabStrip);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.theme_pink));
        pagerTabStrip.setTextColor(getResources().getColor(R.color.theme_pink));

        for (int i = 0; i < pagerTabStrip.getChildCount(); ++i) {
            View nextChild = pagerTabStrip.getChildAt(i);
            if (nextChild instanceof TextView) {
                Typeface font = Typeface.createFromAsset(getAssets(), "HelveticaNeueLight.ttf");
                TextView textViewToConvert = (TextView) nextChild;
                textViewToConvert.setTypeface(font);
            }
        }

        viewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new TabPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setOnPageChangeListener(this);

        if (ContentProvider.getInstance().currentInvitation == null) {
            pushLoadingActivity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //--------------------------
    // Navigation Methods
    //--------------------------

    public void pushPhotoActivity() {
        Intent myIntent = new Intent(MainActivity.this, PhotoActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    private void pushLoadingActivity() {
        Intent myIntent = new Intent(MainActivity.this, LoadingActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void pushMenuActivity() {
        Intent myIntent = new Intent(MainActivity.this, MenuActivity.class);
        MainActivity.this.startActivity(myIntent);
    }


    //------------------------------
    // OnPageChangeListener Methods
    //------------------------------

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 1) {
            ((GuestsFragment)mAdapter.getItem(position)).reloadData();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    //----------------------------------
    // OnUpdateFinishedListener Methods
    //----------------------------------


    @Override
    public void onUpdateFinished() {
        ((GuestsFragment)mAdapter.getItem(1)).reloadData();
    }
}
