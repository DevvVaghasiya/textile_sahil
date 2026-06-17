package com.example.febriccostcalculator.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.febriccostcalculator.R;

public final class ActivitySplashBinding implements ViewBinding {
    public final LinearLayout llAnim;
    private final ConstraintLayout rootView;
    public final ImageView splashIcon;
    public final TextView txtSplash;

    private ActivitySplashBinding(ConstraintLayout rootView2, LinearLayout llAnim2, ImageView splashIcon2, TextView txtSplash2) {
        this.rootView = rootView2;
        this.llAnim = llAnim2;
        this.splashIcon = splashIcon2;
        this.txtSplash = txtSplash2;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySplashBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivitySplashBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_splash, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivitySplashBinding bind(View rootView2) {
        int id = R.id.llAnim;
        LinearLayout llAnim2 = (LinearLayout) ViewBindings.findChildViewById(rootView2, R.id.llAnim);
        if (llAnim2 != null) {
            id = R.id.splash_icon;
            ImageView splashIcon2 = (ImageView) ViewBindings.findChildViewById(rootView2, R.id.splash_icon);
            if (splashIcon2 != null) {
                id = R.id.txt_splash;
                TextView txtSplash2 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.txt_splash);
                if (txtSplash2 != null) {
                    return new ActivitySplashBinding((ConstraintLayout) rootView2, llAnim2, splashIcon2, txtSplash2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(id)));
    }
}
