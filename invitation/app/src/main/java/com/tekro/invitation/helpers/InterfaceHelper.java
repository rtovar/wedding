package com.tekro.invitation.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by Pau on 12/2/2015.
 */
public class InterfaceHelper {

    public static Context context;

    public static Drawable getImageWitResIDAndColor(int imageResID, int color ) {
        Drawable drawable = context.getDrawable(imageResID);
        drawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        return drawable;
    }
}
