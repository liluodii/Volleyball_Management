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
import com.canada.volleyballmanagement.adapter.PlayerListAdapter;
import com.canada.volleyballmanagement.adapter.TeamManagerListAdapter;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityPlayerBinding;
import com.canada.volleyballmanagement.databinding.ActivityTeamManagerBinding;
import com.canada.volleyballmanagement.pojo.CommonRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.GetTeamManagerListResponse;
import com.canada.volleyballmanagement.pojo.GetTeamManagerListResponse;
import com.canada.volleyballmanagement.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamManagerActivity extends BaseActivity {

    ActivityTeamManagerBinding binding;
    TeamManagerListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_manager);
        binding.setActivity(this);
        showToolBar(true, getString(R.string.text_team_manager));

        if (checkConnection()) {
            callApi("");
        } else {
            showNoInternetDialog();
        }

        init();

    }

    public void init() {

        binding.rvTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TeamManagerListAdapter(getActivity());
        binding.rvTeam.setAdapter(adapter);

        adapter.setEventListener(new TeamManagerListAdapter.EventListener() {
            @Override
            public void onDelete(GetTeamManagerListResponse.Datum data, int position) {
                deleteDialog(data, position);
            }

            @Override
            public void onEdit(GetTeamManagerListResponse.Datum data, int position) {
                Intent intent = new Intent(getActivity(), AddTeamManagerActivity.class);
                intent.putExtra(Constants.isEditPlayer, true);
                intent.putExtra(Constants.playerId, data.getTeamManagerID());
                startActivity(intent);
            }
        });

    }

    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fab:
                intent = new Intent(getActivity(), AddTeamManagerActivity.class);
                intent.putExtra(Constants.isEditPlayer, false);
                startActivity(intent);
                break;
            case R.id.imgSearch:
                if (checkConnection()) {
                    hideKeyboard();
                    callApi(binding.edSearch.getText().toString());
                } else {
                    showNoInternetDialog();
                }
                break;
        }

    }

    public void callApi(String search) {
        showDialog();
        requestAPI.GetTeamManagerList(getUserID(), search).enqueue(GetTeamManagerListCallback);
    }

    Callback<GetTeamManagerListResponse> GetTeamManagerListCallback = new Callback<GetTeamManagerListResponse>() {
        @Override
        public void onResponse(Call<GetTeamManagerListResponse> call, Response<GetTeamManagerListResponse> response) {

            dismissDialog();

            GetTeamManagerListResponse playerListResponse = response.body();

            adapter.clear();

            if (playerListResponse.getReturnCode().equals("1")) {

                adapter.addAll(playerListResponse.getData());

            } else {

                Toast.makeText(getActivity(), "" + playerListResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

            }

            noDataFound();

        }

        @Override
        public void onFailure(Call<GetTeamManagerListResponse> call, Throwable t) {
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


    public void deleteDialog(final GetTeamManagerListResponse.Datum data, final int position) {

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

    private void deleteApi(final GetTeamManagerListResponse.Datum data, final int position) {

        CommonRequest request = new CommonRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setUserID(data.getTeamManagerID());
        showDialog();

        requestAPI.DeleteTeamManager(request).enqueue(new Callback<CommonResponse>() {
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
