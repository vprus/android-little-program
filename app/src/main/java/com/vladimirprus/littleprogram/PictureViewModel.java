package com.vladimirprus.littleprogram;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class PictureViewModel {

    public String name;
    public Drawable image;

    public Picture model;

    static PictureViewModel make(Picture picture, Resources resources)
    {
        PictureViewModel vm = new PictureViewModel();
        vm.model = picture;
        vm.name = picture.name;
        vm.image = resources.getDrawable(picture.imageResource);
        return vm;
    }
}
