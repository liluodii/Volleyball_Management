package com.canada.volleyballmanagement.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.adapter.SelectPlayerAdapter;
import com.canada.volleyballmanagement.adapter.SelectTeamMemberAdapter;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivitySelectTeamBinding;
import com.canada.volleyballmanagement.pojo.AddPlayerInTeamRequest;
import com.canada.volleyballmanagement.pojo.CommonRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.DeleteSelectTeamMember;
import com.canada.volleyballmanagement.pojo.GetPlayerListResponse;
import com.canada.volleyballmanagement.pojo.GetTeamManagerListResponse;
import com.canada.volleyballmanagement.pojo.GetTeamMemberRequest;
import com.canada.volleyballmanagement.pojo.LoginResponse;
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
        switch (view.getId()) {
            case R.id.fab:
                selectPlayer();
                break;
        }

    }

    public void selectPlayer() {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_select_team_member, null);
        ImageView imgClose = v.findViewById(R.id.imgClose);

        final RecyclerView rvPlayer = v.findViewById(R.id.rvPlayer);

        final Button btnDone = v.findViewById(R.id.btnDone);

        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
        dialog.setCancelable(false);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);

        showDialog();

        rvPlayer.setLayoutManager(new LinearLayoutManager(getActivity()));
        final SelectPlayerAdapter playerAdapter = new SelectPlayerAdapter(getActivity());
        rvPlayer.setAdapter(playerAdapter);
        playerAdapter.clear();

        requestAPI.GetPlayerList(getUserID(), "", 1).enqueue(new Callback<GetPlayerListResponse>() {
            @Override
            public void onResponse(Call<GetPlayerListResponse> call, Response<GetPlayerListResponse> response) {

                dismissDialog();

                GetPlayerListResponse listResponse = response.body();

                if (listResponse.getReturnCode().equals("1")) {
                    playerAdapter.addAll(listResponse.getData());
                } else {
                    Toast.makeText(getActivity(), "" + listResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<GetPlayerListResponse> call, Throwable t) {
                dismissDialog();
            }

        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!playerAdapter.getId().isEmpty()) {
                    dialog.dismiss();
                    callApiAddPlayer(playerAdapter.getId());
                } else {
                    Toast.makeText(getActivity(), "" + getResources().getString(R.string.err_select_player), Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }


    public void callApiAddPlayer(String strId) {

        showDialog();
        AddPlayerInTeamRequest request = new AddPlayerInTeamRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setUserID(getUserID());
        request.setTeamID(getIntent().getIntExtra(Constants.TeamId, 0));
        request.setPlayerIDs(strId);
        requestAPI.AddPlayerInTeam(request).enqueue(AddPlayerCallback);

    }

    Callback<CommonResponse> AddPlayerCallback = new Callback<CommonResponse>() {

        @Override
        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

            dismissDialog();

            CommonResponse playerListResponse = response.body();

            if (playerListResponse.getReturnCode().equals("1")) {
                callApi();
            } else {
                Toast.makeText(getActivity(), "" + playerListResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onFailure(Call<CommonResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };


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
