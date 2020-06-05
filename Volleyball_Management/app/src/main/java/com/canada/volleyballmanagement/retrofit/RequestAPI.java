package com.canada.volleyballmanagement.retrofit;


import com.canada.volleyballmanagement.pojo.LoginRequest;
import com.canada.volleyballmanagement.pojo.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestAPI {

    @POST("/Login")
    Call<LoginResponse> Login(@Body LoginRequest request);


//    @Multipart
//    @POST("/CreateClass")
//    Call<CommonResponse> stepThirdCreateClass(@Part("APIKey") RequestBody APIKey, @Part("ProviderID") RequestBody ProviderID, @Part("ListingID") RequestBody ListingID,
//                                              @Part("Step") RequestBody Step, @Part("IsCreate") RequestBody IsCreate, @PartMap HashMap<String, RequestBody> map, @Part("ProfilePicChanged") RequestBody ProfilePicChanged, @Part("CoverImageChanged") RequestBody CoverImageChanged, @Part("YoutubeLink") RequestBody YoutubeLink);


}
