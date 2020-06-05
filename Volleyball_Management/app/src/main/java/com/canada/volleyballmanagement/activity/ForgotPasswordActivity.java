package com.canada.volleyballmanagement.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends BaseActivity {

    ActivityForgotPasswordBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        binding.setActivity(this);

        Animation topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        binding.imgLogo.setAnimation(topanim);
        binding.txtLogo.setAnimation(topanim);

    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDone:
                break;
        }
    }


}
