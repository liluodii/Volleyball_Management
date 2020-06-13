package com.canada.volleyballmanagement.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.adapter.TeamListAdapter;
import com.canada.volleyballmanagement.adapter.TeamListAdapter;
import com.canada.volleyballmanagement.baseclass.BaseActivity;
import com.canada.volleyballmanagement.databinding.ActivityMainBinding;
import com.canada.volleyballmanagement.databinding.NavHeaderMainBinding;
import com.canada.volleyballmanagement.pojo.CommonRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.DeleteTeamRequest;
import com.canada.volleyballmanagement.pojo.EventBusType;
import com.canada.volleyballmanagement.pojo.GetTeamListResponse;
import com.canada.volleyballmanagement.pojo.GetTeamManagerListResponse;
import com.canada.volleyballmanagement.utils.Constants;
import com.google.android.material.navigation.NavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    NavHeaderMainBinding headerMainBinding;
    TeamListAdapter adapter;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusType event) {

        switch (event.getType()) {
            case 1:
                if (checkConnection()) {
                    callApi("");
                } else {
                    showNoInternetDialog();
                }
                break;
            case 4:
                headerMainBinding.txtTeamName.setText(getLoginResponse().getData().getFirstName() + " " + getLoginResponse().getData().getLastName());
                if (!getLoginResponse().getData().getProfilePic().isEmpty()) {

                    if (!getLoginResponse().getData().getProfilePic().isEmpty()) {

                        RequestOptions options = new RequestOptions();
                        options.placeholder(getActivity().getResources().getDrawable(R.drawable.ic_person));
                        options.error(getActivity().getResources().getDrawable(R.drawable.ic_person));
                        Glide.with(getActivity()).setDefaultRequestOptions(options)
                                .load(getLoginResponse().getData().getProfilePic()).into(headerMainBinding.imgProfile);

                    } else {
                        headerMainBinding.imgProfile.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_person));
                    }

                } else {
                    headerMainBinding.imgProfile.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_person));
                }
                break;
        }

//        if (event.getType() == 1) {
//

//
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
        EventBus.getDefault().register(this);
        binding.lvNavigation.setNavigationItemSelectedListener(this);
        binding.lvNavigation.setItemIconTintList(null);
        binding.lvNavigation.getMenu().clear();

        if (isTeamManager()) {
            binding.fab.setVisibility(View.GONE);
        }

        if (checkConnection()) {
            callApi("");
        } else {
            showNoInternetDialog();
        }

        setHeader();

        init();

    }

    public void callApi(String search) {
        showDialog();
        requestAPI.GetTeamList(getUserID(), search).enqueue(GetTeamManagerListCallback);
    }

    Callback<GetTeamListResponse> GetTeamManagerListCallback = new Callback<GetTeamListResponse>() {
        @Override
        public void onResponse(Call<GetTeamListResponse> call, Response<GetTeamListResponse> response) {

            dismissDialog();

            GetTeamListResponse playerListResponse = response.body();

            adapter.clear();

            if (playerListResponse.getReturnCode().equals("1")) {

                adapter.addAll(playerListResponse.getData());

            } else {

            }

            noDataFound();

        }

        @Override
        public void onFailure(Call<GetTeamListResponse> call, Throwable t) {
            dismissDialog();
            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };


    public void init() {

        binding.rvTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TeamListAdapter(getActivity());
        binding.rvTeam.setAdapter(adapter);

        adapter.setEventListener(new TeamListAdapter.EventListener() {

            @Override
            public void onPerson(GetTeamListResponse.Datum data, int position) {
                Intent intent = new Intent(getActivity(), SelectTeamActivity.class);
                intent.putExtra(Constants.TeamId, data.getTeamID());
                startActivity(intent);
            }

            @Override
            public void onDelete(GetTeamListResponse.Datum data, int position) {
                deleteDialog(data, position);
            }

            @Override
            public void onEdit(GetTeamListResponse.Datum data, int position) {
                Intent intent = new Intent(getActivity(), AddTeamActivity.class);
                intent.putExtra(Constants.isEditPlayer, true);
                intent.putExtra(Constants.Image, data.getPhoto());
                intent.putExtra(Constants.TeamId, data.getTeamID());
                intent.putExtra(Constants.Name, data.getName());
                intent.putExtra(Constants.TeamManagerId, data.getTeamManagerID());
                startActivity(intent);
            }

        });

    }

    public void deleteDialog(final GetTeamListResponse.Datum data, final int position) {

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

    private void deleteApi(final GetTeamListResponse.Datum data, final int position) {

        DeleteTeamRequest request = new DeleteTeamRequest();
        request.setAPIKey(Constants.APIKEY);
        request.setUserID(getUserID());
        request.setTeamID(data.getTeamID());
        showDialog();

        requestAPI.DeleteTeam(request).enqueue(new Callback<CommonResponse>() {
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

    private void noDataFound() {
        if (adapter.checkSize()) {
            binding.rvTeam.setVisibility(View.GONE);
            binding.lvNoDataFound.setVisibility(View.VISIBLE);
        } else {
            binding.rvTeam.setVisibility(View.VISIBLE);
            binding.lvNoDataFound.setVisibility(View.GONE);
        }
    }

    public void setHeader() {

        headerMainBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header_main, binding
                .lvNavigation, false);
        binding.lvNavigation.addHeaderView(headerMainBinding.getRoot());
        headerMainBinding.txtTeamName.setText(getLoginResponse().getData().getFirstName() + " " + getLoginResponse().getData().getLastName());

        if (!getLoginResponse().getData().getProfilePic().isEmpty()) {
            RequestOptions options = new RequestOptions();
            options.placeholder(getActivity().getResources().getDrawable(R.drawable.ic_person));
            options.error(getActivity().getResources().getDrawable(R.drawable.ic_person));
            Glide.with(getActivity()).setDefaultRequestOptions(options)
                    .load(getLoginResponse().getData().getProfilePic()).into(headerMainBinding.imgProfile);
        } else {
            headerMainBinding.imgProfile.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_person));
        }

        if (isTeamManager())
            binding.lvNavigation.inflateMenu(R.menu.team_manager_drawer);
        else
            binding.lvNavigation.inflateMenu(R.menu.league_manager_drawer);

    }


    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imgMenu:
                binding.lvDrawer.openDrawer(GravityCompat.START);
                break;
            case R.id.fab:
                intent = new Intent(getActivity(), AddTeamActivity.class);
                intent.putExtra(Constants.isEditPlayer, false);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case R.id.navTeams:

                break;
            case R.id.navPlayers:
                intent = new Intent(getActivity(), PlayersActivity.class);
                startActivity(intent);
                break;

            case R.id.navMatches:

                break;
            case R.id.navTeamManager:
                intent = new Intent(getActivity(), TeamManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.navProfile:
                intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.navSignOut:
                logoutDialog();
                break;
        }

        binding.lvDrawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
