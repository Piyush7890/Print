package com.example.mamid.print;

import android.util.Log;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthService {
Call call;
    public void getToken(String code , final listener listener)
    {

        AuthApi api =new   Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create()).build().create(AuthApi.class);
Call<AccessToken> call = api.getAccessToken(MainActivity.CLIENT_ID,MainActivity.REDIRECT_URI,code,"authorization_code");
call.enqueue(new Callback<AccessToken>() {
    @Override
    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {


           listener.onSuccess(response.body());

    }

    @Override
    public void onFailure(Call<AccessToken> call, Throwable t) {
        listener.onFailed(t);
    }
});
    this.call = call;

    }

    public void submitJob(String content, String title, String ticket, final listener listener, String token)
    {
        AuthApi api = new Retrofit
                .Builder()
                .baseUrl("https://www.google.com/cloudprint/")
                .client(new OkHttpClient
                        .Builder()
                        .addInterceptor(new Interceptor("ya29.GlsVBuW9b7Ak7CrWTZlphkS0Eo_MsN6bJXvtOrynVxCVC-YOvvc3czMzaCXofSyvo7decpUBZmDQno4r0DeMWOZFJXhmM98x_ltAkNwLaZ0w5ZcxjUHtQ2NpWJhI")).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthApi.class);
        Call<SubmitOutput> call = api.submitJob("0d38c60a-fbb5-32c1-a3d4-3a17c03fc4bd",
                title, content, "url");

                call.enqueue(new Callback<SubmitOutput>() {
            @Override
            public void onResponse(Call<SubmitOutput> call, Response<SubmitOutput> response) {
Log.d("CODE",String.valueOf(response.code()));

                Log.d("HELLO","HELLO");
                if(response.code()==200)
                listener.onSubmitSuccess(response.body());

            }

            @Override
            public void onFailure(Call<SubmitOutput> call, Throwable t) {
                listener.onSubmitFailed(t);
            }
        });
    }

    public void cancel()
    {
        if (call!=null)
            call.cancel();
    }


    public interface listener
    {
        void onSuccess(AccessToken token);
        void onFailed(Throwable t);
        void onSubmitSuccess(SubmitOutput output);
        void onSubmitFailed(Throwable t);
    }
}
