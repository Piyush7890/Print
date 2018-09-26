package com.example.mamid.print;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthApi {
String grant_type = "authorization_type";
    @POST("/oauth2/v4/token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(@Field("client_id") String client_id,
                                     @Field("redirect_uri") String redirect_uri,
                                     @Field("code") String code,
                                     @Field("grant_type") String grant_type);


    @GET("list")
    Call<PrinterList> getPrinterList(@Query("proxy")String proxy);


    @GET("submit")
    Call<SubmitOutput> submitJob(@Query("printerid")String printerid,
                                 @Query("title")String title,
//                                 @Query("ticket")String ticket,
                                 @Query("content")String content,
                                 @Query("contentType") String type);



}
