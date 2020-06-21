package com.canada.volleyballmanagement.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityAddPlayersBinding;
import com.canada.volleyballmanagement.databinding.ActivityAddTournamentBinding;
import com.canada.volleyballmanagement.pojo.AddEditTournamentRequest;
import com.canada.volleyballmanagement.pojo.AddPlayerRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.EditPlayerResponse;
import com.canada.volleyballmanagement.pojo.EventBusType;
import com.canada.volleyballmanagement.utils.Constants;
import com.canada.volleyballmanagement.utils.ImageCompress;
import com.canada.volleyballmanagement.utils.ImageFilePath;
import com.google.gson.Gson;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.canada.volleyballmanagement.utils.MarshMallowPermission.CAMERA_CODE;
import static com.canada.volleyballmanagement.utils.MarshMallowPermission.READ_EXTERNAL_STORAGE_CODE;
import static com.canada.volleyballmanagement.utils.MarshMallowPermission.WRITE_EXTERNAL_STORAGE_CODE;

public class AddTournamentActivity extends BaseActivity {

    ActivityAddTournamentBinding binding;
    int TournamentId = 0;
    boolean isEdit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_tournament);
        binding.setActivity(this);
        showToolBar(true, getResources().getString(R.string.text_add_season));
        init();
    }

    public void init() {

        isEdit = getIntent().getBooleanExtra(Constants.isEditPlayer, false);

        if (isEdit) {
            TournamentId = getIntent().getIntExtra(Constants.TeamId, 0);
            binding.edName.setText(bindView(getIntent().getStringExtra(Constants.Name)));
        }

    }

    public void onClick(View view) {
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

        String strName = returnText(binding.edName);

        if (strName.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_name), Toast.LENGTH_SHORT).show();
        } else if (strName.toString().length() < 2) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_valid_name), Toast.LENGTH_SHORT).show();
        }

        return isValidation;
    }

    public void callApi() {
        showDialog();
        AddEditTournamentRequest request = new AddEditTournamentRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setUserID(getUserID());
        request.setTournamentID(TournamentId);
        request.setName(returnText(binding.edName));

        Log.e("callApi: ", "" + new Gson().toJson(request));

        requestAPI.AddEditTournamet(request).enqueue(AddEditTournamentCallback);

    }

    Callback<CommonResponse> AddEditTournamentCallback = new Callback<CommonResponse>() {
        @Override
        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

            CommonResponse commonresponse = response.body();

            if (commonresponse.getReturnCode().equals("1")) {

                EventBus.getDefault().post(new EventBusType(4));
                dismissDialog();
                finish();

            } else {
                dismissDialog();
                Toast.makeText(getActivity(), "" + commonresponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onFailure(Call<CommonResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };


}
