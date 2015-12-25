package com.vladimirprus.littleprogram;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Picture implements Serializable {

    private static final long serialVersionUID = 7666098927554208607L;

    public Picture(String name, int imageResource)
    {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String name;
    // In real life, this would be an URL, for a little program just point at a resource.
    public int imageResource;
}
