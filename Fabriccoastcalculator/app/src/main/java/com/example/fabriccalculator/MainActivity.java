package com.example.fabriccalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fabriccalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static int itemWarp = 1;
    public static int itemWeft = 4;
    public DrawerLayout drawerLayout;
    double jobCost = -1.0d;
    ActivityMainBinding mBinding;
    double sumOfTotalPick;
    double warpCost1,warpCost2,warpWeight1,warpWeight2;
    double weftCost1, weftCost2, weftCost3, weftCost4, weftCost5, weftCost6, weftCost7, weftCost8;
    double weftWeight1, weftWeight2, weftWeight3, weftWeight4, weftWeight5, weftWeight6, weftWeight7, weftWeight8;
    double yarnCost = -1.0d;
    double yarnJob = -1.0d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        this.mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        updateUI();
        clickEvent();
    }

    public void calculation1() {
        this.warpWeight1 = step1(this.mBinding.warp1.editView1, this.mBinding.warp1.editView2, this.mBinding.itemPanoInch.editPano);
        this.warpWeight2 = step1(this.mBinding.warp2.editView1, this.mBinding.warp2.editView2, this.mBinding.itemPanoInch.editPano);
        calculation2();
    }

    public void calculation2() {
        this.weftWeight1 = step2(this.mBinding.weft1.editView1, this.mBinding.weft1.editView2, this.mBinding.itemPanoInch.editPano, 1);
        this.weftWeight2 = step2(this.mBinding.weft2.editView1, this.mBinding.weft2.editView2, this.mBinding.itemPanoInch.editPano, 2);
        this.weftWeight3 = step2(this.mBinding.weft3.editView1, this.mBinding.weft3.editView2, this.mBinding.itemPanoInch.editPano, 3);
        this.weftWeight4 = step2(this.mBinding.weft4.editView1, this.mBinding.weft4.editView2, this.mBinding.itemPanoInch.editPano, 4);
        this.weftWeight5 = step2(this.mBinding.weft5.editView1, this.mBinding.weft5.editView2, this.mBinding.itemPanoInch.editPano, 5);
        this.weftWeight6 = step2(this.mBinding.weft6.editView1, this.mBinding.weft6.editView2, this.mBinding.itemPanoInch.editPano, 6);
        this.weftWeight7 = step2(this.mBinding.weft7.editView1, this.mBinding.weft7.editView2, this.mBinding.itemPanoInch.editPano, 7);
        this.weftWeight8 = step2(this.mBinding.weft8.editView1, this.mBinding.weft8.editView2, this.mBinding.itemPanoInch.editPano, 8);
        calculation3();
    }

    public void calculation3() {
        this.warpCost1 = step3(this.mBinding.warp1.editView3, this.warpWeight1, 9);
        this.warpCost2 = step3(this.mBinding.warp2.editView3, this.warpWeight2, 10);
        this.weftCost1 = step3(this.mBinding.weft1.editView3, this.weftWeight1, 1);
        this.weftCost2 = step3(this.mBinding.weft2.editView3, this.weftWeight2, 2);
        this.weftCost3 = step3(this.mBinding.weft3.editView3, this.weftWeight3, 3);
        this.weftCost4 = step3(this.mBinding.weft4.editView3, this.weftWeight4, 4);
        this.weftCost5 = step3(this.mBinding.weft5.editView3, this.weftWeight5, 5);
        this.weftCost6 = step3(this.mBinding.weft6.editView3, this.weftWeight6, 6);
        this.weftCost7 = step3(this.mBinding.weft7.editView3, this.weftWeight7, 7);
        this.weftCost8 = step3(this.mBinding.weft8.editView3, this.weftWeight8, 8);
        calculation4();
    }

    public void calculation4() {
        this.yarnCost = step4();
        calculation5();
    }

    public void calculation5() {
        this.sumOfTotalPick = step5();
        calculation6();
    }

    public void calculation6() {
        this.jobCost = step6(this.mBinding.itemPanoInch.editJobCharge);
        calculation7();
    }

    public void calculation7() {
        this.yarnJob = step7();
        setAnswers();
    }

    public void setAnswers() {
        if (!this.mBinding.itemMeter2.editView1.getText().toString().equals("")) {
            getAnswer((double) Integer.parseInt(this.mBinding.itemMeter2.editView1.getText().toString()));
        }
        this.mBinding.itemMeter1.tvMeter.setText("1");
        this.mBinding.itemMeter1.tvYarnCost1.setText("" + this.yarnCost);
        this.mBinding.itemMeter1.tvJobCost1.setText("" + this.jobCost);
        this.mBinding.itemMeter1.tvYarnJob1.setText("" + this.yarnJob);
    }

    public double step1(EditText editRead, EditText editDenier, EditText editPano) {
        double read = 0;
        double denier = 0;
        double pano = 0;
        if (!editRead.getText().toString().equals("")) {
//            read = Integer.parseInt(editRead.getText().toString());
            read =  Double.parseDouble(editRead.getText().toString());
        }
        if (!editDenier.getText().toString().equals("")) {
//            denier = Integer.parseInt(editDenier.getText().toString());
            denier = Double.parseDouble(editDenier.getText().toString());
        }
        if (!editPano.getText().toString().equals("")) {
//            pano = Integer.parseInt(editPano.getText().toString());
            pano = Double.parseDouble(editPano.getText().toString());
        }
        return (denier * ((read * pano) + 112)) / 9000.0d;
    }

    public double step2(EditText editRead, EditText editDenier, EditText editPano, int flag) {
        double read = 0;
        double pano = 0;
        double denier = 0;
        if (!editRead.getText().toString().equals("")) {
//            read = Integer.parseInt(editRead.getText().toString());
            read =  Double.parseDouble(editRead.getText().toString());
        }
        if (!editPano.getText().toString().equals("")) {
//            pano = Integer.parseInt(editPano.getText().toString());
            pano = Double.parseDouble(editPano.getText().toString());
        }
        if (!editDenier.getText().toString().equals("")) {
//            denier = Integer.parseInt(editDenier.getText().toString());
            denier = Double.parseDouble(editDenier.getText().toString());
        }
        return ((read * denier) * pano) / 9000.0d;
    }

    public double step3(EditText editRate, double weight, int flag) {
        double rate = 0;
        if (!editRate.getText().toString().equals("")) {
//            rate = Integer.parseInt(editRate.getText().toString());
            rate = Double.parseDouble(editRate.getText().toString());
        }
        return (rate * weight) / 1000.0d;
    }

    public double step4() {
        double sum = this.warpCost1 + this.warpCost2 + this.weftCost1 + this.weftCost2 + this.weftCost3 + this.weftCost4 + this.weftCost5 + this.weftCost6 + this.weftCost7 + this.weftCost8;
        double wastage = 0.0d;
        if (!this.mBinding.itemPanoInch.editWastage.getText().toString().equals("")) {
            wastage = Double.parseDouble(this.mBinding.itemPanoInch.editWastage.getText().toString());
        }
        return (((3.0d + wastage) * sum) / 100.0d) + sum;
    }

    public double step5() {
        double pick1 = 0;
        double pick2 = 0;
        double pick3 = 0;
        double pick4 = 0;
        double pick5 = 0;
        double pick6 = 0;
        double pick7 = 0;
        double pick8 = 0;
        if (!this.mBinding.weft1.editView1.getText().toString().equals("")) {
//            pick1 = Integer.parseInt(this.mBinding.weft1.editView1.getText().toString());
            pick1 = Double.parseDouble(this.mBinding.weft1.editView1.getText().toString());
        }
        if (!this.mBinding.weft2.editView1.getText().toString().equals("")) {
//            pick2 = Integer.parseInt(this.mBinding.weft2.editView1.getText().toString());
            pick2 = Double.parseDouble(this.mBinding.weft2.editView1.getText().toString());
        }
        if (!this.mBinding.weft3.editView1.getText().toString().equals("")) {
//            pick3 = Integer.parseInt(this.mBinding.weft3.editView1.getText().toString());
            pick3 = Double.parseDouble(this.mBinding.weft3.editView1.getText().toString());
        }
        if (!this.mBinding.weft4.editView1.getText().toString().equals("")) {
//            pick4 = Integer.parseInt(this.mBinding.weft4.editView1.getText().toString());
            pick4 = Double.parseDouble(this.mBinding.weft4.editView1.getText().toString());
        }
        if (!this.mBinding.weft5.editView1.getText().toString().equals("")) {
//            pick5 = Integer.parseInt(this.mBinding.weft5.editView1.getText().toString());
            pick5 = Double.parseDouble(this.mBinding.weft5.editView1.getText().toString());
        }
        if (!this.mBinding.weft6.editView1.getText().toString().equals("")) {
//            pick6 = Integer.parseInt(this.mBinding.weft6.editView1.getText().toString());
            pick6 = Double.parseDouble(this.mBinding.weft6.editView1.getText().toString());
        }
        if (!this.mBinding.weft7.editView1.getText().toString().equals("")) {
//            pick7 = Integer.parseInt(this.mBinding.weft7.editView1.getText().toString());
            pick7 = Double.parseDouble(this.mBinding.weft7.editView1.getText().toString());
        }
        if (!this.mBinding.weft8.editView1.getText().toString().equals("")) {
//            pick8 = Integer.parseInt(this.mBinding.weft8.editView1.getText().toString());
            pick8 = Double.parseDouble(this.mBinding.weft8.editView1.getText().toString());
        }
        return pick1 + pick2 + pick3 + pick4 + pick5 + pick6 + pick7 + pick8;
    }

    public double step6(EditText editJobCharge) {
        int jobCharge = 0;
        if (!editJobCharge.getText().toString().equals("")) {
            jobCharge = Integer.parseInt(editJobCharge.getText().toString());
        }
        return (this.sumOfTotalPick * ((double) jobCharge)) / 100.0d;
    }

    public double step7() {
        return this.yarnCost + this.jobCost;
    }

    public void getDenier(double count) {
        this.mBinding.editToDenier.setText("" + (5315.0d / count));
    }

    public void getAnswer(double meter) {
        if (this.yarnCost >= 0.0d && this.jobCost >= 0.0d && this.yarnJob >= 0.0d) {
            this.mBinding.itemMeter2.tvYarnCost1.setText("" + (this.yarnCost * meter));
            this.mBinding.itemMeter2.tvJobCost1.setText("" + (this.jobCost * meter));
            this.mBinding.itemMeter2.tvYarnJob1.setText("" + (this.yarnJob * meter));
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        ShowExitDialog(MainActivity.this);
    }

    private void ShowExitDialog(Context context) {
        Dialog dialogExit = new Dialog(context);
        dialogExit.setContentView(R.layout.view_exit_dialog);
        dialogExit.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND | WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialogExit.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(dialogExit.getWindow().getAttributes());
        params.width = (int) (Resources.getSystem().getDisplayMetrics().widthPixels * 0.83f);
        params.dimAmount = 0.70f;
        params.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        dialogExit.getWindow().setAttributes(params);
        dialogExit.show();
        dialogExit.findViewById(R.id.btn_no).setOnClickListener(view -> dialogExit.dismiss());
        dialogExit.findViewById(R.id.btn_yes).setOnClickListener(view -> {
            dialogExit.dismiss();
            finishAffinity();
        });
    }

    public void onResume() {
        super.onResume();
        addRemoveWeftItem();
        addRemoveWarpItem();
    }

    public void clickEvent() {
        this.mBinding.itemMeter2.editView1.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
//                if (!mBinding.itemMeter2.editView1.getText().toString().equals("")) {
//                    getAnswer((double) Long.parseLong(mBinding.itemMeter2.editView1.getText().toString()));
                    String text = mBinding.itemMeter2.editView1.getText().toString();
                    if (!text.isEmpty()) {
                        try {
                            double value = Double.parseDouble(text);
                            getAnswer(value);
                        } catch (NumberFormatException e) {
                            e.printStackTrace(); // Or show a user-friendly error
                        }
                    }
//                }
            }
        });

        this.mBinding.editCount.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
