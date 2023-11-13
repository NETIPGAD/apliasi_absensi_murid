package com.example.apliasi_absensi_murid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apliasi_absensi_murid.R;
import com.example.apliasi_absensi_murid.model.LoginResponse;
import com.example.apliasi_absensi_murid.network.ServiceClient;
import com.example.apliasi_absensi_murid.network.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {

    EditText etNIS, etPass;
    Spinner spTigkatan, spTahunAjaran;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etNIS = findViewById(R.id.NIS);
        etPass = findViewById(R.id.pass);
        spTahunAjaran = findViewById(R.id.tahunAjaran);
        spTigkatan = findViewById(R.id.tingkatan);

        pd = new ProgressDialog(this);
    }

    public void login(View view) {
        pd.setMessage("Loading...");
        pd.show();

        if(etNIS.getText().toString().isEmpty()){
            pd.dismiss();
            Toast.makeText(this,"nis tidak boleh kosong",Toast.LENGTH_SHORT).show();
            return;
        }
        if(etPass.getText().toString().isEmpty()){
            pd.dismiss();
            Toast.makeText(this,"password tidak boleh kosong",Toast.LENGTH_SHORT).show();
            return;
        }

        String nis = etNIS.getText().toString().trim();
        String Pass = etPass.getText().toString().trim();

        String tingkatan = spTigkatan.getSelectedItem().toString();
        String tahunAjaran = spTahunAjaran.getSelectedItem().toString();

        ServiceClient service = ServiceGenerator.createService(ServiceClient.class);

        Call<LoginResponse> requestLogin = service.loginSiswa("loginsiswa",
                "login",
                tingkatan,tahunAjaran,nis,Pass);

        requestLogin.enqueue((new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                pd.dismiss();
                if (response.body().getHasil().equals("success")){
                    startActivity(new Intent(login.this,dashboard.class));
                    finish();
                }else {
                    Toast.makeText(login.this,"Login gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(login.this,"Koneksi error",Toast.LENGTH_SHORT).show();

            }
        }));






    }
}