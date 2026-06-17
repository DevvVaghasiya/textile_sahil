package com.example.febriccostcalculator.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.febriccostcalculator.R;

public final class ViewExitDialogBinding implements ViewBinding {
    public final TextView btnNo;
    public final TextView btnYes;
    public final LinearLayout llRate;
    private final RelativeLayout rootView;
    public final TextView tv2;

    private ViewExitDialogBinding(RelativeLayout rootView2, TextView btnNo2, TextView btnYes2, LinearLayout llRate2, TextView tv22) {
        this.rootView = rootView2;
        this.btnNo = btnNo2;
        this.btnYes = btnYes2;
        this.llRate = llRate2;
        this.tv2 = tv22;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ViewExitDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ViewExitDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.view_exit_dialog, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ViewExitDialogBinding bind(View rootView2) {
        int id = R.id.btn_no;
        TextView btnNo2 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.btn_no);
        if (btnNo2 != null) {
            id = R.id.btn_yes;
            TextView btnYes2 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.btn_yes);
            if (btnYes2 != null) {
                id = R.id.llRate;
                LinearLayout llRate2 = (LinearLayout) ViewBindings.findChildViewById(rootView2, R.id.llRate);
                if (llRate2 != null) {
                    id = R.id.tv2;
                    TextView tv22 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.tv2);
                    if (tv22 != null) {
                        return new ViewExitDialogBinding((RelativeLayout) rootView2, btnNo2, btnYes2, llRate2, tv22);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(id)));
    }
}
