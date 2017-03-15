package com.example.kooryy2.cleanmaster.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.Fragment.FragmentPrivacyPolicy;
import com.example.kooryy2.cleanmaster.R;
import com.example.kooryy2.cleanmaster.Utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.app_name)
    TextView app_name;

    @BindView(R.id.btnKhamPha)
    Button btnKhamPha;

    @BindView(R.id.layoutanim)
    LinearLayout tvAnimation;

    private SharedPreferences sharedPreferences;
    private Animation animation;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        app_name.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/aguda_bold.otf"));
        sharedPreferences = SplashActivity.this.getSharedPreferences("SplashPreference", MODE_PRIVATE);
        initAnim();

        btnKhamPha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("start", true);
                editor.apply();
            }
        });

        boolean firstTime = sharedPreferences.getBoolean("start", false);
        if (firstTime) {
            btnKhamPha.setVisibility(View.GONE);
            setListener();
        }
    }

    private void initAnim() {
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_spash_move_up);
        tvAnimation.startAnimation(animation);
    }

    private void setListener() {
        this.animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
                Log.v("LOGANIM", String.valueOf(System.currentTimeMillis()));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @OnClick(R.id.tv_policy)
    void goPrivacy() {
        FragmentUtils.pushFragment(this, FragmentPrivacyPolicy.newInstance(), null);
    }
}
