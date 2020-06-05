package com.canada.volleyballmanagement.activity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityForgotPasswordBinding;
import com.canada.volleyballmanagement.pojo.ForgotPasswordRequest;
import com.canada.volleyballmanagement.pojo.ForgotPasswordResponse;
import com.canada.volleyballmanagement.utils.Constants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity {

    ActivityForgotPasswordBinding binding;

    Callback<ForgotPasswordResponse> LoginCallback = new Callback<ForgotPasswordResponse>() {
        @Override
        public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {

            dismissDialog();

            ForgotPasswordResponse loginResponse = response.body();

            Toast.makeText(getActivity(), "" + loginResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };

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
                if (checkConnection()) {
                    if (validate()) {
                        hideKeyboard();
                        callApi();
                    }
                } else {
                    showNoInternetDialog();
                }
                break;
        }
    }

    private boolean validate() {

        boolean isValidation = true;

        String strEmail = returnText(binding.edEmail);

        if (strEmail.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_email), Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_valid_email), Toast.LENGTH_SHORT).show();
        }

        return isValidation;
    }

    public void callApi() {
        showDialog();
        ForgotPasswordRequest request = new ForgotPasswordRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setEmail(returnText(binding.edEmail));
        requestAPI.ForgetPassword(request).enqueue(LoginCallback);
        Log.e("callApi: ", "" + new Gson().toJson(request));
    }

}
