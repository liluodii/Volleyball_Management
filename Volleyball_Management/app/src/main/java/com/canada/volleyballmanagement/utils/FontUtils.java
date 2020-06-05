package com.canada.volleyballmanagement.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FontUtils {

    public static Typeface createTypeface(Context context, boolean isBold) {
        if (isBold) return Typeface.createFromAsset(context.getAssets(),
                "Poppins-Regular.otf");
        else return Typeface.createFromAsset(context.getAssets(),
                "Poppins-Regular.otf");
    }

    public static void setFont(ViewGroup group, Typeface font) {
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(font);
            } else if (v instanceof ViewGroup)
                setFont((ViewGroup) v, font);
        }
    }

    public static void setFont(View v, Typeface font) {
        if (v instanceof TextView) {
            ((TextView) v).setTypeface(font);
        }
    }

    public static Typeface fontName(Context context, int type) {
        Typeface typeface;
        switch (type) {
            case 1:
                typeface = Typeface.createFromAsset(context.getAssets(),
                        "font/AvenirNextLTPro-Regular.otf");
                break;
            case 2:
                typeface = Typeface.createFromAsset(context.getAssets(),
                        "font/AvenirNext-Bold_1.ttf");
                break;
            case 3:
                typeface = Typeface.createFromAsset(context.getAssets(),
                        "font/AvenirNext-Medium_2.ttf");
                break;
            case 4:
                typeface = Typeface.createFromAsset(context.getAssets(),
                        "font/AvenirNext-Bold_1.ttf");
                break;
            default:
                typeface = Typeface.createFromAsset(context.getAssets(),
                        "fonts/SFUIDisplay-Regular.ttf");
                break;
        }
        return typeface;
    }


}