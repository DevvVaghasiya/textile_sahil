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

public final class ItemMeterBinding implements ViewBinding {
    public final ConstraintLayout constraintView;
    public final EditText editView1;
    public final TextView labelJobCost;
    public final TextView labelMeter;
    public final TextView labelYarnCost;
    public final TextView labelYarnJob;
    private final ConstraintLayout rootView;
    public final TextView tvJobCost1;
    public final TextView tvMeter;
    public final TextView tvYarnCost1;
    public final TextView tvYarnJob1;

    private ItemMeterBinding(ConstraintLayout rootView2, ConstraintLayout constraintView2, EditText editView12, TextView labelJobCost2, TextView labelMeter2, TextView labelYarnCost2, TextView labelYarnJob2, TextView tvJobCost12, TextView tvMeter2, TextView tvYarnCost12, TextView tvYarnJob12) {
        this.rootView = rootView2;
        this.constraintView = constraintView2;
        this.editView1 = editView12;
        this.labelJobCost = labelJobCost2;
        this.labelMeter = labelMeter2;
        this.labelYarnCost = labelYarnCost2;
        this.labelYarnJob = labelYarnJob2;
        this.tvJobCost1 = tvJobCost12;
        this.tvMeter = tvMeter2;
        this.tvYarnCost1 = tvYarnCost12;
        this.tvYarnJob1 = tvYarnJob12;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemMeterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ItemMeterBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_meter, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemMeterBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.constraintView;
        ConstraintLayout constraintView2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.constraintView);
        if (constraintView2 != null) {
            id = R.id.editView1;
            EditText editView12 = (EditText) ViewBindings.findChildViewById(view, R.id.editView1);
            if (editView12 != null) {
                id = R.id.label_job_cost;
                TextView labelJobCost2 = (TextView) ViewBindings.findChildViewById(view, R.id.label_job_cost);
                if (labelJobCost2 != null) {
                    id = R.id.label_meter;
                    TextView labelMeter2 = (TextView) ViewBindings.findChildViewById(view, R.id.label_meter);
                    if (labelMeter2 != null) {
                        id = R.id.label_yarn_cost;
                        TextView labelYarnCost2 = (TextView) ViewBindings.findChildViewById(view, R.id.label_yarn_cost);
                        if (labelYarnCost2 != null) {
                            id = R.id.label_yarn_job;
                            TextView labelYarnJob2 = (TextView) ViewBindings.findChildViewById(view, R.id.label_yarn_job);
                            if (labelYarnJob2 != null) {
                                id = R.id.tvJobCost1;
                                TextView tvJobCost12 = (TextView) ViewBindings.findChildViewById(view, R.id.tvJobCost1);
                                if (tvJobCost12 != null) {
                                    id = R.id.tvMeter;
                                    TextView tvMeter2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvMeter);
                                    if (tvMeter2 != null) {
                                        id = R.id.tvYarnCost1;
                                        TextView tvYarnCost12 = (TextView) ViewBindings.findChildViewById(view, R.id.tvYarnCost1);
                                        if (tvYarnCost12 != null) {
                                            id = R.id.tvYarnJob1;
                                            TextView tvYarnJob12 = (TextView) ViewBindings.findChildViewById(view, R.id.tvYarnJob1);
                                            if (tvYarnJob12 != null) {
                                                return new ItemMeterBinding((ConstraintLayout) view, constraintView2, editView12, labelJobCost2, labelMeter2, labelYarnCost2, labelYarnJob2, tvJobCost12, tvMeter2, tvYarnCost12, tvYarnJob12);
                                            }
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
