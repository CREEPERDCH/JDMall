package com.creeperdch.jdmall.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import com.creeperdch.jdmall.R;
import com.creeperdch.jdmall.utils.ActivityUtil;

public class SplashActivity extends BaseActivity {

    private ImageView mLogoIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initSplashAnim();
    }

    @Override
    protected void initView() {
        mLogoIv = (ImageView) findViewById(R.id.logo_iv);
    }

    private void initSplashAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLogoIv, "alpha", 0.1f, 1.0f);
        animator.setDuration(2000);
        //跳转到登录界面
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ActivityUtil.startNew(SplashActivity.this, LoginActivity.class, true);
            }
        });
        animator.start();
    }
}
