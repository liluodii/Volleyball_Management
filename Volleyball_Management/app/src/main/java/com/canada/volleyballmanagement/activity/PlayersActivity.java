package com.canada.volleyballmanagement.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.adapter.PlayerListAdapter;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityPlayerBinding;
import com.canada.volleyballmanagement.pojo.AddPlayerRequest;
import com.canada.volleyballmanagement.pojo.CommonRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.GetPlayerListResponse;
import com.canada.volleyballmanagement.utils.Constants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayersActivity extends BaseActivity {

    ActivityPlayerBinding binding;
    PlayerListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player);
        binding.setActivity(this);
        showToolBar(true, getString(R.string.text_players));
        if (checkConnection()) {
            callApi("");
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
        adapter = new PlayerListAdapter(getActivity());
        binding.rvPlayer.setAdapter(adapter);

        adapter.setEventListener(new PlayerListAdapter.EventListener() {
            @Override
            public void onDelete(GetPlayerListResponse.Datum data, int position) {
                deleteDialog(data, position);
            }

            @Override
            public void onEdit(GetPlayerListResponse.Datum data, int position) {
                Intent intent = new Intent(getActivity(), AddPlayersActivity.class);
                intent.putExtra(Constants.isEditPlayer, true);
                intent.putExtra(Constants.playerId, data.getPlayerID());
                startActivity(intent);
            }
        });
    }

    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fab:
                intent = new Intent(getActivity(), AddPlayersActivity.class);
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
        requestAPI.GetPlayerList(getUserID(), search,0).enqueue(GetPlayerListCallback);
    }

    Callback<GetPlayerListResponse> GetPlayerListCallback = new Callback<GetPlayerListResponse>() {
        @Override
        public void onResponse(Call<GetPlayerListResponse> call, Response<GetPlayerListResponse> response) {

            dismissDialog();

            GetPlayerListResponse playerListResponse = response.body();

            adapter.clear();

            if (playerListResponse.getReturnCode().equals("1")) {

                adapter.addAll(playerListResponse.getData());

            } else {

                Toast.makeText(getActivity(), "" + playerListResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();

            }

            noDataFound();

        }

        @Override
        public void onFailure(Call<GetPlayerListResponse> call, Throwable t) {
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


    public void deleteDialog(final GetPlayerListResponse.Datum data, final int position) {

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

    private void deleteApi(final GetPlayerListResponse.Datum data, final int position) {

        CommonRequest request = new CommonRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setUserID(data.getPlayerID());
        showDialog();

        requestAPI.DeletePlayer(request).enqueue(new Callback<CommonResponse>() {
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
