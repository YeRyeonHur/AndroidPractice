package com.kyh.movieapp.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImagePutter {
    private ImagePutter() {}

    public static void putImage(Context context, String imgPath, ImageView imageView) {
        if(context != null && imgPath != null && !imgPath.equals("") && imageView != null) {
            Picasso.with(context).load(imgPath).into(imageView);
        }
    }
}
