package com.example.febriccostcalculator.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.febriccostcalculator.R;

public final class ItemCostBinding implements ViewBinding {
    public final EditText editView1;
    public final EditText editView2;
    public final EditText editView3;
    public final ConstraintLayout item;
    private final ConstraintLayout rootView;
    public final TextView tvLabel;
    public final TextView tvLabel1;
    public final TextView tvLabel2;
    public final TextView tvLabel3;

    private ItemCostBinding(ConstraintLayout rootView2, EditText editView12, EditText editView22, EditText editView32, ConstraintLayout item2, TextView tvLabel4, TextView tvLabel12, TextView tvLabel22, TextView tvLabel32) {
        this.rootView = rootView2;
        this.editView1 = editView12;
        this.editView2 = editView22;
        this.editView3 = editView32;
        this.item = item2;
        this.tvLabel = tvLabel4;
        this.tvLabel1 = tvLabel12;
        this.tvLabel2 = tvLabel22;
        this.tvLabel3 = tvLabel32;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemCostBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ItemCostBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_cost, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemCostBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.editView1;
        EditText editView12 = (EditText) ViewBindings.findChildViewById(view, R.id.editView1);
        if (editView12 != null) {
            id = R.id.editView2;
            EditText editView22 = (EditText) ViewBindings.findChildViewById(view, R.id.editView2);
            if (editView22 != null) {
                id = R.id.editView3;
                EditText editView32 = (EditText) ViewBindings.findChildViewById(view, R.id.editView3);
                if (editView32 != null) {
                    ConstraintLayout item2 = (ConstraintLayout) view;
                    id = R.id.tvLabel;
                    TextView tvLabel4 = (TextView) ViewBindings.findChildViewById(view, R.id.tvLabel);
                    if (tvLabel4 != null) {
                        id = R.id.tvLabel1;
                        TextView tvLabel12 = (TextView) ViewBindings.findChildViewById(view, R.id.tvLabel1);
                        if (tvLabel12 != null) {
                            id = R.id.tvLabel2;
                            TextView tvLabel22 = (TextView) ViewBindings.findChildViewById(view, R.id.tvLabel2);
                            if (tvLabel22 != null) {
                                id = R.id.tvLabel3;
                                TextView tvLabel32 = (TextView) ViewBindings.findChildViewById(view, R.id.tvLabel3);
                                if (tvLabel32 != null) {
                                    return new ItemCostBinding((ConstraintLayout) view, editView12, editView22, editView32, item2, tvLabel4, tvLabel12, tvLabel22, tvLabel32);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(id)));
    }
}