//                if (!mBinding.editCount.getText().toString().equals("")) {
//                    getDenier((double) Long.parseLong(mBinding.editCount.getText().toString()));
                    String text = mBinding.editCount.getText().toString();
                    if (!text.isEmpty()) {
                        try {
                            double value = Double.parseDouble(text);
                            getDenier(value);
                        } catch (NumberFormatException e) {
                            e.printStackTrace(); // Or show a user-friendly error
                        }
                    }
//                }
            }
        });

        this.mBinding.btnAddWrap.setOnClickListener(view -> {
            addRemoveWrap();
        });

        this.mBinding.btnAddWeft.setOnClickListener(view -> {
            addWeft();
        });

        this.mBinding.btnRemoveWeft.setOnClickListener(view -> {
            removeWeft();
        });

        this.mBinding.btnCalculate.setOnClickListener(view -> {
            calculation1();
        });

        this.mBinding.btnClearAll.setOnClickListener(view -> clearAll());

        this.mBinding.btnRate.setOnClickListener(view -> MainActivity.this.Rate_Dialog());

        this.mBinding.btnShare.setOnClickListener(view -> {
            Intent sharingIntent = new Intent("android.intent.action.SEND");
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra("android.intent.extra.TEXT", String.format("try this amazing Fabric Costing Calculator application. Click on the link to download now http://play.google.com/store/apps/details?id=%s", new Object[]{Integer.valueOf(R.string.app_name), MainActivity.this.getPackageName()}));
            MainActivity.this.startActivity(Intent.createChooser(sharingIntent, "Share using"));
        });
    }

    public void addRemoveWrap() {
        int i = itemWarp;
        if (i == 2) {
            itemWarp = i - 1;
            this.mBinding.btnAddWrap.setImageResource(R.drawable.add_icon);
        } else {
            itemWarp = i + 1;
            this.mBinding.btnAddWrap.setImageResource(R.drawable.remove_icon);
        }
        addRemoveWarpItem();
    }

    public void addWeft() {
        int i = itemWeft;
        if (i != 8) {
            itemWeft = i + 1;
            addRemoveWeftItem();
        }
    }

    public void removeWeft() {
        itemWeft--;
        addRemoveWeftItem();
    }

    public void Rate_Dialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.view_rate_us);
        ((TextView) dialog.findViewById(R.id.btn_letter)).setOnClickListener(view -> dialog.dismiss());
        ((TextView) dialog.findViewById(R.id.btn_rate_now)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        });
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

    public void addRemoveWarpItem() {
        if (itemWarp == 2) {
            this.mBinding.warp2.item.setVisibility(View.VISIBLE);
        } else {
            this.mBinding.warp2.item.setVisibility(View.GONE);
        }
    }

    public void addRemoveWeftItem() {
        switch (itemWeft) {
            case 4:
                this.mBinding.weft5.item.setVisibility(View.GONE);
                this.mBinding.weft6.item.setVisibility(View.GONE);
                this.mBinding.weft7.item.setVisibility(View.GONE);
                this.mBinding.weft8.item.setVisibility(View.GONE);
                this.mBinding.btnRemoveWeft.setVisibility(View.GONE);
                this.mBinding.btnAddWeft.setVisibility(View.VISIBLE);
                return;
            case 5:
                this.mBinding.weft5.item.setVisibility(View.VISIBLE);
                this.mBinding.weft6.item.setVisibility(View.GONE);
                this.mBinding.weft7.item.setVisibility(View.GONE);
                this.mBinding.weft8.item.setVisibility(View.GONE);
                this.mBinding.btnRemoveWeft.setVisibility(View.VISIBLE);
                this.mBinding.btnAddWeft.setVisibility(View.VISIBLE);
                return;
            case 6:
                this.mBinding.weft5.item.setVisibility(View.VISIBLE);
                this.mBinding.weft6.item.setVisibility(View.VISIBLE);
                this.mBinding.weft7.item.setVisibility(View.GONE);
                this.mBinding.weft8.item.setVisibility(View.GONE);
                this.mBinding.btnRemoveWeft.setVisibility(View.VISIBLE);
                this.mBinding.btnAddWeft.setVisibility(View.VISIBLE);
                return;
            case 7:
                this.mBinding.weft5.item.setVisibility(View.VISIBLE);
                this.mBinding.weft6.item.setVisibility(View.VISIBLE);
                this.mBinding.weft7.item.setVisibility(View.VISIBLE);
                this.mBinding.weft8.item.setVisibility(View.GONE);
                this.mBinding.btnRemoveWeft.setVisibility(View.VISIBLE);
                this.mBinding.btnAddWeft.setVisibility(View.VISIBLE);
                return;
            case 8:
                this.mBinding.weft5.item.setVisibility(View.VISIBLE);
                this.mBinding.weft6.item.setVisibility(View.VISIBLE);
                this.mBinding.weft7.item.setVisibility(View.VISIBLE);
                this.mBinding.weft8.item.setVisibility(View.VISIBLE);
                this.mBinding.btnRemoveWeft.setVisibility(View.VISIBLE);
                this.mBinding.btnAddWeft.setVisibility(View.GONE);
                return;
            default:
                return;
        }
    }

    public void updateUI() {
        this.mBinding.txtTitle.setText(Html.fromHtml("<u>" + getResources().getString(R.string.title) + "</u>"));
        this.mBinding.warp1.tvLabel.setText(getResources().getText(R.string.wrap) + " 1");
        this.mBinding.warp2.tvLabel.setText(getResources().getText(R.string.wrap) + " 2");
        this.mBinding.weft1.tvLabel.setText(getResources().getText(R.string.weft) + " 1");
        this.mBinding.weft2.tvLabel.setText(getResources().getText(R.string.weft) + " 2");
        this.mBinding.weft3.tvLabel.setText(getResources().getText(R.string.weft) + " 3");
        this.mBinding.weft4.tvLabel.setText(getResources().getText(R.string.weft) + " 4");
        this.mBinding.weft5.tvLabel.setText(getResources().getText(R.string.weft) + " 5");
        this.mBinding.weft6.tvLabel.setText(getResources().getText(R.string.weft) + " 6");
        this.mBinding.weft7.tvLabel.setText(getResources().getText(R.string.weft) + " 7");
        this.mBinding.weft8.tvLabel.setText(getResources().getText(R.string.weft) + " 8");
        this.mBinding.warp1.tvLabel1.setVisibility(View.VISIBLE);
        this.mBinding.warp1.tvLabel2.setVisibility(View.VISIBLE);
        this.mBinding.warp1.tvLabel3.setVisibility(View.VISIBLE);
        this.mBinding.weft1.tvLabel1.setVisibility(View.VISIBLE);
        this.mBinding.weft1.tvLabel2.setVisibility(View.VISIBLE);
        this.mBinding.weft1.tvLabel3.setVisibility(View.VISIBLE);
        this.mBinding.weft1.tvLabel1.setText(getResources().getText(R.string.pick));
        this.mBinding.itemMeter1.editView1.setVisibility(View.GONE);
        this.mBinding.itemMeter1.tvMeter.setVisibility(View.VISIBLE);
        this.mBinding.itemMeter2.editView1.setVisibility(View.VISIBLE);
        this.mBinding.itemMeter2.tvMeter.setVisibility(View.GONE);


        mBinding.itemMeter2.editView1.setFilters(new InputFilter[] {new InputFilter.LengthFilter(3)});
    }

    public void clearAll() {
        this.mBinding.warp1.editView1.setText("");
        this.mBinding.warp1.editView2.setText("");
        this.mBinding.warp1.editView3.setText("");
        this.mBinding.warp2.editView1.setText("");
        this.mBinding.warp2.editView2.setText("");
        this.mBinding.warp2.editView3.setText("");
        this.mBinding.weft1.editView1.setText("");
        this.mBinding.weft1.editView2.setText("");
        this.mBinding.weft1.editView3.setText("");
        this.mBinding.weft2.editView1.setText("");
        this.mBinding.weft2.editView2.setText("");
        this.mBinding.weft2.editView3.setText("");
        this.mBinding.weft3.editView1.setText("");
        this.mBinding.weft3.editView2.setText("");
        this.mBinding.weft3.editView3.setText("");
        this.mBinding.weft4.editView1.setText("");
        this.mBinding.weft4.editView2.setText("");
        this.mBinding.weft4.editView3.setText("");
        this.mBinding.weft5.editView1.setText("");
        this.mBinding.weft5.editView2.setText("");
        this.mBinding.weft5.editView3.setText("");
        this.mBinding.weft6.editView1.setText("");
        this.mBinding.weft6.editView2.setText("");
        this.mBinding.weft6.editView3.setText("");
        this.mBinding.weft7.editView1.setText("");
        this.mBinding.weft7.editView2.setText("");
        this.mBinding.weft7.editView3.setText("");
        this.mBinding.weft8.editView1.setText("");
        this.mBinding.weft8.editView2.setText("");
        this.mBinding.weft8.editView3.setText("");
        this.mBinding.itemPanoInch.editPano.setText("");
        this.mBinding.itemPanoInch.editJobCharge.setText("");
        this.mBinding.itemPanoInch.editWastage.setText("");
        this.mBinding.itemMeter1.tvMeter.setText("");
        this.mBinding.itemMeter1.tvJobCost1.setText("");
        this.mBinding.itemMeter1.tvYarnJob1.setText("");
        this.mBinding.itemMeter1.tvYarnCost1.setText("");
        this.mBinding.itemMeter2.editView1.setText("");
        this.mBinding.itemMeter2.tvJobCost1.setText("");
        this.mBinding.itemMeter2.tvYarnJob1.setText("");
        this.mBinding.itemMeter2.tvYarnCost1.setText("");
        this.mBinding.editCount.setText("");
        this.mBinding.editToDenier.setText("");
    }
}