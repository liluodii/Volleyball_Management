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
import com.canada.volleyballmanagement.adapter.PlayerListAdapter;
import com.canada.volleyballmanagement.adapter.TournamentListAdapter;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityTournamentBinding;
import com.canada.volleyballmanagement.pojo.CommonRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.DeleteTournamentRequest;
import com.canada.volleyballmanagement.pojo.EventBusType;
import com.canada.volleyballmanagement.pojo.GetPlayerListResponse;
import com.canada.volleyballmanagement.pojo.GetTournamentResponse;
import com.canada.volleyballmanagement.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TournamentActivity extends BaseActivity {

    ActivityTournamentBinding binding;
    TournamentListAdapter adapter;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusType event) {

        switch (event.getType()) {
            case 4:
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tournament);
        binding.setActivity(this);
        EventBus.getDefault().register(this);
        showToolBar(true, getString(R.string.text_season));

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

        binding.rvPlayer.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TournamentListAdapter(getActivity());
        binding.rvPlayer.setAdapter(adapter);

        adapter.setEventListener(new TournamentListAdapter.EventListener() {
            @Override
            public void onDelete(GetTournamentResponse.Datum data, int position) {
                deleteDialog(data, position);
            }

            @Override
            public void onEdit(GetTournamentResponse.Datum data, int position) {
                Intent intent = new Intent(getActivity(), AddTournamentActivity.class);
                intent.putExtra(Constants.isEditPlayer, true);
                intent.putExtra(Constants.TeamId, data.getTournamentID());
                intent.putExtra(Constants.Name, data.getName());
                startActivity(intent);
            }

            @Override
            public void onMatch(GetTournamentResponse.Datum data, int position) {
                Intent intent = new Intent(getActivity(), TournamentTeamActivity.class);
                intent.putExtra(Constants.TeamId, data.getTournamentID());
                startActivity(intent);
            }

        });
    }

    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fab:
                intent = new Intent(getActivity(), AddTournamentActivity.class);
                intent.putExtra(Constants.isEditPlayer, false);
                startActivity(intent);
                break;
        }
    }

    public void callApi() {
        showDialog();
        requestAPI.GetTournamentList(getUserID()).enqueue(GetTournamentListCallback);
    }

    Callback<GetTournamentResponse> GetTournamentListCallback = new Callback<GetTournamentResponse>() {
        @Override
        public void onResponse(Call<GetTournamentResponse> call, Response<GetTournamentResponse> response) {

            dismissDialog();

            GetTournamentResponse playerListResponse = response.body();

            adapter.clear();

            if (playerListResponse.getReturnCode().equals("1")) {

                adapter.addAll(playerListResponse.getData());

            } else {

                Toast.makeText(getActivity(), "" + playerListResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

            }

            noDataFound();

        }

        @Override
        public void onFailure(Call<GetTournamentResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };

    private void noDataFound() {
        if (adapter.checkSize()) {
            binding.rvPlayer.setVisibility(View.GONE);
            binding.lvNoDataFound.setVisibility(View.VISIBLE);
        } else {
            binding.rvPlayer.setVisibility(View.VISIBLE);
            binding.lvNoDataFound.setVisibility(View.GONE);
        }
    }


    public void deleteDialog(final GetTournamentResponse.Datum data, final int position) {

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

    private void deleteApi(final GetTournamentResponse.Datum data, final int position) {

        DeleteTournamentRequest request = new DeleteTournamentRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setTournamentID(data.getTournamentID());
        showDialog();

        requestAPI.DeleteTournament(request).enqueue(new Callback<CommonResponse>() {
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
