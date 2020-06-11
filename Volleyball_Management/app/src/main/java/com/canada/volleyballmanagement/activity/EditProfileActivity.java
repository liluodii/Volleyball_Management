package com.canada.volleyballmanagement.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
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
import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityEditProfileBinding;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.EventBusType;
import com.canada.volleyballmanagement.pojo.LoginResponse;
import com.canada.volleyballmanagement.utils.Constants;
import com.canada.volleyballmanagement.utils.ImageCompress;
import com.canada.volleyballmanagement.utils.ImageFilePath;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.canada.volleyballmanagement.utils.MarshMallowPermission.CAMERA_CODE;
import static com.canada.volleyballmanagement.utils.MarshMallowPermission.READ_EXTERNAL_STORAGE_CODE;
import static com.canada.volleyballmanagement.utils.MarshMallowPermission.WRITE_EXTERNAL_STORAGE_CODE;

public class EditProfileActivity extends BaseActivity {

    ActivityEditProfileBinding binding;
    boolean isCamera = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        binding.setActivity(this);
    }

    public void onClock(View view) {
        switch (view.getId()) {
            case R.id.btnChangePassword:
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
                break;
        }
    }


    public void selectedUserDialog() {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_camera, null);

        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();

        dialog.setCancelable(false);

        dialog.getWindow().setGravity(Gravity.BOTTOM);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        final TextView txtCamera = v.findViewById(R.id.txtCamera);
        final TextView txtGallery = v.findViewById(R.id.txtGallery);
        final TextView txtCancel = v.findViewById(R.id.txtCancel);

        txtCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCamera = true;
                takePicture();
                dialog.dismiss();
            }
        });

        txtGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCamera = false;
                openGallery();
                dialog.dismiss();
            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
                break;
            case WRITE_EXTERNAL_STORAGE_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (isCamera)
                        takePicture();
                    else
                        openGallery();
                }
                break;
            case CAMERA_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                }
                break;

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode != getActivity().RESULT_OK) {
                return;
            }

            switch (requestCode) {
                case REQUEST_CODE_GALLERY:

                    try {
                        Uri i = data.getData();

                        String strProfilePic = ImageFilePath.getPath(
                                getActivity(), i);
                        strProfilePic = new ImageCompress(getActivity()).compressImage(strProfilePic);

                        Glide.with(getActivity())
                                .load(strProfilePic).into(binding.imgProfile);

                        callApiForProfilePic(strProfilePic);

                    } catch (Exception e) {

                    }

                    break;
                case REQUEST_CODE_TAKE_PICTURE:

                    String strProfilePic = new ImageCompress(getActivity()).compressImage(mFileTemp.getPath());
                    pref.deleteImage(mFileTemp.getPath());

                    Glide.with(getActivity())
                            .load(strProfilePic).into(binding.imgProfile);

                    callApiForProfilePic(strProfilePic);

                    break;

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    public void callApiForProfilePic(String strProfile) {
        RequestBody APIKey = RequestBody.create(MediaType.parse("text/plain"), Constants.APIKEY);
        RequestBody UserID = RequestBody.create(MediaType.parse("text/plain"), "" + getLoginResponse().getData().getUserID());
        RequestBody ProfilePic = RequestBody.create(MediaType.parse("*/*"), new File(strProfile));
        requestAPI.UpdateProfilePic(APIKey, UserID, ProfilePic).enqueue(ProfilePicCallback);
        showDialog();
    }


    Callback<CommonResponse> ProfilePicCallback = new Callback<CommonResponse>() {

        @Override
        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

            dismissDialog();

            CommonResponse commonResponse = response.body();

            Toast.makeText(getActivity(), "" + commonResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

            if (commonResponse.getReturnCode().equals("1")) {

                LoginResponse contributorResponse = getLoginResponse();
                contributorResponse.getData().setProfilePic(commonResponse.getReturnValue());
                pref.putString(Constants.LOGIN_REPONSE, new Gson().toJson(contributorResponse));

                EventBus.getDefault().post(new EventBusType(1));

            }

        }

        @Override
        public void onFailure(Call<CommonResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }


    };

}
