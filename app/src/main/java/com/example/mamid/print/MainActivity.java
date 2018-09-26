package com.example.mamid.print;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AuthService.listener, ListService.PrinterListListener{
    Button b;
    AuthService service;
    TextView token;
    ListAdapter adapter;
    RecyclerView rview;
    ListService listing;
    private static final String tokenn = "ya29.GlsOBp3179uc3jz4K_oDJjBlnwkp7vna0gr3yZFN-MeiFWDgR4aSSt0OsyvtRXeRg_MfulNi7PjNc194NL8FAx0yb4m273KPBQGgSGt-d0loDSDhJPITtKQBleJY";
public static final String CLIENT_ID = "76023066008-nnc6sph0nfl5qfhpbi75nbd7h3euq75c.apps.googleusercontent.com";
public static final String REDIRECT_URI = "com.example.mamid.print:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        token = findViewById(R.id.token);
        rview = findViewById(R.id.printerlist);
        adapter = new ListAdapter();
        rview.setAdapter(adapter);
        listing = new ListService();
        rview.setLayoutManager(new LinearLayoutManager(this));
        b = findViewById(R.id.signin);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listing.getPrinterList("171b5802-f6e2-44ee-ad71-b33a8a0e36f6",
                        tokenn,
                        MainActivity.this);

            }
        });
        service = new AuthService();
        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
            Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG).show();

            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
            String[] data = intent.getData().toString().split("=");
            Toast.makeText(MainActivity.this, data[1], Toast.LENGTH_LONG).show();

            service.getToken(data[1], this);
            String code = intent.getData().getQueryParameter("code");
            SharedPreferences prefs = getSharedPreferences("AUTH_TOKEN", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.apply();
            editor.putString("CODE", code);
            service.getToken(code, this);


        }
    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onSuccess(AccessToken token) {
        SharedPreferences prefs = getSharedPreferences("AUTH_TOKEN", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        this.token.setText(token.getAccess_token());
        Log.d("token", token.getAccess_token());
        Toast.makeText(this, "TOKENEN" + token.getAccess_token(),Toast.LENGTH_LONG).show();
        editor.putString("ACCESS_TOKEN", token.getAccess_token());
        editor.putString("REFRESH_TOKEN", token.getRefresh_token());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onFailed(Throwable t) {
            Toast.makeText(this, "FAILEDDDDDD",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSubmitSuccess(SubmitOutput output) {
        Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();   
    }

    @Override
    public void onSubmitFailed(Throwable t) {
        Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show();
        Log.d("Failed",t.toString());
    }

    @Override
    public void onListLoaded(PrinterList list) {
        Log.d("LISTLOADED", "loaded");
        Log.d("SIZEE",String.valueOf(list.getPrinters().size()));
        adapter.adddata(list.getPrinters());
        rview.setVisibility(View.VISIBLE);
    }

    @Override
    public void OnListLoadingFailed(Throwable t) {
        Toast.makeText(this,t.getMessage(),Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==500 && resultCode == RESULT_OK)
        {
            ClipData data1  = data.getClipData();
//            for(int i=0;i<data1.getItemCount();i++)
//            {
//                uri[i] =  data1.getItemAt(i).getUri();
//            }
            Uri uri=data.getData();
            try {
                Toast.makeText(this, uri.getPath(), Toast.LENGTH_SHORT).show();

                InputStream inputStream = this.getContentResolver().openInputStream(uri);
                File file = new File(uri.getPath());
//                Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, file.getPath(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, inputStream.toString(), Toast.LENGTH_SHORT).show();
                byte[] bytes = new byte[1024];
                inputStream.read(bytes);
                inputStream.close();
                String content = Base64.encodeToString(bytes,Base64.DEFAULT);
                AuthService service = new AuthService();
                String cjt  = new GsonBuilder().create().toJson(new CJT());
                Log.d("GSON", cjt);
                service.submitJob("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf","hello",cjt, MainActivity.this,tokenn);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "File not found", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void selectPdf(View view)
    {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("application/pdf");
        startActivityForResult(i, 500);
    }
}
