package com.canada.volleyballmanagement.retrofit;

import com.canada.volleyballmanagement.pojo.AddEditTournamentRequest;
import com.canada.volleyballmanagement.pojo.AddEditTournamentTeamRequest;
import com.canada.volleyballmanagement.pojo.AddPlayerInTeamRequest;
import com.canada.volleyballmanagement.pojo.AddPlayerRequest;
import com.canada.volleyballmanagement.pojo.AddTeamManagerRequest;
import com.canada.volleyballmanagement.pojo.ChangePasswordRequest;
import com.canada.volleyballmanagement.pojo.CommonRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.DeleteSelectTeamMember;
import com.canada.volleyballmanagement.pojo.DeleteTeamRequest;
import com.canada.volleyballmanagement.pojo.DeleteTournamentRequest;
import com.canada.volleyballmanagement.pojo.DeleteTournamentTeamRequest;
import com.canada.volleyballmanagement.pojo.EditPlayerResponse;
import com.canada.volleyballmanagement.pojo.EditProfileRequest;
import com.canada.volleyballmanagement.pojo.EditTeamManagerResponse;
import com.canada.volleyballmanagement.pojo.ForgotPasswordRequest;
import com.canada.volleyballmanagement.pojo.ForgotPasswordResponse;
import com.canada.volleyballmanagement.pojo.GetPlayerListResponse;
import com.canada.volleyballmanagement.pojo.GetTeamListResponse;
import com.canada.volleyballmanagement.pojo.GetTeamManagerListResponse;
import com.canada.volleyballmanagement.pojo.GetTeamMemberRequest;
import com.canada.volleyballmanagement.pojo.GetTournamentResponse;
import com.canada.volleyballmanagement.pojo.GetTournamentTeamRequest;
import com.canada.volleyballmanagement.pojo.LoginRequest;
import com.canada.volleyballmanagement.pojo.LoginResponse;
import com.canada.volleyballmanagement.pojo.MatchResponse;
import com.canada.volleyballmanagement.pojo.SelectTeamMemberResponse;
import com.canada.volleyballmanagement.pojo.TournamentTeamResponse;
import com.canada.volleyballmanagement.pojo.UpdateScoreRequest;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestAPI {

    @POST("/Login")
    Call<LoginResponse> Login(@Body LoginRequest request);

    @POST("/ForgetPassword")
    Call<ForgotPasswordResponse> ForgetPassword(@Body ForgotPasswordRequest request);

    @POST("/GetTeamMember")
    Call<SelectTeamMemberResponse> GetTeamMember(@Body GetTeamMemberRequest request);

    @Multipart
    @POST("/AddUserProfilePic")
    Call<CommonResponse> UpdateProfilePic(@Part("APIKey") RequestBody APIKey, @Part("UserID") RequestBody UserDetailID, @Part("ImageData\"; filename=\"ImageData.png\" ") RequestBody ProfilePic);

    @Multipart
    @POST("/AddManageTeam")
    Call<CommonResponse> AddManageTeam(@Part("APIKey") RequestBody APIKey, @Part("UserID") RequestBody UserDetailID, @Part("TeamID") RequestBody TeamID, @Part("TeamManagerID") RequestBody TeamManagerID, @Part("Name") RequestBody Name, @Part("ImageData\"; filename=\"ImageData.png\" ") RequestBody ProfilePic);

    @Multipart
    @POST("/AddManageTeam")
    Call<CommonResponse> AddManageTeam(@Part("APIKey") RequestBody APIKey, @Part("UserID") RequestBody UserDetailID, @Part("TeamID") RequestBody TeamID, @Part("TeamManagerID") RequestBody TeamManagerID, @Part("Name") RequestBody Name);

    @POST("/ChangePassword")
    Call<CommonResponse> ChangePasswordRequest(@Body ChangePasswordRequest request);

    @POST("/UpdateProfile")
    Call<LoginResponse> UpdateProfile(@Body EditProfileRequest request);

    @PUT("/AddEditPlayer")
    Call<CommonResponse> AddEditPlayer(@Body AddPlayerRequest request);

    @PUT("/AddEditTournamentTeam")
    Call<CommonResponse> AddEditTournamentTeam(@Body AddEditTournamentTeamRequest request);

    @PUT("/AddEditTournamet")
    Call<CommonResponse> AddEditTournamet(@Body AddEditTournamentRequest request);

    @PUT("/AddPlayerInTeam")
    Call<CommonResponse> AddPlayerInTeam(@Body AddPlayerInTeamRequest request);

    @PUT("/AddEditTeamManager")
    Call<CommonResponse> AddEditTeamManager(@Body AddTeamManagerRequest request);

    @PUT("/UpdateScore")
    Call<CommonResponse> UpdateScore(@Body UpdateScoreRequest request);

    @GET("/GetPlayerList")
    Call<GetPlayerListResponse> GetPlayerList(@Query("UserID") int id, @Query("Search") String search, @Query("IsCheckInTeam") int IsCheckInTeam);

    @GET("/GetTournamentList")
    Call<GetTournamentResponse> GetTournamentList(@Query("UserID") int id);

    @POST("/GetTournamentTeam")
    Call<TournamentTeamResponse> GetTournamentTeam(@Body GetTournamentTeamRequest request);

    @GET("/GetTeamManagerList")
    Call<GetTeamManagerListResponse> GetTeamManagerList(@Query("UserID") int id, @Query("Search") String search);

    @GET("/GetMatchList")
    Call<MatchResponse> GetMatchList(@Query("UserID") int id);

    @GET("/GetTeamList")
    Call<GetTeamListResponse> GetTeamList(@Query("UserID") int id, @Query("Search") String search);

    @GET("/GetPlayerDetails")
    Call<EditPlayerResponse> GetPlayerDetails(@Query("PlayerID") int id);

    @GET("/GetTeamManagerDetails")
    Call<EditTeamManagerResponse> GetTeamManagerDetails(@Query("TeamManagerID") int id);

    @HTTP(method = "DELETE", path = "DeletePlayer/", hasBody = true)
    Call<CommonResponse> DeletePlayer(@Body CommonRequest request);

    @HTTP(method = "DELETE", path = "DeleteTournament/", hasBody = true)
    Call<CommonResponse> DeleteTournament(@Body DeleteTournamentRequest request);

    @HTTP(method = "DELETE", path = "DeleteTournamentTeam/", hasBody = true)
    Call<CommonResponse> DeleteTournamentTeam(@Body DeleteTournamentTeamRequest request);

    @HTTP(method = "DELETE", path = "DeleteTeamManager/", hasBody = true)
    Call<CommonResponse> DeleteTeamManager(@Body CommonRequest request);

    @HTTP(method = "DELETE", path = "DeletePlayerFromTeam/", hasBody = true)
    Call<CommonResponse> DeletePlayerFromTeam(@Body DeleteSelectTeamMember request);

    @HTTP(method = "DELETE", path = "DeleteTeam/", hasBody = true)
    Call<CommonResponse> DeleteTeam(@Body DeleteTeamRequest request);


//    @Multipart
//    @POST("/CreateClass")
//    Call<CommonResponse> stepThirdCreateClass(@Part("APIKey") RequestBody APIKey, @Part("ProviderID") RequestBody ProviderID, @Part("ListingID") RequestBody ListingID,
//                                              @Part("Step") RequestBody Step, @Part("IsCreate") RequestBody IsCreate, @PartMap HashMap<String, RequestBody> map, @Part("ProfilePicChanged") RequestBody ProfilePicChanged, @Part("CoverImageChanged") RequestBody CoverImageChanged, @Part("YoutubeLink") RequestBody YoutubeLink);


}
