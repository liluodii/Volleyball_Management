package com.canada.volleyballmanagement.activity;

import android.annotation.SuppressLint;
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
import com.canada.volleyballmanagement.adapter.TournamentTeamListAdapter;
import com.canada.volleyballmanagement.adapter.TournamentTeamListAdapter;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityTournamentBinding;
import com.canada.volleyballmanagement.databinding.ActivityTournamentTeamBinding;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.DeleteTournamentRequest;
import com.canada.volleyballmanagement.pojo.DeleteTournamentTeamRequest;
import com.canada.volleyballmanagement.pojo.EventBusType;
import com.canada.volleyballmanagement.pojo.GetTournamentTeamRequest;
import com.canada.volleyballmanagement.pojo.TournamentTeamResponse;
import com.canada.volleyballmanagement.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TournamentTeamActivity extends BaseActivity {

    ActivityTournamentTeamBinding binding;
    TournamentTeamListAdapter adapter;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusType event) {
        switch (event.getType()) {
            case 5:
                if (checkConnection()) {
                    callApi();
                } else {
                    showNoInternetDialog();
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tournament_team);
        binding.setActivity(this);
        EventBus.getDefault().register(this);
        showToolBar(true, getString(R.string.text_tournament_team));

        if (checkConnection()) {
            callApi();
        } else {
            showNoInternetDialog();
        }

        if (isTeamManager()) {
            binding.fab.setVisibility(View.GONE);
        }

        init();

    }

    public void init() {

        binding.rvTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TournamentTeamListAdapter(getActivity());
        binding.rvTeam.setAdapter(adapter);

        adapter.setEventListener(new TournamentTeamListAdapter.EventListener() {
            @Override
            public void onDelete(TournamentTeamResponse.Datum data, int position) {
                deleteDialog(data, position);
            }
        });

    }

    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fab:
                intent = new Intent(getActivity(), AddTournamentTeamActivity.class);
                intent.putExtra(Constants.isEditPlayer, false);
                intent.putExtra(Constants.TeamId, getIntent().getIntExtra(Constants.TeamId, 0));
                startActivity(intent);
                break;
        }
    }

    public void callApi() {
        showDialog();
        GetTournamentTeamRequest request = new GetTournamentTeamRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setTournamentTeamID(getIntent().getIntExtra(Constants.TeamId, 0));
        requestAPI.GetTournamentTeam(request).enqueue(GetTournamentListCallback);
    }

    Callback<TournamentTeamResponse> GetTournamentListCallback = new Callback<TournamentTeamResponse>() {
        @Override
        public void onResponse(Call<TournamentTeamResponse> call, Response<TournamentTeamResponse> response) {

            dismissDialog();

            TournamentTeamResponse playerListResponse = response.body();

            adapter.clear();

            if (playerListResponse.getReturnCode().equals("1")) {

                adapter.addAll(playerListResponse.getData());

            } else {

                Toast.makeText(getActivity(), "" + playerListResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

            }

            noDataFound();

        }

        @Override
        public void onFailure(Call<TournamentTeamResponse> call, Throwable t) {
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


    public void deleteDialog(final TournamentTeamResponse.Datum data, final int position) {

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

    private void deleteApi(final TournamentTeamResponse.Datum data, final int position) {

        DeleteTournamentTeamRequest request = new DeleteTournamentTeamRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setTournamentTeamID(data.getTournamentTeamID());
        showDialog();

        requestAPI.DeleteTournamentTeam(request).enqueue(new Callback<CommonResponse>() {
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
