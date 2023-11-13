package com.example.apliasi_absensi_murid.network;

import com.example.apliasi_absensi_murid.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceClient {
    @FormUrlEncoded
    @POST("exec")
    Call<LoginResponse> loginSiswa(
            @Field(value = "sheetName", encoded = true) String sheetName,
            @Field(value = "action", encoded = true) String login,
            @Field(value = "tingkatan", encoded = true) String tingkatan,
            @Field(value = "tahunAjaran", encoded = true) String tahunAjaran,
            @Field(value = "nis", encoded = true) String nis,
            @Field(value = "pass", encoded = true) String pass);
}