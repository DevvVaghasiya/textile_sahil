package com.example.febriccostcalculator;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.febriccostcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static int itemWarp = 1;
    public static int itemWeft = 4;
    public DrawerLayout drawerLayout;
    double jobCost = -1.0d;
    ActivityMainBinding mBinding;
    int sumOfTotalPick;
    double warpCost1;
    double warpCost2;
    double warpWeight1;
    double warpWeight2;
    double weftCost1;
    double weftCost2;
    double weftCost3;
    double weftCost4;
    double weftCost5;
    double weftCost6;
    double weftCost7;
    double weftCost8;
    double weftWeight1;
    double weftWeight2;
    double weftWeight3;
    double weftWeight4;
    double weftWeight5;
    double weftWeight6;
    double weftWeight7;
    double weftWeight8;
    double yarnCost = -1.0d;
    double yarnJob = -1.0d;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding inflate = ActivityMainBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        updateUI();
        clickEvent();
    }

    /* access modifiers changed from: package-private */
    public void clickEvent() {
        this.mBinding.itemMeter2.editView1.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                if (!MainActivity.this.mBinding.itemMeter2.editView1.getText().toString().equals("")) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.getAnswer((double) Integer.parseInt(mainActivity.mBinding.itemMeter2.editView1.getText().toString()));
                }
            }
        });
        this.mBinding.editCount.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                if (!MainActivity.this.mBinding.editCount.getText().toString().equals("")) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.getDenier((double) Integer.parseInt(mainActivity.mBinding.editCount.getText().toString()));
                }
            }
        });
        this.mBinding.btnAddWrap.setOnClickListener(new MainActivity$$ExternalSyntheticLambda0(this));
        this.mBinding.btnAddWeft.setOnClickListener(new MainActivity$$ExternalSyntheticLambda1(this));
        this.mBinding.btnRemoveWeft.setOnClickListener(new MainActivity$$ExternalSyntheticLambda2(this));
        this.mBinding.btnCalculate.setOnClickListener(new MainActivity$$ExternalSyntheticLambda3(this));
        this.mBinding.btnClearAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.clearAll();
            }
        });
        this.mBinding.btnRate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.Rate_Dialog();
            }
        });
        this.mBinding.btnShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent sharingIntent = new Intent("android.intent.action.SEND");
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra("android.intent.extra.TEXT", String.format("try this amazing Fabric Costing Calculator application. Click on the link to download now http://play.google.com/store/apps/details?id=%s", new Object[]{Integer.valueOf(R.string.app_name), MainActivity.this.getPackageName()}));
                MainActivity.this.startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$clickEvent$0$com-example-febriccostcalculator-MainActivity  reason: not valid java name */
    public /* synthetic */ void m2lambda$clickEvent$0$comexamplefebriccostcalculatorMainActivity(View view) {
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

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$clickEvent$1$com-example-febriccostcalculator-MainActivity  reason: not valid java name */
    public /* synthetic */ void m3lambda$clickEvent$1$comexamplefebriccostcalculatorMainActivity(View view) {
        int i = itemWeft;
        if (i != 8) {
            itemWeft = i + 1;
            addRemoveWeftItem();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$clickEvent$2$com-example-febriccostcalculator-MainActivity  reason: not valid java name */
    public /* synthetic */ void m4lambda$clickEvent$2$comexamplefebriccostcalculatorMainActivity(View view) {
        itemWeft--;
        addRemoveWeftItem();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$clickEvent$3$com-example-febriccostcalculator-MainActivity  reason: not valid java name */
    public /* synthetic */ void m5lambda$clickEvent$3$comexamplefebriccostcalculatorMainActivity(View view) {
        calculation1();
    }

    /* access modifiers changed from: private */
    public void Rate_Dialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(17170445);
        dialog.setContentView(R.layout.view_rate_us);
        ((TextView) dialog.findViewById(R.id.btn_letter)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda6(dialog));
        ((TextView) dialog.findViewById(R.id.btn_rate_now)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda7(this, dialog));
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$Rate_Dialog$5$com-example-febriccostcalculator-MainActivity  reason: not valid java name */
    public /* synthetic */ void m0lambda$Rate_Dialog$5$comexamplefebriccostcalculatorMainActivity(Dialog dialog, View v) {
        rateOption();
        dialog.dismiss();
    }

    public void rateOption() {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
    }

    /* access modifiers changed from: package-private */
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

    /* access modifiers changed from: package-private */
    public void addRemoveWarpItem() {
        if (itemWarp == 2) {
            this.mBinding.warp2.item.setVisibility(0);
        } else {
            this.mBinding.warp2.item.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    public void addRemoveWeftItem() {
        switch (itemWeft) {
            case 4:
                this.mBinding.weft5.item.setVisibility(8);
                this.mBinding.weft6.item.setVisibility(8);
                this.mBinding.weft7.item.setVisibility(8);
                this.mBinding.weft8.item.setVisibility(8);
                this.mBinding.btnRemoveWeft.setVisibility(8);
                this.mBinding.btnAddWeft.setVisibility(0);
                return;
            case 5:
                this.mBinding.weft5.item.setVisibility(0);
                this.mBinding.weft6.item.setVisibility(8);
                this.mBinding.weft7.item.setVisibility(8);
                this.mBinding.weft8.item.setVisibility(8);
                this.mBinding.btnRemoveWeft.setVisibility(0);
                this.mBinding.btnAddWeft.setVisibility(0);
                return;
            case 6:
                this.mBinding.weft5.item.setVisibility(0);
                this.mBinding.weft6.item.setVisibility(0);
                this.mBinding.weft7.item.setVisibility(8);
                this.mBinding.weft8.item.setVisibility(8);
                this.mBinding.btnRemoveWeft.setVisibility(0);
                this.mBinding.btnAddWeft.setVisibility(0);
                return;
            case 7:
                this.mBinding.weft5.item.setVisibility(0);
                this.mBinding.weft6.item.setVisibility(0);
                this.mBinding.weft7.item.setVisibility(0);
                this.mBinding.weft8.item.setVisibility(8);
                this.mBinding.btnRemoveWeft.setVisibility(0);
                this.mBinding.btnAddWeft.setVisibility(0);
                return;
            case 8:
                this.mBinding.weft5.item.setVisibility(0);
                this.mBinding.weft6.item.setVisibility(0);
                this.mBinding.weft7.item.setVisibility(0);
                this.mBinding.weft8.item.setVisibility(0);
                this.mBinding.btnRemoveWeft.setVisibility(0);
                this.mBinding.btnAddWeft.setVisibility(8);
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
        this.mBinding.warp1.tvLabel1.setVisibility(0);
        this.mBinding.warp1.tvLabel2.setVisibility(0);
        this.mBinding.warp1.tvLabel3.setVisibility(0);
        this.mBinding.weft1.tvLabel1.setVisibility(0);
        this.mBinding.weft1.tvLabel2.setVisibility(0);
        this.mBinding.weft1.tvLabel3.setVisibility(0);
        this.mBinding.weft1.tvLabel1.setText(getResources().getText(R.string.pick));
        this.mBinding.itemMeter1.editView1.setVisibility(8);
        this.mBinding.itemMeter1.tvMeter.setVisibility(0);
        this.mBinding.itemMeter2.editView1.setVisibility(0);
        this.mBinding.itemMeter2.tvMeter.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        addRemoveWeftItem();
        addRemoveWarpItem();
    }

    /* access modifiers changed from: package-private */
    public void calculation1() {
        this.warpWeight1 = step1(this.mBinding.warp1.editView1, this.mBinding.warp1.editView2, this.mBinding.itemPanoInch.editPano, 1);
        this.warpWeight2 = step1(this.mBinding.warp2.editView1, this.mBinding.warp2.editView2, this.mBinding.itemPanoInch.editPano, 2);
        calculation2();
    }

    /* access modifiers changed from: package-private */
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

    /* access modifiers changed from: package-private */
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

    /* access modifiers changed from: package-private */
    public void calculation4() {
        this.yarnCost = step4();
        calculation5();
    }

    /* access modifiers changed from: package-private */
    public void calculation5() {
        this.sumOfTotalPick = step5();
        calculation6();
    }

    /* access modifiers changed from: package-private */
    public void calculation6() {
        this.jobCost = step6(this.mBinding.itemPanoInch.editJobCharge);
        calculation7();
    }

    /* access modifiers changed from: package-private */
    public void calculation7() {
        this.yarnJob = step7();
        setAnswers();
    }

    /* access modifiers changed from: package-private */
    public void setAnswers() {
        if (!this.mBinding.itemMeter2.editView1.getText().toString().equals("")) {
            getAnswer((double) Integer.parseInt(this.mBinding.itemMeter2.editView1.getText().toString()));
        }
        this.mBinding.itemMeter1.tvMeter.setText("1");
        this.mBinding.itemMeter1.tvYarnCost1.setText("" + this.yarnCost);
        this.mBinding.itemMeter1.tvJobCost1.setText("" + this.jobCost);
        this.mBinding.itemMeter1.tvYarnJob1.setText("" + this.yarnJob);
    }

    public double step1(EditText editRead, EditText editDenier, EditText editPano, int flag) {
        int read = 0;
        int denier = 0;
        int pano = 0;
        if (!editRead.getText().toString().equals("")) {
            read = Integer.parseInt(editRead.getText().toString());
        }
        if (!editDenier.getText().toString().equals("")) {
            denier = Integer.parseInt(editDenier.getText().toString());
        }
        if (!editPano.getText().toString().equals("")) {
            pano = Integer.parseInt(editPano.getText().toString());
        }
        return (((double) denier) * ((double) ((read * pano) + 112))) / 9000.0d;
    }

    public double step2(EditText editRead, EditText editDenier, EditText editPano, int flag) {
        int read = 0;
        int pano = 0;
        int denier = 0;
        if (!editRead.getText().toString().equals("")) {
            read = Integer.parseInt(editRead.getText().toString());
        }
        if (!editPano.getText().toString().equals("")) {
            pano = Integer.parseInt(editPano.getText().toString());
        }
        if (!editDenier.getText().toString().equals("")) {
            denier = Integer.parseInt(editDenier.getText().toString());
        }
        return ((double) ((read * denier) * pano)) / 9000.0d;
    }

    public double step3(EditText editRate, double weight, int flag) {
        int rate = 0;
        if (!editRate.getText().toString().equals("")) {
            rate = Integer.parseInt(editRate.getText().toString());
        }
        return (((double) rate) * weight) / 1000.0d;
    }

    public double step4() {
        double sum = this.warpCost1 + this.warpCost2 + this.weftCost1 + this.weftCost2 + this.weftCost3 + this.weftCost4 + this.weftCost5 + this.weftCost6 + this.weftCost7 + this.weftCost8;
        double wastage = 0.0d;
        if (!this.mBinding.itemPanoInch.editWastage.getText().toString().equals("")) {
            wastage = Double.parseDouble(this.mBinding.itemPanoInch.editWastage.getText().toString());
        }
        return (((3.0d + wastage) * sum) / 100.0d) + sum;
    }

    public int step5() {
        int pick1 = 0;
        int pick2 = 0;
        int pick3 = 0;
        int pick4 = 0;
        int pick5 = 0;
        int pick6 = 0;
        int pick7 = 0;
        int pick8 = 0;
        if (!this.mBinding.weft1.editView1.getText().toString().equals("")) {
            pick1 = Integer.parseInt(this.mBinding.weft1.editView1.getText().toString());
        }
        if (!this.mBinding.weft2.editView1.getText().toString().equals("")) {
            pick2 = Integer.parseInt(this.mBinding.weft2.editView1.getText().toString());
        }
        if (!this.mBinding.weft3.editView1.getText().toString().equals("")) {
            pick3 = Integer.parseInt(this.mBinding.weft3.editView1.getText().toString());
        }
        if (!this.mBinding.weft4.editView1.getText().toString().equals("")) {
            pick4 = Integer.parseInt(this.mBinding.weft4.editView1.getText().toString());
        }
        if (!this.mBinding.weft5.editView1.getText().toString().equals("")) {
            pick5 = Integer.parseInt(this.mBinding.weft5.editView1.getText().toString());
        }
        if (!this.mBinding.weft6.editView1.getText().toString().equals("")) {
            pick6 = Integer.parseInt(this.mBinding.weft6.editView1.getText().toString());
        }
        if (!this.mBinding.weft7.editView1.getText().toString().equals("")) {
            pick7 = Integer.parseInt(this.mBinding.weft7.editView1.getText().toString());
        }
        if (!this.mBinding.weft8.editView1.getText().toString().equals("")) {
            pick8 = Integer.parseInt(this.mBinding.weft8.editView1.getText().toString());
        }
        return pick1 + pick2 + pick3 + pick4 + pick5 + pick6 + pick7 + pick8;
    }

    public double step6(EditText editJobCharge) {
        int jobCharge = 0;
        if (!editJobCharge.getText().toString().equals("")) {
            jobCharge = Integer.parseInt(editJobCharge.getText().toString());
        }
        return (((double) this.sumOfTotalPick) * ((double) jobCharge)) / 100.0d;
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
        finishAffinity();
        super.onBackPressed();
    }

    private void ShowExitDialog(Context context) {
        Dialog dialogExit = new Dialog(context);
        dialogExit.setContentView(R.layout.view_exit_dialog);
        dialogExit.getWindow().clearFlags(131080);
        dialogExit.getWindow().setBackgroundDrawableResource(17170445);
        Window window = dialogExit.getWindow();
        window.setLayout(-1, -2);
        window.setGravity(17);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogExit.getWindow().getAttributes());
        lp.width = (int) (((double) Resources.getSystem().getDisplayMetrics().widthPixels) / 1.2d);
        lp.dimAmount = 0.7f;
        lp.flags = 2;
        dialogExit.getWindow().setAttributes(lp);
        dialogExit.show();
        ((TextView) dialogExit.findViewById(R.id.btn_no)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda4(dialogExit));
        ((TextView) dialogExit.findViewById(R.id.btn_yes)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda5(this, dialogExit));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$ShowExitDialog$7$com-example-febriccostcalculator-MainActivity  reason: not valid java name */
    public /* synthetic */ void m1lambda$ShowExitDialog$7$comexamplefebriccostcalculatorMainActivity(Dialog dialogExit, View view) {
        dialogExit.dismiss();
        finishAffinity();
    }
}
