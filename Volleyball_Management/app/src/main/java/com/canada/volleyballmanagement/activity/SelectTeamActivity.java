package com.canada.volleyballmanagement.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.adapter.SelectTeamMemberAdapter;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivitySelectTeamBinding;
import com.canada.volleyballmanagement.pojo.CommonRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.DeleteSelectTeamMember;
import com.canada.volleyballmanagement.pojo.GetTeamManagerListResponse;
import com.canada.volleyballmanagement.pojo.GetTeamMemberRequest;
import com.canada.volleyballmanagement.pojo.SelectTeamMemberResponse;
import com.canada.volleyballmanagement.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectTeamActivity extends BaseActivity {

    ActivitySelectTeamBinding binding;
    SelectTeamMemberAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_team);
        binding.setActivity(this);
        showToolBar(true, getString(R.string.text_team_manager));

        if (checkConnection()) {
            callApi();
        } else {
            showNoInternetDialog();
        }

        init();

    }

    public void init() {

        binding.rvTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SelectTeamMemberAdapter(getActivity());
        binding.rvTeam.setAdapter(adapter);

        adapter.setEventListener(new SelectTeamMemberAdapter.EventListener() {
            @Override
            public void onDelete(SelectTeamMemberResponse.Datum data, int position) {
                deleteDialog(data, position);
            }
        });

    }

    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fab:

                break;
        }

    }

    public void callApi() {

        showDialog();
        GetTeamMemberRequest request = new GetTeamMemberRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setUserID(getUserID());
        request.setTeamID(getIntent().getIntExtra(Constants.TeamId, 0));
        requestAPI.GetTeamMember(request).enqueue(GetTeamManagerListCallback);

    }

    Callback<SelectTeamMemberResponse> GetTeamManagerListCallback = new Callback<SelectTeamMemberResponse>() {

        @Override
        public void onResponse(Call<SelectTeamMemberResponse> call, Response<SelectTeamMemberResponse> response) {

            dismissDialog();

            SelectTeamMemberResponse playerListResponse = response.body();

            adapter.clear();

            if (playerListResponse.getReturnCode().equals("1")) {

                adapter.addAll(playerListResponse.getData());

            } else {

                Toast.makeText(getActivity(), "" + playerListResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

            }

            noDataFound();

        }

        @Override
        public void onFailure(Call<SelectTeamMemberResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };


    private void noDataFound() {
        if (adapter.checkSize()) {
            binding.rvTeam.setVisibility(View.GONE);
            binding.lvNoDataFound.setVisibility(View.VISIBLE);
        } else {
            binding.rvTeam.setVisibility(View.VISIBLE);
            binding.lvNoDataFound.setVisibility(View.GONE);
        }
    }


    public void deleteDialog(final SelectTeamMemberResponse.Datum data, final int position) {

        AlertDialog dialog = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                .setTitle(getString(R.string.title_delete))
                .setMessage(getString(R.string.msg_delete))
                .setCancelable(false)
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                        deleteApi(data, position);

                    }
                }).create();

        dialog.show();

    }

    private void deleteApi(final SelectTeamMemberResponse.Datum data, final int position) {

        DeleteSelectTeamMember request = new DeleteSelectTeamMember();
        request.setAPIKey(Constants.APIKEY);
        request.setUserID(getUserID());
        request.setTeamMemberJoinID(data.getTeamMemberJoinID());
        showDialog();

        requestAPI.DeletePlayerFromTeam(request).enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                dismissDialog();
                CommonResponse commonresponse = response.body();
                if (commonresponse.getReturnCode().equals("1")) {
                    adapter.removeAt(position);
                    noDataFound();
                } else {
                    Toast.makeText(getActivity(), "" + commonresponse.getReturnMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                dismissDialog();
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
