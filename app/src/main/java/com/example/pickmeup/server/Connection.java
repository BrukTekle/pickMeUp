package com.example.pickmeup.server;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import okhttp3.*;

public class Connection {
    private final OkHttpClient httpClient = new OkHttpClient();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String login(String email, String password) throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url("http://62.151.177.60/api/v1/login")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String signup(String firstname, String lastname, String phone, String email, String password) throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("firstname", firstname)
                .add("lastname", lastname)
                .add("phone", phone)
                .add("email", email)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url("http://62.151.177.60/api/v1/signup")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String update(String email, String car_type, String color, String plate) throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("car_type", car_type)
                .add("color", color)
                .add("plate", plate)
                .build();

        Request request = new Request.Builder()
                .url("http://62.151.177.60/api/v1/update")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String add(String driver_id, String departure, String from_lng, String from_lat, String to_lng, String to_lat, String fare, String seats_available, String comment) throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("driver_id", driver_id)
                .add("departure", departure)
                .add("from_lng", from_lng)
                .add("from_lat", from_lat)
                .add("to_lng", to_lng)
                .add("to_lat", to_lat)
                .add("fare", fare)
                .add("seats_available", seats_available)
                .add("comment", comment)
                .build();

        Request request = new Request.Builder()
                .url("http://62.151.177.60/api/v1/add")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String check(String departure, String from_lng, String from_lat, String to_lng, String to_lat) throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("departure", departure)
                .add("from_lng", from_lng)
                .add("from_lat", from_lat)
                .add("to_lng", to_lng)
                .add("to_lat", to_lat)
                .build();

        Request request = new Request.Builder()
                .url("http://62.151.177.60/api/v1/check")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String posted(String email) throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .build();

        Request request = new Request.Builder()
                .url("http://62.151.177.60/api/v1/posted")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }
}
