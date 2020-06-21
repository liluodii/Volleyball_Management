package com.canada.volleyballmanagement.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityAddTournamentTeamBinding;
import com.canada.volleyballmanagement.pojo.AddEditTournamentTeamRequest;
import com.canada.volleyballmanagement.pojo.AddPlayerRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.EventBusType;
import com.canada.volleyballmanagement.pojo.GetTeamListResponse;
import com.canada.volleyballmanagement.utils.Constants;
import com.google.gson.Gson;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTournamentTeamActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {

    ActivityAddTournamentTeamBinding binding;
    int teamFirst = 0;
    int teamSecond = 0;

    ArrayAdapter<GetTeamListResponse.Datum> adapterFirst;
    ArrayAdapter<GetTeamListResponse.Datum> adapterSecond;
    ArrayList<GetTeamListResponse.Datum> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_tournament_team);
        binding.setActivity(this);
        showToolBar(true, getResources().getString(R.string.text_add_tournament_team));

        if (checkConnection()) {
            callApiForTeamManager();
        } else {
            showNoInternetDialog();
        }


    }


    public void initSpinnerFirst() {

        binding.spTeamFirst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teamFirst = data.get(position).getTeamID();

                if (position > 0) {

                } else {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        adapterFirst = new ArrayAdapter<GetTeamListResponse.Datum>(getActivity(), R.layout.spinnerblack, data) {

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

        adapterFirst.setDropDownViewResource(R.layout.spinnerblack);

        binding.spTeamFirst.setAdapter(adapterFirst);

    }

    public void initSpinnerSecond() {

        binding.spTeamSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                teamSecond = data.get(position).getTeamID();

                if (position > 0) {
                } else {
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        adapterSecond = new ArrayAdapter<GetTeamListResponse.Datum>(getActivity(), R.layout.spinnerblack, data) {

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

        adapterSecond.setDropDownViewResource(R.layout.spinnerblack);

        binding.spTeamSecond.setAdapter(adapterSecond);

    }


    public void callApiForTeamManager() {
        showDialog();
        requestAPI.GetTeamList(getUserID(), "").enqueue(GetTeamManagerListCallback);
    }

    Callback<GetTeamListResponse> GetTeamManagerListCallback = new Callback<GetTeamListResponse>() {
        @Override
        public void onResponse(Call<GetTeamListResponse> call, Response<GetTeamListResponse> response) {

            dismissDialog();

            GetTeamListResponse teamManagerListResponse = response.body();

            if (teamManagerListResponse.getReturnCode().equals("1")) {
                data.clear();
                data.addAll(teamManagerListResponse.getData());
                initSpinnerFirst();
                initSpinnerSecond();
            } else {

                Toast.makeText(getActivity(), "" + teamManagerListResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onFailure(Call<GetTeamListResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edDate:
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

    public void calender(String tag) {
        Calendar now = Calendar.getInstance();
//        now.add(Calendar.YEAR, -18);
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
        binding.edDate.setText("" + strDOB);
    }

    private boolean validate() {
        boolean isValidation = true;
        String strDate = returnText(binding.edDate);
        if (strDate.isEmpty()) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_select_date), Toast.LENGTH_SHORT).show();
        } else if (teamFirst == teamSecond) {
            isValidation = false;
            Toast.makeText(getActivity(), "" + getString(R.string.err_select_different_team), Toast.LENGTH_SHORT).show();
        }
        return isValidation;
    }


    public void callApi() {
        showDialog();
        AddEditTournamentTeamRequest request = new AddEditTournamentTeamRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setUserID(getUserID());
        request.setStartDate(returnText(binding.edDate));
        request.setTournamentTeamID(0);
        request.setTournamentID(getIntent().getIntExtra(Constants.TeamId, 0));
        request.setTeam1(teamFirst);
        request.setTeam2(teamSecond);

        requestAPI.AddEditTournamentTeam(request).enqueue(AddEditPlayerCallback);

    }

    Callback<CommonResponse> AddEditPlayerCallback = new Callback<CommonResponse>() {
        @Override
        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
            CommonResponse commonresponse = response.body();
            if (commonresponse.getReturnCode().equals("1")) {
                EventBus.getDefault().post(new EventBusType(5));
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
