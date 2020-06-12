package com.canada.volleyballmanagement.retrofit;

import com.canada.volleyballmanagement.pojo.AddPlayerRequest;
import com.canada.volleyballmanagement.pojo.ChangePasswordRequest;
import com.canada.volleyballmanagement.pojo.CommonResponse;
import com.canada.volleyballmanagement.pojo.EditProfileRequest;
import com.canada.volleyballmanagement.pojo.ForgotPasswordRequest;
import com.canada.volleyballmanagement.pojo.ForgotPasswordResponse;
import com.canada.volleyballmanagement.pojo.LoginRequest;
import com.canada.volleyballmanagement.pojo.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface RequestAPI {

    @POST("/Login")
    Call<LoginResponse> Login(@Body LoginRequest request);

    @POST("/ForgetPassword")
    Call<ForgotPasswordResponse> ForgetPassword(@Body ForgotPasswordRequest request);

    @Multipart
    @POST("/AddUserProfilePic")
    Call<CommonResponse> UpdateProfilePic(@Part("APIKey") RequestBody APIKey, @Part("UserID") RequestBody UserDetailID, @Part("ImageData\"; filename=\"ImageData.png\" ") RequestBody ProfilePic);

    @POST("/ChangePassword")
    Call<CommonResponse> ChangePasswordRequest(@Body ChangePasswordRequest request);

    @POST("/UpdateProfile")
    Call<LoginResponse> UpdateProfile(@Body EditProfileRequest request);

    @PUT("/AddEditPlayer")
    Call<CommonResponse> AddEditPlayer(@Body AddPlayerRequest request);


//    @Multipart
//    @POST("/CreateClass")
//    Call<CommonResponse> stepThirdCreateClass(@Part("APIKey") RequestBody APIKey, @Part("ProviderID") RequestBody ProviderID, @Part("ListingID") RequestBody ListingID,
//                                              @Part("Step") RequestBody Step, @Part("IsCreate") RequestBody IsCreate, @PartMap HashMap<String, RequestBody> map, @Part("ProfilePicChanged") RequestBody ProfilePicChanged, @Part("CoverImageChanged") RequestBody CoverImageChanged, @Part("YoutubeLink") RequestBody YoutubeLink);


}
