package com.example.febriccostcalculator.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.febriccostcalculator.R;

public final class ActivityMainBinding implements ViewBinding {
    public final ImageView btnAddWeft;
    public final ImageView btnAddWrap;
    public final TextView btnCalculate;
    public final TextView btnClearAll;
    public final ImageView btnRate;
    public final ImageView btnRemoveWeft;
    public final ImageView btnShare;
    public final EditText editCount;
    public final TextView editToDenier;
    public final ItemMeterBinding itemMeter1;
    public final ItemMeterBinding itemMeter2;
    public final ItemPanoInchBinding itemPanoInch;
    public final TextView labelCount;
    public final TextView labelToDenier;
    public final LinearLayout llButton;
    public final LinearLayout llWeft;
    private final ConstraintLayout rootView;
    public final TextView txtTitle;
    public final ItemCostBinding warp1;
    public final ItemCostBinding warp2;
    public final ItemCostBinding weft1;
    public final ItemCostBinding weft2;
    public final ItemCostBinding weft3;
    public final ItemCostBinding weft4;
    public final ItemCostBinding weft5;
    public final ItemCostBinding weft6;
    public final ItemCostBinding weft7;
    public final ItemCostBinding weft8;

    private ActivityMainBinding(ConstraintLayout rootView2, ImageView btnAddWeft2, ImageView btnAddWrap2, TextView btnCalculate2, TextView btnClearAll2, ImageView btnRate2, ImageView btnRemoveWeft2, ImageView btnShare2, EditText editCount2, TextView editToDenier2, ItemMeterBinding itemMeter12, ItemMeterBinding itemMeter22, ItemPanoInchBinding itemPanoInch2, TextView labelCount2, TextView labelToDenier2, LinearLayout llButton2, LinearLayout llWeft2, TextView txtTitle2, ItemCostBinding warp12, ItemCostBinding warp22, ItemCostBinding weft12, ItemCostBinding weft22, ItemCostBinding weft32, ItemCostBinding weft42, ItemCostBinding weft52, ItemCostBinding weft62, ItemCostBinding weft72, ItemCostBinding weft82) {
        this.rootView = rootView2;
        this.btnAddWeft = btnAddWeft2;
        this.btnAddWrap = btnAddWrap2;
        this.btnCalculate = btnCalculate2;
        this.btnClearAll = btnClearAll2;
        this.btnRate = btnRate2;
        this.btnRemoveWeft = btnRemoveWeft2;
        this.btnShare = btnShare2;
        this.editCount = editCount2;
        this.editToDenier = editToDenier2;
        this.itemMeter1 = itemMeter12;
        this.itemMeter2 = itemMeter22;
        this.itemPanoInch = itemPanoInch2;
        this.labelCount = labelCount2;
        this.labelToDenier = labelToDenier2;
        this.llButton = llButton2;
        this.llWeft = llWeft2;
        this.txtTitle = txtTitle2;
        this.warp1 = warp12;
        this.warp2 = warp22;
        this.weft1 = weft12;
        this.weft2 = weft22;
        this.weft3 = weft32;
        this.weft4 = weft42;
        this.weft5 = weft52;
        this.weft6 = weft62;
        this.weft7 = weft72;
        this.weft8 = weft82;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityMainBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.btnAddWeft;
        ImageView btnAddWeft2 = (ImageView) ViewBindings.findChildViewById(view, R.id.btnAddWeft);
        if (btnAddWeft2 != null) {
            id = R.id.btnAddWrap;
            ImageView btnAddWrap2 = (ImageView) ViewBindings.findChildViewById(view, R.id.btnAddWrap);
            if (btnAddWrap2 != null) {
                id = R.id.btn_calculate;
                TextView btnCalculate2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_calculate);
                if (btnCalculate2 != null) {
                    id = R.id.btn_clear_all;
                    TextView btnClearAll2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_clear_all);
                    if (btnClearAll2 != null) {
                        id = R.id.btn_rate;
                        ImageView btnRate2 = (ImageView) ViewBindings.findChildViewById(view, R.id.btn_rate);
                        if (btnRate2 != null) {
                            id = R.id.btnRemoveWeft;
                            ImageView btnRemoveWeft2 = (ImageView) ViewBindings.findChildViewById(view, R.id.btnRemoveWeft);
                            if (btnRemoveWeft2 != null) {
                                id = R.id.btn_share;
                                ImageView btnShare2 = (ImageView) ViewBindings.findChildViewById(view, R.id.btn_share);
                                if (btnShare2 != null) {
                                    id = R.id.edit_count;
                                    EditText editCount2 = (EditText) ViewBindings.findChildViewById(view, R.id.edit_count);
                                    if (editCount2 != null) {
                                        id = R.id.edit_to_denier;
                                        TextView editToDenier2 = (TextView) ViewBindings.findChildViewById(view, R.id.edit_to_denier);
                                        if (editToDenier2 != null) {
                                            id = R.id.item_meter1;
                                            View itemMeter12 = ViewBindings.findChildViewById(view, R.id.item_meter1);
                                            if (itemMeter12 != null) {
                                                ItemMeterBinding binding_itemMeter1 = ItemMeterBinding.bind(itemMeter12);
                                                id = R.id.item_meter2;
                                                View itemMeter22 = ViewBindings.findChildViewById(view, R.id.item_meter2);
                                                if (itemMeter22 != null) {
                                                    ItemMeterBinding binding_itemMeter2 = ItemMeterBinding.bind(itemMeter22);
                                                    id = R.id.item_pano_inch;
                                                    View itemPanoInch2 = ViewBindings.findChildViewById(view, R.id.item_pano_inch);
                                                    if (itemPanoInch2 != null) {
                                                        ItemPanoInchBinding binding_itemPanoInch = ItemPanoInchBinding.bind(itemPanoInch2);
                                                        id = R.id.label_count;
                                                        TextView labelCount2 = (TextView) ViewBindings.findChildViewById(view, R.id.label_count);
                                                        if (labelCount2 != null) {
                                                            id = R.id.label_to_denier;
                                                            TextView labelToDenier2 = (TextView) ViewBindings.findChildViewById(view, R.id.label_to_denier);
                                                            if (labelToDenier2 != null) {
                                                                id = R.id.llButton;
                                                                LinearLayout llButton2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.llButton);
                                                                if (llButton2 != null) {
                                                                    id = R.id.llWeft;
                                                                    LinearLayout llWeft2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.llWeft);
                                                                    if (llWeft2 != null) {
                                                                        id = R.id.txt_title;
                                                                        TextView txtTitle2 = (TextView) ViewBindings.findChildViewById(view, R.id.txt_title);
                                                                        if (txtTitle2 != null) {
                                                                            id = R.id.warp_1;
                                                                            View warp12 = ViewBindings.findChildViewById(view, R.id.warp_1);
                                                                            if (warp12 != null) {
                                                                                ItemCostBinding binding_warp1 = ItemCostBinding.bind(warp12);
                                                                                id = R.id.warp_2;
                                                                                View warp22 = ViewBindings.findChildViewById(view, R.id.warp_2);
                                                                                if (warp22 != null) {
                                                                                    ItemCostBinding binding_warp2 = ItemCostBinding.bind(warp22);
                                                                                    id = R.id.weft1;
                                                                                    View weft12 = ViewBindings.findChildViewById(view, R.id.weft1);
                                                                                    if (weft12 != null) {
                                                                                        ItemCostBinding binding_weft1 = ItemCostBinding.bind(weft12);
                                                                                        id = R.id.weft2;
                                                                                        View weft22 = ViewBindings.findChildViewById(view, R.id.weft2);
                                                                                        if (weft22 != null) {
                                                                                            ItemCostBinding binding_weft2 = ItemCostBinding.bind(weft22);
                                                                                            id = R.id.weft3;
                                                                                            View weft32 = ViewBindings.findChildViewById(view, R.id.weft3);
                                                                                            if (weft32 != null) {
                                                                                                ItemCostBinding binding_weft3 = ItemCostBinding.bind(weft32);
                                                                                                id = R.id.weft4;
                                                                                                View weft42 = ViewBindings.findChildViewById(view, R.id.weft4);
                                                                                                if (weft42 != null) {
                                                                                                    ItemCostBinding binding_weft4 = ItemCostBinding.bind(weft42);
                                                                                                    id = R.id.weft5;
                                                                                                    View weft52 = ViewBindings.findChildViewById(view, R.id.weft5);
                                                                                                    if (weft52 != null) {
                                                                                                        ItemCostBinding binding_weft5 = ItemCostBinding.bind(weft52);
                                                                                                        id = R.id.weft6;
                                                                                                        View weft62 = ViewBindings.findChildViewById(view, R.id.weft6);
                                                                                                        if (weft62 != null) {
                                                                                                            ItemCostBinding binding_weft6 = ItemCostBinding.bind(weft62);
                                                                                                            id = R.id.weft7;
                                                                                                            View weft72 = ViewBindings.findChildViewById(view, R.id.weft7);
                                                                                                            if (weft72 != null) {
                                                                                                                ItemCostBinding binding_weft7 = ItemCostBinding.bind(weft72);
                                                                                                                id = R.id.weft8;
                                                                                                                View weft82 = ViewBindings.findChildViewById(view, R.id.weft8);
                                                                                                                if (weft82 != null) {
                                                                                                                    return new ActivityMainBinding((ConstraintLayout) view, btnAddWeft2, btnAddWrap2, btnCalculate2, btnClearAll2, btnRate2, btnRemoveWeft2, btnShare2, editCount2, editToDenier2, binding_itemMeter1, binding_itemMeter2, binding_itemPanoInch, labelCount2, labelToDenier2, llButton2, llWeft2, txtTitle2, binding_warp1, binding_warp2, binding_weft1, binding_weft2, binding_weft3, binding_weft4, binding_weft5, binding_weft6, binding_weft7, ItemCostBinding.bind(weft82));
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
