package com.example.febriccostcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_splash);
        ((LinearLayout) findViewById(R.id.llAnim)).startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_anim));
        new Handler().postDelayed(new SplashActivity$$ExternalSyntheticLambda0(this), 3000);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-example-febriccostcalculator-SplashActivity  reason: not valid java name */
    public /* synthetic */ void m6lambda$onCreate$0$comexamplefebriccostcalculatorSplashActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
