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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.canada.volleyballmanagement.databinding.ActivityAddTeamBinding;
import com.canada.volleyballmanagement.pojo.AddPlayerRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.EditPlayerResponse;
import com.canada.volleyballmanagement.pojo.EventBusType;
import com.canada.volleyballmanagement.pojo.GetTeamManagerListResponse;
import com.canada.volleyballmanagement.utils.Constants;
import com.canada.volleyballmanagement.utils.ImageCompress;
import com.canada.volleyballmanagement.utils.ImageFilePath;
import com.google.gson.Gson;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

public class AddTeamActivity extends BaseActivity {

    ActivityAddTeamBinding binding;
    boolean isCamera = false;
    boolean isEdit = false;
    int teamId = 0;
    int teamManagerID = 0;
    String strProfilePic = "";
    ArrayAdapter<GetTeamManagerListResponse.Datum> adapter;
    ArrayList<GetTeamManagerListResponse.Datum> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_team);
        binding.setActivity(this);
        showToolBar(true, getResources().getString(R.string.text_add_team));

        if (checkConnection()) {
            callApiForTeamManager("");
        } else {
            showNoInternetDialog();
        }

        isEdit = getIntent().getBooleanExtra(Constants.isEditPlayer, false);

        if (isEdit) {
            init();
        }
    }

    public void init() {

        RequestOptions options = new RequestOptions();
        options.placeholder(getActivity().getResources().getDrawable(R.drawable.ic_person));
        options.error(getActivity().getResources().getDrawable(R.drawable.ic_person));

        Glide.with(getActivity()).setDefaultRequestOptions(options)
                .load(getIntent().getStringExtra(Constants.Image)).into(binding.imgProfile);

        teamId = getIntent().getIntExtra(Constants.TeamId, 0);

        binding.edFirstName.setText(bindView(getIntent().getStringExtra(Constants.Name)));

    }

    public void initSpinner() {

        binding.spTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teamManagerID = data.get(position).getTeamManagerID();

                if (position > 0) {

                } else {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        adapter = new ArrayAdapter<GetTeamManagerListResponse.Datum>(getActivity(), R.layout.spinnerblack, data) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
//                if (position == 0) {
//                    ((TextView) v).setText("Select");
//
//                } else {
                ((TextView) v).setText(data.get(position).getName());
//                }
                return v;
            }

            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
//                if (position == 0) {
//                    ((TextView) v).setText("Select");
//                } else {
                ((TextView) v).setText(data.get(position).getName());
//                }

                return v;
            }

        };

        adapter.setDropDownViewResource(R.layout.spinnerblack);

        binding.spTeam.setAdapter(adapter);

        if (isEdit) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getTeamManagerID() == getIntent().getIntExtra(Constants.TeamManagerId, 0)) {
                    binding.spTeam.setSelection(i);
                    break;
                }
            }
        }

    }

    public void callApiForTeamManager(String search) {
        showDialog();
        requestAPI.GetTeamManagerList(getUserID(), search).enqueue(GetTeamManagerListCallback);
    }

    Callback<GetTeamManagerListResponse> GetTeamManagerListCallback = new Callback<GetTeamManagerListResponse>() {
        @Override
        public void onResponse(Call<GetTeamManagerListResponse> call, Response<GetTeamManagerListResponse> response) {

            dismissDialog();

            GetTeamManagerListResponse teamManagerListResponse = response.body();

            if (teamManagerListResponse.getReturnCode().equals("1")) {
                data.clear();
                data.addAll(teamManagerListResponse.getData());
                initSpinner();

            } else {

                Toast.makeText(getActivity(), "" + teamManagerListResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onFailure(Call<GetTeamManagerListResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgProfile:
                selectedUserDialog();
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

        if (strProfilePic.isEmpty() && !isEdit) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_select_image), Toast.LENGTH_SHORT).show();
        } else if (strFirstName.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_empty_first_name), Toast.LENGTH_SHORT).show();
        } else if (strFirstName.toString().length() < 2) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_valid_first_name), Toast.LENGTH_SHORT).show();
        }

        return isValidation;
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

    public void callApi() {
        showDialog();
        RequestBody APIKey = RequestBody.create(MediaType.parse("text/plain"), Constants.APIKEY);
        RequestBody UserID = RequestBody.create(MediaType.parse("text/plain"), "" + getUserID());
        RequestBody TeamID = RequestBody.create(MediaType.parse("text/plain"), "" + teamId);
        RequestBody TeamManagerID = RequestBody.create(MediaType.parse("text/plain"), "" + teamManagerID);
        RequestBody Name = RequestBody.create(MediaType.parse("text/plain"), "" + returnText(binding.edFirstName));

        if (!isEdit || (isEdit && !strProfilePic.isEmpty())) {
            RequestBody ProfilePic = RequestBody.create(MediaType.parse("*/*"), new File(strProfilePic));
            requestAPI.AddManageTeam(APIKey, UserID, TeamID, TeamManagerID, Name, ProfilePic).enqueue(addTeamCallback);
        } else if (isEdit && strProfilePic.isEmpty()) {
            requestAPI.AddManageTeam(APIKey, UserID, TeamID, TeamManagerID, Name).enqueue(addTeamCallback);
        }

    }

    Callback<CommonResponse> addTeamCallback = new Callback<CommonResponse>() {

        @Override
        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
            dismissDialog();
            CommonResponse commonresponse = response.body();
            if (commonresponse.getReturnCode().equals("1")) {
                EventBus.getDefault().post(new EventBusType(1));
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
