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

public final class ItemPanoInchBinding implements ViewBinding {
    public final EditText editJobCharge;
    public final EditText editPano;
    public final EditText editWastage;
    private final ConstraintLayout rootView;
    public final TextView tvInch;
    public final TextView tvJobCharge;
    public final TextView tvPano;
    public final TextView tvPickWise;
    public final TextView tvWastage;
    public final TextView tvper;

    private ItemPanoInchBinding(ConstraintLayout rootView2, EditText editJobCharge2, EditText editPano2, EditText editWastage2, TextView tvInch2, TextView tvJobCharge2, TextView tvPano2, TextView tvPickWise2, TextView tvWastage2, TextView tvper2) {
        this.rootView = rootView2;
        this.editJobCharge = editJobCharge2;
        this.editPano = editPano2;
        this.editWastage = editWastage2;
        this.tvInch = tvInch2;
        this.tvJobCharge = tvJobCharge2;
        this.tvPano = tvPano2;
        this.tvPickWise = tvPickWise2;
        this.tvWastage = tvWastage2;
        this.tvper = tvper2;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemPanoInchBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ItemPanoInchBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_pano_inch, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemPanoInchBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.editJobCharge;
        EditText editJobCharge2 = (EditText) ViewBindings.findChildViewById(view, R.id.editJobCharge);
        if (editJobCharge2 != null) {
            id = R.id.editPano;
            EditText editPano2 = (EditText) ViewBindings.findChildViewById(view, R.id.editPano);
            if (editPano2 != null) {
                id = R.id.editWastage;
                EditText editWastage2 = (EditText) ViewBindings.findChildViewById(view, R.id.editWastage);
                if (editWastage2 != null) {
                    id = R.id.tvInch;
                    TextView tvInch2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvInch);
                    if (tvInch2 != null) {
                        id = R.id.tvJobCharge;
                        TextView tvJobCharge2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvJobCharge);
                        if (tvJobCharge2 != null) {
                            id = R.id.tvPano;
                            TextView tvPano2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvPano);
                            if (tvPano2 != null) {
                                id = R.id.tvPickWise;
                                TextView tvPickWise2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvPickWise);
                                if (tvPickWise2 != null) {
                                    id = R.id.tvWastage;
                                    TextView tvWastage2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvWastage);
                                    if (tvWastage2 != null) {
                                        id = R.id.tvper;
                                        TextView tvper2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvper);
                                        if (tvper2 != null) {
                                            return new ItemPanoInchBinding((ConstraintLayout) view, editJobCharge2, editPano2, editWastage2, tvInch2, tvJobCharge2, tvPano2, tvPickWise2, tvWastage2, tvper2);
                                        }
                                    }
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
