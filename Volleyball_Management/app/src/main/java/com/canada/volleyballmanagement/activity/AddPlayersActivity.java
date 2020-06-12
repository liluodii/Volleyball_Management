package com.canada.volleyballmanagement.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.canada.volleyballmanagement.databinding.ActivityEditProfileBinding;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.EditProfileRequest;
import com.canada.volleyballmanagement.pojo.EventBusType;
import com.canada.volleyballmanagement.pojo.LoginResponse;
import com.canada.volleyballmanagement.utils.Constants;
import com.canada.volleyballmanagement.utils.ImageCompress;
import com.canada.volleyballmanagement.utils.ImageFilePath;
import com.google.gson.Gson;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.canada.volleyballmanagement.utils.MarshMallowPermission.CAMERA_CODE;
import static com.canada.volleyballmanagement.utils.MarshMallowPermission.READ_EXTERNAL_STORAGE_CODE;
import static com.canada.volleyballmanagement.utils.MarshMallowPermission.WRITE_EXTERNAL_STORAGE_CODE;

public class AddPlayersActivity extends BaseActivity {

    ActivityAddPlayersBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_players);
        binding.setActivity(this);
        showToolBar(true, getResources().getString(R.string.text_add_player));
        init();
    }

    public void init() {
        binding.rbMale.setChecked(true);
        binding.edJoinDate.setKeyListener(null);
        binding.edDob.setKeyListener(null);
    }

    public void onClock(View view) {
        switch (view.getId()) {
            case R.id.edDob:
                break;
            case R.id.btnDone:
                break;
        }
    }


}
