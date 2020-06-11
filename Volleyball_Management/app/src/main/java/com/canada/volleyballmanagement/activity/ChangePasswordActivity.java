package com.canada.volleyballmanagement.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityChangePasswordBinding;
import com.canada.volleyballmanagement.pojo.ChangePasswordRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends BaseActivity {

    ActivityChangePasswordBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
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
        String strOldPassword = returnText(binding.edOldPassword);
        String strPassword = returnText(binding.edNewPassword);
        String strConfirm = returnText(binding.edConfirmPassword);

        if (strOldPassword.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_old_password), Toast.LENGTH_SHORT).show();
        } else if (strPassword.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_pass), Toast.LENGTH_SHORT).show();
        }  else if (strConfirm.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_confirm_pass), Toast.LENGTH_SHORT).show();
        } else if (!strPassword.equals(strConfirm)) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_pass_not_match), Toast.LENGTH_SHORT).show();
        }

        return isValidation;
    }

    public void callApi() {
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setaPIKey(Constants.APIKEY);
        request.setUserID(getUserID());
        request.setOldPass(returnText(binding.edOldPassword));
        request.setNewPass(returnText(binding.edNewPassword));
        requestAPI.ChangePasswordRequest(request).enqueue(ChangePasswordCallback);
        showDialog();
    }

    Callback<CommonResponse> ChangePasswordCallback = new Callback<CommonResponse>() {
        @Override
        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
            dismissDialog();
            CommonResponse commonResponse = response.body();
            Toast.makeText(getActivity(), "" + commonResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();
            if (commonResponse.getReturnCode().equals("1")) {
                finish();
            }
        }

        @Override
        public void onFailure(Call<CommonResponse> call, Throwable t) {
            dismissDialog();
        }

    };


}
