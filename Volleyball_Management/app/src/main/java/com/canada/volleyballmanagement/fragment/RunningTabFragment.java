package com.canada.volleyballmanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.canada.volleyballmanagement.R;
import com.canada.volleyballmanagement.activity.AddTeamManagerActivity;
import com.canada.volleyballmanagement.activity.MatchActivity;
import com.canada.volleyballmanagement.adapter.RunningListAdapter;
import com.canada.volleyballmanagement.adapter.SelectPlayerAdapter;
import com.canada.volleyballmanagement.adapter.TeamManagerListAdapter;
import com.canada.volleyballmanagement.adapter.UpcomingListAdapter;
import com.canada.volleyballmanagement.baseclass.BaseFragment;
import com.canada.volleyballmanagement.databinding.FragmentUpcomingBinding;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.GetPlayerListResponse;
import com.canada.volleyballmanagement.pojo.GetTeamManagerListResponse;
import com.canada.volleyballmanagement.pojo.MatchResponse;
import com.canada.volleyballmanagement.pojo.UpdateScoreRequest;
import com.canada.volleyballmanagement.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RunningTabFragment extends BaseFragment {

    RunningListAdapter adapter;
    FragmentUpcomingBinding binding;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_upcoming, viewGroup, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        binding.rvUpComing.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RunningListAdapter(getActivity());
        adapter.addAll(MatchActivity.activity.dataRunning);
        binding.rvUpComing.setAdapter(adapter);

        adapter.setEventListener(new RunningListAdapter.EventListener() {
            @Override
            public void onRunning(MatchResponse.Running data, int position) {
                if (isLogin()) {
                    if (!isTeamManager()) {
                        updateScore(data);
                    }
                }

            }
        });

        noDataFound();
    }

    private void noDataFound() {
        if (adapter.checkSize()) {
            binding.rvUpComing.setVisibility(View.GONE);
            binding.lvNoDataFound.setVisibility(View.VISIBLE);
        } else {
            binding.rvUpComing.setVisibility(View.VISIBLE);
            binding.lvNoDataFound.setVisibility(View.GONE);
        }
    }

    public void updateScore(final MatchResponse.Running data) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_update_score, null);
        ImageView imgClose = v.findViewById(R.id.imgClose);

        final Button btnDone = v.findViewById(R.id.btnDone);

        final TextView txtTeamOne = v.findViewById(R.id.txtTeamOne);
        final EditText edTeamScoreOne = v.findViewById(R.id.edTeamScoreOne);
        final TextView txtTeamTwo = v.findViewById(R.id.txtTeamTwo);
        final EditText edTeamScoreTwo = v.findViewById(R.id.edTeamScoreTwo);

        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
        dialog.setCancelable(false);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!returnText(edTeamScoreOne).isEmpty()) {

                    if (!returnText(edTeamScoreTwo).isEmpty()) {

                        showDialog();

                        UpdateScoreRequest request = new UpdateScoreRequest();
                        request.setAPIKey(Constants.APIKEY);
                        request.setTeam1Score(Integer.parseInt(returnText(edTeamScoreOne)));
                        request.setTeam2Score(Integer.parseInt(returnText(edTeamScoreTwo)));
                        request.setTournamentTeamID(data.getTournamentTeamID());

                        requestAPI.UpdateScore(request).enqueue(new Callback<CommonResponse>() {
                            @Override
                            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

                                dismissDialog();

                                CommonResponse listResponse = response.body();

                                if (listResponse.getReturnCode().equals("1")) {

                                    MatchActivity.activity.callApi();
                                    MatchActivity.activity.changePager(0);

                                    dialog.dismiss();

                                } else {
                                    Toast.makeText(getActivity(), "" + listResponse.getReturnMsg(), Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }

                            }

                            @Override
                            public void onFailure(Call<CommonResponse> call, Throwable t) {
                                dismissDialog();
                            }

                        });

                    } else {
                        Toast.makeText(getActivity(), "" + R.string.err_empty_team_two_score, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "" + R.string.err_empty_team_one_score, Toast.LENGTH_SHORT).show();
                }


            }
        });

        txtTeamOne.setText("Team one (" + data.getTeam1Name() + ")");
        txtTeamTwo.setText("Team two (" + data.getTeam2Name() + ")");
        edTeamScoreOne.setText(bindView(data.getTeam1Score()));
        edTeamScoreTwo.setText(bindView(data.getTeam2Score()));

        dialog.show();

    }

}
