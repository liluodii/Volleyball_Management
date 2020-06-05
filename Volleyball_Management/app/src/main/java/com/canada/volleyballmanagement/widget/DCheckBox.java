package com.canada.volleyballmanagement.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatCheckBox;


public class DCheckBox extends AppCompatCheckBox {

    public DCheckBox(Context context) {
        super(context);
    }

    public DCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        TextViewHelper.setTypeface(context, this, attrs);
    }
}