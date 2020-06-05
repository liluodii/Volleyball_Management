package com.canada.volleyballmanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityLoginBinding;
import com.canada.volleyballmanagement.pojo.LoginRequest;
import com.canada.volleyballmanagement.pojo.LoginResponse;
import com.canada.volleyballmanagement.utils.Constants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LogInActivity extends BaseActivity {


    Callback<LoginResponse> LoginCallback = new Callback<LoginResponse>() {
        @Override
        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

            dismissDialog();

            LoginResponse loginResponse = response.body();

            if (loginResponse.getReturnCode().equals("1")) {

                pref.putBoolean(Constants.ISLOGIN, true);
                pref.putString(Constants.LOGIN_REPONSE, new Gson().toJson(loginResponse));

                Toast.makeText(getActivity(), "" + loginResponse.getData().getFirstName() + " " + loginResponse.getData().getLastName(), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getActivity(), "" + loginResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onFailure(Call<LoginResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setActivity(this);
    }

    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.txtForgotPassword:
                 intent = new Intent(getActivity(), ForgotPasswordActivity.class);
                startActivity(intent);
                break;

            case R.id.btnSignIn:

                intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

//                if (checkConnection()) {
//                    if (validate()) {
//                        hideKeyboard();
//                        callApi();
//                    }
//                } else {
//                    showNoInternetDialog();
//                }

                break;
        }

    }

    private boolean validate() {

        boolean isValidation = true;

        String strEmail = returnText(binding.edEmail);

        String strPassword = returnText(binding.edPassword);

        if (strEmail.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_email), Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_valid_email), Toast.LENGTH_SHORT).show();
        } else if (strPassword.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_pass), Toast.LENGTH_SHORT).show();
        }

        return isValidation;
    }

    public void callApi() {
        showDialog();
        LoginRequest request = new LoginRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setEmailID(returnText(binding.edEmail));
        request.setPassword(returnText(binding.edPassword));
        requestAPI.Login(request).enqueue(LoginCallback);
        Log.e("callApi: ", "" + new Gson().toJson(request));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
