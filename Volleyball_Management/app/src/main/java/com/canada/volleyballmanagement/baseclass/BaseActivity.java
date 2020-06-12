package com.canada.volleyballmanagement.baseclass;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.activity.LogInActivity;
import com.canada.volleyballmanagement.pojo.LoginResponse;
import com.canada.volleyballmanagement.retrofit.ApiClient;
import com.canada.volleyballmanagement.retrofit.RequestAPI;
import com.canada.volleyballmanagement.utils.Constants;
import com.canada.volleyballmanagement.utils.InternalStorageContentProvider;
import com.canada.volleyballmanagement.utils.MarshMallowPermission;
import com.canada.volleyballmanagement.utils.Preferences;
import com.canada.volleyballmanagement.widget.DTextView;
import com.google.gson.Gson;
import com.victor.loading.rotate.RotateLoading;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BaseActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_GALLERY = 1;
    public static final int REQUEST_CODE_TAKE_PICTURE = 2;
    public static final String TEMP_PHOTO_FILE_NAME = "temp_photo.jpg";
    public static File mFileTemp;

    public RequestAPI requestAPI;
    public Toolbar toolbar;
    public TextView txtTitle;
    public Preferences pref;
    public MarshMallowPermission marshMallowPermission = new MarshMallowPermission(getActivity());
    AlertDialog dialog;
    Uri mImageCaptureUri = null;
    private Toast toast;

    public LoginResponse getLoginResponse() {
        return new Gson().fromJson(pref.getString(Constants.LOGIN_REPONSE), LoginResponse.class);
    }

    public boolean isTeamManager() {
        return new Gson().fromJson(pref.getString(Constants.LOGIN_REPONSE), LoginResponse.class).getData().getRoleID() == 3;
    }

    public int getUserID() {
        return new Gson().fromJson(pref.getString(Constants.LOGIN_REPONSE), LoginResponse.class).getData().getUserID();
    }

    public boolean isLogin() {
        return pref.getBoolean(Constants.ISLOGIN, false);
    }

    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }


    public void formatMobile(EditText edMobile) {
        InputFilter filter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.length() == 1 && source.length() > 0) {
                    if (!Character.isDigit(source.charAt(0)))
                        return "";
                    else {
                        if (dstart == 3) {
                            return source + ") ";
                        } else if (dstart == 0) {
                            return "(" + source;
                        } else if ((dstart == 5) || (dstart == 9))
                            return "-" + source;
                        else if (dstart >= 14)
                            return "";
                    }
                } else {
                    return "";
                }

                return null;

            }
        };

        edMobile.setFilters(new InputFilter[]{filter});
    }


    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public int getDeviceHeight() {
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public void showDialog() {
        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.progress_dialog, null);
        dialog = new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        RotateLoading rotateLoading = v.findViewById(R.id.rotateloading);
        rotateLoading.start();
        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toast = Toast.makeText(getActivity(), "", Toast.LENGTH_LONG);
        requestAPI = ApiClient.getClient().create(RequestAPI.class);
        pref = new Preferences(getActivity());
    }

    public void initState() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        } else {
            mFileTemp = new File(getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }
    }

    public void showToolBar(boolean isBack, String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        try {
            txtTitle = toolbar.findViewById(R.id.txtTitle);
            txtTitle.setText(title);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(null);

        if (isBack) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            final Drawable upArrow = getResources().getDrawable((R.drawable.ic_arrow_back));
            upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            toolbar.setNavigationIcon(upArrow);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }

    }

    public boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.gymnav.dol.utils.InternetService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


