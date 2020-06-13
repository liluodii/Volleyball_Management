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
import com.canada.volleyballmanagement.databinding.ActivityAddTeamManagerBinding;
import com.canada.volleyballmanagement.pojo.AddPlayerRequest;
import com.canada.volleyballmanagement.pojo.AddTeamManagerRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.EditPlayerResponse;
import com.canada.volleyballmanagement.pojo.EditTeamManagerResponse;
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

public class AddTeamManagerActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {

    ActivityAddTeamManagerBinding binding;
    boolean isCamera = false;
    boolean isJoinDate = false;
    String strProfilePic = "";
    int UserId = 0;
    boolean isEdit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_team_manager);
        binding.setActivity(this);
        showToolBar(true, getResources().getString(R.string.text_add_team_manager));
        init();
    }

    public void init() {

        binding.rbMale.setChecked(true);
        binding.edJoinDate.setKeyListener(null);
        binding.edDob.setKeyListener(null);
        isEdit = getIntent().getBooleanExtra(Constants.isEditPlayer, false);

        if (isEdit) {
            callApiForEditTeam(getIntent().getIntExtra(Constants.playerId, 0));
        }

    }

    public void callApiForEditTeam(int id) {
        showDialog();
        requestAPI.GetTeamManagerDetails(id).enqueue(GetTeamCallback);
    }

    Callback<EditTeamManagerResponse> GetTeamCallback = new Callback<EditTeamManagerResponse>() {
        @Override
        public void onResponse(Call<EditTeamManagerResponse> call, Response<EditTeamManagerResponse> response) {

            dismissDialog();

            EditTeamManagerResponse teamManagerResponse = response.body();

            if (teamManagerResponse.getReturnCode().equals("1")) {

                if (!teamManagerResponse.getData().getProfilePic().isEmpty()) {

                    RequestOptions options = new RequestOptions();
                    options.placeholder(getActivity().getResources().getDrawable(R.drawable.ic_person));
                    options.error(getActivity().getResources().getDrawable(R.drawable.ic_person));

                    Glide.with(getActivity()).setDefaultRequestOptions(options)
                            .load(teamManagerResponse.getData().getProfilePic()).into(binding.imgProfile);

                } else {
                    binding.imgProfile.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_person));
                }

                binding.edFirstName.setText(bindView(teamManagerResponse.getData().getFirstName()));
                binding.edLastName.setText(bindView(teamManagerResponse.getData().getLastName()));
                binding.edEmail.setText(teamManagerResponse.getData().getEmailID());
                binding.edDob.setText(teamManagerResponse.getData().getDOB());
                binding.edContact.setText(bindView(teamManagerResponse.getData().getContact()));
                binding.edJoinDate.setText(teamManagerResponse.getData().getJoinDate());
                binding.edAddress.setText(bindView(teamManagerResponse.getData().getAddress()));

                if (teamManagerResponse.getData().getGender().equals("male")) {
                    binding.rbMale.setChecked(true);
                } else if (teamManagerResponse.getData().getGender().equals("female")) {
                    binding.rbFemale.setChecked(true);
                }

                UserId = teamManagerResponse.getData().getUserID();

            } else {

                Toast.makeText(getActivity(), "" + teamManagerResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onFailure(Call<EditTeamManagerResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgProfile:
                selectedUserDialog();
                break;
            case R.id.edDob:
                isJoinDate = false;
                calender("Dob");
                break;
            case R.id.edJoinDate:
                isJoinDate = true;
                calender("Dob");
                break;
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

        String strFirstName = returnText(binding.edFirstName);
        String strLastName = returnText(binding.edLastName);
        String strEmail = returnText(binding.edEmail);
        String strDate = returnText(binding.edDob);
        String strContact = returnText(binding.edContact);
        String strJoinDate = returnText(binding.edJoinDate);
        String strAddress = returnText(binding.edAddress);

        if (strProfilePic.isEmpty() && !isEdit) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_select_image), Toast.LENGTH_SHORT).show();
        } else if (strFirstName.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_first_name), Toast.LENGTH_SHORT).show();
        } else if (strFirstName.toString().length() < 2) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_valid_first_name), Toast.LENGTH_SHORT).show();
        } else if (strLastName.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_last_name), Toast.LENGTH_SHORT).show();
        } else if (strLastName.toString().length() < 2) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_valid_last_name), Toast.LENGTH_SHORT).show();
        } else if (strEmail.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_email), Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_valid_email), Toast.LENGTH_SHORT).show();
        } else if (strDate.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_select_dob), Toast.LENGTH_SHORT).show();
        } else if (strContact.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_Please_empty_contact), Toast.LENGTH_SHORT).show();
        } else if (strContact.toString().length() < 10) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_valid_contact), Toast.LENGTH_SHORT).show();
        } else if (strJoinDate.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_select_join_date), Toast.LENGTH_SHORT).show();
        } else if (strAddress.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_address), Toast.LENGTH_SHORT).show();
        } else if (strAddress.toString().length() < 2) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_valid_address), Toast.LENGTH_SHORT).show();
        }

        return isValidation;
    }

    public void callApi() {
        showDialog();
        AddTeamManagerRequest request = new AddTeamManagerRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setUserID(UserId);
        request.setFirstName(returnText(binding.edFirstName));
        request.setLastName(returnText(binding.edLastName));
        request.setEmailID(returnText(binding.edEmail));
        request.setDOB(returnText(binding.edDob));
        request.setContact(returnText(binding.edContact));
        request.setJoinDate(returnText(binding.edJoinDate));
        request.setAddress(returnText(binding.edAddress));

        if (binding.rbMale.isChecked()) {
            request.setGender(getResources().getString(R.string.text_male).toLowerCase());
        } else if (binding.rbFemale.isChecked()) {
            request.setGender(getResources().getString(R.string.text_female).toLowerCase());
        }

        Log.e("callApi: ", "" + new Gson().toJson(request));

        requestAPI.AddEditTeamManager(request).enqueue(AddEditTeamCallback);

    }

    Callback<CommonResponse> AddEditTeamCallback = new Callback<CommonResponse>() {
        @Override
        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

            CommonResponse commonresponse = response.body();

            if (commonresponse.getReturnCode().equals("1")) {

                if (strProfilePic.isEmpty()) {
                    EventBus.getDefault().post(new EventBusType(3));
                    dismissDialog();
                    finish();
                } else {
                    callApiForProfilePic(strProfilePic, commonresponse.getReturnValue());
                }

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

    public void calender(String tag) {

        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, -18);

        DatePickerDialog dpd = DatePickerDialog.newInstance(
                (DatePickerDialog.OnDateSetListener) getActivity(),
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAccentColor(getResources().getColor(R.color.colorPrimary));
        dpd.show(getFragmentManager(), tag);

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String strDOB = "" + (monthOfYear + 1) + "-" + dayOfMonth + "-" + year;
        if (isJoinDate) {
            binding.edJoinDate.setText("" + strDOB);
        } else {
            binding.edDob.setText("" + strDOB);
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

                        strProfilePic = ImageFilePath.getPath(
                                getActivity(), i);

                        strProfilePic = new ImageCompress(getActivity()).compressImage(strProfilePic);

                        Glide.with(getActivity())
                                .load(strProfilePic).into(binding.imgProfile);


                    } catch (Exception e) {

                    }

                    break;
                case REQUEST_CODE_TAKE_PICTURE:

                    strProfilePic = new ImageCompress(getActivity()).compressImage(mFileTemp.getPath());

                    pref.deleteImage(mFileTemp.getPath());

                    Glide.with(getActivity())
                            .load(strProfilePic).into(binding.imgProfile);

                    break;

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void callApiForProfilePic(String strProfile, String userId) {
        RequestBody APIKey = RequestBody.create(MediaType.parse("text/plain"), Constants.APIKEY);
        RequestBody UserID = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody ProfilePic = RequestBody.create(MediaType.parse("*/*"), new File(strProfile));
        requestAPI.UpdateProfilePic(APIKey, UserID, ProfilePic).enqueue(ProfilePicCallback);
    }


    Callback<CommonResponse> ProfilePicCallback = new Callback<CommonResponse>() {

        @Override
        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
            dismissDialog();
            CommonResponse commonresponse = response.body();
            if (commonresponse.getReturnCode().equals("1")) {
                EventBus.getDefault().post(new EventBusType(3));
                finish();
            } else {
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
