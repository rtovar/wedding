package com.tekro.invitation;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tekro.invitation.adapter.TabPagerAdapter;


public class MainActivity extends FragmentActivity implements android.support.v7.app.ActionBar.TabListener {

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
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagerTabStrip);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.theme_pink));
        pagerTabStrip.setTextColor(R.color.theme_pink);

        viewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new TabPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
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
    // TabListener Methods
    //--------------------------

    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    //--------------------------
    // Navigation Methods
    //--------------------------

    public void pushPhotoActivity() {
        Intent myIntent = new Intent(MainActivity.this, PhotoActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

}
