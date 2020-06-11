package com.canada.volleyballmanagement.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity {

    private static int SPASH_SCREEN = 5000;

    Animation topanim, bottomanim;
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        //Animation
        topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        binding.imgLogo.setAnimation(topanim);
        binding.txtLogo.setAnimation(bottomanim);
        binding.txtSlogan.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isLogin()) {

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    finishAffinity();

                } else {

                    Intent intent = new Intent(getActivity(), LogInActivity.class);

                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(binding.imgLogo, "logo_image");
                    pairs[1] = new Pair<View, String>(binding.txtLogo, "logo_text");

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs);
                    startActivity(intent, options.toBundle());
                    finishAffinity();

                }


            }

        }, SPASH_SCREEN);


    }
}
