package com.example.mamid.print;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListService {



    public void getPrinterList(String proxy, String token, final PrinterListListener listListener)
    {
            AuthApi api = new Retrofit.Builder()
                    .baseUrl("https://www.google.com/cloudprint/")
                    .client(new OkHttpClient.Builder()
                            .addInterceptor(new Interceptor(token))
                            .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AuthApi.class);
            Call<PrinterList> call = api.getPrinterList(proxy);
            call.enqueue(new Callback<PrinterList>() {
                @Override
                public void onResponse(Call<PrinterList> call, Response<PrinterList> response) {
                    listListener.onListLoaded(response.body());
                }

                @Override
                public void onFailure(Call<PrinterList> call, Throwable t) {
listListener.OnListLoadingFailed(t);
                }
            });


    }


    public interface PrinterListListener
    {
        void onListLoaded(PrinterList list);
        void OnListLoadingFailed(Throwable t);
    }



}