//    private void applyFontToMenuItem(MenuItem mi) {
//        Typeface font = FontUtils.fontName(getActivity(), 1);
//        SpannableString mNewTitle = new SpannableString(mi.getTitle());
//        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        mi.setTitle(mNewTitle);
//    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public String returnText(TextView view) {
        return view.getText().toString().trim();
    }

    public String returnText(EditText view) {
        return view.getText().toString().trim();
    }

    public void hideKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("", "hideKeyboard: ");
        }
    }

    public void showToast(final int text, final boolean isShort) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                toast.setText(getString(text).toString());
                toast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    public void showToast(final String text, final boolean isShort) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                toast.setText(text);
                toast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }

    public BaseActivity getActivity() {
        return this;
    }

    public void noDataFound(DTextView dTextView, ListView view) {
        dTextView.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);
    }

    public void takePicture() {
        initState();

        if (!marshMallowPermission.checkPermissionForCamera()) {
            marshMallowPermission.requestPermissionForCamera();
        } else {
            if (!marshMallowPermission.checkPermissionForWriteexternal()) {
                marshMallowPermission.requestPermissionForWriteexternal();
            } else {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {

                    String state = Environment.getExternalStorageState();

                    if (Environment.MEDIA_MOUNTED.equals(state)) {

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            mImageCaptureUri = FileProvider.getUriForFile(this, Constants.PROVIDER,
                                    mFileTemp);
                        } else {
                            mImageCaptureUri = Uri.fromFile(mFileTemp);
                        }

                    } else {
                        mImageCaptureUri = InternalStorageContentProvider.CONTENT_URI;
                    }

                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);

                } catch (ActivityNotFoundException e) {

                }
            }
        }
    }

    public void openGallery() {
        if (!marshMallowPermission.checkPermissionForReadexternal()) {
            marshMallowPermission.requestPermissionForReadexternal();
        } else if (!marshMallowPermission.checkPermissionForWriteexternal()) {
            marshMallowPermission.requestPermissionForWriteexternal();
        } else {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
        }
    }

    public void logoutDialog() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.title_logout))
                .setMessage(getString(R.string.msg_logout))
                .setCancelable(false)
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        pref.putBoolean(Constants.ISLOGIN, false);
                        Intent intent = new Intent(getActivity(), LogInActivity.class);
                        startActivity(intent);
                        getActivity().finishAffinity();
                    }
                }).create();
        dialog.show();
    }

    public void showNoInternetDialog() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage(getString(R.string.title_no_internet))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            getActivity().finishAffinity();
                        }
                    }
                }).create();
        dialog.show();
    }

//    public void callApiForLogout() {
//        showDialog();
//        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(getActivity(), new OnSuccessListener<InstanceIdResult>() {
//            @Override
//            public void onSuccess(InstanceIdResult instanceIdResult) {
//                LogoutRequest request = new LogoutRequest();
//                request.setAPIKey(Constants.APIKEY);
//                request.setUserID(getUserID());
//                request.setDeviceTypeID(Constants.DeviceTypeID);
//                request.setDeviceID(instanceIdResult.getToken());
//                requestAPI.Logout(request).enqueue(LogoutCallback);
//            }
//        });
//    }
//
//    Callback<CommonResponse> LogoutCallback = new Callback<CommonResponse>() {
//        @Override
//        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
//
//            dismissDialog();
//            CommonResponse chatDetailResponse = response.body();
//
//            if (chatDetailResponse.getReturnCode().equals("1")) {
//                pref.clear();
//                Intent intent = new Intent(getActivity(), SelectUserTypeActivity.class);
//                startActivity(intent);
//                getActivity().finishAffinity();
//            }
//
//        }
//
//        @Override
//        public void onFailure(Call<CommonResponse> call, Throwable t) {
//            dismissDialog();
//        }
//
//    };


    public String bindView(String value) {
        if (value == null) {
            return "";
        } else if (value.equals("null")) {
            return "";
        } else {
            return value.trim();
        }
    }

    public String bindView(Integer value) {
        if (value == null) {
            return "";
        } else {
            return String.valueOf(value).trim();
        }
    }

    public void showSettingAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BaseActivity.this);
        alertDialog.setTitle("GPS");
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        alertDialog.show();
    }

    public boolean checkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }


}
