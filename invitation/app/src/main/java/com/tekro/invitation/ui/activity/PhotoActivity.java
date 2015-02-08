package com.tekro.invitation.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.tekro.invitation.R;
import com.tekro.invitation.adapter.TabPagerAdapter;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Rocío on 01/02/2015.
 */
public class PhotoActivity extends Activity{


    //--------------------------
    // Attributes
    //--------------------------

    private ImageView imageView;
    private PhotoViewAttacher photoViewAttacher;


    //--------------------------
    // FragmentActivity Methods
    //--------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imageView = (ImageView) findViewById(R.id.imageView);
        photoViewAttacher = new PhotoViewAttacher(imageView);
    }
}
